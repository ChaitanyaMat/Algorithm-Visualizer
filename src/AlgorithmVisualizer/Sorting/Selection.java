package AlgorithmVisualizer.Sorting;

import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;

public class Selection{
    
    private int array_index, compare_index, extreme_index;
    private int[] array;
    private boolean findingMin,order;

    public Selection(int[] array) {
        this.array = array;
        array_index = 0;
        compare_index = 1;
        extreme_index = 0;
        findingMin = false;
    }

    public void swap(int arr[], int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public int[] sortOnlyOneItem() {

        if (findingMin == false) {
            Logger.addLog((order?"Smallest = ":"Largest = ") + array[array_index], TimeTaken.time(false));
            extreme_index = array_index;
            findingMin = true;
        }

        if ((order && array[compare_index]<array[extreme_index]) ||(!order && array[compare_index]>array[extreme_index])) {
            Logger.addLog((order?"New Smallest = ":"New Largest = ") + array[compare_index], TimeTaken.time(false));
            extreme_index = compare_index;
        }

        compare_index++;

        if (compare_index >= array.length) {
            if(extreme_index != array_index)
                Logger.addLog("Swapping " + array[array_index] + " and " + array[extreme_index] + ".", TimeTaken.time(false));
            swap(array, extreme_index, array_index);
            array_index++;
            extreme_index = array_index;
            compare_index = array_index + 1;
            findingMin = false;
        }

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

    public int getExtremeIndex() {
        return extreme_index;
    }

    public void setExtremeIndex(int extreme_index) {
        this.extreme_index = extreme_index;
    } 

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }
}