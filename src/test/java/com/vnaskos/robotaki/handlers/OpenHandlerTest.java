package com.vnaskos.robotaki.handlers;

import com.vnaskos.robotaki.actions.ActionType;
import com.vnaskos.robotaki.actions.Action;
import com.vnaskos.robotaki.actions.DelayAction;
import com.vnaskos.robotaki.actions.RepeatAction;
import com.vnaskos.robotaki.exceptions.InvalidActionException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.vnaskos.robotaki.ui.ActionsListObserver;

/**
 *
 * @author Vasilis Naskos
 */
public class OpenHandlerTest {

    private static final String INVALID_ENCODED_ACTION = null;
    private static final String INVALID_EMPTY_ENCODED_ACTION = "";
    private static final String UNKNOWN_ENCODED_ACTION = "-1";
    private static final String FIVE_MILLISECONDS_ENCODED_DELAY = ActionType.DELAY + ":5";
    private static final int FIVE_MILLISECONDS = 5;
    private static final String ENCODED_REPEAT = ActionType.REPEAT + ":3";
    private static final String END_OF_FILE = null;
    private static final String NON_EXISTING_FILEPATH = "";

    private OpenHandler openHandler;
    private FakeActionObserver fakeActionObserver;

    @Before
    public void setup() {
        fakeActionObserver = new FakeActionObserver();
        openHandler = new OpenHandler(fakeActionObserver);
    }

    @Test(expected = InvalidActionException.class)
    public void parseAction_InvalidEncodedAction_ShouldThrow() throws InvalidActionException {
        openHandler.parseSingleAction(INVALID_ENCODED_ACTION);
    }

    @Test(expected = InvalidActionException.class)
    public void parseAction_EmptyEncodedAction_ShouldThrow() throws InvalidActionException {
        openHandler.parseSingleAction(INVALID_EMPTY_ENCODED_ACTION);
    }

    @Test(expected = InvalidActionException.class)
    public void parseAction_UnknownEncodedAction_ShouldThrow() throws InvalidActionException {
        openHandler.parseSingleAction(UNKNOWN_ENCODED_ACTION);
    }

    @Test
    public void parseAction_ReadEncoded5MsDelay_Return5MsDelayAction() throws InvalidActionException {
        DelayAction delayAction = (DelayAction) openHandler.parseSingleAction(FIVE_MILLISECONDS_ENCODED_DELAY);
        assertEquals(FIVE_MILLISECONDS, delayAction.getDelayMs());
    }

    @Test
    public void parseActions_ReadEncodedStrings_CreateActions() throws IOException {
        BufferedReader br = Mockito.mock(BufferedReader.class);
        Mockito.when(br.readLine()).thenReturn(
                FIVE_MILLISECONDS_ENCODED_DELAY,
                ENCODED_REPEAT, END_OF_FILE);

        openHandler.parseActions(br);

        assertTrue(fakeActionObserver.getLastAction() instanceof RepeatAction);
    }

    @Test
    public void parseActions_ReadInvalidStrings_ShouldNotCreateAction() throws IOException {
        BufferedReader br = Mockito.mock(BufferedReader.class);
        Mockito.when(br.readLine()).thenReturn(
                INVALID_EMPTY_ENCODED_ACTION, END_OF_FILE);

        openHandler.parseActions(br);

        assertNull(fakeActionObserver.getLastAction());
    }

    @Test(expected = FileNotFoundException.class)
    public void open_NonExistingFile_ShouldThrow() throws FileNotFoundException, IOException {
        openHandler.open(NON_EXISTING_FILEPATH);
    }

    @Test
    public void open_WithValidStrings_CreateActions() throws IOException {
        openHandler = new TestableOpenHadler(fakeActionObserver);
        openHandler.open("");

        assertTrue(fakeActionObserver.getLastAction() instanceof RepeatAction);
    }

    private static class TestableOpenHadler extends OpenHandler {

        String encodedActions;

        public TestableOpenHadler(ActionsListObserver observer) {
            super(observer);
        }

        @Override
        protected BufferedReader getReader(String filepath) throws IOException {
            BufferedReader br = Mockito.mock(BufferedReader.class);
            Mockito.when(br.readLine()).thenReturn(ENCODED_REPEAT, END_OF_FILE);
            return br;
        }

        @Override
        protected void validateFile(String filepath) {}

    }

    private static class FakeActionObserver implements ActionsListObserver {
        Action lastAction;

        @Override
        public void addAction(Action action) {
            lastAction = action;
        }

        public Action getLastAction() {
            return lastAction;
        }
    }
}
