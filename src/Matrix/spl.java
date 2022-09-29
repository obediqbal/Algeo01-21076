package Matrix;
import Matrix.determinant;
import Matrix.OBE;
import Utils.utils;

public class spl {
	public static double[] cramer(double[][] m) {
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

	private static boolean isSole(double[][] m, int row){
		int count = 0;
		for(int i=0; i<m[0].length-1; i++){
			if(m[row][i]!=0) count++;
		}
		if(count>1) return false;
		return true;
	}

	private static boolean hasNoSolution(double[][] m){
		for(int i = 0; i<m.length; i++){
			if(OBE.isRowZero(m, i, 0, m[0].length-1) && m[i][m[0].length-1]!=0) return true;
		}
		return false;
	}

	private static int findBaseVarIdx(double[][] m, int row){
		for(int i = 0; i<m[0].length-1; i++){
			if(m[row][i]!=0) return i;
		}
		return -1;
	}

	public static double[][] eliminasiGauss(double[][] m){
		// Menerima augmented matriks m
		// Menghasilkan solusi SPL dari m
		// Menghasilkan matriks berukuran 0x0 jika tidak ada solusi
		// TODO: jika parametrik
		double[][] nm = new double[m[0].length][m[0].length];
		// utils.fillZero(nm);
		utils.forceCopyMatrix(m, nm);
		// OBE.triangleup(nm);
		OBE.toEchelon(nm, false);
		System.out.println("nm");
		utils.printMatrix(nm);
		System.out.println();

		double[][] res;
		if(hasNoSolution(nm)){
			res = new double[0][0];
			return res;
		}
		res = new double[nm.length-1][1];
		Matriks.fillNaN(res);
		int i, j;
		int var;
		for(i=m.length-1; i>=0; i--){
			var = findBaseVarIdx(nm, i);
			if(var!=-1){
				res[var][0] = nm[i][nm.length-1];
				// System.out.println("res: " + res[var][0] + "; var: " + var);
				j = var+1;
				while(j<nm.length-1){
					if(var!=j && nm[i][j]!=0) res[var][0] -= nm[i][j]*res[j][0];
					j++;
				}
			}
			// TODO: perlu validasi nm[i][i] bukan 0, atau validasi dia pasti segitiga bawah
		}
		return res; 
	}

	public static double[][] eliminasiGaussJordan(double[][] m){
		// GAK BISA PAKE TRIANGLE DOWN EUYY, AKAN KUBIKIN TOESELON()
		double[][] nm = new double[utils.max(m.length, m[0].length)][m[0].length];
		utils.fillZero(nm);
		utils.forceCopyMatrix(m, nm);
		OBE.toEchelon(nm, true);
		// System.out.println("gauss jordan");
		// utils.printMatrix(nm);
		// System.out.println();

		double[][] res;
		if(hasNoSolution(nm)){
			res = new double[0][0];
			return res;
		}

		res = new double[m[0].length-1][1];
		int i;
		for(i=0; i<m.length; i++){
			OBE.multdivrows(nm, false, i, nm[i][i]);
			res[i][0] = nm[i][m[i].length-1];
		}
		return res;
	}
	public static void main(String[] args) {
		int a = 3;
		int b = 4;
		// double[][] m = new double[a][b];
		// utils.readMatrix(m, a, b);
		// double[][] m = {{1, -1, 2, 5}, {2, -2, 4, 10}, {3, -1, 6, 15}}; //parametrik
		// double[][] m ={{2,3,-1,5},{-2,3,-1,1},{4,4,-3,3}}; // punya solusi
        double m[][] = {{1,3,-2,0,2,0,0},{2,6,-5,-2,4,-3,-1},{0,0,5,10,0,15,5},{2,6,0,8,4,18,6}}; // parametrik
		// double[][] m = {{1,2,1,1},{2,2,0,2},{3,4,1,2}}; //Tidak ada solusi
		// utils.printMatrix(m);
		double[][] res = eliminasiGaussJordan(m);
		// double[][] res = eliminasiGauss(m);
		// res = eliminasiGauss(m);
		utils.printMatrix(res);
	}
}