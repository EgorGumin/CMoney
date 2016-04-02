package com.lymno.cmoney.model.export;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginData {
    @Expose
    @SerializedName("Login")
    private String login;

    @Expose
    @SerializedName("Password")
    private String password;

    public LoginData(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
