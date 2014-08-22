<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:urn="urn:SMSServiceControllerwsdl">
<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="urn:getDeliveryStatusData">
		<statusRequest>
			<systemName>
				<xsl:value-of select="systemName"/>
			</systemName>
		</statusRequest>
	</xsl:template>
</xsl:stylesheet>
