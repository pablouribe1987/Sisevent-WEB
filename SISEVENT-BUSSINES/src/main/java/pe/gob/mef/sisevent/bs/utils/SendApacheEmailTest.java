package pe.gob.mef.sisevent.bs.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class SendApacheEmailTest implements Runnable {

	public SendApacheEmailTest() {

	}

	public static void sendEmailUtil(Session session, String toEmail, String subject, String body) {
		// Used to debug SMTP issues
		session.setDebug(true);
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

			msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			System.out.println("Message is ready");
			Transport.send(msg);

			System.out.println("EMail Sent Successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendEmailSinAutenticacion() {

		System.out.println("SimpleEmail Start");
		String smtpHostServer = "smtp.example.com";
		String emailID = "email_me@example.com";

		Properties props = System.getProperties();

		props.put("mail.smtp.host", smtpHostServer);

		Session session = Session.getInstance(props, null);

		sendEmailUtil(session, emailID, "SimpleEmail Testing Subject", "SimpleEmail Testing Body");
	}

	/**
	 * Outgoing Mail (SMTP) Server requires TLS or SSL: smtp.gmail.com (use authentication) Use Authentication: Yes Port
	 * for TLS/STARTTLS: 587
	 */
	public static void sendEmailTLS() {
		final String fromEmail = "myemailid@gmail.com"; // requires valid gmail id
		final String password = "mypassword"; // correct password for gmail id
		final String toEmail = "myemail@yahoo.com"; // can be any email id

		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);

		sendEmailUtil(session, toEmail, "TLSEmail Testing Subject", "TLSEmail Testing Body");
	}

	/**
	 * Outgoing Mail (SMTP) Server requires TLS or SSL: smtp.gmail.com (use authentication) Use Authentication: Yes Port
	 * for SSL: 465
	 */
	public static void sendEmailSSL() {
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

		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
		sendEmailUtil(session, toEmail, "SSLEmail Testing Subject", "SSLEmail Testing Body");
		sendAttachmentEmail(session, toEmail, "SSLEmail Testing Subject with Attachment",
				"SSLEmail Testing Body with Attachment");
		sendImageEmail(session, toEmail, "SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");
	}

	/**
	 * Utility method to send email with attachment
	 * 
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendAttachmentEmail(Session session, String toEmail, String subject, String body) {
		// Used to debug SMTP issues
		session.setDebug(true);
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
			messageBodyPart.setText(body);

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

	public static void sendImageEmail(Session session, String toEmail, String subject, String body) {
		// Used to debug SMTP issues
		session.setDebug(true);
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

			messageBodyPart.setText(body);

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
			// Trick is to add the content-id header here
			messageBodyPart.setHeader("Content-ID", "image_id");
			multipart.addBodyPart(messageBodyPart);

			// third part for displaying image in the email body
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("<h1>Attached Image</h1>" + "<img src='cid:image_id'>", "text/html");
			multipart.addBodyPart(messageBodyPart);

			// Set the multipart message to the email message
			msg.setContent(multipart);

			// Send message
			Transport.send(msg);
			System.out.println("EMail Sent Successfully with image!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void ping(String ipAddress) throws UnknownHostException, IOException {
		InetAddress geek = InetAddress.getByName(ipAddress);
		System.out.println("Sending Ping Request to " + ipAddress);
		if (geek.isReachable(5000))
			System.out.println("Host is reachable");
		else
			System.out.println("Sorry ! We can't reach to this host");
	}

	/**
	 * List<String> commands = new ArrayList<String>(); commands.add("ping"); boolean isWindows =
	 * System.getProperty("os.name").toLowerCase().contains("win"); commands.add(isWindows? "-n" : "-c");
	 * commands.add("5"); commands.add("74.125.236.73"); ping.doCommand(commands);
	 **/
	public void doCommand(List<String> command) throws IOException {
		String s = null;

		ProcessBuilder pb = new ProcessBuilder(command);
		Process process = pb.start();

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

		// read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}

		// read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
			System.out.println(s);
		}
	}

	public static Duration pingDuration(String host) {
		Instant startTime = Instant.now();
		try {
			InetAddress address = InetAddress.getByName(host);
			if (address.isReachable(1000)) {
				return Duration.between(startTime, Instant.now());
			}
		} catch (IOException e) {
			// Host not available, nothing to do here
		}
		return Duration.ofDays(1);
	}

	// Telnet
	// import org.apache.commons.net.telnet.TelnetClient;
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private Thread listener = null;

	/***
	 * test of client-driven subnegotiation.
	 * 
	 * 
	 * 
	 * @param port
	 *            - server port on which to listen.
	 ***/
	public SendApacheEmailTest(int port) throws IOException {
		serverSocket = new ServerSocket(port);

		listener = new Thread(this);

		listener.start();
	}

	/***
	 * Run for the thread. Waits for new connections
	 ***/
	public void run() {
		boolean bError = false;
		while (!bError) {
			try {
				clientSocket = serverSocket.accept();
				synchronized (clientSocket) {
					try {
						clientSocket.wait();
					} catch (Exception e) {
						System.err.println("Exception in wait, " + e.getMessage());
					}
					try {
						clientSocket.close();
					} catch (Exception e) {
						System.err.println("Exception in close, " + e.getMessage());
					}
				}
			} catch (IOException e) {
				bError = true;
			}
		}

		try {
			serverSocket.close();
		} catch (Exception e) {
			System.err.println("Exception in close, " + e.getMessage());
		}
	}

	/***
	 * Disconnects the client socket
	 ***/
	public void disconnect() {
		synchronized (clientSocket) {
			try {
				clientSocket.notify();
			} catch (Exception e) {
				System.err.println("Exception in notify, " + e.getMessage());
			}
		}
	}

	/***
	 * Stop the listener thread
	 ***/
	public void stop() {
		listener.interrupt();
		try {
			serverSocket.close();
		} catch (Exception e) {
			System.err.println("Exception in close, " + e.getMessage());
		}
	}

	/***
	 * Gets the input stream for the client socket
	 ***/
	public InputStream getInputStream() throws IOException {
		if (clientSocket != null) {
			return (clientSocket.getInputStream());
		} else {
			return (null);
		}
	}

	/***
	 * Gets the output stream for the client socket
	 ***/
	public OutputStream getOutputStream() throws IOException {
		if (clientSocket != null) {
			return (clientSocket.getOutputStream());
		} else {
			return (null);
		}
	}

	// EMAIL
	protected String host;
	protected String username = null;
	protected String password = null;
	protected boolean isTLS = false;

	public SendApacheEmailTest(String host, String username, String password, boolean isTLS) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.isTLS = isTLS;
	}

	public SendApacheEmailTest(String host, String username, String password) {
		this(host, username, password, false);
	}

	public SendApacheEmailTest(String host) {
		this(host, null, null, false);
	}

	// public void sendHTMLMail(String fromEmail, String fromName, String[] toEmail, String[] toName, String subject,
	// String html, String alternateText) throws EmailException {
	// HtmlEmail email = new HtmlEmail();
	// email.setHostName(host);
	// email.setFrom(fromEmail, fromName);
	// for (int i = 0; i < toEmail.length; i++) {
	// email.addTo(toEmail[i], toName[i]);
	// }
	// if (username != null) {
	// email.setAuthentication(username, password);
	// }
	// email.setTLS(isTLS);
	// email.setSubject(subject);
	// email.setHtmlMsg(html);
	// email.setTextMsg(alternateText);
	// email.send();
	// }

	public void sendHTMLMail(String fromEmail, String fromName, String[] toEmail, String[] toName, String subject,
			String html, String alternateText, Resource[] resources) throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(host);
		email.setFrom(fromEmail, fromName);
		for (int i = 0; i < toEmail.length; i++) {
			email.addTo(toEmail[i], toName[i]);
		}
		if (username != null) {
			email.setAuthentication(username, password);
		}
		if (resources != null) {
			int i = 0;
			for (Resource resource : resources) {
				String id = email.embed(resource.getUrl(), resource.getName());
				html = html.replaceAll("\\$\\{" + i++ + "\\}", "cid:" + id);
			}
		}
		email.setTLS(isTLS);
		email.setSubject(subject);
		email.setHtmlMsg(html);
		email.setTextMsg(alternateText);
		email.send();
	}

	public void sendHTMLMail(String fromEmail, String fromName, String[] toEmail, String[] toName, String subject,
			String html, String alternateText) throws EmailException {
		sendHTMLMail(fromEmail, fromName, toEmail, toName, subject, html, alternateText, null);
	}

	public static class Resource {
		private URL url;
		private String name;

		public Resource(URL url, String name) {
			this.url = url;
			this.name = name;
		}

		public Resource(String url, String name) throws MalformedURLException {
			this(new URL(url), name);
		}

		public URL getUrl() {
			return url;
		}

		public void setUrl(URL url) {
			this.url = url;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static void Ejemmain(String[] args) throws Exception {
		new SendApacheEmailTest("smtp.gmail.com", "youruser@gmail.com", "yourpassword", true)
				.sendHTMLMail("admin@test.com", "Tester", new String[] { "guy.bashan@gmail.com" },
						new String[] { "Guy Bashan" }, "Testing HTML Email",
						"<html><body><table><tr><td>Image 1</td><td>Image 2</td></tr><tr><td>"
								+ "<img src='${0}'/></td><td><img src='${1}'/></td></tr></table></body></html>",
						"alternate HTML",
						new Resource[] {
								new Resource("http://www.google.com/intl/en_ALL/images/logo.gif", "Google Logo 1"),
								new Resource("http://www.google.com/intl/en_ALL/images/logo.gif", "Google Logo 2") });
	}

	public static final String HOST_NAME = "smtp.gmail.com";
	public static final int PORT = 465;
	public static final String TEXT_PLAIN = "text/plain";

	public static void Ejemplo2main(String[] args) throws IOException, EmailException {

		final String userName = args[0];
		final String password = args[1];
		final String recipientEmailAddress = args[2];
		final String attachmentLocation = args[3];

		HtmlEmail email = new HtmlEmail();
		email.setHostName(HOST_NAME);
		email.setSmtpPort(PORT);
		email.setSSLOnConnect(true);

		email.setAuthentication(userName, password);

		email.setSubject("Some subject");
		email.setFrom(userName, "Firstname Lastname", String.valueOf(StandardCharsets.UTF_8));
		email.addTo(recipientEmailAddress);
		email.setHtmlMsg("<h3>Message body</h3>");

		ByteArrayDataSource dataSource = new ByteArrayDataSource(new FileInputStream(attachmentLocation), TEXT_PLAIN);
		email.attach(dataSource, "attachment", "attachment");
		email.send();
	}

	public static void sendemailjavamailGooglr(){
    	try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "false");
            props.put("mail.smtp.ssl.enable", "true");

            Authenticator auth = new Authenticator() {
    			// override the getPasswordAuthentication method
    			protected PasswordAuthentication getPasswordAuthentication() {
    				return new PasswordAuthentication("sendersEmailAddress", "password");
    			}
    		};
    		
            Session session = Session.getInstance(props, auth);
            Message msg = new MimeMessage(session);

            InternetAddress from = new InternetAddress("sendersEmailAddress", "Sender's name");
            msg.setFrom(from);

            InternetAddress toAddress = new InternetAddress("Receiver's email");

            msg.setRecipient(Message.RecipientType.TO, toAddress);

            msg.setSubject("Test");
//            msg.setContent(msg.setContent("<html>\n" +
//                    "<body>\n" +
//                    "\n" +
//                    "<a href=\"http://pushpalankajaya.blogspot.com\">\n" +
//                    "This is a link</a>\n" +
//                    "\n" +
//                    "</body>\n" +
//                    "</html>", "text/html");, "text/html");
            Transport.send(msg);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

	public static void sendemailHtmlEmailGooglr() throws EmailException {
		 HtmlEmail email = new HtmlEmail();
	        email.setHostName("smtp.gmail.com");
	        email.setSmtpPort(465);
	        email.setAuthenticator(new DefaultAuthenticator("sendersEmailAddress", "password"));
	        email.setSSLOnConnect(true);
	        email.setFrom("Senders' email");
	        email.setSubject("TestMail- Alternative message");
	        email.setHtmlMsg("<html>\n" +
	                "<body>\n" +
	                "\n" +
	                "<a href=\"http://pushpalankajaya.blogspot.com\">\n" +
	                "This is a link</a>\n" +
	                "\n" +
	                "</body>\n" +
	                "</html>");
	        // set the alternative message
	        email.setTextMsg("This is a link: http://pushpalankajaya.blogspot.com");
	        email.addTo("lanka@wso2.com");
	        email.send();
	}
}
