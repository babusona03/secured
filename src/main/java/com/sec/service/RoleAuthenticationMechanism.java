package com.sec.service;

import java.util.Arrays;
import java.util.HashSet;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.CallerOnlyCredential;
import javax.security.enterprise.credential.Credential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sec.util.Roles;
@ApplicationScoped
public class RoleAuthenticationMechanism implements HttpAuthenticationMechanism{
	@Override
	public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
		if(httpMessageContext.isAuthenticationRequest()) {
			Credential credential = httpMessageContext.getAuthParameters().getCredential();
			if(!(credential instanceof CallerOnlyCredential)) {
				throw new IllegalStateException("Invalid mechanism.");
			}
		CallerOnlyCredential callerOnlyCredential = (CallerOnlyCredential) credential;
		if(callerOnlyCredential.getCaller() == null) {
			throw new AuthenticationException();
		}
		else {
			switch(callerOnlyCredential.getCaller()) {
			case "user1": return httpMessageContext.notifyContainerAboutLogin(callerOnlyCredential.getCaller(), new HashSet<>(Arrays.asList(Roles.ROLE1)));
			case "user2": return httpMessageContext.notifyContainerAboutLogin(callerOnlyCredential.getCaller(), new HashSet<>(Arrays.asList(Roles.ROLE2)));
			case "user3": return httpMessageContext.notifyContainerAboutLogin(callerOnlyCredential.getCaller(), new HashSet<>(Arrays.asList(Roles.ROLE3)));
			default: 	  throw new AuthenticationException();
			}
		}
		}
		
		return httpMessageContext.doNothing();
	}

}
