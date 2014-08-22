<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:xs="http://www.w3.org/2001/XMLSchema" 
extension-element-prefixes="xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/smsGroup">
		<SENDSMS xmlns="http://smsapi.bulk.gmsu.ua/sms.php">
			<VERSION>1.0</VERSION>
			<SDATE/>
			<DDATE/>
			<VP>120</VP>
			<SENDER>NovaPoshta</SENDER>
			<SEPERATOR>:</SEPERATOR>
			<TM_LIST>
				<TM>
				<DST_MSISDN_LIST>
					<xsl:for-each select="sms">
						<xsl:variable name="reqId" select="id" />
						<xsl:variable name="msgText" select="text" />
						<DST_MSISDN	extraID = "{$reqId}" param = "{$msgText}">	
							<xsl:value-of select="phoneNumber"/>
						</DST_MSISDN>
					</xsl:for-each>
				</DST_MSISDN_LIST>
				<CONTENT_LIST>
					<CONTENT>
						<CONTENT_TEXT> {1} </CONTENT_TEXT>
					</CONTENT>
				</CONTENT_LIST>
				</TM>
			</TM_LIST>			
		</SENDSMS>
	</xsl:template>
</xsl:stylesheet>

<!-- 
<smsGroup>
    <sms>
        <id>123</id>
        <text>123123</text>
        <phoneNumber>0663282837</phoneNumber>
        <isStateChanged>false</isStateChanged>
    </sms>
    <sms>
        <id>456</id>
        <text>456456</text>
        <phoneNumber>0663282836</phoneNumber>
        <isStateChanged>false</isStateChanged>
    </sms>
</smsGroup>

[               qtp658525208-63] direct-gmsu                    INFO  Request to GMSU <?xml version="1.0" encoding="UTF-8"?><SENDSMS xmlns="http://smsapi.bulk.gmsu.ua/sms.php">
<VERSION>1.0</VERSION>
<SDATE/>
<DDATE/>
<VP>120</VP>
<SENDER>NovaPoshta</SENDER>
<SEPERATOR>:</SEPERATOR>
<TM_LIST>
<TM>
<DST_MSISDN_LIST/>
<CONTENT_LIST>
<CONTENT>
<CONTENT_TEXT> {1} </CONTENT_TEXT>
</CONTENT>
</CONTENT_LIST>
</TM>
</TM_LIST>
</SENDSMS>
 -->