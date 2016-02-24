package com.hackathon.support;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class NotificationSender {
	private final String DEFAULT_SMTP_HOST  = "smtp.vzbi.com";
	private final String DEFAULT_SMTP_PORT  = "25";
	private final String DEFAULT_FROM_EMAIL = "noreply-SellT@one.verizon.com";
	private final String REPLY_TO = "SellT@one.verizon.com";
	protected Session session; 					// JavaMail's Session object
	protected MimeMessage message;					// message to be sent
	protected String smtpHost;				// SMTP host to send messages through
	protected String smtpPort;				// SMTP port to send messages through
	private String from;
	private String to;
	private String cc = null;
	private String subject;
	private String messageBody;
	private String filePath = null;
	
	public NotificationSender() {
		smtpHost = DEFAULT_SMTP_HOST;
		smtpPort = DEFAULT_SMTP_PORT;
		from = DEFAULT_FROM_EMAIL;
	}
		
	public void createMailContent(String emailTo, String subject, String msgHeader, String msgBody, String filePath){
		to = emailTo;
		this.subject = subject;
		if(filePath != null)
		{
			this.filePath = filePath;
		}
		String message =  "<html> ";
		message = message + "<head>  "+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">";
		message = message + "<title>Our Products subscription</title> \n";			
		
		message = message + "<style type=\"text/css\"> \n";
		message = message + "body {background:#fff; color:#000000; margin:0; padding:0; font-family:Arial, Helvetica, sans-serif; font-size:12px; text-align:left;} \n ";
		message = message + ".header{color:#000; font-size:16px; font-weight:bold; margin:0px; padding:10px 0px 10px 0px; font-family:Arial, Helvetica, sans-serif;} \n";
		message = message + ".datatable {display:block; font-size:12px;	border-top:1px solid #ccc;	border-right:0px solid #ccc;	border-collapse:collapse; } \n";
		message = message + ".datatable caption {font-size:12px;	margin:0; padding:0 0 5px 0;	_padding:0 0 5px 0; caption-side:top;	text-align:left;} \n";
		message = message + ".datatable tr.odd td{background:#f5f5f5;} \n";
		message = message + ".datatable tr.odd .column1{background:#f5f5f5;} \n";
		message = message + ".datatable .column1{background:#f5f5f5;	font-weight:bold;} \n";
		message = message + ".datatable td{font-size:11px;	border-bottom:1px solid #e2e2e2;	border-left:0px solid #ccc;	padding:3px 3px;	text-align:left;} \n";
		message = message + ".datatable th{font-size:11px;	font-weight:bold;	text-align:left;	border-bottom: 1px solid #ccc; border-left:0px solid #ccc; padding:3px 3px;} \n";
		message = message + ".datatable thead th{background: #eee; font-size:11px; text-align:left; font:bold;} \n";
		message = message + ".datatable thead th .second{background: #fff; font-size:11px; text-align:left; font:bold;} \n";
		message = message + "h2 {font-size:12px;	margin:0}";
		message = message + "p {margin-bottom:20px;}";
		message = message + ".odd{color:#777;} \n";
		message = message + "td{font-size:12px;	padding:1px 1px;	text-align:left;}\n";
		message = message + "</style> \n";
		
		message = message + "</head> \n";
		message = message + "<body width=\"100%\" > \n";
		message = message + "<div style=\"margin-left: 0px\"> \n";
		//message = message + "<h1 class=\"header\">" + subject + "</h1> \n";
		message = message + "<p>" + msgHeader + ", </p>" ;	
		message = message + "<p> &nbsp;&nbsp;&nbsp;" + msgBody + "</p>";
		//message = message + "<p>" + msgBody + "</p>";
				
		message = message + "<div>  ";
		message = message + "<table class=\"odd\"> \n";
		message = message + "<tr><td><b>NOTE:</b></td></tr>  \n";
		message = message + "<tr><td>1. This is an automated e-mail notification from Sell T. Please DO NOT reply to this mail.</td></tr> \n";
		message = message + "<tr><td>2. For any issues, please click <a href=\"mailto:" + REPLY_TO + "\">HERE</a> to contact us.</td></tr>  \n";			
		message = message + "</table>  ";
		message = message + "</div>  </div> </td> </tr> </table>";
		
		message = message + "</body> ";
		message = message + "</html> ";
		System.out.println(message);
		messageBody = message;
		
		try{
			sendEmail();
		}catch(Exception e){
			System.out.println("Error in sending an email : " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	public void sendEmail() throws Exception{
		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", this.smtpHost);

		// Get the default Session object.
		session = Session.getDefaultInstance(properties);

		// Create a default MimeMessage object.
		message = new MimeMessage(session);

		// Set the RFC 822 "From" header field using the
		// value of the InternetAddress.getLocalAddress method.
		message.setFrom(new InternetAddress(from));

		// Add the given addresses to the specified recipient type.
		setRecipients(Message.RecipientType.TO, to);
		if(cc != null)
			setRecipients(Message.RecipientType.CC, cc);

		// Set the "Subject" header field.
		message.setSubject(subject);		

		// Create the message part 
		BodyPart messageBodyPart = new MimeBodyPart();

		// Fill the message
		messageBodyPart.setText(messageBody);
		messageBodyPart.setContent(messageBody, "text/html");
			
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		if(filePath != null)
		{
			BodyPart attachment = null;
			/*for (String filename : attachments) 
			{
				attachment = new MimeBodyPart();
				DataSource source = new FileDataSource(filename);
				attachment.setDataHandler(new DataHandler(source));
				int endIndex = filename.lastIndexOf("/");
				if(endIndex != -1)
				{
					filename = filename.substring(endIndex+1);
				}
				attachment.setFileName(filename);
				multipart.addBodyPart(attachment);
			}*/
			attachment = new MimeBodyPart();
			DataSource source = new FileDataSource(filePath);
			attachment.setDataHandler(new DataHandler(source));
			int endIndex = filePath.lastIndexOf("/");
			if(endIndex != -1)
			{
				filePath = filePath.substring(endIndex+1);
			}
			attachment.setFileName("InvoiceAndFortel");
			multipart.addBodyPart(attachment);
		}
					
		// Put parts in message
		message.setContent(multipart);				
	    
		System.out.println("Calling Transport.send()..");
		// Send message
		Transport.send(message);
	}
	
	private void setRecipients(RecipientType recipientType, String recipients) throws MessagingException, AddressException {
		//if (recipients==null || (recipients!=null&&recipients.trim().length()==0)) return;
		String[] emailAddresses = recipients.split(",");	
		for(int i =0; i <emailAddresses.length; i++){
			message.addRecipient(recipientType, new InternetAddress(emailAddresses[i]));
		}
	}
}
