package Matrix;
import Matrix.determinant;
import Utils.utils;

public class spl {
	public static void cramer(double[][] m) {
		double[][] a = new double[m.length][m[0].length-1];
		double[][] b = new double[m.length][1];
		double[][] a1 = new double[m.length][m[0].length-1];
		double det0,det1;
		for(int i=0;i<m.length;i++) {
			for(int j=0;j<m[0].length;j++) {
				if (j!=m[0].length-1) {
					a[i][j]=m[i][j];
				}
				else {
					b[i][0]=m[i][j];
				}
			}
		}
		det0 = determinant.ekspansiKofaktor(a);
		
		for(int j=0;j<a.length;j++){
			spl.copyMatrix(a,a1);
			spl.insertCol(a1, b, j);
			det1=determinant.ekspansiKofaktor(a1);
			System.out.print("x ");
			System.out.print(j);
			System.out.print(" ");
			System.out.print(det1/det0);
			System.out.print("\n");
		}
	}
	
	public static void copyMatrix(double[][] min, double[][] mout) {
		for(int i=0; i<min.length;i++){
			for(int j=0; j<min[0].length;j++){
				mout[i][j]=min[i][j];
			}
		}	
	}
	
	public static void insertCol(double[][] a, double[][] b, int k) {
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				if(j==k){
					a[i][j]=b[i][0];
				}
			}
		}
	}
}
