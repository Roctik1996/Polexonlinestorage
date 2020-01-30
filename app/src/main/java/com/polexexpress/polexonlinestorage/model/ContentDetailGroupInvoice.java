package com.polexexpress.polexonlinestorage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentDetailGroupInvoice {

    @SerializedName("data")
    @Expose
    private List<SearchInvoice> data;

    @SerializedName("pageData")
    @Expose
    private PageData pageData;

    public List<SearchInvoice> getData() {
        return data;
    }

    public void setData(List<SearchInvoice> data) {
        this.data = data;
    }

    public PageData getPageData() {
        return pageData;
    }

    public void setPageData(PageData pageData) {
        this.pageData = pageData;
    }
}
