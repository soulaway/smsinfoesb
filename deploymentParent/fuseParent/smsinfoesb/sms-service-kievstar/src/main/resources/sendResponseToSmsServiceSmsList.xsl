<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:input="http://goldetele.com/cpa" exclude-result-prefixes="input">
    <xsl:template match="/">
    	<smsGroup>
        <xsl:for-each select="//input:status">
            <sms>
	            <id><xsl:value-of select="@clid"/></id>
	            <senderId><xsl:value-of select="@mid"/></senderId>
	            <state><xsl:value-of select="."/></state>
            </sms>
        </xsl:for-each>
        </smsGroup>
    </xsl:template>
</xsl:stylesheet>