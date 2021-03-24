package rememories.handlers;

import com.amazon.ask.dispatcher.request.handler.RequestHandler;

import static rememories.constants.StringConstants.AMAZON_STOP;

/**
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 * @author Anonymous Student
 */
class StopIntentHandlerTest extends HandlerTest {

    @Override
    RequestHandler getHandler() {
        return new CancelAndStopIntentHandler();
    }

    @Override
    String getName() {
        return AMAZON_STOP;
    }
}
