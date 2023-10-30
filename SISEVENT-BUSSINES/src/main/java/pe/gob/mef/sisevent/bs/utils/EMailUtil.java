package pe.gob.mef.sisevent.bs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import pe.gob.mef.sisevent.bs.exception.Validador;
import pe.gob.mef.sisevent.bs.transfer.bk.MsUsuariosBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdAnexosBk;
import pe.gob.mef.sisevent.bs.transfer.bk.TdFlujoBk;

public class EMailUtil {

	public EMailUtil() {
		// TODO Auto-generated constructor stub
	}

////vbaldeon 25092023 inicio
	public static void sendEmail(String tipo, String fromEmail, String password, String toEmail, String SMTPHost,
			String SSLPort, String SMTPPort, String subject, String body, TdFlujoBk tdFlujoBk,
			String remite, MsUsuariosBk usuarioasignado, List<TdAnexosBk> adjuntos, boolean debugmail, int tiposent) throws Validador {
		
		if (tipo.equalsIgnoreCase("SSL")) {
			sendEmailSSL(fromEmail, password, toEmail, SMTPHost, SSLPort, SMTPPort, subject, body, tdFlujoBk,
					remite, usuarioasignado, adjuntos, debugmail, tiposent);
		} else if (tipo.equalsIgnoreCase("TLS")) {
			sendEmailTLS(fromEmail, password, toEmail, SMTPHost, SMTPPort, subject, body, tdFlujoBk,
					remite, usuarioasignado, adjuntos, debugmail, tiposent);
	} else {
			sendEmailSinAutenticacion(toEmail, SMTPHost, subject, body, tdFlujoBk, remite, usuarioasignado, adjuntos,
					debugmail, tiposent);
		}
	}
	 private static void sendEmailSSL(String fromEmail, final String password, String toEmail, String SMTPHost, String SSLPort, String SMTPPort, String subject, String body, TdFlujoBk tdFlujoBk, String remite, MsUsuariosBk usuarioasignado, List<TdAnexosBk> adjuntos, boolean debugmail, int tiposent)
			    throws Validador
			  {
			    System.out.println("SSLEmail Start");
			    Properties props = new Properties();
			    props.put("mail.smtp.host", SMTPHost);
			    props.put("mail.smtp.socketFactory.port", SSLPort);
			    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			    props.put("mail.smtp.auth", "true");
			    props.put("mail.smtp.port", SMTPPort);
			    
			    Authenticator auth = new Authenticator()
			    {
			      protected PasswordAuthentication getPasswordAuthentication()
			      {
//			        return new PasswordAuthentication(EMailUtil.this, password); 
			    	  return new PasswordAuthentication(fromEmail, password); 
			      }
			    };
			    Session session = Session.getDefaultInstance(props, auth);
			    switch (tiposent)
			    {
			    case 0: 
			      sendEmail(session, toEmail, subject, body, tdFlujoBk, remite, adjuntos, debugmail);
			      
			      break;
			    case 1: 
			      sendAlertal(session, toEmail, subject, body, tdFlujoBk, usuarioasignado, adjuntos, debugmail);
			    }
			  }

	private static void sendEmailTLS(String fromEmail, final String password, String toEmail, String SMTPHost, String SMTPPort, String subject, String body, TdFlujoBk tdFlujoBk, String remite, MsUsuariosBk usuarioasignado, List<TdAnexosBk> adjuntos, boolean debugmail, int tiposent)
		    throws Validador
		  {
		    System.out.println("TLSEmail Start");
		    Properties props = new Properties();
		    props.put("mail.smtp.host", SMTPHost);
		    props.put("mail.smtp.port", SMTPPort);
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    
		    Authenticator auth = new Authenticator()
		    {
		      protected PasswordAuthentication getPasswordAuthentication()
		      {
//		        return new PasswordAuthentication(EMailUtil.this, password);
		    	  return new PasswordAuthentication(fromEmail, password);
		        
		      }
		    };
		    Session session = Session.getInstance(props, auth);
		    switch (tiposent)
		    {
		    case 0: 
		      sendEmail(session, toEmail, subject, body, tdFlujoBk, remite, adjuntos, debugmail);
		      
		      break;
		    case 1: 
		      sendAlertal(session, toEmail, subject, body, tdFlujoBk, usuarioasignado, adjuntos, debugmail);
		    }
		  }

