<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:urn="urn:SMSServiceControllerwsdl">
<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="smsBulk">
		<soapenv:Envelope 
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
		xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
		xmlns:urn="urn:SMSServiceControllerwsdl">
		   <soapenv:Header/>
		   <soapenv:Body>
		      <urn:getDeliveryStatusData soapenv:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">
		        <return xsi:type="xsd:string">
		        	&lt;?xml version="1.0" encoding="UTF-8"?&gt;
					&lt;Structure&gt;
					&lt;Value name="ChangesUUID"&gt;
						&lt;Type&gt;String&lt;/Type&gt;
						&lt;Data&gt;00000000-0000-0000-0000-000000000000&lt;/Data&gt;
					&lt;/Value&gt;
						&lt;ValueTable name="StatusData"&gt;
							&lt;Columns&gt;
								&lt;Column&gt;
									&lt;Name&gt;IdInternal&lt;/Name&gt;
								&lt;/Column&gt;
								&lt;Column&gt;
									&lt;Name&gt;CurrentStatus&lt;/Name&gt;
								&lt;/Column&gt;
							&lt;/Columns&gt;
							&lt;Rows&gt;
								<xsl:for-each select="sms">
									&lt;Row&gt;
										&lt;Value name="IdInternal"&gt;
											&lt;Type&gt;String&lt;/Type&gt;
											&lt;Data&gt;<xsl:value-of select="incomingId"/>&lt;/Data&gt;
										&lt;/Value&gt;
										&lt;Value name="CurrentStatus"&gt;
											&lt;Type&gt;String&lt;/Type&gt;
											&lt;Data&gt;<xsl:value-of select="state"/>&lt;/Data&gt;
										&lt;/Value&gt;
									&lt;/Row&gt;						
								</xsl:for-each>		
							&lt;/Rows&gt;
						&lt;/ValueTable&gt;
					&lt;/Structure&gt;
		         </return>
		      </urn:getDeliveryStatusData>
		   </soapenv:Body>
		</soapenv:Envelope>		
	</xsl:template>
</xsl:stylesheet>