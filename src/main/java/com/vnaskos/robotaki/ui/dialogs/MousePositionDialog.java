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

import com.vnaskos.robotaki.actions.MousePositionAction;
import com.vnaskos.robotaki.ui.ActionsListObserver;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Vasilis Naskos
 */
public class MousePositionDialog extends JDialog {

    private final ActionsListObserver list;
    private SpinnerNumberModel xSpinnerModel, ySpinnerModel;

    public MousePositionDialog(ActionsListObserver list) {
        this.list = list;
        createUI();
    }

    private void createUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("x:"), gbc);

        xSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner xSpinner = new JSpinner(xSpinnerModel);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(xSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("y:"), gbc);

        ySpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner ySpinner = new JSpinner(ySpinnerModel);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(ySpinner, gbc);

        JPanel dialogOptionsPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(dialogOptionsPanel, gbc);

        JButton okButton = new JButton("OK");
        okButton.addActionListener((e) -> okButtonListener());
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        dialogOptionsPanel.add(okButton, gbc);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((e) -> dispose());
        gbc.gridx = 1;
        dialogOptionsPanel.add(cancelButton, gbc);

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
