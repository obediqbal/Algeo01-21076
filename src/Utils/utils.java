package Utils;
import java.util.Scanner;
import Matrix.*;
import interpolasi_regresi.interpolasiPolinom;

public class utils {
	public static void readMatrix(double[][] m,int n,int ma) {
		Scanner obj = new Scanner(System.in);
		for(int i=0; i<n;i++){
            for(int j=0;j<ma;j++){
                m[i][j]=obj.nextDouble();
            }
        }
		//obj.close();
	}
	public static double[][] makeMatrix(){
		Scanner obj=new Scanner(System.in);
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
	
	public static void getMenu() {
		Scanner obj = new Scanner(System.in);
		System.out.println("MENU\n 1. Sistem Persamaaan Linier\n 2. Determinan\n 3. Matriks balikan\n 4. Interpolasi Polinom\n 5. Interpolasi Bicubic\n 6. Regresi linier berganda\n 7. Keluar\n");
		int menu=obj.nextInt();
		obj.close();
		if(menu==1){
			splMenu();
		}
		else if(menu==2){
			determinantMenu();
		}
		else if(menu==3){
			double[][] m=makeMatrix();
			Matriks.inverse(m);
		}
		else if(menu==4){
			interpolasiPolinom.interpolasipol();
		}
	}
	
	public static void splMenu() {
		Scanner obj = new Scanner(System.in);
		System.out.println("1. Metode eliminasi Gauss\n 2. Metode eliminasi Gauss-Jordan\n 3. Metode matriks balikan\n 4. Kaidah Cramer\n");
		int menu=obj.nextInt();
		obj.close();
		/*if(menu==1){
			spl.eliminasiGauss();
		}
		else if(menu==2){
			spl.eliminasiGaussJordan();
		}
		else if(menu==3){
			spl.matrixBalikan();
		}
		else if(menu==4){
			spl.cramer(m, x);
		}*/
		
	}

	public static void determinantMenu(){
		Scanner obj = new Scanner(System.in);
		System.out.println("1. Metode ekspansi kofaktor\n 2. Metode reduksi baris\n");
		int menu=obj.nextInt();
		obj.close();
	}

	public static boolean isSameSize(double[][] m1, double[][] m2){
		return (m1.length==m2.length &&  m1[0].length==m2[0].length);
	}

	public static void printSolusi(double[] x){
		for(int i=0;i<x.length;i++){
			System.out.print("x");
			System.out.print(i+1 + ":");
			System.out.print(x[i]+"\n");
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
}
