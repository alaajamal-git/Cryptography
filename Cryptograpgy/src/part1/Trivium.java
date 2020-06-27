package part1;

import java.util.Arrays;

public class Trivium {
	
	private static int[] IV=new int[80];
	private static int[] A_Register=new int[93];
	private static int[] B_Register=new int[84];
	private static int[] C_Register=new int[111];
	private static	int [] Ki=new int[80];
	private static String plaintext="a";
	private static int[] Oj;
	private static int r=7;
	public static void main(String [] args){
		int lengthOfCycle=(A_Register.length+B_Register.length+C_Register.length)*4;

		initiate_Register_A();
		initiate_Register_B();
		initiate_Register_C();
		System.out.println("%%%%%%%%%%%%%%%%%%%% Initial Registers %%%%%%%%%%%%%%%%%%%%%%%%%%");

		System.out.println(Arrays.toString(A_Register));
		System.out.println(Arrays.toString(B_Register));
		System.out.println(Arrays.toString(C_Register));
		
		System.out.println("%%%%%%%%%%%%%%%%%%%%  Registers after warp_up %%%%%%%%%%%%%%%%%%%%%%%%%%");
		warp_up(lengthOfCycle,null);
		
		

		System.out.println("%%%%%%%%%%%%%%%%%%%%  Key Stream %%%%%%%%%%%%%%%%%%%%%%%%%%");
		startPruducting(A_Register,B_Register,C_Register);
		System.out.println(Arrays.toString(Ki));
				
	}
		
	private static void startPruducting(int[] A_Register, int[] B_Register,
			int[] C_Register) {
			warp_up(80, Ki);	
	} 

	private static void warp_up(int lengthOfCycle,int[] Si) {

		int temp_a_69;
		int temp_b_78;
		int temp_c_87;

		int feedback_A=0;
		int feedback_B=0;
		int feedback_C=0;

		for(int j=0;j<lengthOfCycle;j++){
			int a_1 =	(A_Register[90]&A_Register[91])^A_Register[92]^A_Register[65];
			int b_1 =	(B_Register[81]&B_Register[82])^B_Register[83]^B_Register[68];
			int c_1 =	(C_Register[108]&C_Register[109])^C_Register[110]^C_Register[65];
            temp_a_69 = A_Register[68];
            temp_b_78 = B_Register[77];
            temp_c_87 = C_Register[86];
			A_Register =  shift(A_Register);
			B_Register =  shift(B_Register);
			C_Register =  shift(C_Register);
			feedback_A=temp_a_69^c_1;
			feedback_B=temp_b_78^a_1;
			feedback_C=temp_c_87^b_1;
			A_Register[0]=feedback_A;
			B_Register[0]=feedback_B;
			C_Register[0]=feedback_C;
			if(Si!=null){
				Ki[j]=feedback_A^feedback_B^feedback_C;
			}
		}
		if(Si==null){
		System.out.println(Arrays.toString(A_Register));
		System.out.println(Arrays.toString(B_Register));
		System.out.println(Arrays.toString(C_Register));
	
		
		}
		
		
		
		
		
	}

	private static int[] shift(int[] _Register) {

		int[] shifted_array=new int[_Register.length];
		int length=_Register.length;
		for(int i=0;i<length-1;i++){
			shifted_array[i+1]=_Register[i];
		}

		
		return shifted_array;
	}

	private static void initiate_Register_C() {

		C_Register[C_Register.length-1]=1;
		C_Register[C_Register.length-2]=1;
	    C_Register[C_Register.length-3]=1;


	}

	private static void initiate_Register_A() {

		for(int i=0;i<80;i++){
			
			A_Register[i]=((int)(Math.random()*123))%2;
			
		}
			}
	private static void initiate_Register_B() {

		for(int i=0;i<80;i++){
			
			B_Register[i]=((int)(Math.random()*123))%2;
			
		}
			}

}
