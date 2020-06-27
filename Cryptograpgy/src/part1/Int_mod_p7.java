package part1;

public class Int_mod_p7 {

	
	public static void main(String[] args){
		int inverse1 = 0;
		int inverse2 = 0;

		for(int x=1;x<10;x++){
			for(int y=1;y<10;y++){
				
				System.out.print((x*y)%7!=0?(x*y)%7:"-");
				System.out.print("\t");
				if(inverse1==-1||inverse1==0)
				inverse1=(x+3)%7==0?x:-1; 
				if(inverse2==-1||inverse2==0)
					inverse2=(x+5)%7==0?x:-1; 

			}
			System.out.println();
			
			
		}
		
		System.out.println("INVERSE OF 3 : "+inverse1);
		System.out.print("INVERSE OF 5 : "+inverse2);

	}
	
	
	
	
}
