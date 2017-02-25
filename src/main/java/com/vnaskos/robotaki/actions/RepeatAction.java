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
public class RepeatAction implements Action {
    
    private int times;
    private int counter;
    private int startLine;

    public RepeatAction(int times) {
        this.times = times;
    }

    public RepeatAction(String encoded) {
        parse(encoded);
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter() {
        this.counter = times;
    }
    
    public int getStartIndex() {
        return startLine;
    }

    public void setStartIndex(int startLine) {
        this.startLine = startLine;
    }

    @Override
    public final void parse(String encoded) {
        String[] fields = encoded.split(":");
        
        times = Integer.parseInt(fields[1]);
    }

    @Override
    public String encode() {
        return ActionType.REPEAT+":"+times;
    }

    @Override
    public void execute(Robot robot) {
        counter--;
    }

    @Override
    public String toString() {
        return "Repeat " + times + " times";
    }
}
