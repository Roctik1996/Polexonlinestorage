package com.polexexpress.polexonlinestorage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailGroupInvoice {

    @SerializedName("content")
    @Expose
    private ContentDetailGroupInvoice contentDetailGroupInvoice;

    public ContentDetailGroupInvoice getContentDetailGroupInvoice() {
        return contentDetailGroupInvoice;
    }

    public void setContentDetailGroupInvoice(ContentDetailGroupInvoice contentDetailGroupInvoice) {
        this.contentDetailGroupInvoice = contentDetailGroupInvoice;
    }
}
