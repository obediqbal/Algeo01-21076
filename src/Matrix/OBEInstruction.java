package Matrix;
import Utils.Global;
import Utils.utils;

public class OBEInstruction extends OBE {
    public String name;
    int x;
    int y;
    double z;

    public OBEInstruction(String nname, int nx, int ny){
        name = nname;
        x = nx;
        y = ny;
    }
    public OBEInstruction(String nname, int nx, double nz){
        name = nname;
        x = nx;
        z = nz;
    }

    private int determineType(){
        String[] type = {"add", "sub", "mul", "div", "switch"};
        
        for(int i = 0; i<5; i++){
            if(type[i].equals(this.name)) return i;
        }
        return -1;
    }
    private void changerow(double[][] m, int x, int y){
        int i; 
        double temp;
        for (i = 0; i < m[x].length; i++) {
                temp = m[x][i];
                m[x][i] = m[y][i];
                m[y][i] = temp;            
        }
    }
    private void addsubrow(double[][] m, boolean add, int x, int y){
        int j;
        for (j = 0; j < m[x].length; j++){
            if (add){
                m[x][j] = m[y][j] + m[x][j];
            }
            else{
                m[x][j] = m[x][j] - m[y][j];
            }
        }
    }
    private void multdivrow(double[][] m, boolean mult, int x, double multiplydigit){
        int j;
        for (j = 0; j < m[x].length; j++){
            if (mult){
                m[x][j] = multiplydigit * m[x][j];
            }
            else{
                m[x][j] = m[x][j] / multiplydigit;
            }
        }  
    }
    public void runOBE(double[][] m){
        int idx = determineType();
        try{
            switch(idx){
                case 0: //add
                    addsubrow(m, true, this.x, this.y);
                    break;
                case 1: //sub
                    addsubrow(m, false, this.x, this.y);
                    break;
                case 2: //mul
                    multdivrow(m, true, this.x, this.z);
                    break;
                case 3: //div
                    multdivrow(m, false, this.x, this.z);
                    break;
                case 4: //switch
                    changerow(m, this.x, this.y);
                    break;
                default:
                    throw new Exception("Instruksi OBE tidak valid");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
