/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnaskos.robotaki.handlers;

import com.vnaskos.robotaki.actions.Action;
import com.vnaskos.robotaki.actions.ActionType;
import com.vnaskos.robotaki.actions.DelayAction;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Vasilis Naskos
 */
public class SaveHandlerTest {
    
    private static final List<Action> LIST_OF_ACTIONS
            = Arrays.asList(
                    new DelayAction(200)
            );
    private static final String FAKE_VALID_FILEPATH = "";
    private static final String ACTIONS_LIST_STING_RESULT
            = ActionType.DELAY.value
            + ":200" + System.getProperty("line.separator");
    
    @Test
    public void shouldSaveActionsToFile() throws IOException {
        TestableSaveHandler saveHandler = new TestableSaveHandler(
                LIST_OF_ACTIONS, FAKE_VALID_FILEPATH);
        
        saveHandler.save();
        
        Assert.assertEquals(
                ACTIONS_LIST_STING_RESULT,
                saveHandler.writer.content);
    }
    
    private class TestableSaveHandler extends SaveHandler {
        
        public FakeWriter writer = new FakeWriter();
        
        public TestableSaveHandler(List<Action> actions, String filepath) {
            super(actions, filepath);
        }

        @Override
        protected Writer getWriter() throws IOException {
            return writer;
        }
        
    }
    
    private class FakeWriter extends Writer {

        public String content = "";
        
        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
        }

        @Override
        public void write(String str) throws IOException {
            content += str;
        }
        
        @Override
        public void flush() throws IOException {}

        @Override
        public void close() throws IOException {}
        
    }
    
}
