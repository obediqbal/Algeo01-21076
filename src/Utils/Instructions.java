package Utils;

import Matrix.OBEInstruction;

public class Instructions {
    private int CAPACITY = 500000;
    public OBEInstruction[] list = new OBEInstruction[CAPACITY];
    public OBEInstruction currentInstruction;
    public int idx;
    private int nextIdx;
    public int length;

    public Instructions(){
        this.clearInstructions();
    }

    public void clearInstructions(){
        for(int i = 0; i<CAPACITY; i++){
            this.list[i] = null;
        }
        currentInstruction = null;
        idx = 0;
        nextIdx = 0;
        length = 0;
    }

    public boolean isEmpty(){
        return length==0;
    }
    
    public boolean isOne(){
        return length==1;
    }

    public boolean isFull(){
        return length==CAPACITY;
    }

    public void nextInstruction(){
        try{
            if(isEmpty()){
                throw new Exception("Tidak ada instruksi selanjutnya");
            }
        }
        catch (Exception e){
            return;
        }
        
        currentInstruction = list[idx];
        dequeueInstruction();
    }

    public void enqueueInstruction(OBEInstruction o){
        try{
            if(isFull()){
                throw new Exception("Instruksi melebihi batas");
            }
        }
        catch(Exception e){
            return;
        }

        list[nextIdx] = o;
        nextIdx++;
        length++;
        nextIdx %= CAPACITY;
    }

    public void dequeueInstruction(){
        try{
            if(isEmpty()){
                throw new Exception("Instruksi kosong");
            }
        }
        catch(Exception e){
            return;
        }

        list[idx] = null;
        idx++;
        length--;
        idx %= CAPACITY;
    }
}
