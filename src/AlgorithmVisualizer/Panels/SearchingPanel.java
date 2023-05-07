package AlgorithmVisualizer.Panels;

import AlgorithmVisualizer.Event.EventAlgorithmFinished;
import AlgorithmVisualizer.Event.EventPanelChanged;
import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;
import AlgorithmVisualizer.Searching.Binary;
import AlgorithmVisualizer.Searching.Linear;
import AlgorithmVisualizer.Swing.Notification.Notification;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;

public class SearchingPanel extends javax.swing.JPanel {

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = (int)((speed - 1)*(-250/9.0)+250);
    }

    public String getHoverVal() {
        return hoverVal;
    }

    public void setHoverVal(String hoverVal) {
        this.hoverVal = hoverVal;
    }

    public int getHoverX() {
        return hoverX;
    }

    public void setHoverX(int hoverX) {
        this.hoverX = hoverX;
    }

    public int getHoverY() {
        return hoverY;
    }

    public void setHoverY(int hoverY) {
        this.hoverY = hoverY;
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public double getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(double searchItem) {
        this.searchItem = searchItem;
        linearSearch.setSearch(searchItem);
        binarySearch.setSearch(searchItem);
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public double[] getArray() {
        return array;
    }

    public void setArray() {
        array=new double[length];
        for(int i=0;i<length;i++)
            array[i]=5*((int)(Math.random()*100)+1);
        if(search==1){
            for(int i=1; i<length; i++){
                double val=array[i];
                int j=i-1;
                for( ;j>=0 && array[j]>val; j--)
                    array[j+1]=array[j];
                if(j!=i-1)
                    array[j+1]=val;
            }
        }
        linearSearch = new Linear(array);
        binarySearch = new Binary(array);
    }

    public boolean isRunning() {
        if (EventPanelChanged.isChanged())
            return false;
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getSearch() {
        return search;
    }

    public void setSearch(int search) {
        this.search = search;
        
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
        this.setArray();
        setStarted(false);
    }

    private double[] array;
    private boolean running,found,done,hover,started;
    private double searchItem;
    private int length, search, hoverX, hoverY, speed;
    private String hoverVal;
    
    private Linear linearSearch;
    private Binary binarySearch;
    
    public SearchingPanel() {
        initComponents();
    }
    
    private String arrayToString() {
        String ar = "[";
        for(double i:array)
            ar += (int)(i/5) + ", ";
        ar = ar.substring(0, ar.length()-2) + "].";
        return ar;
    }
    
    private void finished() {
        if(found) {
            int ind = search == 0?linearSearch.getCompareIndex():binarySearch.getMid();
            String t=(searchItem==69*5)?"Ah! I see you are a man of culture as well":"Value Found at Index: "+ind;
            Notification noti=new Notification(AlgorithmVisualizer.AlgoVis.getFrame(), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, t);
            noti.showNotification();
            Logger.addLog("Value Found at Index = "+ind+".", TimeTaken.time(false));
        }
        else{
            Notification noti=new Notification(AlgorithmVisualizer.AlgoVis.getFrame(), Notification.Type.INFO, Notification.Location.TOP_CENTER, "Value Not Found");
            noti.showNotification();
            Logger.addLog("Value not present in the Array.", TimeTaken.time(false));
        }
        EventAlgorithmFinished.toggleButton();
    }

    public void animate() throws Exception{
        Logger.addLog(started?"Unpaused":"Array = "+arrayToString(), TimeTaken.time(true));
        Logger.addLog("Search Value = "+(int)searchItem, TimeTaken.time(false));
        
        started = true;
        
        EventPanelChanged.setChanged(false);
        if (search==0) {
            Timer linearTimer  = new Timer(speed, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((Timer)e.getSource()).setDelay(speed);
                    if(isDone() || !isRunning()){
                        if(isDone())
                            finished();
                        else if(!EventPanelChanged.isChanged())
                            Logger.addLog("Paused", TimeTaken.time(false));
                        setRunning(false);
                        ((Timer)e.getSource()).stop();
                        
                    }
                    else if(isRunning()){
                        setDone(linearSearch.searchItem());
                        if(isDone() && linearSearch.getCompareIndex()<array.length)
                            found=true;
                    }
                    repaint();
                }
            });
            linearTimer.start();
        }
        
        if (search==1) {
            Timer binaryTimer  = new Timer(speed, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((Timer)e.getSource()).setDelay(speed);
                    if(isDone() || !isRunning()){
                        if(isDone())
                            finished();
                        else if(!EventPanelChanged.isChanged())
                            Logger.addLog("Paused", TimeTaken.time(false));
                        setRunning(false);
                        ((Timer)e.getSource()).stop();
                        
                    }
                    else if(isRunning()){
                        setDone(binarySearch.searchItem());
                        if(isDone() && binarySearch.getB()<=binarySearch.getE())
                            found=true;
                    }
                    repaint();
                }
            });
            binaryTimer.start();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

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

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        setHover(false);
        int width = getWidth()-2, height = getHeight()-2;
        int w = width/length , offX = 1+(width-(w*length))/2, offY = 1+(height-500)/2;
        int x = evt.getX(), y = evt.getY();
        int relX = x-1-offX, relY = y+offY;
        if(relX>=0 && relX/w < length && relX%w < w-1) {
            int val = (int)array[relX/w];
            if(relY>=(height-val) && relY<height) {
                hoverX =x;
                hoverY = y;
                hoverVal = val/5+"";
                setHover(true);
            }
        }
        this.repaint();
    }//GEN-LAST:event_formMouseMoved

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        setHover(false);
        this.repaint();
    }//GEN-LAST:event_formMouseExited

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2.setColor(new Color(220, 220, 220));
        g2.drawRect(0, 0, getWidth()-1, getHeight()-1);
        int width = getWidth()-2, height = getHeight()-2;
        for (int i = 0; i < length; i++) {
            int barWidth=width/length , offX=1+(width-(barWidth*length))/2, offY =1+(height-500)/2;
            int val=(int)(array[i]);
            g2.setColor(new Color(0, 197, 141));
            if(search==0){
                if(i==linearSearch.getCompareIndex())
                    g2.setColor(found?new Color(160, 32, 240):Color.red);
            }
            if(search==1){
                if(i>=binarySearch.getB() && i<=binarySearch.getE() && !done)
                    g2.setColor(Color.blue);
                if(i==binarySearch.getMid())
                    g2.setColor(found?new Color(160, 32, 240):done?new Color(0, 197, 141):Color.red);
            }
            g2.fillRect(1+offX+(i*barWidth), height - offY - val, barWidth-1, val);
            if(length > 40)
                continue;
            g2.setColor(Color.black);
            int fs=(int)(barWidth/2);
            g2.setFont(new java.awt.Font("SansSerif", 0, fs));
            FontMetrics ft = g2.getFontMetrics();
            Rectangle2D r2 = ft.getStringBounds((val/5)+"", g2);
            int textoffx = (int)((barWidth - r2.getWidth())/2);
            g2.drawString((val/5)+"", textoffx + offX + (i*barWidth), height - 1 - offY - val);
        }
        if(isHover()) {
            g2.setColor(new Color(0, 27, 19));
            g2.setFont(new java.awt.Font("SansSerif", 0, getWidth()/50));
            FontMetrics ft = g2.getFontMetrics();
            Rectangle2D r2 = ft.getStringBounds(hoverVal, g2);
            g2.drawString(hoverVal, hoverX-(int)(r2.getWidth()/2), hoverY);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
