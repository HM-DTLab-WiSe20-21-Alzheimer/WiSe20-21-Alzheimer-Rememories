package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static rememories.constants.StringConstants.AMAZON_HELP;
import static rememories.constants.StringConstants.HELP;

/**
 * Explain user what he can do.
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class HelpIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(AMAZON_HELP));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        return input.getResponseBuilder()
                .withSpeech(HELP)
                .withShouldEndSession(false)
                .build();
    }
}