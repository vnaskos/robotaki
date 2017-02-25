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
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author Vasilis Naskos
 */
public class FileTab extends JPanel {
    
    private final ActionObserver list;
    private JButton saveButton, openButton,
            startButton, stopButton, aboutButton;
    private JToggleButton alwaysOnTopByButton;

    public FileTab(ActionObserver list) {
        this.list = list;
        
        createUI();
    }
    
    private void createUI() {
        setLayout(new FormLayout(
                "$lcgap,f:p:g,$lcgap",
                "[20dlu,p],[20dlu,p],[20dlu,p],[20dlu,p],[20dlu,p],[20dlu,p]"));
        CellConstraints cc = new CellConstraints();
        
        saveButton = new JButton("Save");
        saveButton.addActionListener((ActionEvent e) -> {
            list.triggerSave();
        });
        add(saveButton, cc.xy(2, 1));
        
        openButton = new JButton("Open");
        openButton.addActionListener((ActionEvent e) -> {
            list.triggerOpen();
        });
        add(openButton, cc.xy(2, 2));
        
        startButton = new JButton("Start");
        startButton.addActionListener((ActionEvent e) -> {
            list.triggerStart();
        });
        add(startButton, cc.xy(2, 3));
        
        stopButton = new JButton("Stop");
        add(stopButton, cc.xy(2, 4));
        
        alwaysOnTopByButton = new JToggleButton("On Top");
        add(alwaysOnTopByButton, cc.xy(2, 5));
        
        aboutButton = new JButton("About");
        aboutButton.addActionListener((ActionEvent e) -> {
            AboutFrame about = new AboutFrame();
            about.setVisible(true);
        });
        add(aboutButton, cc.xy(2, 6));
    }
    
}
