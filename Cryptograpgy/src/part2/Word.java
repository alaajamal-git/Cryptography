package part2;

import java.util.Random;

public class Word {
	public static int[] c = {
			// number of 1 bits in 2s complement value (use value+128)
			// used in class Table
			1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4,
			4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5,
			4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 2, 3,
			3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6,
			4, 5, 5, 6, 5, 6, 6, 7, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6,
			6, 7, 4, 5, 5, 6, 5, 6, 6, 7, 5, 6, 6, 7, 6, 7, 7, 8, 0, 1, 1, 2,
			1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3,
			3, 4, 3, 4, 4, 5, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
			2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 1, 2, 2, 3, 2, 3,
			3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5,
			4, 5, 5, 6, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4,
			4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7 };
	public byte[] w; // the only data field in this class
	// Word: construct and fill bytes with random values

	public Word(Random ranNumGen) {
		w = new byte[Shannon.CWS]; // allocate CWS bytes
		for (int j = 0; j < Shannon.CWS; j++)
			w[j] = (byte) (256 * ranNumGen.nextDouble() - 128);
	}

	// Word: construct and copy input Word u into new class
	public Word(Random ranNumGen, Word u) {
		w = new byte[Shannon.CWS];
		for (int j = 0; j < Shannon.CWS; j++)
			w[j] = u.w[j];
	}
}