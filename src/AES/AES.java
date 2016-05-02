package AES;

//Advanced Encryption Standard 
public class AES {
public static String encrypt(String M,String key){
	//128-bit 
	byte key_mat[][]  = new byte[4][4];
	String Hex_form[][] = new String[4][4];
	
	for(int i=0, index=0;i<4;i++){
		for(int j=0;j<4;j++){
			if(index>key.length()-1){
				key_mat[i][j]=0;
			continue;
			}
			key_mat[i][j]=(byte) key.charAt(index++);				
		}
}
	for(int i=0;i<4;i++){
		for(int j=0;j<4;j++){
			if(key_mat[i][j]==0){
				Hex_form[i][j]="00";continue;
			}
			Hex_form[i][j]=Integer.toHexString(key_mat[i][j]);
		}
}
	String keys[][] = new String[4][44];
	keys=keyGenerator.key_gernerator(Hex_form);
for(int i=0;i<44;i++){
	for(int j=0;j<4;j++)
		System.out.print(keys[i][j]+"-");
System.out.println("");}
	//***************************************************************
	//*
	//*
	//* \ /         \ /
	//*	 m war begin m
	//*
	//*	
	//***************************************************************
	String C=new String();
	
		byte [][] state=new byte[4][4];
		for(int i=0, index=0;i<4;i++){
				for(int j=0;j<4;j++){
					if(index>M.length()-1){
						state[i][j]=0;
					continue;
					}
					state[i][j]=(byte) M.charAt(index++);				
				}
		}
		String [][] HexForm=new String[4][4];
		for(int i=0, index=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(state[i][j]==0){
					HexForm[i][j]="00";continue;
				}
				HexForm[i][j]=Integer.toHexString(state[i][j]);
			}
	}
		int Round=0; int start=0,end=3;
		for(int i=0, index=0;i<4;i++){
			for(int j=0;j<4;j++){
				System.out.print(HexForm[i][j]+"-");
			}
			System.out.println("");
	}
	HexForm=add_round_key(HexForm, keys, start, end);	
	
	
	while(Round<9){
		start+=4; end+=4;
		HexForm=Tools.round(HexForm,keys,start,end);
		
		Hex_form=add_round_key(HexForm, keys, start, end);
		Round++;
		
	}
	Hex_form=add_round_key(HexForm, keys, start, end);
	
	for(int i=0, index=0;i<4;i++){
		for(int j=0;j<4;j++){
			C+=HexForm[i][j];
		}
		
}
	return C;
	
	}

//******************************************************
//******************************************************

public static String decrypt(String C,String key){
	String M =new String();
	
	return M;
}
	public static void main(String[] args) {
		// TODO main method 
System.out.println(encrypt("99999898989898","1388787887"));
	}
	public static String [][] add_round_key(String HexForm[][],String key[][],int start,int end){
		System.out.println("start="+start+"end="+end);
		
		for(int i=start,index=0;i<=end;i++,index++){
			for(int j=0;j<4;j++){
				if(index>=4) index=0; 
				System.out.print("the hex form "+Integer.parseInt(HexForm[index][j],16)+"");
				System.out.print("the key form "+Integer.parseInt(key[i][j],16)+"");
				
				HexForm[index][j]=Integer.toHexString((Integer.parseInt(HexForm[index][j],16)^Integer.parseInt(key[i][j],16)));
				System.out.println("the result is ="+HexForm[index][j]);
				if(HexForm[index][j]=="0") HexForm[index][j]+="0";
				if(HexForm[index][j].length()<=1) HexForm[index][j]="0"+HexForm[index][j];
				
			}
		}
		return HexForm;
	}
}
	
class Tools{// this is a container for a specific tools for the AES encryption algorithm so be patient 
	// row shifting stage
	public static String[][] Shift_Rows(String [][] input){
		String [][] temper=new String[4][4];
		for(int i=0;i<4;i++){
			
			for(int j=0;j<4;j++){
					temper[i][j]=input[i][(j+i)%4];
				
			}
		}
		return temper;
	}
	
	//Mix columns 
	public static String[][] Mix_columns(String[][] input){
		 int[][] galois = {{0x02, 0x03, 0x01, 0x01},
			        {0x01, 0x02, 0x03, 0x01},
			        {0x01, 0x01, 0x02, 0x03},
			        {0x03, 0x01, 0x01, 0x02}};
		 // result = galois X input  <---- focus 
		 String result[][] = new String [4][4];
		 
		 for(int i=0;i<4;i++){
			 	for(int j=0;j<4;j++){
			 		result[i][j]=(Tools.multibication_mixing(Integer.parseInt(Integer.toBinaryString(galois[i][0]),2),Tools.hexToBinary(input[0][j]))^
			 				Tools.multibication_mixing(Integer.parseInt(Integer.toBinaryString(galois[i][1]),2),Tools.hexToBinary(input[1][j]))^
			 						Tools.multibication_mixing(Integer.parseInt(Integer.toBinaryString(galois[i][2]),2),Tools.hexToBinary(input[2][j]))^
			 								Tools.multibication_mixing(Integer.parseInt(Integer.toBinaryString(galois[i][3]),2),Tools.hexToBinary(input[3][j])))+"";
			 		
			         
			 		result[i][j]=Integer.toHexString(Integer.parseInt(result[i][j]));
			    
			 		//***********
			 		   
			 	
			 	}
		 }
				
		return result;
	}
	//**********************
	
