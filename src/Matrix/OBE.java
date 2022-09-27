package Matrix;

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
            m[x][j] = multiplydigit / m[x][j];
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
            System.out.println();
        }
    }
    /*public static void main(String[] args) {
        int i, j;
        double m[][] = {{2, 3, 4}, {5, 6, 7}, {8, 9, 1}};
        triangledown(m);
        for (i = 0; i < 3; i++){
            for (j = 0; j < 3; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }*/
}