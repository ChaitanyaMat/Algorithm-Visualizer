
package AlgorithmVisualizer.Form;

import javax.swing.ImageIcon;

public class Form_Home extends javax.swing.JPanel {

    public Form_Home() {
        initComponents();
        setOpaque(false);
        initData();
    }

    private void initData(){
        bg.setIcon(new ImageIcon(getClass().getResource("/Images/home_screen_"+((int)(Math.random()*2)+1)+".png")));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        subtitle1 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        title.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        title.setText("Home");

        subtitle1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        subtitle1.setText("Most Popular");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subtitle1)
                    .addComponent(title))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(subtitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JLabel subtitle1;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
