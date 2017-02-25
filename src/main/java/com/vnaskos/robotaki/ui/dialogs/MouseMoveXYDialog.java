package com.vnaskos.robotaki.ui.dialogs;

import com.vnaskos.robotaki.actions.MouseMoveAction;
import com.vnaskos.robotaki.utils.MouseDirection;
import com.vnaskos.robotaki.ui.ActionsListObserver;

/**
 *
 * @author Vasilis Naskos
 */
public class MouseMoveXYDialog extends javax.swing.JFrame {
    
    ActionsListObserver observer;
    
    protected MouseMoveXYDialog() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    public MouseMoveXYDialog(ActionsListObserver observer) {
        this();
        this.observer = observer;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        directionButtonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        timeLabel = new javax.swing.JLabel();
        stepLabel = new javax.swing.JLabel();
        delayLabel = new javax.swing.JLabel();
        directionLabel = new javax.swing.JLabel();
        dirUpRadio = new javax.swing.JRadioButton();
        dirDownRadio = new javax.swing.JRadioButton();
        dirLeftRadio = new javax.swing.JRadioButton();
        dirRightRadio = new javax.swing.JRadioButton();
        timesSpinner = new javax.swing.JSpinner();
        stepSpinner = new javax.swing.JSpinner();
        delaySpinner = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Linear Move");

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        timeLabel.setText("Times :");

        stepLabel.setText("Step :");

        delayLabel.setText("Delay :");

        directionLabel.setText("Direction:");

        directionButtonGroup.add(dirUpRadio);
        dirUpRadio.setSelected(true);
        dirUpRadio.setText("Up");

        directionButtonGroup.add(dirDownRadio);
        dirDownRadio.setText("Down");

        directionButtonGroup.add(dirLeftRadio);
        dirLeftRadio.setText("Left");

        directionButtonGroup.add(dirRightRadio);
        dirRightRadio.setText("Right");

        timesSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));

        stepSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));

        delaySpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 60000, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(timeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timesSpinner))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(stepLabel)
                        .addGap(21, 21, 21)
                        .addComponent(stepSpinner))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(delayLabel)
                        .addGap(14, 14, 14)
                        .addComponent(delaySpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(directionLabel)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dirUpRadio)
                                .addGap(42, 42, 42)
                                .addComponent(dirLeftRadio))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dirDownRadio)
                                .addGap(19, 19, 19)
                                .addComponent(dirRightRadio)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeLabel)
                    .addComponent(timesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stepLabel)
                    .addComponent(stepSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delayLabel)
                    .addComponent(delaySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(directionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dirUpRadio)
                    .addComponent(dirLeftRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dirDownRadio)
                    .addComponent(dirRightRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        okListener();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel delayLabel;
    private javax.swing.JSpinner delaySpinner;
    private javax.swing.JRadioButton dirDownRadio;
    private javax.swing.JRadioButton dirLeftRadio;
    private javax.swing.JRadioButton dirRightRadio;
    private javax.swing.JRadioButton dirUpRadio;
    private javax.swing.ButtonGroup directionButtonGroup;
    private javax.swing.JLabel directionLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel stepLabel;
    private javax.swing.JSpinner stepSpinner;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JSpinner timesSpinner;
    // End of variables declaration//GEN-END:variables

    private void okListener() {
        MouseDirection direction;
        int times = Integer.parseInt(timesSpinner.getValue().toString());
        int step = Integer.parseInt(stepSpinner.getValue().toString());
        int delay = Integer.parseInt(delaySpinner.getValue().toString());

        if (dirUpRadio.isSelected()) {
            direction = new MouseDirection(MouseDirection.Value.UP);
            step = -step;
        } else if (dirDownRadio.isSelected()) {
            direction = new MouseDirection(MouseDirection.Value.DOWN);
        } else if (dirLeftRadio.isSelected()) {
            direction = new MouseDirection(MouseDirection.Value.LEFT);
            step = -step;
        } else {
            direction = new MouseDirection(MouseDirection.Value.RIGHT);
        }

        MouseMoveAction action = new MouseMoveAction(times, step, delay, direction);
        observer.addAction(action);

        dispose();
    }

}
