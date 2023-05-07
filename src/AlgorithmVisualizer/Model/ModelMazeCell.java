
package AlgorithmVisualizer.Model;

public class ModelMazeCell {

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public ModelMazeCell[] getPfneighbours() {
        return pfneighbours;
    }

    public void setPfneighbours(ModelMazeCell[] pfneighbours) {
        this.pfneighbours = pfneighbours;
    }

    public ModelMazeCell getPrevious() {
        return previous;
    }

    public void setPrevious(ModelMazeCell previous) {
        this.previous = previous;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean[] getWalls() {
        return walls;
    }

    public void setWalls(boolean[] walls) {
        this.walls = walls;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ModelMazeCell[] getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ModelMazeCell[] neighbours) {
        this.neighbours = neighbours;
    }
    private int x;
    private int y;
    private int g;
    private int f;
    private boolean[] walls;
    private boolean visited;
    private ModelMazeCell[] neighbours;
    private ModelMazeCell[] pfneighbours;
    private ModelMazeCell previous;
    
    public ModelMazeCell(int x,int y)
    {
        this. x= x;
        this.y = y;
        walls = new boolean[]{true,true,true,true};
        visited = false;
        neighbours = new ModelMazeCell[]{null,null,null,null};
        pfneighbours = new ModelMazeCell[]{null,null,null,null};
        g = Integer.MAX_VALUE;
        previous = null;
    }
}
