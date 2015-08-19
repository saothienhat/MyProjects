package com.javahonk.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javahonk.dao.StudentTestDaoImpl;
import com.javahonk.model.Constants;
import com.javahonk.model.StudentTest;

/**
 * @author binhtt
 *
 */
public class StudentServiceImpl implements StudentService{
	
	private static final String STUDENTTESTDAOIMPL_DAO_ID = "studentTestDaoImpl";

	
	
	/* (non-Javadoc)
	 * @see com.javahonk.controller.StudentService#getStudent(int)
	 */
	@Override
	public StudentTest getStudent(int studentID) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(Constants.BEAN_XML_CONFIGURATION_FILE);  
		StudentTestDaoImpl studentTestDaoImpl = (StudentTestDaoImpl)ctx.getBean(STUDENTTESTDAOIMPL_DAO_ID);  
		StudentTest student = studentTestDaoImpl.getStudentTestById(studentID);
		System.out.println("Student email: " + (student != null ? student.getEmail() : ""));		
		return student;
	}

	
	
	/* (non-Javadoc)
	 * @see com.javahonk.controller.StudentService#getAllStudents()
	 */
	@Override
	public List<StudentTest> getAllStudents() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(Constants.BEAN_XML_CONFIGURATION_FILE);  
		StudentTestDaoImpl studentTestDaoImpl = (StudentTestDaoImpl)ctx.getBean(STUDENTTESTDAOIMPL_DAO_ID);  
		List<StudentTest> students = studentTestDaoImpl.getAllStudentTest();
		return students;
	}


}
