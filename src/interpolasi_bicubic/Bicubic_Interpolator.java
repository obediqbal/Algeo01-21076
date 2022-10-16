package interpolasi_bicubic;

import Matrix.Matriks;
import Utils.utils;

public class Bicubic_Interpolator {
    private double[][] fval = new double[16][1];
    private double[][] x = new double[16][16];
    private double[] a = new double[16];

    public Bicubic_Interpolator(double[][] fvalin){
        // f = Xa <=> X^(-1)f = a
        fval = fToLinearCol(fvalin);
        x = createX();
        a = solveA();
    }

    private double[][] createX(){
        double[][] res = new double[16][16];

        int idxX, idxY = 0;
        for(int y = -1; y <= 2; y++){
            for(int x = -1; x <= 2; x++){
                idxX = 0;
                for(int j = 0; j <= 3; j++){
                    for(int i = 0; i <= 3; i++){
                        res[idxY][idxX] = Math.pow(x, i) * Math.pow(y, j);
                        idxX++;
                    }
                }
                idxY++;
            }
        }

        return res;
    }

    private double[][] fToLinearCol(double[][] f){
        double[][] res = new double[16][1];
        int count = 0 ;

        for(int i = 0; i<=3; i++){
            for(int j = 0; j<=3; j++){
                res[count][0] = f[j][i];
                count++;
            }
        }

        return res;
    }

    private double[] solveA(){
        double[][] inverse_x = Matriks.inverse(this.x, true);
        double[][] multiply_res = Matriks.multiplyMatrix(inverse_x, this.fval);


        return Matriks.getLinear(multiply_res);
    }

    public double solver(double x, double y){
        int count = 0;
        double res = 0;

        for(int i = 0; i<=3; i++){
            for(int j = 0; j<=3; j++){
                res += this.a[count]*Math.pow(x, i)*Math.pow(y, j);
                count++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        
    }
}
