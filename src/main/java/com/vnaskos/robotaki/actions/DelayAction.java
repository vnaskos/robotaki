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

import com.vnaskos.robotaki.exceptions.InvalidActionException;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vasilis Naskos
 */
public class DelayAction implements Action {
    
    private static final Logger LOGGER = Logger
            .getLogger(DelayAction.class.getName());

    private int delayMs;

    public DelayAction() {
        this(0);
    }

    public DelayAction(int delayMs) {
        this.delayMs = delayMs;
    }
    
    public DelayAction(String encoded) {
        try {
            parse(encoded);
        } catch (InvalidActionException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return delay in milliseconds
     */
    public int getDelayMs() {
        return delayMs;
    }
    
    /**
     * @param delayMs milliseconds
     */
    public void setDelayMs(int delayMs) {
        this.delayMs = delayMs;
    }

    @Override
    public final void parse(String encoded) throws InvalidActionException {
        if(encoded == null || encoded.trim().isEmpty()) {
            throw new InvalidActionException("Invalid delay string");
        }
        
        String[] fields = encoded.trim().split(":");
        
        if(fields.length < 2) {
            throw new InvalidActionException("Empty delay value");
        }
        
        delayMs = Integer.parseInt(fields[1]);
    }

    @Override
    public String encode() {
        return String.join(":",
                Integer.toString(ActionType.DELAY),
                Integer.toString(delayMs));
    }

    @Override
    public void execute(Robot robot) {
        robot.delay(delayMs);
    }

    @Override
    public String toString() {
        long min = TimeUnit.MILLISECONDS.toMinutes(delayMs);
        long sec = TimeUnit.MILLISECONDS.toSeconds(delayMs
                - TimeUnit.MINUTES.toMillis(min));
        long ms = delayMs
                - TimeUnit.MINUTES.toMillis(min)
                - TimeUnit.SECONDS.toMillis(sec);
        
        return String.format("Wait %02d:%02d,%04d", min, sec, ms);
    }
}
