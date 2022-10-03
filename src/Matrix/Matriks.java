package Matrix;
import Matrix.determinant;
import Utils.Global;
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
        double [][]adj = new double[m.length][m.length];
        for (i = 0; i < m.length; i++){
            for (j = 0; j < m.length; j++){
                // adj[i][j] = p*determinant.ekspansiKofaktor(kofaktor(m, i, j));
                // p=-p;
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

    public static double[][] inverse(double[][] m, boolean obe){
        if(determinant.ekspansiKofaktor(m)==0){
            System.out.println("Determinan 0, tidak punya invers");
            return new double[0][0];
        }
        double[][] nm = new double[m.length][m[0].length];
        utils.copyMatrix(m, nm);

        double[][] res = reverseIdentity(nm);

        
        return res;
    }

    public static double[][] reverseIdentity(double[][] m){
        double[][] id = createIdentity(m[0].length);
        double[][] nm = new double[m.length][m[0].length];
        
        utils.copyMatrix(m, nm);
        OBE.toEchelon(nm, true);
        
        while(!Global.instructions.isEmpty()){
            Global.instructions.nextInstruction();
            Global.instructions.nextInstruction();
            Global.instructions.nextInstruction();
            Global.instructions.currentInstruction.runOBE(id);
        }

        return id;

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
    public static double[] getLinear(double[][] m){
        double[] nm = new double[m.length];
        for(int i = 0; i<m.length; i++){
            nm[i] = m[i][0];
        }
        return nm;
    }
    public static double[][] createIdentity(int n){
        double[][] nm = new double[n][n];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(i==j) nm[i][j] = 1;
                else nm[i][j] = 0;
            }
        }
        return nm;
    }

    public static void main(String[] args) {
		// double[][] m ={{2,3,-1},{4,4,-3},{-2,3,-1}};
        // double[][] m = {{1,2,3},{2,5,3},{1,0,8}};
        // double[][] nm = {{1,2,3},{2,5,3},{1,0,8}};
        
        // System.out.println();
        // utils.printMatrix(m);
        // System.out.println();

        // System.out.println();
        // utils.printMatrix(nm);
        // System.out.println();

        // OBE.toEchelon(m, true);
        // double[][] inverse_m = inverse(m, true);
        // System.out.println();
        // utils.printMatrix(inverse_m);
        // System.out.println();

        

        // int count = 0;

        // // Global.instructions.startInstruction();
        // // Global.instructions.currentInstruction.runOBE(nm);
        // Global.instructions.idx = 0;
        // while(!Global.instructions.isEmpty()){
        //     count++;
        //     Global.instructions.nextInstruction();
        //     Global.instructions.nextInstruction();
        //     Global.instructions.nextInstruction();
        //     Global.instructions.currentInstruction.runOBE(nm);
        // }

        // System.out.println(count);
        // System.out.println();
        // utils.printMatrix(m);
        // System.out.println();

        // System.out.println();
        // utils.printMatrix(nm);
        // System.out.println();
        
        
    }
}
