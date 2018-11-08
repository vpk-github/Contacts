package com.agilecrm.service;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import com.agilecrm.contacts.Contact;
import com.agilecrm.dao.ContactsDaoImp;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.PreparedStatement.*;

public class ContactsServiceImp implements ContactsService 
{
	@Override
	public boolean addContact(Contact contact)
	{			
		try 
		{
			return new ContactsDaoImp().addContact(contact);
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void updateContact(Contact contact) throws SQLException 
	{
		
	}
	
	@Override
	public Contact getContact(int id) throws SQLException 
	{
		return new ContactsDaoImp().getContact(id);
	}
	

	@Override
	public void delCon(Contact contact) throws SQLException 
	{
		
			
	}
	
	

}