	public static  String[][] round(String[][] HexForm,String[][] key,int start,int end){
		System.out.println("Round "+start/4);
		for(int i=0, index=0;i<4;i++){
			for(int j=0;j<4;j++){
				System.out.print(HexForm[i][j]+"-");
			}
			System.out.println("");
	}
	//************************************
	Tools.sub_bytes(HexForm);	

	//*************************************
	HexForm=Tools.Shift_Rows(HexForm);

	//*************************************
	HexForm=Tools.Mix_columns(HexForm);

	//**************************************
		System.out.println("passed");
		return HexForm;
	}
	
	//**********************
	public static void sub_bytes(String [][]input){
	//sub_bytes stage
		String [][] output=new String[4][4];
	 int[][] sbox = {{0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76},
			 {0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0}, 
			 {0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15},
			 {0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75}, 
			 {0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84}, 
			 {0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf}, 
			 {0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8}, 
			 {0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2}, 
			 {0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73}, 
			 {0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb}, 
			 {0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79}, 
			 {0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08}, 
			 {0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a}, 
			 {0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e}, 
			 {0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf}, 
			 {0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16}};
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(input[i][j].charAt(1)=='0'&&input[i][j].charAt(0)=='0')
					continue;
			input[i][j]=
					 Integer.toHexString(sbox[ Integer.parseInt(input[i][j].charAt(0)+"",16)]
					 [Integer.parseInt(input[i][j].charAt(1)+"",16)]);
			}
	}
		
	}
 	public static int multibication(int X,String y){
		int result = 0b0;
		//String yy = Integer.toBinaryString(Y);
		   for(int i=0;i<y.length();i++){
			   if(Integer.parseInt(y.charAt(i)+"")==0){
				continue;   
			   }
			   result=result^(X<<(y.length()-i-1));
			}
		   
		return result;
	}
 	//***********************************************************
 	
	public static int modlu(int X,int Y){
		// pynomial x mod y
		
		int result = 0b0;
		String y = Integer.toBinaryString(Y);
		   for(int i=0;i<y.length();i++){
			   if(Integer.parseInt(y.charAt(i)+"")==0){
				continue;   
			   }
			   result=result^(X<<(y.length()-i-1));
			}
		return result;
	}
	//********
	public static int multibication_mixing(int X , String y){
		int result=0b0;
		//X = galois 
		// y = the cell 
			//X=Integer.parseInt(,2);
		  if(X==1){
		return Integer.parseInt(y,2);	  
		  }else if(X==2){
			  int v=Integer.parseInt(y,2);
			  if(y.length()!=8|| (y.length()==8&& y.charAt(0)=='0')){
				  v =  (v<<1);
				  v=(Integer.toBinaryString(v).length()>8)?Integer.parseInt(Integer.toBinaryString(v).substring(1, 9),2):v;
			  }else if(y.charAt(0)=='1'&&y.length()==8){
				
				  v=v<<1;
				  v=(Integer.toBinaryString(v).length()>8)?Integer.parseInt(Integer.toBinaryString(v).substring(1, 9),2):v;
				
				  v= (v^0b00011011);
				}else{System.out.println("error occured #1  here ");}
			  y=Integer.toBinaryString(v);
			
			  return Integer.parseInt(y,2);	 
		  }else if(X==3){
			  int v=Integer.parseInt(y,2);
			  if(y.length()!=8 || (y.length()==8&&y.charAt(0)=='0')){
				  v =  (v<<1);
				
				  v=(Integer.toBinaryString(v).length()>8)?Integer.parseInt(Integer.toBinaryString(v).substring(1, 9),2):v;
				  v =v^(Integer.parseInt(y,2));
			  }else if(y.charAt(0)=='1'&&y.length()==8){
				  v=v<<1;
				  v=(Integer.toBinaryString(v).length()>8)?Integer.parseInt(Integer.toBinaryString(v).substring(1, 9),2):v;
			v= (v^0b00011011)^(Integer.parseInt(y,2));
				}else{System.out.println("error occured #1 ");
				
				}
			  y=Integer.toBinaryString(v);
			  return Integer.parseInt(y,2);	 
		  }
			  System.out.println("error occured");
			  return Integer.parseInt(y,2);	 
	}
	//*******
	public static String hexToBinary(String hex) {
	    int i = Integer.parseInt(hex, 16);
	    String bin= Integer.toBinaryString(i);
	    return bin;
	}
}
