
package com.polexexpress.polexonlinestorage.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchInvoice {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("changeDate")
    @Expose
    private String changeDate;
    @SerializedName("awbNo")
    @Expose
    private String awbNo;
    @SerializedName("trackNo")
    @Expose
    private String trackNo;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("client")
    @Expose
    private Object client;
    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("senderTrackNo")
    @Expose
    private Object senderTrackNo;
    @SerializedName("receiverTrackNo")
    @Expose
    private Object receiverTrackNo;
    @SerializedName("senderCountry")
    @Expose
    private String senderCountry;
    @SerializedName("receiverCountry")
    @Expose
    private String receiverCountry;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("exportRefs")
    @Expose
    private Object exportRefs;
    @SerializedName("purpose")
    @Expose
    private Object purpose;
    @SerializedName("senderName")
    @Expose
    private String senderName;
    @SerializedName("senderAddress")
    @Expose
    private String senderAddress;
    @SerializedName("senderPhone")
    @Expose
    private String senderPhone;
    @SerializedName("exporterName")
    @Expose
    private String exporterName;
    @SerializedName("exporterAddress")
    @Expose
    private String exporterAddress;
    @SerializedName("exporterPerson")
    @Expose
    private Object exporterPerson;
    @SerializedName("exporterPhone")
    @Expose
    private String exporterPhone;
    @SerializedName("receiverName")
    @Expose
    private String receiverName;
    @SerializedName("receiverPhone")
    @Expose
    private String receiverPhone;
    @SerializedName("receiverPerson")
    @Expose
    private Object receiverPerson;
    @SerializedName("receiverAddress")
    @Expose
    private String receiverAddress;
    @SerializedName("importerName")
    @Expose
    private Object importerName;
    @SerializedName("importerAddress")
    @Expose
    private Object importerAddress;
    @SerializedName("placeNumber")
    @Expose
    private Integer placeNumber;
    @SerializedName("totalAmount")
    @Expose
    private Integer totalAmount;
    @SerializedName("cargoType")
    @Expose
    private String cargoType;
    @SerializedName("shipmentType")
    @Expose
    private Object shipmentType;
    @SerializedName("storeInDate")
    @Expose
    private Object storeInDate;
    @SerializedName("storeOutDate")
    @Expose
    private Object storeOutDate;
    @SerializedName("storeOutDocNo")
    @Expose
    private Object storeOutDocNo;
    @SerializedName("storeOutLnpNo")
    @Expose
    private Object storeOutLnpNo;
    @SerializedName("direction")
    @Expose
    private String direction;
    @SerializedName("senderStore")
    @Expose
    private SenderStore senderStore;
    @SerializedName("receiverStore")
    @Expose
    private Object receiverStore;
    @SerializedName("group")
    @Expose
    private Object group;
    @SerializedName("totalPrice")
    @Expose
    private Double totalPrice;

    @SerializedName("totalWeight")
    @Expose
    private Double totalWeight;
    @SerializedName("goods")
    @Expose
    private List<Good> goods = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getAwbNo() {
        return awbNo;
    }

    public void setAwbNo(String awbNo) {
        this.awbNo = awbNo;
    }

    public String getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(String trackNo) {
        this.trackNo = trackNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getClient() {
        return client;
    }

    public void setClient(Object client) {
        this.client = client;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Object getSenderTrackNo() {
        return senderTrackNo;
    }

    public void setSenderTrackNo(Object senderTrackNo) {
        this.senderTrackNo = senderTrackNo;
    }

    public Object getReceiverTrackNo() {
        return receiverTrackNo;
    }

    public void setReceiverTrackNo(Object receiverTrackNo) {
        this.receiverTrackNo = receiverTrackNo;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Object getExportRefs() {
        return exportRefs;
    }

    public void setExportRefs(Object exportRefs) {
        this.exportRefs = exportRefs;
    }

    public Object getPurpose() {
        return purpose;
    }

    public void setPurpose(Object purpose) {
        this.purpose = purpose;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getExporterName() {
        return exporterName;
    }

    public void setExporterName(String exporterName) {
        this.exporterName = exporterName;
    }

    public String getExporterAddress() {
        return exporterAddress;
    }

    public void setExporterAddress(String exporterAddress) {
        this.exporterAddress = exporterAddress;
    }

    public Object getExporterPerson() {
        return exporterPerson;
    }

    public void setExporterPerson(Object exporterPerson) {
        this.exporterPerson = exporterPerson;
    }

    public String getExporterPhone() {
        return exporterPhone;
    }

    public void setExporterPhone(String exporterPhone) {
        this.exporterPhone = exporterPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public Object getReceiverPerson() {
        return receiverPerson;
    }

    public void setReceiverPerson(Object receiverPerson) {
        this.receiverPerson = receiverPerson;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Object getImporterName() {
        return importerName;
    }

    public void setImporterName(Object importerName) {
        this.importerName = importerName;
    }

    public Object getImporterAddress() {
        return importerAddress;
    }

    public void setImporterAddress(Object importerAddress) {
        this.importerAddress = importerAddress;
    }

    public Integer getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(Integer placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public Object getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(Object shipmentType) {
        this.shipmentType = shipmentType;
    }

    public Object getStoreInDate() {
        return storeInDate;
    }

    public void setStoreInDate(Object storeInDate) {
        this.storeInDate = storeInDate;
    }

    public Object getStoreOutDate() {
        return storeOutDate;
    }

    public void setStoreOutDate(Object storeOutDate) {
        this.storeOutDate = storeOutDate;
    }

    public Object getStoreOutDocNo() {
        return storeOutDocNo;
    }

    public void setStoreOutDocNo(Object storeOutDocNo) {
        this.storeOutDocNo = storeOutDocNo;
    }

    public Object getStoreOutLnpNo() {
        return storeOutLnpNo;
    }

    public void setStoreOutLnpNo(Object storeOutLnpNo) {
        this.storeOutLnpNo = storeOutLnpNo;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public SenderStore getSenderStore() {
        return senderStore;
    }

    public void setSenderStore(SenderStore senderStore) {
        this.senderStore = senderStore;
    }

    public Object getReceiverStore() {
        return receiverStore;
    }

    public void setReceiverStore(Object receiverStore) {
        this.receiverStore = receiverStore;
    }

    public Object getGroup() {
        return group;
    }

    public void setGroup(Object group) {
        this.group = group;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

}
