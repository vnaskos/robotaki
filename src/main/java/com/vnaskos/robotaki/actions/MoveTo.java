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
public class MoveTo implements Action {
    
    public static final int ID = 1;
    
    private int x, y;

    public MoveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MoveTo(String encoded) {
        parse(encoded);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public final void parse(String encoded) {
        String[] fields = encoded.split(":");
        
        x = Integer.parseInt(fields[1]);
        y = Integer.parseInt(fields[2]);
    }
    
    @Override
    public String encode() {
        String encoded = ID+":"+x+":"+y;
        return encoded;
    }

    @Override
    public void run(Robot robot) {
        robot.mouseMove(x, y);
    }
    
    @Override
    public String toString() {
        String action = "Move mouse to (" + x + "," + y + ")";
        return action;
    }
}
