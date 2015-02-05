package robot.actions;

import java.awt.Robot;

/**
 *
 * @author Vasilis Naskos
 */
public interface Action {
    
    public void parse(String encoded);
    public String encode();
    public void run(Robot robot);
    
}
