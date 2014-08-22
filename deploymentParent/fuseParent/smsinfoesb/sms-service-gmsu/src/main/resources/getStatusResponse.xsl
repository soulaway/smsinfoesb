<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:output method="xml" encoding="UTF-8" indent="no"/>
    <xsl:template match="/">
    	<smsList>
        <xsl:for-each select="STATUSRETURN/STATUS_LIST/STATUS">
	               <id><xsl:value-of select="MSGID"/></id>
                   <status><xsl:value-of select="MSGSTAT"/></status>
        </xsl:for-each>
        </smsList>
    </xsl:template>
</xsl:stylesheet>