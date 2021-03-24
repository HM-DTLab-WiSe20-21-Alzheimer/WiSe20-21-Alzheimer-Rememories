package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static rememories.constants.StringConstants.AMAZON_FALLBACK;
import static rememories.constants.StringConstants.FALLBACK_MESSAGE;

/**
 * Fallback handling.
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class FallbackIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(final HandlerInput handlerInput) {
        return handlerInput.matches(intentName(AMAZON_FALLBACK));
    }

    @Override
    public Optional<Response> handle(final HandlerInput handlerInput) {
        return handlerInput.getResponseBuilder()
                .withSpeech(FALLBACK_MESSAGE)
                .withReprompt(FALLBACK_MESSAGE)
                .build();
    }
}
