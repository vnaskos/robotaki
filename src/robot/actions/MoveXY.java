package robot.actions;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import robot.utils.Direction;
import robot.utils.DirectionMap;

/**
 *
 * @author Vasilis Naskos
 */
public class MoveXY implements Action {
    
    public static final int ID = 2;
    
    private int iterations, step, delay;
    private Direction direction;

    public MoveXY(int iterations, int step, int delay, Direction direction) {
        this.iterations = iterations;
        this.step = step;
        this.delay = delay;
        this.direction = direction;
    }

    public MoveXY(String encoded) {
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public final void parse(String encoded) {
        String[] fields = encoded.split(":");
        
        iterations = Integer.parseInt(fields[1]);
        step = Integer.parseInt(fields[2]);
        delay = Integer.parseInt(fields[3]);
        direction = Direction.parseDirection(fields[4]);
    }

    @Override
    public String encode() {
        String encoded = ID+":"+iterations+":"+step+":"+delay+":"+direction;
        return encoded;
    }

    @Override
    public void run(Robot robot) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        int x = point.x;
        int y = point.y;
        
        for(int i=0; i<iterations; i++) {
            if(direction.getDirectionMap() == DirectionMap.UP || direction.getDirectionMap() == DirectionMap.DOWN) {
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
        String dir = direction.toString().toLowerCase();
        
        int stepAbs = Math.abs(step);
        String action = "Move mouse " + iterations + " times " + stepAbs +
                " px " + dir + " (delay " + delay + ")";
        return action;
    }
    
}
