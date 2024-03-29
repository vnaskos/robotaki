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
package com.vnaskos.robotaki.handlers;

import com.vnaskos.robotaki.actions.ActionFactory;
import com.vnaskos.robotaki.actions.Action;
import com.vnaskos.robotaki.exceptions.InvalidActionException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.vnaskos.robotaki.ui.ActionsListObserver;

/**
 *
 * @author Vasilis Naskos
 */
public class OpenHandler {

    private static final Logger LOGGER = Logger
            .getLogger(OpenHandler.class.getName());
    protected ActionsListObserver observer;

    public OpenHandler(ActionsListObserver observer) {
        this.observer = observer;
    }

    public void open(String filepath)
            throws FileNotFoundException {
        validateFile(filepath);

        try (BufferedReader in = getReader(filepath)) {
            parseActions(in);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    protected void validateFile(String filepath)
            throws FileNotFoundException {
        if(!new File(filepath).exists()) {
            throw new FileNotFoundException();
        }
    }

    protected BufferedReader getReader(String filepath) throws IOException {
        return new BufferedReader(new FileReader(filepath));
    }

    protected void parseActions(BufferedReader in) throws IOException {
        String encodedAction;

        while ((encodedAction = in.readLine()) != null) {
            try {
                Action action = parseSingleAction(encodedAction);
                observer.addAction(action);
            } catch (InvalidActionException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }

    protected Action parseSingleAction(String encodedAction)
            throws InvalidActionException {
        validateEncodedAction(encodedAction);

        ActionFactory actionFactory = new ActionFactory();
        Action action = actionFactory.getAction(encodedAction);

        return action;
    }

    private void validateEncodedAction(String encodedAction)
            throws InvalidActionException{
        if (encodedAction == null || encodedAction.trim().isEmpty()) {
            throw new InvalidActionException(
                    "Invalid encoded action");
        }
    }
}
