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

import com.vnaskos.robotaki.actions.Action;
import com.vnaskos.robotaki.actions.DelayAction;
import com.vnaskos.robotaki.actions.End;
import com.vnaskos.robotaki.actions.MouseClick;
import com.vnaskos.robotaki.actions.MoveTo;
import com.vnaskos.robotaki.actions.MoveXY;
import com.vnaskos.robotaki.actions.Repeat;
import com.vnaskos.robotaki.exceptions.InvalidActionException;
import com.vnaskos.robotaki.ui.ActionObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vasilis Naskos
 */
public class OpenHandler {
    
    protected ActionObserver observer;

    public OpenHandler(ActionObserver observer) {
        this.observer = observer;
    }
    
    public void open(String filepath)
            throws FileNotFoundException {
        validateFile(filepath);
        
        try (BufferedReader in = getReader(filepath)) {
            parseActions(in);
        } catch (IOException ex) {
            Logger.getLogger(OpenHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void validateFile(String filepath)
            throws FileNotFoundException {
        if(!new File(filepath).exists()) {
            throw new FileNotFoundException();
        }
    }

    protected BufferedReader getReader(String filepath)
            throws FileNotFoundException {
        BufferedReader in = new BufferedReader(new FileReader(filepath));
        return in;
    }
    
    protected void parseActions(BufferedReader in) throws IOException {
        String encodedAction;
        
        while ((encodedAction = in.readLine()) != null) {
            try {
                Action action = parseAction(encodedAction);
                observer.addAction(action);
            } catch (InvalidActionException ex) {
                Logger.getLogger(OpenHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    protected Action parseAction(String encodedAction)
            throws InvalidActionException {
        validateEncodedAction(encodedAction);

        Action action = getIdentifiedAction(encodedAction);

        if(action == null) {
            throw new InvalidActionException(
                    "Can't read the encoded action " + encodedAction);
        }

        return action;
    }
    
    private void validateEncodedAction(String encodedAction)
            throws InvalidActionException{
        if (encodedAction == null || encodedAction.trim().isEmpty()) {
            throw new InvalidActionException(
                    "Invalid encoded action");
        }
    }
    
    private Action getIdentifiedAction(String encodedAction) {
        int id = Integer.parseInt(encodedAction.split(":")[0]);
        Action action = null;
        
        switch (id) {
            case 1:
                action = new MoveTo(encodedAction);
                break;
            case 2:
                action = new MoveXY(encodedAction);
                break;
            case 3:
                action = new MouseClick(encodedAction);
                break;
            case 4:
                action = new DelayAction(encodedAction);
                break;
            case 5:
                action = new Repeat(encodedAction);
                break;
            case 6:
                action = new End(encodedAction);
                break;
        }
        
        return action;
    }
}
