package part1;

public class GaliosGFIntegers {

	public static void main(String[] args) {

		// first you have note that primitive generator p(x) of GF(2,m) must
		// contain of m-bits
		// GF(2,3) generator p(x)=x^3+x^2+1 primitive 3-bits (13)
		// GF(2,3) generator p(x)=x^3+x+1 primitive 3-bits (11)
		// GF(2,2) generator p(x)=x^2+x+1 primitive 2-bits (7)
		// GF(2,4) generator p(x)=x^4+x+1 primitive 4-bits (19)
		// first check the result of multiplication then if result>generator
		// shift generator to right by 1 position
		// and repeat test if still bigger than new generator shift again if not
		// do an XOR between shifted generator and
		// multiplication result and get result
		PrepareGFpmTable(2,2,7);

		System.out.println(test(culculateAND(7, 5), 2));
	}

	public static void PrepareGFpmTable(int p, int m, int generator) {
		int base = (int) Math.pow(p, m);
		int code;
		int[] element = new int[p];
		int shiftedGenerator = generator;
		element = prepareElements(base, generator);
		int x = 0;
		//if add mod i,j from 0
		for (int i = 1; i < base; i++) {
			for (int j = 1; j < base; j++) {
				code = culculateAND(element[i], element[j]);
				//to compute add mod n
			//	code = culculateXOR(element[i], element[j]);

				int location = test(code, p);
				while (true) {
					x = location - m;
					if (x > 0) {
						shiftedGenerator = generator << x - 1;
						code = shiftedGenerator ^ code;
						location = test(code, p);
					} else {
						break;

					}
				}
				System.out.print("(" + code + ")");

			}
			System.out.println();

			System.out.println("***************************");

		}
	}

	private static int culculateAND(int i, int j) {
		int abit = 0;
		int temp = 0;
		int factorBit = 1;
		int mostrightbit;
		while (j != 0 && i != 0) {
			mostrightbit = j & factorBit;
			temp = temp ^ ((i * mostrightbit) << abit);

			j = j >> 1;
			abit++;
		}

		return temp;
	}

	private static int test(int temp, int p) {
		int location = 1;
		if (temp != 0)
			location = (int) (Math.log(temp) / Math.log(p)) + 1;

		return location;
	}

	private static int[] prepareElements(int pm, int g) {

		int elements[] = new int[pm];
		//if add mod i from 0

		for (int i = 1; i < pm; i++) {
			elements[i] = i % g;

		}

		return elements;
	}
	
	private static int culculateXOR(int i, int j) {
		

		return i^j;
	}
	
	

}
