package interpolasi_regresi;
import Matrix.*;
import Utils.txtscanner;
import Utils.utils;

import java.util.Scanner;

public class interpolasiPolinom {
	public static void interpolasipol() {
		//n: banyak titik
		//m: spl
		Scanner obj = new Scanner(System.in);
		double[] xs =null;
		double[][] p=null;
		double[][] m=null;
		System.out.println("apakah masukan dari keyboard(y)/file(n)");
		String masukan=obj.nextLine();
		System.out.println(masukan);
		if (masukan.equals("y")){
			System.out.print("Masukkan jumlah titik: ");
			int n=obj.nextInt();
			p= new double[n][2];
			m = new double[n][n+1];
			for(int i=0;i<n;i++){
				System.out.println("Masukkan titik: ");
				System.out.print("x: ");
				p[i][0]=obj.nextDouble();
				System.out.print("y: ");
				p[i][1]=obj.nextDouble();
			}
			
		}
		else if(masukan.equals("n")){
			System.out.println("nama file beserta path nya");
			String file=obj.nextLine();
			p=txtscanner.getMatrixFile(file);
			m=new double[p.length][p.length+1];
		}
		System.out.println("persamaan interpolasi polinom:");
		System.out.print("y=");
		for (int i=0;i<p.length;i++){
			for (int j=0;j<p.length+1;j++){
				if (j<p.length) {
					m[i][j]=Math.pow(p[i][0], j);
				}
				else{
					m[i][j]=p[i][1];
				}
			}
		}
		xs= spl.cramer(m);
		for(int i=0;i<xs.length;i++){
			System.out.print(xs[i]);
			System.out.print("x^"+i);
			if(i<xs.length-1){
				if(xs[i+1]>=0 ){
					System.out.print("+");
				}
			}
		}
		System.out.print("\n");
		double result=0;
		System.out.print("Masukkan nilai yang akan ditaksir: ");
		double x=obj.nextDouble();
		for(int i=0;i<xs.length;i++){
			result=result+Math.pow(x, i)*xs[i];
		}
		System.out.println(result);
	}
}
