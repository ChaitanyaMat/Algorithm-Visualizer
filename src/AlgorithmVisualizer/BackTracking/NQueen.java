package AlgorithmVisualizer.BackTracking;

import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;
import AlgorithmVisualizer.Model.ModelChessCell;

public class NQueen {

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
        setGrid();
        setStack();
    }

    public ModelChessCell[] getGrid() {
        return grid;
    }

    public void setGrid() {
        boolean odd = gridSize%2==0;
        grid = new ModelChessCell[gridSize*gridSize];
        for(int i=0;i<gridSize;i++) {
            for(int j=0;j<gridSize;j++) {
                grid[(gridSize*i)+j] = new ModelChessCell(j,i);
                if((odd && (i+j)%2!=0) || (!odd && (i+j)%2==0))
                    grid[(gridSize*i)+j].setDark(true);
            }
        }
    }
    
    public int[][] getStack() {
        return stack;
    }

    public void setStack() {
        this.stack = new int[gridSize][2];
    }

    public ModelChessCell getCurrent() {
        return current;
    }

    public void setCurrent(ModelChessCell current) {
        this.current = current;
    }
    
    private boolean solved, done;
    private int gridSize, top;
    private int[][] stack;
    private ModelChessCell grid[], current;
    
    public NQueen(int size) {
        setGridSize(size);
        setTop(-1);
    }
    
    private boolean valid(ModelChessCell c) {
        for(int i=0; i<=top; i++)
            if(c.getX()==stack[i][0] || c.getY()==stack[i][1] || Math.abs(c.getX()-stack[i][0])==Math.abs(c.getY()-stack[i][1]))
                return false;
        return true;
    }
    
    private void push(ModelChessCell c) {
        stack[++top][0] = c.getX();
        stack[top][1] = c.getY();
    }

    private ModelChessCell pop() {
        if(top==-1) {
            Logger.addLog("Can't place Queen in File = A.", TimeTaken.time(false));
            setDone(true);
            setSolved(false);
            return null;
        }
        int x = stack[top][0], y = stack[top--][1];
        grid[x+(y*gridSize)].setOccupied(false);
        y++;
        Logger.addLog("Can't place Queen in File = "+(char)(x+66)+".", TimeTaken.time(false));
        Logger.addLog("Back Tracking to previous File = "+(char)(x+65)+".", TimeTaken.time(false));
        if(y==gridSize)
                return pop();
        return grid[x+(y*gridSize)];
    }
    
    public void place(ModelChessCell c) {
        if(top==gridSize-1){
            setDone(true);
            setSolved(true);
            return;
        }
        int x=c.getX(), y=c.getY()+1;
        if(valid(c)) {
            push(c);
            c.setOccupied(true);
            Logger.addLog("Placing Queen on Cell = "+(char)(x+65)+""+(gridSize+1-y)+".", TimeTaken.time(false));
            if(x+1 != gridSize) {
                Logger.addLog("Moving to File = "+(char)(x+66)+".", TimeTaken.time(false));
                Logger.addLog("Current cell = "+(char)(x+66)+""+gridSize+".", TimeTaken.time(false));
            }
            this.current = grid[(x+1)%gridSize];
            return;
        }
        if(y==gridSize)
            this.current = pop();
        else {
            this.current = grid[x+(y*gridSize)];
            Logger.addLog("Moving to Rank = "+(gridSize-y)+".", TimeTaken.time(false));
        }
    }
}
