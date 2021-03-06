/*
 * This file is part of Robotaki.
 *
 * Robotaki is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Robotaki.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.vnaskos.robotaki.ui.dialogs;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.vnaskos.robotaki.actions.MouseMoveAction;
import com.vnaskos.robotaki.ui.ActionsListObserver;
import com.vnaskos.robotaki.utils.MouseDirection;
import java.awt.Dimension;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Vasilis Naskos
 */
public class MouseMoveDialog extends JDialog {

    private final ActionsListObserver list;
    
    private SpinnerNumberModel timesSpinnerModel,
            stepSpinnerModel, delaySpinnerModel;
    private ComboBoxModel<MouseDirection> directionComboModel;
    private JButton okButton;
    
    public MouseMoveDialog(ActionsListObserver list) {
        this.list = list;
        createUI();
    }
    
    private void createUI() {
        setLayout(new FormLayout(
                "f:p:g,right:p,f:p,f:p:g",
                "f:p:g,f:p,$lgap,f:p,$lgap,f:p,$lgap,f:p,$lgap,f:p,f:p:g"));
        CellConstraints cc = new CellConstraints();
        
        add(new JLabel("Times:"), cc.xy(2, 2));
        
        timesSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner timesSpinner = new JSpinner(timesSpinnerModel);
        add(timesSpinner, cc.xy(3, 2));
        
        add(new JLabel("Step:"), cc.xy(2, 4));
        
        stepSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner stepSpinner = new JSpinner(stepSpinnerModel);
        add(stepSpinner, cc.xy(3, 4));
        
        add(new JLabel("Delay:"), cc.xy(2, 6));
        
        delaySpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner delaySpinner = new JSpinner(delaySpinnerModel);
        add(delaySpinner, cc.xy(3, 6));
        
        add(new JLabel("Direction:"), cc.xy(2, 8));
        
        directionComboModel = new DefaultComboBoxModel<>(
                new MouseDirection[] {
                    new MouseDirection(MouseDirection.Value.UP),
                    new MouseDirection(MouseDirection.Value.DOWN),
                    new MouseDirection(MouseDirection.Value.LEFT),
                    new MouseDirection(MouseDirection.Value.RIGHT)
                });
        JComboBox directionComboBox = new JComboBox(directionComboModel);
        add(directionComboBox, cc.xy(3, 8));
        
        JPanel dialogOptionsPanel = new JPanel(new FormLayout(
                "f:p:g,right:p,right:p",
                "f:p"));
        add(dialogOptionsPanel, cc.xyw(2, 10, 2));
        
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
        
        setTitle("Mouse Move");
        setPreferredSize(new Dimension(300, 200));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private void okButtonListener() {
        int times = timesSpinnerModel.getNumber().intValue();
        int step = stepSpinnerModel.getNumber().intValue();
        int delay = delaySpinnerModel.getNumber().intValue();

        MouseDirection mouseDirection = (MouseDirection) directionComboModel
                .getSelectedItem();
        
        if (mouseDirection.isUp() || mouseDirection.isLeft()) {
            step = -step;
        }

        MouseMoveAction action = new MouseMoveAction(
                times, step, delay, mouseDirection);
        list.addAction(action);

        dispose();
    }
    
}
