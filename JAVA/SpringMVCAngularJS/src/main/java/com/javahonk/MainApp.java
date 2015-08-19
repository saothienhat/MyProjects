package com.javahonk;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javahonk.beans.HelloWorldConfig;
import com.javahonk.beans.HelloWorldIndiaXMLBean;
import com.javahonk.beans.HelloWorldXMLBean;
import com.javahonk.beans.PrototypeBean;
import com.javahonk.beans.SingletonBean;
import com.javahonk.beans.StudentAnnotation;
import com.javahonk.dao.StudentTestDaoImpl;
import com.javahonk.emails.SimpleOrderEmailSender;
import com.javahonk.emails.SimpleRegistrationEmailSender;
import com.javahonk.model.StudentTest;

public class MainApp {
	/*
	 * XML files
	 */
	private static final String BEAN_XML_CONFIGURATION_FILE = "XMLBeans.xml";
	private static final String BEAN_ANNOTATION_CONFIGURATION_FILE = "AnnotationBeans.xml";
	/*
	 * Beans 
	 */
	private static final String SINGLETON_BEAN_ID = "singletonBean";
	private static final String HELLOWORLDXMLBEAN_BEAN_ID = "helloWorldXMLBean";
	private static final String PROTOTYPEBEAN_BEAN_ID = "prototypeBean";
	private static final String HELLOWORLDINDIAXMLBEAN_BEAN_ID = "helloWorldIndiaXMLBean";
	private static final String STUDENTANNOTATION_BEAN_ID = "studentAnnotation";
	private static final String SIMPLEORDEREMAILSENDER_ID = "simpleOrderEmailSender";
	private static final String SIMPLEREGISTRATIONEMAILSENDER_ID = "simpleRegistrationEmailSender";
	/*
	 * DAO classes
	 */
	private static final String STUDENTTESTDAOIMPL_DAO_ID = "studentTestDaoImpl";
	
	
	
	public static void main(String[] args) {
//		testXMLBeans();
		
//		testSingletonBean();
		
//		testPrototypeBean();
		
//		testBeanInheritance();
		
//		testSudentAnnotationBean();
		
//		testConfigurationAndBeanAnnotation();
		
//		testContextEventHandler();
		
		testDatabaseAccessUsingJdbcTemplate();
		
		/* Email Sender feature*/
		// ** SimpleOrderEmailSender
//		testSendEmailUsingSimpleOrderEmailSender();
//		testSendEmailUsingSimpleOrderEmailSenderToManyEmail();
		// ** SimpleRegistrationEmailSender
//		testSimpleRegistrationEmailSender();
//		testSimpleRegistrationEmailToManyRecipient();
	}
	
	
	
	
	/**
	 * Test send Simple email to multi address
	 */
	private static void testSendEmailUsingSimpleOrderEmailSenderToManyEmail() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(BEAN_XML_CONFIGURATION_FILE);  
		SimpleOrderEmailSender simpleOrderEmailSender = (SimpleOrderEmailSender)ctx.getBean(SIMPLEORDEREMAILSENDER_ID); 
		
