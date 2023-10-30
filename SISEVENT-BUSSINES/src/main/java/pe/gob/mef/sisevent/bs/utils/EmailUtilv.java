/*
 * JCatalog Project
 */
package pe.gob.mef.sisevent.bs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;

import com.sun.mail.smtp.SMTPTransport;

/**
 * Utility class to send email.
 * 
 * @author <a href="mailto:derek_shen@hotmail.com">Derek Y. Shen</a>
 */
public class EmailUtilv implements TransportListener, ConnectionListener {

	private static Log logger = LogFactory.getLog(EmailUtilv.class.getName());

	private void sendmailtoM(boolean detalle, int validar, String usuario, String password, Properties props,
			String from, String sub, String recipiente, String msg) {
		SMTPTransport t = null;
		try {
			Session session = javax.mail.Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session);
			message.addHeader("Content-type", "text/html");
			message.setSubject(sub);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, recipiente);
			message.setText(msg, "ISO-8859-1", "html");
			message.setSentDate(new Date());
			t = (SMTPTransport) session.getTransport("smtp");

			if (detalle) {
				t.addConnectionListener(this);
				t.addTransportListener(this);
			}

			if (validar == 0) {
				t.connect(usuario, password);
			} else {
				t.connect();
			}
			t.sendMessage(message, message.getAllRecipients());
		} catch (MessagingException mex) {
			if (detalle) {
				mex.printStackTrace();
				System.out.println();
				Exception ex = mex;
				do {
					if (ex instanceof SendFailedException) {
						SendFailedException sfex = (SendFailedException) ex;
						Address[] invalid = sfex.getInvalidAddresses();
						if (invalid != null) {
							System.out.println(" ** Invalid Addresses");
							if (invalid != null) {
								for (int i = 0; i < invalid.length; i++)
									System.out.println(" " + invalid[i]);
							}
						}
						Address[] validUnsent = sfex.getValidUnsentAddresses();
						if (validUnsent != null) {
							System.out.println(" ** ValidUnsent Addresses");
							if (validUnsent != null) {
								for (int i = 0; i < validUnsent.length; i++)
									System.out.println(" " + validUnsent[i]);
							}
						}
						Address[] validSent = sfex.getValidSentAddresses();
						if (validSent != null) {
							System.out.println(" ** ValidSent Addresses");
							if (validSent != null) {
								for (int i = 0; i < validSent.length; i++)
									System.out.println(" " + validSent[i]);
							}
						}
					}
					System.out.println();
					if (ex instanceof MessagingException)
						ex = ((MessagingException) ex).getNextException();
					else
						ex = null;
				} while (ex != null);
			}
		} finally {
			try {
				if (t != null)
					t.close();
			} catch (MessagingException mex) { /* ignore */
			}
		}
	}

	// YO
	public static boolean validateEmail(String email) {
		try {
			new InternetAddress(email).validate();

		} catch (AddressException ex) {
			System.out.println("Error : " + ex.getMessage());
			return false;
		}
		return true;
	}

	public void addToZipFile(String fileName, String ruta, ZipOutputStream zos)
			throws FileNotFoundException, IOException {

		System.out.println("Writing '" + fileName + "' to zip file");

		File file = new File(ruta);
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}

	@Override
	public void closed(ConnectionEvent arg0) {
		System.out.println("Connection closed");
	}

	@Override
	public void disconnected(ConnectionEvent arg0) {
		System.out.println("Connection disconnected");
	}

	@Override
	public void opened(ConnectionEvent arg0) {
		System.out.println("Connection opened");
	}

	@Override
	public void messageDelivered(TransportEvent e) {
		System.out.println("Message delivered for:");
		if (e != null) {
			Address[] a = e.getValidSentAddresses();
			if (a != null && a.length > 0) {
				for (int i = 0; i < a.length; i++) {
					System.out.println(a[i].toString());
				}
			}
			System.out.println("");
		}
	}

	@Override
	public void messageNotDelivered(TransportEvent e) {
		System.out.println("Message not delivered for:");
		if (e != null) {
			Address[] a = e.getValidUnsentAddresses();
			if (a != null && a.length > 0) {
				for (int i = 0; i < a.length; i++) {
					System.out.println(a[i].toString());
				}
			}
			System.out.println("");
		}
	}

	@Override
	public void messagePartiallyDelivered(TransportEvent e) {
		System.out.println("These addresses are invalid:");
		if (e != null) {
			Address[] a = e.getInvalidAddresses();
			if (a != null && a.length > 0) {
				for (int i = 0; i < a.length; i++) {
					System.out.println(a[i].toString());
				}
			}
			System.out.println("");
		}
	}

	// public String miniEncriptado(String mensaje){
	// char array[]=mensaje.toCharArray();
	// for(int i=0;i<array.length;i++){
	// array[i]=(char)(array[i]+(char)5);
	// }
	// String encriptado =String.valueOf(array);
	// return encriptado;
	// }
	//
	// public String miniDesencriptado(String encriptado){
	// char arrayD[]=encriptado.toCharArray();
	// for(int i=0;i<arrayD.length;i++){
	// arrayD[i]=(char)(arrayD[i]-(char)5);
	// }
	// String desencriptado =String.valueOf(arrayD);
	// return desencriptado;
	// }

	private String getApellidoDeCorreo(String correo) {
		String apellido = null;
		int arrova = correo.indexOf('@');
		if (arrova > 0) {
			apellido = correo.substring(1, arrova);
		}
		return apellido;
	}

	public void sendEmailCpyM(List<String> recipients, List<String> recipientsCC, List<String> recipientsCCO,
			String sub, String msg) {

		if (recipients == null) {
			String errMsg = "Could not send email: smtp host address is null";
			logger.error(errMsg);
			return;
		}

		Properties propertieAlarma = PropertiesMg.getAlarmaProperties();
		int validar = 0;
		try {
			validar = Integer.parseInt(propertieAlarma.getProperty("VALIDAR", "0"));
		} catch (Exception e) {
		}

		boolean detalle = false;
		try {
			detalle = Boolean.parseBoolean(propertieAlarma.getProperty("DETALLE", "false"));
		} catch (Exception e) {
		}

		String from = null;
		if (propertieAlarma != null) {
			Properties props = new Properties();
			from = propertieAlarma.getProperty("userSMTP");
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.smtp.host", propertieAlarma.getProperty("servidorSMTP"));

			props.setProperty("mail.smtp.port", propertieAlarma.getProperty("puertoSMTP"));

			if (validar == 0 || validar == 1) {
				props.setProperty("mail.smtp.starttls.enable", "true");
				if (validar == 0) {
					props.setProperty("mail.smtp.user", from);
					props.setProperty("mail.smtp.auth", "true");
				}
			}

			String password = propertieAlarma.getProperty("passSMTP");
			String usuario = from;

			SMTPTransport t = null;
			try {
				Session session = javax.mail.Session.getDefaultInstance(props);

				MimeMessage message = new MimeMessage(session);
				message.addHeader("Content-type", "text/html");
				message.setSubject(sub);
				message.setFrom(new InternetAddress(propertieAlarma.getProperty("userSMTP")));

				for (Iterator<String> it = recipients.iterator(); it.hasNext();) {
					String email = (String) it.next();
					message.addRecipients(Message.RecipientType.TO, email);
				}

				if (recipientsCC != null && recipientsCC.size() > 0) {
					for (Iterator<String> it = recipientsCC.iterator(); it.hasNext();) {
						String email = (String) it.next();
						message.addRecipients(Message.RecipientType.CC, email);
					}
				}

				if (recipientsCCO != null && recipientsCCO.size() > 0) {
					for (Iterator<String> it = recipientsCCO.iterator(); it.hasNext();) {
						String email = (String) it.next();
						message.addRecipients(Message.RecipientType.BCC, email);
					}
				}

				message.setText(msg, "ISO-8859-1", "html");
				message.setSentDate(new Date());

				t = (SMTPTransport) session.getTransport("smtp");

				if (validar == 0) {
					t.connect(usuario, password);
				} else {
					t.connect();
				}
				t.sendMessage(message, message.getAllRecipients());
			} catch (MessagingException mex) {
				if (detalle) {
					mex.printStackTrace();
					System.out.println();
					Exception ex = mex;
					do {
						if (ex instanceof SendFailedException) {
							SendFailedException sfex = (SendFailedException) ex;
							Address[] invalid = sfex.getInvalidAddresses();
							if (invalid != null) {
								System.out.println(" ** Invalid Addresses");
								if (invalid != null) {
									for (int i = 0; i < invalid.length; i++)
										System.out.println(" " + invalid[i]);
								}
							}
							Address[] validUnsent = sfex.getValidUnsentAddresses();
							if (validUnsent != null) {
								System.out.println(" ** ValidUnsent Addresses");
								if (validUnsent != null) {
									for (int i = 0; i < validUnsent.length; i++)
										System.out.println(" " + validUnsent[i]);
								}
							}
							Address[] validSent = sfex.getValidSentAddresses();
							if (validSent != null) {
								System.out.println(" ** ValidSent Addresses");
								if (validSent != null) {
									for (int i = 0; i < validSent.length; i++)
										System.out.println(" " + validSent[i]);
								}
							}
						}
						// System.out.println();
						if (ex instanceof MessagingException)
							ex = ((MessagingException) ex).getNextException();
						else
							ex = null;
					} while (ex != null);
				}
			} finally {
				try {
					if (t != null)
						t.close();
				} catch (MessagingException mex) { /* ignore */
				}
			}
		}

	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// RECTIFICADOS
	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void sendEmail(List<String> recipients, List<String> recipientsCC, List<String> recipientsCCO, String sub,
			String msg) {

		if (recipients == null) {
			String errMsg = "Could not send email: smtp host address is null";
			logger.error(errMsg);
			return;
		}

		Properties propertieAlarma = PropertiesMg.getAlarmaProperties();
		int validar = 0;
		try {
			validar = Integer.parseInt(propertieAlarma.getProperty("VALIDAR", "0"));
		} catch (Exception e) {
		}

		boolean debug = false;
		try {
			debug = Boolean.parseBoolean(propertieAlarma.getProperty("DEBUG", "false"));
		} catch (Exception e) {
		}

		String fromEmail = propertieAlarma.getProperty("userSMTP");
		Integer serverPort = Integer.parseInt(propertieAlarma.getProperty("puertoSMTP"));
		String serverName = propertieAlarma.getProperty("servidorSMTP");
		String userName = propertieAlarma.getProperty("userSMTP");
		String password = propertieAlarma.getProperty("passSMTP");

		SendEmailUtil sendEmailUtil = new SendEmailUtil(serverName, serverPort, fromEmail, userName, password);
		sendEmailUtil.setDebug(debug);

		if (validar > 1)
			sendEmailUtil.setUseSsl(true);
		else if (validar > 0)
			sendEmailUtil.setUseTls(true);

		for (Iterator<String> it = recipients.iterator(); it.hasNext();) {
			String email = (String) it.next();
			sendEmailUtil.sendEmail(email, null, null, sub, msg, null);
		}

		if (recipientsCC != null && recipientsCC.size() > 0) {
			for (Iterator<String> it = recipientsCC.iterator(); it.hasNext();) {
				String email = (String) it.next();
				sendEmailUtil.sendEmail(email, null, null, sub, msg, null);
			}
		}

		if (recipientsCCO != null && recipientsCCO.size() > 0) {
			for (Iterator<String> it = recipientsCCO.iterator(); it.hasNext();) {
				String email = (String) it.next();
				sendEmailUtil.sendEmail(email, null, null, sub, msg, null);
			}
		}
	}

	@Async
	public void enviarAlarma(String correo, String nombre, Long iddoc, Integer tipoexpediente, String numeroSid,
			Integer numeroAnio) {
		if (correo == null)
			return;
		if (correo.trim().length() < 3)
			return;
		try {
			String dominio = PropertiesMg.getSistemaProperties().getProperty(PropertiesMg.L_DOMINIO,
					PropertiesMg.D_DOMINIO);
			StringBuffer sb1 = new StringBuffer();
			sb1.append(
					"<style ><!-- table { border-bottom:1px solid #000000; border-right:1px solid #000000;border-top:1px solid #000000;border-left:1px solid #000000;}"
							+ "body{font-size:13px;font-weight:inherit;font-family:'Verdana,sans-serif';}"
							+ "table td {border-bottom:1px solid #000000;border-right:1px solid #000000;border-top:1px solid #000000;border-left:1px solid #000000;}#tablitaalan td{ font-family:'Verdana,sans-serif'; font-size:11px;}--></style>"
							+ "<p><b> SR(A):<br> ");
			sb1.append(nombre);
			sb1.append("</b>   <span ></span>");
			sb1.append(
					"</p> <p style='font-family:Frutiger 45 Light;font-size:14px'> Nos dirigimos a usted, para informarle que ha recibido un nuevo documento del Sistema de Tr&aacute;mite Documentario: ");

			if (tipoexpediente.intValue() == 0) {
				sb1.append("<a href=\"").append(dominio).append("/paginas/verexpediente.htm?iddocparam=").append(iddoc)
						.append("\" >");
			} else if (tipoexpediente.intValue() == 1) {
				sb1.append("<a href=\"").append(dominio).append("/paginas/verexpediente.htm?iddocparam=").append(iddoc)
						.append("\" >");
			} else { // if(this.tipoexpediente.intValue()==0){
				sb1.append("<a href=\"").append(dominio).append("/paginas/verexpediente.htm?iddocparam=").append(iddoc)
						.append("\" >");
			}
			sb1.append(numeroSid).append("-").append(numeroAnio);
			sb1.append("</a>");
			sb1.append("</p>");
			sb1.append("<p>Si requiere de alguna informaci&oacute;n adicional, enviar su solicitud a:");
			sb1.append("<a href='mailto:")
					.append(PropertiesMg.getAlarmaProperties().getProperty(
							PropertiesMg.L_ALARMA_REPRESENTANTE_CORREO,
							PropertiesMg.D_ALARMA_REPRESENTANTE_CORREO))
					.append("'>")
					.append(PropertiesMg.getAlarmaProperties().getProperty(
							PropertiesMg.L_ALARMA_REPRESENTANTE_CORREO,
							PropertiesMg.D_ALARMA_REPRESENTANTE_CORREO))
					.append("</a> o llamar al anexo ")
					.append(PropertiesMg.getAlarmaProperties().getProperty(
							PropertiesMg.L_ALARMA_REPRESENTANTE_ANEXO,
							PropertiesMg.D_ALARMA_REPRESENTANTE_ANEXO))
					.append("</p>");
			sb1.append("<p><br>Atentamente,<br>")
					.append(PropertiesMg.getAlarmaProperties().getProperty(
							PropertiesMg.L_ALARMA_REPRESENTANTE_PERSONA,
							PropertiesMg.D_ALARMA_REPRESENTANTE_PERSONA))
					.append("<br>  Anexo ")
					.append(PropertiesMg.getAlarmaProperties().getProperty(
							PropertiesMg.L_ALARMA_REPRESENTANTE_ANEXO,
							PropertiesMg.D_ALARMA_REPRESENTANTE_ANEXO))
					.append("<br> ")
					.append(PropertiesMg.getAlarmaProperties().getProperty(
							PropertiesMg.L_ALARMA_REPRESENTANTE_AREA,
							PropertiesMg.D_ALARMA_REPRESENTANTE_AREA))
					.append("</p>");

			// sendEmail(correo, "S.T.D. Nuevo documento: " + numeroSid + "-" + numeroAnio, sb1.toString());

			String sub = "S.T.D. Nuevo documento: " + numeroSid + "-" + numeroAnio;

			Properties propertieAlarma = PropertiesMg.getAlarmaProperties();
			int validar = 0;
			try {
				validar = Integer.parseInt(propertieAlarma.getProperty("VALIDAR", "0"));
			} catch (Exception e) {
			}

			boolean debug = false;
			try {
				debug = Boolean.parseBoolean(propertieAlarma.getProperty("DEBUG", "false"));
			} catch (Exception e) {
			}

			Properties props = new Properties();
			String fromEmail = propertieAlarma.getProperty("userSMTP");
			Integer serverPort = Integer.parseInt(propertieAlarma.getProperty("puertoSMTP"));
			String serverName = propertieAlarma.getProperty("servidorSMTP");
			String userName = propertieAlarma.getProperty("userSMTP");
			String password = propertieAlarma.getProperty("passSMTP");

			SendEmailUtil sendEmailUtil = new SendEmailUtil(serverName, serverPort, fromEmail, userName, password);
			sendEmailUtil.setDebug(debug);

			if (validar > 1)
				sendEmailUtil.setUseSsl(true);
			else if (validar > 0)
				sendEmailUtil.setUseTls(true);

			sendEmailUtil.sendEmail(correo, null, null, sub, sb1.toString(), null);

		} catch (Exception e) {
			System.out.println("enviarAlarma: " + e.getMessage());
			e.printStackTrace();
		}
	}

//	public void sendMailUsuarioNotificacion(TdAtencionesBk tdAtencionesBk, List<IDsValorDto> snaexos) {
//		String correo = tdAtencionesBk.getCorreo();
//		String copiaa = PropertiesMg.getSistemaProperties().getProperty("RESPONSABLE_NOTIFICACIONES");
//		String mensaje = getModeloNotificacion(1, tdAtencionesBk);
//		String fileclave = "FINELECTR-" + tdAtencionesBk.getNumeroSacc() + ".zip";
//		String rutaFileclave = FuncionesStaticas.getFileNameRutaSistema(fileclave);
//		File f = new File(rutaFileclave);
//		if (!f.exists()) {
//			try {
//				// crea un buffer temporal para ir poniendo los archivos a comprimir
//				ZipOutputStream zous = new ZipOutputStream(new FileOutputStream(rutaFileclave));
//				for (IDsValorDto anexo : snaexos) {
////					File fileAux = FuncionesStaticas.getFileNameSistemaCompletoSearch(anexo.getId());
////					if (fileAux.exists()) {
////						addToZipFile(anexo.getValor(), fileAux.getAbsolutePath(), zous);
////					}
//				}
//				zous.close();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		f = new File(rutaFileclave);
//		if (f.exists()) {
//			List<String> correos = new ArrayList<String>();
//			if (correo.indexOf(';') > 0) {
//				StringTokenizer stk = new StringTokenizer(correo, ";");
//				while (stk.hasMoreTokens()) {
//					String correoa = stk.nextToken();
//					correos.add(correoa);
//				}
//			} else {
//				correos.add(correo);
//			}
//			List<String> copiaas = new ArrayList<String>();
//			if (copiaa != null && copiaa.indexOf(';') > 0) {
//				StringTokenizer stk = new StringTokenizer(copiaa, ";");
//				while (stk.hasMoreTokens()) {
//					String copiaaa = stk.nextToken();
//					copiaas.add(copiaaa);
//				}
//			} else {
//				if (copiaa != null)
//					copiaas.add(copiaa);
//			}
//			String sub = "NOTIFICACI�N: Ministerio de Econom�a y Finanzas HR " + tdAtencionesBk.getNumeroSacc();
//			sendEmail(correos, copiaas, null, sub, mensaje);
//
//			System.out.println("SE ENVI� NOTIFICACION A " + correos + " COPIAS " + copiaas);
//		}
//	}
//
//	public String getModeloNotificacion(int tipo, TdAtencionesBk tdAtencionesBk) {
//
//		String etiqueta_Asunto = "&ltAsunto&gt";
//		String etiqueta_DicreccionFirmaDigital = "&ltdirección web firma digital&gt";
//		String etiqueta_NumeroOficio = "&ltNúmero Oficio&gt";
//		String etiqueta_Clave = "&ltclave&gt";
//
//		String dato_Asunto = "";//tdExpedienteBk.getIdproctxt(); // tdExpedienteBk.getAsunto();
//		String dato_DicreccionFirmaDigital = "";//ProcesarNuevaVersion.getDireccionWeb("exp", tdExpedienteBk.getIddoc());
//		String dato_NumeroOficio = "";//tdExpedienteBk.getIdclaseFintxt() + " " + tdExpedienteBk.getNumeroDocFin();
//		String dato_Clave = "";//ProcesarNuevaVersion.miniEncriptadoB(tdExpedienteBk.getIddoc().toString());
//
//		Map<String, String> datos = new HashMap<String, String>();
//
//		datos.put(etiqueta_Asunto, dato_Asunto);
//		datos.put(etiqueta_DicreccionFirmaDigital, dato_DicreccionFirmaDigital);
//		datos.put(etiqueta_NumeroOficio, dato_NumeroOficio);
//		datos.put(etiqueta_Clave, dato_Clave);
//
//		StringBuffer sb1 = new StringBuffer();
//
//		switch (tipo) {
//		case 1: {
//			sb1.append("<table>");
//			// + "<tr><td colspan=\"2\"style=\"height: 40px; text-align: left;\" >"
//			// + "<h3 style=\"background-color: #FFF;font-family:Verdana;font-size:13px;\">1. <U>Para informaci�n
//			// entregada por correo</U></td></tr>");
//			// sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >" +
//			// "<p><b><i>&ltAsunto&gt</i></b></p>" +
//			// "</td></tr>");
//			// sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >" +
//			// "<p><i><b>&ltTexto correo&gt:</b> Consignar el siguiente texto:</i></p>" + "</td></tr>");
//			sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//					+ "<p>Estimado usuario:</p>" + "</td></tr>");
//			sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//					+ "<p>Por el presente se remite la dirección web <i>&ltdirección web firma digital&gt</i> &nbsp<SUP>1/</SUP> donde encontrará el <i>&ltNúmero Oficio&gt</i> y la información solicitada para su conocimiento y fines pertinentes.<br /><br /></p>"
//					+ "</td></tr></table>");
//		}
//			break;
//		case 2: {
//			sb1.append("<table>");
//			// + "<tr><td colspan=\"2\"style=\"height: 40px; text-align: left;\" >"
//			// +
//			// "<h3 style=\"background-color: #FFF;font-family:Verdana;font-size:13px;\">2. <U>Para comunicar la
//			// liquidaci�n por correo (Entrega de informaci�n f�sica)</U></td></tr>");
//			// sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >" +
//			// "<p><b><i>&ltAsunto&gt</i></b></p>" +
//			// "</td></tr>");
//			// sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >" +
//			// "<p><i><b>&ltTexto correo&gt:</b> Consignar el siguiente texto:</i></p>" + "</td></tr>");
//			sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//					+ "<p>Estimado usuario:</p>" + "</td></tr>");
//			sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//					+ "<p>Por el presente se remite la dirección web <i>&ltdirección web firma digital&gt</i> &nbsp<SUP>1/</SUP> donde encontrará el <i>&ltNúmero Oficio&gt</i>, el mismo que detalla el costo de liquidación a fin de poder entregarle la información solicitada.<br /><br /></p>"
//					+ "</td></tr></table>");
//		}
//			break;
//		case 3: {
//			// sb1.append("<table><tr><td colspan=\"2\"style=\"height: 40px; text-align: left;\" >" +
//			// "<h3 style=\"background-color: #FFF;font-family:Verdana;font-size:13px;\">3. <U>Para el caso de encauces
//			// (notificaci�n por correo)</U></td></tr>");
//			// sb1.append("<table><tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >" +
//			// "<p><b><i>&ltAsunto&gt</i></b></p>"
//			// + "</td></tr>");
//			// sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//			// + "<p><i><b>&ltTexto correo&gt:</b> Consignar el siguiente texto:</i></p>" + "</td></tr>");
//			sb1.append("<table>");
//			sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//					+ "<p>Estimado usuario:</p>" + "</td></tr>");
//			sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//					+ "<p>Por el presente se remite la direcci�n web <i>&ltdirecci�n web firma digital&gt</i> &nbsp<SUP>1/</SUP> donde encontrar� el <i>&ltN�mero Oficio&gt</i>, en el que se comunica el traslado de su solicitud a la entidad competente para su conocimiento y fines pertinentes.<br /><br /></p>"
//					+ "</td></tr></table>");
//		}
//			break;
//		case 4: {
//			// sb1.append("<table><tr><td colspan=\"2\"style=\"height: 40px; text-align: left;\" >" +
//			// "<h3 style=\"background-color: #FFF;font-family:Verdana;font-size:13px;\">4. <U>Para el caso de
//			// subsanaci�n (notificaci�n por correo)</U></td></tr>");
//			// sb1.append("<table><tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >" +
//			// "<p><b><i>&ltAsunto&gt</i></b></p>"
//			// + "</td></tr>");
//			// sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//			// + "<p><i><b>&ltTexto correo&gt:</b> Consignar el siguiente texto:</i></p>" + "</td></tr>");
//			sb1.append("<table>");
//			sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//					+ "<p>Estimado usuario:</p>" + "</td></tr>");
//			sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//					+ "<p>Por el presente se remite la direcci�n web <i>&ltdirecci�n web firma digital&gt</i> &nbsp<SUP>1/</SUP> donde encontrar� el <i>&ltN�mero Oficio&gt</i>, mediante el cual se solicita subsanar su solicitud de acceso a la informaci�n.<br /><br /></p>"
//					+ "</td></tr></table>");
//		}
//			break;
//		case 5: {
//			// sb1.append("<table><tr><td colspan=\"2\"style=\"height: 40px; text-align: left;\" >" +
//			// "<h3 style=\"background-color: #FFF;font-family:Verdana;font-size:13px;\">5. <U>Para el caso de pr�rroga
//			// (notificaci�n por correo) </U></td></tr>");
//			// sb1.append("<table><tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >" +
//			// "<p><b><i>&ltAsunto&gt</i></b></p>"
//			// + "</td></tr>");
//			// sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//			// + "<p><i><b>&ltTexto correo&gt:</b> Consignar el siguiente texto:</i></p>" + "</td></tr>");
//			sb1.append("<table>");
//			sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//					+ "<p>Estimado usuario:</p>" + "</td></tr>");
//			sb1.append("<tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//					+ "<p>Por el presente se remite la direcci�n web <i>&ltdirecci�n web firma digital&gt</i> &nbsp<SUP>1/</SUP> donde encontrar� el <i>&ltN�mero Oficio&gt</i>, mediante el cual se pone a conocimiento, el uso del periodo adicional de pr�rroga.<br /><br /></p>"
//					+ "</td></tr></table>");
//		}
//			break;
//		}
//		// sb1.append("<table><tr><td colspan=\"2\"style=\"height: 40px; text-align: left;\" >" +
//		// "<h3 style=\"background-color: #FFF;font-family:Verdana;font-size:13px;\"><U>Para todos los
//		// casos</U></td></tr>");
//		// sb1.append("<table><tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >" +
//		// "<p><i><b>&ltTexto final correo&gt:</b> Consignar el siguiente texto al final del correo:</i></p>" +
//		// "</td></tr>");
//
//		sb1.append("<table><tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//				+ "<p>Agradeceremos confirmar la recepci�n del presente.</p>" + "</td></tr></table>");
//		sb1.append("<table><tr><td colspan=\"2\"style=\"height: 30px;font-family:Verdana;font-size:12px;\" >"
//				+ "<p><SUP>1/</SUP><i>El Oficio que se encontrar� en la direcci�n web antes indicada, es una copia aut�ntica imprimible de un documento electr�nico archivado en el Ministerio de Econom�a y Finanzas, aplicando lo dispuesto por el Art. 25 del  D.S N� 070-2013-PCM y la Tercera Disposici�n Complementaria Final del D. S. N� 026-2016-2016-PCM. Su autenticidad e integridad pueden ser contrastadas a trav�s de la siguiente direcci�n web: &ltdirecci�n web firma digital&gt e ingresando la siguiente clave: &ltclave&gt</i></p>"
//				+ "</td></tr></table>");
//
//		sb1.append("<p style='font-weight: normal;font-family:Calibri;font-size:14px'>Atentamente,<br />");
//		sb1.append("<br /><b>Acceso a la Informaci�n P�blica del MEF</b>");
//		sb1.append("<br /><b>Oficina de Gesti�n Documental y Atenci�n al Usuario</b>");
//		sb1.append("<br /><b>Oficina General de Servicios al Usuario</b>");
//		sb1.append("<br />Ministerio de Econom�a y Finanzas");
//		sb1.append("<br />Jr. Jun�n N� 319- Lima");
//		sb1.append("<br />Tel�fono: 311-5930   Anexos 4282, 4285 y 4289");
//		sb1.append("<br /><a href=\"http://www.mef.gob.pe\">www.mef.gob.pe</a>");
//		sb1.append("<br /></p>");
//
//		String retorno = sb1.toString();
//		for (Map.Entry<String, String> entry : datos.entrySet()) {
//			retorno = retorno.replaceAll(entry.getKey(), entry.getValue());
//		}
//
//		return retorno;
//	}



	public void sendEmailF(List<String> recipients, List<String> recipientsCC, List<String> recipientsCCO, String sub,
			String msg, File archivo) {

		if (recipients == null) {
			String errMsg = "Could not send email: smtp host address is null";
			logger.error(errMsg);
			return;
		}

		Properties propertieAlarma = PropertiesMg.getAlarmaProperties();
		int validar = 0;
		try {
			validar = Integer.parseInt(propertieAlarma.getProperty("VALIDAR", "0"));
		} catch (Exception e) {
		}

		boolean debug = false;
		try {
			debug = Boolean.parseBoolean(propertieAlarma.getProperty("DEBUG", "false"));
		} catch (Exception e) {
		}

		String fromEmail = propertieAlarma.getProperty("userSMTP");
		Integer serverPort = Integer.parseInt(propertieAlarma.getProperty("puertoSMTP"));
		String serverName = propertieAlarma.getProperty("servidorSMTP");
		String userName = propertieAlarma.getProperty("userSMTP");
		String password = propertieAlarma.getProperty("passSMTP");

		SendEmailUtil sendEmailUtil = new SendEmailUtil(serverName, serverPort, fromEmail, userName, password);
		sendEmailUtil.setDebug(debug);

		if (validar > 1)
			sendEmailUtil.setUseSsl(true);
		else if (validar > 0)
			sendEmailUtil.setUseTls(true);

		for (Iterator<String> it = recipients.iterator(); it.hasNext();) {
			String email = (String) it.next();
			sendEmailUtil.sendEmail(email, null, null, sub, msg, archivo.getAbsolutePath());
		}

		if (recipientsCC != null && recipientsCC.size() > 0) {
			for (Iterator<String> it = recipientsCC.iterator(); it.hasNext();) {
				String email = (String) it.next();
				sendEmailUtil.sendEmail(email, null, null, sub, msg, archivo.getAbsolutePath());
			}
		}

		if (recipientsCCO != null && recipientsCCO.size() > 0) {
			for (Iterator<String> it = recipientsCCO.iterator(); it.hasNext();) {
				String email = (String) it.next();
				sendEmailUtil.sendEmail(email, null, null, sub, msg, archivo.getAbsolutePath());
			}
		}
	}

	public void sendEmail(String receiverAddress, String sub, String msg) throws Exception {
		List<String> recipients = new ArrayList<String>();
		if (receiverAddress.indexOf(';') > 0) {
			StringTokenizer stk = new StringTokenizer(receiverAddress, ";");
			while (stk.hasMoreTokens()) {
				String recipiente = stk.nextToken();
				recipients.add(recipiente);
			}
		} else {
			recipients.add(receiverAddress);
		}
		sendEmail(recipients, null, null, sub, msg);
	}

}
