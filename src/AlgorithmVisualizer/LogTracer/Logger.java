package AlgorithmVisualizer.LogTracer;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Logger {

    public static JTable getCurrent() {
        return current;
    }

    public static void setCurrent(JTable aCurrent) {
        current = aCurrent;
    }
    
    public static boolean isEnable() {
        return enable;
    }

    public static void setEnable(boolean aEnable) {
        enable = aEnable;
    }
    
    private static JTable current;
    private static int index = 1;
    private static boolean enable = true;
    
    public static void addLog(String log, String time) {
        if(!enable)
            return ;
        DefaultTableModel model = (DefaultTableModel) current.getModel();
        model.addRow(new Object[]{index++, log, time});
        current.scrollRectToVisible(current.getCellRect(current.getRowCount()-1, 0, true));
    }
    
    public static void emptyLog() {
        if(current == null || !enable)
            return;
        DefaultTableModel model = (DefaultTableModel)current.getModel();
        model.setRowCount(0);
        index = 1;
        enable = true;
    }
}