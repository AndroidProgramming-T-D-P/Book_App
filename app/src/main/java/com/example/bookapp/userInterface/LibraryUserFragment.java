package com.example.bookapp.userInterface;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bookapp.Interface.ItemClickListener;
import com.example.bookapp.R;
import com.example.bookapp.Service.ApiService;
import com.example.bookapp.Service.RetrofitClient;
import com.example.bookapp.adapters.itemBookAdapter;
import com.example.bookapp.models.Photo;
import com.example.bookapp.utils.Utils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryUserFragment extends Fragment implements ItemClickListener {

    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    private List<Photo> list;
    private itemBookAdapter adapter;
    ApiService apiService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int user_id;

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

        SharedPreferences preferences = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        user_id = preferences.getInt("user_id", -1);

        recyclerView = view.findViewById(R.id.recyclerViewLibrary);
        tabLayout = view.findViewById(R.id.tabLayoutLibrary);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        //Danh sach mau
//        list = new ArrayList<>();
//        list.add(new Photo(R.drawable.img_1,"",""));
//        loadContinueReadingBook(list);
////        list.add(new Photo(R.drawable.img_1,""));
////        list.add(new Photo(R.drawable.img_1,""));
//
//        adapter = new itemBookAdapter(getContext(), list);
//        recyclerView.setAdapter(adapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            //@SuppressLint("NotifyDataSetChanged")
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
//                    loadContinueReadingBook(list);
//                    adapter.notifyDataSetChanged();
                    loadfavouriteBook();
                    //adapter.notifyDataSetChanged();
                } else if(position == 1) {
                    //loadfavouriteBook(list);
                    loadfavouriteBook();
                    //adapter.notifyDataSetChanged();
                } else if(position == 2) {
//                    loadDownLoadBook(list);
//                    adapter.notifyDataSetChanged();
                    loadfavouriteBook();
                    //adapter.notifyDataSetChanged();
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

    private void loadfavouriteBook() {
        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);
        compositeDisposable.add(apiService.getSachYeuThich(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        photoModel -> {
                            if(photoModel.isSuccess()){
                                list = photoModel.getResult();
                                adapter = new itemBookAdapter(getContext(), list, this);
                                recyclerView.setAdapter(adapter);
                            }
                        }
                ));
    }
//    @SuppressLint("NotifyDataSetChanged")
//    private void loadContinueReadingBook(List<Photo> list){
//        list.clear();
//
//        list.add(new Photo(R.drawable.img_8, "Sach 1",""));
//        list.add(new Photo(R.drawable.img_7, "Sach 2",""));
//        list.add(new Photo(R.drawable.img_2, "Sach 3",""));
//
//
//    }
//    @SuppressLint("NotifyDataSetChanged")
//    private void loadfavouriteBook(List<Photo> list){
//        list.clear();
//
//        list.add(new Photo(R.drawable.img_6, "Sach 1",""));
//        list.add(new Photo(R.drawable.img_2, "Sach 2",""));
//        list.add(new Photo(R.drawable.img_5, "Sach 3",""));
//        list.add(new Photo(R.drawable.img_1, "Sach 4",""));
//        list.add(new Photo(R.drawable.img_12, "Sach 5",""));
//        list.add(new Photo(R.drawable.img_4, "Sach 6",""));
//        list.add(new Photo(R.drawable.img_11, "Sach 7",""));
//        list.add(new Photo(R.drawable.img_9, "Sach 8",""));
//        list.add(new Photo(R.drawable.img_8, "Sach 9",""));
//        list.add(new Photo(R.drawable.img_7, "Sach 10",""));
//        list.add(new Photo(R.drawable.img_10, "Sach 11",""));
//        list.add(new Photo(R.drawable.img_3, "Sach 12",""));
//    }
//    @SuppressLint("NotifyDataSetChanged")
//    private void loadDownLoadBook(List<Photo> list){
//        list.clear();
//
//        list.add(new Photo(R.drawable.img_3, "Sach 1",""));
//        list.add(new Photo(R.drawable.img_4, "Sach 2",""));
//        list.add(new Photo(R.drawable.img_5, "Sach 3",""));
//
//    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void OnItemClick(Photo photo) {
        ViewBookFragment viewBookFragment = ViewBookFragment.newInstance(photo);
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.view_pager_trangchu, viewBookFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onclick(View view, int pos, boolean isLongClick) {

    }
}