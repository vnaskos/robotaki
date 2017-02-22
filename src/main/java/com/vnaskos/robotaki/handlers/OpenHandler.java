package com.vnaskos.robotaki.handlers;

import com.vnaskos.robotaki.actions.Action;
import com.vnaskos.robotaki.actions.DelayAction;
import com.vnaskos.robotaki.actions.End;
import com.vnaskos.robotaki.actions.MouseClick;
import com.vnaskos.robotaki.actions.MoveTo;
import com.vnaskos.robotaki.actions.MoveXY;
import com.vnaskos.robotaki.actions.Repeat;
import com.vnaskos.robotaki.ui.ActionObserver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                action = new DelayAction(encodedAction);
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
