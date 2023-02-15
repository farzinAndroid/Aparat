package com.farzin.aparat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.farzin.aparat.R;
import com.farzin.aparat.adapter.CategoryAdapter;
import com.farzin.aparat.databinding.FragmentCategoryBinding;
import com.farzin.aparat.models.Categories;
import com.farzin.aparat.webService.ApiCaller;
import com.farzin.aparat.webService.MassageListener;
import com.farzin.aparat.webService.Service;

import java.util.List;


public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;
    private ApiCaller apiCaller;


    public CategoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(getLayoutInflater(),container,false);


        //get All Categories
        binding.progress.setVisibility(View.VISIBLE);
        showCategories();



















        return binding.getRoot();
    }

    private void showCategories() {
        apiCaller = new ApiCaller();
        apiCaller.getCategories(new MassageListener() {
            @Override
            public void onSuccess(Object successMassage) {
                binding.progress.setVisibility(View.GONE);

                CategoryAdapter adapter = new CategoryAdapter((List<Categories>) successMassage,getContext());
                binding.rvCategory.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
                binding.rvCategory.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(String errorMassage) {
                binding.progress.setVisibility(View.GONE);
            }
        });
    }
}