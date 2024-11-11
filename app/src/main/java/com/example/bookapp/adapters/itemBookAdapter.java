package com.example.bookapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;
import com.example.bookapp.models.Photo;

import java.util.List;

public class itemBookAdapter extends RecyclerView.Adapter<itemBookAdapter.PhotoViewHolder> {
    private final List<Photo> mListPhoto;
    private Context context;

    public itemBookAdapter(List<Photo> mListPhoto) {
        this.mListPhoto = mListPhoto;
    }

    public itemBookAdapter(Context context, List<Photo> mListPhoto) {
        this.context = context;
        this.mListPhoto = mListPhoto;
    }


    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new itemBookAdapter.PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = mListPhoto.get(position);
        if(photo == null){
            return;
        }
        holder.imgPhoto.setImageResource(photo.getResourceId());
        holder.titleBook.setText(photo.getTitle());
    }

    @Override
    public int getItemCount() {
        if(mListPhoto != null){
            return mListPhoto.size();
        }
        return 0;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgPhoto;
        private final TextView titleBook;

        @SuppressLint("WrongViewCast")
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.bookImage);
            titleBook = itemView.findViewById(R.id.bookTitleItem);
        }
    }
}
