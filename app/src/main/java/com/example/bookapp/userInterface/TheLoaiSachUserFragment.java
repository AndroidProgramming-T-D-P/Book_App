package com.example.bookapp.userInterface;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bookapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TheLoaiSachUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TheLoaiSachUserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TheLoaiSachUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TheLoaiSachUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TheLoaiSachUserFragment newInstance(String param1, String param2) {
        TheLoaiSachUserFragment fragment = new TheLoaiSachUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_the_loai_sach_user, container, false);

        ListView listView = view.findViewById(R.id.list_view_the_loai);
        if (listView == null) {
            Log.e("TheLoaiSachUserFragment", "ListView is null!");
        }

        List<String> items = new ArrayList<>();
        items.add("Thiên nhiên");
        items.add("Khoa học viễn tưởng");
        items.add("Doanh nhân");
        items.add("Kinh tế - tài chính");
        items.add("Trinh thám - kinh dị");
        items.add("Tài chính cá nhân");
        items.add("Tâm lý - giới tính");
        items.add("Sức khỏe");
        items.add("Làm đẹp");
        items.add("Phát triển cá nhân");
        items.add("tư duy sáng tạo");
        items.add("Nghệ thuật");
        items.add("Khoa học viễn tưởng");
        items.add("Khoa học viễn tưởng");
        items.add("Khoa học viễn tưởng");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, items);
        listView.setAdapter(adapter);


        //Quay lại
        // Thê Loại sách
        ImageButton backButton = view.findViewById(R.id.icon_quay_lai);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToTrangChu();
            }
        });

        //Search Sách
        ImageButton searchButton = view.findViewById(R.id.icon_tim_kiem_in_The_Loai);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearchFragment();
            }
        });
        return view;
    }

    //Quay về trang chủ
    private void moveToTrangChu(){
        HomeUserFragment homeUserFragment = new HomeUserFragment();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_pager_trangchu, homeUserFragment)
                .addToBackStack(null)
                .commit();
    }

    //Search Sách
    private void showSearchFragment(){
        SearchFragment searchFragment = new SearchFragment();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_pager_trangchu, searchFragment)
                .addToBackStack(null)
                .commit();
    }
}