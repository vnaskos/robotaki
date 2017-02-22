package com.vnaskos.robotaki.handlers;

import com.vnaskos.robotaki.actions.Action;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vasilis Naskos
 */
public class SaveHandler {
    
    private final ArrayList<Action> actions;
    private final String filepath;
    
    public SaveHandler(ArrayList<Action> actions, String filepath) {
        this.actions = actions;
        this.filepath = filepath;
        
        if (!filepath.endsWith(".rmc")) {
            filepath += ".rmc";
        }
    }
    
    public static void save(ArrayList<Action> actions, String filepath) {
        SaveHandler saveHandler = new SaveHandler(actions, filepath);
        
        String output = saveHandler.actionsToString();
        saveHandler.saveToFile(output);
    }
    
    private String actionsToString() {
        StringBuilder output = new StringBuilder();

        for (Action action : actions) {
            output.append(action.encode());
            output.append(System.getProperty("line.separator"));
        }
        
        return output.toString();
    }
    
    private void saveToFile(String output) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filepath));
            bw.write(output);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(SaveHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(SaveHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
