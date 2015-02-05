package robot.handlers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import robot.ActionObserver;
import robot.actions.Action;
import robot.actions.Delay;
import robot.actions.End;
import robot.actions.MouseClick;
import robot.actions.MoveTo;
import robot.actions.MoveXY;
import robot.actions.Repeat;

/**
 *
 * @author Vasilis Naskos
 */
public class OpenHandler {
    
    ActionObserver observer;
    String filepath;

    protected OpenHandler(ActionObserver observer, String filepath) {
        this.observer = observer;
        this.filepath = filepath;
    }
    
    public static void open(ActionObserver observer, String filepath) {
        OpenHandler loadHandler = new OpenHandler(observer, filepath);
        loadHandler.loadActions();
    }
    
    private void loadActions() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filepath));
            
            while (in.ready()) {
                String encodedAction = in.readLine();
                addAction(encodedAction);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(OpenHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(OpenHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void addAction(String encodedAction) {
        Action action = getIdentifiedAction(encodedAction);  
        
        if (action != null) {
            observer.addAction(action);
        }
    }
    
    private Action getIdentifiedAction(String encodedAction) {
        int id = Integer.parseInt(encodedAction.split(":")[0]);
        Action action = null;
        
        switch (id) {
            case 1:
                action = new MoveTo(encodedAction);
                break;
            case 2:
                action = new MoveXY(encodedAction);
                break;
            case 3:
                action = new MouseClick(encodedAction);
                break;
            case 4:
                action = new Delay(encodedAction);
                break;
            case 5:
                action = new Repeat(encodedAction);
                break;
            case 6:
                action = new End(encodedAction);
                break;
        }
        
        return action;
    }
}
