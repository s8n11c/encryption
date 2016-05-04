package DES;

import java.io.UnsupportedEncodingException;

import javax.crypto.EncryptedPrivateKeyInfo;
import javax.swing.JOptionPane;

public class STR_DES {

	public String encrypt(String M,String key) throws UnsupportedEncodingException{ String C = new String();
	        if(key.length()!=8)
	        	return "the key is not 8*N";
			if(M.length()%8==0){
				C=without_padding(M,key);
			}else{
				//C=with_padding(M);
				JOptionPane.showMessageDialog(null,"the plain length is not 8*N  . padding not implemented yet");
				System.out.println("with padding not impelmented yet");
			}
			
		return C;
	}
	public String decrypt(String M,String key) throws UnsupportedEncodingException{ String C = new String();
	   if(key.length()!=8)
       	return "the key is not 8*N";
	if(M.length()%8==0){
		C=dec_without_padding(M,key);
	}else{
		//C=with_padding(M);
		JOptionPane.showMessageDialog(null,"the plain length is not 8*N  . padding not implemented yet");
		System.out.println("decryption with padding not impelmented yet");
	}
	
return C;
}
	
	// routing 
	public String without_padding(String B,String key) throws UnsupportedEncodingException{
		//00000000000000000000000000
		String bin_key=new String();
		for(int i=0;i<key.length();i++){
			bin_key+=String.format("%8s", Integer.toBinaryString((byte)key.charAt(i) & 0xFF)).replace(' ','0');
		}
		
		//00000000000000000000000000
		int begin=0,end=7;
		String C = new String();
		for(int i=0;i<(B.length()/8);i++){
			
			String bridge=new String();
			   
		
			  for(int j=begin;j<=end;j++){
				  
				  bridge+= String.format("%8s", Integer.toBinaryString((byte)B.charAt(j) & 0xFF)).replace(' ', '0');
				  
			  }
			  
			  bridge=DES.encrypt(bridge, bin_key);
			  
			  for(int x=0;x<64;x=x+8){
				  C+=To_Char(bridge.substring(x,x+8));
			
			  }
			  
			  begin=begin+8;
			  end=end+8;
			
		}
		return C;
	}
	public String with_padding(String B){
		return B;
	}
public static String To_String(String M){
		
		String result = new String();
	int begin=0,end=7;
	for(int i=0;i<(M.length()/8);i++){
		result+=To_Char(M.substring(begin,end));
		begin=end;
		end=end+8;
		
	}
	return result;}
public static char To_Char(String Cipher){
	
	char result =0;
	int charcode=Integer.parseInt(Cipher,2);
	result=new Character((char)charcode);
return result;

}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		STR_DES t = new STR_DES();
		String plain="welcometwelcomet"; String key="kkkkkkkk";
		String result=t.encrypt(plain, key);
		System.out.println(result);
		System.out.println("the decryption part is :"+t.decrypt(result, key)); 
		
	}
	
	
	
	public String dec_without_padding(String B,String key) throws UnsupportedEncodingException{
		String bin_key=new String();
		for(int i=0;i<key.length();i++){
			String ch=(String.format("%8s", Integer.toBinaryString((byte)key.charAt(i) & 0xFF)).replace(' ','0'));
			
			bin_key+=ch;
		}
	
		//00000000000000000000000000
		int begin=0,end=7;
		String C = new String();
		for(int i=0;i<(B.length()/8);i++){
			
			String bridge=new String();
			   
		
			  for(int j=begin;j<=end;j++){
				  
				  bridge+= String.format("%8s", Integer.toBinaryString((byte)B.charAt(j) & 0xFF)).replace(' ', '0');
				  
			  }
			  
			  bridge=DES.decrypt(bridge, bin_key);
			  
			  for(int x=0;x<64;x=x+8){
				  C+=To_Char(bridge.substring(x,x+8));
				
			  }
			  
			  begin=begin+8;
			  end=end+8;
			
		}
		return C;}
}
