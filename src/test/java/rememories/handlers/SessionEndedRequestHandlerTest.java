package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static rememories.constants.StringConstants.RECORD_INTENT;
import static rememories.constants.StringConstants.SESSION_END_MESSAGE;

/**
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
class SessionEndedRequestHandlerTest {
    @Test
    void canHandle() {
        SessionEndedHandler handler = new SessionEndedHandler();
        SessionEndedRequest sessionEndedRequest = SessionEndedRequest.builder().build();
        RequestEnvelope requestEnvelope = RequestEnvelope.builder().withRequest(sessionEndedRequest).build();
        HandlerInput handlerInput = HandlerInput.builder().withRequestEnvelope(requestEnvelope).build();

        // assert
        assertTrue(handler.canHandle(handlerInput));
    }

    @Test
    void cannotHandle() {
        SessionEndedHandler handler = new SessionEndedHandler();
        Intent intent = Intent.builder().withName(RECORD_INTENT).build();
        IntentRequest intentRequest = IntentRequest.builder().withIntent(intent).build();
        RequestEnvelope requestEnvelope = RequestEnvelope.builder().withRequest(intentRequest).build();
        HandlerInput handlerInput = HandlerInput.builder().withRequestEnvelope(requestEnvelope).build();

        // assert
        assertFalse(handler.canHandle(handlerInput));
    }

    @Test
    void handle() {
        SessionEndedHandler handler = new SessionEndedHandler();
        SessionEndedRequest sessionEndedRequest = SessionEndedRequest.builder().build();
        RequestEnvelope requestEnvelope = RequestEnvelope.builder().withRequest(sessionEndedRequest).build();
        HandlerInput handlerInput = HandlerInput.builder().withRequestEnvelope(requestEnvelope).build();
        final Optional<Response> maybeResponse = handler.handle(handlerInput);

        // assert
        assertTrue(maybeResponse.isPresent());
        final Response response = maybeResponse.get();
        final String responseSpeech = response.getOutputSpeech().toString();
        assertTrue(responseSpeech.contains(SESSION_END_MESSAGE));
    }
}
