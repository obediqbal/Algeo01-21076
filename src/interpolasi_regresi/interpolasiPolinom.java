package interpolasi_regresi;
import Matrix.*;
import Utils.utils;

import java.util.Scanner;

public class interpolasiPolinom {
	public static void interpolasipol() {
		//n: banyak titik
		//m: spl
		Scanner obj = new Scanner(System.in);
		System.out.print("Masukkan jumlah titik: ");
		int n=obj.nextInt();
		double[][] p= new double[n][2];
		double[][] m = new double[n][n+1];
		for(int i=0;i<n;i++){
			System.out.println("Masukkan titik: ");
			System.out.print("x: ");
			p[i][0]=obj.nextDouble();
			System.out.print("y: ");
			p[i][1]=obj.nextDouble();
		}
		for (int i=0;i<n;i++){
			for (int j=0;j<n+1;j++){
				if (j<n) {
					m[i][j]=Math.pow(p[i][0], j);
				}
				else{
					m[i][j]=p[i][1];
				}
			}
		}
		utils.printMatrix(m);
		double[] xs= spl.cramer(m);
		double result=0;
		System.out.print("Masukkan x: ");
		double x=obj.nextDouble();
		for(int i=0;i<xs.length;i++){
			result=result+Math.pow(x, i)*xs[i];
		}
		obj.close();
		System.out.println(result);
	}
}
