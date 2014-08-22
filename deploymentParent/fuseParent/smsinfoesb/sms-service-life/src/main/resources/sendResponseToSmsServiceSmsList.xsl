<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" version="1.0"
                encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <smsGroup>
            <xsl:for-each select="//detail">
	            <sms>
	                <senderId><xsl:value-of select="id"/></senderId>
	                <state><xsl:value-of select="state"/></state>
	            </sms>
            </xsl:for-each>
        </smsGroup>
    </xsl:template>
</xsl:stylesheet>


        <!-- FROM-->
        <!--
        <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<status groupid="1" date="Fri, 11 Jul 2014 15:09:20 +0300">
<detail>
    <id>155892621</id>
    <state>Accepted</state>
</detail>
<detail>
    <senderId>155892622</senderId>
    <state>Accepted</state>
</detail>
</status>
-->


        <!-- TO-->
<!--
<?xml version="1.0" encoding="UTF-8"?>
<smsList>
    <senderId>{mid}</senderId>
    <state>{status}</state>
</smsList>
<smsList>
    <senderId>{mid}</senderId>
    <state>{status}</state>
</smsList>
-->