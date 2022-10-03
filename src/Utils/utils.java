package Utils;
import java.util.Scanner;
import Matrix.*;
import interpolasi_bicubic.Bicubic_Interpolator;
import interpolasi_regresi.interpolasiPolinom;

public class utils {
	public static void readMatrix(double[][] m,int n,int ma) {
		Scanner obj = new Scanner(System.in);
		System.out.println("masukkan matrix: ");
		for(int i=0; i<n;i++){
            for(int j=0;j<ma;j++){
                m[i][j]=obj.nextDouble();
            }
        }
		//obj.close();
	}
	public static double[][] makeMatrix(){
		Scanner obj=new Scanner(System.in);
		System.out.println("masukkan banyak baris dan kolom matrix: ");
		int n=obj.nextInt();
		int ma=obj.nextInt();
		double[][] m=new double[n][ma];
		//obj.close();
		readMatrix(m, n, ma);
		return m;
	}
	
	public static void printMatrix(double[][] m) {
		for(int i=0; i< m.length;i++){
            for(int j=0;j< m[0].length;j++){
                if(j<m[0].length-1){
                    System.out.print(m[i][j]+" ");
                }
                else{
                    System.out.print(m[i][j]+"\n");
                }
            }
        }
	}
	
	public static void forceCopyMatrix(double[][] min, double[][] mout){
		for(int i=0; i<min.length;i++){
			for(int j=0; j<min[0].length;j++){
				mout[i][j]=min[i][j];
			}
		}	
	}

	public static void copyMatrix(double[][] min, double[][] mout) {
		if(isSameSize(min, mout)){
			for(int i=0; i<min.length;i++){
				for(int j=0; j<min[0].length;j++){
					mout[i][j]=min[i][j];
				}
			}	
		}

	}

	public static void fillZero(double[][] m){
		for(int i = 0; i<m.length; i++){
			for(int j = 0; j<m[0].length; j++){
				m[i][j] = 0;
			}
		}
	}

	public static int max(int a, int b){
		if(a>b) return a;
		else return b;
	}

	public static double abs(double a){
		if(a<0) return -a;
		return a;
	}
	
	public static void getMenu() {
		Scanner obj = new Scanner(System.in);
		System.out.println("MENU\n 1. Sistem Persamaaan Linier\n 2. Determinan\n 3. Matriks balikan\n 4. Interpolasi Polinom\n 5. Interpolasi Bicubic\n 6. Regresi linier berganda\n 7. Keluar\n");
		int menu=obj.nextInt();
		if(menu==1){
			splMenu();
		}
		else if(menu==2){
			determinantMenu();
		}
		else if(menu==3){
			double[][] m=null;
			System.out.println("input dari keyboard(1) atau file(2): ");
			int fileorKey=obj.nextInt();
			while(fileorKey!=1 && fileorKey !=2){
				System.out.println("input salah, ulangi input: ");
				fileorKey=obj.nextInt();
			}
			if(fileorKey==1){
				m=makeMatrix();
			}
			else if (fileorKey==2){
				System.out.println("masukkan nama file beserta path nya: ");
				String fileName = obj.nextLine();
				fileName = obj.nextLine();
				m = txtscanner.getMatrixFile(fileName);
			}
			//System.out.println("1");
			//printMatrix(m);
			m=Matriks.inverse(m);
			//System.out.println("2");
			printMatrix(m);
			System.out.println("simpan file?\n simpan(1)\n tidak(2)");
			int saveFile = obj.nextInt();
			while(saveFile!=1 && saveFile !=2){
				System.out.println("input salah, ulangi input: ");
				saveFile=obj.nextInt();
			}
			if(saveFile==1){
				System.out.println("masukkan nama file beserta path nya: ");
				String saveFileName = obj.nextLine();
				saveFileName = obj.nextLine();
				txtwriter.writeMatrix(m, saveFileName);
			}
			else if (saveFile==2){

			}
		}
		else if(menu==4){
			System.out.println("masukkan nama file beserta path nya: ");
			String file = obj.nextLine();
			file = obj.nextLine();
			interpolasiPolinom.interpolasipol(file);
		}
		else if(menu==5) {
			System.out.println("masukkan nama file beserta path nya: ");
			String file = obj.nextLine();
			file = obj.nextLine();
			double[][] m=txtscanner.getMatrixFile(file);
			
			System.out.println("Masukkan titik");
			double x = obj.nextDouble();
			double y = obj.nextDouble();
			
			Bicubic_Interpolator bic = new Bicubic_Interpolator(m);
			System.out.println(bic.solver(x, y));
			//interpolasiPolinom.
		}
		else if(menu==6) {
			System.out.println("masukkan nama file beserta path nya: ");
			String file = obj.nextLine();
			file = obj.nextLine();
			double[][] m=txtscanner.getMatrixFile(file);
			interpolasi_regresi.Regresi.regresi(m);
		}
		else if(menu==7) {
			//System.out.println("Keluar..");
			Global.isRun = false;
		}
	}
	
