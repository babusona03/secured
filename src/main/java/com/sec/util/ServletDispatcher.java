package com.sec.util;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Named
@ApplicationScoped
public class ServletDispatcher {
	public void callReader() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/initread.do");
		try {
			dispatcher.forward(request, response);
		} 
		catch (ServletException | IOException e) {			
			e.printStackTrace();
		}
		finally {
			facesContext.responseComplete();
		}
	}
}
