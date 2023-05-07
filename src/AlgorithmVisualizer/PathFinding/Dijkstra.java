package AlgorithmVisualizer.PathFinding;

import AlgorithmVisualizer.BackTracking.MazeGen;
import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;
import AlgorithmVisualizer.Model.ModelMazeCell;
import java.util.Arrays;

public class Dijkstra {

    public MazeGen getMaze() {
        return maze;
    }

    public void setMaze(MazeGen maze) {
        this.maze = maze;
        for(ModelMazeCell c:maze.getBlock()){
            c.setVisited(false);
        }
        setNeighbours();
    }

    public ModelMazeCell getBegin() {
        return begin;
    }

    public void setBegin(ModelMazeCell begin) {
        if(this.begin != null)
            this.begin.setG(Integer.MAX_VALUE);
        this.begin = begin;
        this.begin.setG(0);
        setOpen();
    }

    public ModelMazeCell getGoal() {
        return goal;
    }

    public void setGoal(ModelMazeCell goal) {
        this.goal = goal;
    }

    public ModelMazeCell getCurrent() {
        return current;
    }

    public void setCurrent(ModelMazeCell current) {
        this.current = current;
    }

    public ModelMazeCell[] getPath() {
        return path;
    }

    public void setPath(ModelMazeCell[] path) {
        this.path = path;
    }

    public ModelMazeCell[] getOpen() {
        return open;
    }

    public void setOpen() {
        this.open = new ModelMazeCell[maze.getBlock().length];
        for(ModelMazeCell c:maze.getBlock())
            push(open, c);
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    
    private MazeGen maze;
    private ModelMazeCell begin, goal, current;
    private ModelMazeCell[] path, open;
    private boolean found, done;
    
    public Dijkstra(MazeGen maze) {
        init(maze);
    }
    
    private void init(MazeGen maze) {
        setMaze(maze);
    }
    
    private void setNeighbours() {
        for(ModelMazeCell c:maze.getBlock()) {
            c.setPfneighbours(new ModelMazeCell[]{null, null, null, null});
            int x = c.getX(), y = c.getY(), w = 699/maze.getCellSize(), h = 557/maze.getCellSize();
            if(!c.getWalls()[0] && y > 0)
                c.getPfneighbours()[0] = maze.getBlock()[((y-1)*w)+x];
            if(!c.getWalls()[1] && x < w-1)
                c.getPfneighbours()[1] = maze.getBlock()[(y*w)+x+1];
            if(!c.getWalls()[2] && y < h-1)
                c.getPfneighbours()[2] = maze.getBlock()[((y+1)*w)+x];
            if(!c.getWalls()[3] && x > 0)
                c.getPfneighbours()[3] = maze.getBlock()[(y*w)+x-1];
        }
    }

    public void updateNeighbour(ModelMazeCell c, boolean set) {
        int x = c.getX(), y = c.getY(), h = 557/maze.getCellSize(), w = 699/maze.getCellSize();
        c.setWalls(new boolean[]{set, set, set, set});
        if(y > 0)
            maze.getBlock()[x+((y-1)*w)].getWalls()[2] = set;
        if(x < w-1)
            maze.getBlock()[x+1+(y*w)].getWalls()[3] = set;
        if(y < h-1)
            maze.getBlock()[x+((y+1)*w)].getWalls()[0] = set;
        if(x > 0)
            maze.getBlock()[x-1+(y*w)].getWalls()[1] = set;
        
        if(y == 0)
            c.getWalls()[0] = true;
        if(x == w-1)
            c.getWalls()[1] = true;
        if(y == h-1)
            c.getWalls()[2] = true;
        if(x == 0)
            c.getWalls()[3] = true;
        setNeighbours();
    }
    
    private void push(ModelMazeCell[] list, ModelMazeCell item) {
        for(int i=list.length-2;i>=0;i--)
        {
            if(list[i]!=null)
                list[i+1]=list[i];
        }
        list[0]=item;
    }
    
    private boolean notEmpty() {
        for(ModelMazeCell c:open)
            if(c != null && !c.isVisited())
                return true;
        return false;
    }
    
    private ModelMazeCell minDistance() {
        ModelMazeCell l = open[0];
        for(ModelMazeCell i:open) {
            if(i !=null && i.getG() < l.getG())
                l = i;
        }
        return l;
    }
    
    private void reconstructPath() {
        ModelMazeCell c = goal;
        int count = 0;
        while(c != null){
            count++;
            push(path, c);
            c = c.getPrevious();
        }
        path = Arrays.copyOfRange(path, 0, count);
    }
        
    private void removeCurrent() {
        int count=0;
        for (ModelMazeCell i : open) {
            if (i == current) {
                continue;
            }
            open[count++] = i;
        }
        for(int i=count; i<open.length; i++)
            open[i] = null;
    }
    
    public void find() {
        if(notEmpty()) {
            current = minDistance();
            current.setVisited(true);
            if(current == goal) {
                done = true;
                if(current.getG() < Integer.MAX_VALUE) {
                    found = true;
                    reconstructPath();
                    Logger.addLog("Reconstructing Path.", TimeTaken.time(false));
                }
                return;
            }
            removeCurrent();
            Logger.addLog("Removing ("+(current.getX()+1)+","+(current.getY()+1)+") from Open List.", TimeTaken.time(false));
            for(ModelMazeCell c:current.getPfneighbours()){
                if(c == null)
                    continue;
                int tentativeG = current.getG() + (int)((Math.random() * 4) + 1);//1;
                if(tentativeG < c.getG()) {
                    Logger.addLog("Updating G for ("+(c.getX()+1)+","+(c.getY()+1)+") to "+tentativeG+".", TimeTaken.time(false));
                    c.setG(tentativeG);
                    c.setPrevious(current);
                }
            }
        }
        else {
            current = null;
            done = true;
        }
    }
}
