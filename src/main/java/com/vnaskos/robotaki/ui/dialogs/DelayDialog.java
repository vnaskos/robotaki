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
import com.vnaskos.robotaki.actions.DelayAction;
import com.vnaskos.robotaki.ui.ActionObserver;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Vasilis Naskos
 */
public class DelayDialog extends JDialog {
    
    private final ActionObserver list;
    
    private JSpinner delaySpinner;
    private JButton okButton, cancelButton;

    public DelayDialog(ActionObserver list) throws HeadlessException {
        this.list = list;
        
        createUI();
    }
    
    private void createUI() {
        setLayout(new FormLayout(
                "f:p:g,$lcgap,f:p:g,f:p,f:p",
                "f:p,$lgap,f:p"));
        CellConstraints cc = new CellConstraints();
        
        add(new JLabel("Delay (ms):"), cc.xy(1, 1));
        
        delaySpinner = new JSpinner(
                new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        add(delaySpinner, cc.xyw(3, 1, 3));
         
        okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent e) -> {
            DelayAction action = new DelayAction();
            action.setDelayMs(Integer.parseInt(delaySpinner.getValue().toString()));
            list.addAction(action);
        });
        add(okButton, cc.xy(4, 3));
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((ActionEvent e) -> {
            dispose();
        });
        add(cancelButton, cc.xy(5, 3));
        
        pack();
        setLocationRelativeTo(null);
    }
    
}