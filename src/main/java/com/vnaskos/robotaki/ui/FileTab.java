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

import com.vnaskos.robotaki.Robotaki;
import com.vnaskos.robotaki.handlers.RunHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author Vasilis Naskos
 */
public class FileTab extends JPanel {

    private final Robotaki robotaki;

    public FileTab(Robotaki robotaki) {
        this.robotaki = robotaki;

        createUI();
    }

    private void createUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener((ActionEvent e) -> robotaki.save());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        add(saveButton, gbc);

        JButton openButton = new JButton("Open");
        openButton.addActionListener((ActionEvent e) -> robotaki.open());
        add(openButton, gbc);

        JButton startButton = new JButton("Start");
        startButton.addActionListener((ActionEvent e) -> robotaki.start());
        add(startButton, gbc);

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener((e) -> RunHandler.stop());
        add(stopButton, gbc);

        JToggleButton alwaysOnTopByButton = new JToggleButton("On Top");
        alwaysOnTopByButton.addActionListener((e) -> robotaki.toggleAlwaysOnTop());
        add(alwaysOnTopByButton, gbc);

        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener((ActionEvent e) -> {
            AboutFrame about = new AboutFrame();
            about.setVisible(true);
        });
        add(aboutButton, gbc);
    }

}
