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
public class EndAction implements Action {
    
    protected RepeatAction repeat;
    
    public EndAction() {}

    public EndAction(String encoded) {
        parse(encoded);
    }
    
    @Override
    public final void parse(String encoded) {}

    @Override
    public String encode() {
        return Integer.toString(ActionType.END);
    }

    @Override
    public void execute(Robot robot) {}
    
    public int getNextIndex(int nextAction) {
        return repeat.getCounter() < 0
                ? nextAction + 1
                : repeat.getStartIndex();
    }

    public void setRepeat(RepeatAction repeat) {
        this.repeat = repeat;
    }
    
    public int getRepeatIndex() {
        return repeat.getStartIndex();
    }

    @Override
    public String toString() {
        return "End";
    }
}
