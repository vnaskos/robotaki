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
package com.vnaskos.robotaki.ui;

import com.vnaskos.robotaki.actions.EndAction;
import com.vnaskos.robotaki.ui.dialogs.DelayDialog;
import com.vnaskos.robotaki.ui.dialogs.MouseClickDialog;
import com.vnaskos.robotaki.ui.dialogs.MouseMoveDialog;
import com.vnaskos.robotaki.ui.dialogs.MousePositionDialog;
import com.vnaskos.robotaki.ui.dialogs.RepeatDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author Vasilis Naskos
 */
public class ActionsTab extends JPanel {

    private final ActionsListObserver list;
    private final MouseClickDialog mouseClickDialog;
    private final MousePositionDialog goToDialog;
    private final MouseMoveDialog moveXYDialog;
    private final DelayDialog delayDialog;
    private final RepeatDialog repeatDialog;

    public ActionsTab(ActionsListObserver list) {
        this.list = list;

        createUI();

        goToDialog = new MousePositionDialog(list);
        mouseClickDialog = new MouseClickDialog(list);
        moveXYDialog = new MouseMoveDialog(list);
        delayDialog = new DelayDialog(list);
        repeatDialog = new RepeatDialog(list);
    }

    private void createUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton goToButton = new JButton("Go To");
        goToButton.addActionListener((ActionEvent e) -> goToDialog.setVisible(true));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        add(goToButton, gbc);

        JButton moveXYButton = new JButton("Move at X,Y");
        moveXYButton.addActionListener((ActionEvent e) -> moveXYDialog.setVisible(true));
        add(moveXYButton, gbc);

        JButton mouseClickButton = new JButton("Mouse Click");
        mouseClickButton.addActionListener((ActionEvent e) -> mouseClickDialog.setVisible(true));
        add(mouseClickButton, gbc);

        JButton delayBtn = new JButton("Delay");
        delayBtn.addActionListener((ActionEvent e) -> delayDialog.setVisible(true));
        add(delayBtn, gbc);

        JButton repeatButton = new JButton("Repeat");
        repeatButton.addActionListener((ActionEvent e) -> repeatDialog.setVisible(true));
        add(repeatButton, gbc);

        JButton endButton = new JButton("End");
        endButton.addActionListener((ActionEvent e) -> {
            EndAction end = new EndAction();
            list.addAction(end);
        });

        add(endButton, gbc);
    }

}
