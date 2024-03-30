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

import com.vnaskos.robotaki.actions.MouseClickAction;
import com.vnaskos.robotaki.ui.ActionsListObserver;
import com.vnaskos.robotaki.utils.Button;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Vasilis Naskos
 */
public class MouseClickDialog extends JDialog {

    private final ActionsListObserver list;

    private ComboBoxModel<Button> buttonComboModel;
    private JComboBox<String> stateComboBox;

    public MouseClickDialog(ActionsListObserver list) {
        this.list = list;
        createUI();
    }

    private void createUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Button:"), gbc);

        buttonComboModel = new DefaultComboBoxModel<>(
                new Button[] {
                    Button.LEFT_CLICK, Button.RIGHT_CLICK
                });
        JComboBox<Button> buttonComboBox = new JComboBox<>(buttonComboModel);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(buttonComboBox, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("State:"), gbc);

        ComboBoxModel<String> stateComboModel = new DefaultComboBoxModel<>(
                new String[] {
                    "press", "release"
                });
        stateComboBox = new JComboBox<>(stateComboModel);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(stateComboBox, gbc);

        JPanel dialogOptionsPanel = new JPanel(new GridBagLayout());
        gbc.fill = GridBagConstraints.NONE;
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

        setTitle("Mouse Click");
        setPreferredSize(new Dimension(300, 200));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void okButtonListener() {
        Button btn = (Button) buttonComboModel.getSelectedItem();
        boolean press = stateComboBox.getSelectedIndex() == 0;

        MouseClickAction action = new MouseClickAction(btn, press);
        list.addAction(action);

        dispose();
    }

}
