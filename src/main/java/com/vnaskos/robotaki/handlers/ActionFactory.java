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
import com.vnaskos.robotaki.actions.EndAction;
import com.vnaskos.robotaki.actions.MouseClick;
import com.vnaskos.robotaki.actions.MoveTo;
import com.vnaskos.robotaki.actions.MoveXY;
import com.vnaskos.robotaki.actions.RepeatAction;
import com.vnaskos.robotaki.exceptions.InvalidActionException;

/**
 *
 * @author Vasilis Naskos
 */
public class ActionFactory {
    
    public Action getAction(String encodedAction) 
            throws InvalidActionException {
        int id = Integer.parseInt(encodedAction.split(":")[0]);
        Action action = null;
        
        switch (id) {
            case MoveTo.ID:
                action = new MoveTo(encodedAction);
                break;
            case MoveXY.ID:
                action = new MoveXY(encodedAction);
                break;
            case MouseClick.ID:
                action = new MouseClick(encodedAction);
                break;
            case DelayAction.ID:
                action = new DelayAction(encodedAction);
                break;
            case RepeatAction.ID:
                action = new RepeatAction(encodedAction);
                break;
            case EndAction.ID:
                action = new EndAction(encodedAction);
                break;
            default:
                throw new InvalidActionException(
                        "Unknown action " + encodedAction);
        }
        
        return action;
    }
    
}
