package part1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class mlt_add_modp {
	static File f;
	static PrintWriter out;
	public mlt_add_modp() throws IOException {
		  f = new File(this.getClass().getResource("").getPath()+"a.txt");
		if(!f.exists())
		System.out.println(f.createNewFile());
	}
	
	private static int order=0;
	public static void main(String [] args) throws IOException{
         new mlt_add_modp();
		 out=new PrintWriter(new FileWriter(f), true);
	//	addmod(8);
		out.println();
		multmod(8);
		out.println();
        
		//primpow(256);
		out.println(6^3^2^1);

}
	
	
 public static long pow(int x, int y ){
	 
	 
		long result=1;

	 for(int i=0;i<y;i++)
		 result*=x;
		 if(result>0)
      return result;
		 else
			 return 0;
	 
 }
 
 
 public static void addmod(int p){
	 
	 for(int i=0;i<p;i++){
								
		for(int k=0;k<p;k++){
						out.print((i+k)%p+"    ");
						
						
						
					}
		out.println();
		}
	 
	 
 }
 public static void multmod(int p){
	 int r;
	 
	 String GF="GF["+p+"]={ ";
	 for(int i=1;i<p;i++){
		for(int k=1;k<p;k++){
					r	=i*k%p;
					if(r==1) {GF+=i+" ";
					order++;
					out.print("("+r+")    ");

					}
					else		out.print(" "+r+"    ");
						
						
						
					}
		out.println();
		}
		out.println(GF+"}");

		out.println("order= "+order);

 }
 public static void primpow(int p){
	 
	 for(int i=1;i<p;i++){
	 for(int j=1;j<p;j++){
			
			
			out.print(pow(i, j)%p+"   ");
			
			
			
		}
		out.println();
		
	}

	 
 }
	
	
}
