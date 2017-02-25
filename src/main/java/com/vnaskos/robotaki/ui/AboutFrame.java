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
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author Vasilis Naskos
 */
public class AboutFrame extends JFrame {

    public AboutFrame() throws HeadlessException {
        createUI();
    }
    
    private void createUI() {
        setLayout(new FormLayout(
                "$lcgap,f:p:g,f:p:g,right:p:g,$lcgap",
                "$lcgap,f:p,10dlu,f:p:g,10dlu,f:p,$lcgap"));
        CellConstraints cc = new CellConstraints();
        
        JLabel aboutLabel = new JLabel("Robotaki v1.0 by vnaskos");
        aboutLabel.setFont(new java.awt.Font("Ubuntu", 0, 18));
        add(aboutLabel, cc.xy(2, 2));
        
        JTextPane aboutTextPane = new JTextPane();
        aboutTextPane.setEditable(false);
        aboutTextPane.setText("Robotaki aims to help user, automate repetitive"
                + " and boring tasks. The program runs sequentially all given"
                + " commands, which may include mouse and keyboard events."
                + " Thus you have to use it with extreme caution."
                + "\n\nProject page:\nhttps://github.com/vnaskos/robotaki"
                + "\n\nGNU General Public License 3.0"
                + "\nhttp://www.gnu.org/licenses/gpl-3.0.html"
                + "\n\nUSE WITH EXTREME CAUTION!!!");
        add(new JScrollPane(aboutTextPane), cc.xyw(2, 4, 3));
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener((e) -> {
            dispose();
        });
        add(okButton, cc.xy(4, 6));
        
        setTitle("About Robotaki");
        setPreferredSize(new Dimension(500, 350));
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
}
