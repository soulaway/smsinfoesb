package ua.np.services.smsinfo.gmsu;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import ua.np.services.smsinfo.SmsGroup;


//@WebService(targetNamespace = "http://smsinfo.services.np.ua")

public interface GmsuTestService {
	@WebMethod
	public String saveMessages (SmsGroup group);
}