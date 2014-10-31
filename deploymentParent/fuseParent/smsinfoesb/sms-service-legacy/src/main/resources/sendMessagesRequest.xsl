<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="data">
		<smsBulk>
			<incomingId></incomingId>
            <xsl:variable name="sysName" select="systemName"/>
			<xsl:for-each select="Structure/Array/Structure">
				<sms>
				<id></id>
				<bulkId></bulkId>
				<groupId></groupId>
				<state></state>
				<xsl:for-each select="Value">
					<xsl:if test="@name = 'id'">
						<incomingId><xsl:value-of select="Data"/></incomingId>		
					</xsl:if>
					<xsl:if test="@name = 'phone'">
						<phoneNumber><xsl:value-of select="Data"/></phoneNumber>	
					</xsl:if>
					<xsl:if test="@name = 'text'">
						<text><xsl:value-of select="Data"/></text>	
					</xsl:if>
                    <systemName><xsl:value-of select="$sysName"/></systemName>
				</xsl:for-each>
				</sms>
			</xsl:for-each>
		</smsBulk>
	</xsl:template>
</xsl:stylesheet>

<!-- 
Converts:
<soapenv:Envelope 
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
xmlns:urn="urn:SMSServiceControllerwsdl">
   <soapenv:Header/>
   <soapenv:Body>
      <urn:sendMessages soapenv:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">
         <xml xsi:type="xsd:string">
			<Structure>
				<Array name="messageArray">
					<Structure>
						<Value name="id">
							<Type>String</Type>
							<Data>ИД СООБЩЕНИЯ 1</Data>
						</Value>
						<Value name="phone">
							<Type>String</Type>
							<Data>ТЕЛЕФОН 1</Data>
						</Value>
						<Value name="text">
							<Type>String</Type>
							<Data>ТЕКСТ 1</Data>
						</Value>
					</Structure>
					<Structure>
						<Value name="id">
							<Type>String</Type>
							<Data>ИД СООБЩЕНИЯ 2</Data>
						</Value>
						<Value name="phone">
							<Type>String</Type>
							<Data>ТЕЛЕФОН 2</Data>
						</Value>
						<Value name="text">
							<Type>String</Type>
							<Data>ТЕКСТ 2</Data>
						</Value>
					</Structure>
				</Array>
			</Structure>         
         </xml>
         <systemName xsi:type="xsd:string">awis</systemName>
      </urn:sendMessages>
   </soapenv:Body>
</soapenv:Envelope>

To:
<?xml version="1.0" encoding="UTF-8"?>
<smsBulk>
	<incomingId/>
	<systemName>Awis</systemName>
	<message>
		<id/>
		<bulkId/>
		<groupId/>
		<state/>
		<incomingId>ИД СООБЩЕНИЯ</incomingId>
		<phoneNumber>ТЕЛЕФОН</phoneNumber>
		<text>ТЕКСТ</text>
	</message>
</smsBulk> 
 -->