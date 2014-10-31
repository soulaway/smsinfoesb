<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" version="1.0"
                encoding="UTF-8" indent="yes"/>
<!-- 
o Accepted - сообщение принято IP2SMS-платформой, но попытка доставки еще не предпринималось
o Enroute - предпринимаются попытки доставить сообщение, однако, оно еще не доставлено
o Delivered - сообщение доставлено получателю
o Expired - исчерпан лимит времени на попытки доставить сообщение; последующие попытки предприниматься не будут
o Deleted - сообщение принудительно удалено из системы администратором
o Undeliverable - сообщение по тем или иным причинам не может быть доставлено получателю (например, попытка доставить на несуществующий телефонный номер)
o Rejected - сообщение отвергнуто из-за ошибок в нем (нарушение формата, попытка отправить сообщение пределы украинских операторов и т.п.)
o Unknown - состояние сообщения неизвестно.
Если статус отличен от Accepted, Enroute и Delivered, атрибут error тэга state может содержать уточняющую информацию об ошибке
 -->
    <xsl:template match="/">
        <smsGroup>
            <xsl:for-each select="//detail">
	            <sms>
	                <senderId><xsl:value-of select="id"/></senderId>
	                <!-- TODO convert life states to internal -->
	                <!-- <state><xsl:value-of select="state"/></state> -->
					<xsl:variable name="state"
					              select="state"/>
					<xsl:choose>
					    <xsl:when test='$state=Enroute'>
					        <state>0</state>
					    </xsl:when>
					    <xsl:when test='$state=Accepted'>
					        <state>1</state>
					    </xsl:when>
					    <xsl:when test='$state=Delivered'>
					        <state>4</state>
					    </xsl:when>
					    <xsl:when test='$state=Expired'>
					        <state>5</state>
					        <failReason><xsl:value-of select="@error"/></failReason>
					    </xsl:when>
					    <xsl:when test='$state=Deleted'>
					        <state>3</state>
					        <failReason><xsl:value-of select="@error"/></failReason>
					    </xsl:when>
					    <xsl:when test='$state=Undeliverable'>
					        <state>5</state>
					        <failReason><xsl:value-of select="@error"/></failReason>
					    </xsl:when>
					    <xsl:when test='$state=Rejected'>
					        <state>3</state>
					        <failReason><xsl:value-of select="@error"/></failReason>
					    </xsl:when>
					    <xsl:when test='$state=Unknown'>
					        <state>-1</state>
					        <failReason><xsl:value-of select="@error"/></failReason>
					    </xsl:when>
					</xsl:choose>
	            </sms>
            </xsl:for-each>
        </smsGroup>
    </xsl:template>
</xsl:stylesheet>