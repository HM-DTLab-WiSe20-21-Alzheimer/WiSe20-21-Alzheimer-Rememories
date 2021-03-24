package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;
import static rememories.constants.StringConstants.SESSION_END_MESSAGE;

/**
 * Session end handler.
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class SessionEndedHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(SessionEndedRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        return input.getResponseBuilder()
                .withSpeech(SESSION_END_MESSAGE)
                .withShouldEndSession(true).build();
    }
}