package com.lymno.cmoney.model.imported;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Token {
    @Expose
    @SerializedName("Token")
    String accessToken;

    public Token(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
