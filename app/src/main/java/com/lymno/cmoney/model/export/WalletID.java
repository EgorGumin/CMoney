package com.lymno.cmoney.model.export;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by color on 02.04.2016.
 */
public class WalletID {
    @Expose
    @SerializedName("WalletId")
    private int walletID;

    public WalletID(int walletID) {
        this.walletID = walletID;
    }
}
