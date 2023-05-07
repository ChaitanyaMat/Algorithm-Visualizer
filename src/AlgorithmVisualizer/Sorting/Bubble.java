package AlgorithmVisualizer.Sorting;

import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;

public class Bubble {
    private int[] array;
    private int array_index, compare_index;
    private boolean order;

    public Bubble(int[] array) {
        this.array = array;
        compare_index = 0;
        array_index = 0;
    }

    public int[] sortOnlyOneItem() {

        if ((order && array[compare_index]>array[compare_index+1]) || (!order && array[compare_index]<array[compare_index+1])) {
            Logger.addLog("Swapping " + array[compare_index] + " and " + array[compare_index + 1] + ".", TimeTaken.time(false));
            int temp = array[compare_index];
            array[compare_index] = array[compare_index+1];
            array[compare_index+1] = temp;
        }
        
        if ((compare_index+1) >= (array.length - array_index - 1)) {
            Logger.addLog("Iteration " + (array_index + 1) + " ended.", TimeTaken.time(false));
            array_index++;
            compare_index = 0;
        }
        
        else compare_index++;

        return array;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getArrayIndex() {
        return array_index;
    }

    public void setArrayIndex(int array_index) {
        this.array_index = array_index;
    }

    public int getCompareIndex() {
        return compare_index;
    }

    public void setCompareIndex(int compare_index) {
        this.compare_index = compare_index;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }
}
