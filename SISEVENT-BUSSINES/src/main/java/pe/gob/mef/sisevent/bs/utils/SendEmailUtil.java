package pe.gob.mef.sisevent.bs.utils;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import com.sun.mail.smtp.SMTPTransport;


public class SendEmailUtil implements Serializable,TransportListener, ConnectionListener{

	/**
	 * //MPINARES 08032019 - INICIO
	 */
	private static final long serialVersionUID = 382707667317107783L;

	private static final Logger log = Logger.getLogger(SendEmailUtil.class.getName());
	
    private String serverName = null;
    
    private Integer serverPort = 993;
	
    private boolean useSsl = true;
    private boolean useTls = true;
    
    private String userName = null;
    
    private String password = null;
	
    //props.put("mail.smtp.debug", "true");
    private boolean debug = false;
    
    private String fromEmail = null;
   
    
	public SendEmailUtil(String serverName, Integer serverPort, String fromEmail, String userName, String password) {
		this.serverName = serverName;
		this.serverPort = serverPort;
		this.userName = userName;
		this.password = password;
		this.fromEmail = fromEmail;
	}
	
	public boolean isUseSsl() {
		return useSsl;
	}

	public void setUseSsl(boolean useSsl) {
		this.useSsl = useSsl;
	}

	public boolean isUseTls() {
		return useTls;
	}

	public void setUseTls(boolean useTls) {
		this.useTls = useTls;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public void sendEmail(String toEmail,String coEmail,String ccEmail, String subject, String body, String filepath){
		if(debug)
			log.info("SEND EMAIL A "+toEmail);
		
		Session session = null;
		Properties props = new Properties();
		if(useSsl){
			if(debug)
				log.info("SSLEmail Start");			
			props.put("mail.smtp.host", serverName); // SMTP Host
			props.put("mail.smtp.socketFactory.port", serverPort); //"465" // SSL Port
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
			props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
			props.put("mail.smtp.port", serverPort); //"465" // SMTP Port
			props.put("mail.smtp.debug", Boolean.toString(debug));

			Authenticator auth = new Authenticator() {
				// override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			};

//			session = Session.getDefaultInstance(props, auth);
			//MPINARES 08032019 - INICIO
			session = Session.getInstance(props, auth);
			//MPINARES 08032019 - FIN
			
		}else if(useTls){
			if(debug)
				log.info("TLSEmail Start");
			props.put("mail.smtp.host", serverName); // SMTP Host
			props.put("mail.smtp.port", serverPort); //"587" // TLS Port
			props.put("mail.smtp.auth", "true"); // enable authentication
			
			props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
			props.put("mail.smtp.debug", Boolean.toString(debug));
			// create Authenticator object to pass in Session.getInstance argument
			Authenticator auth = new Authenticator() {
				// override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			};
			session = Session.getInstance(props, auth);
		}else{
			if(debug)
				log.info("Normal Email Start");
			props.put("mail.smtp.host", serverName); 
			props.put("mail.smtp.port", serverPort); 
			props.put("mail.smtp.debug", Boolean.toString(debug));

			session = Session.getInstance(props, null);
		}
		
		if(debug)
			log.info("Fin de optener Session");
		
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			
			msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));
			msg.setReplyTo(InternetAddress.parse(fromEmail, false));

			msg.setSubject(subject, "UTF-8");
			msg.setSentDate(new Date());
			
			if(debug)
				log.info("SET CORREO TO "+toEmail);
			
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, true));
			
