package ua.np.services.smsinfo.gmsu;

import ua.np.services.smsinfo.Sms;
import ua.np.services.smsinfo.SmsGroup;
import ua.np.services.smsinfo.StateHelper;

public class GmsuHelper {
	public SmsGroup updateGroupAfterSendMessagesResponce(SmsGroup group, String groupId){
		for (Sms sms : group.getSms()) {
			sms.setSenderName("Gmsu");
			sms.setSenderGroupId(groupId);
			sms.setState(StateHelper.STATE_SENDING.value());
		}
		return group;
	}
}