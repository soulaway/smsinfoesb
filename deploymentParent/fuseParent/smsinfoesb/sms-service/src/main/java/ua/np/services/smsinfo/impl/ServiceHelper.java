package ua.np.services.smsinfo.impl;

import ua.np.services.smsinfo.Sms;
import ua.np.services.smsinfo.SmsGroup;

public class ServiceHelper {

    public SmsGroup setSenderName(SmsGroup group, String senderName){
        for (Sms sms:group.getSms()){
        	sms.setSenderName(senderName);        
        }
        return group;
    }

}
