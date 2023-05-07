package AlgorithmVisualizer.Form;

import AlgorithmVisualizer.Swing.Notification.Notification;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form_Cipher_2 extends javax.swing.JPanel {

    private final String[] cipher = {"Caeser", "Atbash", "A1Z26", "Pig Latin"};;
    public Form_Cipher_2(int index) {
        initComponents();
        init(index);
        cipherPanel.setIndex(index);
    }
    
    private void init(int index) {
        subtitle.setText(cipher[index]+" Cipher");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = searchVal.getText().toUpperCase().trim();
                if(msg.length()>50){
                    Notification noti=new Notification(AlgorithmVisualizer.AlgoVis.getFrame(), Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Input Length Exceeded. Limiting to 50 characters.");
                    noti.showNotification();
                    msg=msg.substring(0,50);
                }
                searchVal.setEnabled(false);
                cipherPanel.setMsg(msg);
                cipherPanel.repaint();
                cipherPanel.setRunning(true);
                try {
                    cipherPanel.animate();
                } catch (Exception ex) {}
                reset.setEnabled(false);
                start.setEnabled(false);
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset.setEnabled(true);
                start.setEnabled(true);
                cipherPanel.setRunning(false);
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchVal.setEnabled(true);
                cipherPanel.setMsg("");
                cipherPanel.repaint();
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        subtitle = new javax.swing.JLabel();
        line1 = new javax.swing.JLabel();
        searchVal = new AlgorithmVisualizer.Swing.TextFieldCustom();
        line2 = new javax.swing.JLabel();
        start = new AlgorithmVisualizer.Swing.ButtonCustom();
        stop = new AlgorithmVisualizer.Swing.ButtonCustom();
        reset = new AlgorithmVisualizer.Swing.ButtonCustom();
        cipherPanel = new AlgorithmVisualizer.Panels.CipherPanel();

        setOpaque(false);

        title.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        title.setText("Cipher");

        subtitle.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        subtitle.setPreferredSize(new java.awt.Dimension(121, 16));

        line1.setBackground(new java.awt.Color(13, 13, 13));
        line1.setOpaque(true);
        line1.setPreferredSize(new java.awt.Dimension(1000, 2));

        searchVal.setLabelText("Message to Encode");

        line2.setBackground(new java.awt.Color(13, 13, 13));
        line2.setOpaque(true);
        line2.setPreferredSize(new java.awt.Dimension(1000, 2));

        start.setText("Start");
        start.setPreferredSize(new java.awt.Dimension(60, 30));
        start.setRadius(30);

        stop.setText("Stop");
        stop.setColorBorder(new java.awt.Color(197, 0, 56));
        stop.setColorClick(new java.awt.Color(197, 0, 56));
        stop.setColorOver(new java.awt.Color(232, 153, 175));
        stop.setPreferredSize(new java.awt.Dimension(60, 30));
        stop.setRadius(30);

        reset.setText("Reset");
        reset.setPreferredSize(new java.awt.Dimension(60, 30));
        reset.setRadius(30);

        javax.swing.GroupLayout cipherPanelLayout = new javax.swing.GroupLayout(cipherPanel);
        cipherPanel.setLayout(cipherPanelLayout);
        cipherPanelLayout.setHorizontalGroup(
            cipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        cipherPanelLayout.setVerticalGroup(
            cipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 533, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchVal, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(line1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(line2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cipherPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(title)
                                    .addComponent(subtitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subtitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(line1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addComponent(searchVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(line2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cipherPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private AlgorithmVisualizer.Panels.CipherPanel cipherPanel;
    private javax.swing.JLabel line1;
    private javax.swing.JLabel line2;
    private AlgorithmVisualizer.Swing.ButtonCustom reset;
    private AlgorithmVisualizer.Swing.TextFieldCustom searchVal;
    private AlgorithmVisualizer.Swing.ButtonCustom start;
    private AlgorithmVisualizer.Swing.ButtonCustom stop;
    private javax.swing.JLabel subtitle;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
