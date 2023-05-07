
package Splashscreen;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class SplashProgressBar extends JProgressBar {
    
    public SplashProgressBar(){
        setPreferredSize(new Dimension(100, 4));
        setForeground(new Color(242, 242, 242));
        setUI(new BasicProgressBarUI());
    }
}
