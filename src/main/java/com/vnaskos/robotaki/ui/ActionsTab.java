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

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.vnaskos.robotaki.actions.EndAction;
import com.vnaskos.robotaki.ui.dialogs.DelayDialog;
import com.vnaskos.robotaki.ui.dialogs.MouseClickDialog;
import com.vnaskos.robotaki.ui.dialogs.MouseMoveXYDialog;
import com.vnaskos.robotaki.ui.dialogs.MousePositionDialog;
import com.vnaskos.robotaki.ui.dialogs.RepeatDialog;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Vasilis Naskos
 */
public class ActionsTab extends JPanel {

    private final ActionObserver list;
    
    private JButton goToButton, moveXYButton,
            mouseClickButton, delayBtn, repeatButton,
            endButton;
    
    private final MouseClickDialog mouseClickDialog;
    private final MousePositionDialog mousePositionDialog;
    private final MouseMoveXYDialog moveXYDialog;
    private final DelayDialog delayDialog;
    private final RepeatDialog repeatDialog;
    
    public ActionsTab(ActionObserver list) {
        this.list = list;
        
        createUI();
        
        mousePositionDialog = new MousePositionDialog(list);
        mouseClickDialog = new MouseClickDialog(list);
        moveXYDialog = new MouseMoveXYDialog(list);
        delayDialog = new DelayDialog(list);
        repeatDialog = new RepeatDialog(list);
    }
    
    private void createUI() {
        setLayout(new FormLayout(
                "$lcgap,f:p:g,$lcgap",
                "[20dlu,p],[20dlu,p],[20dlu,p],[20dlu,p],[20dlu,p],[20dlu,p]"));
        CellConstraints cc = new CellConstraints();
        
        goToButton = new JButton("Go To");
        goToButton.addActionListener((ActionEvent e) -> {
            mousePositionDialog.setVisible(true);
        });
        add(goToButton, cc.xy(2, 1));
        
        moveXYButton = new JButton("Move at X,Y");
        moveXYButton.addActionListener((ActionEvent e) -> {
            moveXYDialog.setVisible(true);
        });
        add(moveXYButton, cc.xy(2, 2));
        
        mouseClickButton = new JButton("Mouse Click");
        mouseClickButton.addActionListener((ActionEvent e) -> {
            mouseClickDialog.setVisible(true);
        });
        add(mouseClickButton, cc.xy(2, 3));
        
        delayBtn = new JButton("Delay");
        delayBtn.addActionListener((ActionEvent e) -> {
            delayDialog.setVisible(true);
        });
        add(delayBtn, cc.xy(2, 4));
        
        repeatButton = new JButton("Repeat");
        repeatButton.addActionListener((ActionEvent e) -> {
            repeatDialog.setVisible(true);
        });
        add(repeatButton, cc.xy(2, 5));
        
        endButton = new JButton("End");
        endButton.addActionListener((ActionEvent e) -> {
            EndAction end = new EndAction();
            list.addAction(end);
        });
        
        add(endButton, cc.xy(2, 6));
    }
    
}
