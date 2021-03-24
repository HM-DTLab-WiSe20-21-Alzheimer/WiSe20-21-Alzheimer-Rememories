package rememories.handlers;

import com.amazon.ask.attributes.persistence.PersistenceAdapter;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.RequestEnvelope;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static rememories.constants.StringConstants.CONFIRMATION;
import static rememories.constants.StringConstants.CONFIRM_PLAY_ENTRY;
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
class ConfirmPlayEntryHandlerTest extends HandlerTest {
    @Override
    RequestHandler getHandler() {
        return new ConfirmPlayEntryHandler();
    }

    @Override
    String getName() {
        return CONFIRM_PLAY_ENTRY;
    }

    @Test
    void playConfirmed() {
        final String entryDate = "2003-03-03";
        final String entryContent = "Wow es geht ja wirklich";

        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_DATE, entryDate))
                .withConfirmationStatus(CONFIRMATION)
                .build();

        final PersistenceAdapter mockedPersistenceAdapter = mock(PersistenceAdapter.class);
        when(mockedPersistenceAdapter.getAttributes(any(RequestEnvelope.class)))
                .thenReturn(Optional.of(Map.of(entryDate, entryContent)));

        // assert
        final String responseSpeech = getResponseSpeech(intent, mockedPersistenceAdapter);
        assertTrue(responseSpeech.contains(entryContent));
    }

    @Test
    void playDenied() {
        final String entryDate = "2003-03-03";

        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_DATE, entryDate))
                .withConfirmationStatus(DENIAL)
                .build();

        // assert
        final String responseSpeech = getResponseSpeech(intent);
        assertTrue(responseSpeech.contains(NEUTRAL_RESPONSE));
    }


}
