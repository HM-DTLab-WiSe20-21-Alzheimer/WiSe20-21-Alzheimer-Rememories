package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.LaunchRequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;
import static rememories.constants.StringConstants.LAUNCH_MESSAGE;
import static rememories.constants.StringConstants.RE_PROMPT_MESSAGE;

/**
 * Skill start handler.
 *
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
public class CustomLaunchHandler implements LaunchRequestHandler {
    @Override
    public boolean canHandle(HandlerInput input, LaunchRequest launchRequest) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input, LaunchRequest launchRequest) {
        return input.getResponseBuilder()
                .withSpeech(LAUNCH_MESSAGE)
                .withReprompt(RE_PROMPT_MESSAGE)
                .build();
    }
}
