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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Vasilis Naskos
 */
public class DelayTest {
    
    DelayAction delay;
    
    @Before
    public void setUp() {
        delay = new DelayAction();
    }

    @Test(expected = InvalidActionException.class)
    public void parse_NullString_ThrowInvalidException()
            throws InvalidActionException {
        delay.parse(null);
    }
    
    @Test(expected = InvalidActionException.class)
    public void parse_EmptyString_ThrowInvalidException()
            throws InvalidActionException {
        delay.parse("");
    }
    
    @Test(expected=InvalidActionException.class)
    public void parse_EmptyDelayValue_ThrowInvalidException()
            throws InvalidActionException {
        delay.parse(DelayAction.ID + ":");
    }
    
    @Test
    public void parse_DelayFiveMillisecondsString_DelayEqualsFive()
            throws InvalidActionException {
        delay.parse(DelayAction.ID + ":5");
        int actualDelay = delay.getDelayMs();
        assertEquals(5, actualDelay);
    }
    
    @Test(expected = InvalidActionException.class)
    public void parse_DelayFiveMillisecondsStringWithSpaces_DelayEqualsFive()
            throws InvalidActionException {
        delay.parse(DelayAction.ID + " : ");
        int actualDelay = delay.getDelayMs();
        assertEquals(5, actualDelay);
    }
    
    @Test
    public void encode_DelayFiveMilliseconds_ReturnEncodedString() {
        delay.setDelayMs(5);
        String actualDelayString = delay.encode();
        assertEquals(DelayAction.ID + ":5", actualDelayString);
    }
    
    @Test
    public void toString_DelayTwoMinutesFiveSeconds_ReturnInHumanReadableFormat() {
        delay.setDelayMs(125000);
        assertEquals("Wait 02:05,0000", delay.toString());
    }
}
