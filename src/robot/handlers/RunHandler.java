package robot.handlers;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import robot.ActionObserver;
import robot.actions.Action;
import robot.actions.End;
import robot.actions.Repeat;

/**
 *
 * @author Vasilis Naskos
 */
public class RunHandler {
    
    private static RunHandler instance;
    private final ArrayList<Action> actions;
    private final ActionObserver observer;
    private static boolean exitLoop;
    private static boolean stop;

    protected RunHandler(ArrayList<Action> actions, ActionObserver observer) {
        this.actions = actions;
        this.observer = observer;
        exitLoop = false;
        stop = false;
    }
    
    public static RunHandler getInstance(ArrayList<Action> actions, ActionObserver observer) {
        if (instance == null) {
            instance = new RunHandler(actions, observer);
            return instance;
        }
        return null;
    }
    
    private static void destroyInstance() {
        instance = null;
    }
    
    public void start() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    startProcess();
                } catch (AWTException ex) {
                    Logger.getLogger(RunHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();        
    }
    
    private void startProcess() throws AWTException {
        Robot robot = new Robot();
        ArrayList<Repeat> repeats = new ArrayList<Repeat>();
         
        for(int i=0; i<actions.size(); i++) {
            observer.notifySelection(i);
            Action a = actions.get(i);
            
            if(stop) {
                break;
            }
            if(a instanceof Repeat) {
                Repeat repeat = (Repeat) a;
                repeat.setStartLine(i);
                repeat.setCounter();
                repeats.add(repeat);
            }
            if(!repeats.isEmpty() && a instanceof End) {
                Repeat repeat = repeats.get(repeats.size()-1);
                if(repeat.getCounter() != 0 && !exitLoop) {
                    repeat.decreaseCounter();
                    i = repeat.getStartLine();
                } else {
                    repeats.remove(repeats.size()-1);
                    if(exitLoop) {
                        exitLoop = false;
                    }
                }
            }
                
            a.run(robot);
        }
        
        destroyInstance();
    }
    
    public static void exitLoop() {
        exitLoop = true;
    }

    public static void stop() {
        stop = true;
        exitLoop = true;
    }
}
