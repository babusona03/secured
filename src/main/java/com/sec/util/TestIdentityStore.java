//package com.sec.util;
//
//
//import static java.util.Arrays.asList;
//import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
//
//import java.util.HashSet;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.security.enterprise.credential.UsernamePasswordCredential;
//import javax.security.enterprise.identitystore.CredentialValidationResult;
//import javax.security.enterprise.identitystore.IdentityStore;
//
//@ApplicationScoped
//public class TestIdentityStore implements IdentityStore {
//
//    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {
//    	System.out.println("IdentityStore implementation intercepted. inside validate().");
//        if (usernamePasswordCredential.compareTo("user", "password")) {
//            return new CredentialValidationResult("user", new HashSet<>(asList("kaz", "bar")));
//        }
//        if (usernamePasswordCredential.compareTo("admin", "admin")) {
//            return new CredentialValidationResult("admin", new HashSet<>(asList("foo", "kaz")));
//        }
//
//        return INVALID_RESULT;
//    }
//
//}