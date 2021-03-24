package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static rememories.constants.StringConstants.AMAZON_CANCEL;
import static rememories.constants.StringConstants.AMAZON_STOP;
import static rememories.constants.StringConstants.SESSION_END_MESSAGE;

/**
 * Cancel and Stop handling.
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class CancelAndStopIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(final HandlerInput handlerInput) {
        return handlerInput.matches(intentName(AMAZON_STOP).or(intentName(AMAZON_CANCEL)));
    }

    @Override
    public Optional<Response> handle(final HandlerInput handlerInput) {
        return handlerInput.getResponseBuilder()
                .withSpeech(SESSION_END_MESSAGE)
                .build();
    }
}

