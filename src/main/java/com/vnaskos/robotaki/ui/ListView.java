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
import com.vnaskos.robotaki.actions.Action;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Vasilis Naskos
 */
public class ListView extends JPanel {

    private JList<Action> actionsList;
    private DefaultListModel<Action> model;
    private JButton moveUpButton, moveDownButton, removeButton, clearButton;
    
    private enum Direction {
        UP(-1), DOWN(1);
        
        public int value;
        
        private Direction(int value) {
            this.value = value;
        }
    }
    
    public ListView() {
        createUI();
    }
    
    private void createUI() {
        setLayout(new FormLayout(
                "$lcgap,f:p:g,f:p:g,f:p:g,f:p:g,$lcgap,",
                "f:p:g,$lgap,f:p,$lgap"));
        CellConstraints cc = new CellConstraints();
        
        model =  new DefaultListModel<>();
        actionsList = new JList<>();
        actionsList.setModel(model);
        add(new JScrollPane(actionsList), cc.xyw(1, 1, 6));
        
        moveUpButton = new JButton("Move Up");
        moveUpButton.addActionListener((ActionEvent e) -> {
            moveSelectedElements(Direction.UP);
        });
        add(moveUpButton, cc.xy(2, 3));
        
        moveDownButton = new JButton("Move Down");
        moveDownButton.addActionListener((ActionEvent e) -> {
            moveSelectedElements(Direction.DOWN);
        });
        add(moveDownButton, cc.xy(3, 3));
        
        removeButton = new JButton("Remove");
        removeButton.addActionListener((ActionEvent e) -> {
            int[] selectedIndices = actionsList.getSelectedIndices();
            
            for(int i=selectedIndices.length-1; i>=0; i--) {
                model.removeElementAt(selectedIndices[i]);
            }
        });
        add(removeButton, cc.xy(4, 3));
        
        clearButton = new JButton("Clear");
        clearButton.addActionListener((ActionEvent e) -> {
            model.clear();
        });
        add(clearButton, cc.xy(5, 3));
    }
    
    private void moveSelectedElements(Direction direction) {
        int[] indices = actionsList.getSelectedIndices();
        
        if(indices.length == 0
                || (indices[0] == 0 && direction == Direction.UP)
                || (indices[indices.length-1] == model.getSize()-1
                    && direction == Direction.DOWN)) {
            return;
        }
        
        for(int i=0; i<indices.length; i++) {
            Action actionToMove = model.remove(indices[i]);
            indices[i] = indices[i] + (1 * direction.value);
            model.add(indices[i], actionToMove);
        }
        
        actionsList.setSelectedIndices(indices);
        actionsList.updateUI();
    }
    
    public void addAction(Action action) {
        model.addElement(action);
    }
    
    public ArrayList<Action> getActions() {
        ArrayList<Action> actions = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            Action a = (Action) model.get(i);
            actions.add(a);
        }

        return actions;
    }
}

