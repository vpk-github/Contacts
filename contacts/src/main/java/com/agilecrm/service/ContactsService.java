package com.agilecrm.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.agilecrm.contacts.Contact;

public interface ContactsService
{
		public void updateContact(Contact contact) throws SQLException;
		public void delCon(Contact contact) throws SQLException;
		public Contact getContact(int id) throws SQLException;
		boolean addContact(Contact contact);
}
