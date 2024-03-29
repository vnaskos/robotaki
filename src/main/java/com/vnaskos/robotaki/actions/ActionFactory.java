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
package com.vnaskos.robotaki.actions;

import com.vnaskos.robotaki.exceptions.InvalidActionException;

/**
 *
 * @author Vasilis Naskos
 */
public class ActionFactory {

    public Action getAction(String encodedAction)
            throws InvalidActionException {
        int id = Integer.parseInt(encodedAction.split(":")[0]);

        return switch (id) {
            case ActionType.MOUSE_POSITION -> new MousePositionAction(encodedAction);
            case ActionType.MOUSE_MOVE -> new MouseMoveAction(encodedAction);
            case ActionType.MOUSE_CLICK -> new MouseClickAction(encodedAction);
            case ActionType.DELAY -> new DelayAction(encodedAction);
            case ActionType.REPEAT -> new RepeatAction(encodedAction);
            case ActionType.END -> new EndAction(encodedAction);
            default -> throw new InvalidActionException(
                "Unknown action " + encodedAction);
        };
    }

}
