package task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompSession;
import task.client.JwtTokenClient;
import task.client.WebSocketClient;
import task.dto.DeleteRangeDTO;
import task.dto.GenderDTO;
import task.dto.PageRequestDTO;
import task.dto.UserDTO;

import java.util.Random;

import static task.UrlConstant.REQUEST_CREATE;
import static task.UrlConstant.REQUEST_DELETE;
import static task.UrlConstant.REQUEST_DELETE_RANGE;
import static task.UrlConstant.REQUEST_FIND;
import static task.UrlConstant.REQUEST_UPDATE;

@Slf4j
public class Main {

    private static final WebSocketClient WEB_SOCKET_CLIENT = new WebSocketClient();
    public static final JwtTokenClient JWT_TOKEN_CLIENT = new JwtTokenClient();

    public static void main(String[] args) throws InterruptedException {
        String token = JWT_TOKEN_CLIENT.getJwtToken("admin", "admin");
        StompSession session = WEB_SOCKET_CLIENT.createSession(token);
        session.send(REQUEST_CREATE, getCreateUserDTO());
        Thread.sleep(1000);
        session.send(REQUEST_FIND, new PageRequestDTO(0, 10));
        Thread.sleep(1000);
        session.send(REQUEST_UPDATE, getUpdateUserDTO());
        Thread.sleep(1000);
        session.send(REQUEST_FIND, new PageRequestDTO(0, 10));
        Thread.sleep(1000);
        session.send(REQUEST_DELETE, 2L);
        Thread.sleep(1000);
        session.send(REQUEST_FIND, new PageRequestDTO(0, 10));
        Thread.sleep(1000);
        session.send(REQUEST_CREATE, getCreateUserDTO());
        session.send(REQUEST_CREATE, getCreateUserDTO());
        session.send(REQUEST_CREATE, getCreateUserDTO());
        session.send(REQUEST_CREATE, getCreateUserDTO());
        Thread.sleep(2000);
        session.send(REQUEST_DELETE_RANGE, new DeleteRangeDTO(2L, 4L));
        Thread.sleep(4000);
        session.send(REQUEST_FIND, new PageRequestDTO(0, 10));
        Thread.sleep(4000);
        session.send(REQUEST_DELETE, 1L);
        Thread.sleep(4000);
        session.send(REQUEST_FIND, new PageRequestDTO(0, 10));
        Thread.sleep(4000);
    }

    private static UserDTO getCreateUserDTO() {
        return UserDTO.builder()
                .login("ppa" + new Random().nextInt(1000))
                .password("123@aasds")
                .fullName("Some man")
                .gender(GenderDTO.builder()
                        .id(1)
                        .build())
                .build();
    }

    private static UserDTO getUpdateUserDTO() {
        return UserDTO.builder()
                .id(2L)
                .login("ppa5")
                .build();
    }
}