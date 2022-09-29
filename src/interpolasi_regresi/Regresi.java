package interpolasi_regresi;

import Matrix.Matriks;
import Utils.utils;

public class Regresi {
    public static void regresi(double[][] m){
        double[][] a = new double[m.length][m[0].length-1];
        double[][] at = new double[m.length][m[0].length-1];
        double[][] kiri = new double[m.length][m[0].length-1];
        double[][] kanan = new double[m.length][1];
		double[][] b = new double[m.length][1];
        double[][] reg;
        utils.augmentedtoMatrix(m, a, b);
        utils.copyMatrix(a, at);
        at = Matriks.transpose(a);
        kiri =Matriks.multiplyMatrix(at, a);
        kanan = Matriks.multiplyMatrix(at, b);
        reg = utils.matrixtoAugmented(kiri, kanan);
        double[][] reg1 = new double[reg.length+1][reg[0].length+1];
        reg1[0][0]=reg1.length*reg1[0].length;
        int k=0,l=0;
        for(int i=0;i<reg1.length;i++){
            for(int j=0;j<reg1[0].length;j++){
                if(i==0){
                    if(j>0){
                        reg1[i][j]=utils.sumCol(m, j-1);
                    }
                }
                else if(j==0){
                    if(i>0){
                        reg1[i][j]=reg1[j][i];
                    }
                }
                else{
                    reg1[i][j]=reg[k][l];
                    if(l<reg[0].length-1){
                        l++;
                    }
                    else if (l==reg[0].length-1){
                        l=0;
                        k++;
                    }
                }
            }
        }
        utils.printMatrix(reg1);
    }
}
