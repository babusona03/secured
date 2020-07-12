//package com.sec.util;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Default;
//import javax.inject.Named;
//import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
//import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
//
//@ApplicationScoped
//@Default
//@DatabaseIdentityStoreDefinition(
//		dataSourceLookup = "${dbConfig.jndiDataSource}",
//		callerQuery = "${dbConfig.callerQuery}",
//		groupsQuery = "${dbConfig.groupsQuery}",
//		hashAlgorithm = Pbkdf2PasswordHash.class,
//		hashAlgorithmParameters = "${dbConfig.passphraseConfig}"		
//		)
//@Named
//public class DbConfig {
//	public String getJndiDataSource() {
//		return "java:jboss/datasources/OracleDS";
//	}
//	public String getCallerQuery() {
//		return "select passphrase from users where username=?";
//	}
//	public String getGroupsQuery() {
//		return "select r.rolename as 'role' from permissions p inner join roles r inner join users u on p.roleId=r.id and p.userId=u.id and u.username like ?";
//	}
//	public String[] getPassphraseConfig() {
//		return new String[] { 
//				"Pbkdf2PasswordHash.Iterations=3072", 
//        		"Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512",
//        		"Pbkdf2PasswordHash.SaltSizeBytes=64"
//        		};
//	}
//}
