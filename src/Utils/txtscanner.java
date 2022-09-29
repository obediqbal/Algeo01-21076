package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class txtscanner {
    public static double[][] getMatrixFile(String file){
        Scanner obj=null;
        double[][] m=null;
        int maxrow = 0;
        int maxcol = 0;
        try{
            obj = new Scanner(new File(file));
            maxrow=getMatrixsize(file)[0];
            maxcol=getMatrixsize(file)[1];
        }
        catch(FileNotFoundException nf){
            System.out.println("file tidak ditemukan");
        }
        
        m= new double[maxrow][maxcol];
        System.out.println(maxrow);
        System.out.println(maxcol);
        int i=0;
        int j=0;
        while (obj.hasNextDouble()){
            m[i][j]=obj.nextDouble();
            if(j<maxcol-1){
                j++;
            }
            else{
                j=0;
                i++;
            }
        }
        obj.close();
        return m;
    }
    public static int[] getMatrixsize(String file) throws FileNotFoundException{
        int[] x = new int[2];
        int rowCount=0;
        int colCount=0;
        Scanner scanrow = new Scanner(new File(file));
        Scanner scancol = new Scanner(new File(file));
        while(scanrow.hasNextLine()==true){
            rowCount++;
            scanrow.nextLine();
            
        }
        while(scancol.hasNextDouble()){
            colCount++;
            scancol.nextDouble();
        }
        x[0]=rowCount;
        x[1]=colCount/rowCount;
        scancol.close();
        scanrow.close();
        return x;
    }
}
