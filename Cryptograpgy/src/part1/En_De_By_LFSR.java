package part1;

import java.util.Arrays;

public class En_De_By_LFSR {

	private static String message ="asdds";
	public static void main(String[] args) {
	    int	bitlengthofmessage=message.length()*7;

		//encrypt
		byte[] plaintext=message.getBytes();
		System.out.println("plaintext: "+Arrays.toString(plaintext));

		int [] Si=new int[bitlengthofmessage];
		byte Byt;
		int counter=0;
		int bit ;
		int [] y=new int[bitlengthofmessage];
		for(int i =0;i<plaintext.length;i++){
			Byt=plaintext[i];
			for(int j=0;j<7;j++){
		      Si[counter]=geKeyBit(counter);
			  bit =	(((Byt>>>j)&1)+Si[counter])%2;
			  Si[counter]=geKeyBit(counter);
			y[counter]=bit;
			counter++;
			}
			
			
		}
		System.out.println("Si= "+Arrays.toString(Si));
		System.out.println("Yi= "+Arrays.toString(y));
		
		
		
		// decrypt
    	int 	ciphertextlegth=y.length;
		byte [] xi=new byte[ciphertextlegth/7];
		counter=0;
	    String B="";
	    bit=0;
		for(int k=0;k<y.length;k++){
			bit=((y[k]+geKeyBit(k))%2);
			B+=bit;
			//B.intern();
			if((k+1)%7==0){
				
				xi[counter]=Byte.parseByte(new StringBuffer(B).reverse().toString(), 2);
				counter++;
                B="";
                bit=0;
			}
			
		}
		
		System.out.println("plaintext: "+Arrays.toString(xi));
		
	}

	public static int geKeyBit(int i){
		
		int p=19; //x^4+x^1+1
		int x=0;
    	
    		p=19;
    	//	initial value 1111=15=x^3+x^2+x+1 of flip-flop
    		x=6<<(i%15)+1;
            int s = (int)((Math.log(x)/Math.log(2))+1)-(int)((Math.log(p)/Math.log(2))+1);
             while (s>=0){
            	 p=19;
            	 p=p<<s;
            	 x=p ^ (int)x;
            	 s=	(int)((Math.log(x)/Math.log(2))+1)-(int)((Math.log(19)/Math.log(2))+1);
             }
            return x&1;
    		
    	
		
		
		
		
		
	}
}
