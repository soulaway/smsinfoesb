<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" version="1.0"
                encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <message>
            <xsl:variable name="uniqId" select="//id"/>
<!--
			<xsl:variable name="expDate" select="//endDateTime"/>
            <service id="individual" validity='{$expDate}' source="NovaPoshta" uniq_key='{$uniqId}'/> 
-->
            <service id="individual" validity="+5 hour" source="NovaPoshta" uniq_key='{$uniqId}'/>
            <xsl:for-each select="//sms">
                <to><xsl:value-of select="phoneNumber"/></to>
                <body content-type="text/plain">
                    <xsl:value-of select="text"/>
                </body>
            </xsl:for-each>
        </message>
    </xsl:template>
</xsl:stylesheet>
