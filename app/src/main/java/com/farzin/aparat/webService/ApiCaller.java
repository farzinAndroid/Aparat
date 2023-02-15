package com.farzin.aparat.webService;

import android.util.Log;

import com.farzin.aparat.models.Categories;
import com.farzin.aparat.models.Creator;
import com.farzin.aparat.models.News;
import com.farzin.aparat.models.Videos;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCaller {

    Service service;

    //کانکت کردن اینترفیس Service به ApiClient
    public ApiCaller() {
        service = ApiClient.getClient().create(Service.class);
    }

    //گرفتن جدید ترین ویدیو ها
    public void getNewVideos(MassageListener massageListener){

        Call<List<Videos>> call = service.getNewVideos();

        call.enqueue(new Callback<List<Videos>>() {
            @Override
            public void onResponse(Call<List<Videos>> call, Response<List<Videos>> response) {

                massageListener.onSuccess(response.body());
                Log.i("", "");
            }

            @Override
            public void onFailure(Call<List<Videos>> call, Throwable t) {
                massageListener.onFailure(t.getMessage());
            }
        });
    }

    //دریافت ویدیو های مخصوص
    public void getSpecialVideos(MassageListener massageListener){

        Call<List<Videos>> call = service.getSpecialVideos();

        call.enqueue(new Callback<List<Videos>>() {
            @Override
            public void onResponse(Call<List<Videos>> call, Response<List<Videos>> response) {
                massageListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Videos>> call, Throwable t) {
                massageListener.onFailure(t.getMessage());
            }
        });
    }

    //دریافت بهترین ویدیو ها
    public void getBestVideos(MassageListener massageListener){

        Call<List<Videos>> call = service.getBestVideos();

        call.enqueue(new Callback<List<Videos>>() {
            @Override
            public void onResponse(Call<List<Videos>> call, Response<List<Videos>> response) {
                massageListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Videos>> call, Throwable t) {
                massageListener.onFailure(t.getMessage());
            }
        });
    }


    //دریافت دسته بندی ویدیو ها
    public void getCategories(MassageListener massageListener){

        Call<List<Categories>> call = service.getAllCategories();
        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                massageListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                massageListener.onFailure(t.getMessage());
            }
        });
    }


    //دریافت ویدیو های هر دسته بندی
    public void getCategoryVideos(int id,int from,int to, MassageListener massageListener){

        Call<List<Videos>> call = service.getVideosCategory(id,from,to);
        call.enqueue(new Callback<List<Videos>>() {
            @Override
            public void onResponse(Call<List<Videos>> call, Response<List<Videos>> response) {
                massageListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Videos>> call, Throwable t) {
                massageListener.onFailure(t.getMessage());
            }
        });
    }


    //دریافت اخبار
    public void getNews(MassageListener massageListener){

        Call<List<News>> call = service.getNews();

        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                massageListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                massageListener.onFailure(t.getMessage());
            }
        });
    }



    //دریافت سرچ
    public void getSearch(String title, MassageListener massageListener){

        Call<List<Videos>> call = service.getSearch(title);

        call.enqueue(new Callback<List<Videos>>() {
            @Override
            public void onResponse(Call<List<Videos>> call, Response<List<Videos>> response) {
                massageListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Videos>> call, Throwable t) {
                massageListener.onFailure(t.getMessage());
            }
        });
    }


    //دریافت رجیستر
    public void getUserRegister(String user, String password, MassageListener massageListener){
        Call<ResponseBody> call = service.getRegister(user, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    massageListener.onSuccess(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                massageListener.onFailure(t.getMessage());
            }
        });
    }


    //دریافت لاگین
    public void getUserLogin(String user, String password, MassageListener massageListener){

        Call<ResponseBody> call = service.login(user,password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    massageListener.onSuccess(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                massageListener.onFailure(t.getMessage());
            }
        });
    }

    //دریافت تعداد بازخورد ویدیو
    public void increaseViewNumber(int videoId, MassageListener massageListener){

        Call<ResponseBody> call = service.increaseView(videoId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    massageListener.onSuccess(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                massageListener.onFailure(t.getMessage());
            }
        });
    }

    //دریافت سازنده ویدیو
    public void getCreator(int creatorID, MassageListener massageListener){
        Call<Creator> call = service.creator(creatorID);

        call.enqueue(new Callback<Creator>() {
            @Override
            public void onResponse(Call<Creator> call, Response<Creator> response) {
                massageListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Creator> call, Throwable t) {
                massageListener.onFailure(t.getMessage());
            }
        });
    }
















}
