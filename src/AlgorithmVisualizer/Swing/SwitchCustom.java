package AlgorithmVisualizer.Swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import AlgorithmVisualizer.Event.EventSwitchSelected;

public class SwitchCustom extends Component {

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        timer.start();
        runEvent();
    }
    
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    private Timer timer;
    private float location;
    private boolean selected;
    private boolean mouseOver;
    private boolean enabled;
    private float speed = 15f;
    private List<EventSwitchSelected> events;

    public SwitchCustom() {
        setEnabled(true);
        setBackground(new Color(0, 197, 141));
        setPreferredSize(new Dimension(50, 25));
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        events = new ArrayList<>();
        location = 2;
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isSelected()) {
                    int endLocation = getWidth() - getHeight() + 2;
                    if (location < endLocation) {
                        location += speed;
                        repaint();
                    } else {
                        timer.stop();
                        location = endLocation;
                        repaint();
                    }
                } else {
                    int endLocation = 2;
                    if (location > endLocation) {
                        location -= speed;
                        repaint();
                    } else {
                        timer.stop();
                        location = endLocation;
                        repaint();
                    }
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (enabled&&mouseOver) {
                        setSelected(!selected);
                    }
                }
            }
        });
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, 15, 15);
        g2.setColor(getForeground());
        g2.fillOval((int) location, 2, height - 4, height - 4);
        super.paint(grphcs);
    }

    private void runEvent() {
        for (EventSwitchSelected event : events) {
            event.onSelected(selected);
        }
    }

    public void addEventSelected(EventSwitchSelected event) {
        if(enabled)
            events.add(event);
    }
}