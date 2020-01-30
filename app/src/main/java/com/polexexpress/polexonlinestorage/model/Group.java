
package com.polexexpress.polexonlinestorage.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group {

    @SerializedName("filter")
    @Expose
    private Filter filter;
    @SerializedName("pagingInfo")
    @Expose
    private PagingInfo pagingInfo;
    @SerializedName("sortInfos")
    @Expose
    private List<SortInfo> sortInfos = null;

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<SortInfo> getSortInfos() {
        return sortInfos;
    }

    public void setSortInfos(List<SortInfo> sortInfos) {
        this.sortInfos = sortInfos;
    }

}
