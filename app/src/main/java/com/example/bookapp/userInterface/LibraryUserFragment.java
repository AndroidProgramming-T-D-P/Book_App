package com.example.bookapp.userInterface;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookapp.R;
import com.example.bookapp.adapters.itemBookAdapter;
import com.example.bookapp.models.Photo;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryUserFragment extends Fragment {

    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    private List<Photo> list;
    private itemBookAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LibraryUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LibraryUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LibraryUserFragment newInstance(String param1, String param2) {
        LibraryUserFragment fragment = new LibraryUserFragment();
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
        View view = inflater.inflate(R.layout.fragment_library_user, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewLibrary);
        tabLayout = view.findViewById(R.id.tabLayoutLibrary);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        //Danh sach mau
        list = new ArrayList<>();
        list.add(new Photo(R.drawable.img_1,"",""));
        loadContinueReadingBook(list);
//        list.add(new Photo(R.drawable.img_1,""));
//        list.add(new Photo(R.drawable.img_1,""));

        adapter = new itemBookAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    loadContinueReadingBook(list);
                    adapter.notifyDataSetChanged();
                } else if(position == 1) {
                    loadfavouriteBook(list);
                    adapter.notifyDataSetChanged();
                } else if(position == 2) {
                    loadDownLoadBook(list);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
    @SuppressLint("NotifyDataSetChanged")
    private void loadContinueReadingBook(List<Photo> list){
        list.clear();

        list.add(new Photo(R.drawable.img_8, "Sach 1",""));
        list.add(new Photo(R.drawable.img_7, "Sach 2",""));
        list.add(new Photo(R.drawable.img_2, "Sach 3",""));


    }
    @SuppressLint("NotifyDataSetChanged")
    private void loadfavouriteBook(List<Photo> list){
        list.clear();

        list.add(new Photo(R.drawable.img_6, "Sach 1",""));
        list.add(new Photo(R.drawable.img_2, "Sach 2",""));
        list.add(new Photo(R.drawable.img_5, "Sach 3",""));
        list.add(new Photo(R.drawable.img_1, "Sach 4",""));
        list.add(new Photo(R.drawable.img_12, "Sach 5",""));
        list.add(new Photo(R.drawable.img_4, "Sach 6",""));
        list.add(new Photo(R.drawable.img_11, "Sach 7",""));
        list.add(new Photo(R.drawable.img_9, "Sach 8",""));
        list.add(new Photo(R.drawable.img_8, "Sach 9",""));
        list.add(new Photo(R.drawable.img_7, "Sach 10",""));
        list.add(new Photo(R.drawable.img_10, "Sach 11",""));
        list.add(new Photo(R.drawable.img_3, "Sach 12",""));
    }
    @SuppressLint("NotifyDataSetChanged")
    private void loadDownLoadBook(List<Photo> list){
        list.clear();

        list.add(new Photo(R.drawable.img_3, "Sach 1",""));
        list.add(new Photo(R.drawable.img_4, "Sach 2",""));
        list.add(new Photo(R.drawable.img_5, "Sach 3",""));

    }
}