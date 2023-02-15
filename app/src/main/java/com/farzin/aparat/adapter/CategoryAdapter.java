package com.farzin.aparat.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.farzin.aparat.R;
import com.farzin.aparat.activities.CategoryVideosActivity;
import com.farzin.aparat.databinding.CategoryRowBinding;
import com.farzin.aparat.models.Categories;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private List<Categories> categoriesList;
    private Context c;

    public CategoryAdapter(List<Categories> categoriesList, Context c) {
        this.categoriesList = categoriesList;
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        CategoryRowBinding categoryRowBinding = CategoryRowBinding.inflate(layoutInflater,parent,false);
        return new MyViewHolder(categoryRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.binding.txtCategory.setText(categoriesList.get(position).getTitle());

        Glide.with(c)
                .asBitmap()
                .load(categoriesList.get(position).getIcon())
                .placeholder(R.drawable.placeholder)
                .into(holder.binding.imgCategory);


        holder.binding.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c,CategoryVideosActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("category",categoriesList.get(holder.getAbsoluteAdapterPosition()));
                c.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder{

        CategoryRowBinding binding;


        public MyViewHolder(CategoryRowBinding categoryRowBinding) {
            super(categoryRowBinding.getRoot());
            this.binding = categoryRowBinding;
        }
    }
}
