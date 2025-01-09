package com.powerup.common.app;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A container for the response to a token request made to keycloak.
 *
 * @author Chris Picard
 */
public class TokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private String expiresIn;
    @JsonProperty("refresh_expires_in")
    private String refreshExpiresIn;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("not-before-policy")
    private String notBeforePolicy;
    @JsonProperty("session_state")
    private String sessionState;
    private String scope;

    /**
     * Default constructor.
     */
    public TokenResponse() {
    }

    /**
     * Accessor for the access token content.
     *
     * @return Access token content.
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Mutator for the access token content.
     *
     * @param accessToken Value of access token content.
     */
    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Accessor for the access token content.
     *
     * @return Access token content.
     */
    public String getExpiresIn() {
        return expiresIn;
    }

    /**
     * Mutator for the expires in.
     *
     * @param expiresIn Value of expires in.
     */
    public void setExpiresIn(final String expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * Accessor for the refresh expires in.
     *
     * @return Refresh expires in.
     */
    public String getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    /**
     * Mutator for the refresh expires in.
     *
     * @param refreshExpiresIn Value of refresh expires in.
     */
    public void setRefreshExpiresIn(final String refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }

    /**
     * Accessor for the refresh token.
     *
     * @return Refresh token.
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * Mutator for the refresh token.
     *
     * @param refreshToken Value of refresh token.
     */
    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * Accessor for the token type.
     *
     * @return Token type.
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * Mutator for the token type.
     *
     * @param tokenType Value of token type.
     */
    public void setTokenType(final String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * Accessor for the not before policy value.
     *
     * @return Not before policy value.
     */
    public String getNotBeforePolicy() {
        return notBeforePolicy;
    }

    /**
     * Mutator for the not before policy value.
     *
     * @param notBeforePolicy Value of not before policy.
     */
    public void setNotBeforePolicy(final String notBeforePolicy) {
        this.notBeforePolicy = notBeforePolicy;
    }

    /**
     * Accessor for the session state.
     *
     * @return Session state.
     */
    public String getSessionState() {
        return sessionState;
    }

    /**
     * Mutator for the session state.
     *
     * @param sessionState Value of session state.
     */
    public void setSessionState(final String sessionState) {
        this.sessionState = sessionState;
    }

    /**
     * Accessor for the scope.
     *
     * @return Scope.
     */
    public String getScope() {
        return scope;
    }

    /**
     * Mutator for the scope.
     *
     * @param scope Value of scope.
     */
    public void setScope(final String scope) {
        this.scope = scope;
    }
}