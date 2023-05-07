
package AlgorithmVisualizer.Swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class ButtonCustom extends JButton{

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorBorder() {
        return colorBorder;
    }

    public void setColorBorder(Color colorBorder) {
        this.colorBorder = colorBorder;
    }
    
    public ButtonCustom(){
        setColor(Color.WHITE);
        colorBorder = new Color(0,197,141);
        colorOver = new Color(153, 232, 209);
        colorClick = new Color(0,197,141);
        setBorder(null);
        setFocusable(false);
        setContentAreaFilled(false);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorOver);
                over=true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(color);
                over=false;
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(colorClick);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(over?colorOver:color);
            }
        });
        repaint();
        revalidate();
    }
    
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorBorder;
    private Color colorClick;
    private int radius = 0;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorBorder);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        g2.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, radius-1, radius-1);
        super.paintComponent(g);
    }
    
}
