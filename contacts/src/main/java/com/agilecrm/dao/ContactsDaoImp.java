package com.agilecrm.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.agilecrm.contacts.Contact;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ContactsDaoImp implements ContactsDao
{
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CCONNECTION = "jdbc:mysql://localhost:3306/agile_crm";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root123";

	@Override
	public boolean addContact(Contact contact) throws SQLException
	{
		String sql = "INSERT INTO contacts (firstName, lastName, email, createdBy, createdDate, address, dob, isActive, updatedBy, updatedDate) VALUES(?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try  
		{
			conn = getDBConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			
		    pstmt.setString(1,contact.getFirstName());
		    pstmt.setString(2,contact.getLastName());
		    pstmt.setString(3,contact.getEmail());
		    pstmt.setString(4,contact.getCreatedBy());
		    pstmt.setString(5,contact.getCreatedDate());
		    pstmt.setString(6,contact.getAddress());
		    pstmt.setString(7,contact.getDob());
		    pstmt.setString(8,contact.getIsActive());
		    pstmt.setString(9,contact.getUpdatedBy());
		    pstmt.setString(10,contact.getUpdatedDate());
		 		
			pstmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		finally 
		{

			if (pstmt != null) 
				pstmt.close();
			

			if (conn != null) 
			{
				conn.close();
			}
		}
		
		return true;
	}

	@Override
	public void updateContact(Contact contact) throws SQLException 
	{
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;
		String updateTableSQL = "UPDATE contacts SET firstName = ? " + " WHERE iD = ?";
		
		try 
		{
			dbConnection = getDBConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(updateTableSQL);

			preparedStatement.setString(1, contact.getFirstName());
			preparedStatement.setInt(2, contact.getId());
			preparedStatement.executeUpdate();
		} 
  
		catch (SQLException e) 
		{
				System.out.println(e.getMessage());
		}
		
		finally
		{

			if (preparedStatement != null) 
			{
				preparedStatement.close();
			}

			if (dbConnection != null) 
			{
				dbConnection.close();

			}

		}

	}
	
	@Override
	public Contact getContact(int id) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM contacts WHERE id = ?";
		Contact contact = new Contact();
		
		try 
		{
			dbConnection = getDBConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();
			
			System.out.println(rs);
			
			while (rs.next()) 
			{	
				contact.setFirstName(rs.getString(1));
				contact.setLastName(rs.getString(2));
				contact.setEmail(rs.getString(3));
				contact.setCreatedBy(rs.getString(4));
				contact.setCreatedDate(rs.getString(5)); 
				contact.setAddress(rs.getString(6));
				contact.setDob(rs.getString(7));
				contact.setIsActive(rs.getString(8));
				contact.setUpdatedBy(rs.getString(9));
				contact.setUpdatedDate(rs.getString(10));
			}

		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());

		}
		finally 
		{
			if (preparedStatement != null) 
			{
				preparedStatement.close();
			}

			if (dbConnection != null) 
			{
				dbConnection.close();
			}
		}
		return contact;
	}
	

	@Override
	public void delCon(Contact contact) throws SQLException 
	{
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE contact WHERE id = ?";

		try 
		{
			dbConnection = getDBConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1,contact.getId());
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());

		}
		finally 
		{

			if (preparedStatement != null) 
			{
				preparedStatement.close();
			}
			
			if (dbConnection != null) 
			{
				dbConnection.close();
			}
		}	
			
	}
	
	private static Connection getDBConnection() 
	{
		Connection dbConnection = null;
		try 
		{
			Class.forName(DB_DRIVER);
			dbConnection = (Connection) DriverManager.getConnection(DB_CCONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		}
		
		catch (ClassNotFoundException e) 
		{	
			System.out.println(e.getMessage());
		}

		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}

		return dbConnection;

	}

}
