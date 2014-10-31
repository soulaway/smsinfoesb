<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:xs="http://www.w3.org/2001/XMLSchema" 
extension-element-prefixes="xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes" cdata-section-elements="Array"/>
	<xsl:template match="/smsBulk">
		<soapenv:Envelope 
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
		xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
		xmlns:urn="urn:SMSServiceControllerwsdl">
		   <soapenv:Header/>
		   <soapenv:Body>
		      <urn:sendMessages soapenv:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">
		        <return xsi:type="xsd:string">
		        	&lt;?xml version="1.0" encoding="UTF-8"?&gt;
					&lt;Array&gt;
						<xsl:for-each select="sms">
							&lt;Structure&gt;
								&lt;Value name="IDIncoming"&gt;
									&lt;Type&gt;String&lt;/Type&gt;
									&lt;Data&gt;<xsl:value-of select="incomingId"/>&lt;/Data&gt;
								&lt;/Value&gt;
								&lt;Value name="IDInternal"&gt;
									&lt;Type&gt;String&lt;/Type&gt;
									&lt;Data&gt;<xsl:value-of select="id"/>&lt;/Data&gt;
								&lt;/Value&gt;
							&lt;/Structure&gt;						
						</xsl:for-each>
					&lt;/Array&gt;
		         </return>
		      </urn:sendMessages>
		   </soapenv:Body>
		</soapenv:Envelope>
	</xsl:template>
</xsl:stylesheet>

<!--
FROM
 
<smsBulk>
    <systemName>awis</systemName>
    <sms>
        <id>1406556319854</id>
        <incomingId>1</incomingId>
        <text>test1</text>
        <phoneNumber>0663282837</phoneNumber>
        <isStateChanged>false</isStateChanged>
        <systemName>awis</systemName>
    </sms>
    <sms>
        <id>1406556319855</id>
        <incomingId>2</incomingId>
        <text>test2</text>
        <phoneNumber>0963282837</phoneNumber>
        <isStateChanged>false</isStateChanged>
        <systemName>awis</systemName>
    </sms>
</smsBulk>

TO

<?xml version="1.0" encoding="utf-8"?>
<Array>
	<Structure>
		<Value name="IDIncoming">
			<Type>String</Type>
			<Data>1</Data>
		</Value>
		<Value name="IDInternal">
			<Type>String</Type>
			<Data>1406556319854</Data>
		</Value>
	</Structure>
	<Structure>
		<Value name="IDIncoming">
			<Type>String</Type>
			<Data>2</Data>
		</Value>
		<Value name="IDInternal">
			<Type>String</Type>
			<Data>1406556319855</Data>
		</Value>
	</Structure>
</Array>
 -->