	  private static void sendEmailSinAutenticacion(String toEmail, String SMTPHost, String subject, String body, TdFlujoBk tdFlujoBk, String remite, MsUsuariosBk usuarioasignado, List<TdAnexosBk> adjuntos, boolean debugmail, int tiposent)
			    throws Validador
			  {
			    System.out.println("SimpleEmail Start");
			    
			    Properties props = System.getProperties();
			    props.put("mail.smtp.host", SMTPHost);
			    
			    Session session = Session.getInstance(props, null);
			    switch (tiposent)
			    {
			    case 0: 
			      sendEmail(session, toEmail, subject, body, tdFlujoBk, remite, adjuntos, debugmail);
			      
			      break;
			    case 1: 
			      sendAlertal(session, toEmail, subject, body, tdFlujoBk, usuarioasignado, adjuntos, debugmail);
			    }
			  }
	  

	private static void sendEmail(Session session, String toEmail, String subject, String body,
			TdFlujoBk tdFlujoBk,String remite, List<TdAnexosBk> adjuntos, boolean debugmail) throws Validador {
		// Used to debug SMTP issues
		session.setDebug(debugmail);
		try {

//			String remite = tdFlujoBk.getNombreCompleto();
			String uoresponde = tdFlujoBk.getIdunidadDerivaTxt();

			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@mef.gob.pe", "NoReply-JD"));

			msg.setReplyTo(InternetAddress.parse("no_reply@mef.gob.pe.com", false));

			msg.setSubject(subject, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			// Create the message body part
			// BodyPart messageBodyPart = new MimeBodyPart();

			// messageBodyPart.setText(body);

			ClassLoader classLoader = EMailUtil.class.getClassLoader();
			File plantillaFile = new File(classLoader.getResource("plantilla3.html").getFile());
			if (!plantillaFile.exists()) {
				Resource resource = new ClassPathResource("plantilla3.html");
				try {
					plantillaFile = resource.getFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (!plantillaFile.exists()) {
				throw new Validador("NO SE ENCUENTRA LA PLANTILLA...");
			}
			File logoFile = new File(classLoader.getResource("logo.png").getFile());
			if (!logoFile.exists()) {
				Resource resource = new ClassPathResource("logo.png");
				try {
					logoFile = resource.getFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (!plantillaFile.exists()) {
				throw new Validador("NO SE ENCUENTRA LA PLANTILLA...");
			}

			StringWriter sw = new StringWriter();
			InputStreamReader in = new InputStreamReader(new FileInputStream(plantillaFile), "UTF-8");
			char[] buffer = new char[1024 * 2];
			int n = 0;
			while ((n = in.read(buffer)) != -1) {
				sw.write(buffer, 0, n);
			}
			in.close();

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			// multipart.addBodyPart(messageBodyPart);

			// Second part is image attachment
			BodyPart messageBodyPart = new MimeBodyPart();

			DataSource sourceImage = new FileDataSource(logoFile);
			messageBodyPart.setDataHandler(new DataHandler(sourceImage));
			messageBodyPart.setFileName("logo.png");
			// Trick is to add the content-id header here
			messageBodyPart.setHeader("Content-ID", "IDLOGO");
			multipart.addBodyPart(messageBodyPart);

			//////////////////////////
			if (adjuntos != null) {
				for (TdAnexosBk tdAnexosBk : adjuntos) {
					File archivo = FuncionesStaticas.getFileSistemaCompletoSearch(tdAnexosBk.getFilename(),
							tdAnexosBk.getFechaCrea());
					if (archivo != null && archivo.exists()) {
						// Second part is attachment
						MimeBodyPart attachPart = new MimeBodyPart();
						DataSource sourcearchivo = new FileDataSource(archivo);
						attachPart.setDataHandler(new DataHandler(sourcearchivo));
						attachPart.setFileName(tdAnexosBk.getFilenameoriginal());
						multipart.addBodyPart(attachPart);
					}
				}
			}
			//////////////////////////
			String splantilla = sw.toString();
			String mensage = null;
			if (remite != null) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					remite = remite.replace(entry.getKey(), entry.getValue());
				}
				mensage = splantilla.replace("[[REMITENTE]]", remite);
			} else
				mensage = splantilla.replace("[[REMITENTE]]", "");
			if (uoresponde != null) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					uoresponde = uoresponde.replace(entry.getKey(), entry.getValue());
				}
				mensage = mensage.replace("[[UOQUERESPONDE]]", uoresponde);
			} else
				mensage = mensage.replace("[[UOQUERESPONDE]]", "");
			if (body != null) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					body = body.replace(entry.getKey(), entry.getValue());
				}
				mensage = mensage.replace("[[CUERPO]]", body);
			} else
				mensage = mensage.replace("[[CUERPO]]", "");

			// third part for displaying image in the email body
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(mensage, "text/html");
			multipart.addBodyPart(messageBodyPart);

			// Set the multipart message to the email message
			msg.setContent(multipart);

			// Send message
			Transport.send(msg);

		} catch (MessagingException e) {
			// e.printStackTrace();
			throw new Validador(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			throw new Validador(e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new Validador(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	private static void sendAlertal(Session session, String toEmail, String subject, String body,
			TdFlujoBk tdFlujoBk, MsUsuariosBk usuarioasignado, List<TdAnexosBk> adjuntos, boolean debugmail)
			throws Validador {
		// Used to debug SMTP issues
		session.setDebug(debugmail);
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@mef.gob.pe", "NoReply-JD"));

			msg.setReplyTo(InternetAddress.parse("no_reply@mef.gob.pe.com", false));

			msg.setSubject(subject, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			// Create the message body part
			// BodyPart messageBodyPart = new MimeBodyPart();

			// messageBodyPart.setText(body);

			ClassLoader classLoader = EMailUtil.class.getClassLoader();
			File plantillaFile = new File(classLoader.getResource("plantilla2.html").getFile());
			if (!plantillaFile.exists()) {
				Resource resource = new ClassPathResource("plantilla2.html");
				try {
					plantillaFile = resource.getFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (!plantillaFile.exists()) {
				throw new Validador("NO SE ENCUENTRA LA PLANTILLA...");
			}
			File logoFile = new File(classLoader.getResource("logo.png").getFile());
			if (!logoFile.exists()) {
				Resource resource = new ClassPathResource("logo.png");
				try {
					logoFile = resource.getFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (!plantillaFile.exists()) {
				throw new Validador("NO SE ENCUENTRA LA PLANTILLA...");
			}

			StringWriter sw = new StringWriter();
			InputStreamReader in = new InputStreamReader(new FileInputStream(plantillaFile), "UTF-8");
			char[] buffer = new char[1024 * 2];
			int n = 0;
			while ((n = in.read(buffer)) != -1) {
				sw.write(buffer, 0, n);
			}
			in.close();

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			// multipart.addBodyPart(messageBodyPart);

			// Second part is image attachment
			BodyPart messageBodyPart = new MimeBodyPart();

			DataSource sourceImage = new FileDataSource(logoFile);
			messageBodyPart.setDataHandler(new DataHandler(sourceImage));
			messageBodyPart.setFileName("logo.png");
			// Trick is to add the content-id header here
			messageBodyPart.setHeader("Content-ID", "IDLOGO");
			multipart.addBodyPart(messageBodyPart);

			//////////////////////////
			if (adjuntos != null) {
				for (TdAnexosBk tdAnexosBk : adjuntos) {
					File archivo = FuncionesStaticas.getFileSistemaCompletoSearch(tdAnexosBk.getFilename(),
							tdAnexosBk.getFechaCrea());
					if (archivo != null && archivo.exists()) {
						// Second part is attachment
						MimeBodyPart attachPart = new MimeBodyPart();
						DataSource sourcearchivo = new FileDataSource(archivo);
						attachPart.setDataHandler(new DataHandler(sourcearchivo));
						attachPart.setFileName(tdAnexosBk.getFilenameoriginal());
						multipart.addBodyPart(attachPart);
					}
				}
			}
			//////////////////////////
			String splantilla = sw.toString();
			String mensage = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String sREMITENTE = usuarioasignado.geNombreCompleto();
			String sNUMERO = "";
			String sANIO = "";
			String sNOMBREAPELLIDO = usuarioasignado.geNombreCompleto();
			String sNUMERODOCUMENTO = "";
			String sCORREO = usuarioasignado.getCorreo() == null ? "" : usuarioasignado.getCorreo();
			String sTELEFONO ="";
			String sCELULAR = usuarioasignado.getCelular() == null ? "" : usuarioasignado.getCelular();
			String sENTIDAD = usuarioasignado.getIdunidadTxt()== null ? "" : usuarioasignado.getIdunidadTxt();
			String sTEMA = "";
			String sSUBTEMA = "";
			String sHOJARUTA = "";
			String sDETALLE = "";
			String sOBSERVACION = body == null ? "" : body;
			String sFECHA = tdFlujoBk.getFechaDerivacion() == null ? ""
					: sdf.format(tdFlujoBk.getFechaDerivacion());

			if (sREMITENTE != null && sREMITENTE.length() > 1) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					sREMITENTE = sREMITENTE.replace(entry.getKey(), entry.getValue());
				}
			}
			mensage = splantilla.replace("[[REMITENTE]]", sREMITENTE);
			
			if (sNUMERO != null && sNUMERO.length() > 1) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					sNUMERO = sNUMERO.replace(entry.getKey(), entry.getValue());
				}
			}
			mensage = mensage.replace("[[NUMERO]]", sNUMERO);
			
			if (sANIO != null && sANIO.length() > 1) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					sANIO = sANIO.replace(entry.getKey(), entry.getValue());
				}
			}
			mensage = mensage.replace("[[ANIO]]", sANIO);
			
			if (sNOMBREAPELLIDO != null && sNOMBREAPELLIDO.length() > 1) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					sNOMBREAPELLIDO = sNOMBREAPELLIDO.replace(entry.getKey(), entry.getValue());
				}
			}			
			mensage = mensage.replace("[[NOMBREAPELLIDO]]", sNOMBREAPELLIDO);
			
			if (sNUMERODOCUMENTO != null && sNUMERODOCUMENTO.length() > 1) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					sNUMERODOCUMENTO = sNUMERODOCUMENTO.replace(entry.getKey(), entry.getValue());
				}
			}
			mensage = mensage.replace("[[NUMERODOCUMENTO]]", sNUMERODOCUMENTO);
			
