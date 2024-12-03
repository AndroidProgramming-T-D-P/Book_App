package com.example.bookapp.Interface;

import android.view.View;

import com.example.bookapp.models.Photo;

public interface ItemClickListener {
    void OnItemClick(Photo photo);

    void onclick(View view, int pos, boolean isLongClick);
}
