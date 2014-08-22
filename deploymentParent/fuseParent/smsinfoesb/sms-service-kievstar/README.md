profile-create --parents jboss-fuse-medium sms-impl

profile-edit --features camel-cxf sms-impl
profile-edit --features camel-jpa sms-impl
profile-edit --features camel-blueprint sms-impl
profile-edit --bundles mvn:org.hibernate.javax.persistence/hibernate-jpa-2.1-api/1.0.0.Final sms-impl
profile-edit --bundles wrap:http://nexus.np.ua/nexus/content/groups/public_snapshots/com/microsoft/sqlserver/sqljdbc4/4.0/sqljdbc4-4.0.jar sms-impl
profile-edit --bundles mvn:ua.np.services/sms-service-api/1.0.4-SNAPSHOT sms-impl
profile-edit --bundles mvn:ua.np.services/sms-service-impl/1.0.4-SNAPSHOT sms-impl

container-create-child --profile sms-impl root smsimpl