	public static void splMenu() {
		Scanner obj = new Scanner(System.in);
		System.out.println("1. Metode eliminasi Gauss\n 2. Metode eliminasi Gauss-Jordan\n 3. Metode matriks balikan\n 4. Kaidah Cramer\n");
		int menu=obj.nextInt();
		if(menu==1){
			double[][] m=null;
			System.out.println("input dari keyboard(1) atau file(2): ");
			int fileorKey=obj.nextInt();
			while(fileorKey!=1 && fileorKey !=2){
				System.out.println("input salah, ulangi input: ");
				fileorKey=obj.nextInt();
			}
			if(fileorKey==1){
				m=makeMatrix();
			}
			else if (fileorKey==2){
				System.out.println("masukkan nama file beserta path nya: ");
				String fileName = obj.nextLine();
				fileName = obj.nextLine();
				m = txtscanner.getMatrixFile(fileName);
			}
			String[] solusi = spl.eliminasiGauss(m,true);
			printMatrix(m);
			//System.out.println(solusi[0]);
			printSolusiPar(solusi);
		}
		else if(menu==2){
			double[][] m=null;
			System.out.println("input dari keyboard(1) atau file(2): ");
			int fileorKey=obj.nextInt();
			while(fileorKey!=1 && fileorKey !=2){
				System.out.println("input salah, ulangi input: ");
				fileorKey=obj.nextInt();
			}
			if(fileorKey==1){
				m=makeMatrix();
			}
			else if (fileorKey==2){
				System.out.println("masukkan nama file beserta path nya: ");
				String fileName = obj.nextLine();
				fileName = obj.nextLine();
				m = txtscanner.getMatrixFile(fileName);
			}
			String[] solusi = spl.eliminasiGaussJordan(m,true);
			printMatrix(m);
			printSolusiPar(solusi);
		}
		else if(menu==3){
			double[][] m=null;
			System.out.println("input dari keyboard(1) atau file(2): ");
			int fileorKey=obj.nextInt();
			while(fileorKey!=1 && fileorKey !=2){
				System.out.println("input salah, ulangi input: ");
				fileorKey=obj.nextInt();
			}
			if(fileorKey==1){
				m=makeMatrix();
			}
			else if (fileorKey==2){
				System.out.println("masukkan nama file beserta path nya: ");
				String fileName = obj.nextLine();
				fileName = obj.nextLine();
				m = txtscanner.getMatrixFile(fileName);
			}
			double[] solusi= spl.inverseMatrixMethod(m);
			printSolusi(solusi);
		}
		else if(menu==4){
			double[][] m=null;
			System.out.println("input dari keyboard(1) atau file(2): ");
			int fileorKey=obj.nextInt();
			while(fileorKey!=1 && fileorKey !=2){
				System.out.println("input salah, ulangi input: ");
				fileorKey=obj.nextInt();
			}
			if(fileorKey==1){
				m=makeMatrix();
			}
			else if (fileorKey==2){
				System.out.println("masukkan nama file beserta path nya: ");
				String fileName = obj.nextLine();
				fileName = obj.nextLine();
				m = txtscanner.getMatrixFile(fileName);
			}
			double[] solusi =spl.cramer(m);
			printSolusi(solusi);
		}
		
	}

