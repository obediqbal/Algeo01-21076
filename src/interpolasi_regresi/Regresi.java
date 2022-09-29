package interpolasi_regresi;

import java.util.Scanner;

import Matrix.Matriks;
import Matrix.spl;
import Utils.utils;

public class Regresi {
    public static void regresi(double[][] m){
        Scanner obj = new Scanner(System.in);
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
        double[] solusi=spl.eliminasiGauss(reg1);
        System.out.println("matrix hasil normal estimation equation:");
        utils.printMatrix(reg1);
        System.out.println("persamaan regresi linier berganda:");
        System.out.print("y=");
        for (int i=0;i<solusi.length;i++){
            if(solusi[i]>0){
                System.out.print("+ ");
            }
            System.out.print(solusi[i]);
            
            if(i>0){
                System.out.print("x"+i);
            }
            System.out.print(" ");
            
        }
        double[] x= new double[3];
        System.out.print("\n");
        System.out.println("masukkan 3 peubah yang akan ditaksir");
        for (int i=0;i<x.length;i++){
            x[i]=obj.nextDouble();
        }
        double result=solusi[0];
        for(int i=1;i<solusi.length;i++){
            result=result+solusi[i]*x[i-1];
        }
        System.out.println("hasil taksiran: "+result);
    }
}
