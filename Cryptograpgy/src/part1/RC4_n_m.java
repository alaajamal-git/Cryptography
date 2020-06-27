package part1;
import java.util.Arrays;


public class RC4_n_m {

	private static final int n=8;
	private static final int m=32;
	//Key of 11 bytes
	//HEX to Decimal
	private static int[] key={0XAF, 0XFE,0XA3, 0XCE,
		0XDE,0X4E,0X43,0X5B,0XF2,0X43,0XC3};
	//
	//private static byte[] IV={} ;
	private static int[] S;
	private static String message="In this section, we prove that RC4(n;m) is not resistant against distin-guishing and key recovery attacks.";
	private static int[] output;
	public static void main(String[] args) {

		int N =(int)Math.pow(2, n);
		int M =(int)Math.pow(2, m);
		S=new int[N];
		byte [] _M=message.getBytes();
	    System.out.println("bytes of the message"+Arrays.toString(_M));
		//encrypt
	    System.out.println("---------------Encryption");

		output=	intiatPRGS(_M,initKSA(N,M),N,M);
		byte[] ciphertext =  encrypt(output,_M);
		System.out.println("cipher text: "+Arrays.toString(ciphertext));
		System.out.println(new String(ciphertext));		

		//decrypt
	    System.out.println("---------------Decryption");

		output=	intiatPRGS(ciphertext,initKSA(N,M),N,M);
	    ciphertext =  encrypt(output,ciphertext);
		System.out.println(Arrays.toString(ciphertext));
		System.out.println(new String(ciphertext));		
	}
	private static byte[] encrypt(int[] output2, byte[] _M) {

		byte[] enc=new byte[_M.length];
		for(int i=0;i<_M.length;i++){
			enc[i]=(byte) (output2[i]^_M[i]);			
		}
		return enc;

		
		
	}
	private static int[] intiatPRGS(byte[] _M, int k,int N,int M) {

		

		int counter=0;
		int[] out_put=new int[_M.length];
		int i=0,j=0;
		
		for(byte b:_M){
			i=(i+1)%N;
			j=(j+S[i])%N;
			k=(j+S[j])%M;
			out_put[counter]=(S[(S[i]+S[j])%N]+k)%M;
			S[(S[i] + S[j] ) %N] = (S[i] + k )% M;
			counter++;
		}
		return out_put;
		
		
		
		
		
	}
	private static int initKSA(int N,int M) {

		int j=0,k=0;
		for(int i=0;i<N;i++){
			S[i]=((129+i*13)%N);
		}
	    System.out.println("IV of S[]: "+Arrays.toString(S));

		for(int a=0;a<N;a++){
			j=(j+S[a]+key[a%11])%N;
			S[a]=S[j];
			S[a]=(S[a]+S[j])%M;
			k=((k+S[a]))%M;
		}
	    System.out.println("S[] output of KSA: "+Arrays.toString(S));

		
		return k;
		
		
	}

}
