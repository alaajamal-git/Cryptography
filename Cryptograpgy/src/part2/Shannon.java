package part2;

//Shannon.java: a simulation of Shannon’s random coding
import java.util.Random; // use fancy rng for reproducability

public class Shannon {
	public static final double P = 0.75; // prob of no error
	public static int N; // blocksize, from command line
	public static int expN; // = 2**N, table size, calculated from N
	public static final double C = capacity(P);
	public static int CWS; // the codeword size, bytes, from cmd line
	private static Random ranNumGen = new Random(); // diff each time

	public static double log2(double d) { // for log2 in Java
		return Math.log(d) / Math.log(2.0);
	}

	public static double capacity(double p) { // channel capacity
		if (p == 0 || p == 1)
			return 1;
		return 1 + p * log2(p) + (1 - p) * log2(1 - p);
	}

	public static int randInt(int i) { // rand int, between 0 and i-1
		return (int) (ranNumGen.nextDouble() * i);
	}

	// perturb: alter bits of input word, each time with prob 1-P
	public static Word perturb(Word v) {
		Word u = new Word(ranNumGen, v);
		int[] mask = { 1, 2, 4, 8, 16, 32, 64, -128 };
		for (int i = 0; i < Shannon.CWS; i++)
			for (int j = 0; j < 8; j++)
				if (ranNumGen.nextDouble() > Shannon.P) {
					u.w[i] = (byte) (mask[j] ^ u.w[i]);
				}
		return u;
	}

	public static void main(String[] args) {
		int simSize = (int) 10e5; // # of trials
		N = Integer.parseInt("10"); // block size
		CWS = Integer.parseInt("10"); // codeword size
		expN = 1;
		for (int i = 0; i < N; i++)
			expN = expN * 2; // expN = 2**N, table size in Table.java
		System.out.println("simSize: " + simSize + ", Blocksize: " + Shannon.N
				+ ", Codeword size (bytes): " + Shannon.CWS + ", expN: "
				+ Shannon.expN);
		// count matches and two kinds of mismatches
		int numMatch = 0, numNonMatch = 0, numMultiMatch = 0;
		System.out.println(ranNumGen.nextDouble());

		Table tab = new Table(ranNumGen); // the coding table
		for (int k = 0; k < simSize; k++) {
			int ind = randInt(Shannon.expN); // index of rand code word
			Word w = tab.getWord(ind); // w is the random code word
			Word u = perturb(w); // u is w with random noise added
			int ind2 = tab.search(u); // closest match, perturbed code word
			if (ind2 == ind)
				numMatch++;
			else if (ind2 >= 0) { // matched wrong code word, not one sent
				numNonMatch++;
			} else if (ind2 < 0)
				numMultiMatch++; // multiple matches
			if (k % 500 == 499) {
				System.out.print("Error Rate "+k+": " + (k + 1 - numMatch)
						/ (double) (k + 1));
				System.out.println(", Match: " + numMatch + ", Non-Match: "
						+ numNonMatch + ", Multiples: " + numMultiMatch);
			}
		} // for
		System.out.print("Error Rate: " + (simSize - numMatch)
				/ (double) simSize);
		System.out.println(", Match: " + numMatch + ", Non-Match: "
				+ numNonMatch + ", Multiples: " + numMultiMatch);
	}
}