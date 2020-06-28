package com.sec.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.AutoApplySession;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;


import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ApplicationScoped
@AutoApplySession
public class ContentAuthenticationMechanism implements HttpAuthenticationMechanism{
	private Map<String,String> users;
	private Map<String,Set<String>> roles;
	
	
	
	@PostConstruct
	public void init() {
		users= new HashMap<>();roles= new HashMap<>();
		
		users.put("admin", "admin");users.put("user", "password");users.put("guest", "guest");
		
		roles.put("admin", new HashSet<>(Arrays.asList("admin")));
		roles.put("user", new HashSet<>(Arrays.asList("user")));
		roles.put("guest", new HashSet<>(Arrays.asList("guest")));
		
	}
	@Override
	public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response,
			HttpMessageContext httpMessageContext) throws AuthenticationException {
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		System.out.println("username:"+username+" password:"+password);
		if(username!=null && password != null) {
			if(users.containsKey(username)){
				if(users.get(username).equals(password)) {
					return httpMessageContext.notifyContainerAboutLogin(username,roles.get(username));
				}
			}
			return httpMessageContext.responseUnauthorized();
			
					
		}
		return httpMessageContext.doNothing();
	}
	
}