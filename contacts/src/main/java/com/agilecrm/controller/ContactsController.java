package com.agilecrm.controller;

import java.io.IOException;


import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.agilecrm.contacts.Contact;
import com.agilecrm.dao.ContactsDaoImp;
import com.agilecrm.service.ContactsServiceImp;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/test")
public class ContactsController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	 protected void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	 {
			System.out.println("I am in DoPost method");
			
			Contact contact = new Contact();
			
		    contact.setFirstName(request.getParameter("firstName"));
		    contact.setLastName(request.getParameter("lastName"));
		    contact.setEmail(request.getParameter("email"));
		    contact.setCreatedBy(request.getParameter("createdBy"));
		    contact.setCreatedDate(request.getParameter("createdDate"));
		    contact.setAddress(request.getParameter("address"));
		    contact.setDob(request.getParameter("dob"));
		    contact.setIsActive(request.getParameter("isActive"));
		    contact.setUpdatedBy(request.getParameter("updatedBy"));
		    contact.setUpdatedDate(request.getParameter("updatedDate"));
		    
		   
		    ContactsServiceImp service = new ContactsServiceImp();
		    service.addContact(contact);

		   Contact contact2 = new Contact();
		    try 
		    {
				contact2 = service.getContact(contact.getId());
			}
		    catch (SQLException e) 
		    {
				e.printStackTrace();
			}
	

			System.out.println(contact2.getFirstName());
			System.ou			// Convert object to JSON string and save into a file directlyt.println(contact2.getLastName());
			System.out.println(contact2.getEmail());
			System.out.println(contact2.getCreatedBy());
			System.out.println(contact2.getCreatedDate());;
			System.out.println(contact2.getAddress());
			System.out.println(contact2.getDob());
			System.out.println(contact2.getIsActive());
			System.out.println(contact2.getUpdatedBy());
			System.out.println(contact2.getUpdatedDate());
		
			ObjectMapper mapper = new ObjectMapper();
		
			try
			{
				// Convert object to JSON string and save into a file directly
				mapper.writeValueAsString(contact);

				// Convert object to JSON string
				String jsonInString = mapper.writeValueAsString(contact);
				System.out.println(jsonInString);
	
				// Convert object to JSON string and pretty print
				jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(contact);
				System.out.println(jsonInString);
			} 
			
			catch (JsonGenerationException e) 
			{
				e.printStackTrace();
			}
			catch (JsonMappingException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
	@Override
	 protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	 {
		System.out.println("I am in DoGet method");

	 }
	 
	 

}

