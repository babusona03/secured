package com.sec.controller;

import java.io.IOException;

import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.CallerOnlyCredential;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sec.model.Role1Executor;
import com.sec.model.Role2Executor;
import com.sec.model.Role3Executor;
import com.sec.model.RoleExecutable;
import com.sec.model.UserActivity;
import com.sec.util.Roles;

@DeclareRoles({Roles.ROLE1,Roles.ROLE2,Roles.ROLE3})
@WebServlet(name = "/UserAuthorizationServlet", urlPatterns = "/UserAuthorizationServlet")
public class UserAuthorizationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Inject	
	private SecurityContext securityContext;
	
	@Inject
	private Role1Executor role1Executor;
	
	@Inject
	private Role2Executor role2Executor;
	
	@Inject
	private Role3Executor role3Executor;
	
	@Inject
	private UserActivity userActivity;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
		try{
		String name = request.getParameter("name");
		if(name != null || !("".equals(name))) {
			AuthenticationStatus status = securityContext.authenticate(request, response, AuthenticationParameters.withParams().credential(new CallerOnlyCredential(name)));
			response.getWriter().write("Authentication Status:"+status.name()+"\n");
		}
		String principal = null;
		if(request.getUserPrincipal() != null) {
			principal = request.getUserPrincipal().getName();
		}
		response.getWriter().write("User: "+principal+"\n");
		response.getWriter().write("Role \"role1\" access:" +request.isUserInRole(Roles.ROLE1)+"\n");
		response.getWriter().write("Role \"role2\" access:" +request.isUserInRole(Roles.ROLE2)+"\n");	
		response.getWriter().write("Role \"role3\" access:" +request.isUserInRole(Roles.ROLE3)+"\n");
		
		RoleExecutable roleExecutable = null;
		
		if(request.isUserInRole(Roles.ROLE1)) {
			roleExecutable = role1Executor;
		}
		else if(request.isUserInRole(Roles.ROLE2)) {
			roleExecutable = role2Executor;
		}
		else if(request.isUserInRole(Roles.ROLE3)) {
			roleExecutable = role3Executor;
		}
		
		if(roleExecutable != null) {				
					roleExecutable.run(() -> {
						try {
							userActivity.role1Allowed();							
							response.getWriter().write("role1Allowed() executed: true\n");
						}
						catch(Exception e) {
							response.getWriter().write("role1Allowed() executed: false\n");
						}
						try {
							userActivity.role2Allowed();
							response.getWriter().write("role2Allowed() executed: true\n");
						}
						catch(Exception e) {
							response.getWriter().write("role2Allowed() executed: false\n");
						}
						try {
							userActivity.role3Allowed();
							response.getWriter().write("role3Allowed() executed: true\n");
						}
						catch(Exception e) {
							response.getWriter().write("role3Allowed() executed: false\n");
						}
					});
		}
		try {
			userActivity.anonymousAllowed();
			response.getWriter().write("Anonymous allowed:true\n");
		}
		catch(Exception e) {
			response.getWriter().write("Anonymous allowed:false");
		}
		
		try {
			userActivity.noOneAllowed();
			response.getWriter().write("No one allowed: true");
		}
		catch(Exception e) {
			response.getWriter().write("No one allowed: false");
		}
	}
	catch(Exception e) {
		System.err.println(e.getMessage());
	}
	}
}
