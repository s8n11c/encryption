package ceaser;

import java.awt.JobAttributes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.naming.LimitExceededException;
import javax.swing.JOptionPane;

public class Caesar {
	static 
	int limits = 127;
public static String encrypt(String M,int key){
	String C = new String();
	
	for(int i=0;i<M.length();i++){
		
		C+=(char) ((M.charAt(i)+key));
		
	}
	
	return  C;
}
public static String alpha_encrypt(String M,int key){
	String C = new  String();
	// Encrypt alphabitically within 26 character 
	String alpha="abcdefghijklmnopqrstuvwxyz";
	
	for(int i=0;i<M.length();i++){
	    int x = alpha.indexOf(M.charAt(i));
	       int z = x+key;
	       if(z-26==0) z=key-1;
	       else if(z>26) z-=26;
	       C+=alpha.charAt(z);
	}
	
	return C;
	
}

public static String alpha_decrypt(String C,int key){
	String M = new  String();
	String alpha="abcdefghijklmnopqrstuvwxyz";
	
	for(int i=0;i<C.length();i++){
       int x = alpha.indexOf(C.charAt(i));
       int z = x-key;
       if(z-26==0) z=26; 
       else  if(z<0) z+=26;
       M+=alpha.charAt(z);
       
      
	}
	
	return M;
	
}
public static  String decrypt(String C,int key){
	String M = new String();
for(int i=0;i<C.length();i++){
	
	M+=(char) ((C.charAt(i)-key));
		
	}
	return  M;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

  File file=new File("/home/s0n1c/Desktop/encrypt.txt");
  try {
	PrintWriter pw = new PrintWriter(file.getAbsoluteFile().toString());
String enc= JOptionPane.showInputDialog("enter the message");
int key= Integer.parseInt(JOptionPane.showInputDialog("enter the key"));

	enc=Caesar.alpha_encrypt(enc, key);
	
		pw.write("the encrypted message is:"+enc);
		
	pw.close();
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  
  
	}

}
