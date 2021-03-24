package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static rememories.constants.StringConstants.AMAZON_FALLBACK;
import static rememories.constants.StringConstants.FALLBACK_MESSAGE;

/**
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
class FallbackIntentHandlerTest extends HandlerTest {
    @Override
    RequestHandler getHandler() {
        return new FallbackIntentHandler();
    }

    @Override
    String getName() {
        return AMAZON_FALLBACK;
    }

    @Test
    void handle() {
        Intent intent = mock(Intent.class);
        when(intent.getName()).thenReturn(getName());

        // assert
        final String responseSpeech = getResponseSpeech(intent);
        assertTrue(responseSpeech.contains(FALLBACK_MESSAGE));
    }
}
