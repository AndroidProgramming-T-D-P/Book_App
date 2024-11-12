package com.example.bookapp.userInterface;

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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemBookTheoChuDeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemBookTheoChuDeFragment extends Fragment {

    private String category;
    private static final String ARG_CATEGORY = "category";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ItemBookTheoChuDeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ItemBookTheoChuDeFragment newInstance(String category) {
        ItemBookTheoChuDeFragment fragment = new ItemBookTheoChuDeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_book_theo_chu_de, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewChuDeSach);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

//        List<Photo> photoList = getBooksByCategory(category);
//        itemBookAdapter adapter = new itemBookAdapter(photoList);
//        recyclerView.setAdapter(adapter);

        return view;
    }

//    private List<Photo> getBooksByCategory(String category) {
//        List<Photo> books = new ArrayList<>();
//        List<Photo> allBook = getAllBooks();
//
//        for(Photo photo : allBook) {
//            if(photo.getCategory().equals(category)){
//                books.add(photo);
//            }
//        }
//
//        return books;
//    }

    // Danh sách sách mẫu
//    private List<Photo> getAllBooks() {
//        List<Photo> books = new ArrayList<>();
//        books.add(new Photo(R.drawable.img_12, "Thực vật và nấm", "Thiên nhiên"));
//        books.add(new Photo(R.drawable.img_9, "Tế bào gốc", "Thiên nhiên"));
//        books.add(new Photo(R.drawable.img_7, "Nhà giả kim", "Thiên nhiên"));
//
//        return books;
//    }
}