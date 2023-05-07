
package AlgorithmVisualizer.Component;

import AlgorithmVisualizer.Event.EventMenu;
import AlgorithmVisualizer.Event.EventMenuSelected;
import AlgorithmVisualizer.Event.EventShowPopupMenu;
import AlgorithmVisualizer.Model.ModelMenu;
import AlgorithmVisualizer.Swing.MenuAnimation;
import AlgorithmVisualizer.Swing.MenuItem;
import AlgorithmVisualizer.Swing.ScrollBar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

public class Menu extends javax.swing.JPanel {

    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }
    
    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    private final MigLayout layout;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;
            
    
    public Menu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
    }
    public void initMenuItem() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/Images/icon_home_b.png")), "Home"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/Images/icon_sorting_b.png")), "Sorting" , "Bubble Sort" , " Selection Sort" , "Insertion Sort" , "Merge Sort" , "Quick Sort"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/Images/icon_searching_b.png")), "Searching" , "Linear Search" , "Binary Search"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/Images/icon_backtracking_b.png")), "Back Tracking" , "Maze Generator" , "N-Queen"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/Images/icon_pathfinding_b.png")), "Path Finding" , "A★ Search", "Dijkstra's Algorithm", "Breadth-First Search", "Depth-First Search"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/Images/icon_graph_b.png")), "Graph" , "Pre-Order" , "In-Order" , "Post-Order"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/Images/icon_cipher_b.png")), "Cipher" , "Ceaser" , "Atbash" , "A1Z26", "Pig Latin"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/Images/icon_settings_b.png")), "Settings"));
        
    }

    private void addMenu(ModelMenu menu) {
        panel.add(new MenuItem(menu , getEventMenu() , event , panel.getComponentCount()),"h 40!");
    }
    
    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if(enableMenu)  {
                    if(isShowMenu()){
                        if(open){
                            new MenuAnimation(layout,com).openMenu();
                        }
                        else {
                            new MenuAnimation(layout,com).closeMenu();
                        }
                        return true;
                    }
                    else {
                        eventShowPopup.showPopup(com);
                    }
                }
                return false;
            }
            
        };
    }
    
    public void hideAllMenu(){
        for(Component com:panel.getComponents()){
            MenuItem item=(MenuItem) com;
            if(item.isOpen()){
                new MenuAnimation(layout, com, 500).closeMenu();
                item.setOpen(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        profile = new AlgorithmVisualizer.Component.Profile();

        setOpaque(false);

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setViewportBorder(javax.swing.BorderFactory.createCompoundBorder());
        sp.setOpaque(false);

        panel.setFocusable(false);
        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra=new GradientPaint(0 , 0 , new Color(0,190,82) , getWidth() , 0 , new Color(0,190,163));
        g2.setPaint(gra);
        g2.fillRect(0 , 0 , getWidth() , getHeight());
        super.paintComponent(g);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private AlgorithmVisualizer.Component.Profile profile;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables

    
}
