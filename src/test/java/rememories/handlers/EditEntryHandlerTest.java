package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static rememories.constants.StringConstants.EDIT_ENTRY;
import static rememories.constants.StringConstants.EXPAND_KEYWORD;
import static rememories.constants.StringConstants.FALLBACK_MESSAGE;
import static rememories.constants.StringConstants.NEUTRAL_RESPONSE;
import static rememories.constants.StringConstants.REPLACE_KEYWORD;
import static rememories.constants.StringConstants.SLOT_TASK;

/**
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
class EditEntryHandlerTest extends HandlerTest {

    @Override
    RequestHandler getHandler() {
        return new EditEntryHandler();
    }

    @Override
    String getName() {
        return EDIT_ENTRY;
    }

    @Test
    void fallback() {
        final String falseTask = "ignore";

        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_TASK, falseTask))
                .build();

        // assert
        assertTrue(getResponseSpeech(intent).contains(FALLBACK_MESSAGE));
    }

    @Test
    void simpleEditExpand() {
        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_TASK, EXPAND_KEYWORD))
                .build();

        // assert
        final String responseSpeech = getResponseSpeech(intent);
        assertTrue(responseSpeech.contains(NEUTRAL_RESPONSE));
    }

    @Test
    void simpleEditReplace() {
        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_TASK, REPLACE_KEYWORD))
                .build();

        // assert
        final String responseSpeech = getResponseSpeech(intent);
        assertTrue(responseSpeech.contains(NEUTRAL_RESPONSE));
    }
}

