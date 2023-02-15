package com.farzin.aparat.ORM;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.farzin.aparat.models.VideoVO;

import java.util.List;

//به ORM نشان میدیم که این یک فایل data object hsj
@Dao
public interface VideoDAO {
    //این اینترفیس برای ساخت تمام فانکشن های دیتابیس است
    //مثل update,delete,insert,selectAll


    @Insert
    public Long insertVideo(VideoVO videos);

    @Delete
    public int deleteFavorites(VideoVO videoVO);

    //لیستی برمیگرداند از همه ی ویدیو ها
    @Query("select * from tbl_video")
    public List<VideoVO> getAllVideos();
}
