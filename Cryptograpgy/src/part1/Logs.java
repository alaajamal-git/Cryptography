package part1;
public class Logs {
	// main function to try out Logs class
	public static void main(String[] args) {
		System.out.println("log base 2 of 1024 = " + log2(1024));
		System.out.println("log base 10 of 1000 = " + log10(1000));
		System.out.println("log 2 = " + Math.log(2));
		System.out.println("1/log 2 = " + 1 / Math.log(2));
		System.out.println("log base 10 of 2 = " + log10(2));
	} // end of main
		// log2: Logarithm base 2

	public static double log2(double d) {
		return Math.log(d) / Math.log(2.0);
	}

	// log10: Logarithm base 10
	public static double log10(double d) {
		return Math.log(d) / Math.log(10.0);
	}
}