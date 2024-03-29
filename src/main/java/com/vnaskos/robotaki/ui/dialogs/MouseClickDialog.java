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
import com.vnaskos.robotaki.actions.MouseClickAction;
import com.vnaskos.robotaki.ui.ActionsListObserver;
import com.vnaskos.robotaki.utils.Button;
import java.awt.Dimension;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        setLayout(new FormLayout(
                "f:p:g,right:p,f:p,f:p:g",
                "f:p:g,f:p,$lgap,f:p,$lgap,f:p,f:p:g"));
        CellConstraints cc = new CellConstraints();

        add(new JLabel("Button:"), cc.xy(2, 2));

        buttonComboModel = new DefaultComboBoxModel<>(
                new Button[] {
                    Button.LEFT_CLICK, Button.RIGHT_CLICK
                });
        JComboBox<Button> buttonComboBox = new JComboBox<>(buttonComboModel);
        add(buttonComboBox, cc.xy(3, 2));

        add(new JLabel("State:"), cc.xy(2, 4));

        ComboBoxModel<String> stateComboModel = new DefaultComboBoxModel<>(
                new String[] {
                    "press", "release"
                });
        stateComboBox = new JComboBox<>(stateComboModel);
        add(stateComboBox, cc.xy(3, 4));

        JPanel dialogOptionsPanel = new JPanel(new FormLayout(
                "f:p:g,right:p,right:p",
                "f:p"));
        add(dialogOptionsPanel, cc.xyw(2, 6, 2));

        JButton okButton = new JButton("OK");
        okButton.addActionListener((e) -> okButtonListener());
        dialogOptionsPanel.add(okButton, cc.xy(2,1));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((e) -> {
            dispose();
        });
        dialogOptionsPanel.add(cancelButton, cc.xy(3,1));

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
