package rememories.handlers;

import com.amazon.ask.attributes.persistence.PersistenceAdapter;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.RequestEnvelope;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static rememories.constants.StringConstants.REPLACE_ENTRY;
import static rememories.constants.StringConstants.SLOT_DATE;
import static rememories.constants.StringConstants.SLOT_STORE;

/**
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
class ReplaceEntryHandlerTest extends HandlerTest {
    @Override
    RequestHandler getHandler() {
        return new ReplaceEntryHandler() {
        };
    }

    @Override
    String getName() {
        return REPLACE_ENTRY;
    }

    @Test
    void simpleReplace() {
        final String entryContent = "Neuer Eintrag mit Datum";
        final String entryDate = "2000-01-01";
        final String entryReplacement = "Alter besserer Eintrag mit Datum";

        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(Map.of(SLOT_DATE, entryDate, SLOT_STORE, entryReplacement)))
                .build();

        final Map<String, Object> database = new HashMap<>();
        database.put(entryDate, entryContent);

        final PersistenceAdapter mockedPersistenceAdapter = mock(PersistenceAdapter.class);
        when(mockedPersistenceAdapter.getAttributes(any(RequestEnvelope.class)))
                .thenReturn(Optional.of(database));

        // assert
        final String responseSpeech = getResponseSpeech(intent, mockedPersistenceAdapter);
        assertTrue(responseSpeech.contains(entryReplacement));
        assertTrue(responseSpeech.contains(entryDate));
        assertTrue(responseSpeech.contains("Ich habe den Eintrag vom"));
        assertTrue(responseSpeech.contains("mit"));
        assertTrue(responseSpeech.contains("ersetzt."));
    }
}
