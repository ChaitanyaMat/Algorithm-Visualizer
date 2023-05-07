
package AlgorithmVisualizer.Searching;

import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;

public class Binary {

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

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }
    private double[] array;
    private double search;
    private int b,e,mid;
    
    public Binary(double[] array) {
        this.array = array;
        search = Double.MIN_VALUE;
        b = -1;
        e = -1;
        mid = -1;
    }
    
    public void init() {
        b = 0;
        e = array.length-1;
        mid = (b + e) / 2;
    }
    
    public boolean searchItem(){
        if(b==-1) {
            init();
            return false;
        }
        Logger.addLog("Begin Index = "+b+". End Index = "+e+". Mid Index = "+mid, TimeTaken.time(false));
        Logger.addLog("Comparing "+(int)search+" to Value at Mid Index.", TimeTaken.time(false));
        if(array[mid]==search*5)
            return true;
        if(array[mid]<search*5) {
            b=mid+1;
            Logger.addLog((int)search+" > Value at Mid Index.", TimeTaken.time(false));
            Logger.addLog("Increasing the value of Begin Index.", TimeTaken.time(false));
        }
        else if(array[mid]>search*5) {
            e=mid-1;
            Logger.addLog((int)search+" < Value at Mid Index.", TimeTaken.time(false));
            Logger.addLog("Decreasing the value of End Index.", TimeTaken.time(false));
        }
        if(b>e) {
            Logger.addLog("End Index < Begin Index. Terminating Search", TimeTaken.time(false));
            return true;
        }
        mid=(b+e)/2;
        return false;
    }
}
