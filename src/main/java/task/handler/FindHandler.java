package task.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;

@Slf4j
public class FindHandler implements StompFrameHandler {
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return PageImpl.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        PageImpl page = (PageImpl) payload;
        log.info("Found users: {}", page);
    }
}
