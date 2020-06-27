package part1;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Fermat {

    static ServerSocket socket;
    static Socket client;
    static PrintWriter  out;
	public static void main(String[] args) throws IOException {
		socket=new ServerSocket(80);
		while(true){
		
		client=socket.accept();
		out=new PrintWriter(client.getOutputStream(),true);
		long p = 13; // the fixed prime base
		out.println("<table border nosave >");
		out.println("<tr><th>p</th><th>a</th><th></th>");
		for (int col = 1; col < p; col++)
			out.print("<th>a<sup>" + col + "</sup></th>");
		out.println("</tr><tr colspan=" + (p + 2) + "></tr>");
		for (long row = 2; row < p; row++) {
			out.print("<tr align=right><td>" + p);
			out.print("</td><td>" + row + "</td><td></td>");
			boolean firstCycle = true;
			for (long col = 1; col < p; col++) {
				if (firstCycle)
					out.print("<td><b><font color=FF0000>"
							+ pow(row, col, p) + "</font></b></td>");
				else
					out.print("<td>" + pow(row, col, p) + "</td>");
				if (firstCycle)
					if (pow(row, col, p) == 1)
						firstCycle = false;
			}
			out.println("</tr>");
		}
		out.println("</table>");}
	} 
	// end of main
	// pow: calculate xˆy mod p, without overflowing
	// (Algorithm from Gries, The Science of Programming, p. 240

	public static long pow(long x, long y, long p) {
		long z = 1;
		while (y > 0) {
			while (y % 2 == 0) {
				x = (x * x) % p;
				y = y / 2;
			}
			z = (z * x) % p;
			y = y - 1;
		}
		return z;
	}
}