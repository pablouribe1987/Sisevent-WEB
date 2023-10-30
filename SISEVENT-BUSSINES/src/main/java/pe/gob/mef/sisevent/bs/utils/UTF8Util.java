/**
 * 
 */
package pe.gob.mef.sisevent.bs.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

import javax.naming.InvalidNameException;

/**
 * @author cafach
 *
 */
public class UTF8Util {

	/** &lt;hex> ::= [0x30-0x39] | [0x41-0x46] | [0x61-0x66] */
	  private static final byte[] HEX_VALUE =
	      { 
	          -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 00 -> 0F
	          -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 10 -> 1F
	          -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 20 -> 2F
	           0,  1,  2,  3,  4,  5,  6,  7,  8,  9, -1, -1, -1, -1, -1, -1, // 30 -> 3F ( 0, 1,2, 3, 4,5, 6, 7, 8, 9 )
	          -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 40 -> 4F ( A, B, C, D, E, F )
	          -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, // 50 -> 5F
	          -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1  // 60 -> 6F ( a, b, c, d, e, f )
	      };
	  
	/**
	 * 
	 */
	public UTF8Util() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String xmlstring = "Здравей' хора";
        String utf8string = UTF8Util.convertToUTF8(xmlstring);
        for (int i = 0; i < utf8string.length(); ++i) {
            System.out.printf("%x ", (int) utf8string.charAt(i));
        }
	}
	
	
	  /**
	   * Decodes values of attributes in the DN encoded in hex into a UTF-8 
	   * String.  RFC2253 allows a DN's attribute to be encoded in hex.
	   * The encoded value starts with a # then is followed by an even 
	   * number of hex characters.  
	   */
	 public static final String decodeHexString( String str ) throws InvalidNameException
	  {
	      if ( str == null || str.length() == 0 )
	      {
	          throw new InvalidNameException( "Expected string to start with a '#' character.  " +
	              "Invalid hex encoded string for empty or null string."  );
	      }
	      
	      char[] chars = str.toCharArray();
	      if ( chars[0] != '#' )
	      {
	          throw new InvalidNameException( "Expected string to start with a '#' character.  " +
	                  "Invalid hex encoded string: " + str  );
	      }
	      
	      // the bytes representing the encoded string of hex
	      // this should be ( length - 1 )/2 in size
	      byte[] decoded = new byte[ ( chars.length - 1 ) >> 1 ];

	      for ( int ii = 1, jj = 0 ; ii < chars.length; ii+=2, jj++ )
	      {
	          int ch = ( HEX_VALUE[chars[ii]] << 4 ) + 
	              HEX_VALUE[chars[ii+1]];
	          decoded[jj] = ( byte ) ch;
	      }
	      
	      return utf8ToString( decoded );
	  }
	  /**
	   * Return an UTF-8 encoded String
	   * 
	   * @param bytes
	   *            The byte array to be transformed to a String
	   * @return A String.
	   */
	  public static final String utf8ToString( byte[] bytes )
	  {
	      if ( bytes == null )
	      {
	          return "";
	      }

	      try
	      {
	          return new String( bytes, "UTF-8" );
	      }
	      catch ( UnsupportedEncodingException uee )
	      {
	          return "";
	      }
	  }
	  
	  public static int getUTFSize(String s) {

	      int len = (s == null) ? 0
	                            : s.length();
	      int l   = 0;

	      for (int i = 0; i < len; i++) {
	          int c = s.charAt(i);

	          if ((c >= 0x0001) && (c <= 0x007F)) {
	              l++;
	          } else if (c > 0x07FF) {
	              l += 3;
	          } else {
	              l += 2;
	          }
	      }

	      return l;
	  }

	// convert from UTF-8 -> internal Java String format
	    public static String convertFromUTF8(String s) {
	        String out = null;
	        try {
	            out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
	        } catch (java.io.UnsupportedEncodingException e) {
	            return null;
	        }
	        return out;
	    }
	 
	    // convert from internal Java String format -> UTF-8
	    public static String convertToUTF8(String s) {
	        String out = null;
	        try {
	            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
	        } catch (java.io.UnsupportedEncodingException e) {
	            return null;
	        }
	        return out;
	    }
	    
	    static public String byteToHex(byte b) {
	        // Returns hex String representation of byte b
	        char hexDigit[] = {
	           '0', '1', '2', '3', '4', '5', '6', '7',
	           '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	        };
	        char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
	        return new String(array);
	     }
	   
	     static public String charToHex(char c) {
	        // Returns hex String representation of char c
	        byte hi = (byte) (c >>> 8);
	        byte lo = (byte) (c & 0xff);
	        return byteToHex(hi) + byteToHex(lo);
	     }
	     
	     public static String stripGarbage(String s) {
	    	    String good = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+-?(),;:!%=\"'_|";
	    	    String result = "";
	    	    for (int i = 0; i < s.length(); i++) {
	    	      if (good.indexOf(s.charAt(i)) >= 0)
	    	        result += s.charAt(i);
	    	    }
	    	    return result;
	    	  }
	     
	     public static String stringToCanonico(String s) {
	    	    String good = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+-?(),;:!*¡¿_|";
	    	    String result = "";
	    	    for (int i = 0; i < s.length(); i++) {
	    	      if (good.indexOf(s.charAt(i)) >= 0){
	    	        result += s.charAt(i);
	    	      }else{
	    	    	  result += unicodeEscaped(s.charAt(i));
	    	      }
	    	    }
	    	    return result;
	    	  }
	     
	     public static String unicodeEscaped(char ch) {
	         if (ch < 0x10) {
	             return "\\u000" + Integer.toHexString(ch);
	         } else if (ch < 0x100) {
	             return "\\u00" + Integer.toHexString(ch);
	         } else if (ch < 0x1000) {
	             return "\\u0" + Integer.toHexString(ch);
	         }
	         return "\\u" + Integer.toHexString(ch);
	     }
	     	     
	     public static void imprimirConTodosLosEncodes(String s){
	    	 Map<String, Charset> map = Charset.availableCharsets();
	         for (String name : map.keySet()) {
	        	 try {
					byte[] b = s.getBytes(name);
					String r = new String(b);
					System.out.println(name+"-->"+r);
				} catch (UnsupportedEncodingException e) {
					e.getLocalizedMessage();
				}catch (Exception e) {
					e.getLocalizedMessage();
				}	            
	         }
	     }
	     
	     public static void imprimirConTodosLosEncodes( byte[] bytes )
		  {
	    	 Map<String, Charset> map = Charset.availableCharsets();
	         for (String name : map.keySet()) {
	        	 try {
					String r = new String(bytes,name);
					System.out.println(name+"-->"+r);
				} catch (UnsupportedEncodingException e) {
					e.getLocalizedMessage();
				}catch (Exception e) {
					e.getLocalizedMessage();
				}	            
	         }
		  }
	     
	     public static void imprimirConTodosYTodosLosEncodes(String s, String c)
		  {
	    	 String namee1=null;
	    	 String namee2=null;
	    	 String re = null;
	    	 Map<String, Charset> map = Charset.availableCharsets();
	         for (String name : map.keySet()) {
	        	 try {
	        		 byte[] b = s.getBytes(name);
					Map<String, Charset> map2 = Charset.availableCharsets();
			         for (String name2 : map2.keySet()) {
			        	 String r = new String(b,name2);
						System.out.println(name+":"+name2+"-->"+r);
						if(r.equals(c)){
							namee1= name;
							namee2= name2;
							re = r;
						}
			         }
				} catch (UnsupportedEncodingException e) {
					e.getLocalizedMessage();
				}catch (Exception e) {
					e.getLocalizedMessage();
				}	            
	         }
	         System.out.println("FINAL "+namee1+":"+namee2+"-->"+re);
		  }
}
