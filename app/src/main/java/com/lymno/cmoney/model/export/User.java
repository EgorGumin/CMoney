package com.lymno.cmoney.model.export;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by color on 02.04.2016.
 */
public class User {
    @Expose
    @SerializedName("Login")
    private String login;

    @Expose
    @SerializedName("Password")
    private String password;

    @Expose
    @SerializedName("Nickname")
    private String nickname;

    public User(String login, String password, String nickname) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
    }

}