			if(coEmail!=null){
				try{
					if(debug)
						log.info("SET CORREO CC "+coEmail);
					msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(coEmail,true));
				}catch(AddressException ae){
					if(debug)
					ae.printStackTrace();
				}
			}
			
			if(ccEmail!=null){
				try{
					if(debug)
						log.info("SET CORREO CC "+ccEmail);
					msg.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(ccEmail,true));
				}catch(AddressException ae){
					if(debug)
					ae.printStackTrace();
				}
			}
			
			if(filepath!=null && filepath.length()>0){
				File f = new File(filepath);
				if(f.exists()){
				// Create the message body part
				BodyPart messageBodyPart = new MimeBodyPart();

				// Fill the message
				messageBodyPart.setContent(body, "text/html");
//				messageBodyPart.setText(body);

				// Create a multipart message for attachment
				Multipart multipart = new MimeMultipart();

				// Set text message part
				multipart.addBodyPart(messageBodyPart);

				// Second part is attachment
				messageBodyPart = new MimeBodyPart();
				
				DataSource source = new FileDataSource(f);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(f.getName());
				multipart.addBodyPart(messageBodyPart);

				// Send the complete message parts
				msg.setContent(multipart);
								
				}else{
					msg.setText(body, "UTF-8", "html");
				}				
			}else{
				msg.setText(body, "UTF-8", "html");
			}
			
			if(debug)
				log.info("Message is ready a "+serverName+" p "+serverPort+" u "+userName+" pss "+password+" ssl "+useSsl+" ttl "+useTls+" file "+filepath==null?"":filepath);
			
			SMTPTransport tsmtp = (SMTPTransport) session.getTransport("smtp");

			if (debug) {
				tsmtp.addConnectionListener(this);
				tsmtp.addTransportListener(this);
			}
			
			SMTPTransport.send(msg);
