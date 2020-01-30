package com.polexexpress.polexonlinestorage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreInfo {
    @SerializedName("groupCount")
    @Expose
    private Integer groupCount;
    @SerializedName("groupsInvoiceCount")
    @Expose
    private Integer groupsInvoiceCount;
    @SerializedName("myInvoiceCount")
    @Expose
    private Integer myInvoiceCount;
    @SerializedName("totalInvoiceCount")
    @Expose
    private Integer totalInvoiceCount;

    public Integer getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }

    public Integer getGroupsInvoiceCount() {
        return groupsInvoiceCount;
    }

    public void setGroupsInvoiceCount(Integer groupsInvoiceCount) {
        this.groupsInvoiceCount = groupsInvoiceCount;
    }

    public Integer getMyInvoiceCount() {
        return myInvoiceCount;
    }

    public void setMyInvoiceCount(Integer myInvoiceCount) {
        this.myInvoiceCount = myInvoiceCount;
    }

    public Integer getTotalInvoiceCount() {
        return totalInvoiceCount;
    }

    public void setTotalInvoiceCount(Integer totalInvoiceCount) {
        this.totalInvoiceCount = totalInvoiceCount;
    }
}
