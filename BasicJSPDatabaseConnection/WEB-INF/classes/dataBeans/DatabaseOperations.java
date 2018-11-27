/* Author: Dylan MacDermot
 * Version 1.0
 * Purpose: To provide a JSP with the functionality to connect to an Oracle 11g Database
 */
package dataBeans;

import database.*;

import java.io.*;
import java.util.ArrayList;
import java.sql.*;
import oracle.jdbc.OracleResultSetMetaData;

public class DatabaseOperations{

	private String username;
	private String password;
	private String table;
	
	private static final String DB_CONN_URL = "jdbc:oracle:thin:@localhost:1521:XE";

	public DatabaseOperations(){
		/*
		 * Default Constructor for the DatabaseOperations class, intentionally left blank
		 */
	}
	
	public String getTable(){
		return table;
	}
	
	public void setTable(String t){
		this.table = t;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String s){
		this.username = s;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String p){
		this.password = p;
	}
	
	/*
	 * Method to initalize the connection to the database, only for use by other class methods
	 */
	private Connection createConnection()
		throws SQLException{
		
		// Initialize the Connection and Statement variables to allow them to be used in later parts of the connection 
		Connection conn = null;
		
		// Load the jdbc driver 
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		
		// Create connection to the database 
		conn = DriverManager.getConnection(
			DB_CONN_URL, username, password);
			
		return conn;
	}
	
	private void closeConnection(Connection conn, PreparedStatement prepStmt)
		throws SQLException{
			conn.close();
			prepStmt.close();
	}
	
	/*
	 * Method to create the table, if it does not already exist
	 */ 
	public void createTable() 
		throws SQLException{
		
		// Initialize the connection and preparedstatement variables
		Connection dbConnection = null;
		PreparedStatement prepStmt = null;
		
		// Create the String object that will hold the update statement
		String createTable = "CREATE TABLE "+ table + " (firstName     VARCHAR(35) NOT NULL, " + 
																										"lastName      VARCHAR(35) NOT NULL, " +
																										"phoneNumber   VARCHAR(35) NOT NULL, " + 
																									  "emailAddress  VARCHAR(35) NOT NULL)";
		
		// Try to connect to the database & execute the query
		dbConnection = createConnection();
		prepStmt = dbConnection.prepareStatement(createTable);
		prepStmt.executeUpdate();
		
		closeConnection(dbConnection, prepStmt);
	}
	
	/*
	 * Method to delete the table if the user chooses
	 */
	public void dropTable() 
		throws SQLException{
		
		// Initialize the connection and preparedstatement variables
		Connection dbConnection = null;
		PreparedStatement prepStmt = null;
		
		// Create a String object to hold the drop table statement
		String dropTable = "DROP TABLE " + table;
		
		// Try/catch statements to create the DB connection, as well as execute the drop table statement
		dbConnection = createConnection();
		prepStmt = dbConnection.prepareStatement(dropTable);
		
		prepStmt.executeUpdate();
		closeConnection(dbConnection, prepStmt);
	}
	
	/*
	 * Method to update the table, returns true if successful
	 */ 
	public boolean updateTable(String firstName, String lastName, String phoneNumber, String emailAddress) 
		throws SQLException{
		
		// Initialize the connection and preparedstatement variables
		Connection dbConnection = null;
		PreparedStatement prepStmt = null;
		
		// Create the String object to hold the insert statement
		// See following URL for info: https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
		String updateTable = "INSERT INTO " + table + " (firstName, lastName, phoneNumber, emailAddress) " + 
		                     "VALUES (?, ?, ?, ?)";
												 
		// Thank you Alex, idea for server-side validation taken from Alex Mohr
		if(firstName.length()    < 1 ||
		   lastName.length()     < 1 ||
			 phoneNumber.length()  < 1 ||
			 emailAddress.length() < 1) {
		 return false;
		} else {
												 
			// Try/catch to create connection and execute the insert statement
			dbConnection = createConnection();
			prepStmt = dbConnection.prepareStatement(updateTable);
			
			prepStmt.setString(1, firstName);
			prepStmt.setString(2, lastName);
			prepStmt.setString(3, phoneNumber);
			prepStmt.setString(4, emailAddress);

			prepStmt.executeUpdate();
			
			closeConnection(dbConnection, prepStmt);
			return true;
		}
	}
	
	/*
	 * Method to create an arraylist that contains each object currently held in the database supplied by the javabean
	 */
	public ArrayList<DatabaseObject> queryTable()
	  throws SQLException{
		
		// Initialize the connection, statement, and resultset variables
		Connection dbConnection = null;
		PreparedStatement prepStmt = null;
		ResultSet rs;
		
		// Define the arraylist and query objects
		ArrayList<DatabaseObject> data = new ArrayList<DatabaseObject>();
		String query = "SELECT * FROM " + table;
		
		
		dbConnection = createConnection();
		prepStmt = dbConnection.prepareStatement(query);
		
		// Populate the resultset with the String query specified above
		rs = prepStmt.executeQuery();
		
		// Iterate through the resultset returned
		while(rs.next()){
			data.add(new DatabaseObject(rs.getString(1),
																	rs.getString(2),
																	rs.getString(3),
																	rs.getString(4)										
																	)
							);
		}
		
		// return the arraylist to the calling method
		closeConnection(dbConnection, prepStmt);
		return data;

	} 
}













