package com.javahonk.beans;

import javax.interceptor.Interceptors;

import com.javahonk.interceptor.NotificationInterceptor;

/**
 * @author binhtt
 *  This Bean is created with XML file configuration
 *
 */
@Interceptors(NotificationInterceptor.class)
public class HelloWorldXMLBean {

	private String message;
	private String message2;
	
	/**
	 */
	public HelloWorldXMLBean() {
		System.out.println("Call Constructor of HelloWorldXMLBean class..............");
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void getMessage() {
		System.out.println("Your Message : " + message);
	}

	
	/**
	 * @return the message2
	 */
	public String getMessage2() {
		return message2;
	}

	
	/**
	 * @param message2 the message2 to set
	 */
	public void setMessage2(String message2) {
		this.message2 = message2;
	}


}
