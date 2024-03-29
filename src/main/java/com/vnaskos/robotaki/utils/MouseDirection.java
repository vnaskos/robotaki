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

/**
 *
 * @author Vasilis Naskos
 */
public class MouseDirection {

    public enum Value {
        UP, DOWN, LEFT, RIGHT
    }

    private final Value value;

    public MouseDirection(Value value) {
        this.value = value;
    }

    public static MouseDirection parseDirection(String str) {
        Value dir = Value.valueOf(str);

        return new MouseDirection(dir);
    }

    public boolean isUp() { return value == Value.UP; }
    public boolean isDown() { return value == Value.DOWN; }
    public boolean isLeft() { return value == Value.LEFT; }

    @Override
    public String toString() {
        return value.toString();
    }
}
