package rememories.handlers;

import com.amazon.ask.attributes.persistence.PersistenceAdapter;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static rememories.constants.StringConstants.RECORD_INTENT;

/**
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
abstract class HandlerTest {

    RequestHandler getHandler() {
        return null;
    }

    String getName() {
        return "";
    }

    PersistenceAdapter getMockedPersistenceAdapter() {
        return mock(PersistenceAdapter.class);
    }

    String getResponseSpeech(Intent intent, PersistenceAdapter persistenceAdapter) {
        final IntentRequest intentRequest = IntentRequest.builder().withIntent(intent).build();
        final RequestEnvelope requestEnvelope = RequestEnvelope.builder().withRequest(intentRequest).build();
        final HandlerInput handlerInput = HandlerInput.builder()
                .withPersistenceAdapter(persistenceAdapter)
                .withRequestEnvelope(requestEnvelope)
                .build();

        final Optional<Response> maybeResponse = getHandler().handle(handlerInput);

        assertTrue(maybeResponse.isPresent());
        final Response response = maybeResponse.get();

        return response.getOutputSpeech().toString();
    }

    String getResponseSpeech(Intent intent) {
        return getResponseSpeech(intent, getMockedPersistenceAdapter());
    }

    Map<String, Slot> getSlots(Map<String, String> slotNamesAndValues) {
        final Map<String, Slot> slots = new HashMap<>();

        slotNamesAndValues.forEach((name, value) -> slots.put(name, Slot.builder().withValue(value).build()));

        return slots;
    }

    Map<String, Slot> getSlots(String name, String value) {
        return getSlots(Map.of(name, value));
    }

    @Test
    void canHandle() {
        Intent intent = mock(Intent.class);
        when(intent.getName()).thenReturn(getName());
        IntentRequest intentRequest = IntentRequest.builder().withIntent(intent).build();
        RequestEnvelope requestEnvelope = RequestEnvelope.builder().withRequest(intentRequest).build();
        HandlerInput handlerInput1 = HandlerInput.builder().withRequestEnvelope(requestEnvelope).build();

        // assert
        assertTrue(getHandler().canHandle(handlerInput1));
    }

    @Test
    void cannotHandle() {
        Intent intent = Intent.builder().withName(RECORD_INTENT).build();
        IntentRequest intentRequest = IntentRequest.builder().withIntent(intent).build();
        RequestEnvelope requestEnvelope = RequestEnvelope.builder().withRequest(intentRequest).build();
        HandlerInput handlerInput = HandlerInput.builder().withRequestEnvelope(requestEnvelope).build();

        // assert
        assertFalse(getHandler().canHandle(handlerInput));
    }
}
