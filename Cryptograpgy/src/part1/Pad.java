package part1;

//Pad.java: generate Postscript code to print a one-time pad
import java.text.DecimalFormat;

public class Pad {
	static DecimalFormat twoDigits = new DecimalFormat("00");
	static char[] let = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z' };
	static int xCoord = 0, yCoord = 0, lineCount = 0;

	public static void main(String[] args) {
		System.out.println("%!PS-Adobe-2.0");
		System.out.println("/Courier-Bold findfont 14 scalefont setfont");
		System.out.println("/onepad {");
		for (int i = 0; i < 20; i++) {
			System.out.println("0 " + yCoord + " moveto");
			System.out.print("(" + twoDigits.format(lineCount) + " ");
			for (int j = 0; j < 50; j++) {
				System.out.print(oneLet());
				if (j % 5 == 4)
					System.out.print(" ");
				if (j % 10 == 9)
					System.out.print(" ");
			}
			System.out.println(") show");
			yCoord -= 15;
			if (lineCount == 9)
				System.out.println();
			if (lineCount % 5 == 4)
				yCoord -= 15;
			lineCount++;
		}
		System.out.println("}def");
		System.out.println("gsave 30 750 translate onepad grestore");
		System.out.println("gsave 30 360 translate onepad grestore");
		System.out.println("10 390 moveto");
		System.out.print("(============t=e=a=r===h=e=r=e=======");
		System.out.println("=======t=e=a=r===h=e=r=e==========) show");
		System.out.println("showpage");
	} // end of main

	private static char oneLet() {
		return let[(int) (Math.random() * 26)];
	}
}