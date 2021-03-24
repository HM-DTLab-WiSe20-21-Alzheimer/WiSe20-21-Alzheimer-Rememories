package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static rememories.constants.StringConstants.AMAZON_CANCEL;
import static rememories.constants.StringConstants.SESSION_END_MESSAGE;

/**
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
class CancelIntentHandlerTest extends HandlerTest {

    @Override
    RequestHandler getHandler() {
        return new CancelAndStopIntentHandler();
    }

    @Override
    String getName() {
        return AMAZON_CANCEL;
    }

    @Test
    void stop() {
        final Intent intent = Intent.builder()
                .withName(getName())
                .build();

        final String responseSpeech = getResponseSpeech(intent);
        assertTrue(responseSpeech.contains(SESSION_END_MESSAGE));
    }
}
