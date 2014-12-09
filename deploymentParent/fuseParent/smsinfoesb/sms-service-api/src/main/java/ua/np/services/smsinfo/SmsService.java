package ua.np.services.smsinfo;

/**
 * Copyright (C) 2014 Nova Poshta. All rights reserved. http://novaposhta.ua/
 * Author: soloviov.d email: soloviov.d@novaposhta.ua
 */

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService
public interface SmsService {
		@WebMethod
	public SmsBulk saveMessages (@WebParam(name = "bulk") SmsBulk bulk);

	@WebMethod
	public String getStatesChangeCount (@WebParam(name = "systemName") String systemName);

	@WebMethod
	public SmsBulk getStates (@WebParam(name = "systemName") String systemName);
}
