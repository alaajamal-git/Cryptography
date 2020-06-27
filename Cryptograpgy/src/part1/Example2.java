package part1;

import java.util.*;

class Example2 {
	
	public static void main (String [] args){
		
		byte[] b={122,123,122,66,77,101,55,98};
		long x=Math2.bytesToLong(b);
		System.out.println(x);
		System.out.println(Arrays.toString(Math2.longToBytes2(x)));

		
	}
}