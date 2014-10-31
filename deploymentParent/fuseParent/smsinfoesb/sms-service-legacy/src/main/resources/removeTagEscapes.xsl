<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:urn="urn:SMSServiceControllerwsdl">
<xsl:output method="text" encoding="UTF-8" indent="yes"/>
	<xsl:template match="urn:sendMessages">
		&lt;data&gt;
			&lt;systemName&gt;<xsl:value-of select="systemName"/>&lt;/systemName&gt;
			<xsl:value-of select="xml" disable-output-escaping="yes" />
		&lt;/data&gt;
	</xsl:template>
</xsl:stylesheet>