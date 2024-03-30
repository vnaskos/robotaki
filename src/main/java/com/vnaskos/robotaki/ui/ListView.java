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

import com.vnaskos.robotaki.actions.Action;

import java.awt.*;
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

    private void removeElement(ActionEvent e) {
        int[] selectedIndices = actionsList.getSelectedIndices();

        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            model.removeElementAt(selectedIndices[i]);
        }
    }

    private enum Direction {
        UP(-1), DOWN(1);

        public final int value;

        Direction(int value) {
            this.value = value;
        }
    }

    public ListView() {
        createUI();
    }

    private void createUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        model =  new DefaultListModel<>();
        actionsList = new JList<>();
        actionsList.setModel(model);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 4;
        add(new JScrollPane(actionsList), gbc);

        JButton moveUpButton = new JButton("Move Up");
        moveUpButton.addActionListener((ActionEvent e) -> moveSelectedElements(Direction.UP));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridwidth = 1;
        add(moveUpButton, gbc);

        JButton moveDownButton = new JButton("Move Down");
        moveDownButton.addActionListener((ActionEvent e) -> moveSelectedElements(Direction.DOWN));
        gbc.gridx = 1;
        add(moveDownButton, gbc);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(this::removeElement);
        gbc.gridx = 2;
        add(removeButton, gbc);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener((ActionEvent e) -> model.clear());
        gbc.gridx = 3;
        add(clearButton, gbc);
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
            indices[i] = indices[i] + (direction.value);
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
            Action a = model.get(i);
            actions.add(a);
        }

        return actions;
    }
}

