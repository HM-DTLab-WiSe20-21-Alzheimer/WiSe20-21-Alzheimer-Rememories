package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static rememories.constants.StringConstants.CONFIRMATION;
import static rememories.constants.StringConstants.DELETE_DONE;
import static rememories.constants.StringConstants.DELETE_ENTRY;
import static rememories.constants.StringConstants.DELETE_NOT_FOUND;
import static rememories.constants.StringConstants.NEUTRAL_RESPONSE;
import static rememories.constants.StringConstants.SLOT_DATE;

/**
 * Delete entry
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class DeleteEntryHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(DELETE_ENTRY));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        final IntentRequest intent = (IntentRequest) handlerInput.getRequestEnvelope().getRequest();
        final String date = intent.getIntent().getSlots().get(SLOT_DATE).getValue();
        final boolean confirmation = intent.getIntent().getConfirmationStatusAsString().equals(CONFIRMATION);

        String response = NEUTRAL_RESPONSE;

        if (confirmation) {
            final Object oldEntry = handlerInput.getAttributesManager().getPersistentAttributes().remove(date);
            handlerInput.getAttributesManager().savePersistentAttributes();

            // no old entry
            response = String.format(oldEntry == null ? DELETE_NOT_FOUND : DELETE_DONE, date);
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(response)
                .withShouldEndSession(false)
                .build();
    }
}
