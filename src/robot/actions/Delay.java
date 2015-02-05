package robot.actions;

import java.awt.Robot;

/**
 *
 * @author Vasilis Naskos
 */
public class Delay implements Action {
    
    public static final int ID = 4;
    
    private int delay;

    public Delay(int delay) {
        this.delay = delay;
    }

    public Delay(String encoded) {
        parse(encoded);
    }

    @Override
    public final void parse(String encoded) {
        String[] fields = encoded.split(":");
        
        delay = Integer.parseInt(fields[1]);
    }

    @Override
    public String encode() {
        String encoded = ID+":"+delay;
        return encoded;
    }

    @Override
    public void run(Robot robot) {
        robot.delay(delay);
    }

    @Override
    public String toString() {
        String action = "Wait ";
        int wait = delay;
        
        if(wait >= 60000) {
            action += (wait/1000)/60 + "m ";
            wait -= ((wait/1000)/60)*60000;
        }
        if(wait >= 1000) {
            action += wait/1000 + "s ";
            wait -= (wait/1000)*1000;
        }
        if(wait != 0) {
            action += wait + "ms";
        }
        return action;
    }
}
