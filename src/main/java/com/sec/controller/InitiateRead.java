package com.sec.controller;

import java.io.IOException;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.glassfish.soteria.identitystores.annotation.Credentials;
import org.glassfish.soteria.identitystores.annotation.EmbeddedIdentityStoreDefinition;

@WebServlet(name = "/InitiateRead", urlPatterns = {"/initread.do"})
@DeclareRoles({"user","admin","guest"})
@ServletSecurity(@HttpConstraint(rolesAllowed = {"user","admin"}))
@BasicAuthenticationMechanismDefinition(realmName="security-realm")
@EmbeddedIdentityStoreDefinition({
	@Credentials(callerName = "user",  password = "password123", 	groups = {"user" }),
	@Credentials(callerName = "admin", password = "admin123", 		groups = {"admin"}),
	@Credentials(callerName = "guest", password = "guest123", 		groups = {"guest"})	
	})


/*
 * Notes:
 * only this servlet is required to test run embeddedidentitystoredefinition
 * with JASPIC enabled by placing jboss-web.xml,any HAM implementation causes ambiguous dependency by weld,
 * soteria's implementation collides with that of the custom one.
 * this can be bypassed by implementing IdentityStore and overriding the validate() for credential validation purpose.
 * now, without jboss-web.xml, JASPIC won't kick in.so,jboss-web.xml is a must to implement security with wildfly.
 * in this implementation, the validation mechanism is delegated to soteria's own HAM implementation.
 * works fine. 
 *
 * */
public class InitiateRead extends HttpServlet {

  private static final long serialVersionUID = 1L;
  


  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	  RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/jsf/read.xhtml");
	  System.out.println("inside read initiator servlet.");
//	  view.forward(request, response);


  }
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  doGet(request,response);
  }
  
  /*
   * context: calling a servlet from jsf command button onclick
   What you are trying to attempt will not work. The moment you start using JSF tags / components, any reference to a bean or object,
    JSF tries to look within its context, facesconfig. It does not go outside its perimeter.
If your intention is to all call a servlet by clicking a link within a page rendered by JSF, use regualr HTML tags or call 
a managed bean and then forward to a servlet
*/
}
