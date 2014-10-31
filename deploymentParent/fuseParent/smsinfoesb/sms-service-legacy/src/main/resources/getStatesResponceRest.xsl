<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:urn="urn:SMSServiceControllerwsdl">
<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="smsGroup">
		<Structure>
		<Value name="ChangesUUID">
			<Type>String</Type>
			<Data>00000000-0000-0000-0000-000000000000</Data>
		</Value>
			<ValueTable name="StatusData">
				<Columns>
					<Column>
						<Name>IdInternal</Name>
					</Column>
					<Column>
						<Name>CurrentStatus</Name>
					</Column>
				</Columns>
				<Rows>
					<xsl:for-each select="sms">
						<Row>
							<Value name="IdInternal">
								<Type>String</Type>
								<Data><xsl:value-of select="incomingId"/></Data>
							</Value>
							<Value name="CurrentStatus">
								<Type>String</Type>
								<Data><xsl:value-of select="state"/></Data>
							</Value>
						</Row>						
					</xsl:for-each>				
				</Rows>
			</ValueTable>
		</Structure>
	</xsl:template>
</xsl:stylesheet>