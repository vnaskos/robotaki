package robot.actions;

import java.awt.Robot;

/**
 *
 * @author Vasilis Naskos
 */
public class End implements Action {

    public static final int ID = 6;
    
    public End() {}

    public End(String encoded) {
        parse(encoded);
    }
    
    @Override
    public final void parse(String encoded) {}

    @Override
    public String encode() {
        String encode = ID + "";
        return encode;
    }

    @Override
    public void run(Robot robot) {}

    @Override
    public String toString() {
        String action = "End";
        return action;
    }
}