		int studentID_1 = 5; 
		int studentID_2 = 6; 
		StudentTestDaoImpl studentTestDaoImpl = (StudentTestDaoImpl)ctx.getBean(STUDENTTESTDAOIMPL_DAO_ID);  
		StudentTest student_1 = studentTestDaoImpl.getStudentTestById(studentID_1);
		System.out.println("Register Student_1 email: " + (student_1 != null ? student_1.getEmail() : ""));
		StudentTest student_2 = studentTestDaoImpl.getStudentTestById(studentID_2);
		System.out.println("Register Student_2 email: " + (student_2 != null ? student_2.getEmail() : ""));
		List<StudentTest> list = new ArrayList<StudentTest>();
		list.add(student_1);
		list.add(student_2);
		simpleOrderEmailSender.sendSimpleEmailToRecipients(list);
	}


	/**
	 * This method test Registration email sending feature.
	 * Refer: http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mail.html
	 */
	private static void testSimpleRegistrationEmailSender() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(BEAN_XML_CONFIGURATION_FILE);  
		SimpleRegistrationEmailSender simpleRegistrationEmailSender = (SimpleRegistrationEmailSender)ctx.getBean(SIMPLEREGISTRATIONEMAILSENDER_ID); 
		
	      
		int studentID = 5; 
		StudentTestDaoImpl studentTestDaoImpl = (StudentTestDaoImpl)ctx.getBean(STUDENTTESTDAOIMPL_DAO_ID);  
		StudentTest student = studentTestDaoImpl.getStudentTestById(studentID);
		System.out.println("Register Student email: " + (student != null ? student.getEmail() : ""));
		simpleRegistrationEmailSender.register(student); 
	}
	
	/**
	 * This method test Registration email sending to multi-USer feature.
	 * Refer: http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mail.html
	 */
	private static void testSimpleRegistrationEmailToManyRecipient() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(BEAN_XML_CONFIGURATION_FILE);  
		SimpleRegistrationEmailSender simpleRegistrationEmailSender = (SimpleRegistrationEmailSender)ctx.getBean(SIMPLEREGISTRATIONEMAILSENDER_ID); 
		
	      
		int studentID_1 = 5; 
		int studentID_2 = 6; 
		StudentTestDaoImpl studentTestDaoImpl = (StudentTestDaoImpl)ctx.getBean(STUDENTTESTDAOIMPL_DAO_ID);  
		StudentTest student_1 = studentTestDaoImpl.getStudentTestById(studentID_1);
		System.out.println("Register Student_1 email: " + (student_1 != null ? student_1.getEmail() : ""));
		StudentTest student_2 = studentTestDaoImpl.getStudentTestById(studentID_2);
		System.out.println("Register Student_2 email: " + (student_2 != null ? student_2.getEmail() : ""));
		List<StudentTest> list = new ArrayList<StudentTest>();
		list.add(student_1);
		list.add(student_2);
		
		simpleRegistrationEmailSender.register(list); 
	}



	/**
	 * This method test Email sending feature.
	 * Refer: http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mail.html
	 */
	private static void testSendEmailUsingSimpleOrderEmailSender() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(BEAN_XML_CONFIGURATION_FILE);  
		SimpleOrderEmailSender simpleOrderEmailSender = (SimpleOrderEmailSender)ctx.getBean(SIMPLEORDEREMAILSENDER_ID); 
		
		int studentID = 5; 
		StudentTestDaoImpl studentTestDaoImpl = (StudentTestDaoImpl)ctx.getBean(STUDENTTESTDAOIMPL_DAO_ID);  
		StudentTest student = studentTestDaoImpl.getStudentTestById(studentID);
		System.out.println("Register Student email: " + (student != null ? student.getEmail() : ""));
		simpleOrderEmailSender.sendSimpleEmail(student);
	}


	/**
	 * This method to test DB access using JdbcTemplate object
	 */
	private static void testDatabaseAccessUsingJdbcTemplate() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(BEAN_XML_CONFIGURATION_FILE);  
	      
		StudentTestDaoImpl studentTestDaoImpl = (StudentTestDaoImpl)ctx.getBean(STUDENTTESTDAOIMPL_DAO_ID);  
		
		String email = studentTestDaoImpl.getEmailById(1);
		System.out.println("Email: " + email); 
		
		int studentID = 2;
		StudentTest studentTestById = studentTestDaoImpl.getStudentTestById(studentID);
		System.out.println("\nInfo of Sturent with ID = " + studentID);
		System.out.println("	Firt Name: " + studentTestById.getFirstName());
		System.out.println("	Last Name: " + studentTestById.getLastName());
		System.out.println("	Email: " + studentTestById.getEmail());
		System.out.println("	Address: " + studentTestById.getAddress());
		