//			Transport.send(msg);
			
			if(debug)
				log.info("EMail Sent Successfully!!");
		    	
		} catch (MessagingException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
	}


	//------------------------------------------------------EJEMPLOS----------------------------------------------------------------------------------
	@SuppressWarnings("unused")
	private static void _sendEmail(Session session, List<String> listatoEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@mef.gob.pe", "NoReply-JD"));

			msg.setReplyTo(InternetAddress.parse("no_reply@mef.gob.pe", false));

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");
			msg.setSentDate(new Date());
			

			for (String toEmail : listatoEmail) {
				try {				
					msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
				    System.out.println("Message is ready");
				    Transport.send(msg);
				    System.out.println("EMail Sent Successfully!!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} catch (MessagingException e1) {
			
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			
			e1.printStackTrace();
		}
	}
	
	private static void _sendEmail(Session session, String toemail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@mef.gob.pe", "NoReply-JD"));

			msg.setReplyTo(InternetAddress.parse("no_reply@mef.gob.pe", false));

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");
			msg.setSentDate(new Date());
							try {
				
					msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toemail, false));
				    System.out.println("Message is ready");
				    Transport.send(msg);

				    System.out.println("EMail Sent Successfully!!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void _simpleEmail() {

		System.out.println("SimpleEmail Start");

		String smtpHostServer = "smtp.example.com";
		String emailID = "email_me@example.com";

		Properties props = System.getProperties();

		props.put("mail.smtp.host", smtpHostServer);
		 //props.put("mail.smtp.debug", "true");

		Session session = Session.getInstance(props, null);

		_sendEmail(session, emailID, "SimpleEmail Testing Subject", "SimpleEmail Testing Body");
	}

	public static void tlsEmail() {
		final String fromEmail = "myemailid@gmail.com"; // requires valid gmail id
		final String password = "mypassword"; // correct password for gmail id
		final String toEmail = "myemail@yahoo.com"; // can be any email id

		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
		 //props.put("mail.smtp.debug", "true");

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);

		_sendEmail(session, toEmail, "TLSEmail Testing Subject", "TLSEmail Testing Body");

	}

	public static void sslEmail() {
		final String fromEmail = "myemailid@gmail.com"; // requires valid gmail id
		final String password = "mypassword"; // correct password for gmail id
		final String toEmail = "myemail@yahoo.com"; // can be any email id

		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
		props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); // SMTP Port
		 //props.put("mail.smtp.debug", "true");

		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
		_sendEmail(session, toEmail, "SSLEmail Testing Subject", "SSLEmail Testing Body");

		_sendAttachmentEmail(session, toEmail, "SSLEmail Testing Subject with Attachment", "SSLEmail Testing Body with Attachment");

		_sendImageEmail(session, toEmail, "SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");

	}

	private static void _sendAttachmentEmail(Session session, String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

			msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

			msg.setSubject(subject, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
//			messageBodyPart.setText(body);
			messageBodyPart.setContent(body, "text/html");

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = "abc.txt";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			msg.setContent(multipart);

			// Send message
			Transport.send(msg);
			System.out.println("EMail Sent Successfully with attachment!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private static void _sendImageEmail(Session session, String toEmail, String subject, String body){
		try{
	         MimeMessage msg = new MimeMessage(session);
	         msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		     msg.addHeader("format", "flowed");
		     msg.addHeader("Content-Transfer-Encoding", "8bit");
		      
		     msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

		     msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

		     msg.setSubject(subject, "UTF-8");

		     msg.setSentDate(new Date());

		     msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
		      
	         // Create the message body part
	         BodyPart messageBodyPart = new MimeBodyPart();

//	         messageBodyPart.setText(body);
	         messageBodyPart.setContent(body, "text/html");
	         
	         // Create a multipart message for attachment
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Second part is image attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "image.png";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         //Trick is to add the content-id header here
	         messageBodyPart.setHeader("Content-ID", "image_id");
	         multipart.addBodyPart(messageBodyPart);

	         //third part for displaying image in the email body
	         messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setContent("<h1>Attached Image</h1>" +
	        		     "<img src='cid:image_id'>", "text/html");
	         multipart.addBodyPart(messageBodyPart);
	         
	         //Set the multipart message to the email message
	         msg.setContent(multipart);

	         // Send message
	         Transport.send(msg);
	         System.out.println("EMail Sent Successfully with image!!");
	      }catch (MessagingException e) {
	         e.printStackTrace();
	      } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		}
	}

///LEER FOLDER	
	public String getMessages() throws MessagingException{
		StringBuffer sb = new StringBuffer();
		
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		@SuppressWarnings("unused")
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("emailid","password");
		}
		});

		Session sessionpop = Session.getInstance(props, null);
		Store store = sessionpop.getStore();
		store.connect("imap.gmail.com", "emailid", "password");
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_WRITE);
		//Message msg = inbox.getMessage(inbox.getMessageCount());
		// Get the messages which is unread in the Inbox//

		Message messages[] = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));

		System.out.println("Unread e-mail count : "+ messages.length);
		
		return sb.toString(); 
	}
	
	public Store getImapSession() throws MessagingException, NoSuchProviderException {
	    Properties props = new Properties();
	    props.put("mail.imap.ssl.trust", "*");
	    props.put("mail.imaps.ssl.trust", "*");
	    props.setProperty("mail.imap.starttls.enable", Boolean.toString(useSsl));
	    Session session = Session.getInstance(props, null);
	    Store store = session.getStore(useSsl ? "imaps" : "imap");
	    store.connect(serverName, userName, password);
	    return store;
	}

	@Override
	public void closed(ConnectionEvent arg0) {
		if(debug)
			log.info("Cerrando conexi�n "+arg0.toString());
	}

	@Override
	public void disconnected(ConnectionEvent arg0) {
		if(debug)
			log.info("Desconectandose "+arg0.toString());
	}

	@Override
	public void opened(ConnectionEvent arg0) {
		if(debug)
			log.info("Abriendo conexi�n "+arg0.toString());
	}

	@Override
	public void messageDelivered(TransportEvent arg0) {
		if(debug){
			log.info("Message delivered for: ");		
		if (arg0 != null) {
			Address[] a = arg0.getValidSentAddresses();
			if (a != null && a.length > 0) {
				for (int i = 0; i < a.length; i++) {
					log.info(a[i].toString());
				}
			}
		}}
	}

	@Override
	public void messageNotDelivered(TransportEvent arg0) {
		if(debug){
			log.info("Message not delivered for:");
		if (arg0 != null) {
			Address[] a = arg0.getValidUnsentAddresses();
			if (a != null && a.length > 0) {
				for (int i = 0; i < a.length; i++) {
					log.info(a[i].toString());
				}
			}
		}}
	}

	@Override
	public void messagePartiallyDelivered(TransportEvent arg0) {
		if(debug){
			log.info("These addresses are invalid:");
		if (arg0 != null) {
			Address[] a = arg0.getInvalidAddresses();
			if (a != null && a.length > 0) {
				for (int i = 0; i < a.length; i++) {
					log.info(a[i].toString());
				}
			}
		}}
	}
	
	
}