			if (sCORREO != null && sCORREO.length() > 1) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					sCORREO = sCORREO.replace(entry.getKey(), entry.getValue());
				}
			}
			mensage = mensage.replace("[[CORREO]]", sCORREO);
			
			if (sTELEFONO != null && sTELEFONO.length() > 1) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					sTELEFONO = sTELEFONO.replace(entry.getKey(), entry.getValue());
				}
			}
			mensage = mensage.replace("[[TELEFONO]]", sTELEFONO);
			//MPINARES 27042022 - INICIO
		      if ((sCELULAR != null) && (sCELULAR.length() > 1)) {
		          for (Map.Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
		        	  sCELULAR = sCELULAR.replace((CharSequence)entry.getKey(), (CharSequence)entry.getValue());
		          }
		        }
		        mensage = mensage.replace("[[CELULAR]]", sCELULAR);
		    //MPINARES 27042022 - FIN
			if (sENTIDAD != null && sENTIDAD.length() > 1) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					sENTIDAD = sENTIDAD.replace(entry.getKey(), entry.getValue());
				}
			}			
			mensage = mensage.replace("[[ENTIDAD]]", sENTIDAD);
			
			if (sTEMA != null && sTEMA.length() > 1) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					sTEMA = sTEMA.replace(entry.getKey(), entry.getValue());
				}
			}
			mensage = mensage.replace("[[TEMA]]", sTEMA);
			
			if (sSUBTEMA != null && sSUBTEMA.length() > 1) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					sSUBTEMA = sSUBTEMA.replace(entry.getKey(), entry.getValue());
				}
			}
			mensage = mensage.replace("[[SUBTEMA]]", sSUBTEMA);
			 //MPINARES 27042022 - INICIO
		      if ((sHOJARUTA != null) && (sHOJARUTA.length() > 1)) {
		          for (Map.Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
		        	  sHOJARUTA = sHOJARUTA.replace((CharSequence)entry.getKey(), (CharSequence)entry.getValue());
		          }
		        }
		        mensage = mensage.replace("[[HOJARUTA]]", sHOJARUTA);
		    //MPINARES 27042022 - FIN
			if (sDETALLE != null && sDETALLE.length() > 1) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					sDETALLE = sDETALLE.replace(entry.getKey(), entry.getValue());
				}
			}
			mensage = mensage.replace("[[DETALLE]]", sDETALLE);
			
			if (sOBSERVACION != null && sOBSERVACION.length() > 1) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					sOBSERVACION = sOBSERVACION.replace(entry.getKey(), entry.getValue());
				}
			}
			mensage = mensage.replace("[[OBSERVACION]]", sOBSERVACION);
			
			if (sFECHA != null && sFECHA.length() > 1) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					sFECHA = sFECHA.replace(entry.getKey(), entry.getValue());
				}
			}
			mensage = mensage.replace("[[FECHA]]", sFECHA);

			// third part for displaying image in the email body
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(mensage, "text/html");
			multipart.addBodyPart(messageBodyPart);

			// Set the multipart message to the email message
			msg.setContent(multipart);

			// Send message
			Transport.send(msg);

		} catch (MessagingException e) {
			// e.printStackTrace();
			throw new Validador(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			throw new Validador(e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new Validador(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	  
		////vbaldeon 25092023 fin

	public static String getPreView(String body, String remite, String uoresponde, List<TdAnexosBk> tdAnexosBkss) throws Validador {//MPINARES 27042022 - INICIO
		ClassLoader classLoader = EMailUtil.class.getClassLoader();
		File plantillaFile = new File(classLoader.getResource("plantilla3.html").getFile());
		if (!plantillaFile.exists()) {
			Resource resource = new ClassPathResource("plantilla3.html");
			try {
				plantillaFile = resource.getFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!plantillaFile.exists()) {
			throw new Validador("NO SE ENCUENTRA LA PLANTILLA...");
		}

		try {
			StringWriter sw = new StringWriter();
			InputStreamReader in = new InputStreamReader(new FileInputStream(plantillaFile), "UTF-8");
			char[] buffer = new char[1024 * 2];
			int n = 0;
			while ((n = in.read(buffer)) != -1) {
				sw.write(buffer, 0, n);
			}
			in.close();

			String splantilla = sw.toString();
			String mensage = null;
			if (remite != null)
				mensage = splantilla.replace("[[REMITENTE]]", remite);
			else
				mensage = splantilla.replace("[[REMITENTE]]", "");
			if (uoresponde != null)
				mensage = mensage.replace("[[UOQUERESPONDE]]", uoresponde);
			else
				mensage = mensage.replace("[[UOQUERESPONDE]]", "");
			if (body != null) {
				for (Entry<String, String> entry : HTML_ESPECIAL.entrySet()) {
					body = body.replace(entry.getKey(), entry.getValue());
				}
				mensage = mensage.replace("[[CUERPO]]", body);
			} else
				mensage = mensage.replace("[[CUERPO]]", "");

			//MPINARES 27042022 - INICIO
			if(tdAnexosBkss!=null && tdAnexosBkss.size()>0){
				StringBuffer sb1 = new StringBuffer();
				sb1.append("<table>");
				for(TdAnexosBk anx:tdAnexosBkss){
					sb1.append("<tr><td style=\"font-size:15px;\"><img src=\"resources/imagen/mime/"+anx.getTipo()+".gif\" alt=\"{{"+ anx.getFilenameoriginal()+"}}\" style=\"float:left;\">"+anx.getFilenameoriginal()+"</td></tr>");
				}
				sb1.append("</table>");
				mensage = mensage.replace("[[DIGITALES]]", sb1.toString());
			}else{
				mensage = mensage.replace("[[DIGITALES]]", "");
			}
			//MPINARES 27042022 - FIN
			
			mensage = mensage.replace("cid:IDLOGO", "resources/imagen/u12.png");

			return mensage;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new Validador(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new Validador(e.getMessage());
		}
	}

	public static Map<String, String> HTML_ESPECIAL = new HashMap<String, String>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4223655195734244184L;

		{
			// put("\"", "&quot;");
			// put("&", "&amp;");
			// put("/", "&sol;");
			// put("<", "&lt;");
			// put(">", "&gt;");
			// put("‚", "&sbquo;");
			put("„", "&bdquo;");
			put("†", "&dagger;");
			put("‡", "&Dagger;");
			put("‰", "&permil;");
			put("‹", "&lsaquo;");
			put("‘", "&lsquo;");
			put("’", "&rsquo;");
			put("“", "&ldquo;");
			put("”", "&rdquo;");
			put("™", "&trade;");
			put("›", "&rsaquo;");
			put("  ", "&nbsp;&nbsp;");
			put("¡", "&iexcl;");
			put("¢", "&cent;");
			put("£", "&pound;");
			put("¤", "&curren;");
			put("¥", "&yen;");
			put("¦", "&brvbar;");
			put("§", "&sect;");
			put("¨", "&uml;");
			put("©", "&copy;");
			put("ª", "&ordf;");
			put("«", "&laquo;");
			put("¬", "&not;");
			// put(" ", "&shy;");
			put("®", "&reg;");
			put("¯", "&macr;");
			put("°", "&deg;");
			put("±", "&plusmn;");
			put("²", "&sup2;");
			put("³", "&sup3;");
			put("´", "&acute;");
			put("µ", "&micro;");
			put("¶", "&para;");
			put("·", "&middot;");
			put("¸", "&cedil;");
			put("¹", "&sup1;");
			put("º", "&ordm;");
			put("»", "&raquo;");
			put("¼", "&frac14;");
			put("½", "&frac12;");
			put("¾", "&frac34;");
			put("¿", "&iquest;");
			put("À", "&Agrave;");
			put("Á", "&Aacute;");
			put("Â", "&Acirc;");
			put("Ã", "&Atilde;");
			put("Ä", "&Auml;");
			put("Å", "&Aring;");
			put("Æ", "&AElig;");
			put("Ç", "&Ccedil;");
			put("È", "&Egrave;");
			put("É", "&Eacute;");
			put("Ê", "&Ecirc;");
			put("Ë", "&Euml;");
			put("Ì", "&Igrave;");
			put("Í", "&Iacute;");
			put("Î", "&Icirc;");
			put("Ï", "&Iuml;");
			put("Ð", "&ETH;");
			put("Ñ", "&Ntilde;");
			put("Ò", "&Ograve;");
			put("Ó", "&Oacute;");
			put("Ô", "&Ocirc;");
			put("Õ", "&Otilde;");
			put("Ö", "&Ouml;");
			put("×", "&times;");
			put("Ø", "&Oslash;");
			put("Ù", "&Ugrave;");
			put("Ú", "&Uacute;");
			put("Û", "&Ucirc;");
			put("Ü", "&Uuml;");
			put("Ý", "&Yacute;");
			put("Þ", "&THORN;");
			put("ß", "&szlig;");
			put("à", "&agrave;");
			put("á", "&aacute;");
			put("â", "&acirc;");
			put("ã", "&atilde;");
			put("ä", "&auml;");
			put("å", "&aring;");
			put("æ", "&aelig;");
			put("ç", "&ccedil;");
			put("è", "&egrave;");
			put("é", "&eacute;");
			put("ê", "&ecirc;");
			put("ë", "&euml;");
			put("ì", "&igrave;");
			put("í", "&iacute;");
			put("î", "&icirc;");
			put("ï", "&iuml;");
			put("ð", "&eth;");
			put("ñ", "&ntilde;");
			put("ò", "&ograve;");
			put("ó", "&oacute;");
			put("ô", "&ocirc;");
			put("õ", "&otilde;");
			put("ö", "&ouml;");
			put("÷", "&divide;");
			put("ø", "&oslash;");
			put("ù", "&ugrave;");
			put("ú", "&uacute;");
			put("û", "&ucirc;");
			put("ü", "&uuml;");
			put("ý", "&yacute;");
			put("þ", "&thorn;");
			put("ÿ", "&yuml;");
			put("ƒ", "&fnof;");
			put("Α", "&Alpha;");
			put("Β", "&Beta;");
			put("Γ", "&Gamma;");
			put("Δ", "&Delta;");
			// put("Ε", "&Epsilon;");
			// put("Ζ", "&Zeta;");
			// put("Η", "&Eta;");
			put("Θ", "&Theta;");
			// put("Ι", "&Iota;");
			// put("Κ", "&Kappa;");
			put("Λ", "&Lambda;");
			// put("Μ", "&Mu;");
			// put("Ν", "&Nu;");
			put("Ξ", "&Xi;");
			// put("Ο", "&Omicron;");
			put("Π", "&Pi;");
			// put("Ρ", "&Rho;");
			put("Σ", "&Sigma;");
			put("Τ", "&Tau;");
			put("Υ", "&Upsilon;");
			put("Φ", "&Phi;");
			put("Χ", "&Chi;");
			put("Ψ", "&Psi;");
			put("Ω", "&Omega;");
			put("α", "&alpha;");
			put("β", "&beta;");
			put("γ", "&gamma;");
			put("δ", "&delta;");
			put("ε", "&epsilon;");
			put("ζ", "&zeta;");
			put("η", "&eta;");
			put("θ", "&theta;");
			put("ι", "&iota;");
			put("κ", "&kappa;");
			put("λ", "&lambda;");
			put("μ", "&mu;");
			put("ν", "&nu;");
			put("ξ", "&xi;");
			put("ο", "&omicron;");
			put("π", "&pi;");
			put("ρ", "&rho;");
			put("ς", "&sigmaf;");
			put("σ", "&sigma;");
			put("τ", "&tau;");
			put("υ", "&upsilon;");
			put("φ", "&phi;");
			put("χ", "&chi;");
			put("ψ", "&psi;");
			put("ω", "&omega;");
			put("ϑ", "&thetasym;");
			put("ϒ", "&upsih;");
			put("ϖ", "&piv;");
			put("•", "&bull;");
			put("…", "&hellip;");
			put("′", "&prime;");
			put("″", "&Prime;");
			put("‾", "&oline;");
			put("ℑ", "&image;");
			put("℘", "&weierp;");
			put("ℜ", "&real;");
			put("ℵ", "&alefsym;");
			put("←", "&larr;");
			put("↑", "&uarr;");
			put("→", "&rarr;");
			put("↓", "&darr;");
			put("↔", "&harr;");
			put("↵", "&crarr;");
			put("⇐", "&lArr;");
			put("⇑", "&uArr;");
			put("⇒", "&rArr;");
			put("⇓", "&dArr;");
			put("⇔", "&hArr;");
			put("∀", "&forall;");
			put("∂", "&part;");
			put("∃", "&exist;");
			put("∅", "&empty;");
			put("∇", "&nabla;");
			put("∈", "&isin;");
			put("∉", "&notin;");
			put("∋", "&ni;");
			put("∏", "&prod;");
			put("∑", "&sum;");
			put("−", "&minus;");
			// put("∗", "&lowast;");
			put("√", "&radic;");
			put("∝", "&prop;");
			put("∞", "&infin;");
			put("∠", "&ang;");
			put("∧", "&and;");
			put("∨", "&or;");
			put("∩", "&cap;");
			put("∪", "&cup;");
			put("∫", "&int;");
			put("∴", "&there4;");
			put("∼", "&sim;");
			put("≅", "&cong;");
			put("≈", "&asymp;");
			put("≠", "&ne;");
			put("≡", "&equiv;");
			put("≤", "&le;");
			put("≥", "&ge;");
			put("⊂", "&sub;");
			put("⊃", "&sup;");
			put("⊄", "&nsub;");
			put("⊆", "&sube;");
			put("⊇", "&supe;");
			put("⊕", "&oplus;");
			put("⊗", "&otimes;");
			put("⊥", "&perp;");
			put("⋅", "&sdot;");
			put("⌈", "&lceil;");
			put("⌉", "&rceil;");
			put("⌊", "&lfloor;");
			put("⌋", "&rfloor;");
			put("〈", "&lang;");
			put("〉", "&rang;");
			put("◊", "&loz;");
			put("♠", "&spades;");
			put("♣", "&clubs;");
			put("♥", "&hearts;");
			put("♦", "&diams;");
			put("\n", "<BR />");
		}
	};
}
