package part1;

public class exponentiation_algorithm {
	public static void main(String[] args) {

		//1011=11
		int[] N={1,1,0,1};
		int k=N.length-1;
		System.out.println(exp(2,N , k));
	}
	static int exp(int x, int Y[], int k) {
		// Y = Y[k] Y[k-1] ... Y[1] Y[0] (in binary)
		int y = 0, z = 1;
		for (int i = k; i >= 0; i--) {
			y = 2*y;
			z = z*z;
			if (Y[i] == 1) {
			y++;
			z = z*x;
			
			}
			System.out.println(y+"---"+z);;

			}
		System.out.println(y);;
			return z;
			}
}
