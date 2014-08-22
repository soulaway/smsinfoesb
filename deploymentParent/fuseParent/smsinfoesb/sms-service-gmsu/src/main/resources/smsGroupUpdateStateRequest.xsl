<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:output method="xml" encoding="UTF-8" indent="no"/>
    <xsl:template match="/">
<GETSTATUS>
<VERSION>1.0</VERSION>
 <MSGID_LIST>
        <xsl:for-each select="smsGroup/sms">
	               <MSGID><xsl:value-of select="id"/></MSGID>
        </xsl:for-each>
</MSGID_LIST>
</GETSTATUS>
    </xsl:template>
</xsl:stylesheet>