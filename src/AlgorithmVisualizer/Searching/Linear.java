
package AlgorithmVisualizer.Searching;

import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;

public class Linear {

    public double[] getArray() {
        return array;
    }

    public void setArray(double[] array) {
        this.array = array;
    }

    public double getSearch() {
        return search;
    }

    public void setSearch(double search) {
        this.search = search;
    }

    public int getCompareIndex() {
        return compare_index;
    }

    public void setCompareIndex(int compare_index) {
        this.compare_index = compare_index;
    }
    private double[] array;
    private double search;
    private int compare_index;
    
    public Linear(double[] array) {
        this.array = array;
        search = Double.MIN_VALUE;
        compare_index = 0;
    }
    
    public boolean searchItem(){
        if(compare_index<array.length)
            Logger.addLog("Comparing "+(int)search+" to Value at Index = "+compare_index+".", TimeTaken.time(false));
        if((compare_index<array.length && array[compare_index]==search*5) || compare_index==array.length)
            return true;
        compare_index++;
        return false;
    }
}
