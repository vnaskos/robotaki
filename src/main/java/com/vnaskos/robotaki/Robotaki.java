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

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.vnaskos.robotaki.actions.Action;
import com.vnaskos.robotaki.handlers.OpenHandler;
import com.vnaskos.robotaki.handlers.RunHandler;
import com.vnaskos.robotaki.handlers.SaveHandler;
import com.vnaskos.robotaki.ui.ActionObserver;
import com.vnaskos.robotaki.ui.ListView;
import com.vnaskos.robotaki.ui.ActionsTab;
import com.vnaskos.robotaki.ui.FileTab;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Vasilis Naskos
 */
public class Robotaki extends JFrame {

    private ActionObserver listObserver;
    
    private ListView listView;
    private JTabbedPane toolboxTabs;
    private ActionsTab toolboxView;
    private FileTab fileTab;
    
    private JFileChooser fileChooser;
    
    public Robotaki() {
        createUI();
    }
    
    private void createUI() {
        setLayout(new FormLayout("f:p:g,f:p:g", "f:p:g"));
        
        CellConstraints cc = new CellConstraints();
        
        fileChooser = new JFileChooser();
        
        listView = new ListView();
        add(listView, cc.xy(1, 1));
        
        this.listObserver = new ActionObserver() {
            @Override
            public void addAction(Action action) {
                listView.addAction(action);
            }

            @Override
            public void triggerSave() {
                save();
            }

            @Override
            public void triggerOpen() {
                open();
            }

            @Override
            public void triggerStart() {
                start();
            }
        };
        
        toolboxTabs = new JTabbedPane();
        add(toolboxTabs, cc.xy(2, 1));
        
        toolboxView = new ActionsTab(listObserver);
        toolboxTabs.addTab("Actions", toolboxView);
        fileTab = new FileTab(listObserver);
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
    
    private void save() {
        if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String filepath = fileChooser.getSelectedFile().getPath();
        SaveHandler saveHandler = new SaveHandler(
                listView.getActions(), filepath);
        saveHandler.save();
    }

    private void open() {
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

    private void start() {
        ArrayList<Action> actions = listView.getActions();

        RunHandler.start(actions);
    }
}
