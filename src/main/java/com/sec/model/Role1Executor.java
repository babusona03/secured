package com.sec.model;

import javax.annotation.security.RunAs;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.sec.util.Roles;

@Named
@RequestScoped
@RunAs(Roles.ROLE1)
public class Role1Executor implements RoleExecutable{
	public void run(Executable executable) throws Exception{
		executable.execute();
	}
}
