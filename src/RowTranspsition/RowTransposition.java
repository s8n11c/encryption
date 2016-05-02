package RowTranspsition;

import java.util.List;

import org.omg.CORBA.FREE_MEM;

import java.util.ArrayList;

public class RowTransposition {
public static String encrypt(String M,int[] key){
	//turning the key to numbers 
	//****************************
	//int [] key =Key_prepare(key_str);
	int [] buffer = new  int[key.length];
	for(int i=0;i<key.length;i++){
		buffer[i]=key[i];
		System.out.println("the value is "+key[i]);
	}
	System.out.println("the length is +"+key.length);
	String C= new  String();
	int L=key.length;
	int def=M.length()%L;
	//filling with | 
	if(def!=0){
		def=L-def;
		
		for(int i=0;i<def;i++){
		M+='|';
	}
	}
	L=M.length();
	
	
	int row=1+(M.length()/key.length);
	int col=key.length;
	
	char [][] Mat = new char[row][col];
	
	java.util.Arrays.parallelSort(key);
	String bridge = new String(M);
	//M="";
	/*for(int i=0;i<key.length;i++)
		M+=key[i]+"";
	M+=bridge;	
	*/
	int index=0;
	for(int i=1;i<col;i++){
		Mat[0][i]=(char)key[i];
	}
	for(int i=1;i<row;i++){
			for(int j=0;j<col;j++){
				Mat[i][j]=M.charAt(index++);
			}
	}

	for(int i=0;i<row;i++){
		for(int j=0;j<col;j++){
			System.out.print(Mat[i][j]);
		}
		System.out.println("");
}

	// the resorting with the god damn transposition 
	char [][] result = new char[row][col];
	for(int i=0;i<buffer.length;i++){
		for(int j=0;j<key.length;j++){
			
			if(buffer[i]==key[j]){
				result[0][i]=Mat[0][j];
				for(int d = 1 ;d<row;d++){
					result[d][i]=Mat[d][j];
				}
			key[j]=-100;
				break;
			}
		}
	}

	for(int i=0;i<row;i++){
		for(int j=0;j<col;j++){
			System.out.print(Mat[i][j]);
		}
		System.out.println("");
}	
	//**********************************************

	//*********************************************
	for(int i=0;i<col;i++){
		for(int j=1;j<row;j++){
			//if(result[j][i]=='|')
				//continue;
			C+=result[j][i];
		}
	}

	for(int i=0;i<row;i++){
		for(int j=0;j<col;j++){
			System.out.print(result[i][j]);
		}
		System.out.println("");
}
	System.out.println(C);
			return C;
}
/****************************************************/
//***************************************************
static public String decrypt(String C,int [] key){
	String M = new String();
	//int []key=Key_prepare(key_str);
	int L=key.length;
	int def=C.length()%L;
	//filling with | 
	if(def!=0){
		def=L-def;
		System.out.println("def = "+def);
		
		for(int i=0;i<def;i++){
		C+='|';
	}
	}
	L=C.length();
	int row=1+(C.length()/key.length);
	int col=key.length;

	char [][] Mat = new char[row][col];
	
	
	int index=0;
	for(int i=0;i<col;i++){
			for(int j=1;j<row;j++){
				Mat[j][i]=
						C.charAt(index++);
			}
	}
	

	for(int i=0;i<col;i++){Mat[0][i]=(char)(key[i]+48);}
	System.out.println("the length is "+Mat.length);

	for(int i=0;i<row;i++){
		for(int j=0;j<col;j++){
			System.out.print(Mat[i][j]);
		}
		System.out.println("");
}
	//sorting the columns
	//Bubble sort algorithm
	for(int i=0;i<key.length;i++){
		for(int j=i+1;j<key.length;j++){
			 if(key[i]>key[j]){
				 Mat=swap_cols(Mat, i, j);
			 }
		}
	}
	System.out.println("after the sorting");
	for(int i=0;i<row;i++){
		for(int j=0;j<col;j++){
			System.out.print(Mat[i][j]);
		}
		System.out.println("");
}
	//collecting the rest 
	for(int i=1;i<row;i++){
		for(int j=0;j<col;j++){
			//if(Mat[i][j]=='|')
				//continue;
			M+=Mat[i][j];
		}
	}
	return M;
}

//assistant methods 
public static char [][] swap_cols(char[][] Mat,int index1 ,int index2 ){
	char [] temp = new char[Mat.length];
	for(int i=0;i<temp.length;i++){
		temp[i]=Mat[i][index1];
	}
	
	for(int i=0;i<temp.length;i++){
		Mat[i][index1]=Mat[i][index2];
	}
	

	for(int i=0;i<temp.length;i++){
		Mat[i][index2]=temp[i];
	}
	return Mat;
}
//***********************************************
static public int [] Key_prepare(String key){
	int [] key_param  = new int[key.length()];
	String alpha="abcdefghijklmnopqrstuvwxyz";
	int i=0; while(i<key.length()){
				key_param[i]=alpha.indexOf(key.charAt(i++));
	         }
return key_param;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Notes:
		//working fine without reapeating 'e' 
		int [] key={3,4,2,1,5,6,7};
		int [] key2={3,4,2,1,5,6,7};
		
String test=encrypt("attackpostponeduntiltwoamxyz", key);
System.out.println(decrypt(test, key2));
			
	}

}
