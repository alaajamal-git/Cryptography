package part1;
import java.util.Arrays;
public class GCDalaa {
	private static long remainder=0;
	public static void main(String[] args) {
    long x=251 ;
    long y=201 ;
    long d[] = new long[(int) getSteps(x, y) + 1];
    d[d.length-1]=1;
    d=getPruductArray(d,x,y);
	System.out.println(Arrays.toString(d));	
    d= getAB(d);
    long a=d[1];
    long b=d[0];
    if(a*x==-b*y+remainder)
    	b=-b;
    else a=-a;
    
	System.out.println(Arrays.toString(d));	
	System.out.println("( ("+a+")*"+x+"+("+b+")*"+y+") ="+remainder);	
	
	
	
	
}
	
	private static long[] getAB(long[] d) {
		int v= d.length-3;
		System.out.println("v: "+v);
	    while(v>=0){
	    	d[v]=(d[v]*d[v+1])+d[v+2];
	    	v--;}
	    return d;
	}





	private static long[] getPruductArray(long[] d, long x, long y) {
		int i=0;
		 long c=x%y;
		    while(c!=0){
		    	d[i]=x/y;
		    	x=y;
		    	y=c;
		    	c=x%y;
		    	i++;
		    }
		    return d;
	}





	public static long getSteps(long x, long y) {
		long h=0;
		while (y != 0) {
			remainder = x % y;
			x = y;
			y = remainder;
			remainder=x;
			h++;
		}
		return h-=1;
	}
}
