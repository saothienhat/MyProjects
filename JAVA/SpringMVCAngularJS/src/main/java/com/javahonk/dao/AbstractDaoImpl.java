package com.javahonk.dao;

import com.javahonk.model.DBConstants;

public class AbstractDaoImpl {
	
	/**
	 * @param tableName
	 * @return
	 */
	protected String getRowCountFromTableQuery(String tableName){
		return DBConstants.QUERY_GET_COUNT + tableName;
	}
	
	/**
	 * @param tableName
	 * @return
	 */
	protected String getAllRowsFromTableQuery(String tableName){
		return DBConstants.QUERY_GET_ALL + tableName;
	}

	
	
	/**
	 * @param tableName
	 * @return
	 */
	protected String getObjectByIdQuery(String tableName){
		return "SELECT * FROM " + tableName + " WHERE ID = ?";
	}
	
}
