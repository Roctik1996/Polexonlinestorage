
package com.polexexpress.polexonlinestorage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Good {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("invoice")
    @Expose
    private Integer invoice;
    @SerializedName("placeNumber")
    @Expose
    private Integer placeNumber;
    @SerializedName("packageNumber")
    @Expose
    private Integer packageNumber;
    @SerializedName("packagingType")
    @Expose
    private String packagingType;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("descriptionRU")
    @Expose
    private Object descriptionRU;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("measure")
    @Expose
    private String measure;
    @SerializedName("dimensions")
    @Expose
    private Object dimensions;
    @SerializedName("weight")
    @Expose
    private Double weight;
    @SerializedName("unitValue")
    @Expose
    private Double unitValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvoice() {
        return invoice;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public Integer getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(Integer placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Integer getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(Integer packageNumber) {
        this.packageNumber = packageNumber;
    }

    public String getPackagingType() {
        return packagingType;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDescriptionRU() {
        return descriptionRU;
    }

    public void setDescriptionRU(Object descriptionRU) {
        this.descriptionRU = descriptionRU;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Object getDimensions() {
        return dimensions;
    }

    public void setDimensions(Object dimensions) {
        this.dimensions = dimensions;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Double unitValue) {
        this.unitValue = unitValue;
    }

}
