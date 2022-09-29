package Matrix;
import Matrix.determinant;
import Matrix.OBE;
import Utils.utils;

public class spl {
	public static double[] cramer(double[][] m) {
		// prekondisi: det(m)!=0
		double[][] a = new double[m.length][m[0].length-1];
		double[][] b = new double[m.length][1];
		double[][] a1 = new double[m.length][m[0].length-1];
		double[] x = new double[m.length];
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
			utils.copyMatrix(a,a1);
			spl.insertCol(a1, b, j);
			det1=determinant.ekspansiKofaktor(a1);
			x[j]=det1/det0;
		}
		return x;
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

	/*public static double[][] eliminasiGauss(double[][] m){
		// Menerima augmented matriks m
		// Menghasilkan solusi SPL dari m
		double[][] nm = new double[m[0].length][m[0].length];
		utils.fillZero(nm);
		utils.forceCopyMatrix(m, nm);
		utils.printMatrix(nm);
		OBE.triangleup(nm);
		System.out.println();
		utils.printMatrix(nm);
		System.out.println();

		double[][] res = new double[m.length][1];
		int i, j;
		for(i=m.length-1; i>=0; i--){
			// if(utils.isRowZero(m, i)) continue;
			OBE.multdivrows(nm, false, i, nm[i][i]);
			j = 0;
			res[i][0] = nm[i][nm.length-1];
			while(j<nm.length-1){
				if(i!=j) res[i][0] -= nm[i][j]*res[j][0];
				j++;
			}
			// perlu validasi nm[i][i] bukan 0, atau validasi dia pasti segitiga bawah
		}
		utils.printMatrix(nm);

		return res;
	}

	public static void eliminasiGaussJordan(double[][] m){
 
	}
	public static void main(String[] args) {
		int a = 3;
		int b = 4;
		// double[][] m = new double[a][b];
		// utils.readMatrix(m, a, b);
		double[][] m = {{1, -1, 2, 5}, {2, -2, 4, 10}, {3, -1, 6, 15}};
		// utils.printMatrix(m);
		double[][] res = new double[b][1];
		res = eliminasiGauss(m);
		utils.printMatrix(res);
	}*/
}
