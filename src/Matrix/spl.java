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
		/*for(int i=0;i<m.length;i++) {
			for(int j=0;j<m[0].length;j++) {
				if (j!=m[0].length-1) {
					a[i][j]=m[i][j];
				}
				else {
					b[i][0]=m[i][j];
				}
			}
		}*/
		utils.augmentedtoMatrix(m, a, b);
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

	private static String[] listDoubleToString(double[] m){
		String[] nm = new String[m.length];
		for(int i = 0; i<m.length; i++){
			if(!Double.isNaN(m[i])) nm[i] = String.valueOf(m[i]);
		}
		return nm;
	}

	private static int findNaN(double[] m){
		for(int i = m.length-1; i>=0; i--){
			if(Double.isNaN(m[i])) return i;
		}
		return -1;
	}

	private static boolean isAllZero(double[] m){
		for(int i = 0; i<m.length; i++){
			if(m[i]!=0) return false;
		}
		return true;
	}

	private static void addList(double[] m, double[] n){
		for(int i = 0; i<m.length; i++){
			m[i] += n[i];
			// System.out.println(m[i] + "  " + n[i]);
		}
	}

	private static double[] copyList(double[] m){
		double[] nm = new double[m.length];
		for(int i = 0; i<m.length; i++){
			nm[i] = m[i];
		}
		return nm;
	}

	private static double[] mulitplyList(double[] m, double k){
		double[] nm = copyList(m);
		for(int i = 0; i<m.length; i++){
			nm[i] *= k;
		}
		return nm;
	}

	private static String resToParametric(double[] res, int var, double[] rawres){
		String s = "";
		if(isAllZero(res)){
			s += "x"+(var+1);
		}
		else{
			for(int i = var+1; i<res.length-1; i++){
				if(res[i]!=0 && Double.isNaN(rawres[i])){
					if(res[i]>0 && !s.isEmpty()){
						s += " + ";
					}
					if(res[i]<0){
						if(!s.isEmpty()) s += " - ";
						else s += "-";
					}
					if(res[i]!=1){
						s += utils.abs(res[i]);
					}
					s += "x"+(i+1);
				}
			}
			if(res[res.length-1]!=0){
				if(res[res.length-1]>0){
					s += " + ";
				}
				else if(res[res.length-1]<0){
					s += " - ";
				}
				s += utils.abs(res[res.length-1]);
			}
		}
		return s;
	}

	public static String[] resListToParametric(double[] res, double[][] m, double[][] nm, boolean parametric){
		if(findNaN(res)!=-1 && parametric){
			int i, j, var;
			boolean found = false;
			double[][] nres = new double[res.length][res.length+1];
			for(i=m.length-1; i>=0; i--){
				var = OBE.findBaseVarIdx(nm, i);
				if(var!=-1){
					for(j=var+1; j<m[0].length-1; j++){ // assign reduced echlon result into new result matrix
						nres[var][j] = -nm[i][j];
						// System.out.print(nres[var][j] + " ");
					}
					// System.out.println();
					nres[var][nres[0].length-1] = nm[i][m[0].length-1];
	
					// System.out.println("\nnres");
					// utils.printMatrix(nres);
					// System.out.println();
	
					for(j=var+1; j<m[0].length-1; j++){
						if(!Double.isNaN(res[j]) && !found){
							found = true;
							// System.out.println(var);
							// System.out.print(nres[var][nres.length] + " + " + nres[var][j] + " * " + res[j]);
							nres[var][nres.length] += nres[var][j]*res[j];
							// System.out.println(" = "+nres[var][nres.length]);
						}
						if(!isAllZero(nres[j]) && nres[var][j]!=0 && Double.isNaN(res[j])){
							// System.out.println();
							// System.out.println((var+1) + " : " + (j+1));
							addList(nres[var], mulitplyList(nres[j], nres[var][j]));
							nres[var][j] = 0;
						}
						// System.out.println();
					}
				}
			}
	
			String[] sres = new String[res.length];
			for(i = 0; i<sres.length; i++){
				if(Double.isNaN(res[i])){
					sres[i] = resToParametric(nres[i], i, res);
				}
				else{
					sres[i] = String.valueOf(res[i]);
				}
			}
			return sres;
		}
		else{
			return listDoubleToString(res); 
		}
	}

	public static double[] eliminasiGauss(double[][] m){
		// Menerima augmented matriks m
		// Menghasilkan solusi SPL dari m
		// Menghasilkan matriks berukuran 0x0 jika tidak ada solusi
		double[][] nm = new double[utils.max(m.length, m[0].length)][m[0].length];

		utils.forceCopyMatrix(m, nm);
		OBE.toEchelon(nm, false);
		// System.out.println("nm");
		// utils.printMatrix(nm);
		// System.out.println();

		double[] res;
		if(hasNoSolution(nm)){
			res = new double[0];
			return res;
		}
		res = new double[nm[0].length-1];
		Matriks.fillNaN(res);
		int i, j;
		int var;
		for(i=nm.length-1; i>=0; i--){
			var = OBE.findBaseVarIdx(nm, i);
			if(var!=-1){
				System.out.println("var: " + var + " i: " + i);
				res[var] = nm[i][m[0].length-1];
				j = var+1;
				while(j<m[0].length-1){
					if(var!=j && nm[i][j]!=0) res[var] -= nm[i][j]*res[j];
					j++;
				}
			}
		}

		return res;
	}
	
	public static String[] eliminasiGauss(double[][] m, boolean parametric){
		double[][] nm = new double[utils.max(m.length, m[0].length)][m[0].length];

		utils.forceCopyMatrix(m, nm);
		OBE.toEchelon(nm, false);

		System.out.println();
		utils.printMatrix(nm);
		System.out.println();

		double[] res = eliminasiGauss(m);
		return resListToParametric(res, m, nm, parametric);
	} 

	public static double[] eliminasiGaussJordan(double[][] m){
		// GAK BISA PAKE TRIANGLE DOWN EUYY, AKAN KUBIKIN TOESELON()
		double[][] nm = new double[utils.max(m.length, m[0].length)][m[0].length];

		utils.fillZero(nm);
		utils.forceCopyMatrix(m, nm);
		OBE.toEchelon(nm, true);
		// System.out.println("gauss jordan");
		// utils.printMatrix(nm);
		// System.out.println();

		double[] res;
		if(hasNoSolution(nm)){
			res = new double[0];
			return res;
		}

		res = new double[m[0].length-1];
		Matriks.fillNaN(res);
		int i;
		int var;
		for(i=0; i<m.length; i++){
			var=OBE.findBaseVarIdx(nm, i);
			if(var!=-1 && isSole(nm, i)){
				if(nm[i][i]!=1 && nm[i][i]!=0) OBE.multdivrows(nm, false, i, nm[i][i]);
				res[var] = nm[i][m[i].length-1];
			}
		}
		return res;
	}

	public static String[] eliminasiGaussJordan(double[][] m, boolean parametric){
		double[][] nm = new double[utils.max(m.length, m[0].length)][m[0].length];

		utils.forceCopyMatrix(m, nm);
		OBE.toEchelon(nm, true);

		System.out.println();
		utils.printMatrix(nm);
		System.out.println();
		
		double[] res = eliminasiGaussJordan(m);
		return resListToParametric(res, m, nm, parametric);
	}
	public static void main(String[] args) {
		int a = 3;
		int b = 4;
		// double[][] m = new double[a][b];
		// utils.readMatrix(m, a, b);
		// double[][] m = {{1, -1, 2, 5}, {2, -2, 4, 10}, {3, -1, 6, 15}}; //parametrik
		// double[][] m ={{2,3,-1,5},{-2,3,-1,1},{4,4,-3,3}}; // punya solusi
		// double[][] m ={{2,3,-1,5},{4,4,-3,3},{-2,3,-1,1}}; // punya solusi
        // double m[][] = {{1,3,-2,0,2,0,0},{2,6,-5,-2,4,-3,-1},{0,0,5,10,0,15,5},{2,6,0,8,4,18,6}}; // parametrik
		// double[][] m = {{1,2,1,1},{2,2,0,2},{3,4,1,2}}; //Tidak ada solusi
		// utils.printMatrix(m);
	
		double[][] m = {{2,0,8,0,8},{0,1,0,4,6},{-4,0,6,0,6},{0,-2,0,3,-1},{2,0,-4,0,-4},{0,1,0,-2,0}};
		// double[][] m ={{1,1,-1,-1,1},{2,5,-7,-5,-2},{2,-1,1,3,4},{5,2,-4,2,6}};
		// double[][] m = {{1,-1,0,0,1,3},{1,1,0,-3,0,6},{2,-1,0,1,-1,5},{-1,2,0,-2,-1,-1}};
		// String[] res = eliminasiGaussJordan(m);
		// utils.printSolusi(res);;
		String[] gres = eliminasiGauss(m, true);
		// double[] dres = eliminasiGauss(m);
		// double[] dres = eliminasiGaussJordan(m);
		System.out.println();
		utils.printSolusiPar(gres);
		String[] gjres = eliminasiGaussJordan(m, true);
		System.out.println();
		utils.printSolusiPar(gjres);
		// double[][] res = eliminasiGauss(m);
		// res = eliminasiGauss(m);
	}
}