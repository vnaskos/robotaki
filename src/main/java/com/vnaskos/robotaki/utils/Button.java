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
package com.vnaskos.robotaki.utils;

import java.awt.event.InputEvent;

/**
 *
 * @author Vasilis Naskos
 */
public enum Button {
    LEFT_CLICK(InputEvent.BUTTON1_MASK),
    RIGHT_CLICK(InputEvent.BUTTON3_MASK);
    
    public int name;

    private Button(int name) {
        this.name = name;
    }
    
    public static Button getByName(String name) {
        return Button.valueOf(name);
    }

    @Override
    public String toString() {
        return super.toString()
                .replace("_", " ")
                .toLowerCase();
    }
}
