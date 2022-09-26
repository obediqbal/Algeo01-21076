import java.util.Scanner;
import Matrix.*;

class Main{
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        int x=obj.nextInt();
        double[][] m=new double[x][x];
        for(int i=0; i<x;i++){
            for(int j=0;j<x;j++){
                m[i][j]=obj.nextInt();
            }
        }
        for(int i=0; i<x;i++){
            for(int j=0;j<x;j++){
                if(j<x-1){
                    System.out.print(m[i][j]+" ");
                }
                else{
                    System.out.print(m[i][j]+"\n");
                }
            }
        }
        System.out.println(determinant.ekspansiKofaktor(m));
        obj.close();
    }
}