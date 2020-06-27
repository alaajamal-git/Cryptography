package part1;
public class Xor {
	// main function to try out Base class
	public static void main(String[] args) {
		int a = 123456789, b = -987654321;
		printThem(a, b);
		// interchange a and b
		a = a ^ b;
		b = a^b;
		a = a^b;
		printThem(a, b);
		a = 234234234;
		b = -789789789;
		printThem(a, b);
		// interchange a and b
		a ^= b;
		b ^= a;
		a ^= b;
		printThem(a, b);
	} // end of main

	private static void printThem(int a, int b) {
		System.out.println("a: " + a + ", \tb: " + b);
	}
}