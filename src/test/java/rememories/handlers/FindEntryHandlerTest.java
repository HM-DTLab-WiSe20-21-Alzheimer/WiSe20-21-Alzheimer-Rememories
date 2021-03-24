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
import static org.mockito.Mockito.when;
import static rememories.constants.StringConstants.FIND_ENTRY;
import static rememories.constants.StringConstants.SLOT_SEARCH;

/**
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
class FindEntryHandlerTest extends HandlerTest {
    @Override
    String getName() {
        return FIND_ENTRY;
    }

    @Override
    RequestHandler getHandler() {
        return new FindEntryHandler();
    }

    @Test
    void noEntryFound() {
        final String search = "Hans";

        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_SEARCH, search))
                .build();

        // assert
        final String responseSpeech = getResponseSpeech(intent);
        assertTrue(responseSpeech.contains("Ich konnte keinen Eintrag über "));
        assertTrue(responseSpeech.contains(search));
    }

    @Test
    void entryFound() {
        final String search = "Hans";
        final String entryDate1 = "2015-05-31";
        final String entryDate2 = "2010-02-02";
        final String entryDate3 = "2014-03-03";
        final String entryDate4 = "2015-05-05";
        final String entryDate5 = "2015-05-07";
        final String entryDate6 = "2015-05-03";
        final String entryContent1 = "Ich hab Hans endlich mal wieder gesehen";
        final String entryContent2 = "Irgendwas";
        final String entryContent3 = "Ich hab Gertrude getroffen";
        final String entryContent4 = "Ich hab Hans nicht getroffen";
        final String entryContent5 = "Ich habe mich bei Hans verplappert";
        final String entryContent6 = "Ich habe Hans nicht getroffen";


        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_SEARCH, search))
                .build();

        final Map<String, Object> database = new HashMap<>();
        database.put(entryDate1, entryContent1);
        database.put(entryDate2, entryContent2);
        database.put(entryDate3, entryContent3);
        database.put(entryDate4, entryContent4);
        database.put(entryDate5, entryContent5);
        database.put(entryDate6, entryContent6);

        final PersistenceAdapter mockedPersistenceAdapter = getMockedPersistenceAdapter();
        when(mockedPersistenceAdapter.getAttributes(any(RequestEnvelope.class)))
                .thenReturn(Optional.of(database));

        // assert
        final String responseSpeech = getResponseSpeech(intent, mockedPersistenceAdapter);
        assertTrue(responseSpeech.contains(search));
        assertTrue(responseSpeech.contains(entryDate1));
        assertTrue(responseSpeech.contains(entryContent1));
    }

    @Test
    void entryFoundAbstruseDates() {
        final String search = "Hans";
        final String entryDate1 = "20015-05-31";
        final String entryDate2 = "201770-0062-02";
        final String entryDate3 = "20014-03-093";
        final String entryDate4 = "2015-0785-07885";
        final String entryDate5 = "2015-0885-07897";
        final String entryDate6 = "2015-0905-03";
        final String entryContent1 = "Hans";
        final String entryContent2 = "Hans";
        final String entryContent3 = "Hans";
        final String entryContent4 = "Hans";
        final String entryContent5 = "Hans";
        final String entryContent6 = "Hans";


        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_SEARCH, search))
                .build();

        final Map<String, Object> database = new HashMap<>();
        database.put(entryDate1, entryContent1);
        database.put(entryDate2, entryContent2);
        database.put(entryDate3, entryContent3);
        database.put(entryDate4, entryContent4);
        database.put(entryDate5, entryContent5);
        database.put(entryDate6, entryContent6);

        final PersistenceAdapter mockedPersistenceAdapter = getMockedPersistenceAdapter();
        when(mockedPersistenceAdapter.getAttributes(any(RequestEnvelope.class)))
                .thenReturn(Optional.of(database));

        // assert
        final String responseSpeech = getResponseSpeech(intent, mockedPersistenceAdapter);
        assertTrue(responseSpeech.contains(search));
        assertTrue(responseSpeech.contains(entryDate2));
        assertTrue(responseSpeech.contains(entryContent2));
    }

    @Test
    void entryFoundWeirdDates() {
        final String search = "Hans";
        final String entryDate1 = "20015-05-31";
        final String entryDate2 = "11111111-1111111-1111111";
        final String entryDate3 = "11111111-1111111-1111110";
        final String entryDate4 = "11111111-1111110-1111111";
        final String entryDate5 = "11111110-1111111-1111111";
        final String entryContent1 = "Hans";
        final String entryContent2 = "Hans";
        final String entryContent3 = "Hans";
        final String entryContent4 = "Hans";
        final String entryContent5 = "Hans";

        final Intent intent = Intent.builder()
                .withName(getName())
                .withSlots(getSlots(SLOT_SEARCH, search))
                .build();

        final Map<String, Object> database = new HashMap<>();
        database.put(entryDate1, entryContent1);
        database.put(entryDate2, entryContent2);
        database.put(entryDate3, entryContent3);
        database.put(entryDate4, entryContent4);
        database.put(entryDate5, entryContent5);

        final PersistenceAdapter mockedPersistenceAdapter = getMockedPersistenceAdapter();
        when(mockedPersistenceAdapter.getAttributes(any(RequestEnvelope.class)))
                .thenReturn(Optional.of(database));

        // assert
        final String responseSpeech = getResponseSpeech(intent, mockedPersistenceAdapter);
        assertTrue(responseSpeech.contains(search));
        assertTrue(responseSpeech.contains(entryDate2));
        assertTrue(responseSpeech.contains(entryContent2));
    }

}
