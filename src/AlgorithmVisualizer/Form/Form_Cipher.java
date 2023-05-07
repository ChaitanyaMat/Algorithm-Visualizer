package AlgorithmVisualizer.Form;

import AlgorithmVisualizer.Swing.Notification.Notification;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Form_Cipher extends javax.swing.JPanel {

    private String[] cipher={"Caeser", "Atbash", "A1Z26", "Pig Latin"};
    public Form_Cipher(int index) {
        initComponents();
        init(index);
        cipherPanel.setIndex(index);
    }

    private void init(int index) {
        subtitle.setText(cipher[index]+" Cipher");
        slider.addChangeListener(new ChangeListener() {
        @Override
            public void stateChanged(ChangeEvent e) {
                sliderValue.setText("No. of Shifts = "+slider.getValue());
                //searchingPanel.setLength(slider.getValue());
            }
        });
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = msgVal.getText().toUpperCase().trim();
                if(msg.length()>50){
                    Notification noti=new Notification(AlgorithmVisualizer.AlgoVis.getFrame(), Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Input Length Exceeded. Limiting to 50 characters.");
                    noti.showNotification();
                    msg=msg.substring(0,50);
                }
                msgVal.setEnabled(false);
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
                msgVal.setEnabled(true);
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
        line = new javax.swing.JLabel();
        msgVal = new AlgorithmVisualizer.Swing.TextFieldCustom();
        slider = new AlgorithmVisualizer.Swing.Slider.SliderCustom();
        sliderValue = new javax.swing.JLabel();
        cipherPanel = new AlgorithmVisualizer.Panels.CipherPanel();
        start = new AlgorithmVisualizer.Swing.ButtonCustom();
        stop = new AlgorithmVisualizer.Swing.ButtonCustom();
        reset = new AlgorithmVisualizer.Swing.ButtonCustom();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1040, 670));

        title.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        title.setText("Cipher");

        subtitle.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        subtitle.setPreferredSize(new java.awt.Dimension(121, 16));

        line.setBackground(new java.awt.Color(13, 13, 13));
        line.setOpaque(true);
        line.setPreferredSize(new java.awt.Dimension(1000, 2));

        msgVal.setLabelText("Message to Encode");

        slider.setMaximum(25);
        slider.setMinimum(1);
        slider.setValue(1);

        sliderValue.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        sliderValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sliderValue.setText("No. of Shifts = 1");

        javax.swing.GroupLayout cipherPanelLayout = new javax.swing.GroupLayout(cipherPanel);
        cipherPanel.setLayout(cipherPanelLayout);
        cipherPanelLayout.setHorizontalGroup(
            cipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        cipherPanelLayout.setVerticalGroup(
            cipherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );

        start.setText("Start");
        start.setPreferredSize(new java.awt.Dimension(60, 25));
        start.setRadius(25);

        stop.setText("Stop");
        stop.setColorBorder(new java.awt.Color(197, 0, 56));
        stop.setColorClick(new java.awt.Color(197, 0, 56));
        stop.setColorOver(new java.awt.Color(232, 153, 175));
        stop.setPreferredSize(new java.awt.Dimension(60, 25));
        stop.setRadius(25);

        reset.setText("Reset");
        reset.setPreferredSize(new java.awt.Dimension(60, 25));
        reset.setRadius(25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderValue, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(msgVal, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cipherPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(line, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1028, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(title, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(subtitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subtitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(line, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(msgVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sliderValue)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(cipherPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private AlgorithmVisualizer.Panels.CipherPanel cipherPanel;
    private javax.swing.JLabel line;
    private AlgorithmVisualizer.Swing.TextFieldCustom msgVal;
    private AlgorithmVisualizer.Swing.ButtonCustom reset;
    private AlgorithmVisualizer.Swing.Slider.SliderCustom slider;
    private javax.swing.JLabel sliderValue;
    private AlgorithmVisualizer.Swing.ButtonCustom start;
    private AlgorithmVisualizer.Swing.ButtonCustom stop;
    private javax.swing.JLabel subtitle;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables

    
}