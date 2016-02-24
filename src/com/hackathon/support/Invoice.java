package com.hackathon.support;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hackathon.support.NotificationSender;

public class Invoice extends HttpServlet{

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NotificationSender notify = null;
		String subject;
		String header;
		String body;
		String filePath = "C:\\Users\\Administrator\\workspace\\MiraclePersonal\\src\\Invoice.pdf"; // path of invoice.pdf
		System.out.println("in the post");
		if (request.getParameter("inv") != null) {
			System.out.println("in the inv");

			notify = new NotificationSender();
			subject = "Sell T - Your invoice has been generated!";
			header = "<h3> Visit our paper free billing! </h3> <br/> <b> Dear Customer</b>";
			body = "Thank you for using our service! Please find the bill attached for your previous month's usage.<br/> <br/>" + 
					"&nbsp;&nbsp;&nbsp;Use our Automated bill payment option in <a href=\"URL\">our website</a> to pay your bills automatically without any delay.<br/><br/>" +
					"&nbsp;&nbsp;&nbsp;Visit our Website to know more of our products.";
			notify.createMailContent("keerthi.gudur@one.verizon.com", subject, header, body, filePath);
			
		} else if (request.getParameter("alr") != null) {
			System.out.println("in the alr");

			notify = new NotificationSender();
			subject = "Sell T - Know your usage details!";
			header = "<b> Dear Customer</b>";
			body = "Thank you for using our service! You have reached 80% of your internet pack on mobile number 6547768854.<br/> <br/>" + 
					"&nbsp;&nbsp;&nbsp;Please dial *111# to upgrade your usage to enjoy our service without any interruption.<br/><br/>" +
					"&nbsp;&nbsp;&nbsp;Visit <a href=\"URL\">our website</a> to know more of our products.";
			notify.createMailContent("keerthi.gudur@one.verizon.com", subject, header, body, null);
			System.out.println("comlteed");

			
		}
		request.getRequestDispatcher("/dashboard.html").forward(request, response);
	}
}
