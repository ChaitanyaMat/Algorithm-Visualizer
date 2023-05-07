package Splashscreen;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SplashScreen extends javax.swing.JFrame {

    public SplashScreen() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/Images/logo_32.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splash_panel = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        foreground = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        subtitle = new javax.swing.JLabel();
        line = new javax.swing.JLabel();
        ProgressBar = new Splashscreen.SplashProgressBar();
        load_msg = new javax.swing.JLabel();
        load_val = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        splash_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo_64.png"))); // NOI18N
        splash_panel.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        foreground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/splash_fore.png"))); // NOI18N
        splash_panel.add(foreground, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 25, 450, 250));

        title.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("ALGORITHM VISUALIZER");
        splash_panel.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 280, -1, -1));

        subtitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        subtitle.setForeground(new java.awt.Color(242, 242, 242));
        subtitle.setText("SEEING IS BELIEVING");
        subtitle.setPreferredSize(new java.awt.Dimension(240, 32));
        splash_panel.add(subtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 350, -1, -1));

        line.setBackground(new java.awt.Color(255, 255, 255));
        line.setOpaque(true);
        line.setPreferredSize(new java.awt.Dimension(300, 2));
        splash_panel.add(line, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 345, -1, -1));

        ProgressBar.setBackground(new java.awt.Color(255, 255, 255));
        ProgressBar.setBorder(null);
        ProgressBar.setValue(100);
        ProgressBar.setOpaque(false);
        splash_panel.add(ProgressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 900, 10));

        load_msg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        load_msg.setForeground(new java.awt.Color(255, 255, 255));
        load_msg.setText("Loading . . .");
        splash_panel.add(load_msg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, -1));

        load_val.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        load_val.setForeground(new java.awt.Color(255, 255, 255));
        load_val.setText("0 %");
        splash_panel.add(load_val, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 460, -1, -1));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/splash_back.png"))); // NOI18N
        splash_panel.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splash_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splash_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String m="Loading . . .";
                    for(int i=0;i<=100;i++)
                    {
                        if(i==10)
                            m="Connecting To Modules . . .";
                        if(i==20)
                            m="Loading Modules . . .";
                        if(i==70)
                            m="Modules Loaded . . .";
                        if(i==80)
                            m="Launching Application . . .";
                        progressUpdate(m,i);
                    }
                    dispose();
                    JFrame fram=AlgorithmVisualizer.AlgoVis.getFrame();
                    fram.setVisible(true);//new AlgoVis.AlgoVis().setVisible(true);
                }
                catch(Exception e){}
            }
        }).start();
    }//GEN-LAST:event_formWindowOpened
    
    private void progressUpdate(String msg, int val)throws Exception{
        load_val.setText(val+" %");
        load_msg.setText(msg);
        ProgressBar.setValue(val);
        Thread.sleep((int)(Math.random()*49)+2);
    }
    
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SplashScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Splashscreen.SplashProgressBar ProgressBar;
    private javax.swing.JLabel background;
    private javax.swing.JLabel foreground;
    private javax.swing.JLabel line;
    private javax.swing.JLabel load_msg;
    private javax.swing.JLabel load_val;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel splash_panel;
    private javax.swing.JLabel subtitle;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
