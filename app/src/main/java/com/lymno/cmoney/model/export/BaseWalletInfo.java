package com.lymno.cmoney.model.export;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by color on 02.04.2016.
 */
public class BaseWalletInfo {
    @Expose
    @SerializedName("Name")
    private String name;

    @Expose
    @SerializedName("TargetSum")
    private int goal;

    public BaseWalletInfo(String name, int goal) {
        this.name = name;
        this.goal = goal;
    }
}
