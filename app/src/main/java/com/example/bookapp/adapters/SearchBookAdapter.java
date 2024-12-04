package com.example.bookapp.adapters;

import android.annotation.SuppressLint;
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
import com.example.bookapp.Interface.ItemClickListener;
import com.example.bookapp.R;
import com.example.bookapp.models.Photo;
import com.example.bookapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SearchBookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Photo> array;
    List<Photo> All_Array;
    Context context;
    Utils utils;

    private ItemClickListener itemClickListener;
    // Constructor
    public SearchBookAdapter(Context context, List<Photo> array) {
        this.context = context;
        this.array = array;
    }

    public SearchBookAdapter(Context context, List<Photo> array, ItemClickListener itemClickListener) {
        this.context = context;
        this.array = array;
        this.itemClickListener = itemClickListener;
        this.All_Array = new ArrayList<>(array);
    }

    public SearchBookAdapter(List<Photo> array, Context context) {
        this.array = array;
        this.context = context;
    }

    //Lọc sách khi có thay đổi từ editText
    @SuppressLint("NotifyDataSetChanged")
    public void LocSach(String query) {
        array.clear();
        if(query.isEmpty()) {
            array.addAll(All_Array);
        } else {
            for (Photo photo : All_Array) {
                if(photo.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    array.add(photo);
                }
            }
        }
        notifyDataSetChanged(); //Cập nhật recyclerView Sau khi cập nhật
    }

    // ViewHolder class
    public static class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView author;
        ImageView imgHinhanh;
        private ItemClickListener itemClickListener;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bookTitle_search);
            imgHinhanh = itemView.findViewById(R.id.bookImage_search);
            author = itemView.findViewById(R.id.tentacgia);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onclick(view, getAdapterPosition(), false);
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_book, parent, false);
        return new PhotoViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PhotoViewHolder) {
            PhotoViewHolder myViewHolder = (PhotoViewHolder) holder;
            Photo photo = array.get(position);
            if (photo != null) {
                // Set title
                myViewHolder.title.setText(photo.getTitle());
                myViewHolder.author.setText(photo.getAuthor());
                Log.d("thông báo", photo.getCover_image());

                // Load image using Glide
                Glide.with(context)
                        .load(utils.BASE_URL + photo.getCover_image())  // Sử dụng phương thức đúng
                        .transition(DrawableTransitionOptions.withCrossFade()) // Thêm hiệu ứng chuyển mượt mà
                        .error(R.drawable.ic_book) // Hình mặc định nếu lỗi
                        .into(myViewHolder.imgHinhanh);

                myViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void OnItemClick(Photo photo) {

                    }

                    @Override
                    public void onclick(View view, int pos, boolean isLongClick) {
                        if(!isLongClick) {
                            itemClickListener.OnItemClick(photo);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return array.size();
    }
}