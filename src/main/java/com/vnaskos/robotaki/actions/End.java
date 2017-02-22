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

import java.awt.Robot;

/**
 *
 * @author Vasilis Naskos
 */
public class End implements Action {

    public static final int ID = 6;
    
    public End() {}

    public End(String encoded) {
        parse(encoded);
    }
    
    @Override
    public final void parse(String encoded) {}

    @Override
    public String encode() {
        String encode = ID + "";
        return encode;
    }

    @Override
    public void run(Robot robot) {}

    @Override
    public String toString() {
        String action = "End";
        return action;
    }
}
