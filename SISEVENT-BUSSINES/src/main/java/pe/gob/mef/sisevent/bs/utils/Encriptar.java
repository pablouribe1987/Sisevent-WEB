package pe.gob.mef.sisevent.bs.utils;

import java.util.Date;



/**
 * Clase que implementa la desincriptación de la contraseña de usuario de base
 * de datos en el modulo de seguridad para JBOSS
 * 
 * @author Carlos Aguilar
 * @version 2.0, 10/01/2012
 * 
 * 
 * 
 *          /----------Nombre----------/----fecha----/-------------Motivo------
 *          --------/ Carlos Aguilar Chamochumbi 10/01/2012 Creación de la clase
 * 
 * 
 */
public class Encriptar {

	public String EncriptarData(String pLogin, String pClave) {
		// System.out.println("CALCULO==>"+278%255);

		int li_Cont = 0, li_Magia = 0, li_Long = 0, li_Char = 0, li_Ascii1 = 0, li_Ascii2 = 0, li_Signo = 0;
		String ls_Login = "", ls_Clave = "", ls_Cripto = "", ls_Result = "";

		if (pLogin.trim().length() > pClave.trim().length()) {
			ls_Login = pLogin;
			String temp = pClave + pClave + pClave + pClave;
			// System.out.println("temp==>"+temp);
			ls_Clave = temp.substring(0, ls_Login.length());
			// System.out.println("ls_Clave==>"+ls_Clave);
		} else {
			ls_Clave = pClave;
			String temp = pLogin + pLogin + pLogin + pLogin;
			ls_Login = temp.substring(0, ls_Clave.length());
		}

		li_Long = ls_Login.length();
		// System.out.println("li_Long==>"+li_Long);

		// depurador.info("=> Generamos número mágico");

		for (li_Cont = 0; li_Cont < li_Long; li_Cont++) {
			// System.out.println("ls_Login==>"+ls_Login);
			// System.out.println("ANTES li_Magia==>"+li_Magia);
			li_Magia = ((li_Magia + getAscii(ls_Login.substring(li_Cont,
					li_Cont + 1))) % 255) + 1;
			// System.out.println("li_Magia==>"+li_Magia);
		}
		li_Magia = ((li_Magia + total_segundos()) % 255) + 1;

		// depurador.info("=> Encriptamos por primera vez");
		for (li_Cont = 0; li_Cont < li_Long; li_Cont++) {
			li_Ascii1 = getAscii(ls_Login.substring(li_Cont, li_Cont + 1));
			li_Ascii2 = getAscii(ls_Clave.substring(li_Cont, li_Cont + 1));
			li_Char = li_Ascii2 + li_Ascii1 + li_Magia;
			ls_Cripto = ls_Cripto + completa_codigo("" + li_Char, 3);
			// System.out.println("li_Ascii1==>"+li_Ascii1);
			// System.out.println("li_Ascii2==>"+li_Ascii2);
			// System.out.println("li_Char==>"+li_Char);
			// System.out.println("ls_Cripto==>"+ls_Cripto);

		}
		ls_Cripto = ls_Cripto + completa_codigo("" + li_Magia, 3)
				+ completa_codigo("" + pClave.length(), 2);

		// depurador.info("Encriptamos por segunda vez");
		for (li_Cont = 1; li_Cont <= ls_Cripto.length(); li_Cont++) {
			if (li_Cont % 2 == 0) {
				li_Signo = 1;
			} else {
				li_Signo = -1;
			}
			li_Char = getAscii(ls_Cripto.substring(li_Cont - 1, li_Cont));
			ls_Result = ls_Result
					+ ascii_to_string(li_Char + (li_Long * li_Signo));
			// System.out.println("li_Char==>"+li_Char);
			// System.out.println("ls_Result==>"+ls_Result);
		}

		return ls_Result;
	}

	public String Desencriptar(String pLogin, String pCripto) {

		// / depurador.info("pLogin => "+pLogin);
		// depurador.info("pCripto => "+pCripto);
		int li_Cont = 0, li_Magia = 0, li_Long = 0, li_Ascii1 = 0, li_Ascii2 = 0, li_Char = 0, li_Signo = 0;
		String ls_Login = "", ls_Cripto = "";

		// depurador.info("Desencriptamos por primera vez");
		li_Long = (pCripto.length() - 5) / 3;
		for (li_Cont = 1; li_Cont <= pCripto.length(); li_Cont++) {
			if ((li_Cont % 2) == 0) {
				li_Signo = 1;
			} else {
				li_Signo = -1;
			}
			li_Char = getAscii(pCripto.substring(li_Cont - 1, li_Cont));
			ls_Cripto = ls_Cripto
					+ ascii_to_string(li_Char - (li_Long * li_Signo));
		}

		// depurador.info("Igualamos las dos cadenas");
		li_Long = Integer.parseInt(ls_Cripto.substring(ls_Cripto.length() - 2,
				ls_Cripto.length()));
		pLogin = pLogin + pLogin + pLogin + pLogin;
		pLogin = pLogin.substring(0, li_Long);

		// depurador.info("Obtenemos número mágico");
		li_Magia = Integer.parseInt(ls_Cripto.substring(ls_Cripto.length() - 5,
				ls_Cripto.length() - 2));

		// depurador.info("Desencriptamos por segunda vez");
		for (li_Cont = 1; li_Cont <= li_Long; li_Cont++) {
			li_Ascii1 = getAscii(pLogin.substring(li_Cont - 1, li_Cont));
			li_Ascii2 = Integer.parseInt(ls_Cripto.substring((li_Cont * 3) - 3,
					li_Cont * 3));
			ls_Login = ls_Login
					+ ascii_to_string(li_Ascii2 - li_Ascii1 - li_Magia);
		}

		return ls_Login;
	}

	public int getAscii(String valor) {
		// System.out.println("valor==>"+valor);
		int j = (int) valor.charAt(0);
		// System.out.println("getAscii==>"+j);
		return j;

		/*
		 * String test = "jpablo"; for ( int i = 0; i < test.length(); ++i ) {
		 * char c = test.charAt( i ); int j = (int) c; System.out.println(j); }
		 */
	}

	public int total_segundos() {
		Date now = new Date(System.currentTimeMillis());
		@SuppressWarnings("deprecation")
		int cant = (Integer.parseInt("" + now.getHours() * 3600))
				+ (Integer.parseInt("" + now.getMinutes() * 60))
				+ Integer.parseInt("" + now.getSeconds());
		return cant;
	}

	public String completa_codigo(String codigo, int tamanio) {
		String ncodigo = "";

		if (codigo.length() >= tamanio) {
			for (int i = 0; i < tamanio; i++) {
				ncodigo = ncodigo + codigo.charAt(i);
			}
		} else {
			for (int i = 0; i < tamanio - codigo.length(); i++) {
				ncodigo = ncodigo + "0";
			}
			ncodigo = ncodigo + codigo;
		}

		return ncodigo;
	}

	public String ascii_to_string(int i) {
		return new Character((char) i).toString();
	}

	public static void main(String[] args) {
		Encriptar enc = new Encriptar();
		String emcriptado = enc.EncriptarData("adminsid-d", "222");
		System.out.println(emcriptado);
	}
}
