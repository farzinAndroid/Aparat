package com.farzin.aparat.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Videos implements Parcelable {

    public Videos() {

    }

    @SerializedName("id")
    private int id;
    @SerializedName("cat_id")
    private int catId;
    @SerializedName("title")
    private String title;
    @SerializedName("icon")
    private String icon;
    @SerializedName("creator")
    private int creator;
    @SerializedName("description")
    private String description;
    @SerializedName("link")
    private String link;
    @SerializedName("view")
    private int view;
    @SerializedName("time")
    private String time;
    @SerializedName("special")
    private int special;

    protected Videos(Parcel in) {
        id = in.readInt();
        catId = in.readInt();
        title = in.readString();
        icon = in.readString();
        creator = in.readInt();
        description = in.readString();
        link = in.readString();
        view = in.readInt();
        time = in.readString();
        special = in.readInt();
    }

    public static final Creator<Videos> CREATOR = new Creator<Videos>() {
        @Override
        public Videos createFromParcel(Parcel in) {
            return new Videos(in);
        }

        @Override
        public Videos[] newArray(int size) {
            return new Videos[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
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

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(catId);
        parcel.writeString(title);
        parcel.writeString(icon);
        parcel.writeInt(creator);
        parcel.writeString(description);
        parcel.writeString(link);
        parcel.writeInt(view);
        parcel.writeString(time);
        parcel.writeInt(special);
    }
}
