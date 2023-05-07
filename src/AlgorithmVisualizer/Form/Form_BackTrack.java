package AlgorithmVisualizer.Form;

import AlgorithmVisualizer.Event.EventAlgorithmFinished;
import AlgorithmVisualizer.LogTracer.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import AlgorithmVisualizer.LogTracer.TimeTaken;
import AlgorithmVisualizer.Swing.LogTable.TableCustom;

public class Form_BackTrack extends javax.swing.JPanel {

    private int index;
    private final String[] bt={"Maze Generator", "N-Queen"};
    
    public Form_BackTrack(int index) {
        this.index = index;
        initComponents();
        init();
        backTrackPanel.setIndex(index);
        backTrackPanel.setcSize(slider.getValue());
        backTrackPanel.setSpeed(speedSlider.getValue());
        backTrackPanel.repaint();
    }

    private void init() {
        TableCustom.apply(jScrollPane, TableCustom.TableType.MULTI_LINE);
        EventAlgorithmFinished.setReset(reset);
        EventAlgorithmFinished.setStop(stop);
        subtitle.setText(bt[index]);
        slider.addChangeListener(new ChangeListener() {
        @Override
            public void stateChanged(ChangeEvent e) {
                sliderValue.setText((index==0?"Cell Size = ":"Grid Size = ")+slider.getValue());
                backTrackPanel.setcSize(slider.getValue());
                backTrackPanel.repaint();
            }
        });
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                speedValue.setText("Speed = "+speedSlider.getValue());
                backTrackPanel.setSpeed(speedSlider.getValue());
                backTrackPanel.repaint();
            }
            
        });
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Logger.setCurrent(log);
                    slider.setEnabled(false);
                    reset.setEnabled(false);
                    stop.setEnabled(true);
                    backTrackPanel.setRunning(true);
                    backTrackPanel.animate();
                    start.setEnabled(false);
                } catch (Exception ex) {}
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset.setEnabled(true);
                start.setEnabled(true);
                stop.setEnabled(false);
                backTrackPanel.setRunning(false);
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logger.emptyLog();
                TimeTaken.resetTime();
                start.setEnabled(true);
                slider.setEnabled(true);
                backTrackPanel.setcSize(slider.getValue());
                backTrackPanel.repaint();
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        subtitle = new javax.swing.JLabel();
        controlPanel = new javax.swing.JPanel();
        slider = new AlgorithmVisualizer.Swing.Slider.SliderCustom();
        sliderValue = new javax.swing.JLabel();
        speedSlider = new AlgorithmVisualizer.Swing.Slider.SliderCustom();
        speedValue = new javax.swing.JLabel();
        start = new AlgorithmVisualizer.Swing.ButtonCustom();
        stop = new AlgorithmVisualizer.Swing.ButtonCustom();
        reset = new AlgorithmVisualizer.Swing.ButtonCustom();
        tableScrollButton = new AlgorithmVisualizer.Swing.LogTable.TableScrollButton();
        jScrollPane = new javax.swing.JScrollPane();
        log = new javax.swing.JTable();
        backTrackPanel = new AlgorithmVisualizer.Panels.BackTrackPanel();

        setOpaque(false);

        title.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        title.setText("Back Tracking");

        subtitle.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        subtitle.setPreferredSize(new java.awt.Dimension(121, 16));

        controlPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        controlPanel.setOpaque(false);

        slider.setMaximum(index==0?10:15);
        slider.setMinimum(index==0?1:3);
        slider.setValue(index==0?1:3);

        sliderValue.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        sliderValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sliderValue.setText(index==0?"Cell Size = 1":"Grid Size = 3");

        speedSlider.setMaximum(10);
        speedSlider.setMinimum(1);

        speedValue.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        speedValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        speedValue.setText("Speed = 10");

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

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sliderValue, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 306, Short.MAX_VALUE)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(speedValue, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sliderValue, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(speedValue, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8))
        );

        jScrollPane.setFocusable(false);
        jScrollPane.setOpaque(false);

        log.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        log.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Log Entry", "Time Passed"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        log.setFocusable(false);
        log.getTableHeader().setReorderingAllowed(false);
        jScrollPane.setViewportView(log);
        if (log.getColumnModel().getColumnCount() > 0) {
            log.getColumnModel().getColumn(0).setMinWidth(45);
            log.getColumnModel().getColumn(0).setMaxWidth(45);
            log.getColumnModel().getColumn(2).setMinWidth(95);
            log.getColumnModel().getColumn(2).setMaxWidth(95);
        }

        tableScrollButton.add(jScrollPane, java.awt.BorderLayout.CENTER);

        backTrackPanel.setBackground(new java.awt.Color(255, 255, 255));
        backTrackPanel.setOpaque(true);

        javax.swing.GroupLayout backTrackPanelLayout = new javax.swing.GroupLayout(backTrackPanel);
        backTrackPanel.setLayout(backTrackPanelLayout);
        backTrackPanelLayout.setHorizontalGroup(
            backTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        backTrackPanelLayout.setVerticalGroup(
            backTrackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title)
                            .addComponent(subtitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(backTrackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tableScrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subtitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tableScrollButton, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                    .addComponent(backTrackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private AlgorithmVisualizer.Panels.BackTrackPanel backTrackPanel;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable log;
    private AlgorithmVisualizer.Swing.ButtonCustom reset;
    private AlgorithmVisualizer.Swing.Slider.SliderCustom slider;
    private javax.swing.JLabel sliderValue;
    private AlgorithmVisualizer.Swing.Slider.SliderCustom speedSlider;
    private javax.swing.JLabel speedValue;
    private AlgorithmVisualizer.Swing.ButtonCustom start;
    private AlgorithmVisualizer.Swing.ButtonCustom stop;
    private javax.swing.JLabel subtitle;
    private AlgorithmVisualizer.Swing.LogTable.TableScrollButton tableScrollButton;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
