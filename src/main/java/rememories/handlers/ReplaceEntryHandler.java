package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static rememories.constants.StringConstants.REPLACE_ENTRY;
import static rememories.constants.StringConstants.REPLACE_RESPONSE;
import static rememories.constants.StringConstants.SLOT_DATE;
import static rememories.constants.StringConstants.SLOT_STORE;

/**
 * Replace an existing entry
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class ReplaceEntryHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(REPLACE_ENTRY));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        final IntentRequest intent = (IntentRequest) handlerInput.getRequestEnvelope().getRequest();
        final String date = intent.getIntent().getSlots().get(SLOT_DATE).getValue();
        final String toStore = intent.getIntent().getSlots().get(SLOT_STORE).getValue();

        final String response = String.format(REPLACE_RESPONSE, date, toStore);

        handlerInput.getAttributesManager().getPersistentAttributes().remove(date);
        handlerInput.getAttributesManager().getPersistentAttributes().put(date, toStore);
        handlerInput.getAttributesManager().savePersistentAttributes();

        return handlerInput.getResponseBuilder()
                .withSpeech(response)
                .withShouldEndSession(false)
                .build();
    }
}
