package com.tecba.adsitios.services;

import com.tecba.adsitios.dtos.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final WebClient.Builder webClientBuilder;
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Value("${google.url.auth}")
    private String urlAuth;
    @Value("${google.client.id}")
    private  String clientId;
    @Value("${google.client.secret}")
    private String clientSecret;
    @Value("${google.grant.type}")
    private String grantType;
    @Value("${google.refresh.token}")
    private   String refreshToken;

    public String getToken(){
        WebClient client = webClientBuilder
                .baseUrl(urlAuth)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Map<String,String> body=Map.of(
                "client_id",clientId,
                "client_secret",clientSecret,
                "refresh_token",refreshToken,
                "grant_type",grantType
        );

        return client.post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(AuthResponse.class)
                .map(AuthResponse::getAccessToken)
                .block();

    }

}
