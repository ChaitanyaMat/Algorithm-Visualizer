package AlgorithmVisualizer.Panels;

import AlgorithmVisualizer.Cipher.Cipher;
import AlgorithmVisualizer.Event.EventPanelChanged;
import AlgorithmVisualizer.Swing.Notification.Notification;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;

public class CipherPanel extends javax.swing.JPanel {

    public boolean isRunning() {
        if (EventPanelChanged.isChanged())
            return false;
        return running;

    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public String getMsg() {
        return cipher.getMsg();
    }

    public void setMsg(String msg) {
        cipher = new Cipher(msg, index);
        this.msg = cipher.getMsg();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private int index;
    private String msg="";
    private boolean running;
    
    private Cipher cipher;
    
    public CipherPanel() {
        initComponents();
    }

    public void animate()throws Exception {
        EventPanelChanged.setChanged(false);
        Timer cipherTimer = new Timer(0, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isRunning() || cipher.getIndex()==msg.length()){
                    if(cipher.getIndex()==msg.length()){
                        Notification noti=new Notification(AlgorithmVisualizer.AlgoVis.getFrame(), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Message Encoded");
                        noti.showNotification();
                    }
                    setRunning(false);
                    ((Timer)e.getSource()).stop();
                }
                else {
                    cipher.code();
                    msg=cipher.getMsg();
                }
                repaint();
            }

        });
        cipherTimer.start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2.setStroke(new BasicStroke(2));
        int length=msg.length();
        if(length==0)
            return;
        int a=(length-1)/25;
        int size=getWidth()/(length>5?(length>25?25:length):5);
        int offsetY = (getHeight() - (size*(a+1)))/(2+a);
        int[] offsetX = new int[a+1];
        int l=length;
        for(int i=0;i<=a;i++){
            offsetX[i]=(getWidth()-(size*Math.min(25,l)))/2;
            l -= 25;
        }
        for(int i=0;i<=a;i++) {
            for(int j=0 ;j<Math.min(25, length);j++){
                int index = (i*25)+j;
                g2.setColor(new Color(0, 197, 141));
                g2.drawRect(offsetX[i]+(j*size),offsetY+(i*(size+offsetY)),size-1,size-1);
                if(index-1==cipher.getIndex()){
                    g2.setColor(Color.red);
                    g2.fillRect(offsetX[i]+(j*size),offsetY+(i*(size+offsetY))-1,size,size+2);
                    g2.setColor(new Color(245, 245, 245));
                }                
                g2.setFont(new java.awt.Font("SansSerif", 1, size-5));
                FontMetrics ft = g2.getFontMetrics();
                Rectangle2D r2 = ft.getStringBounds(""+msg.charAt(index), g2);
                int toffx=(int)(size-r2.getWidth())/2;
                int toffy=(int)(size-r2.getHeight())/2;
                boolean sp=!(Character.isLetterOrDigit(msg.charAt(index)));
                g2.drawString(""+msg.charAt(index), toffx+offsetX[i]+(j*size), toffy+((i+1)*(size+offsetY))-(size/(sp?4:6)));
            }
            length -= 25;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}