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

import com.vnaskos.robotaki.actions.DelayAction;
import com.vnaskos.robotaki.ui.ActionsListObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Delay (ms):"), gbc);

        spinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner delaySpinner = new JSpinner(spinnerModel);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(delaySpinner, gbc);

        JPanel controlsPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(controlsPanel, gbc);

        JButton okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent e) -> okButtonPressed());
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        controlsPanel.add(okButton, gbc);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((ActionEvent e) -> dispose());
        gbc.gridx = 1;
        controlsPanel.add(cancelButton, gbc);

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
