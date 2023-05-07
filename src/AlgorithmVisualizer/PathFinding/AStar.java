package AlgorithmVisualizer.PathFinding;

import AlgorithmVisualizer.BackTracking.MazeGen;
import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;
import AlgorithmVisualizer.Model.ModelMazeCell;
import java.util.Arrays;

public class AStar {

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
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
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public ModelMazeCell getCurrent() {
        return current;
    }

    public void setCurrent(ModelMazeCell current) {
        this.current = current;
    }

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
        this.begin.setF(heuristic(begin)+1);
        setOpen();
    }

    public ModelMazeCell getGoal() {
        return goal;
    }

    public void setGoal(ModelMazeCell goal) {
        this.goal = goal;
    }
    
    private MazeGen maze;
    private ModelMazeCell begin, goal, current;
    private ModelMazeCell[] path, open;
    private boolean found, done;
    
    public AStar(MazeGen maze){
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
    
    private int heuristic(ModelMazeCell from) {
        int distance;
        distance = Math.abs(from.getX() - goal.getX()) + Math.abs(from.getY() - goal.getY());//Manhatten
/*
        int dx = Math.abs(from.getX() - goal.getX()), dy = Math.abs(from.getY() - goal.getY());
        double d2 = Math.sqrt(2);
        distance = (float)(dx + dy + (d2 - 2) * Math.min(dx, dy));//Diagonal
        distance = (float)Math.sqrt(Math.pow(from.getX() - goal.getX(), 2) + Math.pow(from.getY() - goal.getY(), 2));//Eucledian
*/
        if(from != begin)
            Logger.addLog("Heuristic for ("+(from.getX()+1)+","+(from.getY()+1)+") = "+distance+".", TimeTaken.time(false));
        return distance;
    }

    public void push(ModelMazeCell[] list, ModelMazeCell item) {
        for(int i=list.length-2;i>=0;i--)
        {
            if(list[i]!=null)
                list[i+1]=list[i];
        }
        list[0]=item;
    }
    
    private boolean notNull() {
        for(ModelMazeCell c:open)
            if(c != null)
                return true;
        return false;
    }
    
    private ModelMazeCell lowestOpen() {
        ModelMazeCell l = open[0];
        for(ModelMazeCell i:open) {
            if(i!=null && i.getF() < l.getF())
                l = i;
        }
        return l;
    }
    
    private void removeCurrent() {
        int count=0;
        for (ModelMazeCell i : open) {
            if (i == current) {
                continue;
            }
            open[count++] = i;
        }
    }
    
    public boolean inOpen(ModelMazeCell cell){
        boolean present = false;
        for(ModelMazeCell i:open)
            if(i == cell)
                present = true;
        return present;
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
    
    public void find() {
        if(notNull()){
            current = lowestOpen();
            current.setVisited(true);
            if(current == goal) {
                found = true;
                done = true;
                reconstructPath();
                Logger.addLog("Reconstructing Path.", TimeTaken.time(false));
                return;
            }
            removeCurrent();
            Logger.addLog("Removing ("+(current.getX()+1)+","+(current.getY()+1)+") from Open List.", TimeTaken.time(false));
            for(int i=0; i<4; i++){
                ModelMazeCell n = current.getPfneighbours()[i];
                if(n == null)
                    continue;
                int tentativeG = current.getG() + 1;
                if(tentativeG < n.getG()){
                    n.setPrevious(current);
                    int h = heuristic(n);
                    Logger.addLog("Updating G for ("+(n.getX()+1)+","+(n.getY()+1)+") to "+tentativeG+".", TimeTaken.time(false));
                    n.setG(tentativeG);
                    Logger.addLog("Updating F for ("+(n.getX()+1)+","+(n.getY()+1)+") to "+(n.getG()+h)+".", TimeTaken.time(false));
                    n.setF(n.getG() + h);
                    if(!inOpen(n)) {
                        Logger.addLog("Pushing ("+(n.getX()+1)+","+(n.getY()+1)+") into the Open List.", TimeTaken.time(false));
                        push(open, n);
                    }
                }
            }
        }
        else {
            current = null;
            done = true;
        }
    }
}
