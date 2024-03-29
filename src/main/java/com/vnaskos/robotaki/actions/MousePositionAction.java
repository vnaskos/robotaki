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
public class MousePositionAction implements Action {

    private int x, y;

    public MousePositionAction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MousePositionAction(String encoded) {
        parse(encoded);
    }

    @Override
    public final void parse(String encoded) {
        String[] fields = encoded.split(":");

        x = Integer.parseInt(fields[1]);
        y = Integer.parseInt(fields[2]);
    }

    @Override
    public String encode() {
        return String.join(":",
                Integer.toString(ActionType.MOUSE_POSITION),
                Integer.toString(x),
                Integer.toString(y));
    }

    @Override
    public void execute(Robot robot) {
        robot.mouseMove(x, y);
    }

    @Override
    public String toString() {
        return String.format("Move mouse to (%d,%d)", x, y);
    }
}
