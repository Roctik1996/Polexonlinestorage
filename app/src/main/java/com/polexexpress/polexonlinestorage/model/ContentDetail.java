
package com.polexexpress.polexonlinestorage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentDetail {

    @SerializedName("content")
    @Expose
    private Datum data;

    public Datum getData() {
        return data;
    }

    public void setData(Datum data) {
        this.data = data;
    }
}
