/**
 * 
 */
package com.javahonk.springmapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.javahonk.model.DBConstants;
import com.javahonk.model.JinzaResultSet;
import com.javahonk.model.StudentTest;

/**
 * @author binhtt
 *
 */
public class StudentTestMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		StudentTest student = new StudentTest();
		JinzaResultSet jinzaResultSet = new JinzaResultSet(resultSet);
		try{
			student.setId(jinzaResultSet.getInt(DBConstants.ID_COLUMN));
			student.setFirstName(jinzaResultSet.getString(DBConstants.FIRSTNAME_COLUMN)); 
			student.setLastName(jinzaResultSet.getString(DBConstants.LASTNAME_COLUMN)); 
			student.setAge(jinzaResultSet.getInt(DBConstants.AGE_COLUMN)); 
			student.setEmail(jinzaResultSet.getString(DBConstants.EMAIL_COLUMN));
			student.setAddress(jinzaResultSet.getString(DBConstants.ADDRESS_COLUMN)); 			
		}catch(SQLException e){
			System.out.println("A SQLException happened when call StudentTestMapper.mapRow() method.");  
		}finally{
			if (jinzaResultSet != null) {
				jinzaResultSet.close();
			}
		}
		
		return student;
	}

//	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//	      Student student = new Student();
//	      student.setID(rs.getInt("id"));
//	      student.setName(rs.getString("name"));
//	      student.setAge(rs.getInt("age"));
//	      return student;
//	   }
	
}
