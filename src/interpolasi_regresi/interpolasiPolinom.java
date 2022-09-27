package interpolasi_regresi;
import Matrix.*;

public class interpolasiPolinom {
	public static void interpolasipol(double[][] m,double x) {
		double[] xs= new double[m.length];
		spl.cramer(m, xs);
		double result=0;
		for(int i=0;i<xs.length;i++){
			result=result+Math.pow(x, i)*xs[i];
		}
		System.out.println(result);
	}
}
