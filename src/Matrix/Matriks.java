

public class Matriks {
    public static void transpose(double[][] m){
        double transpose[][] = new double[3][3];
        int i, j;
        for (i = 0; i < m.length; i++) {
            for (j = 0; j < m.length; j++) {
                transpose[i][j] = m[j][i];
                System.out.print(" ");
            }
            System.out.println();
        }   
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
        double [][]adj = new double[m.length][m.length];
        for (i = 0; i < m.length; i++){
            for (j = 0; j < m.length; j++){
                adj[i][j] = 
            }
        }
    }
    public static void main(String[] args) {
        double[][] m = {{2,3,4},{5,6,7},{8,9,1}};
        m = kofaktor(m, 1, 0);

        int i, j;
        for(i=0; i<m.length; i++){
            for(j=0; j<m[i].length; j++){
                System.out.print(" ");
            }
        }
    }
}