	public static void determinantMenu(){
		Scanner obj = new Scanner(System.in);
		System.out.println("1. Metode ekspansi kofaktor\n 2. Metode reduksi baris\n");
		int menu=obj.nextInt();
		if(menu==1) {
			double[][] m=null;
			System.out.println("input dari keyboard(1) atau file(2): ");
			int fileorKey=obj.nextInt();
			while(fileorKey!=1 && fileorKey !=2){
				System.out.println("input salah, ulangi input: ");
				fileorKey=obj.nextInt();
			}
			if(fileorKey==1){
				m=makeMatrix();
			}
			else if (fileorKey==2){
				System.out.println("masukkan nama file beserta path nya: ");
				String fileName = obj.nextLine();
				fileName = obj.nextLine();
				m = txtscanner.getMatrixFile(fileName);
			}
			System.out.print("determinant: ");
			System.out.print(determinant.ekspansiKofaktor(m));
			System.out.print("\n");
		}
		else if(menu==2) {
			double[][] m=null;
			System.out.println("input dari keyboard(1) atau file(2): ");
			int fileorKey=obj.nextInt();
			while(fileorKey!=1 && fileorKey !=2){
				System.out.println("input salah, ulangi input: ");
				fileorKey=obj.nextInt();
			}
			if(fileorKey==1){
				m=makeMatrix();
			}
			else if (fileorKey==2){
				System.out.println("masukkan nama file beserta path nya: ");
				String fileName = obj.nextLine();
				fileName = obj.nextLine();
				m = txtscanner.getMatrixFile(fileName);
			}
			System.out.print("determinant: ");
			System.out.print(determinant.reduksiBaris(m));
			System.out.print("\n");
		}
	}

	public static boolean isSameSize(double[][] m1, double[][] m2){
		return (m1.length==m2.length &&  m1[0].length==m2[0].length);
	}

	public static void printSolusi(double[] x){
		if(x.length>0) {
			for(int i=0;i<x.length;i++){
				System.out.print("x");
				System.out.print(i+1 + ":");
				System.out.print(x[i]+"\n");
			}
		}
		else {
			System.out.println("tidak ada solusi");
		}
	}

	public static void printSolusiPar(String[] x){
		if(x.length>0) {
			for(int i=0;i<x.length;i++){
				System.out.print("x");
				System.out.print(i+1 + ":");
				System.out.print(x[i]+"\n");
			}
		}
		else {
			System.out.println("tidak ada solusi");
		}
		
	}
	public static boolean isSquare(double[][] m){
		return m.length==m[0].length;
	}
	public static void augmentedtoMatrix(double[][] m,double[][] a, double[][] b){
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
	}

	public static double[][] matrixtoAugmented(double[][] a, double[][] b){
		double[][] m = new double[a.length][a[0].length+1];
		for(int i=0; i<m.length;i++){
			for (int j=0;j<m[0].length;j++){
				if(j<m[0].length-1){
					m[i][j]=a[i][j];
				}
				else if(j==m[0].length-1){
					m[i][j]=b[i][0];
				}
			}
		}
		return m;
	}

	public static double sumCol(double[][] m,int j){
		double sum=0;
		for(int i=0;i<m.length;i++){
			sum+=m[i][j];
		}
		return sum;
	}

	public static double sumRow(double[][] m, int i){
		double sum=0;
		for(int j=0;j<m[0].length;j++){
			sum+=m[i][j];
		}
		return sum;
	}
}
