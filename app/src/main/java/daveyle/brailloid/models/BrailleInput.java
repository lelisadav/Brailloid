package daveyle.brailloid.models;

/**
 * Created by daveyle on 10/5/2015.
 */
public class BrailleInput {
    private boolean[] keys={false, false, false, false, false, false};
    public BrailleInput(){

    }
    public void setKey(boolean value, int key){
        keys[key]=value;
    }
    public boolean[] getKeys(){
        return this.keys;
    }
    public int getBinValue(){
        int i=0;
        for (int j=0; j<6; j++){
            if (keys[j]){
                i+=Math.pow(2,j);
            }
        }
        return i;
    }
}
