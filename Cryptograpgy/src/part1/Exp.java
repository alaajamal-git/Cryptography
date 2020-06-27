package part1;

public class Exp {
	// exp1: uses binary representation of the exponent.
	// Works on binary bits from most significant to least.
	// Variable y only present to give loop invariant:
	// xˆy = z, and y gives the leading bits of Y.
	public static long exp1(long x, long Y[], int k) {
		long y = 0, z = 1;
		int round = 0;
		dump1("Initial. ", x, y, z);
		for (int i = k; i >= 0; i--) {
			y = 2 * y;
			z = z * z;
			dump1("Round: " + (round) + ", ", x, y, z);
			if (Y[i] == 1) {
				y++;
				z = z * x;
				dump1("Round: " + (round++) + ", ", x, y, z);
			}
		}
		return z;
	}

	// dump1: function to spit out debug information
	private static void dump1(String s, long x, long y, long z) {
		System.out.println(s + "x: " + x + ",\ty: " + y + ",\tz: " + z
				+ ",\t(xˆy): " + (exp(x, y)));
	}

	// exp2: uses binary rep of exponent, without constructing it.
	// Works on binary bits from least significant to most.
	// Loop invariant is: z*xˆy = XˆY
	public static long exp2(long X, long Y) {
		long x = X, y = Y, z = 1;
		dump2("Initial. ", x, y, z);
		int round = 1;
		while (y > 0) {
			while (y % 2 == 0) {
				x = x * x;
				y = y / 2;
				dump2("Round: " + (round) + ", ", x, y, z);
			}
			z = z * x;
			y = y - 1;
		}
		dump2("Round: " + (round++) + ", ", x, y, z);
		return z;
	}

	// exp: extra copy of exp2 function without debug code
	public static long exp(long X, long Y) {
		long x = X, y = Y, z = 1;
		while (y > 0) {
			while (y % 2 == 0) {
				x = x * x;
				y = y / 2;
			}
			z = z * x;
			y = y - 1;
		}
		return z;
	}

	// dump2: function to spit out debug information
	private static void dump2(String s, long x, long y, long z) {
		System.out.println(s + "x: " + x + ",\ty: " + y + ",\tz: " + z
				+ ",\tz*(xˆy): " + (z * exp(x, y)));
	}

	public static void main(String[] args) {
		long x = Long.parseLong("2");
		long y = Long.parseLong("72");
		// Convert y to array Y of bits
		long Y[] = new long[50];
		int k = 0;
		long yt = y;
		while (yt > 0) {
			Y[k++] = yt % 2;
			yt = yt / 2;
		}
		k--;
		System.out.println(k);

		System.out.println("Try first exponentiation algorithm ...");
		long z1 = Exp.exp1(x, Y, k);
		System.out.println("Method 1: exp1(" + x + ", " + y + ") = " + z1
				+ "\n");
		System.out.println("Try second exponentiation algorithm ...");
		long z2 = Exp.exp2(x, y);
		System.out.println("Method 2: exp2(" + x + ", " + y + ") = " + z2);
	}
}