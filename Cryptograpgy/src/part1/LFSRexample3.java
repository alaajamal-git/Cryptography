package part1;

public class LFSRexample3 {

	public static void main(String[] args) {
		int p=19;
		int x=6;
		int i=1;
		int counter;
		int XOR;
		int shift;
		while (i<100){
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
			x=((x>>>1)|(XOR<<3));
			System.out.print((x&1)+" ");
			if(i%15==0)System.out.println();
			i++;
			
		}

	}

}
