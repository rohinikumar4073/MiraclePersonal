package com.miraclepersona;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadData extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException{		
		request.getRequestDispatcher("/simulator.html").forward(request, response);
}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name=(String) req.getParameter("name");
		String orgName=(String) req.getParameter("orgName");
		String orgType=(String) req.getParameter("orgType");
		String orgStrength=(String) req.getParameter("orgStrength");
		String region=(String) req.getParameter("region");
		String companyAge=(String) req.getParameter("companyAge");
		System.out.println("name is "+name);
		System.out.println("orgName is "+orgName);
		System.out.println("orgType is "+orgType);
		System.out.println("orgStrength is "+orgStrength);
		System.out.println("region is "+region);
		System.out.println("println is "+companyAge);
		StringBuilder inputString= new StringBuilder();
		inputString.append(name).append(",").append(orgName).append(",").append(orgType).append(",")
		.append(orgStrength).append(",").append(region).append(",").append(companyAge).append(",");
		req.getSession().setAttribute("name", name);
        String outPut = null;
        String inputData = inputString.toString();
		req.getSession().setAttribute("inputData", inputData);

        String Host = "113.128.165.224";
        //String Host = "localhost";
        // String Host = "scsblnx-982422.ebiz.verizon.com";
        PATClient obj = new PATClient();
        try
        {
                String outputData = obj.communicatePAT(outPut, inputData, Host, 5001);
            	req.getSession().setAttribute("outputData", outputData);
        }
        catch(IOException ie)
        {

        }
        catch(Exception e)
        {
        }
      
		req.getRequestDispatcher("/products.jsp").forward(req, resp);
	}
}
