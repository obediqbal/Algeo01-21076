package Matrix;

import Utils.utils;

public class determinant {
	public static double ekspansiKofaktor(double[][] m) {
		double det=0;
		double p=1;
		double[][] minor = new double[m.length-1][m.length-1];
		if(m.length==1) {
			return m[0][0];
		}
		else {
			for(int i=0;i<m.length;i++) {
				minor = kofaktor(m,i,0);
				det=det+(p*m[i][0]*ekspansiKofaktor(minor));
				p=-p;
			}
		}
		return det;
	}
	
	public static double[][] kofaktor(double[][] m, int a, int b){
		int k=0;
		int l=0;
		double[][] minor = new double[m.length-1][m.length-1];
		for(int i=0;i<m.length;i++) {
			for(int j=0;j<m.length;j++) {
				if(i!=a && j!=b) {
					minor[k][l]=m[i][j];
					if (l==minor.length-1)
	                {
	                    l=0;
	                    k++;
	                }
	                else{
	                    l++;
	                }
				}
			}
		}
		return minor;
	}
	
	public static double reduksiBaris(double[][] m) {
		double[][] newm = new double[m.length][m.length];
		spl.copyMatrix(m, newm);
		int swapcount=0;
		double det=1;
		int firstidx=0;
		for(int i=0;i<m[0].length;i++) {
			if(newm[i][i]==0){
				int swapidx=i;
				while(swapidx<newm.length ){
					if(newm[swapidx][i]!=0){
						swap(newm,i,swapidx);
						swapcount++;
						break;
					}
					swapidx++;
				}
			}
			double bawah=newm[firstidx][i];
			for(int j=firstidx+1; j<m[0].length;j++) {
				double atas =newm[j][i];
				for (int k=i;k<newm[0].length;k++){
					newm[j][k]-=(newm[firstidx][k]*atas)/bawah;
				}
			}
			firstidx++;
		}
		utils.printMatrix(newm);
		det=det*Math.pow(-1, swapcount);
		for(int i=0;i<m.length;i++){
			det=det*newm[i][i];
		}
		return det;
		
	}
	public static void swap(double[][] m,int i1,int i2) {
		double[] temp = m[i1];
		m[i1]=m[i2];
		m[i2]=temp;
	}
}
