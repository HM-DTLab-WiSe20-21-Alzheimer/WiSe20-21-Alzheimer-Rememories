package rememories.handlers;

import com.amazon.ask.attributes.persistence.PersistenceAdapter;
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
import static rememories.constants.StringConstants.CONFIRMATION;
import static rememories.constants.StringConstants.DELETE_ENTRY;
import static rememories.constants.StringConstants.DENIAL;
import static rememories.constants.StringConstants.NEUTRAL_RESPONSE;
import static rememories.constants.StringConstants.SLOT_DATE;

/**
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
class DeleteEntryHandlerTest extends HandlerTest {

    @Override
    DeleteEntryHandler getHandler() {
        return new DeleteEntryHandler();
    }

    @Override
    String getName() {
        return DELETE_ENTRY;
    }

    @Test
    void noEntryFound() {
        final String entryDate = "2003-03-03";

        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_DATE, entryDate))
                .withConfirmationStatus(CONFIRMATION)
                .build();

        // assert
        final String responseSpeech = getResponseSpeech(intent);
        assertTrue(responseSpeech.contains(entryDate));
        assertTrue(responseSpeech.contains("Ich konnte keinen Eintrag für den "));
        assertTrue(responseSpeech.contains("finden."));
    }

    @Test
    void deleteEntryConfirmed() {
        final String entryDate = "2003-04-04";
        final String entryContent = "Bitte nicht löschen, wichtig!";

        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_DATE, entryDate))
                .withConfirmationStatus(CONFIRMATION)
                .build();

        final Map<String, Object> database = new HashMap<>();
        database.put(entryDate, entryContent);

        final PersistenceAdapter mockedPersistenceAdapter = mock(PersistenceAdapter.class);
        when(mockedPersistenceAdapter.getAttributes(any(RequestEnvelope.class)))
                .thenReturn(Optional.of(database));

        // assert
        final String responseSpeech = getResponseSpeech(intent, mockedPersistenceAdapter);
        assertTrue(responseSpeech.contains(entryDate));
        assertTrue(responseSpeech.contains("Eintrag vom "));
        assertTrue(responseSpeech.contains("gelöscht."));
    }

    @Test
    void deleteEntryDenied() {
        final String entryDate = "2003-04-04";
        final String entryContent = "Bitte nicht löschen, wichtig!";

        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_DATE, entryDate))
                .withConfirmationStatus(DENIAL)
                .build();

        final Map<String, Object> database = new HashMap<>();
        database.put(entryDate, entryContent);

        final PersistenceAdapter mockedPersistenceAdapter = mock(PersistenceAdapter.class);
        when(mockedPersistenceAdapter.getAttributes(any(RequestEnvelope.class)))
                .thenReturn(Optional.of(database));

        // assert
        final String responseSpeech = getResponseSpeech(intent, mockedPersistenceAdapter);
        assertTrue(responseSpeech.contains(NEUTRAL_RESPONSE));
    }
}