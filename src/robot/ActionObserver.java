package robot;

import robot.actions.Action;

/**
 *
 * @author Vasilis Naskos
 */
public interface ActionObserver {
    
    public void notifySelection(int line);
    public void addAction(Action action);
    
}
