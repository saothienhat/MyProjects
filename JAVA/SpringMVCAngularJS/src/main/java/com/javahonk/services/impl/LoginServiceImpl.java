package com.javahonk.services.impl;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javahonk.dao.impl.UserDaoImpl;
import com.javahonk.model.Constants;
import com.javahonk.services.LoginService;

public class LoginServiceImpl implements LoginService {
	
	private static final String USERDAOIMPL_DAO_ID = "userDaoImpl";

	@Override
	public boolean isValidUser(String username, String password) throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(Constants.BEAN_XML_CONFIGURATION_FILE);  
		UserDaoImpl userDaoImpl = (UserDaoImpl)ctx.getBean(USERDAOIMPL_DAO_ID);
		return userDaoImpl.isValidUser(username, password); 
	}

}
