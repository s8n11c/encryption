package DES;

public class key_gen {
public static String[] generate(String key){
	System.out.println("the real key :"+key );
	String [] keys =new String[17];
	key=permutations.PC_1(key);
	System.out.println("after PC1 k0:"+key);
	
	pair[] k= new pair[17];
	k[0]=new pair(key.substring(0,28),key.substring(28,56));
	
	for(int i=1;i<=16;i++){
		k[i]=new pair("", "");
	if(i==1||i==2||i==9||i==16)
		k[i]=key_shifting(k[i-1], 1);
		else
			k[i]=key_shifting(k[i-1], 2);
	}
	
	for(int i=0;i<=16;i++){
		keys[i]=permutations.PC_2(k[i].get_left()+k[i].get_right());
		// System.out.println("key["+i+"]="+keys[i]);
	}
	
return keys;	
}
public static String shift(String s) {
    return s.substring(1, s.length())+s.charAt(0);
}
public static pair key_shifting(pair key,int i){
	pair result=new pair("", "");
	
	if(i==1){
		result.set_left(shift(key.get_left()));
		result.set_right(shift(key.get_right()));
		
	}else{
		result.set_left(shift(key.get_left()));
		result.set_left(shift(result.get_left()));
		result.set_right(shift(key.get_right()));
		result.set_right(shift(result.get_right()));
	}
	return result;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
String[] keys=generate("0001001100110100010101110111100110011011101111001101111111110001");
for(String v:keys){
	System.out.println(v);
}

	}

}
