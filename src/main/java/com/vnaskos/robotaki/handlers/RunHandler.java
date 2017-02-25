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
import com.vnaskos.robotaki.actions.EndAction;
import com.vnaskos.robotaki.actions.RepeatAction;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vasilis Naskos
 */
public class RunHandler implements Runnable {
    
    private static final Logger LOGGER = Logger
            .getLogger(RunHandler.class.getName());
    private static RunHandler instance;
    
    protected final List<Action> actions;
    protected static boolean stop;
    protected int nextAction = 0;
    
    protected RunHandler(List<Action> actions) {
        this.actions = actions;
        stop = false;
    }
    
    public static void start(List<Action> actions) {
        if(instance != null) {
            return;
        }
        
        new Thread(new RunHandler(actions)).start();
    }
    
    @Override
    public void run() {
        Robot robot = getRobot();
        
        if(robot == null) {
            return;
        }
        
        constructRepeatEndPairsFromActions();
        
        while(nextAction < actions.size()) {
            if(stop) { break; }
            
            Action action = actions.get(nextAction);
            action.execute(robot);
            calculateNextIndex(action);
        }
        
        destroyInstance();
    }
    
    protected Robot getRobot() {
        try {
            return new Robot();
        } catch (AWTException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    protected void constructRepeatEndPairsFromActions() {
        List<RepeatAction> repeats = new ArrayList();
                
        for(int i=0; i<actions.size(); i++) {
            Action action = actions.get(i);
            
            if(action instanceof RepeatAction) {
                repeats.add(0, initRepeat(action, i));
            } else if(action instanceof EndAction) {
                EndAction end = (EndAction) action;
                end.setRepeat(repeats.get(0));
                repeats.remove(0);
            }
        }
    }

    protected RepeatAction initRepeat(Action action, int i) {
        RepeatAction repeat = (RepeatAction) action;
        repeat.setStartIndex(i);
        repeat.setCounter();
        
        return repeat;
    }
    
    protected void calculateNextIndex(Action action) {
        if(action instanceof EndAction) {
            EndAction end = (EndAction) action;
            nextAction = end.getNextIndex(nextAction);
        } else {
            nextAction++;
        }
    }
    
    protected void destroyInstance() {
        instance = null;
    }
    
    public static void stop() {
        stop = true;
    }
    
}
