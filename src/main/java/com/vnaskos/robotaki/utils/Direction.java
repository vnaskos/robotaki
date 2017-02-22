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
public class Direction {
    private final DirectionMap map;
    
    public Direction(DirectionMap map) {
        this.map = map;
    }
    
    public static Direction parseDirection(String str) {
        DirectionMap dir = DirectionMap.valueOf(str);

        return new Direction(dir);
    }

    public DirectionMap getDirectionMap() {
        return map;
    }
    
    @Override
    public String toString() {
        return map.toString();
    }
}