//		StudentTest insertedStudent = new StudentTest();
//		insertedStudent.setAddress("28 Le Loi");
//		insertedStudent.setAge(12);
//		insertedStudent.setEmail("Teery@yahoo.com");
//		insertedStudent.setFirstName("Jenifer");
//		insertedStudent.setLastName("Cue");
//		int insertResult = studentTestDaoImpl.insert(insertedStudent);
//		System.out.println("Insert result: " + insertResult);
		
		List<StudentTest> allStudentTest = studentTestDaoImpl.getAllStudentTest();
		for (StudentTest studentTestItem : allStudentTest) {
			System.out.println("\nInfo of Sturent with ID = " + studentTestItem.getId());
			System.out.println("	Firt Name: " + studentTestItem.getFirstName());
			System.out.println("	Last Name: " + studentTestItem.getLastName());
			System.out.println("	Email: " + studentTestItem.getEmail());
			System.out.println("	Address: " + studentTestItem.getAddress());
		}
		
		
		System.out.println("Count of StudentTest in DB: " + studentTestDaoImpl.getStudentTestCount()); 
		
	}


	private static void testContextEventHandler() {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		printCurrentTestMethodBegin(methodName);
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(BEAN_XML_CONFIGURATION_FILE);

		// Let us raise a start event.
		context.start();

		HelloWorldXMLBean obj = (HelloWorldXMLBean) context.getBean(HELLOWORLDXMLBEAN_BEAN_ID);

		obj.getMessage();

		// Let us raise a stop event.
		context.stop();
		
		printCurrentTestMethodEnd(methodName);
	}


	private static void testConfigurationAndBeanAnnotation() {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		printCurrentTestMethodBegin(methodName);
		ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfig.class);

		HelloWorldXMLBean helloWorld = ctx.getBean(HelloWorldXMLBean.class);

		helloWorld.setMessage("Hello World !!!");
		helloWorld.getMessage();
		

		printCurrentTestMethodEnd(methodName);
	}


	private static void testSudentAnnotationBean() {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		printCurrentTestMethodBegin(methodName);
		ApplicationContext context = new ClassPathXmlApplicationContext(BEAN_ANNOTATION_CONFIGURATION_FILE);

		StudentAnnotation student = (StudentAnnotation) context.getBean(STUDENTANNOTATION_BEAN_ID);

		System.out.println("Name : " + student.getName());
		System.out.println("Age : " + student.getAge());
		printCurrentTestMethodEnd(methodName);
	}


	private static void testBeanInheritance() {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		printCurrentTestMethodBegin(methodName);
		
		ApplicationContext context = new ClassPathXmlApplicationContext(BEAN_XML_CONFIGURATION_FILE);
	      HelloWorldXMLBean objA = (HelloWorldXMLBean) context.getBean(HELLOWORLDXMLBEAN_BEAN_ID);

	      objA.getMessage();
	      objA.getMessage2();

	      HelloWorldIndiaXMLBean objB = (HelloWorldIndiaXMLBean) context.getBean(HELLOWORLDINDIAXMLBEAN_BEAN_ID);
	      objB.getMessage();
	      objB.getMessage2();
	      objB.getMessage3();
		
		printCurrentTestMethodEnd(methodName); 
	}


	/**
	 */
	private static void testPrototypeBean() {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		printCurrentTestMethodBegin(methodName);

		ApplicationContext context = new ClassPathXmlApplicationContext(BEAN_XML_CONFIGURATION_FILE);

		PrototypeBean objA = (PrototypeBean) context.getBean(PROTOTYPEBEAN_BEAN_ID);
		objA.setMessage("I'm object PrototypeBean A ");
		objA.getMessage();

		PrototypeBean objB = (PrototypeBean) context.getBean(PROTOTYPEBEAN_BEAN_ID);
		objB.getMessage();

		
		printCurrentTestMethodEnd(methodName);
	}


	/**
	 */
	private static void testSingletonBean() {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		printCurrentTestMethodBegin(methodName);
		
		ApplicationContext context = new ClassPathXmlApplicationContext(BEAN_XML_CONFIGURATION_FILE);

		SingletonBean singletonBean_A = (SingletonBean) context.getBean(SINGLETON_BEAN_ID);
		singletonBean_A.setMessage("I'm object A");
		singletonBean_A.getMessage();

		SingletonBean singletonBean_B = (SingletonBean) context.getBean(SINGLETON_BEAN_ID);
		singletonBean_B.getMessage();
		
		HelloWorldXMLBean helloWorldXMLBean = (HelloWorldXMLBean) context.getBean(HELLOWORLDXMLBEAN_BEAN_ID);
		helloWorldXMLBean.getMessage();
		
		printCurrentTestMethodEnd(methodName); 
	}


	/**
	 */
	private static void testXMLBeans() {
		ApplicationContext context = new ClassPathXmlApplicationContext(BEAN_XML_CONFIGURATION_FILE);
		HelloWorldXMLBean helloWorldXMLBean = (HelloWorldXMLBean) context.getBean("helloWorldXMLBean");

		helloWorldXMLBean.getMessage();
	}
	
	
	
	/**
	 * @param methodName
	 */
	private static void printCurrentTestMethodBegin(String methodName){
		System.out.println("\n=====================  BEGIN '" + methodName + "' ==========================="); 
	}
	
	
	private static void printCurrentTestMethodEnd(String methodName){
		System.out.println("=====================  END '" + methodName + "' ==========================="); 
	}
	
	
	
	
	/**
	 */
	private void methodTemplate(){
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		printCurrentTestMethodBegin(methodName);
		
		printCurrentTestMethodEnd(methodName); 
	}
}
