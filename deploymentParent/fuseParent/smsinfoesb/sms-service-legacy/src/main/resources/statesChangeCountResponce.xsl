<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:urn="urn:SMSServiceControllerwsdl">
<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="statusRequest">
		<soapenv:Envelope 
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
		xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
		xmlns:urn="urn:SMSServiceControllerwsdl">
		   <soapenv:Header/>
		   <soapenv:Body>
		      <urn:getDeliveryStatusChangeCount soapenv:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">
		        <return xsi:type="xsd:string">
		        	&lt;?xml version="1.0" encoding="UTF-8"?&gt;
					&lt;Value&gt;
						&lt;Type&gt;Number&lt;/Type&gt;
						&lt;Data&gt;<xsl:value-of select="changeCount"/>&lt;/Data&gt;
					&lt;/Value&gt;
		         </return>
		      </urn:getDeliveryStatusChangeCount>
		   </soapenv:Body>
		</soapenv:Envelope>			
	</xsl:template>
</xsl:stylesheet>