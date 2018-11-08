package com.agilecrm.dao;
import java.sql.SQLException;

import com.agilecrm.contacts.*;

public interface ContactsDao 
{
	public boolean addContact(Contact contact) throws SQLException; 
	public void updateContact(Contact contact) throws SQLException;
	public void delCon(Contact contact) throws SQLException;
	public Contact getContact(int id) throws SQLException;

}
