package AlgorithmVisualizer.BackTracking;

import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;
import AlgorithmVisualizer.Model.ModelMazeCell;

public class MazeGen {

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
        setBlock();
        setStack();
    }

    public ModelMazeCell[] getBlock() {
        return block;
    }

    public void setBlock() {
        int h=557/cellSize,w=699/cellSize;
        block=new ModelMazeCell[h*w];
        for(int i=0;i<h;i++)
            for(int j=0;j<w;j++)
                getBlock()[(i*w)+j]=new ModelMazeCell(j,i);
        for(int i=0;i<h;i++)
        {
            for(int j=0;j<w;j++)
            {
                if(i-1 >= 0)
                    block[(i*w)+j].getNeighbours()[0]=getBlock()[((i-1)*w)+j];
                if(j+1 < w)
                    block[(i*w)+j].getNeighbours()[1]=getBlock()[(i*w)+j+1];
                if(i+1 < h)
                    block[(i*w)+j].getNeighbours()[2]=getBlock()[((i+1)*w)+j];
                if(j-1 >= 0)
                    block[(i*w)+j].getNeighbours()[3]=getBlock()[(i*w)+j-1];
            }
        }
    }

    public ModelMazeCell[] getStack() {
        return stack;
    }

    public void setStack() {
        stack=new ModelMazeCell[block.length];
    }

    public ModelMazeCell getCurrent() {
        return current;
    }

    public void setCurrent(ModelMazeCell current) {
        this.current = current;
    }
    private int cellSize;
    private ModelMazeCell block[],stack[], current;
    
    public MazeGen(int size) {
        setCellSize(size);
    }
    
    public int available(ModelMazeCell cell) {
        int n=4;
        for(int i=0;i<4;i++)
            if(cell.getNeighbours()[i]==null || cell.getNeighbours()[i].isVisited())
                n--;
        return n;
    }
    
    public void push(ModelMazeCell item) {
        for(int i=stack.length-1;i>=0;i--)
        {
            if(stack[i]!=null)
                stack[i+1]=stack[i];
        }
        stack[0]=item;
    }
    
    public ModelMazeCell pop() {
        ModelMazeCell r=stack[0];
        for(int i=1;i<stack.length && stack[i-1]!=null;i++)
            stack[i-1]=stack[i];
        return r;
    }
    
    public int next(ModelMazeCell cell, int s) {
        int open[]=new int[s];
        int count=0;
        for(int i=0;i<4;i++)
        {
            if(cell.getNeighbours()[i]==null || cell.getNeighbours()[i].isVisited())
                continue;
            open[count++]=i;
        }
        return open[(int)(Math.random()*s)];
    }
    
    public void gen(ModelMazeCell current) {
        if(current==null)
            return;
        int open=available(current);
        current.setVisited(true);
        
        Logger.addLog("Current Cell = ("+current.getX()+","+current.getY()+").", TimeTaken.time(false));
        if(open!=0)
        {
            Logger.addLog("Open Neighbours = "+open, TimeTaken.time(false));
            if(stack[0]!=null)
            {
                ModelMazeCell prev=pop();
                push(prev);
            }
            int n=next(current, open);
            switch(n) {
                case 0:Logger.addLog("Moving Up. Removing top Wall.", TimeTaken.time(false));
                    break;
                case 1:Logger.addLog("Moving Right. Removing right Wall.", TimeTaken.time(false));
                    break;
                case 2:Logger.addLog("Moving Down. Removing bottom Wall.", TimeTaken.time(false));
                    break;
                case 3:Logger.addLog("Moving Left. Removing left Wall.", TimeTaken.time(false));
                    break;
            }
            push(current);
            current.getWalls()[n]=false;
            current.getNeighbours()[n].getWalls()[(n+2)%4]=false;
            this.current=current.getNeighbours()[n];
        }
        else{
            //Logger.addLog("No Open Neighbours.",  TimeTaken.time(false));
            Logger.addLog("Back Tracking to Previous Cell.", TimeTaken.time(false));
            this.current=pop();
        }
    }
    
    public void randomiseWalls() {
        for(ModelMazeCell c:this.getBlock()) {
            if(Math.random()<0.25) {
                int x = c.getX(), y = c.getY(), h = 557/this.getCellSize(), w = 699/this.getCellSize();
                int index = (int)(Math.random()/0.25);
                c.getWalls()[index] = false;
                ModelMazeCell neighbour = c.getNeighbours()[index];
                if(neighbour != null)
                    neighbour.getWalls()[Math.abs((2+index)%4)] = false;
                if(y == 0)
                    c.getWalls()[0] = true;
                if(x == w-1)
                    c.getWalls()[1] = true;
                if(y == h-1)
                    c.getWalls()[2] = true;
                if(x == 0)
                    c.getWalls()[3] = true;
            }
        }
    }
}
