package com.example.bookapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookapp.R;
import com.example.bookapp.models.LoaiSach;

import java.util.List;

public class LoaiSachAdapter extends BaseAdapter {
    List<LoaiSach> array;
    Context context;

    public LoaiSachAdapter(Context context,List<LoaiSach> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder{
        TextView textTenLoai;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_loaisach, null);
            viewHolder.textTenLoai = view.findViewById(R.id.item_ten_Loai_Sach);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textTenLoai.setText(array.get(i).getCategory_name());

        return view;
    }
}
