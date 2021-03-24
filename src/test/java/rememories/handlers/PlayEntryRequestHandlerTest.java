package rememories.handlers;

import com.amazon.ask.attributes.persistence.PersistenceAdapter;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.RequestEnvelope;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static rememories.constants.StringConstants.PLAY_ENTRY;
import static rememories.constants.StringConstants.SLOT_DATE;

/**
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
class PlayEntryRequestHandlerTest extends HandlerTest {

    @Override
    RequestHandler getHandler() {
        return new PlayEntryHandler();
    }

    @Override
    String getName() {
        return PLAY_ENTRY;
    }

    @Test
    void noEntryFound() {
        final String entryDate = "2002-02-02";

        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_DATE, entryDate))
                .build();

        // assert
        final String responseSpeech = getResponseSpeech(intent);
        assertTrue(responseSpeech.contains("Ich konnte keinen Eintrag für "));
        assertTrue(responseSpeech.contains("finden."));
    }

    @Test
    void playEntry() {
        final String entryDate = "2003-03-03";
        final String entryContent = "Wow es geht ja wirklich";

        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_DATE, entryDate))
                .build();

        final PersistenceAdapter mockedPersistenceAdapter = mock(PersistenceAdapter.class);
        when(mockedPersistenceAdapter.getAttributes(any(RequestEnvelope.class)))
                .thenReturn(Optional.of(Map.of(entryDate, entryContent)));

        // assert
        final String responseSpeech = getResponseSpeech(intent, mockedPersistenceAdapter);
        assertTrue(responseSpeech.contains("Eintrag vom " + entryDate + ": " + entryContent));
    }
}

