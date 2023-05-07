package AlgorithmVisualizer.Sorting;

import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;

public class Insertion {
    private int[] array;
    private int array_index;
    private int compare_index;
    private int key;
    private boolean startOfIteration = false,order;

    public Insertion(int[] array) {
        this.array = array;
        array_index = 1;
    }

    public int[] sortOnlyOneItem() {

        if (startOfIteration == false) {
            Logger.addLog("Key = " + array[array_index] + ".", TimeTaken.time(false));
            key = array[array_index];
            compare_index = array_index - 1;
            startOfIteration = true;
        }

        if (compare_index >= 0 && ((order && array[compare_index]>key) || (!order && array[compare_index]<key))) {
            Logger.addLog("Shifting " + array[compare_index] + " to the right.", TimeTaken.time(false));
            array[compare_index + 1] = array[compare_index];
            compare_index--;
            return array;
        }
        else {
            Logger.addLog("Inserted " + key + " at index " + (compare_index+1) + ".", TimeTaken.time(false));
            array[compare_index + 1] = key;
            array_index++;
        }

        startOfIteration = false;
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

    public boolean getStartOfIteration() {
        return startOfIteration;
    }

    public void setStartOfIteration(boolean startOfIteration) {
        this.startOfIteration = startOfIteration;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }
}
