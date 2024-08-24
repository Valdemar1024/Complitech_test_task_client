package task.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import task.dto.MessageDTO;

import java.lang.reflect.Type;

@Slf4j
public class DeleteHandler implements StompFrameHandler {
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return MessageDTO.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        MessageDTO<Long> messageWithId = (MessageDTO<Long>) payload;
        log.info("Deleted user id: {}", messageWithId.getPayload());
    }
}
