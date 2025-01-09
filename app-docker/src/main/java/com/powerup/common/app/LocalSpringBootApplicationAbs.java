package com.powerup.common.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * The <code>LocalSpringBootApplicationAbs</code> class is the parent class to use for spring boot applications that run local on a machine.
 *
 * @author Chris Picard
 */
public abstract class LocalSpringBootApplicationAbs extends SpringBootApplicationAbs {
    private static final String USERNAME_FIELD = "username";
    private static final String PASSWORD_FIELD = "password";
    private final WebClient loginClient;
    private final MultiValueMap<String, String> formData;
    private final String keycloakRealm;

    /**
     * Base constructor.
     *
     * @param childClass The class of the child spring boot class.
     * @param keycloakUrl The URL for accessing keycloak.
     * @param username The username to be used in requests.
     * @param password The password to be used in requests.
     * @param keycloakRealm The keycloak realm the local spring application is connecting to.
     */
    public LocalSpringBootApplicationAbs(final Class<?> childClass, final String keycloakUrl, final String username, final String password,
            final String keycloakRealm) {
        super(childClass);
        loginClient = WebClient.builder().baseUrl(keycloakUrl).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();
        formData = new LinkedMultiValueMap<>();
        formData.add("client_id", "login-app");
        formData.add(USERNAME_FIELD, username);
        formData.add(PASSWORD_FIELD, password);
        formData.add("grant_type", PASSWORD_FIELD);
        this.keycloakRealm = keycloakRealm;
    }

    /**
     * Method that allows login information for making method calls to be set.
     *
     * @param username The username to use in requests.
     * @param password The password to use in requests.
     */
    public void setLoginInfo(final String username, final String password) {
        formData.set(USERNAME_FIELD, username);
        formData.set(PASSWORD_FIELD, password);
    }

    /**
     * Utility method that will refresh an access token or generates the initial token to make request.
     */
    public void refreshToken() {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = loginClient.post().uri("/realms/" + keycloakRealm + "/protocol/openid-connect/token")
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(String.class).block();
        try {
            TokenResponse tokenResponse = objectMapper.readValue(result, TokenResponse.class);
            UsernamePasswordAuthenticationToken authReq
                    = new UsernamePasswordAuthenticationToken(formData.get(USERNAME_FIELD), tokenResponse.getAccessToken());
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authReq);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}