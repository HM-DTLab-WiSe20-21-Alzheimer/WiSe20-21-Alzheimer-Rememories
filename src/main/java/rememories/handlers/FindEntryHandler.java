package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import rememories.util.DateUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static rememories.constants.StringConstants.ENTRY_LATEST;
import static rememories.constants.StringConstants.FIND_ENTRY;
import static rememories.constants.StringConstants.FIND_NOT_FOUND;
import static rememories.constants.StringConstants.SLOT_SEARCH;

/**
 * Search for an Entry.
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class FindEntryHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(FIND_ENTRY));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        final IntentRequest intent = (IntentRequest) handlerInput.getRequestEnvelope().getRequest();
        final String search = intent.getIntent().getSlots().get(SLOT_SEARCH).getValue();

        final Map<String, String> foundEntries = new HashMap<>();

        handlerInput.getAttributesManager().getPersistentAttributes()
                .forEach((date, entry) -> {
                    if (entry.toString().toLowerCase().contains(search.toLowerCase()))
                        foundEntries.put(date, entry.toString());
                });

        String response = String.format(FIND_NOT_FOUND, search);

        if (!foundEntries.isEmpty()) {
            final String latestEntryDate = Collections.max(foundEntries.keySet(), DateUtil.getComparator());
            final String latestEntryContent = foundEntries.get(latestEntryDate);
            response = String.format(ENTRY_LATEST, search, latestEntryContent, latestEntryDate);
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(response)
                .withShouldEndSession(false)
                .build();
    }
}
