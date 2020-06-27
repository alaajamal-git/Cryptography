package part1;
import java.util.Arrays;

public class Gsimple {


	public static void main(String[] args) {
		long[] u = new long[3];
		long x = Long.parseLong("333883");
		long y = Long.parseLong("19765599");
		u = Gsimple.gcd(x, y);
		System.out.println("gcd(" + x + ", " + y + ") = " + u[2]);
		System.out.println("(" + u[0] + ")*" + x + " + " + "(" + u[1] + ")*"
				+ y + " = " + u[2]);
	}
	
	
	
	public static long[] gcd(long x, long y) {
		int j=0;
		long[] u = { 1, 0, x }, v = { 0, 1, y }, t = new long[3];
		while (v[2] != 0) {
			long q = u[2] / v[2];
			System.out.println("while loop : "+j+"----------------------- q="+q);
			for (int i = 0; i < 3; i++) {
				System.out.println("for loop :"+i);
				t[i] = u[i] - v[i] * q;
				u[i] = v[i];
				v[i] = t[i];
				System.out.println("U :"+Arrays.toString(u));
				System.out.println("V :"+Arrays.toString(v));
				System.out.println("T :"+Arrays.toString(t));

			}
			j++;
		}
		return u;
	}
}