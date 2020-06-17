package com.sec.util;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;

@RequestScoped//without this bean defining annotation, CDI was not kicking in. CDI would not treat this as a bean unless annotated with BeanDefining Annotation
//this a producer method; it returns an injectable logger
public class HAMProducer {
	@Produces	
	private HttpAuthenticationMechanism httpAuthenticationMechanism;
}
