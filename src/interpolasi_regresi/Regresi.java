package interpolasi_regresi;

import Utils.utils;

public class Regresi {
    public static void regresi(double[][] m){
        double[][] a = new double[m.length][m[0].length-1];
		double[][] b = new double[m.length][1];
        utils.augmentedtoMatrix(m, a, b);
        
    }
}
