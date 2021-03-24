package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static rememories.constants.StringConstants.AMAZON_HELP;
import static rememories.constants.StringConstants.HELP;

/**
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
class HelpIntentHandlerTest extends HandlerTest {
    @Override
    RequestHandler getHandler() {
        return new HelpIntentHandler();
    }

    @Override
    String getName() {
        return AMAZON_HELP;
    }

    @Test
    void askForHelp() {
        final Intent intent = Intent.builder().withName(getName()).build();

        // assert
        final String responseSpeech = getResponseSpeech(intent);
        assertTrue(responseSpeech.contains(HELP));
    }
}
