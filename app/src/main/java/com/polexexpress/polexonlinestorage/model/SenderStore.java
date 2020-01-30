
package com.polexexpress.polexonlinestorage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SenderStore {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("partnerId")
    @Expose
    private Object partnerId;
    @SerializedName("partnerDto")
    @Expose
    private PartnerDto partnerDto;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("person")
    @Expose
    private Object person;
    @SerializedName("phone")
    @Expose
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Object partnerId) {
        this.partnerId = partnerId;
    }

    public PartnerDto getPartnerDto() {
        return partnerDto;
    }

    public void setPartnerDto(PartnerDto partnerDto) {
        this.partnerDto = partnerDto;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getPerson() {
        return person;
    }

    public void setPerson(Object person) {
        this.person = person;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
