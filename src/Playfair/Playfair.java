package Playfair;

/*
* author : s0n1c 
* Date :12:05 10-3-16
*  all right reserved 
*  Under GPL (GNU General public license)
*/
public class Playfair {
    public static String encrypt(String M,String k){
    	String C = new String();
        char [][]key = new char[5][5];
        StringBuilder alpha=new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        String bridge = new String();
        
        for(int i = 0 ;i<k.length();i++){
        	
       	char g = k.charAt(i);
       if(g=='j'){g='i';}
       	if(bridge.indexOf(g)!=-1)
        		continue;
        bridge+=g;
        alpha.deleteCharAt(alpha.indexOf(g+""));
        }
        for(int i = 0 ;i<alpha.length();i++){
     bridge+=alpha.charAt(i);
        }
        // establishing the matrix of fusion 
        int index=0;
        for (int i = 0 ;i<5;i++){
        	for(int j = 0 ;j<5;j++){
        		key[i][j]=bridge.charAt(index++);		
        	}
        }
        for(int m=0;m<5;m++){
        	for(int n = 0;n<5;n++){
              System.out.print(key[m][n]);        
        	}System.out.println("");
        }
        //************************************************************************
        //*********************[[ ciphering  algorithm  ]]************************
        //************************************************************************
        char p1,p2;
        for(int i = 0 ;i<M.length();i=i+2){
        	
        	
        	p1=M.charAt(i); 
        	if(i+1>M.length()-1){
        		p2='x';
        		
        	}else{
        		p2=M.charAt(i+1);
            	
        	}
        	if(p1==p2){
        		
        		p2='x';
        		i=i-1;
        	}
        	
        	//[a] stands for x axis matrix (rows)
        	//[b] for (cols)
        	int a1=0,b1=0,a2 = 0,b2=0;
        //checking positions of the pairs 
        	// go back in time .. remember when we had it all 
        for(int m=0;m<5;m++){
        	for(int n = 0;n<5;n++){
        		if(key[m][n]==p1){
        			a1=m;b1=n;
        			 break;
        		}
        	}
        }
        for(int m=0;m<5;m++){
        	for(int n = 0;n<5;n++){
        		if(key[m][n]==p2){
        			a2=m;b2=n;
        			 break;
        		}
        	}
        }// in the same row
        if(a1==a2){
        	
        C+=key[a1][(b1+1>4)?0:b1+1];
        C+=key[a2][(b2+1>4)?0:b2+1];
        }
        // in the same row 
        else if (b1==b2){C+=key[(a1+1>4)?0:a1+1][b1];
              C+=key[(a2+1>4)?0:a2+1][b2];
        }
        //otherwise
        else{
        	C+=key[a2][b1];
        	C+=key[a1][b2];
        }
        
        }return C;
    }
    //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
  //*******************************************************************************************************
    
    public static String decrypt(String C,String k){
    	String M = new String();
        char [][]key = new char[5][5];
        //**********************************************************************
        StringBuilder alpha=new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        String bridge = new String();
        
        for(int i = 0 ;i<k.length();i++){
        	
       	char g = k.charAt(i);
       if(g=='j'){g='i';}
       	if(bridge.indexOf(g)!=-1)
        		continue;
        bridge+=g;
        alpha.deleteCharAt(alpha.indexOf(g+""));
        }
        for(int i = 0 ;i<alpha.length();i++){
     bridge+=alpha.charAt(i);
        }
        // establishing the matrix of fusion 
        int index=0;
        for (int i = 0 ;i<5;i++){
        	for(int j = 0 ;j<5;j++){
        		key[i][j]=bridge.charAt(index++);		
        	}
        }
        for(int m=0;m<5;m++){
        	for(int n = 0;n<5;n++){
              System.out.print(key[m][n]);        
        	}System.out.println("");
        }
        //************************************************************************
        //*********************[[ Deciphering  algorithm  ]]**********************
        //************************************************************************
        char p1,p2;
        for(int i = 0 ;i<C.length();i=i+2){
        	
        	
        	p1=C.charAt(i); 
        	if(i+1>C.length()-1){
        		p2='x';
        		
        	}else{
        		p2=C.charAt(i+1);
            	
        	}
        
        	
        	//[a] stands for x axis matrix (rows)
        	//[b] for (cols)
        	int a1=0,b1=0,a2 = 0,b2=0;
        //checking positions of the pairs 
        	// go back in time .. remember when we had it all 
        for(int m=0;m<5;m++){
        	for(int n = 0;n<5;n++){
        		if(key[m][n]==p1){
        			a1=m;b1=n;
        			 break;
        		}
        	}
        }
        for(int m=0;m<5;m++){
        	for(int n = 0;n<5;n++){
        		if(key[m][n]==p2){
        			a2=m;b2=n;
        			 break;
        		}
        	}
        }
        System.out.println("a1 is "+a1+"--b1 is :"+b1+"--a2 is "+a2+"--b2 is "+b2);
        // in the same row
        if(a1==a2){
        	
        M+=key[a1][(b1-1<0)?4:b1-1];
        M+=key[a2][(b2-1<0)?4:b2-1];
        }
        // in the same row 
        else if (b1==b2){
        	System.out.println("its the same col");
        	  M+=key[(a1-1<0)?4:a1-1][b1];
              M+=key[(a2-1<0)?4:a2-1][b2];
        }
        //otherwise
        else{
        	M+=key[a2][b1];
        	M+=key[a1][b2];
        }
        }
    	return M;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data=encrypt("balloon", "monoarchy");
System.out.println(data);
System.out.println(decrypt(data,"monoarchy"));
	}

};
