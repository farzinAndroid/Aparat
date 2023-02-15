package com.farzin.aparat.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_video")
public class VideoVO {

    //ساخت مدل برای دیتابیس رووم برای ذخیره کردن علاقه مندی ها روی دیتابیس

    public VideoVO(int videoId, int cat_id, String title, String icon, int creator, String description, String link, int view, String time, int special) {
        this.videoId = videoId;
        this.cat_id = cat_id;
        this.title = title;
        this.icon = icon;
        this.creator = creator;
        this.description = description;
        this.link = link;
        this.view = view;
        this.time = time;
        this.special = special;
    }

    //یکی از سازنده ها حتما باید ignore شود
    @Ignore
    public VideoVO(int id, int videoId, int cat_id, String title, String icon, int creator, String description, String link, int view, String time, int special) {
        this.id = id;
        this.videoId = videoId;
        this.cat_id = cat_id;
        this.title = title;
        this.icon = icon;
        this.creator = creator;
        this.description = description;
        this.link = link;
        this.view = view;
        this.time = time;
        this.special = special;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "videoId")
    public int videoId;

    @ColumnInfo(name = "cat_id")
    public int cat_id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "icon")
    public String icon;

    @ColumnInfo(name = "creator")
    public int creator;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "link")
    public String link;

    @ColumnInfo(name = "view")
    public int view;

    @ColumnInfo(name = "time")
    public String time;

    @ColumnInfo(name = "special")
    public int special;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
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
}
