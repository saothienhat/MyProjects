/**
 * 
 */
package com.javahonk.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author binhtt
 *
 */
public class PrototypeBean implements InitializingBean, DisposableBean, BeanPostProcessor  {
	private String message;

	/**
	 */
	public PrototypeBean() {
		System.out.println("Call Constructor of PrototypeBean class..........");
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void getMessage() {
		System.out.println("Your Message : " + message);
	}
	
	public void initMethod(){
		System.out.println("Call initMethod() method of PrototypeBean class..........");
	}
	
	public void destroyMethod(){
		System.out.println("Call destroyMethod() method of PrototypeBean class..........");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Call afterPropertiesSet() method of PrototypeBean class..........");
	}

	
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	@Override
	public void destroy() throws Exception {
		System.out.println("Call destroy() method of PrototypeBean class..........");
	}

	
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		System.out.println("AfterInitialization : " + beanName);
	      return bean;
	}

	
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//		System.out.println("BeforeInitialization : " + beanName);
	      return bean;
	}

}
