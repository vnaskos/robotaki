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
import java.awt.event.InputEvent;

/**
 *
 * @author Vasilis Naskos
 */
public class MouseClick implements Action {

    public static final int ID = 3;

    private Button button;
    private boolean state;

    public MouseClick(Button button, boolean state) {
        this.button = button;
        this.state = state;
    }

    public MouseClick(String encoded) {
        parse(encoded);
    }
    
    @Override
    public final void parse(String encoded) {
        String[] fields = encoded.split(":");
        
        button = Button.parseButton(fields[1]);
        state = Boolean.parseBoolean(fields[2]);
    }

    @Override
    public String encode() {
        String encoded = ID+":"+button+":"+Boolean.toString(state);
        return encoded;
    }

    @Override
    public void run(Robot robot) {
        int btn = 0;
        
        switch(button.getButtonMap()) {
            case LEFT_CLICK:
                btn = InputEvent.BUTTON1_MASK;
                break;
            case RIGHT_CLICK:
                btn = InputEvent.BUTTON3_MASK;
                break;
        }
        
        if(state) {
            robot.mousePress(btn);
        } else {
            robot.mouseRelease(btn);
        }
            
    }

    @Override
    public String toString() {
        String action = state ? "Press " : "Release ";
        
        action += button.getButtonMap().toString()
                .replace("_", " ").toLowerCase();
        
        return action;
    }
    
}
