package part2;
//Table.java: the code table for Shannon’s random code
import java.util.Random;
public class Table {
	public Word[] t; // the only data field in this class
	// Table: constructor. Allocate expN = 2**N random words

	public Table(Random ranNumGen) {
		t = new Word[Shannon.expN];
		for (int i = 0; i < Shannon.expN; i++)
			t[i] = new Word(ranNumGen);
	}

	// search: search Table t for an input word w
	public int search(Word w) {
		int comp;
		int minComp = Shannon.CWS * 8 + 1;
		int minCompCount = -100000000;
		int index = -200000000;
		for (int i = 0; i < Shannon.expN; i++) {
			comp = compare(t[i], w); // count bits that differ
			if (comp == minComp){ // an old minimum
				minCompCount++;

			}
			if (comp < minComp) { // a new minimum
				index = i;
				minComp = comp;
				minCompCount = 1;
			}
		}
		if (minCompCount == 1)
			return index; // unique minimum
		else
			return -minCompCount; // several different minimums
	}

	// compare: return count of differences of bits of input words
	private int compare(Word u, Word v) {
		int diffs = 0;
		for (int i = 0; i < Shannon.CWS; i++)
			diffs += countDiffs(u.w[i], v.w[i]);
		return diffs;
	}

	// countDiffs: return count of differences of bits of input bytes
	private int countDiffs(byte b1, byte b2) {
		byte b = (byte) (b1^b2); // xor gives 1 where bytes differ
		//System.out.println("********"+b+"   "+Word.c[b + 128]);
		return Word.c[b + 128]; // table lookup gives # of 1 bits
	}

	// getWord: fetch a word at a given index: part of simulation
	public Word getWord(int index) {
		return t[index];
	}

	// printTable: print the whole table, debug only
	public void printTable() {
		for (int i = 0; i < Shannon.expN; i++) {
			System.out.print("Entry " + i + ": ");
		//	t[i].printWord();
		}
	}
}