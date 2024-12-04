package com.example.bookapp.userInterface;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bookapp.Interface.ItemClickListener;
import com.example.bookapp.R;
import com.example.bookapp.Service.ApiService;
import com.example.bookapp.Service.RetrofitClient;
import com.example.bookapp.adapters.SearchBookAdapter;
import com.example.bookapp.models.Photo;
import com.example.bookapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements ItemClickListener {

    RecyclerView recyclerView;
    ApiService apiService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<Photo> listSach;
    SearchBookAdapter searchBookAdapter;
    EditText txt_search;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.recyvlerViewSearch);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);

        compositeDisposable.add(apiService.getsachsearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        photoModel -> {
                            if(photoModel.isSuccess()) {
                                listSach = photoModel.getResult();
                                searchBookAdapter = new SearchBookAdapter(getActivity(),listSach,this);
                                recyclerView.setAdapter(searchBookAdapter);
                            }
                        }
                ));

        //Search
        txt_search = view.findViewById(R.id.txtsearch);
        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchBookAdapter.LocSach(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Quay lại
        ImageButton backButton = view.findViewById(R.id.backSearch);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

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