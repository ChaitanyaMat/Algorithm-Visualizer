package AlgorithmVisualizer.Panels;

import AlgorithmVisualizer.Event.EventAlgorithmFinished;
import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;
import AlgorithmVisualizer.Event.EventPanelChanged;
import AlgorithmVisualizer.Sorting.Bubble;
import AlgorithmVisualizer.Sorting.Insertion;
import AlgorithmVisualizer.Sorting.Merge;
import AlgorithmVisualizer.Sorting.Quick;
import AlgorithmVisualizer.Sorting.Selection;
import AlgorithmVisualizer.Swing.Notification.Notification;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class SortingPanel extends javax.swing.JPanel {

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = (int)((speed - 1)*(-250/9.0)+250);
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
        setArray();
        setOrder(order);
        setStarted(false);
    }

    public int[] getArray() {
        return array;
    }

    public void setArray() {
        array=new int[length];
        for(int i=0;i<length;i++)
            array[i]=(int)(Math.random()*451)+50;
        bubbleSort = new Bubble(array);
        selectionSort = new Selection(array);
        insertionSort = new Insertion(array);
        mergeSort = new Merge(array);
        quickSort = new Quick(array);
    }

    public boolean isRunning() {
        if(EventPanelChanged.isChanged())
            return false;
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
        bubbleSort.setOrder(order);
        selectionSort.setOrder(order);
        insertionSort.setOrder(order);
        mergeSort.setOrder(order);
        quickSort.setOrder(order);
    }

    private int[] array;
    private boolean running, order, started;
    private int sort, length, speed;
    
    private Bubble bubbleSort;
    private Selection selectionSort;
    private Insertion insertionSort;
    private Merge mergeSort;
    private Quick quickSort;
    
    public SortingPanel() {
        initComponents();
    }
    
    private String arrayToString() {
        String ar = "[";
        for(int i:array)
            ar += i + ", ";
        ar = ar.substring(0, ar.length()-2) + "].";
        return ar;
    }
    
    public boolean isSorted() {
        for (int i = 0; i < getArray().length - 1; i++) {
            if ((order && getArray()[i]>getArray()[i + 1]) || (!order && getArray()[i]<getArray()[i+1])){
                return false; 
            }
        }
        return true;
    }
    
    private void finished() {
        Logger.addLog("Sorting Completed.", TimeTaken.time(false));
        Logger.addLog("Sorted Array = "+arrayToString(), TimeTaken.time(true));
        EventAlgorithmFinished.toggleButton();
        Notification noti=new Notification(AlgorithmVisualizer.AlgoVis.getFrame(), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Sorting Completed");
        noti.showNotification();
                            
    }
    
    public void animate() throws Exception{
        if(started)
            Logger.addLog("Unpaused", TimeTaken.time(true));
        Logger.addLog((started?"Current Array = ":"Original Array = ")+arrayToString(), TimeTaken.time(!started));
        
        started = true;
        
        EventPanelChanged.setChanged(false);
        if (sort==0) {
            Timer bubbleTimer  = new Timer(speed, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((Timer)e.getSource()).setDelay(speed);
                    if (isSorted() || !isRunning()) {
                        setRunning(false);
                        if(isSorted()){
                            finished();
                            bubbleSort.setCompareIndex(Integer.MAX_VALUE);
                            bubbleSort.setArrayIndex(Integer.MAX_VALUE);
                        }
                        else if(!EventPanelChanged.isChanged())
                            Logger.addLog("Paused", TimeTaken.time(false));
                        ((Timer)e.getSource()).stop();
                    } 
                    else if(isRunning()) {
                        array = bubbleSort.sortOnlyOneItem();
                        
                    }
                    repaint();
                }
            });
            bubbleTimer.start();
        }
        
        if (sort==1) {
            Timer selectionTimer  = new Timer(speed, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((Timer)e.getSource()).setDelay(speed);
                    if (isSorted() || !isRunning()) {
                        setRunning(false);
                        if(isSorted()){
                            finished();
                            selectionSort.setArrayIndex(Integer.MAX_VALUE);
                            selectionSort.setCompareIndex(Integer.MAX_VALUE);
                            selectionSort.setExtremeIndex(Integer.MAX_VALUE);
                        }
                        else if(!EventPanelChanged.isChanged())
                            Logger.addLog("Paused", TimeTaken.time(false));
                        ((Timer)e.getSource()).stop();
                    } 
                    else if (isRunning())
                            array = selectionSort.sortOnlyOneItem();
                    repaint();
                }
            });
            selectionTimer.start();
        }
        
        if (sort==2) {
            Timer insertionTimer  = new Timer(speed, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((Timer)e.getSource()).setDelay(speed);
                    if (isSorted() || !isRunning()) {
                        setRunning(false);
                        if(isSorted()) {
                            finished();
                            insertionSort.setCompareIndex(Integer.MAX_VALUE);
                            insertionSort.setArrayIndex(Integer.MAX_VALUE);
                        }
                        else if(!EventPanelChanged.isChanged())
                            Logger.addLog("Paused", TimeTaken.time(false));
                        ((Timer)e.getSource()).stop();
                    } 
                    else if (isRunning())
                            array = insertionSort.sortOnlyOneItem();
                    repaint();
                }
            });
            insertionTimer.start();
        }
        
        if (sort==3) {
            Timer mergeTimer  = new Timer(speed, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((Timer)e.getSource()).setDelay(speed);
                    if (isSorted() || !isRunning()) {
                        setRunning(false);
                        if(isSorted()) {
                            finished();
                            mergeSort.setArray_index(Integer.MAX_VALUE);
                            mergeSort.setCompare_index(Integer.MAX_VALUE);
                            mergeSort.setStore_index(Integer.MAX_VALUE);
                            mergeSort.setLow(Integer.MAX_VALUE);
                            mergeSort.setHigh(Integer.MAX_VALUE);
                        }
                        else if(!EventPanelChanged.isChanged())
                            Logger.addLog("Paused", TimeTaken.time(false));
                        ((Timer)e.getSource()).stop();
                    } 
                    else if (isRunning())
                            array = mergeSort.sortOnlyOneItem();
                    repaint();
                }
            });
            mergeTimer.start();
        }
        
        if (sort==4) {
            Timer quickTimer  = new Timer(speed, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((Timer)e.getSource()).setDelay(speed);
                    if (isSorted() || !isRunning()) {
                        setRunning(false);
                        if(isSorted()) {
                            finished();
                            quickSort.setSP(-1);
                            quickSort.push(0);
                            quickSort.push(length-1);
                            quickSort.setArrayIndex(Integer.MAX_VALUE);
                            quickSort.setCompareIndex(Integer.MAX_VALUE);
                            quickSort.setPartition(-1);
                            quickSort.setIsPartioning(false);
                        }
                        else if(!EventPanelChanged.isChanged())
                            Logger.addLog("Paused", TimeTaken.time(false));
                        ((Timer)e.getSource()).stop();
                    } 
                    else if (isRunning())
                            array = quickSort.sortOnlyOneItem();
                    repaint();
                }
            });

            quickTimer.start();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(220, 220, 220));
        g.drawRect(0, 0, getWidth()-1, getHeight()-1);
        int width = getWidth()-2, height = getHeight()-2;
        for (int i = 0; i < length; i++) {
            int barWidth=width/length , offX=1+(width-(barWidth*length))/2, offY =1+(height-500)/2;
            g.setColor(new Color(0, 197, 141));
            if(started) {
                if (sort==0) {
                    if(i == bubbleSort.getCompareIndex())
                        g.setColor(Color.red);
                    if(i == (bubbleSort.getCompareIndex() + 1))
                        g.setColor(Color.blue);
                }
                if (sort==1) {
                    if(i==selectionSort.getCompareIndex())
                        g.setColor(Color.blue);
                    if(i==selectionSort.getExtremeIndex())
                        g.setColor(new Color(160, 32, 240));
                    if(i==selectionSort.getArrayIndex())
                        g.setColor(Color.red);
                }
                if (sort==2) {

                    if (i == insertionSort.getCompareIndex())
                        g.setColor(Color.blue);
                    if (i == insertionSort.getArrayIndex())
                        g.setColor(Color.red);
                }

                if (sort==3) {
                    if (i == mergeSort.getArray_index() || i == mergeSort.getCompare_index())
                        g.setColor(Color.blue);
                    if (i == mergeSort.getStore_index())
                        g.setColor(Color.red);
                    if (i == mergeSort.getLow() - 1 || i == mergeSort.getHigh() + 1)
                        g.setColor(new Color(160, 32, 240));
                }

                if (sort==4) {

                    if (i == quickSort.getArrayIndex()) {
                        g.setColor(Color.red);
                    }

                    if (i == quickSort.getCompareIndex()) {
                        g.setColor(Color.blue);
                    }

                    if (i == quickSort.getPartition())
                        g.setColor(new Color(160, 32, 240));
                }
            }
            g.fillRect(1+offX+(i*barWidth), height - offY - getArray()[i], barWidth-1, getArray()[i]);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
