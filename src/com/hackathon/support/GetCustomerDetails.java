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

import com.miraclepersona.Product;
import com.miraclepersona.User;

public class GetCustomerDetails extends HttpServlet{
	
	User userDetails;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in get method");	
		userDetails = new User();
		String email = request.getParameter("email");
		String file = "Customerdetails.txt";
		String prodfile = "CustomerProducts.txt";
		
		/*String file = "C:/Workspace/DPC/src/com/hackathon/support/Customerdetails.txt";
		String prodfile = "C:/H_workspace/DPC/src/com/hackathon/support/CustomerProducts.txt";*/

		try {

		    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		    String line;
		    List userList = new ArrayList();
		    List plist = new ArrayList();
		    while ((line = br.readLine()) != null) {
		    		String[] cline = line.split(",");
		    		System.out.println(cline[0]);
	    			System.out.println(email);
		    		if(cline[0].equalsIgnoreCase(email))
		    		{

		    			request.setAttribute("email",cline[0]);
		    			request.setAttribute("cname",cline[1]);
		    			request.setAttribute("oname",cline[2]);
		    			request.setAttribute("otype",cline[3]);
		    			request.setAttribute("ostrength",cline[4]);
		    			request.setAttribute("region",cline[5]);
		    			request.setAttribute("oage",cline[6]);

		    			  
			    	    Product prod;
					    BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(prodfile)));
					    String prodline;
					    while ((prodline = bufr.readLine()) != null) {
				    		String[] pline = prodline.split(",");
					    	if(pline[0].equalsIgnoreCase(email))
				    		{
					    		prod = new Product();
					    		prod.setProductName(pline[2]);
					    		prod.setUsage(pline[3]);
					    		plist.add(prod);
				    		}
			    	    
					    }
					    
					   // request.setAttribute("cdata", cplist);
					    request.setAttribute("pdata", plist);
					    break;
		    		}
		    }
		    br.close();
		   
		    //request.setAttribute("data", userList);

		    RequestDispatcher dispatcher = request.getRequestDispatcher("/DetailsView.jsp");  
		    if (dispatcher != null){  
		           dispatcher.forward(request, response);  
		    }

		} catch (IOException e) {
		    System.out.println("ERROR: unable to read file " + file);
		    e.printStackTrace();   
		}

		//request.getRequestDispatcher("View.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
