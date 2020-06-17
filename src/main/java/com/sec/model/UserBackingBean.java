package com.sec.model;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.sec.entity.UserCredentials;
import com.sec.service.UserCredentialService;



@Named
@RequestScoped
public class UserBackingBean {
	private List<UserCredentials> users;
	
	@EJB
	private UserCredentialService userCredentialService;

	public List<UserCredentials> getUsers() {		
		return users;//test
		
	}

	public void setUsers(List<UserCredentials> users) {
		this.users = users;
	}
	
	@PostConstruct
	public void init() {		
		this.setUsers(userCredentialService.getAllCredentials());
	}
	

}
