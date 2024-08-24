package task.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import task.UrlConstant;

import java.lang.reflect.Type;

@Slf4j
public class MyStompSessionHandler implements StompSessionHandler {
    @Override
    public void afterConnected(
            StompSession session, StompHeaders connectedHeaders) {
        log.info("After connected");
        session.subscribe(UrlConstant.RESPONSE_CREATE, new CreatedHandler());
        session.subscribe(UrlConstant.RESPONSE_UPDATE, new UpdateHandler());
        session.subscribe(UrlConstant.RESPONSE_DELETE, new DeleteHandler());
        session.subscribe(UrlConstant.RESPONSE_FIND, new FindHandler());
        session.subscribe(UrlConstant.RESPONSE_ACTION, new UserActionHandler());
        session.subscribe(UrlConstant.RESPONSE_ERROR, new ErrorHandler());
        session.subscribe(UrlConstant.RESPONSE_DELETE_RANGE, new DeleteRangeHandler());
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Message msg = (Message) payload;
        log.info("Received :{} from : {}", msg.getPayload(), msg.getHeaders());
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error(new String(payload));
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        log.error(exception.getMessage(), exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class;
    }
}
