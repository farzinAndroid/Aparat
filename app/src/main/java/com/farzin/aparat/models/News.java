package com.farzin.aparat.models;

import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("icon")
    private String icon;
    @SerializedName("type")
    private int type;
    @SerializedName("link")
    private String link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
