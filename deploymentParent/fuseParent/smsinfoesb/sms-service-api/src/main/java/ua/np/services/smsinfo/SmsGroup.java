/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package ua.np.services.smsinfo;

/**
 * Copyright (C) 2014 Nova Poshta. All rights reserved.
 * http://novaposhta.ua/
 * Author: soloviov.d
 * email: soloviov.d@novaposhta.ua
 */

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *	Represents the Group Entity that is the work unit of the service  
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SmsGroup implements Serializable {

	private static final long serialVersionUID = SmsGroup.class.getName().hashCode();
	
	private String senderGroupId;
	private List<Sms> sms;
	
	public List<Sms> getSms() {
		return sms;
	}

	public void setSms(List<Sms> sms) {
		this.sms = sms;
	}

	public String getSenderGroupId() {
		return senderGroupId;
	}

	public void setSenderGroupId(String senderGroupId) {
		this.senderGroupId = senderGroupId;
	}


}
