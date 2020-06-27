package part1;

public class LFSRexample2 {

	public static void main(String[] args) {
		int p=19; //x^4+x^1+1
		int x=0;
		
    	for(int i=0;i<60;i++){
    		p=19;
    		x=6<<(i%15)+1;

            if(i%15==0)
     		System.out.println();

    		System.out.print(((x%19)&1)+"  ");
    		
    		
    	}
	}

}
