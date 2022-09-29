package Matrix;

import Utils.utils;

public class OBE {
    public static void changerows(double[][] m, int x, int y){
        int i; 
        double temp;
        for (i = 0; i < m[x].length; i++) {
                temp = m[x][i];
                m[x][i] = m[y][i];
                m[y][i] = temp;            
        }
    }
    public static void addsubrows(double[][] m, boolean add, int x, int y){
        int j;
        for (j = 0; j < m[x].length; j++){
            if (add)
            m[x][j] = m[y][j] + m[x][j];
            else
            m[x][j] = m[x][j] - m[y][j];
        }
    }
    public static void multdivrows(double[][] m, boolean mult, int x, double multiplydigit){
        int j;
        for (j = 0; j < m[x].length; j++){
            if (mult)
            m[x][j] = multiplydigit * m[x][j];
            else
            m[x][j] = m[x][j] / multiplydigit;
        }   
    }  
    public static void triangleup(double[][] m){
        int i, j;
        double multiplydigit;
        
        for (i = 1; i < m.length; i++){
            for (j = 0; j < i; j++){
                multiplydigit = m[j][j] / m[i][j];
                multdivrows(m, true, i, multiplydigit);
                addsubrows(m, false, i, j);
            }
        }
    }
    public static void triangledown(double[][] m){
        int i, j;
        double multiplydigit;
        
        for (i = m.length-1; i >= 0; i--){
            for (j = m.length-1; j > i; j--){
                multiplydigit = m[j][j] / m[i][j];
                multdivrows(m, true, i, multiplydigit);
                addsubrows(m, false, i, j);
                // System.out.println("mult:"+multiplydigit);
                // System.out.print(i + ":" + j + " ");
            }
        }
    }
    private static int findNonZero(double[][] m, int col, int a, int b){
        for(int i = a; i<b; i++){
            if(m[i][col]!=0) return i;
        }
        return -1;
    }
    private static boolean isColumnZero(double[][] m, int col, int a, int b){
        return findNonZero(m, col, a, b)==-1;
    }
    private static boolean isRowZero(double[][] m, int row, int a, int b){
        for(int j = a; j<b; j++){
            if(m[row][j]!=0) return false;
        }return true;
    }
    public static void toEchelon(double[][] m){
        // I.S. m matriks augmented sembarang
        // F.S. m matriks eselon augmented 
        double[][] nm = new double[utils.max(m.length, m[0].length)][m[0].length];
        utils.fillZero(nm);
        utils.forceCopyMatrix(m, nm);


        int toprow = 0;
        int idx;

        for(int j = 0; j<m[0].length; j++){
            if(isColumnZero(m, j, toprow, m.length)) continue;
            for(int i = m.length-1; i>toprow; i--){
                if(m[toprow][j] == 0){ // prevent base OBE row to be 0
                    idx = findNonZero(m, j, toprow, m.length);
                    changerows(m, toprow, idx);
                    System.out.println("switched");
                    if(idx==-1) System.out.println("not found");
                }
                if(m[toprow][j] != 1){
                    multdivrows(m, false, toprow, m[toprow][j]);
                }
                if(m[i][j] != 0){
                    multdivrows(m, false, i, m[i][j]);
                    addsubrows(m, false, i, toprow);
                }
            }
            System.out.println();
            utils.printMatrix(m);
            toprow++;
        }

        // for(int i = 0; i<m.length; i++){
        //     if(m[i][i]!=1) multdivrows(m, false, i, m[i][i]);
        // }
    }
    public static void main(String[] args) {
        double m[][] = {{1,3,-2,0,2,0,0},{2,6,-5,-2,4,-3,-1},{0,0,5,10,0,15,5},{2,6,0,8,4,18,6}};
        toEchelon(m);
        System.out.println();
        utils.printMatrix(m);
    }
}