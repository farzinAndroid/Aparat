package com.farzin.aparat.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.farzin.aparat.R;
import com.farzin.aparat.adapter.VideosAdapter;
import com.farzin.aparat.adapter.VideosCategoryAdapter;
import com.farzin.aparat.databinding.ActivityCategoryVideosBinding;
import com.farzin.aparat.models.Categories;
import com.farzin.aparat.models.Videos;
import com.farzin.aparat.webService.ApiCaller;
import com.farzin.aparat.webService.MassageListener;

import java.util.List;

public class CategoryVideosActivity extends AppCompatActivity {

    Bundle bundle;
    ActivityCategoryVideosBinding binding;
    Categories categories;
    ApiCaller apiCaller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startBinding();
        //گرفتن آبجکت از اداپتر کتگوری
        getExtras();
        //نمایش و گرفتن ویدیو های هر دسته بندی
        getCategoryVideos();

        binding.linBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }




    //گرفتن آبجکت از اداپتر کتگوری
    private void getExtras() {
        categories = new Categories();
        bundle = getIntent().getExtras();
      if (bundle != null){

          categories = bundle.getParcelable("category");
          binding.categoryTitle.setText(categories.getTitle());
          binding.desc.setText(categories.getDescription());
      }
    }


    //binding start
    private void startBinding() {
        binding = ActivityCategoryVideosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


    //get Category Videos
    private void getCategoryVideos(){

        binding.progress.setVisibility(View.VISIBLE);
        apiCaller = new ApiCaller();
        apiCaller.getCategoryVideos(categories.getId(), 0, 10, new MassageListener() {
            @Override
            public void onSuccess(Object successMassage) {
                binding.progress.setVisibility(View.GONE);

                binding.rvCategoryVideos.setAdapter(new VideosCategoryAdapter(getApplicationContext(), (List<Videos>) successMassage));
                binding.rvCategoryVideos.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

            }

            @Override
            public void onFailure(String errorMassage) {
                binding.progress.setVisibility(View.GONE);
            }
        });
    }
}