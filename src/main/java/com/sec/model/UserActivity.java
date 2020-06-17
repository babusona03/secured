package com.sec.model;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;

import com.sec.util.Roles;
@Stateless
//here in lies a thing
//if this is turned into an applicationscoped CDI bean, all the roles were getting allowed,that is, the cdi bean gets access to all 5 of these methods, role123allowed, anonymous 
//even the no one allowed also
//RoleBased access kicks in , once UserActivity is turned into an EJB.
//guessed right, the following excerpt is from stackoverflow
/*
 * @DenyAll and @DeclareRoles (as well as @RolesAllowed) only work with EJB (session) beans, not with beans that are just named.
Try adding @Stateless to make your bean a session bean, or @Stateful in combination with a scope (eg @RequestScoped).*/


//this class contains some action methods that a user can perform
//the methods are to be secured according to the roles of a logged in user
// like, the methods can represent the crud operations , allowed according to the roles
public class UserActivity {
	
	@RolesAllowed({Roles.ROLE1})
	public void role1Allowed() {
		System.out.println("role1Allowed()");
	}
	
	@RolesAllowed({Roles.ROLE2})
	public void role2Allowed() {
		System.out.println("role2Allowed()");		
	}
	
	@RolesAllowed({Roles.ROLE3})
	public void role3Allowed() {
		System.out.println("role3Allowed()");
	}
	
	@PermitAll
	public void anonymousAllowed() {
		System.out.println("anonymousAllowed()");
	}
	
	@DenyAll
	public void noOneAllowed() {
		System.out.println("noOneAllowed()");
	}

}
