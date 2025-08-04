package com.mina.kartngo.data.remote.auth.pojo;

public class LoginResponse {
    private String username;
    private String source;
    private String token;
    private int appVersion;
    private String creationDate;
    private String expirationDate;
    private long expirationMillis;

    public LoginResponse(String username, String source, String token, int appVersion, String creationDate, String expirationDate, long expirationMillis) {
        this.username = username;
        this.source = source;
        this.token = token;
        this.appVersion = appVersion;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.expirationMillis = expirationMillis;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(int appVersion) {
        this.appVersion = appVersion;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getExpirationMillis() {
        return expirationMillis;
    }

    public void setExpirationMillis(long expirationMillis) {
        this.expirationMillis = expirationMillis;
    }
}

