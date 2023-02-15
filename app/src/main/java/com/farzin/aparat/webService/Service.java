package com.farzin.aparat.webService;

import com.farzin.aparat.models.Categories;
import com.farzin.aparat.models.Creator;
import com.farzin.aparat.models.News;
import com.farzin.aparat.models.Videos;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Service {

    //گرفتن جدید ترین ویدیو ها
    @GET("getNewVideos.php")
    Call<List<Videos>> getNewVideos();

    //دریافت ویدیو های مخصوص
    @GET("getSpecial.php")
    Call<List<Videos>> getSpecialVideos();

    //دریافت بهترین ویدیو ها
    @GET("getBestVideos.php")
    Call<List<Videos>> getBestVideos();

    //دریافت دسته بندی ویدیو ها
    @GET("getCategory.php")
    Call<List<Categories>> getAllCategories();

    //دریافت ویدیو های هر دسته بندی
    @FormUrlEncoded
    @POST("getVideosCategory.php")
    Call<List<Videos>> getVideosCategory(@Field("catId") int id, @Field("from") int from, @Field("to") int to);

    //دریافت اخبار
    @GET("getNews.php")
    Call<List<News>> getNews();


    //سرچ
    @GET("search.php")
    Call<List<Videos>> getSearch(@Query("title") String queryTitle);


    //سرویس و رجیستر
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> getRegister(@Field("username") String username, @Field("password") String password);


    //سرویس لاگین
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> login(@Field("username") String username , @Field("password") String password);

    //سرویس ویو
    @GET("increase_view.php")
    Call<ResponseBody> increaseView(@Query("id") int video_id);

    //سرویس سازنده ویدیو
    @GET("getCreator.php")
    Call<Creator> creator(@Query("id") int creatorId);


}
