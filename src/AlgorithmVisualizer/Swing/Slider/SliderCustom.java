package AlgorithmVisualizer.Swing.Slider;

import java.awt.Color;
import javax.swing.JSlider;

public class SliderCustom extends JSlider{
    public SliderCustom(){
        setOpaque(false);
        setBackground(new Color(180, 180, 180));
        setForeground(new Color(0, 197, 141));
        setUI(new SliderUI(this));
    }
}
