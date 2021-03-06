package part1;

import java.util.Arrays;

public class Eample {
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
    private static final byte[][] S = { {
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
    private static final byte[] I = {
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
	public static void main(String[] args) {
		//Long l1 = Long.parseUnsignedLong("17916881237904312345");
		//long n=922337203685477580L ;
       //       System.out.println(l1);
		
		byte [] s={(byte) 162,123,122,66,(byte) 233,(byte) 135,55,(byte) 170};
        int x=0;
		byte [] temp=new byte[8];
		byte [] temp2=new byte[64];
        int counter=0;
        
        
        
int b;

/**
           long l1=Math2.bytesToLong2(s);
           long l2=0;	
	     String str="";
		for(int i=0;i<64;i++){
			str+=((l1>>>(64-IP[i]))&1);
		}
        
       byte[] bval = new BigInteger(str, 2).toByteArray();	
	   System.out.println(Arrays.toString(bval));
        
		for(int i=0;i<64;i++){
			int pos=64-IP[i];
			l2|=(((l1>>>(pos))&1)<<(63-i));
		}
		

        long l2=Math2.bytesToLong(DESFunctions.premuteMethod2(IP, s));
		long Ri;
		long Li;
		byte[] ss=new byte[4];
		 System.out.println(Arrays.toString(Math2.longToBytes(l2)));
		 Ri=DESFunctions.getRightSide(l2);
		 Li=DESFunctions.getLeftSide(l2);
		 System.out.println(DESFunctions.Ex_Per(E, Ri)
				 );*/
//[0, 0, -1, 111, 38, 61, -76, -96]
     //    System.out.println(Arrays.toString("UnalDroD".getBytes()));
         
    System.out.println( DESFunctions.inversIP(FP,205892975L , 1658236212L));


		 System.out.println(Math2.unsignedToBytes((byte)-113));
	
		
			 
		 
		 //33511155554455699
	//	 System.out.println(Integer.SIZE);
		 // System.out.println(Arrays.toString(Math2.longToBytes(Li)));
	 //	 System.out.println(Math2.bytesToLong(DESFunctions.premuteMethod3(E, Math2.longToBytes2(Ri))));
	 	 
	//	 Li=DESFunctions.getLeftSide(l2);
	//	 System.out.println(Arrays.toString(Math2.longToBytes(Li)));
	//	 System.out.println(Li);
	//	 byte [] v=DESFunctions.Ex_Per(E, Ri);
	//	 System.out.println(Arrays.toString(DESFunctions.Ex_Per(E, Ri)));
		 
		 
		 
		/**
		int b;
		
		for(int i=0;i<64;i++){
			 b=IP[i];
			 
			 if(b%8==0)x+=(((s[(b/8)-1]>>>0)&1)<<(7-(i%8)));
			 else	x+=(((s[(b/8)]>>>(8-(b%8)))&1)<<(7-(i%8)));
			if((i+1)%8==0){
				temp[counter]= (byte) x;
				x=0;
				counter++;
			}}


		System.out.println(Arrays.toString(temp));
		*/
	/**		int	[] byt=new int[64];
	for(int i=0;i<8;i++){
		int b=s[i];
		for(int j=7;j>=0;j--){
			byt[counter]= (b>>>j)&1;
			counter++;
		}
		
	}
		String bye="";
		for(int k=0;k<64;k++){
			temp2[k]=(byte) byt[IP[k]-1];
		//	byt[k]=byt[IP[k]-1];
		    bye+=temp2[k];
		//	bye+=byt[k];
		}
		byte[] bval = new BigInteger(bye, 2).toByteArray();
		
		System.out.println(Arrays.toString(bval));
*/
	}

}
