package com.farzin.aparat.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.farzin.aparat.R;
import com.farzin.aparat.databinding.NewsLayoutBinding;
import com.farzin.aparat.models.News;

import java.util.List;

public class NewsAdapter extends PagerAdapter {

    private Context context;
    private List<News> newsList;
    private LayoutInflater inflater;

    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = inflater.inflate(R.layout.news_layout,null);

        AppCompatImageView img_news = view.findViewById(R.id.img_news);

        Glide.with(context)
                .asBitmap()
                .load(newsList.get(position).getIcon())
                .placeholder(R.drawable.placeholder)
                .into(img_news);


        container.addView(view,0);

        return view;
    }

    @Nullable
    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
