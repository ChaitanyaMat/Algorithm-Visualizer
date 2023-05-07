package AlgorithmVisualizer.Panels;

import AlgorithmVisualizer.BackTracking.MazeGen;
import AlgorithmVisualizer.BackTracking.NQueen;
import AlgorithmVisualizer.Event.EventAlgorithmFinished;
import AlgorithmVisualizer.Event.EventPanelChanged;
import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;
import AlgorithmVisualizer.Model.ModelChessCell;
import AlgorithmVisualizer.Model.ModelMazeCell;
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

public class BackTrackPanel extends javax.swing.JPanel {

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isRunning() {
        if (EventPanelChanged.isChanged())
            return false;
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getcSize() {
        return cSize;
    }

    public void setcSize(int size) {
        this.cSize = size;
        if(index == 0)
            this.cSize *= 10;
        maze = new MazeGen(cSize);
        queen = new NQueen(cSize);
        setStarted(false);
    }
    
    private int index, cSize, speed;
    private boolean running, started;
    
    private MazeGen maze;
    private NQueen queen;
    public ModelMazeCell currentM;
    public ModelChessCell currentC;
    
    public BackTrackPanel() {
        initComponents();
    }

    public void animate() throws Exception{
        if(started)
            Logger.addLog("Unpaused", TimeTaken.time(true));
        else {
            if(index == 0) {
                Logger.addLog("Maze Size = "+(699/cSize)+"*"+(557/cSize)+".", TimeTaken.time(true));
                Logger.addLog("All Walls are Up.", TimeTaken.time(false));
            }
            else if(index == 1)
                Logger.addLog("Board Size = "+cSize+"*"+cSize+".", TimeTaken.time(true));
        }
        started = true;
        EventPanelChanged.setChanged(false);
        if(index == 0) {
            maze.setCurrent(maze.getCurrent()==null?maze.getBlock()[0]:maze.getCurrent());
            Timer mazeTimer = new Timer(speed, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((Timer)e.getSource()).setDelay(speed);
                    if(!isRunning() || maze.getCurrent()==null){
                        setRunning(false);
                        if(maze.getCurrent()==null){
                            EventAlgorithmFinished.toggleButton();
                            Logger.addLog("Maze Generated.", TimeTaken.time(false));
                            Notification noti=new Notification(AlgorithmVisualizer.AlgoVis.getFrame(), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Maze Generated");
                            noti.showNotification();
                            currentM = null;
                        }
                        else if(!EventPanelChanged.isChanged())
                            Logger.addLog("Paused", TimeTaken.time(false));
                        ((Timer)e.getSource()).stop();
                    }
                    else{
                        currentM=maze.getCurrent();
                        maze.gen(currentM);
                    }
                    repaint();
                }

            });
            mazeTimer.start();
        }
        
        if(index == 1) {
            if(queen.getCurrent()==null)
                queen.setCurrent(queen.getGrid()[0]);
            Timer queenTimer = new Timer(speed, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((Timer)e.getSource()).setDelay(speed);
                    if(!isRunning() || queen.isDone()){
                        setRunning(false);
                        if(queen.isDone()) {
                            EventAlgorithmFinished.toggleButton();
                            if(queen.isSolved()) {
                                Logger.addLog(cSize+" Queens Placed.", TimeTaken.time(false));
                                Notification noti=new Notification(AlgorithmVisualizer.AlgoVis.getFrame(), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, cSize+" Queens Placed");
                                noti.showNotification();
                            }
                            else {
                                Logger.addLog("Solution Does Not Exist", TimeTaken.time(false));
                                Notification noti=new Notification(AlgorithmVisualizer.AlgoVis.getFrame(), Notification.Type.INFO, Notification.Location.TOP_CENTER, "Solution Does Not Exist");
                                noti.showNotification();
                            }
                        }
                        else if(!EventPanelChanged.isChanged())
                            Logger.addLog("Paused", TimeTaken.time(false));
                        ((Timer)e.getSource()).stop();
                        currentC = null;
                    }
                    else{
                        currentC=queen.getCurrent();
                        queen.place(currentC);
                    }
                    repaint();
                }

            });
            queenTimer.start();
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(220, 220, 220));
        g.drawRect(0, 0, getWidth()-1, getHeight()-1);
        int width = getWidth()-2, height = getHeight()-2;
        if(index == 0 && cSize!=0) {
            int xoff=1+(width-(699/cSize)*cSize)/2;
            int yoff=1+(height-(557/cSize)*cSize)/2;
            for(ModelMazeCell c:maze.getBlock()){
                int x=xoff+c.getX()*cSize,y=yoff+c.getY()*cSize;
                g.setColor(Color.darkGray);
                if(c.isVisited())
                    g.setColor(new Color(245, 245, 245));
                if(c==currentM)
                    g.setColor(new Color(197, 0, 56));
                g.fillRect(x,y,cSize,cSize);
                g.setColor(new Color(0, 197, 141));
                if(c.getWalls()[0])
                    g.drawLine(x,y,x+cSize-1,y);
                if(c.getWalls()[1])
                    g.drawLine(x+cSize-1,y,x+cSize-1,y+cSize-1);
                if(c.getWalls()[2])
                    g.drawLine(x,y+cSize-1,x+cSize-1,y+cSize-1);
                if(c.getWalls()[3])
                    g.drawLine(x,y,x,y+cSize-1);
            }
        }
        
        if(index == 1 && cSize!=0) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            int size = ((height-2)/cSize) - 1;
            int xoff = 1 + (width-(size*cSize))/2;
            int yoff = 1 + (height-(size*cSize))/2;
            g2.setFont(new java.awt.Font("SansSerif", 0, size));
            FontMetrics ft = g2.getFontMetrics();
            Rectangle2D r2 = ft.getStringBounds("♛", g2);
            int textoff = (int)(size-r2.getHeight())/2;
            Color dark = new Color(0, 197, 141);
            Color light = new Color(245, 245, 245);
            g2.setColor(dark);
            g2.drawRect(xoff-1, yoff-1, cSize*size+1, cSize*size+1);
            for(ModelChessCell c:queen.getGrid()) {
                int x=c.getX(), y=c.getY();
                    if(c.isDark())
                        g2.fillRect((x*size)+xoff, yoff+(y*size), size, size);
                    if(c.isOccupied()) {
                        g2.setColor(Color.blue);
                        if(queen.isSolved())
                            g2.setColor(new Color(160, 32, 240));//c.isDark()?light:dark);
                        g2.drawString("♛", (x*size)+xoff, yoff+textoff+((1+y)*size));
                    }
                    if(c==currentC) {
                        g2.setColor(Color.red);
                        g2.drawString("♛", (x*size)+xoff, yoff+textoff+((1+y)*size));
                    }
                    g2.setColor(dark);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}