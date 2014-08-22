<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:urn="urn:SMSServiceControllerwsdl">
<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="statusRequest">
	<Value>
		<Type>Number</Type>
		<Data><xsl:value-of select="changeCount"/></Data>
	</Value>
	</xsl:template>
</xsl:stylesheet>