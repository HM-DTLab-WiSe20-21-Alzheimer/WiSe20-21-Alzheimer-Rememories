package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static rememories.constants.StringConstants.CONFIRM_PLAY_ENTRY;
import static rememories.constants.StringConstants.ENTRY_EXISTS;
import static rememories.constants.StringConstants.ENTRY_NOT_EXISTS;
import static rememories.constants.StringConstants.EXIST_ENTRY;
import static rememories.constants.StringConstants.SLOT_DATE;

/**
 * Check if entry exists.
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class ExistEntryHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(EXIST_ENTRY));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        final IntentRequest intent = (IntentRequest) handlerInput.getRequestEnvelope().getRequest();
        final String date = intent.getIntent().getSlots().get(SLOT_DATE).getValue();

        final boolean entryAvailable = handlerInput.getAttributesManager().getPersistentAttributes().containsKey(date);

        String response = String.format(ENTRY_NOT_EXISTS, date);

        if (entryAvailable)
            return handlerInput.getResponseBuilder()
                    .withSpeech(ENTRY_EXISTS)
                    .addDelegateDirective(
                            Intent.builder()
                                    .withName(CONFIRM_PLAY_ENTRY)
                                    .withSlots(Map.of(SLOT_DATE, Slot.builder().withName(SLOT_DATE).withValue(date).build()))
                                    .build())
                    .build();


        return handlerInput.getResponseBuilder()
                .withSpeech(response)
                .withShouldEndSession(false)
                .build();
    }
}
