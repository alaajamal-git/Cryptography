package part1;

import java.io.IOException;

public class DES {
	static	String path ="C:\\Users\\Black\\Desktop\\crypto\\plaintext.txt";
	static	String path2 ="C:\\Users\\Black\\Desktop\\crypto\\ciphertext.txt";
	static byte [] b=new byte[8];
	//static byte[] K="inalDroD".getBytes();
	static byte [] Key={(byte) 230,(byte) 231,12,22,122,77,111,96};
    
	public static void main(String[] args) throws IOException{
		
	 DESFunctions.encryptFile(path,path2,Key);
	//DESFunctions.decryptFile(path2,"C:\\temp\\t.txt",Key);
		
		
	}

	
}
