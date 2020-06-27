package part1;

//Cryptogram: create a cryptogram as in a newspaper
import java.io.*;

public class Cryptogram {
	private char[] alf = new char[26]; // translation vector

	public Cryptogram() {
		for (int i = 0; i < alf.length; i++)
			alf[i] = (char) ('A' + i);
		randomize();
	}

	private int rand(int r, int s) { // r <= rand <= s
		return (int) ((s - r + 1) * Math.random() + r);
	}

	private void randomize() {
		for (int i = 0; i < alf.length - 1; i++) {
			// Note: for a random permutation, replace "i+1" by "i" below
			// However, we want no letter to remain in its original spot
			int ind = rand(i + 1, alf.length - 1);
			char t = alf[i];
			alf[i] = alf[ind];
			alf[ind] = t;
		}
	}

	public void printArray() {
		System.out.print("Alphabet: ");
		for (int i = 0; i < alf.length; i++)
			System.out.print((char) ('A' + i));
		System.out.println();
		System.out.print("Translated to: ");
		for (int i = 0; i < alf.length; i++)
			System.out.print(alf[i]);
		System.out.println("\n");
	}

	// getNextChar: fetch next char.
	public char getNextChar() {
		char ch = ' '; // = ' ' to keep compiler happy
		try {
			ch = (char) System.in.read();
		} catch (IOException e) {
			System.out.println("Exception reading character");
		}
		return ch;
	}

	public void createCryptogram() {
		char ch;
		while ((byte) (ch = getNextChar()) != -1) {
			if (Character.isUpperCase(ch))
				ch = alf[ch - 'A'];
			System.out.print(ch);
		}
	}

	// main: for cryptogram program
	public static void main(String[] args) {
		Cryptogram crypto = new Cryptogram();
		crypto.printArray();
		crypto.createCryptogram();
	}
}