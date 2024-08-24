package task.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import task.dto.UserDTO;

import java.lang.reflect.Type;

@Slf4j
public class CreatedHandler implements StompFrameHandler {
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return UserDTO.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        UserDTO userDTO = (UserDTO) payload;
        log.info("Created user: {}", userDTO);
    }
}
