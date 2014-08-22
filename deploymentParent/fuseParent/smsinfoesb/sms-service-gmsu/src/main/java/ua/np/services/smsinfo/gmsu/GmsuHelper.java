package ua.np.services.smsinfo.gmsu;

import ua.np.services.smsinfo.Sms;
import ua.np.services.smsinfo.SmsGroup;

public class GmsuHelper {
	public SmsGroup updateGroupAfterSendMessagesResponce(SmsGroup group, String groupId){
		for (Sms sms : group.getSms()) {
			sms.setSenderGroupId(groupId);
			sms.setState("Accepted");
		}
		return group;
	}
}