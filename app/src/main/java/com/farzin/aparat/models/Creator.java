package com.farzin.aparat.models;

import com.google.gson.annotations.SerializedName;

public class Creator {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("icon")
    private String icon;
    @SerializedName("description")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
