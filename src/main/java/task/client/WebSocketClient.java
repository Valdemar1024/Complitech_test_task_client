package task.client;

import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import task.handler.MyStompSessionHandler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class WebSocketClient {

    private static final String URL = "ws://localhost:8080/user-manager-websocket";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    @SneakyThrows
    public StompSession createSession(String jwtToken) {
        org.springframework.web.socket.client.WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new MyStompSessionHandler();

        StompHeaders stompHeaders = new StompHeaders();
        stompHeaders.add(HttpHeaders.AUTHORIZATION, JWT_TOKEN_PREFIX + jwtToken);

        WebSocketHttpHeaders webSocketHttpHeaders = new WebSocketHttpHeaders();
        webSocketHttpHeaders.add(HttpHeaders.AUTHORIZATION, JWT_TOKEN_PREFIX + jwtToken);

        CompletableFuture<StompSession> stompSessionCompletableFuture = stompClient
                .connectAsync(URL, webSocketHttpHeaders, stompHeaders, sessionHandler);
        return stompSessionCompletableFuture.get(360, TimeUnit.SECONDS);
    }
}
