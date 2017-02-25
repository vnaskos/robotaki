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

import com.vnaskos.robotaki.utils.MouseDirection;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

/**
 *
 * @author Vasilis Naskos
 */
public class MouseMoveAction implements Action {
    
    private int iterations, step, delay;
    private MouseDirection direction;

    public MouseMoveAction(
            int iterations, int step,
            int delay, MouseDirection direction) {
        this.iterations = iterations;
        this.step = step;
        this.delay = delay;
        this.direction = direction;
    }

    public MouseMoveAction(String encoded) {
        parse(encoded);
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public MouseDirection getDirection() {
        return direction;
    }

    public void setDirection(MouseDirection direction) {
        this.direction = direction;
    }

    @Override
    public final void parse(String encoded) {
        String[] fields = encoded.split(":");
        
        iterations = Integer.parseInt(fields[1]);
        step = Integer.parseInt(fields[2]);
        delay = Integer.parseInt(fields[3]);
        direction = MouseDirection.parseDirection(fields[4]);
    }

    @Override
    public String encode() {
        return ActionType.MOUSE_MOVE + ":" + iterations + ":"
                + step + ":" + delay + ":" + direction;
    }

    @Override
    public void execute(Robot robot) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        int x = point.x;
        int y = point.y;
        
        for(int i=0; i<iterations; i++) {
            if(direction.isUp() || direction.isDown()) {
                y += step;
            } else {
                x += step;
            }
            robot.mouseMove(x, y);
            robot.delay(delay);
        }
    }

    @Override
    public String toString() {
        return String.format("Move mouse %d times %d px %s (delay %dms)",
                iterations, Math.abs(step),
                direction.toString().toLowerCase(),
                delay);
    }
    
}
