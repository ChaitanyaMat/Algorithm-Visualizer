
package AlgorithmVisualizer.Component;

import AlgorithmVisualizer.Model.ModelCard;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;

public class Card extends javax.swing.JPanel {

    public Color getColorGradient() {
        return colorGradient;
    }

    public void setColorGradient(Color colorGradient) {
        this.colorGradient = colorGradient;
    }

    private Color colorGradient;
    
    public Card() {
        initComponents();
        setOpaque(false);
        setBackground(new Color(112,69,246));
        colorGradient=new Color(255,255,255);
    }
    
    public void setData(ModelCard data){
        DecimalFormat df=new DecimalFormat("#,##0.##");
        lb_title.setText(data.getTitle());
        lb_uses.setText("Uses : "+df.format(data.getUses()));
        lb_icon.setIcon(data.getImageIcon());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_title = new javax.swing.JLabel();
        lb_uses = new javax.swing.JLabel();
        lb_icon = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lb_title.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lb_title.setText("Title");

        lb_uses.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lb_uses.setText("Uses : 0");

        lb_icon.setPreferredSize(new java.awt.Dimension(40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb_uses)
                .addGap(0, 134, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_icon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_title, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_uses, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra=new GradientPaint(0 , getHeight() , getBackground() , getWidth() , 0 , colorGradient);
        g2.setPaint(gra);
        g2.fillRect(0 , 0 , getWidth() , getHeight());
        super.paintComponent(g);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb_icon;
    private javax.swing.JLabel lb_title;
    private javax.swing.JLabel lb_uses;
    // End of variables declaration//GEN-END:variables
}
