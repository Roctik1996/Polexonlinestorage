package com.polexexpress.polexonlinestorage.model;

public class Data {
    private String num;
    private String date;
    private String count;
    private String type;

    public Data(String num, String date, String count, String type) {
        this.num = num;
        this.date = date;
        this.count = count;
        this.type = type;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
