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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import com.vnaskos.robotaki.ui.ActionsListObserver;

/**
 *
 * @author Vasilis Naskos
 */
public class DelayDialog extends JDialog {

    private final ActionsListObserver list;

    private SpinnerNumberModel spinnerModel;

    public DelayDialog(ActionsListObserver list) {
        this.list = list;
        createUI();
    }

    private void createUI() {
        setLayout(new FormLayout(
                "f:p:g,f:p:g,$lcgap,f:p:g,f:p,f:p,f:p:g",
                "f:p:g,f:p,$lgap,f:p,f:p:g"));
        CellConstraints cc = new CellConstraints();

        add(new JLabel("Delay (ms):"), cc.xy(2, 2));

        spinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner delaySpinner = new JSpinner(spinnerModel);
        add(delaySpinner, cc.xyw(4, 2, 3));

        JButton okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent e) -> {
            okButtonPressed();
        });
        add(okButton, cc.xy(5, 4));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((ActionEvent e) -> {
            dispose();
        });
        add(cancelButton, cc.xy(6, 4));

        setTitle("Delay");
        setPreferredSize(new Dimension(300, 150));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void okButtonPressed() throws NumberFormatException {
        DelayAction action = new DelayAction();
        action.setDelayMs(spinnerModel.getNumber().intValue());
        list.addAction(action);
        dispose();
    }

}
