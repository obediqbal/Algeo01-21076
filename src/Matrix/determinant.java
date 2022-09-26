package Matrix;

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
		newm = m;
		double det=0;
		for(int i=0;i<m.length;i++) {
			for(int j=0; j<m.length;j++) {
				if (i==j) {
					
				}
			}
		}
		return det;
	}
	public static void swap(double[][] m,int i1,int i2) {
		double[] temp = m[i1];
		m[i1]=m[i2];
		m[i2]=temp;
	}
}
