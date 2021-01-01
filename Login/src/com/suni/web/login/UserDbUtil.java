package com.suni.web.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.sql.DataSource;

import org.apache.catalina.connector.Response;

public class UserDbUtil {
	private DataSource dataSource;

	public UserDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public void addUser(User theUser) {

		// get db connection
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();

			// prepare sql query
			String sql = "insert into users(name,email,country,password) values(?,?,?,?)";
			myStmt = myConn.prepareStatement(sql);
			// update database
			myStmt.setString(1, theUser.uName);
			myStmt.setString(2, theUser.email);
			myStmt.setString(3, theUser.country);
			myStmt.setString(4, theUser.password);
			
			myStmt.execute();
			// close the connection
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(myConn,myStmt);
		}
	}

	private void close(Connection myConn, PreparedStatement myStmt) {
		try {
			if (myConn != null)
				myConn.close();
			if (myStmt != null)
				myStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public boolean validateUser(String userName, String password) {
		
		//Connect to DB
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs=null;
		
		String sql="select name from users where name=? and password=?";
		try {
			myConn=dataSource.getConnection();
			
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, userName);
			myStmt.setString(2, password);
			myRs=myStmt.executeQuery();
			
			if(myRs.next()) {
				return true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
