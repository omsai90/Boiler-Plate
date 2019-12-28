package com.web.flipzon.common.dbutils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DataBaseManager {
	private static DataBaseManager instance = null;

	private DataBaseManager() {
	}

	public synchronized Connection getConnection(String dsName) throws SQLException {
		Connection conn = null;
//		try {
//			Context initContext = new InitialContext();
//			Context envContext = (Context) initContext.lookup("java:jboss/");
//			DataSource ds = (DataSource) envContext.lookup("datasources/RIVERBANC_DS");
//			conn = ds.getConnection();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return conn;

	}

	/**
	 * method returns the instance of the DataBase manager if already created.
	 * If not, creates a new instance and returns that,
	 * 
	 * @return NBDataBaseManager instance of the class
	 */
	public synchronized static DataBaseManager getInstance() {

		if (DataBaseManager.instance == null) {
			DataBaseManager.instance = new DataBaseManager();
		}
		return DataBaseManager.instance;
	}
}
