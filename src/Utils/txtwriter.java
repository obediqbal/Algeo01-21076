package Utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class txtwriter {
    public static void writeMatrix(double[][] m,String file){
        PrintWriter out =null;
        try {
            out = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(int i=0; i< m.length;i++){
            for(int j=0;j< m[0].length;j++){
                if(j<m[0].length-1){
                    out.print(m[i][j]+" ");
                }
                else{
                    out.print(m[i][j]+"\n");
                }
            }
        }
        out.close();
    }
    public static void writeRegresi(double[][] m, double[] solusi, double result,double[] x,String file){
        PrintWriter out =null;
        try {
            out = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        out.println("matrix hasil normal estimation equation:");
        for(int i=0; i< m.length;i++){
            for(int j=0;j< m[0].length;j++){
                if(j<m[0].length-1){
                    out.print(m[i][j]+" ");
                }
                else{
                    out.print(m[i][j]+"\n");
                }
            }
        }
        out.println("persamaan regresi linier berganda:");
        out.print("y=");
        for (int i=0;i<solusi.length;i++){
            if(solusi[i]>0){
                out.print("+ ");
            }
            out.print(solusi[i]);
            
            if(i>0){
                out.print("x"+i);
            }
            out.print(" ");
            
        }
        out.print("\n");
        out.println("3 peubah yang akan ditaksir:");
        for (int i=0;i<x.length;i++){
            out.print(x[i]+" ");
        }
        out.print("\n");
        out.println("hasil taksiran: "+result);
        out.close();
    }
}
