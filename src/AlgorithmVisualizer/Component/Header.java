package AlgorithmVisualizer.Component;

import AlgorithmVisualizer.AlgoVis;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import static java.awt.Frame.NORMAL;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Header extends javax.swing.JPanel {
    
    private int mouseX, mouseY;

    public Header() {
        initComponents();
    }

    public void addMenuEvent(ActionListener event){
        cmdMenu.addActionListener(event);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdMenu = new AlgorithmVisualizer.Swing.Button();
        close = new AlgorithmVisualizer.Swing.Button();
        screenSize = new AlgorithmVisualizer.Swing.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        cmdMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icon_menu_b.png"))); // NOI18N

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icon_close_b.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeMouseExited(evt);
            }
        });
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        screenSize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icon_fullscreen_b.png"))); // NOI18N
        screenSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                screenSizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                screenSizeMouseExited(evt);
            }
        });
        screenSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screenSizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cmdMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 938, Short.MAX_VALUE)
                .addComponent(screenSize, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmdMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(screenSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        System.exit(0);
    }//GEN-LAST:event_closeActionPerformed

    private void screenSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screenSizeActionPerformed
        JFrame fram=AlgoVis.getFrame();
        boolean max=fram.getExtendedState()== MAXIMIZED_BOTH;
        screenSize.setIcon(new ImageIcon(getClass().getResource("/Images/icon_"+(max?"fullscreen":"collapse")+"_b.png")));
        fram.setExtendedState(max?NORMAL:MAXIMIZED_BOTH);
    }//GEN-LAST:event_screenSizeActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        int w = getWidth()<1220? 240: 60;
        JFrame fram=AlgoVis.getFrame();
        fram.setLocation(x - mouseX - w, y - mouseY);
    }//GEN-LAST:event_formMouseDragged

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        close.setBackground(Color.red);
        close.setOpaque(true);
        close.setIcon(new ImageIcon(getClass().getResource("/Images/icon_close_w.png")));
    }//GEN-LAST:event_closeMouseEntered

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        close.setBackground(Color.white);
        close.setOpaque(false);
        close.setIcon(new ImageIcon(getClass().getResource("/Images/icon_close_b.png")));
    }//GEN-LAST:event_closeMouseExited

    private void screenSizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screenSizeMouseEntered
        screenSize.setBackground(new Color(200, 200, 200));
        screenSize.setOpaque(true);
    }//GEN-LAST:event_screenSizeMouseEntered

    private void screenSizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_screenSizeMouseExited
        screenSize.setBackground(Color.white);
        screenSize.setOpaque(false);
    }//GEN-LAST:event_screenSizeMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private AlgorithmVisualizer.Swing.Button close;
    private AlgorithmVisualizer.Swing.Button cmdMenu;
    private AlgorithmVisualizer.Swing.Button screenSize;
    // End of variables declaration//GEN-END:variables
}
