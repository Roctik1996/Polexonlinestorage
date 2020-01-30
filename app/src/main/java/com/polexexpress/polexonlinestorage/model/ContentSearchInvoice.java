
package com.polexexpress.polexonlinestorage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentSearchInvoice {

    @SerializedName("content")
    @Expose
    private SearchInvoice searchInvoice;

    public SearchInvoice getSearchInvoice() {
        return searchInvoice;
    }

    public void setSearchInvoice(SearchInvoice searchInvoice) {
        this.searchInvoice = searchInvoice;
    }

}
