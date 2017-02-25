/*
 * This file is part of Robotaki.
 *
 * Robotaki is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Robotaki.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.vnaskos.robotaki.handlers;

import com.vnaskos.robotaki.actions.Action;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vasilis Naskos
 */
public class SaveHandler {
    
    private static final Logger LOGGER = Logger
            .getLogger(SaveHandler.class.getName());
    private final List<Action> actions;
    private final String filepath;
    
    public SaveHandler(List<Action> actions, String filepath) {
        this.actions = actions;
        this.filepath = filepath;
    }
    
    public void save() {
        saveToFile(actionsToString());
    }
    
    protected String actionsToString() {
        StringBuilder output = new StringBuilder();

        for (Action action : actions) {
            output.append(action.encode());
            output.append(System.getProperty("line.separator"));
        }
        
        return output.toString();
    }
    
    private void saveToFile(String output) {
        try(Writer writer = getWriter()) {
            writer.write(output);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    protected Writer getWriter()
            throws IOException {
        Writer writer = new FileWriter(filepath);
        return writer;
    }
    
}
