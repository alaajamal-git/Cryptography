package part1;

public class LFSRexample {

	public static void main(String[] args) {

		int p=19; //x^4+x^1+1
		int x=0;
    	for(int i=0;i<60;i++){
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
            if(i%15==0)
     		System.out.println();

    		System.out.print((x&1)+"  ");
    		
    	}
		
		
		
		
		
	}

}
