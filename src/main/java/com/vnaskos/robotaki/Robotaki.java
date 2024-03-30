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
package com.vnaskos.robotaki;

import com.vnaskos.robotaki.actions.Action;
import com.vnaskos.robotaki.handlers.OpenHandler;
import com.vnaskos.robotaki.handlers.RunHandler;
import com.vnaskos.robotaki.handlers.SaveHandler;
import com.vnaskos.robotaki.ui.ActionsListObserver;
import com.vnaskos.robotaki.ui.ActionsTab;
import com.vnaskos.robotaki.ui.FileTab;
import com.vnaskos.robotaki.ui.ListView;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vasilis Naskos
 */
public class Robotaki extends JFrame {

    private ActionsListObserver listObserver;

    private ListView listView;

    private final JFileChooser fileChooser;

    public Robotaki() {
        fileChooser = new JFileChooser();

        createUI();
    }

    private void createUI() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        listView = new ListView();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.8;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(listView, gbc);

        JTabbedPane toolboxTabs = new JTabbedPane();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.2;
        gbc.gridx = 2;
        add(toolboxTabs, gbc);

        listObserver = new DefaultActionsListObserver();
        ActionsTab toolboxView = new ActionsTab(listObserver);
        toolboxTabs.addTab("Actions", toolboxView);
        FileTab fileTab = new FileTab(this);
        toolboxTabs.add("File", fileTab);

        setMinimumSize(new Dimension(500, 200));
        setPreferredSize(new Dimension(520, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Robotaki");
        setLocationRelativeTo(null);
        pack();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Robotaki.class.getName()).log(Level.SEVERE, null, ex);
        }

        Robotaki robotaki = new Robotaki();
        robotaki.setVisible(true);
    }

    public void save() {
        if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String filepath = fileChooser.getSelectedFile().getPath();
        SaveHandler saveHandler = new SaveHandler(listView.getActions(), filepath);
        saveHandler.save();
    }

    public void open() {
        if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String file = fileChooser.getSelectedFile().getPath();

        try {
            OpenHandler openHandler = new OpenHandler(listObserver);
            openHandler.open(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Robotaki.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        ArrayList<Action> actions = listView.getActions();

        RunHandler.start(actions);
    }

    public void toggleAlwaysOnTop() {
        this.setAlwaysOnTop(!this.isAlwaysOnTop());
    }

    private class DefaultActionsListObserver implements ActionsListObserver {

        @Override
        public void addAction(Action action) {
            listView.addAction(action);
        }
    }
}
