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
import static rememories.constants.StringConstants.CONFIRMATION;
import static rememories.constants.StringConstants.DELETE_ENTRY;
import static rememories.constants.StringConstants.DENIAL;
import static rememories.constants.StringConstants.ENTRY_EXISTS;
import static rememories.constants.StringConstants.EXIST_ENTRY;
import static rememories.constants.StringConstants.SLOT_DATE;

/**
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
class ExistEntryHandlerTest extends HandlerTest {

    @Override
    RequestHandler getHandler() {
        return new ExistEntryHandler();
    }

    @Override
    String getName() {
        return EXIST_ENTRY;
    }

    @Test
    void noEntryFoundNotPlay() {
        final String entryDate = "2003-03-03";

        final Intent intent = Intent.builder()
                .withName(DELETE_ENTRY)
                .withSlots(getSlots(SLOT_DATE, entryDate))
                .withConfirmationStatus(DENIAL)
                .build();

        // assert
        final String responseSpeech = getResponseSpeech(intent);
        assertTrue(responseSpeech.contains(entryDate));
        assertTrue(responseSpeech.contains("Nein es gibt keinen Eintrag für den "));
    }

    @Test
    void entryFoundNotPlay() {
        final String entryDate = "2003-04-04";
        final String entryContent = "Ich existiere";

        final Intent intent = Intent.builder()
                .withName(DELETE_ENTRY)
                .withConfirmationStatus(DENIAL)
                .withSlots(getSlots(SLOT_DATE, entryDate))
                .build();

        final Map<String, Object> database = new HashMap<>();
        database.put(entryDate, entryContent);

        final PersistenceAdapter mockedPersistenceAdapter = mock(PersistenceAdapter.class);
        when(mockedPersistenceAdapter.getAttributes(any(RequestEnvelope.class)))
                .thenReturn(Optional.of(database));

        // assert
        final String responseSpeech = getResponseSpeech(intent, mockedPersistenceAdapter);
        assertTrue(responseSpeech.contains(ENTRY_EXISTS));
    }

    @Test
    void entryFoundShouldPlay() {
        final String entryDate = "2003-04-04";
        final String entryContent = "Spiel mich ab!";

        final Intent intent = Intent.builder()
                .withName(DELETE_ENTRY)
                .withConfirmationStatus(CONFIRMATION)
                .withSlots(getSlots(SLOT_DATE, entryDate))
                .build();

        final Map<String, Object> database = new HashMap<>();
        database.put(entryDate, entryContent);

        final PersistenceAdapter mockedPersistenceAdapter = mock(PersistenceAdapter.class);
        when(mockedPersistenceAdapter.getAttributes(any(RequestEnvelope.class)))
                .thenReturn(Optional.of(database));

        // assert
        final String responseSpeech = getResponseSpeech(intent, mockedPersistenceAdapter);
        assertTrue(responseSpeech.contains(ENTRY_EXISTS));
    }
}
