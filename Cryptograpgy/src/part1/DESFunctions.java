package part1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DESFunctions {
	private static final byte[] IP = { 
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9,  1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    };

    /**
     * Final Permutation.  The final result is permuted by this
     * permutation to generate the final ciphertext block.
     */
    private static final byte[] FP = {
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25
    };

    // Permutations relating to the Feistel function.

    /**
     * Expansion Permutation.  The Feistel function begins by applying
     * this permutation to its 32-bit input half-block to create an
     * "expanded" 48-bit value.
     */
    private static final byte[] E = {
        32, 1,  2,  3,  4,  5,
        4,  5,  6,  7,  8,  9,
        8,  9,  10, 11, 12, 13,
        12, 13, 14, 15, 16, 17,
        16, 17, 18, 19, 20, 21,
        20, 21, 22, 23, 24, 25,
        24, 25, 26, 27, 28, 29,
        28, 29, 30, 31, 32, 1
    };
    
    /**
     * Substitution Boxes.  A crucial step in the Feistel function is
     * to perform bit substitutions according to this table.  A 48-bit
     * value is split into 6-bit sections, and each section is permuted
     * into a different 6-bit value according to these eight tables.
     * (One table for each section.)
     * 
     *  According to Wikipedia:
     *  "The S-boxes provide the core of the security of DES - without
     *  them, the cipher would be linear, and trivially breakable."
     */
    private static final int[][] S = { {
        14, 4,  13, 1,  2,  15, 11, 8,  3,  10, 6,  12, 5,  9,  0,  7,
        0,  15, 7,  4,  14, 2,  13, 1,  10, 6,  12, 11, 9,  5,  3,  8,
        4,  1,  14, 8,  13, 6,  2,  11, 15, 12, 9,  7,  3,  10, 5,  0,
        15, 12, 8,  2,  4,  9,  1,  7,  5,  11, 3,  14, 10, 0,  6,  13
    }, {
        15, 1,  8,  14, 6,  11, 3,  4,  9,  7,  2,  13, 12, 0,  5,  10,
        3,  13, 4,  7,  15, 2,  8,  14, 12, 0,  1,  10, 6,  9,  11, 5,
        0,  14, 7,  11, 10, 4,  13, 1,  5,  8,  12, 6,  9,  3,  2,  15,
        13, 8,  10, 1,  3,  15, 4,  2,  11, 6,  7,  12, 0,  5,  14, 9
    }, {
        10, 0,  9,  14, 6,  3,  15, 5,  1,  13, 12, 7,  11, 4,  2,  8,
        13, 7,  0,  9,  3,  4,  6,  10, 2,  8,  5,  14, 12, 11, 15, 1,
        13, 6,  4,  9,  8,  15, 3,  0,  11, 1,  2,  12, 5,  10, 14, 7,
        1,  10, 13, 0,  6,  9,  8,  7,  4,  15, 14, 3,  11, 5,  2,  12
    }, {
        7,  13, 14, 3,  0,  6,  9,  10, 1,  2,  8,  5,  11, 12, 4,  15,
        13, 8,  11, 5,  6,  15, 0,  3,  4,  7,  2,  12, 1,  10, 14, 9,
        10, 6,  9,  0,  12, 11, 7,  13, 15, 1,  3,  14, 5,  2,  8,  4,
        3,  15, 0,  6,  10, 1,  13, 8,  9,  4,  5,  11, 12, 7,  2,  14
    }, {
        2,  12, 4,  1,  7,  10, 11, 6,  8,  5,  3,  15, 13, 0,  14, 9,
        14, 11, 2,  12, 4,  7,  13, 1,  5,  0,  15, 10, 3,  9,  8,  6,
        4,  2,  1,  11, 10, 13, 7,  8,  15, 9,  12, 5,  6,  3,  0,  14,
        11, 8,  12, 7,  1,  14, 2,  13, 6,  15, 0,  9,  10, 4,  5,  3
    }, {
        12, 1,  10, 15, 9,  2,  6,  8,  0,  13, 3,  4,  14, 7,  5,  11,
        10, 15, 4,  2,  7,  12, 9,  5,  6,  1,  13, 14, 0,  11, 3,  8,
        9,  14, 15, 5,  2,  8,  12, 3,  7,  0,  4,  10, 1,  13, 11, 6,
        4,  3,  2,  12, 9,  5,  15, 10, 11, 14, 1,  7,  6,  0,  8,  13
    }, {
        4,  11, 2,  14, 15, 0,  8,  13, 3,  12, 9,  7,  5,  10, 6,  1,
        13, 0,  11, 7,  4,  9,  1,  10, 14, 3,  5,  12, 2,  15, 8,  6,
        1,  4,  11, 13, 12, 3,  7,  14, 10, 15, 6,  8,  0,  5,  9,  2,
        6,  11, 13, 8,  1,  4,  10, 7,  9,  5,  0,  15, 14, 2,  3,  12
    }, {
        13, 2,  8,  4,  6,  15, 11, 1,  10, 9,  3,  14, 5,  0,  12, 7,
        1,  15, 13, 8,  10, 3,  7,  4,  12, 5,  6,  11, 0,  14, 9,  2,
        7,  11, 4,  1,  9,  12, 14, 2,  0,  6,  10, 13, 15, 3,  5,  8,
        2,  1,  14, 7,  4,  10, 8,  13, 15, 12, 9,  0,  3,  5,  6,  11
    } };
    
    /**
     * "P" Permutation.  The Feistel function concludes by applying this
     * 32-bit permutation to the result of the S-box substitution, in
     * order to spread the output bits across 6 different S-boxes in
     * the next round.
     */
    private static final byte[] P = {
        16, 7,  20, 21,
        29, 12, 28, 17,
        1,  15, 23, 26,
        5,  18, 31, 10,
        2,  8,  24, 14,
        32, 27, 3,  9,
        19, 13, 30, 6,
        22, 11, 4,  25
    };

    // Permutations relating to subkey generation

    /**
     * PC1 Permutation.  The supplied 64-bit key is permuted according
     * to this table into a 56-bit key.  (This is why DES is only a
     * 56-bit algorithm, even though you provide 64 bits of key
     * material.)
     */
    private static final byte[] IP2 = {
        57, 49, 41, 33, 25, 17, 9,
        1,  58, 50, 42, 34, 26, 18,
        10, 2,  59, 51, 43, 35, 27,
        19, 11, 3,  60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
        7,  62, 54, 46, 38, 30, 22,
        14, 6,  61, 53, 45, 37, 29,
        21, 13, 5,  28, 20, 12, 4
    };
    
    /**
     * PC2 Permutation.  The subkey generation process applies this
     * permutation to transform its running 56-bit keystuff value into
     * the final set of 16 48-bit subkeys.
     */
    private static final byte[] PC2 = {
        14, 17, 11, 24, 1,  5,
        3,  28, 15, 6,  21, 10,
        23, 19, 12, 4,  26, 8,
        16, 7,  27, 20, 13, 2,
        41, 52, 31, 37, 47, 55,
        30, 40, 51, 45, 33, 48,
        44, 49, 39, 56, 34, 53,
        46, 42, 50, 36, 29, 32
    };
	// premute methods .......................
	

	public static long premuteMethod3(byte[] IP, long plainText, int bitsNumber) {
		long l2 = 0;
		long l1 = plainText;
		int tableLenght=IP.length;
		for (int i = 0; i < tableLenght; i++) {
			int pos = bitsNumber - IP[i];
			l2 |= (((l1 >>> (pos)) & 1) << ((tableLenght-1) - i));
		}
    return l2;
	}
	// End premute methods .......................

	public static long getRightSide(long initP,int length) {

		long r=0;
		int start=length/2;
		for(int i=start-1;i>=0;i--){
			r|=((initP>>i)&1)<<i;
		}
		return r;
	}

	public static long getLeftSide(long initP,int lenght) {
		long l=0;
		int start=lenght;
		int end=lenght/2;
		for(int i=start-1;i>=end;i--){
			l|=((initP>>i)&1)<<(i-end);
			
		}
		return l;
	}

	public static long Ex_Per(byte[] e, long ri) {
				return  premuteMethod3(e, ri, 32);
	}

	public static long[] generateKi(long ki) {

		long r,l;
	    long key=premuteMethod3(IP2, ki,64);
	    long[] intitKeys=new long[16];
	    for(int round=0;round<16;round++){
		 r=getRightSide(key, 56);
		 l=getLeftSide(key, 56);
		 
		if(round==0|round==1|round==8|round==15){
			l=((l>>>27)&1)|((l&0x7FFFFFF)<<1);
			r=((r>>>27)&1)|((r&0x7FFFFFF)<<1);
			key=(l<<28)|r;
		}
		else{
			l=((l>>>27)&1)|((l&0x7FFFFFF)<<1);
			l=((l>>>27)&1)|((l&0x7FFFFFF)<<1);
			r=((r>>>27)&1)|((r&0x7FFFFFF)<<1);
			r=((r>>>27)&1)|((r&0x7FFFFFF)<<1);
			key=(l<<28)|r;
			
		}
		intitKeys[round]=key;
	    }
		return intitKeys;
	}

	public static long subsitute(int[][] s, byte[] p, long eXORK) {
		int x=0;
		long result=0;

		for(int i=1;i<=8;i++){
		  x=(int) ((eXORK>>>48-i*6)&0x3F);
		  result|=	s[i-1][x]<<32-4*i;
		}
		result=premuteMethod3(p, result, 32);
		return result;
		
		
	}

	public static long inversIP(byte[] fp, long li, long ri) {
		long result=(li<<32)|ri;
	return	premuteMethod3(fp, result, 64);
	}

	
	
	public static byte[] encrypt(byte[] b, byte[] Key) {
		long[] keyestablish;
		keyestablish=generateKi(Math2.bytesToLong(Key));
		long h=	premuteMethod3(IP,Math2.bytesToLong(b),64);
		long initP=h;
		long temp;
		long REi = 0,LEi = 0,Expand_48,Ki = 0,EXORK,result;
		// first value of key
		 REi=DESFunctions.getRightSide(initP,64);
		 LEi=DESFunctions.getLeftSide(initP,64);
			for(int round=0;round<16;round++){
				 Expand_48=DESFunctions.Ex_Per(E,REi);
				 Ki=premuteMethod3(PC2,keyestablish[round], 56);;
				 EXORK=Expand_48 ^ Ki;
				 result = DESFunctions.subsitute(S,P,EXORK);
				 temp=REi;
				 REi=result ^ LEi;
				 LEi=temp;
			}
			 byte[] end=Math2.longToBytes(DESFunctions.inversIP(FP,LEi,REi)); 
	      return	end;
		
	}
	
	public static byte[] decrypt(byte[] b, byte[] Key) {	
		 long[] keyestablish;
		 keyestablish=generateKi(Math2.bytesToLong(Key));
	     long h = premuteMethod3(IP,Math2.bytesToLong(b),64);
	     long initP=h;
         long temp;
	     long RDi = 0,LDi = 0,Expand_48,Ki = 0,EXORK,result;
	
	// first value of key
	 LDi=DESFunctions.getRightSide(initP,64);
	 RDi=DESFunctions.getLeftSide(initP,64);
		for(int round=0;round<16;round++){
			 Expand_48=DESFunctions.Ex_Per(E,RDi);
			 Ki=premuteMethod3(PC2,keyestablish[15-round], 56);;
			 EXORK=Expand_48 ^ Ki;
			 result = DESFunctions.subsitute(S,P,EXORK);
			 temp=RDi;
			 RDi=result ^ LDi;
			 LDi=temp;
		}
		 temp=RDi;
		 RDi= LDi;
		 LDi=temp;
		byte[] end= Math2.longToBytes(DESFunctions.inversIP(FP,LDi,RDi));

		 return	end;

}

	public static void encryptFile(String source, String destination, byte[] key) {
		byte [] b=new byte[8];
		try {
			// don't use PrintWriter
			FileOutputStream  out = new FileOutputStream(destination,true);
			BufferedInputStream in =new BufferedInputStream(new FileInputStream(new File(source)),8);

			while(in.read(b)!=-1){
				b=	DESFunctions.encrypt(b,key);
				out.write(b);
				
			}
			System.out.println("File encrypted successfully OUT"+destination);
			out.close();
			in.close();
		} catch (IOException e) {
			System.out.println("Error Path File !!");
		}
	}

	public static void decryptFile(String source, String destination, byte[] key) {
		byte [] b=new byte[8];
		try {
			// don't use PrintWriter
			FileOutputStream  out = new FileOutputStream(destination,true);
			BufferedInputStream in =new BufferedInputStream(new FileInputStream(new File(source)),8);

			while(in.read(b)!=-1){
				b=	DESFunctions.decrypt(b,key);
				out.write(b);
			}
			System.out.println("File decrypted successfully OUT"+destination);
			out.close();
			in.close();
		} catch (IOException e) {
			System.out.println("Error Path File !!");
		}
	}
}
