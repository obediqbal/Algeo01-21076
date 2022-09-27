import java.util.Scanner;
import Utils.*;
import Matrix.*;

class Main{
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        //Utils.utils.getMenu();
        int n=obj.nextInt();
        int ma=obj.nextInt();
        double[][] m = new double[n][ma];
        utils.readMatrix(m,n,ma);
        utils.printMatrix(m);
        System.out.println("determinan");
        System.out.println(determinant.reduksiBaris(m));
        //Matrix.spl.cramer(m);
        //Matrix.OBE.triangleup(m);
        utils.printMatrix(m);
        obj.close();
    }
}