package com.javahonk.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class NotificationInterceptor {
	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		String methodInfo = context.getClass().getName() + "." + context.getMethod().getName();
		System.out.println("-- NotificationInterceptor Start: " + methodInfo);
		Object result = context.proceed();
		System.out.println("-- NotificationInterceptor End: " + methodInfo);
		return result;
	}

}
