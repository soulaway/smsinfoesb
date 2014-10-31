<For clean fuse local run> fabric:create --clean --wait-for-provisioning

New profile details:

	Parent profile
	Jboss-fuse-full

	Features:
	 camel-cxf 
	 camel-blueprint 
	 camel-jpa 
	 
	Bundles:
	 wrap:http://nexus.np.ua/nexus/content/groups/public_snapshots/com/microsoft/sqlserver/sqljdbc4/4.0/sqljdbc4-4.0.jar 
	 mvn:org.hibernate.javax.persistence/hibernate-jpa-2.1-api/1.0.0.Final 
	 mvn:ua.np.services/sms-service/1.0.5-SNAPSHOT 
	 mvn:ua.np.services/sms-service-api/1.0.5-SNAPSHOT 
	 
Profile must be deployed to Root container.