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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "SMS")
@NamedQueries({
	@NamedQuery(name = "updateSmsFlag", query = "UPDATE Sms sms SET sms.isStateChanged = ?1 WHERE sms.systemName = ?2 AND sms.isStateChanged = ?3"),
	@NamedQuery(name = "updateSmsStateAndFlag", query = "UPDATE Sms sms SET sms.state = ?1, sms.isStateChanged = ?2, sms.senderId = ?3, sms.senderGroupId = ?4, sms.senderName = ?5 WHERE sms.id = ?6"),
	@NamedQuery(name = "getChangeCount", query = "SELECT COUNT(sms.id) FROM Sms sms WHERE sms.isStateChanged = ?1 AND sms.systemName = ?2"),
	@NamedQuery(name = "getStates", query = "SELECT sms FROM Sms sms WHERE sms.isStateChanged = ?1 AND sms.systemName = ?2"),
    @NamedQuery(name = "updateSmsStateAndFlagBySenderId", query = "UPDATE Sms sms SET sms.state = ?1, sms.isStateChanged = ?2 WHERE sms.senderId = ?3"),
    @NamedQuery(name = "updateSmsStateAndFlagById", query = "UPDATE Sms sms SET sms.state = ?1, sms.isStateChanged = ?2 WHERE sms.id = ?3"),
    @NamedQuery(name = "getNotDelivered", query = "SELECT sms FROM Sms sms WHERE sms.state != ?1 AND sms.senderName = ?2 AND sms.endDateTime >= ?3")
    }
)

public class Sms implements Serializable {
	
	private static final long serialVersionUID = Sms.class.getName().hashCode();

    @Column(length = 20)
	private String incomingId;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;

	private String text;
    @Column(length = 13)
	private String phoneNumber;
    @Column(length = 20)    
	private String systemName;

    @Column(length = 20)
	private String senderName; // eg. Kievstar, Life, Gmsu    
    @Column(length = 20)
	private String senderId;
	@Column(length = 20)
	private String senderGroupId;
	
    @Column(length = 20)
	private String state;    
	private boolean isStateChanged;
	        
    @Temporal( TemporalType.TIMESTAMP )
    /*@XmlJavaTypeAdapter(MssqlToOracleDateAdapter.class)*/
    private Date endDateTime;

    
	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
    
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenderGroupId() {
		return senderGroupId;
	}

	public void setSenderGroupId(String senderGroupId) {
		this.senderGroupId = senderGroupId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIncomingId() {
		return incomingId;
	}

	public void setIncomingId(String incomingId) {
		this.incomingId = incomingId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public boolean isStateChanged() {
		return isStateChanged;
	}

	public void setStateChanged(boolean isStateChanged) {
		this.isStateChanged = isStateChanged;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Sms)) {
			return false;
		}
		Sms other = (Sms) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SMS[ id=" + this.id + " TEXT=" + this.text + " ]";
	}

}
