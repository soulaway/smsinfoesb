<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:xs="http://www.w3.org/2001/XMLSchema" 
extension-element-prefixes="xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/smsBulk">
		<Array>
			<xsl:for-each select="sms">
				<Structure>
					<Value name="IDIncoming">
						<Type>String</Type>
						<Data><xsl:value-of select="incomingId"/></Data>
					</Value>
					<Value name="IDInternal">
						<Type>String</Type>
						<Data><xsl:value-of select="id"/></Data>
					</Value>
				</Structure>						
			</xsl:for-each>
		</Array>
	</xsl:template>
</xsl:stylesheet>

<!--
FROM
 
<smsBulk>
    <systemName>awis</systemName>
    <sms>
        <id>1406556319854</id>
        <incomingId>1</incomingId>
        <text>test1</text>
        <phoneNumber>0663282837</phoneNumber>
        <isStateChanged>false</isStateChanged>
        <systemName>awis</systemName>
    </sms>
    <sms>
        <id>1406556319855</id>
        <incomingId>2</incomingId>
        <text>test2</text>
        <phoneNumber>0963282837</phoneNumber>
        <isStateChanged>false</isStateChanged>
        <systemName>awis</systemName>
    </sms>
</smsBulk>

TO

<?xml version="1.0" encoding="utf-8"?>
<Array>
	<Structure>
		<Value name="IDIncoming">
			<Type>String</Type>
			<Data>1</Data>
		</Value>
		<Value name="IDInternal">
			<Type>String</Type>
			<Data>1406556319854</Data>
		</Value>
	</Structure>
	<Structure>
		<Value name="IDIncoming">
			<Type>String</Type>
			<Data>2</Data>
		</Value>
		<Value name="IDInternal">
			<Type>String</Type>
			<Data>1406556319855</Data>
		</Value>
	</Structure>
</Array>
 -->