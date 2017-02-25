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

import com.vnaskos.robotaki.utils.Button;
import java.awt.Robot;

/**
 *
 * @author Vasilis Naskos
 */
public class MouseClickAction implements Action {

    private Button button;
    private boolean state;

    public MouseClickAction(Button button, boolean state) {
        this.button = button;
        this.state = state;
    }

    public MouseClickAction(String encoded) {
        parse(encoded);
    }
    
    @Override
    public final void parse(String encoded) {
        String[] fields = encoded.split(":");
        
        button = Button.getByName(fields[1]);
        state = Boolean.parseBoolean(fields[2]);
    }

    @Override
    public String encode() {
        return String.join(":",
                Integer.toString(ActionType.MOUSE_CLICK),
                button.toString(),
                Boolean.toString(state));
    }

    @Override
    public void execute(Robot robot) {
        if(state) {
            robot.mousePress(button.name);
        } else {
            robot.mouseRelease(button.name);
        }
    }

    @Override
    public String toString() {
        String action = state ? "Press " : "Release ";
        
        action += button.toString()
                .replace("_", " ").toLowerCase();
        
        return action;
    }
    
}
