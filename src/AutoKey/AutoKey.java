package AutoKey;

public class AutoKey {
	public static String encrypt(String M,String key){
		String C = new String();
		char [][] table=new char[26][26];
		String alpha="abcdefghijklmnopqrstuvwxyz";
		int index=0;
		for(int i = 0;i<26;i++){
			
			index=i;
			for(int j=0;j<26;j++){
				table[i][j]=alpha.charAt(index);
			    if(index+1>25){
			    	index=0;
			    }else{
			    	index++;
			    }
			}
		}
		String k = new String();
		k+=key;
		for(int i=0;i<M.length()-key.length();i++){
			k+=M.charAt(i);
			
		}
	System.out.println(k);	
		//vignere table printing 
/*	for(int i = 0;i<26;i++){	
			for(int j=0;j<26;j++){
			    System.out.print(table[i][j]+"|");
			}
			System.out.println("\n -------------------------------------------------------\n");
		}*/
		for(int i=0;i<M.length();i++){
			 C+=table[alpha.indexOf(M.charAt(i))][alpha.indexOf(k.charAt(i))];
		}
		return C ;
	}

public static String decrypt(String C,String key){
	String M = new String();
	char [][] table=new char[26][26];
	String alpha="abcdefghijklmnopqrstuvwxyz";
	int index=0;
	for(int i = 0;i<26;i++){
		index=i;
		for(int j=0;j<26;j++){
			table[i][j]=alpha.charAt(index);
		    if(index+1>25){
		    	index=0;
		    }else{
		    	index++;
		    }
		}
	}
	String k = new String();
	index=0;
k+=key;

System.out.println(k+"---length:"+k.length());
System.out.println(C+"---length"+C.length());

	//vignere table printing 
for(int i = 0;i<26;i++){	
		for(int j=0;j<26;j++){
		    System.out.print(table[i][j]+"|");
		}

	   	System.out.println("\n -------------------------------------------------------\n");
	}


int l=k.length();
String buffer="";
for(int i=0;i<k.length();i++){
	buffer=assisstant(C.charAt(i), alpha, table, k.charAt(i));
	if(k.length()<C.length())
    	k+=buffer;
    	M+=buffer;
}
System.out.println(k);
	return M ;
}
//**********************
public static String assisstant(char C,String alpha,char [][]table,char k){
	String M=new String();
		
			for(int j=0;j<alpha.length();j++){
				if(table[j][0]==k){
					for(int m=0;m<alpha.length();m++){
						if(table[j][m]==C){
							M+=table[0][m];
							break;
						}
						
					}
					break;
				}
			}
	               	
			
		
		
		
		
	return M;
}

	public static void main(String[] args) {
		
	}

}
