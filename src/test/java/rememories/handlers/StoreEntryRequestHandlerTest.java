package rememories.handlers;

import com.amazon.ask.attributes.persistence.PersistenceAdapter;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static rememories.constants.StringConstants.ENTRY_STORED;
import static rememories.constants.StringConstants.INVALID_DATE;
import static rememories.constants.StringConstants.SLOT_DATE;
import static rememories.constants.StringConstants.SLOT_STORE;
import static rememories.constants.StringConstants.STORE_ENTRY;

/**
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
class StoreEntryRequestHandlerTest extends HandlerTest {
    @Override
    RequestHandler getHandler() {
        return new StoreEntryHandler();
    }

    @Override
    String getName() {
        return STORE_ENTRY;
    }

    @Test
    void createSimpleEntry() {
        final String entryContent = "Test Eintrag";

        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_STORE, entryContent))
                .build();

        final Map<String, Object> databaseEntries = new HashMap<>();
        final PersistenceAdapter database = getMockedPersistenceAdapter();
        when(database.getAttributes(any())).thenReturn(Optional.of(databaseEntries));
        final String responseSpeech = getResponseSpeech(intent, database);

        assertTrue(responseSpeech.contains(ENTRY_STORED));

        // assert
        final String today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        assertTrue(database.getAttributes(null).isPresent());
        final Map<String, Object> attributes = database.getAttributes(null).get();
        assertFalse(attributes.isEmpty());
        assertEquals(1, attributes.size());
        assertTrue(attributes.containsKey(today));
        assertEquals(attributes.get(today), entryContent);
    }

    @Test
    void createEntryWithDate() {
        final String entryContent = "Neuer Eintrag mit Datum";
        final String entryDate = "2000-01-01";

        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(Map.of(SLOT_STORE, entryContent, SLOT_DATE, entryDate)))
                .build();

        final Map<String, Object> databaseEntries = new HashMap<>();
        final PersistenceAdapter database = getMockedPersistenceAdapter();
        when(database.getAttributes(any())).thenReturn(Optional.of(databaseEntries));
        final String responseSpeech = getResponseSpeech(intent, database);

        assertTrue(responseSpeech.contains(ENTRY_STORED));

        // assert
        assertTrue(database.getAttributes(null).isPresent());
        final Map<String, Object> attributes = database.getAttributes(null).get();
        assertFalse(attributes.isEmpty());
        assertEquals(1, attributes.size());
        assertTrue(attributes.containsKey(entryDate));
        assertEquals(attributes.get(entryDate), entryContent);
    }

    @Test
    void tryToCreateEntryWithInvalidDate() {
        final String entryContent = "Das ist zu ungenau";
        final String entryDate = "2019";

        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(Map.of(SLOT_STORE, entryContent, SLOT_DATE, entryDate)))
                .build();

        final Map<String, Object> databaseEntries = new HashMap<>();
        final PersistenceAdapter database = getMockedPersistenceAdapter();
        when(database.getAttributes(any())).thenReturn(Optional.of(databaseEntries));

        assertTrue(databaseEntries.isEmpty());
        final String responseSpeech = getResponseSpeech(intent, database);

        // assert
        assertTrue(responseSpeech.contains(INVALID_DATE));
        assertTrue(databaseEntries.isEmpty());
    }
}
