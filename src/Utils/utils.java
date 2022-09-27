package Utils;
import java.util.Scanner;
import Matrix.*;

public class utils {
	public static void readMatrix(double[][] m,int n,int ma) {
		Scanner obj = new Scanner(System.in);
		for(int i=0; i<n;i++){
            for(int j=0;j<ma;j++){
                m[i][j]=obj.nextInt();
            }
        }
		obj.close();
	}
	
	public static void printMatrix(double[][] m) {
		for(int i=0; i< m.length;i++){
            for(int j=0;j< m[0].length;j++){
                if(j<m[0].length-1){
                    System.out.print(m[i][j]+" ");
                }
                else{
                    System.out.print(m[i][j]+"\n");
                }
            }
        }
	}
	
	public static void getMenu() {
		Scanner obj = new Scanner(System.in);
		System.out.println("1. metode");
		System.out.println("1. metode");
		System.out.println("1. metode");
		int menu=obj.nextInt();
		
	}
	
	public static void splMenu() {
		Scanner obj = new Scanner(System.in);
		System.out.println("1. spl");
		System.out.println("1. spl");
		System.out.println("1. spl");
		int menu=obj.nextInt();
		
	}
}
