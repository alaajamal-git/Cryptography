package part1;

public class A5_1 {

	
	public static int unsignedToBytes(byte b) {
	    return b & 0xFF;
	  }

	  public static void main(String[] args) {
			System.out.print("key stream :");

		  for(int ki=1;ki<1000;ki++){
			  int out_put_R1=register1(ki,524323);
			  int out_put_R2=register1(ki,4194307);
			  int  out_put_R3=register1(ki,8421379);
		int out=  out_put_R1^out_put_R2^out_put_R3;
		System.out.print(out+",");
		  }
	  }

	private static int register1(int ki,int p) {
	   //int p=524323; //x^19+x^5+x^1+1
	   //int p=4194307; //x^22+x^1+1
	   //int p=8421379; //x^23+x^5+x^1+1

		int x=466973; //init value of register 
		int i=0;
		int counter;
		int XOR;
		int shift;
		int out=0;
		while (i<ki){
			
			counter=0;
		    XOR=0;
			int AND=p&x;
			shift=AND;
			while(true){
				int rightBit=shift&1;
				if(rightBit==1)counter++;
				shift=shift>>>1;
				if(shift==0)
					break;
			}
			if(counter%2!=0)
			XOR =1;
			x=((x>>>1)|(XOR<<18));
		    out=x&1;
			i++;
		}
	  return out;  
	}
	  
}
