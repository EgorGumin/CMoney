package com.lymno.cmoney.model.imported;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginResult {
    @Expose
    @SerializedName("Token")
    String token;

    @Expose
    @SerializedName("Nick")
    String name;

    public LoginResult(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }
}
