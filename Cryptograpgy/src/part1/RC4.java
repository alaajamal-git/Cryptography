package part1;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RC4 {
	
	
	private static final String M="I am not a robot !";
	private static int[] state_S;
	private static String Key="secret key";
	private static byte[] Ci=new byte[M.getBytes().length];
	public static void main(String[] args){
		
		byte[] m=M.getBytes();
		byte[] k=Key.getBytes();
		System.out.println("message: "+M);
		System.out.println("Key: "+Key);
		System.out.println("message_Bytes: "+Arrays.toString(m));
		System.out.println("Key_Bytes: "+Arrays.toString(k));
		
		
		state_S=new int[128];
		byte[] T_vector = prepareKey(Key.getBytes());
		System.out.println("T_vector: "+Arrays.toString(T_vector));

	     intitiatRC4_KSA(T_vector);
		 System.out.println("State_vector: "+Arrays.toString(state_S));
	     start_PRGA(m);
		 System.out.println("Code_byte: "+Arrays.toString(Ci));
	     System.out.println("Code_UTF8: "+new String(Ci, StandardCharsets.UTF_8));

		
		
		
	}

	private static void start_PRGA(byte[] m2) {

		int i = 0,j=0;
		int t=0;
		int counter=0;
		for(@SuppressWarnings("unused") byte b:m2){
			i=(i+1)%128;
			j=(j+state_S[i])%128;
			state_S[i]=state_S[j];
			t=(state_S[i]+state_S[j])%128;
			Ci[counter]=(byte) (m2[counter]^state_S[t]);
			counter++;
		}
		
	}

	private static byte[] prepareKey(byte[] bs) {

		int lengthOfKey=Key.length();
    	int def;
		byte[] key=new byte[128];
		for(int j=0;j<lengthOfKey;j++)
			key[j]=bs[j];
		if(lengthOfKey<128){
			def=128-lengthOfKey;
			for(int i=lengthOfKey;i<128;i++)
				key[i]=key[def-(128-i)];
			
			
		}			
			
		return key;
		
	}

	private static void intitiatRC4_KSA(byte[] k2) {

		for(int i=0;i<128;i++){
			state_S[i]=i;
		}
		
		int j=0;
		for(int k=0;k<128;k++){
			j=(j+state_S[k]+k2[k])%128;
			state_S[k]=state_S[j];
			
		}

		}
	
	
	
	

}
