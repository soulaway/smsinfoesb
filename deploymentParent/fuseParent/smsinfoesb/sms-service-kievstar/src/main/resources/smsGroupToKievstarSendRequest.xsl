<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"

                extension-element-prefixes="xs">
    <xsl:output method="xml" encoding="UTF-8" indent="no"/>
    <xsl:template match="/smsGroup">
        <root xmlns="http://goldentele.com/cpa">
            <service>bulk-request</service>
            <expiry/>
            <tid>1</tid>
            <messages>
                <xsl:for-each select="sms">
                    <message>
                        <IDint>
                            <xsl:value-of select="id"/>
                        </IDint>

                        <sin>
                            <!--if phoneNumber.length = 8, concat 38-->
                            <xsl:variable name="phoneNumber"
                                          select="phoneNumber"/>
                            <xsl:variable name = "lengthSt"
                                          select = "string-length($phoneNumber)" />
                            <xsl:choose>
                                <xsl:when test='$lengthSt=12'>
                                    <xsl:value-of select="$phoneNumber" />
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of select='concat("380", substring($phoneNumber, (number($lengthSt)-8), $lengthSt))' />
                                </xsl:otherwise>
                            </xsl:choose>
                        </sin>

                        <body content-type="text/plain">
                            <xsl:value-of select="text"/>
                        </body>
                    </message>
                </xsl:for-each>
            </messages>
        </root>
    </xsl:template>
</xsl:stylesheet>
