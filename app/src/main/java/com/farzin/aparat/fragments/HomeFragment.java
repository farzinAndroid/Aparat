package com.farzin.aparat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.farzin.aparat.adapter.NewsAdapter;
import com.farzin.aparat.adapter.VideosAdapter;
import com.farzin.aparat.databinding.FragmentHomeBinding;
import com.farzin.aparat.models.News;
import com.farzin.aparat.models.Videos;
import com.farzin.aparat.webService.ApiCaller;
import com.farzin.aparat.webService.MassageListener;

import java.util.List;


public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }


    private ApiCaller apiCaller;
    private FragmentHomeBinding binding;
    private NewsAdapter newsAdapter;
    private int page = 0;
    private Handler handler;
    Runnable runnable;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater,container,false);



        handler = new Handler();
        apiCaller = new ApiCaller();


        binding.progress.setVisibility(View.VISIBLE);
        //get New Videos
        getNewVideos();
        //

        binding.progress.setVisibility(View.VISIBLE);
        // get special Videos
        getSpecialVideos();
        //

        binding.progress.setVisibility(View.VISIBLE);
        // get best Videos
        getBestVideos();
        //

        //get News
        getNews();
        //
        binding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

























        return binding.getRoot();
    }



    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable,1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    private void getBestVideos() {
        apiCaller.getBestVideos(new MassageListener() {
            @Override
            public void onSuccess(Object successMassage) {
                binding.progress3.setVisibility(View.GONE);

                VideosAdapter videosAdapter = new VideosAdapter(getContext(), (List<Videos>) successMassage);
                binding.rvBestVideos.setAdapter(videosAdapter);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                binding.rvBestVideos.setLayoutManager(gridLayoutManager);
            }

            @Override
            public void onFailure(String errorMassage) {
                binding.progress3.setVisibility(View.GONE);
            }
        });
    }

    private void getSpecialVideos() {
        apiCaller.getSpecialVideos(new MassageListener() {
            @Override
            public void onSuccess(Object successMassage) {
                binding.progress2.setVisibility(View.GONE);
                Log.e("", "" );

                VideosAdapter videosAdapter = new VideosAdapter(getContext(), (List<Videos>) successMassage);
                binding.rvSpecialVideos.setAdapter(videosAdapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                binding.rvSpecialVideos.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(String errorMassage) {
                binding.progress2.setVisibility(View.GONE);
                Log.e("", "" );
            }
        });
    }

    private void getNewVideos() {
        apiCaller.getNewVideos(new MassageListener() {
            @Override
            public void onSuccess(Object successMassage) {
                Log.e("", "" );

                binding.progress.setVisibility(View.GONE);

                VideosAdapter videosAdapter = new VideosAdapter(getContext(), (List<Videos>) successMassage);
                binding.rvNewVideos.setAdapter(videosAdapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                binding.rvNewVideos.setLayoutManager(linearLayoutManager);

            }

            @Override
            public void onFailure(String errorMassage) {
                Log.e("", "" );
                binding.progress.setVisibility(View.GONE);
            }
        });
    }

    private void getNews() {
        apiCaller.getNews(new MassageListener() {
            @Override
            public void onSuccess(Object successMassage) {
                Log.e("", "");

                newsAdapter = new NewsAdapter(getActivity(), (List<News>) successMassage);
                binding.pager.setAdapter(newsAdapter);
                binding.dotsIndicator.setViewPager(binding.pager);

//                newsAdapterVP2 = new NewsAdapterVP2(getActivity(), (List<News>) successMassage);
//                binding.pager.setAdapter(newsAdapterVP2);
//                binding.pager.setClipToPadding(false);
//                binding.pager.setClipChildren(false);
//                binding.pager.setOffscreenPageLimit(7);
//                binding.pager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);


                //رد شدن صفحه بعد از چند ثانبه
                runnable = new Runnable() {
                    @Override
                    public void run() {

                        if (newsAdapter.getCount() == page){
                            page = 0;
                        }else {
                            page ++;
                        }

                        binding.pager.setCurrentItem(page,true);
                        handler.postDelayed(this,2000);

                    }
                };
                ////

            }

            @Override
            public void onFailure(String errorMassage) {
                Log.e("", "");
            }
        });
    }
}