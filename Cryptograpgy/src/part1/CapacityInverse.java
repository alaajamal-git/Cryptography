package part1;
import java.text.DecimalFormat;

public class CapacityInverse {
	static final int TABLE_SIZE = 20;
	static DecimalFormat eightDigits = new DecimalFormat("0.00000000");

	// main function to do calculation
	public static void main(String[] args) {
		double c; // channel capacity
		System.out.print("Channel Capacity");
		System.out.print("\t Probability(p)");
		System.out.println("\t\t Probability(1-p)");
		for (int i = 0; i <= TABLE_SIZE; i++) {
			c = (double) i / TABLE_SIZE;
			System.out.print( " "+c+"\t\t\t");
			
			System.out.print(
					eightDigits.format(capacityInverse(c)) + "\t\t\t");
			System.out
					.println(eightDigits.format(1 - capacityInverse(c))
							);
		}
	} // end of main
		// capacity: the capacity of the binary symmetric channel

	private static double capacity(double p) {
		if (p == 0 || p == 1)
			return 1;
		return 1 + p * log2(p) + (1 - p) * log2(1 - p);
	}

	// capacityInverse: the inverse of the capacity function,
	// uses simple bisection method
	private static double capacityInverse(double c) {
		if (c < 0 || c > 1)
			return -1;
		double lo = 0, hi = 0.5, mid, cMid;
		do {
			mid = (lo + hi) / 2;
			cMid = capacity(mid);
			if (c > cMid)
				hi = mid;
			else
				lo = mid;
		} while (hi - lo > 1.0E-15);
		return mid;
	}

	// log2: Logarithm base 2
	public static double log2(double d) {
		return Math.log(d) / Math.log(2.0);
	}
}