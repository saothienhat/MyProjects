package com.javahonk.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.javahonk.model.DBConstants;
import com.javahonk.model.StudentTest;
import com.javahonk.springmapper.StudentTestMapper;

public class StudentTestDaoImpl extends AbstractDaoImpl{
	
	private JdbcTemplate jdbcTemplate;  
	  
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.jdbcTemplate = jdbcTemplate;  
	}  
	
	
	/**
	 * @return
	 */
	public List<StudentTest> getAllStudentTest(){
		String sqlQuery = getAllRowsFromTableQuery(DBConstants.STUDENTTEST_TABLE);
		List<StudentTest> list = jdbcTemplate.query(sqlQuery, new StudentTestMapper());
		if (list == null) {
			list = new ArrayList<StudentTest>();
		}
	
		return list;
	}
	
	
	/**
	 * @return
	 */
	public int getStudentTestCount() {
		String sqlQuery = getRowCountFromTableQuery(DBConstants.STUDENTTEST_TABLE);
		int rowCount = jdbcTemplate.queryForInt(sqlQuery);
		return rowCount;
	}
	
	
	
	/**
	 * @param id
	 * @return
	 */
	public String getEmailById(int id){
		String sqlQuery = "select email from STUDENTTEST where id = ?";
		String email = (String) jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, String.class);
		return email;
	}
	
	
	/**
	 * @param id
	 * @return
	 */
	public StudentTest getStudentTestById(int id){
		String sqlQuery = getObjectByIdQuery(DBConstants.STUDENTTEST_TABLE);
		StudentTest student = (StudentTest) jdbcTemplate.queryForObject(sqlQuery, 
		                  new Object[]{id}, new StudentTestMapper());
		return student;
	}
	
	
	
	/**
	 * @param insertedStudent
	 * @return
	 */
	public int insert(StudentTest insertedStudent){
		String sqlQuery = "INSERT INTO "
				+ DBConstants.STUDENTTEST_TABLE
				+ " ("
				+ DBConstants.LASTNAME_COLUMN
				+ ", "
				+ DBConstants.FIRSTNAME_COLUMN
				+ ", "
				+ DBConstants.EMAIL_COLUMN
				+ ", "
				+ DBConstants.ADDRESS_COLUMN
				+ ", "
				+ DBConstants.AGE_COLUMN
				+ ") VALUES (?, ?, ?, ?, ?)";
		int result = jdbcTemplate.update(sqlQuery,
				new Object[] { insertedStudent.getLastName(), insertedStudent.getFirstName(),
						insertedStudent.getEmail(), insertedStudent.getAddress(), insertedStudent.getAge() });
		return result;
	}
	
//	public int saveEmployee(Employee e){  
//	    String query="insert into employee values(  
//	    '"+e.getId()+"','"+e.getName()+"','"+e.getSalary()+"')";  
//	    return jdbcTemplate.update(query);  
//	}  
//	public int updateEmployee(Employee e){  
//	    String query="update employee set   
//	    name='"+e.getName()+"',salary='"+e.getSalary()+"' where id='"+e.getId()+"' ";  
//	    return jdbcTemplate.update(query);  
//	}  
//	public int deleteEmployee(Employee e){  
//	    String query="delete from employee where id='"+e.getId()+"' ";  
//	    return jdbcTemplate.update(query);  
//	}  

}
