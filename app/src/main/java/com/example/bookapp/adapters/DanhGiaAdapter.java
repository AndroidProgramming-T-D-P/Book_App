package com.example.bookapp.adapters;

import android.content.Context;
import android.media.Rating;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.Interface.ItemClickListener;
import com.example.bookapp.R;
import com.example.bookapp.models.DanhGia;
import com.example.bookapp.models.Photo;
import com.example.bookapp.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DanhGiaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<DanhGia> array;
    Context context;
    Utils utils;

    private ItemClickListener itemClickListener;

    public DanhGiaAdapter(Context context, List<DanhGia> array) {
        this.context = context;
        this.array = array;
    }

    public static class DanhGiaViewHolder extends RecyclerView.ViewHolder{
        TextView userName,moTa,ngayDanhGia;
        ImageView star1, star2, star3, star4, star5;
        LinearLayout RatingLinearLayout;
        private ItemClickListener itemClickListener;

        public DanhGiaViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.tenNguoidung);
            moTa = itemView.findViewById(R.id.moTa);
            ngayDanhGia = itemView.findViewById(R.id.ngayDanhGia);
            star1 = itemView.findViewById(R.id.star01);
            star2 = itemView.findViewById(R.id.star02);
            star3 = itemView.findViewById(R.id.star03);
            star4 = itemView.findViewById(R.id.star04);
            star5 = itemView.findViewById(R.id.star05);
            RatingLinearLayout = itemView.findViewById(R.id.danhgia);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danh_gia, parent, false);
        return new DanhGiaViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DanhGiaViewHolder){
            DanhGiaViewHolder danhGiaViewHolder = (DanhGiaViewHolder) holder;
            DanhGia danhgia = array.get(position);
            if(danhgia != null){
                danhGiaViewHolder.userName.setText(danhgia.getUserName());

                danhGiaViewHolder.moTa.setText(danhgia.getMota());

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String formattedDate = dateFormat.format(danhgia.getNgayDanhGia());
                danhGiaViewHolder.ngayDanhGia.setText(formattedDate);

                int rating = danhgia.getRating();

                int yellowStar = R.drawable.ic_star_filled;
                int emptyStar = R.drawable.ic_star_empty;
                for (int i = 0; i < danhGiaViewHolder.RatingLinearLayout.getChildCount(); i++){
                    ImageView star = (ImageView) danhGiaViewHolder.RatingLinearLayout.getChildAt(i);

                    if(i<rating){
                        star.setImageResource(yellowStar);
                    } else {
                        star.setImageResource(emptyStar);
                    }
                }
            }
        }
    }

//    private void setRating(int rating, LinearLayout linearLayout) {
//        int yellowStar = R.drawable.ic_star_filled;
//        int emptyStar = R.drawable.ic_star_empty;
//        for (int i = 0; i < 5; i++){
//
//        }
//    }

    @Override
    public int getItemCount() {
        return array.size();
    }
}
