package com.example.bookapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.bookapp.R;
import com.example.bookapp.models.Photo;
import com.example.bookapp.utils.Utils;

import java.util.List;

public class SachDaDocAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Photo> array;
    Context context;
    Utils utils;

    public SachDaDocAdapter(Context context, List<Photo> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new SachDaDocAdapter.viewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof viewHolder) {
            viewHolder myViewHolder = (viewHolder) holder;
            Photo photo = array.get(position);
            if (photo != null) {
                // Set title
                myViewHolder.title.setText(photo.getTitle());
                Log.d("thông báo", photo.getCover_image());

                // Load image using Glide
                Glide.with(context)
                        .load(utils.BASE_URL + photo.getCover_image())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_book)
                        .into(myViewHolder.imgHinhanh);
            }
        }
    }


    @Override
    public int getItemCount() {
        return array.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imgHinhanh;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.bookTitleItem);
            imgHinhanh = itemView.findViewById(R.id.bookImage);
        }
    }

}
