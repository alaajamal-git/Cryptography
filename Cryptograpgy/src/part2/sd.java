package part2;

import java.util.Random;

public class sd {

	public static void main(String[] args) {

		Random ranNumGen = new Random();
		for(;;){
			System.out.println((int)(ranNumGen.nextDouble()*32));
	
			
	//	System.out.println((byte)(256*ranNumGen.nextDouble() - 128));
		}
		
		
	}

}
