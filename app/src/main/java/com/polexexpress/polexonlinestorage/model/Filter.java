
package com.polexexpress.polexonlinestorage.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filter {
    @SerializedName("awbNo")
    @Expose
    private String awbNo;

    public String getAwbNo() {
        return awbNo;
    }

    public void setAwbNo(String awbNo) {
        this.awbNo = awbNo;
    }
}
