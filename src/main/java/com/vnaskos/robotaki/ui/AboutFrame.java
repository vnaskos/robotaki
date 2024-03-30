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

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Vasilis Naskos
 */
public class AboutFrame extends JFrame {

    public AboutFrame() throws HeadlessException {
        createUI();
    }

    private void createUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel aboutLabel = new JLabel("Robotaki v1.0 by vnaskos");
        aboutLabel.setFont(new java.awt.Font("Ubuntu", Font.PLAIN, 18));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(12, 12, 12, 12);
        add(aboutLabel, gbc);

        JTextPane aboutTextPane = getAboutPane();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        add(new JScrollPane(aboutTextPane), gbc);

        JButton okButton = new JButton("OK");
        okButton.addActionListener((e) -> dispose());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 2;
        gbc.weighty = 0.0;
        add(okButton, gbc);

        setTitle("About Robotaki");
        setPreferredSize(new Dimension(500, 350));
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private static JTextPane getAboutPane() {
        JTextPane aboutTextPane = new JTextPane();
        aboutTextPane.setEditable(false);
        aboutTextPane.setText("""
            Robotaki aims to help user, automate repetitive and boring tasks. The program runs sequentially all given commands, which may include mouse and keyboard events. Thus you have to use it with extreme caution.

            Project page:
            https://github.com/vnaskos/robotaki

            GNU General Public License 3.0
            https://www.gnu.org/licenses/gpl-3.0.html

            USE WITH EXTREME CAUTION!!!""");
        return aboutTextPane;
    }

}
