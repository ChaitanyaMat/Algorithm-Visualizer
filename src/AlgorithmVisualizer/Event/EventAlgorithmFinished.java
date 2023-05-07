package AlgorithmVisualizer.Event;

import AlgorithmVisualizer.Swing.ButtonCustom;

public class EventAlgorithmFinished {

    public static ButtonCustom getReset() {
        return reset;
    }

    public static void setReset(ButtonCustom aReset) {
        reset = aReset;
    }

    public static ButtonCustom getStop() {
        return stop;
    }

    public static void setStop(ButtonCustom aStop) {
        stop = aStop;
    }

    private static ButtonCustom reset, stop;
    
    public static void toggleButton() {
        reset.setEnabled(true);
        stop.setEnabled(false);
    }
    
}
