package com.farzin.aparat.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppConfig {

    //سیو کردن id شخصی که لاگین کرده

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public AppConfig(Context context) {
        sharedPreferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    //ست کردن ایدی
    public void setUserid(int userid){

        editor.putInt("userid",userid);
        editor.commit();
    }


    //گرفتن ایدی
    public int getUserid(){
        return sharedPreferences.getInt("userid",0);
    }

    //سیو کردن بوک مارک
    public void setBookmark(boolean bookmark){
        editor.putBoolean("bookmark",bookmark);
        editor.commit();
    }

    public boolean getBookmark(){
        return sharedPreferences.getBoolean("bookmark",true);
    }

}
