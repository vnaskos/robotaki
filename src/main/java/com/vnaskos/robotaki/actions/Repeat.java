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
public class Repeat implements Action {
    
    public static final int ID = 5;
    
    private int times;
    private int counter;
    private int startLine;

    public Repeat(int times) {
        this.times = times;
    }

    public Repeat(String encoded) {
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
    
    public void decreaseCounter() {
        counter--;
    }
    
    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    @Override
    public final void parse(String encoded) {
        String[] fields = encoded.split(":");
        
        times = Integer.parseInt(fields[1]);
    }

    @Override
    public String encode() {
        String encoded = ID+":"+times;
        return encoded;
    }

    @Override
    public void run(Robot robot) {}

    @Override
    public String toString() {
        String action = "Repeat " + times + " times";
        return action;
    }
}
