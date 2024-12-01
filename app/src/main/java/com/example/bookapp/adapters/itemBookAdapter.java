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

public class itemBookAdapter extends RecyclerView.Adapter<itemBookAdapter.PhotoViewHolder> {
    private final List<Photo> array;
    private final Context context;
    Utils utils;
    // Constructor
    public itemBookAdapter(Context context, List<Photo> array) {
        this.context = context;
        this.array = array;
    }

    public itemBookAdapter(List<Photo> array, Context context) {
        this.array = array;
        this.context = context;
    }



    // ViewHolder class
    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imgHinhanh;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bookTitleItem);
            imgHinhanh = itemView.findViewById(R.id.bookImage);
        }
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new PhotoViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = array.get(position);
        if (photo != null) {
            // Set title
            holder.title.setText(photo.getTitle());
            Log.d("thông báo",photo.getCover_image());

            // Load image using Glide
            Glide.with(context)
                    .load(utils.BASE_URL + photo.getCover_image())  // Sử dụng phương thức đúng
                    .transition(DrawableTransitionOptions.withCrossFade()) // Thêm hiệu ứng chuyển mượt mà
                    .error(R.drawable.ic_book) // Hình mặc định nếu lỗi
                    .into(holder.imgHinhanh);
        }
    }

    @Override
    public int getItemCount() {
        return array != null ? array.size() : 0;
    }
}
