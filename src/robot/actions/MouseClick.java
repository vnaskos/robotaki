package robot.actions;

import java.awt.Robot;
import java.awt.event.InputEvent;
import robot.utils.Button;

/**
 *
 * @author Vasilis Naskos
 */
public class MouseClick implements Action {

    public static final int ID = 3;

    private Button button;
    private boolean state;

    public MouseClick(Button button, boolean state) {
        this.button = button;
        this.state = state;
    }

    public MouseClick(String encoded) {
        parse(encoded);
    }
    
    @Override
    public final void parse(String encoded) {
        String[] fields = encoded.split(":");
        
        button = Button.parseButton(fields[1]);
        state = Boolean.parseBoolean(fields[2]);
    }

    @Override
    public String encode() {
        String encoded = ID+":"+button+":"+Boolean.toString(state);
        return encoded;
    }

    @Override
    public void run(Robot robot) {
        int btn = 0;
        
        switch(button.getButtonMap()) {
            case LEFT_CLICK:
                btn = InputEvent.BUTTON1_MASK;
                break;
            case RIGHT_CLICK:
                btn = InputEvent.BUTTON3_MASK;
                break;
        }
        
        if(state) {
            robot.mousePress(btn);
        } else {
            robot.mouseRelease(btn);
        }
            
    }

    @Override
    public String toString() {
        String action = state ? "Press " : "Release ";
        
        action += button.getButtonMap().toString()
                .replace("_", " ").toLowerCase();
        
        return action;
    }
    
}
