package rememories.handlers;

import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import static rememories.constants.DynamoDBConstants.TABLE_NAME;

/**
 * Skill entry point.
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class RememoriesStreamHandler extends SkillStreamHandler {

    /**
     * Dieser Konstruktor initialisiert den Skill und fügt die verwendeten Handler und Features
     * hinzu.
     */
    public RememoriesStreamHandler() {
        super(Skills.standard()
                .addRequestHandlers(
                        new CustomLaunchHandler(),
                        new PlayEntryHandler(),
                        new StoreEntryHandler(),
                        new FindEntryHandler(),
                        new DeleteEntryHandler(),
                        new ExistEntryHandler(),
                        new ConfirmPlayEntryHandler(),
                        new HelpIntentHandler(),
                        new CancelAndStopIntentHandler(),
                        new FallbackIntentHandler(),
                        new SessionEndedHandler(),
                        new EditEntryHandler(),
                        new ExpandEntryHandler(),
                        new ReplaceEntryHandler()
                )
                .withTableName(TABLE_NAME)
                .withAutoCreateTable(true)
                .build());
    }
}
