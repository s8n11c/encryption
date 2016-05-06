package AES;

import java.nio.charset.StandardCharsets;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.management.openmbean.OpenDataException;
import javax.swing.JOptionPane;

public class FINAL_AES {
	  static String IV = "AAAAAAAAAAAAAAAA";
	  static String plaintext = "test text 122\0\0\0"; /*Note null padding*/
	  static String encryptionKey = "0123456789abcdef";
	  public static void main(String [] args) {
	    try {
	      
	      System.out.println("==Java==");
	      System.out.println("plain:   " + plaintext);

	      String cipher = encrypt(plaintext, encryptionKey);
          
	      System.out.print("cipher:  ");
	      stringtoint(cipher);
	        System.out.print(cipher);
	      System.out.println("");

	      String decrypted = decrypt(cipher, encryptionKey);

	      System.out.println("decrypt: " + decrypted);

	    } catch (Exception e) {
	     JOptionPane.showMessageDialog(null, e.getMessage());
	    } 
	  }

	  public static String encrypt(String plainText, String encryptionKey) throws Exception {
	    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
	    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
	    cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
	    for(byte i:cipher.doFinal(plainText.getBytes("UTF-8"))){
	    	System.out.println("X = "+i);
	    }
	    return asciiBytesToString(cipher.doFinal(plainText.getBytes("UTF-8")));
	  }
	  public static String asciiBytesToString( byte[] bytes )
	  {
	      
	      String result = new String();
	      
	      for ( int i = 0; i < bytes.length; i++ )
	      {
	          result += bytes[i]+" ";System.out.println(bytes[i]);
	      }
	      
	      return result;
	  }
	  
	  public static byte[] stringtoint(String s){
		 Vector <Integer>V =new  Vector(); int b=0,end=0;
		 System.out.println("Starting"+s);
		   int counter=0; while(counter<s.length()){if(s.charAt(counter)==' '){
			   
			   V.add(Integer.parseInt(s.substring(b,counter)));
			   b=counter+1;
			   
		   } counter++;}
		   
		   byte[] res=new byte[V.size()];
		   for(int i=0;i<V.size();i++){
			char ch= (char) V.get(i).byteValue();
			   res[i]=(byte) (ch);
		
		   }
		   System.out.println("ending");
		return res;
	  }
	  public static String decrypt(String cipherText, String encryptionKey) throws Exception{
		 
		  byte[] c = stringtoint(cipherText);
		  System.out.println(c[1]);
	    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
	    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
	    cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
	    return new String(cipher.doFinal(c),"UTF-8");
	  }
	}