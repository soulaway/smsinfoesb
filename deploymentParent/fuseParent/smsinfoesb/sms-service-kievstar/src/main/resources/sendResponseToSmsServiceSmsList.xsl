<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:input="http://goldetele.com/cpa" exclude-result-prefixes="input">
    <xsl:template match="/">
    	<smsGroup>
        <xsl:for-each select="//input:status">
            <sms>
            	<senderName>Kievstar</senderName>
	            <id><xsl:value-of select="@clid"/></id>
	            <senderId><xsl:value-of select="@mid"/></senderId>
				<!--states adjusting-->
				<xsl:variable name="state"
				              select="."/>
				<xsl:choose>
				    <xsl:when test='$state=Accepted'>
				        <state>0</state>
				    </xsl:when>
				    <xsl:when test='$state=Delivering'>
				        <state>1</state>
				    </xsl:when>
				    <xsl:when test='$state=Delivered'>
				        <state>4</state>
				    </xsl:when>
				    <xsl:when test='$state=Undelivered'>
				        <state>5</state>
				        <failReason><xsl:value-of select="@error"/></failReason>
				    </xsl:when>
				</xsl:choose>
            </sms>
        </xsl:for-each>
        </smsGroup>
    </xsl:template>
</xsl:stylesheet>