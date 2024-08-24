package task.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import task.dto.AuthRequestDTO;
import task.dto.AuthResponseDTO;

public class JwtTokenClient {

    public static final String LOGIN_URL = "http://localhost:8080/api/v1/auth/login";

    private final RestTemplate restTemplate = new RestTemplate();

    public String getJwtToken(String username, String password) {
        AuthRequestDTO loginRequest = new AuthRequestDTO(username, password);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<AuthRequestDTO> request = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<AuthResponseDTO> response = restTemplate.exchange(
                LOGIN_URL, HttpMethod.POST, request, AuthResponseDTO.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getToken();
        } else {
            throw new RuntimeException("Failed to retrieve JWT token");
        }
    }
}
