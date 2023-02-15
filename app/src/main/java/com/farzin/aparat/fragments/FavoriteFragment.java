package com.farzin.aparat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.farzin.aparat.ORM.AppDataBase;
import com.farzin.aparat.R;
import com.farzin.aparat.adapter.VideosAdapter;
import com.farzin.aparat.databinding.FragmentCategoryBinding;
import com.farzin.aparat.databinding.FragmentFavoriteBinding;
import com.farzin.aparat.models.VideoVO;
import com.farzin.aparat.models.Videos;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    private AppDataBase appDataBase;

    public FavoriteFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(getLayoutInflater(),container,false);


        appDataBase = AppDataBase.getInstance(getContext());

        List<VideoVO> videoVOList = appDataBase.videoDAO().getAllVideos();
        List<Videos> videosList = new ArrayList<>();


        //
        //دیتا را از videoVOList به به آرایه دوم videosList ادد میکنیم
        for (VideoVO v : videoVOList){

            Videos videos = new Videos();
            videos.setId(v.getId());
            videos.setCatId(v.getCat_id());
            videos.setTitle(v.getTitle());
            videos.setDescription(v.getDescription());
            videos.setCreator(v.getCreator());
            videos.setIcon(v.getIcon());
            videos.setSpecial(v.getSpecial());
            videos.setView(v.getView());
            videos.setTime(v.getTime());
            videos.setLink(v.getLink());

            videosList.add(videos);
        }

        binding.rvFav.setAdapter(new VideosAdapter(getContext(),videosList));
        binding.rvFav.setLayoutManager(new GridLayoutManager(getContext(),2));

























        return binding.getRoot();
    }
}