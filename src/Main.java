import java.io.IOException;
import java.util.Scanner;
import Utils.*;
import interpolasi_regresi.interpolasiPolinom;
import Matrix.*;

class Main{
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        String file = obj.nextLine();
        file = "C:\\Users\\User\\Documents\\algeo\\Algeo01-21076\\test\\"+file;
        double[][] m;
        m=txtscanner.getMatrixFile(file);
        utils.printMatrix(m);
        //Utils.utils.getMenu();
        //int n=obj.nextInt();
        //int ma=obj.nextInt();
        //double[][] m = new double[n][ma];
        //utils.readMatrix(m,n,ma);
        //utils.printMatrix(m);
        //interpolasiPolinom.interpolasipol();
        //Matriks.adjoint(m);
        //utils.printMatrix(m);
        obj.close();
    }
}