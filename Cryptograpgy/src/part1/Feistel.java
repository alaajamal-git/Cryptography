package part1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
public class Feistel {
	static	String path ="C:\\Users\\Black\\Desktop\\crypto\\plaintext.txt";
	static	String path2 ="C:\\Users\\Black\\Desktop\\crypto\\ciphertext.txt";

	static byte [] b=new byte[8];
	static byte[] K="inal rou".getBytes();
	public static void main(String[] args) throws IOException{
		// it can be turn into decryption mod just by interchang between path and path1 after encryption
		PrintWriter out=new PrintWriter(new FileOutputStream(new File(path2)),true);
		BufferedInputStream in =new BufferedInputStream(new FileInputStream(new File(path)),8);
		while(in.read(b)!=-1){
            System.out.print("plaintext block: "+Arrays.toString(b));

			for(int round=0;round<15;round++){
    		  b=  F_function(b,K);
			}
		      out.print(new String(b));
		      
              System.out.println("     cipher block: "+Arrays.toString(b));
	}
		out.close();
		
		in.close();
		
	
	}
	private static byte[] F_function(byte[] b2, byte[] k2) {

		for(int i=0;i<b2.length;i++){
			for(int j=0;j<k2.length;j++){
				
				b2[i]^=k2[j];
				
			}
			
		}

		
		return b2;
	}

}
