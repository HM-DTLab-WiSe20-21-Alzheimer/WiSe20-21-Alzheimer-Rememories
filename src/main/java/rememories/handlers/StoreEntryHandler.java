package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import rememories.util.DateUtil;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static rememories.constants.StringConstants.ENTRY_STORED;
import static rememories.constants.StringConstants.INVALID_DATE;
import static rememories.constants.StringConstants.SLOT_DATE;
import static rememories.constants.StringConstants.SLOT_STORE;
import static rememories.constants.StringConstants.STORE_ENTRY;

/**
 * Save an entry.
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class StoreEntryHandler implements RequestHandler {

    @Override
    public boolean canHandle(final HandlerInput handlerInput) {
        return handlerInput.matches(intentName(STORE_ENTRY));
    }

    @Override
    public Optional<Response> handle(final HandlerInput handlerInput) {
        final IntentRequest intent = (IntentRequest) handlerInput.getRequestEnvelope().getRequest();
        final Slot storeSlot = intent.getIntent().getSlots().get(SLOT_STORE);
        final Slot dateSlot = intent.getIntent().getSlots().get(SLOT_DATE);

        final String date = Optional.ofNullable(dateSlot)
                // take date if given
                .map(Slot::getValue)
                // get today's date
                .orElse(DateUtil.getToday());

        // check date format (yyyy-MM-dd)
        String response = INVALID_DATE;
        if (date.matches("^\\d\\d\\d\\d-\\d\\d-\\d\\d$")) {
            handlerInput.getAttributesManager().getPersistentAttributes().put(date, storeSlot.getValue());
            handlerInput.getAttributesManager().savePersistentAttributes();

            response = ENTRY_STORED;
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(response)
                .withShouldEndSession(false)
                .build();
    }
}
