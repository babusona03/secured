package com.sec.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.AutoApplySession;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;


import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.CredentialValidationResult.Status;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ApplicationScoped
//In order to ensure that it is available to the Java application, 
//you will need to implement the HttpAuthenticationMechanism interface as a CDI bean with @ApplicationScope.
@AutoApplySession

//https://developer.ibm.com/tutorials/j-javaee8-security-api-2/
/*
 * The interface HttpAuthenticationMechanism defines the following three methods:
		validateRequest() ->	authenticates an HTTP request.
		secureResponse()  ->	secures the HTTP response message.
		cleanSubject() 	  ->	clears the subject of provided principals and credentials.
		All methods accept the same parameter types, 
		which are: HttpServletRequest, HttpServletResponse, and HttpMessageContext. 
		These map to the corresponding methods defined on the JASPIC Server Auth Module interface, 
		which is provided by the container. 
		When a JASPIC method is invoked on Server Auth, 
		it delegates to the corresponding method of your custom HttpAuthenticationMechanism.
 * */

public class ContentAuthenticationMechanism implements HttpAuthenticationMechanism{
	private Map<String,String> users;
	private Map<String,Set<String>> roles;
	
	@Inject 
	private IdentityStoreHandler identityStoreHandler;
	
	
	@PostConstruct
	public void init() {
		users= new HashMap<>();roles= new HashMap<>();
		System.out.println("@PostConstruct called on HAM implementation.");
		users.put("admin", "admin");
		users.put("user", "password");
		users.put("guest", "guest");
		
		roles.put("admin", new HashSet<>(Arrays.asList("admin")));
		roles.put("user", new HashSet<>(Arrays.asList("user")));
		roles.put("guest", new HashSet<>(Arrays.asList("guest")));
		
	}
	@Override
	public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response,
			HttpMessageContext httpMessageContext) throws AuthenticationException {
		
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		UsernamePasswordCredential usernamePasswordCredential = new UsernamePasswordCredential(username, password);
		System.out.println("username:"+username+" password:"+password);
		if(username!=null && password != null) {
			CredentialValidationResult credentialValidationResult = identityStoreHandler.validate(usernamePasswordCredential);
			if(credentialValidationResult.getStatus() == Status.VALID) {
				return httpMessageContext.notifyContainerAboutLogin(credentialValidationResult);
			}
			else {
				return httpMessageContext.responseUnauthorized();
			}
		}
//		if(username!=null && password != null) {
//			if(users.containsKey(username)){
//				if(users.get(username).equals(password)) {
//					return httpMessageContext.notifyContainerAboutLogin(username,roles.get(username));
//				}
//			}
//			return httpMessageContext.responseUnauthorized();
//			
//					
//		}
		return httpMessageContext.responseUnauthorized();
	}
	
}