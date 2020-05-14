package com.melons.game.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenData {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("expiration")
    @Expose
    private Expiration expiration;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Expiration getExpiration() {
        return expiration;
    }

    public void setExpiration(Expiration expiration) {
        this.expiration = expiration;
    }

}
