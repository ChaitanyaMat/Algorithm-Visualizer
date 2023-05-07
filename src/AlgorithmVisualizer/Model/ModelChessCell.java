package AlgorithmVisualizer.Model;

public class ModelChessCell {

    public boolean isDark() {
        return dark;
    }

    public void setDark(boolean dark) {
        this.dark = dark;
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

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    private int x;
    private int y;
    private boolean occupied;
    private boolean dark;

    public ModelChessCell(int x, int y) {
        this.x = x;
        this.y = y;
        occupied = false;
        dark = false;
    }
    
}
