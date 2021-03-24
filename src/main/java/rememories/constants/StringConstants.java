package rememories.constants;

/**
 * All the answers.
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class StringConstants {
    public static final String LAUNCH_MESSAGE = "Willkommen zu Rememories!";
    public static final String FALLBACK_MESSAGE = "Das habe ich leider nicht verstanden.";
    public static final String SESSION_END_MESSAGE = "Auf wiederhören.";
    public static final String RE_PROMPT_MESSAGE = "Sagen Sie \"Hilfe\", um alle Befehle zu hören.";
    public static final String HELP = "Mögliche Befehle sind: Speichern, Löschen, Vorlesen, Prüfen, Suchen und Bearbeiten.";
    public static final String ENTRY_NOT_FOUND = "Ich konnte keinen Eintrag für %s finden.";
    public static final String ENTRY_PREFIX = "Eintrag vom %s: ";
    public static final String DELETE_NOT_FOUND = "Ich konnte keinen Eintrag für den %s finden.";
    public static final String DELETE_DONE = "Eintrag vom %s gelöscht.";
    public static final String ENTRY_EXISTS = "Es gibt einen Eintrag.";
    public static final String ENTRY_NOT_EXISTS = "Nein es gibt keinen Eintrag für den %s.";
    public static final String ENTRY_STORED = "Ich habe den Eintrag gespeichert.";
    public static final String FIND_NOT_FOUND = "Ich konnte keinen Eintrag über %s finden.";
    public static final String ENTRY_LATEST = "Letzter Eintrag über %s: %s. Vom %s.";
    public static final String INVALID_DATE = "Ich konnte den Eintrag nicht speichern da das angegebene Datum zu ungenau ist.";
    public static final String REPLACE_RESPONSE = "Ich habe den Eintrag vom %s mit %s ersetzt.";
    public static final String EXPAND_RESPONSE = "Ich habe den Eintrag vom %s um %s erweitert.";

    public static final String NEUTRAL_RESPONSE = "Ok";
    public static final String ENTRY_SEPARATOR = " ; ";
    public static final String SLOT_DATE = "date";
    public static final String SLOT_STORE = "toStore";
    public static final String SLOT_SEARCH = "search";
    public static final String SLOT_TASK = "task";
    public static final String EXPAND_KEYWORD = "erweitern";
    public static final String REPLACE_KEYWORD = "ersetzen";
    public static final String CONFIRMATION = "CONFIRMED";
    public static final String DENIAL = "DENIED";

    public static final String AMAZON_FALLBACK = "AMAZON.FallbackIntent";
    public static final String AMAZON_HELP = "AMAZON.HelpIntent";
    public static final String AMAZON_CANCEL = "AMAZON.CancelIntent";
    public static final String AMAZON_STOP = "AMAZON.StopIntent";
    public static final String CONFIRM_PLAY_ENTRY = "ConfirmPlayEntry";
    public static final String DELETE_ENTRY = "DeleteEntry";
    public static final String REPLACE_ENTRY = "ReplaceEntry";
    public static final String EXPAND_ENTRY = "ExpandEntry";
    public static final String EXIST_ENTRY = "ExistEntry";
    public static final String EDIT_ENTRY = "EditEntry";
    public static final String PLAY_ENTRY = "PlayEntry";
    public static final String FIND_ENTRY = "FindEntry";
    public static final String STORE_ENTRY = "StoreEntry";
    public static final String RECORD_INTENT = "RecordIntent";

    // util class, do not create instances
    private StringConstants() {
    }
}
