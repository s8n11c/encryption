package rielFence;

public class RielFence {

	public static String encrypt(String M, int key){
		String [] Depth=new String[key];
		
		for(int i=0;i<key;i++) Depth[i] = new String ();
		 int mod=0;
		for(int i=0;i<M.length();i++){
			
			Depth[mod++]+=M.charAt(i);
			if(mod==key){
				mod=0;
			}
		}
		String C= new String();
		for(int i=0;i<Depth.length;i++){
			C+=Depth[i];
		}
		return C;
	}
	
	public static String decrypt(String C,int key){
		String M = new String();
		int length=C.length();
		String Depth[] = new String[key];
		for(int i=0;i<key;i++){
			Depth[i]=new String();
		}
		
		if(length%key==0){
			int P=length/key;
				int mod=0;
				int start=0, end=P;
				for(int i=0;i<key;i++){
					Depth[mod++]=
							C.substring(start,end);
					start=end;
					end=end+P;
				}
				
				System.out.println(Depth[0]);
				System.out.println(Depth[1]);
				System.out.println(Depth[2]);
					mod=0;
				for(int i=0;i<Depth[0].length();i++){
					for(int j=0;j<key;j++){
						M+=Depth[j].charAt(i);
					}
				}
		}else{
			
			int mod=0;
			
			int def=length%key;
		     def=key-def;
			for(int i=0;i<def;i++){
				C+='x';
			}
		
			System.out.println(C);
			int P=C.length()/key;
			int Q=P-1;
			int start=0, end=P;
			def=length%key;
			int add_def=key-def;
			System.out.println(def);
			int index2=0;
			int index=0;
			for(int i=0;i<def;i++){
				Depth[index]=C.substring(start, end);
				start=end;
				end=end+P;
				index++;
			}
			end=end-1;
			P=P-1;
			for(int i=index;i<key;i++){
				Depth[index]=C.substring(start, end);
				start=end;
				end=end+P;
				index++;
			}
				System.out.println(Depth[0]);
				System.out.println(Depth[1]);
				System.out.println(Depth[2]);
				System.out.println(Depth[3]);
				System.out.println(Depth[4]);
			
				mod=0;
			for(int i=0;i<Depth[0].length()-1;i++){
				for(int j=0;j<key;j++){
					M+=Depth[j].charAt(i);
				}
			}
			System.out.println(M);
			for(int i=0;i<def;i++){
				M+=Depth[i].charAt(Depth[0].length()-1);
			}
			System.out.println(M);
		
		}
		
		return M;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		String test="helloworld";
		System.out.println(encrypt(test, 7));
		System.out.println(decrypt(encrypt(test, 7), 7));
	}

}
