package Matrix;
import Matrix.determinant;
import Utils.utils;

public class Matriks {
    public static double[][] transpose(double[][] m){
        double transpose[][] = new double[m[0].length][m.length];
        int i, j;
        for (i = 0; i < m.length; i++) {
            for (j = 0; j < m[0].length; j++) {
                transpose[j][i] = m[i][j];
            }
        }
        return transpose;
    }

    public static double[][] kofaktor(double[][] m, int a, int b){
		int k=0;
		int l=0;
        int neg = ((a+b)%2) == 0 ? 1 : -1;
		double[][] minor = new double[m.length-1][m.length-1];
		for(int i=0;i<m.length;i++) {
			for(int j=0;j<m.length;j++) {
				if(i!=a && j!=b) {
					minor[k][l]=m[i][j]*neg;
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
    public static void adjoint(double [][]m){
        int i, j;
        int p=1;
        double [][]adj = new double[m.length][m.length];
        for (i = 0; i < m.length; i++){
            for (j = 0; j < m.length; j++){
                adj[i][j] = p*determinant.ekspansiKofaktor(kofaktor(m, i, j));
                p=-p;
            }   //System.out.print(adj[i][j] + " ");
        }//System.out.println();
        transpose(adj);
        m=adj;
    }
    /*public static void main(String[] args) {
        double[][] m = {{2,3,4},{5,6,7},{8,9,1}};
        m = kofaktor(m, 1, 0);

        int i, j;
        for(i=0; i<m.length; i++){
            for(j=0; j<m[i].length; j++){
                System.out.print(" ");
            }
        }
    }*/
    public static void inverse(double[][] m){
        adjoint(m);
        double per=determinant.ekspansiKofaktor(m);
        for (int i=0;i<m.length;i++){
            for (int j=0;j<m[0].length;j++){
                m[i][j]=m[i][j]/per;
            }
        }
    }

    public static void fillNaN(double[] m){
        for(int i = 0; i<m.length; i++){
            for(int j = 0; j<m.length; j++){
                m[i] = Double.NaN;
            }
        }
    }
    public static void fillNaN(double[][] m){
        for(int i = 0; i<m.length; i++){
            fillNaN(m);
        }
    }
    public static double[][] multiplyMatrix(double[][] a, double[][] b){
        double[][] newm= new double[a.length][b[0].length];
        for(int i=0;i<a.length;i++){
            for(int j=0;j<b[0].length;j++){
                newm[i][j]=0;
                for(int k=0;k<a[0].length;k++){
                    newm[i][j]=newm[i][j]+a[i][k]*b[k][j];
                }
            }
        }
        return newm;
    }
}
