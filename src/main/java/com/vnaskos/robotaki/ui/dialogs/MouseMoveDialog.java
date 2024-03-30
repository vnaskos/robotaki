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

import com.vnaskos.robotaki.actions.MouseMoveAction;
import com.vnaskos.robotaki.ui.ActionsListObserver;
import com.vnaskos.robotaki.utils.MouseDirection;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Vasilis Naskos
 */
public class MouseMoveDialog extends JDialog {

    private final ActionsListObserver list;

    private SpinnerNumberModel timesSpinnerModel,
            stepSpinnerModel, delaySpinnerModel;
    private ComboBoxModel<MouseDirection> directionComboModel;

    public MouseMoveDialog(ActionsListObserver list) {
        this.list = list;
        createUI();
    }

    private void createUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Times:"), gbc);

        timesSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner timesSpinner = new JSpinner(timesSpinnerModel);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(timesSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Step:"), gbc);

        stepSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner stepSpinner = new JSpinner(stepSpinnerModel);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(stepSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Delay:"), gbc);

        delaySpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner delaySpinner = new JSpinner(delaySpinnerModel);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(delaySpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Direction:"), gbc);

        directionComboModel = new DefaultComboBoxModel<>(
                new MouseDirection[] {
                    new MouseDirection(MouseDirection.Value.UP),
                    new MouseDirection(MouseDirection.Value.DOWN),
                    new MouseDirection(MouseDirection.Value.LEFT),
                    new MouseDirection(MouseDirection.Value.RIGHT)
                });
        JComboBox<MouseDirection> directionComboBox = new JComboBox<>(directionComboModel);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(directionComboBox, gbc);

        JPanel dialogOptionsPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 4;
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
