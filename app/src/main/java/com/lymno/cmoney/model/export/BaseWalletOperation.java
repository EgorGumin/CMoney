package com.lymno.cmoney.model.export;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by color on 02.04.2016.
 */
public class BaseWalletOperation {
    @Expose
    @SerializedName("Text")
    private String text;

    @Expose
    @SerializedName("AccountId")
    private int walletID;

    @Expose
    @SerializedName("Value")
    private int sum;

    public BaseWalletOperation(String text, int walletID, int sum) {
        this.text = text;
        this.walletID = walletID;
        this.sum = sum;
    }
}
