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

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

/**
 *	Represents the Bulk POJO received from Leagcy adapter  
 */

public class SmsBulk implements Serializable {
	private static final long serialVersionUID = 7236005798526643034L;

	private String systemName;
	private List<Sms> sms;

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public List<Sms> getSms() {
		return sms;
	}

	public void setSms(List<Sms> sms) {
		this.sms = sms;
	}

/*	
 	@Override
	public int hashCode() {
		int hash = 0;
		hash += (incomingId != null ? incomingId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SmsBulk)) {
			return false;
		}
		SmsBulk other = (SmsBulk) object;
		if ((this.incomingId == null && other.incomingId != null)
				|| (this.incomingId != null && !this.incomingId.equals(other.incomingId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "BULK[ incomingId=" + this.incomingId + " senderName=" + this.systemName + "]";
	}
*/

}
