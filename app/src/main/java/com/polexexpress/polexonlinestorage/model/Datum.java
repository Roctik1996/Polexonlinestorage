
package com.polexexpress.polexonlinestorage.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("store")
    @Expose
    private Store store;
    @SerializedName("awb")
    @Expose
    private String awb;
    @SerializedName("sendDate")
    @Expose
    private String sendDate;
    @SerializedName("receiveDate")
    @Expose
    private String receiveDate;
    @SerializedName("groupStatus")
    @Expose
    private String groupStatus;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("totalPlaceNumber")
    @Expose
    private Integer totalPlaceNumber;
    @SerializedName("totalWeight")
    @Expose
    private Double totalWeight;
    @SerializedName("currencies")
    @Expose
    private List<Currency> currencies = null;
    @SerializedName("invoiceCount")
    @Expose
    private Integer invoiceCount;
    @SerializedName("senderCountry")
    @Expose
    private String senderCountry;

    @SerializedName("receiverCountry")
    @Expose
    private String receiverCountry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getAwb() {
        return awb;
    }

    public void setAwb(String awb) {
        this.awb = awb;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Integer getTotalPlaceNumber() {
        return totalPlaceNumber;
    }

    public void setTotalPlaceNumber(Integer totalPlaceNumber) {
        this.totalPlaceNumber = totalPlaceNumber;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public Integer getInvoiceCount() {
        return invoiceCount;
    }

    public void setInvoiceCount(Integer invoiceCount) {
        this.invoiceCount = invoiceCount;
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
    }

    public String getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry;
    }
}
