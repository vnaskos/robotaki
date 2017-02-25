/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnaskos.robotaki.ui.dialogs;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.vnaskos.robotaki.actions.MousePositionAction;
import com.vnaskos.robotaki.ui.ActionObserver;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Vasilis Naskos
 */
public class MousePositionDialog extends JDialog {

    private final ActionObserver list;
    
    private SpinnerNumberModel xSpinnerModel, ySpinnerModel;
    private JButton okButton;
    
    public MousePositionDialog(ActionObserver list) {
        this.list = list;
        createUI();
    }
    
    private void createUI() {
        setLayout(new FormLayout(
                "f:p:g,right:p,40dlu,$lcgap,right:p,40dlu,f:p:g",
                "f:p:g,f:p,$lgap,f:p,f:p:g"));
        CellConstraints cc = new CellConstraints();
        
        add(new JLabel("x:"), cc.xy(2, 2));
        
        xSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner xSpinner = new JSpinner(xSpinnerModel);
        add(xSpinner, cc.xy(3, 2));
        
        add(new JLabel("y:"), cc.xy(5, 2));
        
        ySpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner ySpinner = new JSpinner(ySpinnerModel);
        add(ySpinner, cc.xy(6, 2));
        
        JPanel dialogOptionsPanel = new JPanel(new FormLayout(
                "f:p:g,right:p,right:p",
                "f:p"));
        add(dialogOptionsPanel, cc.xyw(2, 4, 5));
        
        okButton = new JButton("OK");
        okButton.addActionListener((e) -> {
            okButtonListener();
        });
        dialogOptionsPanel.add(okButton, cc.xy(2,1));
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((e) -> {
            dispose();
        });
        dialogOptionsPanel.add(cancelButton, cc.xy(3,1));
        
        setTitle("Mouse Position");
        setPreferredSize(new Dimension(300, 150));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void okButtonListener() throws NumberFormatException {
        int x = xSpinnerModel.getNumber().intValue();
        int y = ySpinnerModel.getNumber().intValue();
        
        MousePositionAction action = new MousePositionAction(x, y);
        list.addAction(action);
        
        dispose();
    }
    
}
