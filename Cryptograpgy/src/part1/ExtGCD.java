package part1;
public final class ExtGCD {
	public static long[] GCD(long x, long y) { // assume not 0 or neg
		long[] u = new long[3];
		long[] v = new long[3];
		long[] t = new long[3];
		// at all stages, if w is any of the 3 vectors u, v or t, then
		// x*w[0] + y*w[1] = w[2] (this is verified by "check" below)
		// u = (1, 0, u); v = (0, 1, v);
		u[0] = 1;
		u[1] = 0;
		u[2] = x;
		v[0] = 0;
		v[1] = 1;
		v[2] = y;
		System.out.println("q\tu[0]\tu[1]\tu[2]\tv[0]\tv[1]\tv[2]");
		while (v[2] != 0) {
			long q = u[2] / v[2];
			// t = u - v*q;
			t[0] = u[0] - v[0] * q;
			t[1] = u[1] - v[1] * q;
			t[2] = u[2] - v[2] * q;
			check(x, y, t);
			// u = v;
			u[0] = v[0];
			u[1] = v[1];
			u[2] = v[2];
			check(x, y, u);
			// v = t;
			v[0] = t[0];
			v[1] = t[1];
			v[2] = t[2];
			check(x, y, v);
			System.out.println(q + "\t" + u[0] + "\t" + u[1] + "\t" + u[2]
					+ "\t" + v[0] + "\t" + v[1] + "\t" + v[2]);
		}
		return u;
	}

	public static void check(long x, long y, long[] w) {
		if (x * w[0] + y * w[1] != w[2]) {
			System.out.println("*** Check fails: " + x + " " + y);
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		long[] u = new long[3];
		long x = Long.parseLong("819");
		long y = Long.parseLong("460");
		u = ExtGCD.GCD(x, y);
		System.out.println("\ngcd(" + x + ", " + y + ") = " + u[2]);
		System.out.println("(" + u[0] + ")*" + x + " + " + "(" + u[1] + ")*"
				+ y + " = " + u[2]);
	}
}