package part1;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Encrypt_Dec {

	private static final long S0 = 12345;
	private static final long A = 1103515245;
	private static final long B = 12345;
	private static final double m = Math.pow(2, 12);
	private static int X[];
	private static int Y[];
	private static int D[];
	private static String MSG[];
	private static String message = "secure against known-plaintext attacks, an LFSR should never be"
			+ " used by itself as a keystream generator Nevertheless, LFSRs a";
	private static byte[] MSGBytes;
	public static void main(String[] a) throws UnsupportedEncodingException {

		byte MESSAGE[] = message.getBytes();
		final int msgbitLong = MESSAGE.length * 7;// each byte represent by
													// 7-bits in ASCII
		double S[] = new double[1000];
		S = prepareKeyStream(S0);
		X = new int[msgbitLong];
		X = preparePlainTextStream(MESSAGE);
		Y = new int[msgbitLong];
		Y = encrypte(S, X);
		System.out.println("Stream Key: " + Arrays.toString(S));
		System.out.println("Message :" + message);
		System.out.println("Message bits: " + Arrays.toString(X));
		System.out.println("Encrypt bits: " + Arrays.toString(Y));
		System.out.println("Decrypt bits: " + Arrays.toString(decrypt(S, Y)));
		String str = new String(prepareDeryptPlaintext(D), "UTF-8");
		System.out.println("Message :" + str);
	}

	private static byte[] prepareDeryptPlaintext(int[] D) {
		MSG = new String[125];
		 MSGBytes = new byte[125];
		int counter = 0;

		for (int j = 0; j < 125; j++) {

			String str = "";

			for (int k = 0; k <= 6; k++) {
				str += D[counter];
				counter++;
			}
			MSG[j] = str;

			MSGBytes[j] = Byte.parseByte(str, 2);

		}
		System.out.println("Message bits: " + Arrays.toString(MSG));

		return MSGBytes;

	}

	private static int[] decrypt(double[] K, int[] Y2) {
		D = new int[875];
		for (int j = 0; j < Y2.length; j++) {

			D[j] = (int) ((K[j] + Y2[j]) % 2);

		}
		return D;
	}

	private static int[] preparePlainTextStream(byte[] mESSAGE2) {
		int counter = 0;

		for (int j = 0; j < 125; j++) {

			byte character = mESSAGE2[j];
			for (int k = 6; k >= 0; k--) {
				X[counter] = (character >> k) & 1;
				counter++;
			}

		}

		return X;
	}

	private static double[] prepareKeyStream(long s0) {
		double S[] = new double[1000];
		double Si = s0;
		for (int i = 0; i < 1000; i++) {

			Si = (A * Si + B) % (m);
			S[i] = Si;

		}

		return S;
	}

	public static int[] encrypte(double[] keyStream, int[] MessageStram) {

		for (int z = 0; z < MessageStram.length; z++)
			Y[z] = (int) ((X[z] + keyStream[z]) % 2);

		return Y;

	}

}
