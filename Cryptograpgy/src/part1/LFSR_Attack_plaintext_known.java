package part1;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class LFSR_Attack_plaintext_known {
	
	public static void main(String[] args)
    {
        double [][] values = {{0,1,1,0}, {1,0,1,1}, {0,1,0,1},{1,0,1,0}};
        double [] rhs = { 1,0,1,1 };

        RealMatrix a = new Array2DRowRealMatrix(values);
        System.out.println("a matrix: " + a);
        DecompositionSolver solver = new LUDecomposition(a).getSolver();

        RealVector b = new ArrayRealVector(rhs);
        RealVector x = solver.solve(b);
        System.out.println("solution x: " + x);;
        double [][] values2 = {{1,1,1,0}, {1,1,1,1}, {0,1,1,1},{0,0,1,1}};
        double [] rhs2 = { 1,0,0,0 };
        RealMatrix a2 = new Array2DRowRealMatrix(values2);
        System.out.println("a matrix: " + a);
        DecompositionSolver solver2 = new LUDecomposition(a2).getSolver();

        RealVector b2 = new ArrayRealVector(rhs2);
        RealVector x2 = solver2.solve(b2);
        System.out.println("solution x: " + x2);;

    }

}
