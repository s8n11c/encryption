package DES;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CopyOnWriteArrayList;

public class STR_DES {


	public static String decrypt(String M,String key) throws UnsupportedEncodingException{
			String C = new String();
			if((key.length()*8)!=64)
				return "the length is not good";
			// converting from ascii to binary with padding 
			
			String Container[]=new String[M.length()];
			int counter=0;
			while(counter<M.length()){
				Container[counter]=Integer.toBinaryString((byte) M.charAt(counter));
				if(Container[counter].length()<8){
					int i=0; while(i<(8-Container[counter].length())){
						Container[counter]='0'+Container[counter];
						i++;
					}
				}
				counter++;
			}  
			System.out.println("printing the plain binary form :");
			for(int i=0;i<Container.length;i++){
				System.out.print(Container[i]);
			}
						String key_container[]=new String[key.length()];
			counter=0;
			while(counter<key.length()){
				key_container[counter]=Integer.toBinaryString((byte) key.charAt(counter));
				if(key_container[counter].length()<8){
					int i=0; while(i<(8-key_container[counter].length())){
						key_container[counter]='0'+key_container[counter];
						i++;
					}
				}
				counter++;
			}
			String padding="00000000";
			String bin_key=new String();
			int k=0; while(k<key.length()){bin_key+=key_container[k++];}
			if(bin_key.length()!=64){
				return "the key length is not good";
			}
		
			// initiating Block ciphering process XD
			
			int iteration=0;
			System.out.println("the length"+M.length());
				if(M.length()%8==0)
				{
					int blocks=M.length()/8;
					while(iteration<blocks){
						String bridge=new String();
						int index=iteration*8; while(index<(8*(iteration+1))){bridge+=Container[index];index++;}
						C+=DES.decrypt(bridge, bin_key);
						iteration++;
					}
				}else{
					while(iteration<=(M.length()/8)){
						String bridge=new String();
						int index=(8*iteration); while(index<=(8*(iteration+1))){bridge+=Container[index];index++;}
						C+=DES.decrypt(bridge, bin_key);
						iteration++;
					}
					//padding
					String bridge=new String();
					iteration=0;
					while(iteration<(M.length()%8)){
						bridge+=padding;
						iteration++;
					}
					C+=DES.decrypt(bridge, bin_key);
				}
				System.out.println("this is for decryption");
				C=To_String(C);
		return C;
	}
	
	public static String encrypt(String M,String key) throws UnsupportedEncodingException{
			String C = new String();
			if((key.length()*8)!=64)
				return "the length is not good(must be 64-bit(8-word))";
			// converting from ascii to binary with padding 
			
			String Container[]=new String[M.length()];
			int counter=0;
			while(counter<M.length()){
				Container[counter]=Integer.toBinaryString((byte) M.charAt(counter));
				if(Container[counter].length()<8){
					int i=0; while(i<(8-Container[counter].length())){
						Container[counter]='0'+Container[counter];
						i++;
					}
				}
				counter++;
			}
			String key_container[]=new String[key.length()];
			counter=0;
			while(counter<key.length()){
				key_container[counter]=Integer.toBinaryString((byte) key.charAt(counter));
				if(key_container[counter].length()<8){
					int i=0; while(i<(8-key_container[counter].length())){
						key_container[counter]='0'+key_container[counter];
						i++;
					}
				}
				counter++;
			}
			String padding="00000000";
			String bin_key=new String();
			int k=0; while(k<key.length()){bin_key+=key_container[k++];}
			if(bin_key.length()!=64){
				return "the key length is not good";
			}
		
			// initiating Block ciphering process XD
			
			int iteration=0;
			System.out.println("the length"+M.length());
				if(M.length()%8==0)
				{
					int blocks=M.length()/8;
					while(iteration<blocks){
						String bridge=new String();
						int index=iteration*8; while(index<(8*(iteration+1))){bridge+=Container[index];index++;}
						C+=DES.encrypt(bridge, bin_key);
						iteration++;
					}
				}else{
					
					int blocks=M.length()/8;
					while(iteration<blocks){
						String bridge=new String();
						
						int index=iteration*8; while(index<(8*(iteration+1))){bridge+=Container[index];index++;}
						
						C+=DES.encrypt(bridge, bin_key);
						iteration++;
					}
					
					//padding
					
					String bridge=new String();
					int i=0;
					while(i<(8-(M.length()%8))){
						bridge+=padding;
						i++;
					}
					i=0;
					while(i<(M.length()%8)){
						bridge+=Container[M.length()-(M.length()%8)+i];
						iteration++;
						i++;
					}
					System.out.println(bridge);
					C+=DES.encrypt(bridge, bin_key); 
				}
				System.out.println("this is for encryption");
				C=To_String(C);
				
		return C;
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String result=STR_DES.encrypt("wwwwwwww","kkkkkkkk");
		System.out.println("the output is "+result+"the length is "+result.length());
		System.out.println("the real text is "+STR_DES.decrypt(result, "kkkkkkkk"));
		
		

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
}
