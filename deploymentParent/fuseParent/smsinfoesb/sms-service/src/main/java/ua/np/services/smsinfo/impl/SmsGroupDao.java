package ua.np.services.smsinfo.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ua.np.services.smsinfo.Sms;
import ua.np.services.smsinfo.SmsBulk;
import ua.np.services.smsinfo.SmsGroup;

/**
 * Copyright (C) 2014 Nova Poshta. All rights reserved.
 * http://novaposhta.ua/
 * <p/>
 * for internal use only!
 * <p/>
 * User: yushchenko.i
 * email: yushchenko.i@novaposhta.ua
 * Date: 28.07.2014
 */
public class SmsGroupDao {

    private EntityManager em;
    private static final int SMS_3DAYS_END_TIME_OFFSET = 3 * 86400000; // 3 days
    
    public EntityManager getEntityManager() {
        return em;
    }

    public void setEntityManager( EntityManager em ) {
        this.em = em;
    }
    
    /* first group creation, sms updates with groupId, all things get state "Pending"*/
    public void mergeSmsGroup(SmsGroup smsGroup){
        for (Sms sms : smsGroup.getSms()){
        	getEntityManager().createNamedQuery("updateSmsStateAndFlag")
	            .setParameter(1, sms.getState())
	            .setParameter(2, true)
	            .setParameter(3, sms.getSenderId())
	            .setParameter(4, sms.getSenderGroupId())
	            .setParameter(5, sms.getSenderName())
	            .setParameter(6, sms.getId())
	            .executeUpdate();
        }
    }

    public void updateSmsStatesBySenderId(SmsGroup smsGroup){
        for (Sms sms : smsGroup.getSms()){
            getEntityManager().createNamedQuery("updateSmsStateAndFlagBySenderId")
                    .setParameter(1, sms.getState())
                    .setParameter(2, true)
                    .setParameter(3, sms.getSenderId())
                    .executeUpdate();
        }

    }

    public void persistSms(SmsBulk smsBulk){
        for(Sms msg : smsBulk.getSms()){
        	msg.setEndDateTime(new Date(System.currentTimeMillis() + SMS_3DAYS_END_TIME_OFFSET));
            getEntityManager().persist(msg);
        }
    }

    public int getDeliveryStatusChangeCount(String systemName){
    	int count = ((Number)getEntityManager().createNamedQuery("getChangeCount").setParameter(1, true).setParameter(2, systemName).getSingleResult()).intValue();
    	return count;
    }
    
    public SmsGroup getStates(String systemName){
    	SmsGroup smsGroup = null; 
    	Query query = getEntityManager().createNamedQuery("getStates").setParameter(1, true).setParameter(2, systemName);
		List<Sms> sms = query.getResultList();
		if (sms != null && sms.size() > 0){
			smsGroup = new SmsGroup();
			smsGroup.setSms(sms);
        	getEntityManager().createNamedQuery("updateSmsFlag")
            .setParameter(1, false)
            .setParameter(2, systemName)
            .setParameter(3, true)
            .executeUpdate();
		 } else {
			 throw new IllegalStateException("smsinfo-service: no states changes for " + systemName, new Throwable("No states were returned by getStates request."));
		}
		return smsGroup;
    }
    
    public  SmsGroup getNotAcceptedGmsuGroup(String systemName){
        SmsGroup group = new SmsGroup();
        List<Sms> messages = getEntityManager().createNamedQuery("getNotDelivered").setParameter(1, "Delivered").setParameter(2, "Gmsu").setParameter(3, new Date(System.currentTimeMillis())).getResultList();
        group.setSms(messages);
	    return group;
    }
    
    
    /* update states by timer from Gmsu*/
    /*
		0	–ще не відправлено;
		1	– відправлено;
		2	– успішно відправлено (тобто отримано звіт про прийняття повідомлення від СМСЦ);
		3	– відправка відхилена.;
		4	 – повідомлення  доставлене;
		5	 - повідомлення недоставлене.
		У випадку коли повідомлення знаходиться у статусі «3» чи «5». Тег REASON буде містить пояснення причини статуса повідомлення. Він може принймати наступні значення:
		-	«Internal error: Timeout»;
		-	«Internal error: Invalid message»;
		-	«Internal error: Unknown»;
		-	«External error: Enroute»;
		-	«External error: Expired»;
		-	«External error: Deleted»;
		-	«External error: Undeliverable»;
		-	«External error: Rejected»;
		-	«External error: Unknown»;
     */
    public void updateGmsuStates(SmsGroup smsGroup){
        for (Sms sms : smsGroup.getSms()){
        	/*only for gmsu state handling*/
			if (sms.getState().equals("0")) {
				sms.setState("Accepted"); // status wasn't changed
			} else if (sms.getState().equals("1")) {
				sms.setState("Delivered"); // message was send
			} else if (sms.getState().equals("2")) {
				sms.setState("Delivered"); // message was send, report was returned from SMSCenter
			} else if (sms.getState().equals("3")) {
				sms.setState("Failed");	// rejected
			} else if (sms.getState().equals("4")) {
				sms.setState("Delivered"); // message was successfully delivered
			} else if (sms.getState().equals("5")) {
				sms.setState("Failed"); // delivered failed
			} 
        	getEntityManager().createNamedQuery("updateSmsStateAndFlagById")
                    .setParameter(1, sms.getState())
                    .setParameter(2, true)
                    .setParameter(3, sms.getId())
                    .executeUpdate();
        }

    }
}
