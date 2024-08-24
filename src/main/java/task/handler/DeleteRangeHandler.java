package task.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import task.dto.DeleteRangeDTO;

import java.lang.reflect.Type;

@Slf4j
public class DeleteRangeHandler implements StompFrameHandler {
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return DeleteRangeDTO.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        DeleteRangeDTO deleteRange = (DeleteRangeDTO) payload;
        log.info("Deleted user in id range: {}", deleteRange);
    }
}
