package ua.np.services.smsinfo.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.np.services.smsinfo.Sms;
import ua.np.services.smsinfo.SmsBulk;
import ua.np.services.smsinfo.SmsGroup;
import ua.np.services.smsinfo.StateHelper;

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
    private static final int SMS_24H_END_TIME_OFFSET = 86400000;
    
    private Logger log = LoggerFactory.getLogger(SmsGroupDao.class);
    
    public EntityManager getEntityManager() {
        return em;
    }

    public void setEntityManager( EntityManager em ) {
        this.em = em;
    }
    
    /* returns current jpa and hibernate property set as a string for information*/
    public String init(){
    	Map<String, Object> props = getEntityManager().getEntityManagerFactory().getProperties();
    	Set<Entry<String, Object>> set = props.entrySet();
    	String s = "JPA PROPS: \n";
    	for (Entry<String, Object> e : set){
    		s += "property - " + e.getKey() + " : "+ e.getValue().toString() + "\n";
    	}
    	Session session = getEntityManager().unwrap(Session.class);
    	s +="HBN PROPS: session connected" + session.isConnected() + "\n";
    	SessionImplementor sessionImplementor = getEntityManager().unwrap( SessionImplementor.class );
    	Properties hbnprops = sessionImplementor.getFactory().getProperties();
    	Set<Entry<Object, Object>> hbnset = hbnprops.entrySet(); 
    	for (Entry<Object, Object> e : hbnset){
    		s += "property - " + e.getKey().toString() + " : "+ e.getValue().toString() + "\n";
    	}
    	return s;
    }
    
    /* first group creation, sms updates with groupId, all things get state "Pending"*/
    public void mergeSmsGroup(SmsGroup smsGroup){
    	List<Sms> smsList = smsGroup.getSms();
    	if (smsList != null){
	        for (Sms sms : smsList){
	        	getEntityManager().createNamedQuery("updateSmsStateAndFlag")
		            .setParameter(1, sms.getState())
		            .setParameter(2, true)
		            .setParameter(3, sms.getSenderId())
		            .setParameter(4, sms.getSenderGroupId())
		            .setParameter(5, sms.getSenderName())
		            .setParameter(6, sms.getId())
		            .executeUpdate();
	        }
        } else {
        	log.error("Parsing error for smsGroup. Sms list was empty!");
        }
    }

    public void updateSmsStatesBySenderId(SmsGroup smsGroup){
    	List<Sms> smsList = smsGroup.getSms();
    	if (smsList != null){
	        for (Sms sms : smsList){
	        	getEntityManager().createNamedQuery("updateSmsStateAndFlagBySenderId")
	                    .setParameter(1, sms.getState())
	                    .setParameter(2, true)
	                    .setParameter(3, sms.getFailReason())
	                    .setParameter(4, sms.getSenderId())
	                    .executeUpdate();
	        }
        } else {
        	log.error("Parsing error for smsGroup. Sms list was empty!");
        }
    }

    public void persistSms(SmsBulk smsBulk) {
    	List<Sms> smsList = smsBulk.getSms();
    	if (smsList != null) {
	        for(Sms msg : smsList){
	        	msg.setEndDateTime(new Date(System.currentTimeMillis() + SMS_24H_END_TIME_OFFSET));
	            getEntityManager().persist(msg);
	        }
        }
    	else {
    		log.error("Parsing error for smsBulk. Sms list was empty!");
    	}
    }

    public int getDeliveryStatusChangeCount(String systemName){
    	int count = ((Number)getEntityManager().createNamedQuery("getChangeCount").setParameter(1, true).setParameter(2, systemName).getSingleResult()).intValue();
    	return count;
    }
    
    public SmsGroup getStates(String systemName){
    	SmsGroup smsGroup = null; 
    	Query query = getEntityManager().createNamedQuery("getStates").setParameter(1, true).setParameter(2, systemName);
		@SuppressWarnings("unchecked")
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
        @SuppressWarnings("unchecked")
		List<Sms> messages = getEntityManager().createNamedQuery("getNotDelivered").setParameter(1, StateHelper.STATE_SEND_REJECTED.value()).setParameter(2, "Gmsu").setParameter(3, new Date(System.currentTimeMillis())).getResultList();
        group.setSms(messages);
	    return group;
    }
    
    
    /* update states by timer from Gmsu*/
    public void updateGmsuStates(SmsGroup smsGroup){
    	List<Sms> smsList = smsGroup.getSms();
    	if (smsList != null) {
	        for (Sms sms : smsList){
	        	getEntityManager().createNamedQuery("updateSmsStateAndFlagById")
	                    .setParameter(1, sms.getState())
	                    .setParameter(2, true)
	                    .setParameter(3, sms.getFailReason())
	                    .setParameter(4, sms.getId())
	                    .executeUpdate();
	        }
    	} else {
    		log.error("Parsing error for smsBulk. Sms list was empty!");
    	}
    }
}
