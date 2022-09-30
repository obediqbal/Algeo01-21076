import java.io.IOException;
import java.util.Scanner;
import Utils.*;
import interpolasi_regresi.Regresi;
import interpolasi_regresi.interpolasiPolinom;
import Matrix.*;

class Main{
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        /*double[][] m1;
        double[][] m2;
        double[][] m3;
        m1=utils.makeMatrix();
        m2=utils.makeMatrix();
        utils.printMatrix(m1);
        System.out.println();
        utils.printMatrix(m2);
        System.out.println();
        m3=Matriks.multiplyMatrix(m1, m2);
        utils.printMatrix(m3);*/
        String file = obj.nextLine();
        //String file2 = obj.nextLine();
        //file = "C:/Users/User/Documents/algeo/Algeo01-21076/test/"+file;
        double[][] m;
        //double[][] m2;
        //double[][] newm;
        m=txtscanner.getMatrixFile(file);
        //txtwriter.writeMatrix(m, file);
        //m2=txtscanner.getMatrixFile(file2);
        //newm = Matriks.multiplyMatrix(m, m2);
        //utils.printMatrix(newm);
        //System.out.println("aaaaa");
        //utils.printMatrix(m);
        //Regresi.regresi(m);
        //Utils.utils.getMenu();
        //int n=obj.nextInt();
        //int ma=obj.nextInt();
        //double[][] m = new double[n][ma];
        //utils.readMatrix(m,n,ma);
        //utils.printMatrix(m);
        double[] solusi;
        solusi = spl.eliminasiGauss(m);
        String saveFile=obj.nextLine();
        txtwriter.writeSolusi(saveFile, solusi);
        //interpolasiPolinom.interpolasipol(file);
        //Matriks.adjoint(m);
        //utils.printMatrix(m); C:\\Users\\User\\Documents\\algeo
        obj.close();
    }
}