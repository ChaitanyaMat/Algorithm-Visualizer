package AlgorithmVisualizer.Form;

import AlgorithmVisualizer.Event.EventAlgorithmFinished;
import AlgorithmVisualizer.LogTracer.Logger;
import AlgorithmVisualizer.LogTracer.TimeTaken;
import AlgorithmVisualizer.Swing.LogTable.TableCustom;
import AlgorithmVisualizer.Swing.Notification.Notification;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Form_Searching extends javax.swing.JPanel {

    private final String searches[]={"Linear","Binary"};
    
    public Form_Searching(int index) {
        initComponents();
        init(index);
        searchingPanel.setSearch(index);
        searchingPanel.setLength(slider.getValue());
        searchingPanel.setSpeed(speedSlider.getValue());
        searchingPanel.setFound(false);
        searchingPanel.repaint();
    }
    
    private void init(int index) {
        TableCustom.apply(jScrollPane, TableCustom.TableType.MULTI_LINE);
        EventAlgorithmFinished.setReset(reset);
        EventAlgorithmFinished.setStop(stop);
        subtitle.setText(searches[index]+" Search");
        slider.addChangeListener(new ChangeListener() {
        @Override
            public void stateChanged(ChangeEvent e) {
                sliderValue.setText("No. of Elements = "+slider.getValue());
                searchingPanel.setLength(slider.getValue());
                searchingPanel.repaint();
            }
        });
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                speedValue.setText("Speed = "+speedSlider.getValue());
                searchingPanel.setSpeed(speedSlider.getValue());
                searchingPanel.repaint();
            }
            
        });
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String search=searchVal.getText();
                    if(!isValid(search)){
                        Notification noti=new Notification(AlgorithmVisualizer.AlgoVis.getFrame(), Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Invalid Search Value");
                        noti.showNotification();
                        return ;
                    }
                    Logger.setCurrent(log);
                    searchingPanel.setSearchItem(Double.parseDouble(search));
                    searchVal.setEditable(false);
                    slider.setEnabled(false);
                    reset.setEnabled(false);
                    stop.setEnabled(true);
                    searchingPanel.setRunning(true);
                    searchingPanel.animate();
                    start.setEnabled(false);
                } catch (Exception ex) {}
            }

            private boolean isValid(String search) {
                if(search.equals(""))
                    return false;
                try{
                    double n=Double.parseDouble(search);
                }
                catch(Exception e){return false;}
                return true;
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset.setEnabled(true);
                start.setEnabled(true);
                stop.setEnabled(false);
                searchingPanel.setRunning(false);
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logger.emptyLog();
                TimeTaken.resetTime();
                start.setEnabled(true);
                searchVal.setEditable(true);
                searchingPanel.setLength(slider.getValue());
                slider.setEnabled(true);
                searchingPanel.setDone(false);
                searchingPanel.setFound(false);
                searchingPanel.repaint();
                
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPanel = new javax.swing.JPanel();
        cPanel1 = new javax.swing.JPanel();
        slider = new AlgorithmVisualizer.Swing.Slider.SliderCustom();
        sliderValue = new javax.swing.JLabel();
        cPanel2 = new javax.swing.JPanel();
        speedSlider = new AlgorithmVisualizer.Swing.Slider.SliderCustom();
        speedValue = new javax.swing.JLabel();
        start = new AlgorithmVisualizer.Swing.ButtonCustom();
        stop = new AlgorithmVisualizer.Swing.ButtonCustom();
        reset = new AlgorithmVisualizer.Swing.ButtonCustom();
        searchVal = new AlgorithmVisualizer.Swing.TextFieldCustom();
        tableScrollButton = new AlgorithmVisualizer.Swing.LogTable.TableScrollButton();
        jScrollPane = new javax.swing.JScrollPane();
        log = new javax.swing.JTable();
        title = new javax.swing.JLabel();
        subtitle = new javax.swing.JLabel();
        searchingPanel = new AlgorithmVisualizer.Panels.SearchingPanel();

        setOpaque(false);

        controlPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        controlPanel.setOpaque(false);

        cPanel1.setOpaque(false);

        slider.setMinimum(10);
        slider.setValue(10);

        sliderValue.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        sliderValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sliderValue.setText("No. of Elements = 10");

        javax.swing.GroupLayout cPanel1Layout = new javax.swing.GroupLayout(cPanel1);
        cPanel1.setLayout(cPanel1Layout);
        cPanel1Layout.setHorizontalGroup(
            cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sliderValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(slider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        cPanel1Layout.setVerticalGroup(
            cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sliderValue, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        cPanel2.setOpaque(false);

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

        javax.swing.GroupLayout cPanel2Layout = new javax.swing.GroupLayout(cPanel2);
        cPanel2.setLayout(cPanel2Layout);
        cPanel2Layout.setHorizontalGroup(
            cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanel2Layout.createSequentialGroup()
                .addGroup(cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(speedValue, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        cPanel2Layout.setVerticalGroup(
            cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cPanel2Layout.createSequentialGroup()
                        .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(speedValue, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7))
        );

        searchVal.setLabelText("Search Value");

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(searchVal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
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

        title.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        title.setText("Searching");

        subtitle.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        subtitle.setPreferredSize(new java.awt.Dimension(121, 16));

        searchingPanel.setBackground(new java.awt.Color(255, 255, 255));
        searchingPanel.setOpaque(true);

        javax.swing.GroupLayout searchingPanelLayout = new javax.swing.GroupLayout(searchingPanel);
        searchingPanel.setLayout(searchingPanelLayout);
        searchingPanelLayout.setHorizontalGroup(
            searchingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        searchingPanelLayout.setVerticalGroup(
            searchingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(searchingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tableScrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title)
                            .addComponent(subtitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                    .addComponent(searchingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cPanel1;
    private javax.swing.JPanel cPanel2;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable log;
    private AlgorithmVisualizer.Swing.ButtonCustom reset;
    private AlgorithmVisualizer.Swing.TextFieldCustom searchVal;
    private AlgorithmVisualizer.Panels.SearchingPanel searchingPanel;
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
