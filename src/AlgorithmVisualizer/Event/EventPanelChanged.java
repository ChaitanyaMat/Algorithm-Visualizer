package AlgorithmVisualizer.Event;

public class EventPanelChanged {

    public static boolean isChanged() {
        return changed;
    }

    public static void setChanged(boolean change) {
        changed = change;
    }
    
    private static boolean changed;
}