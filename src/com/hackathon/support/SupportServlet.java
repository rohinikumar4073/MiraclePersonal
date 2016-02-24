package com.hackathon.support;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miraclepersona.User;

public class SupportServlet  extends HttpServlet{

	User userDetails;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in get method");
		String file = "C:\\Users\\Administrator\\workspace\\MiraclePersonal\\src\\CustomerProducts.txt";

		try {

		    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		    String line;
		    //List<User> userList = new ArrayList<User>();
		    List userList = new ArrayList();
		   
		    while ((line = br.readLine()) != null) {
		    		String[] cline = line.split(",");
		    	    User currentUser = new User();
		    	    currentUser.setEmailId(cline[0]);
		    	    currentUser.setUserName(cline[1]);
		    	    userList.add(currentUser);
		    }
		    br.close();
		    request.setAttribute("data", userList);

		    RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerSupport.jsp");  
		    if (dispatcher != null){  
		           dispatcher.forward(request, response);  
		    }

		} catch (IOException e) {
		    System.out.println("ERROR: unable to read file " + file);
		    e.printStackTrace();   
		}
		
		/*request.setAttribute("uname", userDetails.getUserName());
		request.setAttribute("email", userDetails.getEmailId());
		request.getRequestDispatcher("CustomerSupport.jsp").forward(request, response);;*/
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
