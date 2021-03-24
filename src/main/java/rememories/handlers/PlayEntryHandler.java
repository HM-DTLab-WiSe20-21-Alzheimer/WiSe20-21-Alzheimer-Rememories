package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static rememories.constants.StringConstants.ENTRY_NOT_FOUND;
import static rememories.constants.StringConstants.ENTRY_PREFIX;
import static rememories.constants.StringConstants.PLAY_ENTRY;
import static rememories.constants.StringConstants.SLOT_DATE;

/**
 * PLay an entry.
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class PlayEntryHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(PLAY_ENTRY));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        final IntentRequest intent = (IntentRequest) handlerInput.getRequestEnvelope().getRequest();
        final String date = intent.getIntent().getSlots().get(SLOT_DATE).getValue();
        String speechText = String.format(ENTRY_NOT_FOUND, date);

        final Map<String, Object> attributes = handlerInput.getAttributesManager().getPersistentAttributes();

        if (attributes.containsKey(date)) {
            final String entryContent = attributes.get(date).toString();
            speechText = String.format(ENTRY_PREFIX, date) + entryContent;
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
