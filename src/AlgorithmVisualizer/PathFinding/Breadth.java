package AlgorithmVisualizer.PathFinding;

import AlgorithmVisualizer.BackTracking.MazeGen;
import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;
import AlgorithmVisualizer.Model.ModelMazeCell;
import java.util.Arrays;

public class Breadth {

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
        push(open, begin);
        top = 0;
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
    private int top;
    
    public Breadth(MazeGen maze) {
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
        if(list == open)
            top++;
        for(int i=list.length-2;i>=0;i--) {
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
    
    private void reconstructPath() {
        ModelMazeCell c = goal;
        int count = 0;
        push(path, c);
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
        top--;
    }
    
    private boolean inOpen(ModelMazeCell c) {
        for(ModelMazeCell i:open)
            if(c == i)
                return true;
        return false;
    }
    
    public void find() {
        if(notEmpty()) {
            current = open[top];
            current.setVisited(true);
            if(current == goal) {
                found = true;
                done = true;
                reconstructPath();
                Logger.addLog("Reconstructing Path.", TimeTaken.time(false));
                return;
            }
            for(int i=0; i<4; i++){
                ModelMazeCell c = current.getPfneighbours()[i];
                if(c == null)
                    continue;
                if(!inOpen(c) && !c.isVisited()) {
                    c.setPrevious(current);
                    Logger.addLog("Pushing ("+(c.getX()+1)+","+(c.getY()+1)+") into the Open List.", TimeTaken.time(false));
                    push(open, c);
                }
            }
            removeCurrent();
            Logger.addLog("Removing ("+(current.getX()+1)+","+(current.getY()+1)+") from Open List.", TimeTaken.time(false));
        }
        else {
            current = null;
            done = true;
        }
    }
}
