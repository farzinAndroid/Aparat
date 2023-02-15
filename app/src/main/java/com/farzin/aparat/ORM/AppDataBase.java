package com.farzin.aparat.ORM;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.farzin.aparat.models.VideoVO;


//entities = جداول دیتابیس
@Database(entities = {VideoVO.class} , version = 1)
//کلاس را abstract میکنیم تا به override کردن متد ها نیازی نباشد
public abstract class AppDataBase extends RoomDatabase {

    public static String DB_NAME = "video";

    //شیء از کلاس videoDAO
    public abstract VideoDAO videoDAO();

    private static AppDataBase instance = null;

    //دیتابیس را مقدار دهی میکنیم
    public static synchronized AppDataBase getInstance(Context context){

        if (instance == null){

            instance = Room.databaseBuilder(context,AppDataBase.class,DB_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }


}
