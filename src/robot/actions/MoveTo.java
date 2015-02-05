package robot.actions;

import java.awt.Robot;

/**
 *
 * @author Vasilis Naskos
 */
public class MoveTo implements Action {
    
    public static final int ID = 1;
    
    private int x, y;

    public MoveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MoveTo(String encoded) {
        parse(encoded);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public final void parse(String encoded) {
        String[] fields = encoded.split(":");
        
        x = Integer.parseInt(fields[1]);
        y = Integer.parseInt(fields[2]);
    }
    
    @Override
    public String encode() {
        String encoded = ID+":"+x+":"+y;
        return encoded;
    }

    @Override
    public void run(Robot robot) {
        robot.mouseMove(x, y);
    }
    
    @Override
    public String toString() {
        String action = "Move mouse to (" + x + "," + y + ")";
        return action;
    }
}
