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
import com.farzin.aparat.activities.PlayActivity;
import com.farzin.aparat.databinding.VideoRowBinding;
import com.farzin.aparat.models.Videos;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.MyViewHolder>{

    private Context c;
    private List<Videos> videosList;

    public VideosAdapter(Context c, List<Videos> videosList) {
        this.c = c;
        this.videosList = videosList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(c);
        VideoRowBinding videoRowBinding = VideoRowBinding.inflate(inflater,parent,false);
        return new MyViewHolder(videoRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.binding.videoTitle.setText(videosList.get(position).getTitle());

        Glide.with(c)
                .asBitmap()
                .load(videosList.get(position).getIcon())
                .placeholder(R.drawable.placeholder)
                .into(holder.binding.videoImage);


        holder.binding.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, PlayActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("video",videosList.get(holder.getAbsoluteAdapterPosition()));
                c.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }









    //
   protected class MyViewHolder extends RecyclerView.ViewHolder{

        private VideoRowBinding binding;

        public MyViewHolder(VideoRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
