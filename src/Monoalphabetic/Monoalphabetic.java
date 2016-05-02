package Monoalphabetic;

import java.util.Random;
import java.util.Scanner;

import javax.annotation.processing.Processor;
import javax.swing.JOptionPane;
/*
 * author : s0n1c 
 *  
 *  all right reserved 
 *  Under GPL (GNU General public license)
 */
/*

	*/
	// encryption pairing 
	/*
	 * left is the cipher 
	 * right is the key 
	 */
/*
	private String left;
	private String right;
	
	public pair(String left, String right) {
		// TODO Auto-generated constructor stub
		this.left=left;
		this.right=right;
	}

	
	public String get_left(){
		return this.left;
	}
	
	public String get_right(){
		return this.right;
	}
	
	
}
*/
public class Monoalphabetic {
public static String key_generator(){
	StringBuilder alpha= new StringBuilder("abcdefghijklmnopqrstuvwxyz");
	String key=new String();
	int i = 0;
	int j = alpha.length();
	Random Rand = new Random();
	while(i<alpha.length()){
		int rand=Rand.nextInt(j--);
		key+=alpha.charAt(rand);
		alpha.deleteCharAt(rand);
		
		
	}
	return key;
}
	public static pair encrypt (String message){
		String Key =key_generator();
		
		String alpha="abcdefghijklmnopqrstuvwxyz";
		if(Key.length()<26){
			System.out.println("read  the manuals to get the key");
		return  new pair("","");
		}
		String C = new String();
		for (int i = 0 ; i < message.length();i++)
			
			C+=Key.charAt(alpha.indexOf(message.charAt(i)));
			
		return new pair(C, Key);
	}
	public static String decrypt (String message,String Key){
		String alpha="abcdefghijklmnopqrstuvwxyz";
		if(Key.length()<26){
			System.out.println("read  the manuals to get the key");
			return "";
		}
		String C = new String();
		for (int i = 0 ; i < message.length();i++)
		
			C+=alpha.charAt(Key.indexOf(message.charAt(i)));
			return C;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner n = new Scanner(System.in);
		String test=n.nextLine();
		pair N = encrypt(test);
		JOptionPane.showMessageDialog(null, N.get_right());
		System.out.println(N.get_left());
		System.out.println(decrypt(N.get_left(),N.get_right()));
		
	}

}
