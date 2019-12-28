package com.web.flipzon.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import org.springframework.security.BadCredentialsException;

import com.web.flipzon.auth.UserDO;
import com.web.flipzon.common.dbutils.DataBaseManager;
import com.web.flipzon.jsf.ContextHelper;


public class UserDAO {
	private static UserDAO userDao = new UserDAO();
	
	private UserDAO(){
	}
	
	public synchronized static UserDAO getInstance(){
		if(userDao == null){
			userDao = new UserDAO();
		}
		return userDao;
	}

	public UserDO getUser(String username) throws BadCredentialsException{
		return getUser(username, null);
	}

	public UserDO getUser(String username, String password) throws BadCredentialsException{

		String query = "select * from RIVERBANC_USERS where USERNAME='" + username + "'";
		if(password != null && password.trim().length() > 0){
			query = query +"  PASSWORD='"+password+"'";
		}
		
		UserDO user = null;
		Connection dbConnection = null;
		Statement dbStatement = null;
		ResultSet resultSet = null;		
		// execute query
		try {
			dbConnection = DataBaseManager.getInstance().getConnection("RIVERBANC");
			dbStatement = dbConnection.createStatement();
			resultSet = dbStatement.executeQuery(query);
			while (resultSet.next()) {
				user = new UserDO();
				user.setId(resultSet.getInt("ID"));
				user.setUsername(resultSet.getString("USERNAME"));
				user.setPassword(resultSet.getString("PASSWORD"));
				user.setFirstName(resultSet.getString("FIRSTNAME"));
				user.setLastName(resultSet.getString("LASTNAME"));
				user.setLastLogin(resultSet.getTimestamp("LAST_LOGIN"));

				user.setSessionStatus(resultSet.getInt("SESSION_STATUS"));
				user.setEmailId(resultSet.getString("EMAIL_ID"));
				user.setEmailCookie(resultSet.getString("EMAIL_COOKIE"));

				user.setRoles(resultSet.getString("ROLES"));
				user.setActive(resultSet.getBoolean("ACTIVATED"));
				user.setExpired(resultSet.getBoolean("EXPIRED"));
				user.setLocked(resultSet.getBoolean("LOCKED"));

				// store found user to session
				ContextHelper.getSession().setAttribute("user", user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeResources(resultSet, dbStatement, dbConnection);
		}

		if(user == null)
			throw new BadCredentialsException("Invalid Username/password");
		return user;
	}
	
	
	private void closeResources(ResultSet resultSet, Statement stmt, Connection con) {
		try {

			if (resultSet != null) {
				resultSet.close();
			}

			if (stmt != null) {
				stmt.close();
			}
			
			if(con != null){
				con.close();
			}
		} catch (Exception exc) {
		}
	}

}
