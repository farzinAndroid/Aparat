package com.farzin.aparat.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.farzin.aparat.R;
import com.farzin.aparat.adapter.VideosAdapter;
import com.farzin.aparat.databinding.ActivitySearchBinding;
import com.farzin.aparat.models.Videos;
import com.farzin.aparat.webService.ApiCaller;
import com.farzin.aparat.webService.MassageListener;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;
    private Bundle bundle;
    private String search = "";
    private ApiCaller apiCaller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startBinding();
        getExtras();
        binding.includedLayout.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //سرچ
        binding.searchProgress.setVisibility(View.VISIBLE);
        getSearchService();
    }

    //گرفتن کوئری سرچ از اکتیویتی اصلی
    //و ست کردن آن بر روی وب سرویس
    private void getSearchService() {
        apiCaller = new ApiCaller();
        apiCaller.getSearch(search, new MassageListener() {
            @Override
            public void onSuccess(Object successMassage) {
                Log.e("","");

                binding.searchProgress.setVisibility(View.GONE);
                binding.rvSearch.setAdapter(new VideosAdapter(getApplicationContext(), (List<Videos>) successMassage));
                binding.rvSearch.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

            }

            @Override
            public void onFailure(String errorMassage) {
                Log.e("","");
            }
        });
    }

    //دریافت کوئری سرچ از اکتیویتی اصلی
    private void getExtras() {
        bundle = getIntent().getExtras();

        if (bundle != null){
            search = bundle.getString("search");
            binding.includedLayout.txtTitle.setText(search);
        }
    }


    //start binding
    private void startBinding() {
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}