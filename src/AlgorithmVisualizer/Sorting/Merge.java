package AlgorithmVisualizer.Sorting;

import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;

public class Merge {

    public boolean isMerging() {
        return merging;
    }

    public void setMerging(boolean merging) {
        this.merging = merging;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }

    public int getSubSize() {
        return subSize;
    }

    public void setSubSize(int subSize) {
        this.subSize = subSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getArray_index() {
        return array_index;
    }

    public void setArray_index(int array_index) {
        this.array_index = array_index;
    }

    public int getCompare_index() {
        return compare_index;
    }

    public void setCompare_index(int compare_index) {
        this.compare_index = compare_index;
    }

    public int getStore_index() {
        return store_index;
    }

    public void setStore_index(int store_index) {
        this.store_index = store_index;
    }
    
    private int[] array, temp;
    private int subSize, size, low, mid, high, array_index, compare_index, store_index, temp_index;
    private boolean merging, order;
    
    public Merge(int[] array) {
        this.array = array;
        low = 0;
        subSize = 1;
        size = array.length;
        merging = false;
    }
    
    public int[] sortOnlyOneItem() {
        if(!isMerging()){
            if(getSubSize() <= getSize()-1) {
                if(low < getSize()-1) {
                    mid = Math.min(low + getSubSize() - 1, getSize() - 1);
                    high = Math.min(low + (2 * getSubSize()) - 1, getSize() - 1);
                    Logger.addLog("Dividing Sub-Array ["+low+"-"+high+"].", TimeTaken.time(false));
                    setMerging(true);
                    store_index = Integer.MIN_VALUE;
                    temp_index = 0;
                    array_index = low;
                    compare_index = mid+1;
                    temp = new int[high - low + 1];
                    Logger.addLog("Merging Sub-Arrays ["+low+"-"+mid+"] and ["+(mid + 1)+"-"+high+"].", TimeTaken.time(false));
                }
                else{
                    setSubSize(getSubSize() * 2);
                    low = 0;
                }
            }
            
        }
        else {
            if (array_index <= mid  &&  compare_index <= high) {
                if((order && array[array_index]<array[compare_index]) || (!order && array[array_index]>array[compare_index]))
                    temp[temp_index++] = array[array_index++];
                else
                    temp[temp_index++] = array[compare_index++];
            }
            else if (array_index <= mid) {
                temp[temp_index++] = array[array_index++];
            }
            else if (compare_index <= high) {
                temp[temp_index++] = array[compare_index++];
            }
            else if (store_index <= high) {
                if (store_index < low)
                    store_index = low;
                array_index = compare_index = Integer.MAX_VALUE;
                Logger.addLog("Sorting Value at Index = "+store_index, TimeTaken.time(false));
                array[store_index++] = temp[temp.length - (temp_index--)];
            }
            else {
                setMerging(false);
                low += 2*getSubSize();
            }
        }
        return array;
    }
}