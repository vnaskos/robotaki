/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnaskos.robotaki.handlers;

import com.vnaskos.robotaki.actions.Action;
import com.vnaskos.robotaki.actions.ActionType;
import com.vnaskos.robotaki.actions.DelayAction;
import org.junit.Assert;
import org.junit.Test;

import java.io.Writer;
import java.util.List;

/**
 *
 * @author Vasilis Naskos
 */
public class SaveHandlerTest {

    private static final List<Action> LIST_OF_ACTIONS = List.of(new DelayAction(200));
    private static final String FAKE_VALID_FILEPATH = "";
    private static final String ACTIONS_LIST_STING_RESULT = ActionType.DELAY + ":200" + System.lineSeparator();

    @Test
    public void shouldSaveActionsToFile() {
        TestableSaveHandler saveHandler = new TestableSaveHandler(
            LIST_OF_ACTIONS, FAKE_VALID_FILEPATH);

        saveHandler.save();

        Assert.assertEquals(
                ACTIONS_LIST_STING_RESULT,
                saveHandler.writer.content);
    }

    private static class TestableSaveHandler extends SaveHandler {

        public FakeWriter writer = new FakeWriter();

        public TestableSaveHandler(List<Action> actions, String filepath) {
            super(actions, filepath);
        }

        @Override
        protected Writer getWriter() {
            return writer;
        }

    }

    private static class FakeWriter extends Writer {

        public String content = "";

        @Override
        public void write(char[] cbuf, int off, int len) {
        }

        @Override
        public void write(String str) {
            content += str;
        }

        @Override
        public void flush() {}

        @Override
        public void close() {}

    }

}
