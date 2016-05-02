package DES;

import java.io.UnsupportedEncodingException;

// Data encryption standard ---->>>>
public class DES {
public static String encrypt(String M,String key) throws UnsupportedEncodingException{
	String C = new String();
	/*
	byte[] input=M.getBytes();
	String input2=new String();
	String input3=new String();
	for(byte b:input){
		input2+=Integer.toBinaryString(b);
		
	}
	
    if(input2.length()>64){
    	int length=input2.length();
    	for(int i=0;i<64;i++)
    		input3+=input2.charAt(i);
    	
    }else if(input2.length()<64){
    	for(int i=0;i<64;i++){
    		if(i>=input2.length()){
    			input3+="0";
    		}else{
    			input3+=input2.charAt(i);
    		}
    	}
    }else{
    	//equality 
    	input3=input2;
    }
    */
   //***************************************************************	
   //work is starting********************************************>>>
   String input3=M;
	
    input3=permutations.initial_permutaion(input3);
    //make it (IP) first initial permutation 
  
    pair c = new pair(input3.substring(0,32),input3.substring(32, 64));
    String[] keys=key_gen.generate(key);
   for(int i=1;i<=16;i++){
    	
    	c=round(c,keys[i]);
    }
    
    C=c.get_right()+c.get_left();
    C=permutations.final_premuterm(C);
   return C;
}
   public static pair round(pair c ,String key){
	   
	   String buff=c.get_left();
	   String left=c.get_right(); //خلصنا من الشمال 
	   String right=new String();
	   String right2=new String();
		
	   // نروح لليمين
	   right=our_function(c.get_right(), key);
	   for(int i=0;i<left.length();i++){
		   //Xoring 
		   right2+=(buff.charAt(i)==right.charAt(i))?'0':'1';
	   }
	   //النتيجه 
	   pair E = new pair(left, right2);
	   return E;
   }
   //our function
   public static String our_function(String right,String key){
	   // iam here now 
	   right=permutations.bit_selection(right);
	   String output=new String();
	   for(int i=0;i<right.length();i++){
		   //Xoring 
		   output+=(right.charAt(i)==key.charAt(i))?'0':'1';
	   }
	   String output2=new String();
	   int start=0,end=6;
	   for(int i=0;i<8;i++){
		    output2+=permutations.S(output.substring(start,end), i);
		   
		   start=end;
		   end+=6;
	   }
	   output2=permutations.P(output2);
	   return output2;
   }
  
 
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
 //System.out.println("the output is :"+DES.encrypt("0011100000111000001110000011100000111000001110000011100000111000", "0011101100111000100110000011011100010101001000001111011101011110"));
//System.out.println( our_function("11110000101010101111000010101010", "000110110000001011101111111111000111000001110010"));
	//00111000 00111000 00111000 00111000 00111000 00111000 00111000 00111000
	
		//System.out.println("the output is:"+DES.decrypt("0011000110010100111010011001110110100110101010110001100001011001", "0011101100111000100110000011011100010101001000001111011101011110"));

	//the lecture 
		String key="0110011101011010011010010110011101011010010110100110101101011010";
		String value="0110011101011010011010010110011101011110010110100110101101011010";
		              
		String result=encrypt(value, key);
		System.out.println(result);
		System.out.println("decrypt="+decrypt(result, key));
	
	}
	
	//***************************************************************
	//the decryption 
	public static String decrypt(String C,String key){
		String M=new String();
		 String input=C;
		   input=permutations.initial_permutaion(input);
		    //make it (IP) first initial permutation 
		    System.out.println(input);
		    pair c = new pair(input.substring(0,32),input.substring(32, 64));
		    String[] keys=key_gen.generate(key);
		    int index=16;
		    for(int i=1;i<=16;i++){
		   	c=round(c,keys[index--]);
		    }
		    
		    M=c.get_right()+c.get_left();
		    M=permutations.final_premuterm(M);
		return M;
	}
}
class pair{
	
	private String left;
	private String right;
	
	public pair(String left,String right){
		this.left=left;
		this.right=right;
		
	}
	public String get_left(){return left;} 
	public String get_right(){return right;}
	public void set_left(String left){this.left=left;}
	public void set_right(String right){this.right=right;}
}

