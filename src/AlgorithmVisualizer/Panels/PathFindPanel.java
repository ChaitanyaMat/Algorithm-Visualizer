package AlgorithmVisualizer.Panels;

import AlgorithmVisualizer.BackTracking.MazeGen;
import AlgorithmVisualizer.Event.EventAlgorithmFinished;
import AlgorithmVisualizer.Event.EventPanelChanged;
import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;
import AlgorithmVisualizer.Model.ModelMazeCell;
import AlgorithmVisualizer.PathFinding.AStar;
import AlgorithmVisualizer.PathFinding.Breadth;
import AlgorithmVisualizer.PathFinding.Depth;
import AlgorithmVisualizer.PathFinding.Dijkstra;
import AlgorithmVisualizer.Swing.Notification.Notification;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class PathFindPanel extends javax.swing.JPanel {

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = (int)((speed - 1)*(-250/9.0)+250);
    }

    public boolean isDone() {
        if(index == 0)
            return astar.isDone();
        if(index == 1)
            return dijkstra.isDone();
        if(index == 2)
            return bfs.isDone();
        if(index == 3)
            return dfs.isDone();
        return false;
    }
    
    public void find() {
        if(index == 0)
            astar.find();
        if(index == 1)
            dijkstra.find();
        if(index == 2)
            bfs.find();
        if(index == 3)
            dfs.find();
    }

    public void getPath() {
        if(index == 0)
            path = astar.getPath();
        if(index == 1)
            path = dijkstra.getPath();
        if(index == 2)
            path = bfs.getPath();
        if(index == 3)
            path = dfs.getPath();
    }

    public void setPath() {
        this.path = new ModelMazeCell[maze.getBlock().length];
        astar.setPath(path);
        dijkstra.setPath(path);
        bfs.setPath(path);
        dfs.setPath(path);
    }

    public ModelMazeCell getBegin() {
        return begin;
    }

    public void setBegin(ModelMazeCell begin) {
        if(begin == goal) {
            Notification noti=new Notification(AlgorithmVisualizer.AlgoVis.getFrame(), Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Start and Goal cannot be same");
            noti.showNotification();
            return ;
        }
        this.begin = begin;
        astar.setBegin(begin);
        dijkstra.setBegin(begin);
        bfs.setBegin(begin);
        dfs.setBegin(begin);
    }

    public ModelMazeCell getGoal() {
        return goal;
    }

    public void setGoal(ModelMazeCell goal) {
        if(goal == begin) {
            Notification noti=new Notification(AlgorithmVisualizer.AlgoVis.getFrame(), Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Start and Goal cannot be same");
            noti.showNotification();
            return ;
        }
        this.goal = goal;
        astar.setGoal(goal);
        dijkstra.setGoal(goal);
        bfs.setGoal(goal);
        dfs.setGoal(goal);
    }

    public void setCurrent() {
        if(index == 0)
            current = astar.getCurrent();
        if(index == 1)
            current = dijkstra.getCurrent();
        if(index == 2)
            current = bfs.getCurrent();
        if(index == 3)
            current = dfs.getCurrent();
    }

    public boolean isFound() {
        if(index == 0)
            return astar.isFound();
        if(index == 1)
            return dijkstra.isFound();
        if(index == 2)
            return bfs.isFound();
        if(index == 3)
            return dfs.isFound();
        return false;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public MazeGen getMaze() {
        return maze;
    }

    public void setMaze() {
        this.maze = new MazeGen(cSize);
        int x = (int)(Math.random()*(699/cSize));
        int y = (int)(Math.random()*(557/cSize));
        maze.setCurrent(getMaze().getBlock()[x+(y*(699/cSize))]);
        Logger.setEnable(false);
        while(maze.getCurrent()!= null)
            getMaze().gen(maze.getCurrent());
        //maze.randomiseWalls();
        TimeTaken.resetTime();
        Logger.setEnable(true);
        astar = new AStar(maze);
        dijkstra = new Dijkstra(maze);
        bfs = new Breadth(maze);
        dfs = new Depth(maze);
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

    public void setcSize(int cSize) {
        this.cSize = 10*cSize;
        setStarted(false);
        setFound(false);
        setMaze();
        setGoal(maze.getBlock()[maze.getBlock().length-1]);
        setBegin(maze.getBlock()[0]);
        setPath();
    }
    
    private int index, cSize, speed;
    private boolean running, started, found;
    private MazeGen maze;
    private ModelMazeCell begin, goal, current;
    private ModelMazeCell[] path;
    
    AStar astar;
    Dijkstra dijkstra;
    Breadth bfs;
    Depth dfs;
    
    public PathFindPanel() {
        initComponents();
        init();
    }

    private void init() {
        setcSize(10);
    }
    
    public void animate() throws Exception{
        if(started)
            Logger.addLog("Unpaused", TimeTaken.time(true));
        else {
            Logger.addLog("Start = ("+(begin.getX()+1)+","+(begin.getY()+1)+"). Goal = ("+(goal.getX()+1)+","+(goal.getY()+1)+").", TimeTaken.time(true));
            if(index == 1)
                Logger.addLog("Pushing all cells into Open List.", TimeTaken.time(false));
        }
        setStarted(true);
        EventPanelChanged.setChanged(false);
        Timer pfTimer = new Timer(speed, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Timer)e.getSource()).setDelay(speed);
                if(!isRunning() || isDone()){
                    setRunning(false);
                    if(isDone()) {
                        EventAlgorithmFinished.toggleButton();
                        if(found = isFound()){
                            getPath();
                            Logger.addLog("Path Found of Length = "+(path.length-2)+".", TimeTaken.time(false));
                            Notification noti=new Notification(AlgorithmVisualizer.AlgoVis.getFrame(), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Path Found");
                            noti.showNotification();

                        }
                        else {
                            Logger.addLog("Path not Found.", TimeTaken.time(false));
                            Notification noti=new Notification(AlgorithmVisualizer.AlgoVis.getFrame(), Notification.Type.INFO, Notification.Location.TOP_CENTER, "Path Not Found");
                            noti.showNotification();
                        }
                    }
                    
                    ((Timer)e.getSource()).stop();
                }
                else{
                    find();
                    setCurrent();
                }
                repaint();
            }

        });
        pfTimer.start();
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
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

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if(isStarted())
            return;
        int w = getWidth()-2, h = getHeight()-2;
        int xoff = 1+(w-(699/cSize)*cSize)/2;
        int yoff = 1+(h-(557/cSize)*cSize)/2;
        int x = (evt.getX() - xoff) / cSize;
        int y = (evt.getY() - yoff) / cSize;
        if(x>=0 && x<976/cSize && y>=0 && y<553/cSize) {
            ModelMazeCell c = maze.getBlock()[x+(y*(699/cSize))];
            if(SwingUtilities.isLeftMouseButton(evt))
                setBegin(c);
            else if(SwingUtilities.isRightMouseButton(evt))
                setGoal(c);
            repaint();
        }
    }//GEN-LAST:event_formMouseClicked

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if(isStarted())
            return;
        int w = getWidth()-2, h = getHeight()-2;
        int xoff = 1+(w-(699/cSize)*cSize)/2;
        int yoff = 1+(h-(557/cSize)*cSize)/2;
        int x = (evt.getX() - xoff) / cSize;
        int y = (evt.getY() - yoff) / cSize;
        if(x>=0 && x<699/cSize && y>=0 && y<557/cSize){
            ModelMazeCell c = maze.getBlock()[x + (y * (699/cSize))];
            if(SwingUtilities.isLeftMouseButton(evt)) {
                if(index == 0)
                    astar.updateNeighbour(c, true);
                if(index == 1)
                    dijkstra.updateNeighbour(c, true);
                if(index == 2)
                    bfs.updateNeighbour(c, true);
                if(index == 3)
                    dfs.updateNeighbour(c, true);
                
            }
            else if(SwingUtilities.isRightMouseButton(evt)) {
                if(index == 0)
                    astar.updateNeighbour(c, false);
                if(index == 1)
                    dijkstra.updateNeighbour(c, false);
                if(index == 2)
                    bfs.updateNeighbour(c, false);
                if(index == 3)
                    dfs.updateNeighbour(c, false);
            }
            repaint();
        }
    }//GEN-LAST:event_formMouseDragged

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(new Color(220, 220, 220));
        g2.drawRect(0, 0, getWidth()-1, getHeight()-1);
        int w = getWidth()-2, h = getHeight()-2;
        int xoff=1+(w-(699/cSize)*cSize)/2;
        int yoff=1+(h-(557/cSize)*cSize)/2;
        for(ModelMazeCell c:getMaze().getBlock()){
            int x=xoff+c.getX()*cSize,y=yoff+c.getY()*cSize;
            if(found) {
                g2.setColor(Color.magenta);
                g2.setStroke(new BasicStroke(Math.max(cSize/10, 2)));
                for(ModelMazeCell i:path) {
                    if(c == i && c != begin){
                        for(int j=0; j<4; j++) {
                            if(c.getPfneighbours()[j] == c.getPrevious()){
                                if(j==0)
                                    g2.drawLine(x + cSize/2, y + cSize/2, x + cSize/2, y - (c.getPrevious() == begin?-1:cSize/2));
                                if(j==1)
                                    g2.drawLine(x + cSize/2, y + cSize/2, x + (3*cSize)/2, y + cSize/2);
                                if(j==2)
                                    g2.drawLine(x + cSize/2, y + cSize/2, x + cSize/2, y + (3*cSize)/2);
                                if(j==3)
                                    g2.drawLine(x + cSize/2, y + cSize/2, x - (c.getPrevious() == begin?-1:cSize/2), y + cSize/2);
                            }
                        }
                        break;
                    }
                }
            }
            else{
                g2.setColor(new Color(245, 245, 245));
                if(c.isVisited())
                    g2.setColor(Color.blue);
                if(c == current)
                    g2.setColor(Color.red);
                g2.fillRect(x,y,cSize,cSize);
            }
            g2.setStroke(new BasicStroke(1));
            g2.setColor(new Color(0, 197, 141));
            if(c.getWalls()[0] && c.getWalls()[1] && c.getWalls()[2] && c.getWalls()[3])
                g2.fillRect(x, y, cSize, cSize);
            if(c == begin || c == goal) {
                g2.setColor(Color.green);
                if(c == goal)
                    g2.setColor(new Color(197, 0, 56));
                g2.fillRect(x,y,cSize,cSize);
            }
            if(c.getWalls()[0])
                g2.drawLine(x,y,x+cSize-1,y);
            if(c.getWalls()[1])
                g2.drawLine(x+cSize-1,y,x+cSize-1,y+cSize-1);
            if(c.getWalls()[2])
                g2.drawLine(x,y+cSize-1,x+cSize-1,y+cSize-1);
            if(c.getWalls()[3])
                g2.drawLine(x,y,x,y+cSize-1);
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}