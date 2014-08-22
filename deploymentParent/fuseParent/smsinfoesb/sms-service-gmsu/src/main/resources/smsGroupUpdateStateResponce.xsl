<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:output method="xml" encoding="UTF-8" indent="no"/>
    <xsl:template match="/">
     	<smsGroup>
        <xsl:for-each select="STATUSRETURN/STATUS_LIST/STATUS">
			<sms>
                <id><xsl:value-of select="MSGID"/></id>
				<state><xsl:value-of select="MSGSTAT"/></state>
			</sms>
        </xsl:for-each>
        </smsGroup>
    </xsl:template>
</xsl:stylesheet>