package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static rememories.constants.StringConstants.EDIT_ENTRY;
import static rememories.constants.StringConstants.EXPAND_ENTRY;
import static rememories.constants.StringConstants.EXPAND_KEYWORD;
import static rememories.constants.StringConstants.FALLBACK_MESSAGE;
import static rememories.constants.StringConstants.NEUTRAL_RESPONSE;
import static rememories.constants.StringConstants.REPLACE_ENTRY;
import static rememories.constants.StringConstants.REPLACE_KEYWORD;
import static rememories.constants.StringConstants.SLOT_TASK;

/**
 * Edit an entry.
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class EditEntryHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(EDIT_ENTRY));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        final IntentRequest intent = (IntentRequest) handlerInput.getRequestEnvelope().getRequest();
        final String task = intent.getIntent().getSlots().get(SLOT_TASK).getValue();

        if (task.equals(REPLACE_KEYWORD))
            return handlerInput.getResponseBuilder()
                    .withSpeech(NEUTRAL_RESPONSE)
                    .addDelegateDirective(
                            Intent.builder()
                                    .withName(REPLACE_ENTRY)
                                    .build())
                    .build();
        else if (task.equals(EXPAND_KEYWORD))
            return handlerInput.getResponseBuilder()
                    .withSpeech(NEUTRAL_RESPONSE)
                    .addDelegateDirective(
                            Intent.builder()
                                    .withName(EXPAND_ENTRY)
                                    .build())
                    .build();
        else
            return handlerInput.getResponseBuilder()
                    .withSpeech(FALLBACK_MESSAGE)
                    .withReprompt(FALLBACK_MESSAGE)
                    .build();
    }
}
