import java.util.Scanner;
import Utils.*;
import Matrix.*;

class Main{
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        //int menu = Utils.utils.getMenu();
        int n=obj.nextInt();
        int ma=obj.nextInt();
        double[][] m = new double[n][ma];
        utils.readMatrix(m,n,ma);
        utils.printMatrix(m);
        System.out.println(determinant.ekspansiKofaktor(m));
        Matrix.spl.cramer(m);
        obj.close();
    }
}