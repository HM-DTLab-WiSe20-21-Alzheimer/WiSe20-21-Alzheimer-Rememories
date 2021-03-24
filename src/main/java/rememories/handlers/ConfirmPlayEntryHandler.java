package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static rememories.constants.StringConstants.CONFIRMATION;
import static rememories.constants.StringConstants.CONFIRM_PLAY_ENTRY;
import static rememories.constants.StringConstants.NEUTRAL_RESPONSE;
import static rememories.constants.StringConstants.SLOT_DATE;

/**
 * Confirm that entry should be played
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class ConfirmPlayEntryHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(CONFIRM_PLAY_ENTRY));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        final IntentRequest intent = (IntentRequest) handlerInput.getRequestEnvelope().getRequest();
        final String date = intent.getIntent().getSlots().get(SLOT_DATE).getValue();

        String response = NEUTRAL_RESPONSE;
        if (intent.getIntent().getConfirmationStatusAsString().equals(CONFIRMATION)) {
            final Map<String, Object> attributes = handlerInput.getAttributesManager().getPersistentAttributes();
            response = attributes.get(date).toString();
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(response)
                .withShouldEndSession(false)
                .build();
    }
}
