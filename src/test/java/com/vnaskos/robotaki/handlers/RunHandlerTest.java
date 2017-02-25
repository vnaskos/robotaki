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
package com.vnaskos.robotaki.handlers;

import com.vnaskos.robotaki.actions.Action;
import com.vnaskos.robotaki.actions.DelayAction;
import com.vnaskos.robotaki.actions.EndAction;
import com.vnaskos.robotaki.actions.MoveTo;
import com.vnaskos.robotaki.actions.RepeatAction;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Vasilis Naskos
 */
public class RunHandlerTest {
    
    private static final List<Action> LIST_OF_ACTIONS_WITH_REPEAT
            = Arrays.asList(
                    new RepeatAction(2),
                    new MoveTo(0, 0),
                    new DelayAction(200),
                    new MoveTo(100, 0),
                    new EndAction()
            );
    private static final int REPEAT_INDEX = 0;
    private static final int END_INDEX = 4;
    
    private RunHandler runHandler;
    
    @Before
    public void setup() {
        runHandler = new RunHandler(LIST_OF_ACTIONS_WITH_REPEAT);
    }
    
    @Test
    public void shouldCreateRepeatEndPairsFromActions() {
        runHandler.constructRepeatEndPairsFromActions();
        
        EndAction end = (EndAction) LIST_OF_ACTIONS_WITH_REPEAT.get(END_INDEX);
        
        assertEquals(REPEAT_INDEX, end.getRepeatIndex());
    }
    
    @Test
    public void shouldInitRepeatActions() {
        runHandler.constructRepeatEndPairsFromActions();
        
        RepeatAction repeat = (RepeatAction) LIST_OF_ACTIONS_WITH_REPEAT
                .get(REPEAT_INDEX);
        
        assertEquals(REPEAT_INDEX, repeat.getStartIndex());
    }
}
