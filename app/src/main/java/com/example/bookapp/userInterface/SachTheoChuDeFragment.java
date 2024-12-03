package com.example.bookapp.userInterface;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookapp.R;
import com.example.bookapp.Service.ApiService;
import com.example.bookapp.Service.RetrofitClient;
import com.example.bookapp.adapters.itemBookAdapter;
import com.example.bookapp.models.LoaiSach;
import com.example.bookapp.models.Photo;
import com.example.bookapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SachTheoChuDeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SachTheoChuDeFragment extends Fragment {

    TextView title_category;
    ImageView backButon;
    RecyclerView recyclerView;
    ApiService apiService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<LoaiSach> listLoaiSach;
    itemBookAdapter listSachTheoChuDeAdapter;
    List<Photo> listSach;
    int category_id = 0;
    String category_name = "";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SachTheoChuDeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SachTheoChuDeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SachTheoChuDeFragment newInstance(String param1, String param2) {
        SachTheoChuDeFragment fragment = new SachTheoChuDeFragment();
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

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sach_theo_chu_de, container, false);

        title_category = view.findViewById(R.id.category_name);
        recyclerView = view.findViewById(R.id.recycleviewSachTheoChuDe);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);

        Bundle args = getArguments();

        if (args != null) {
            category_id = args.getInt("CATEGORY_ID");  // -1 là giá trị mặc định nếu không có CATEGORY_ID
            category_name = args.getString("CATEGORY_NAME");
            Log.d("CategoryId", "Received category ID: " + category_id);
            // Thực hiện các thao tác với categoryId
            compositeDisposable.add(apiService.getlistsachtheochude(category_id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            sachTheoChuDeModel -> {
                                if(sachTheoChuDeModel.isSuccess()){
                                    listSach = sachTheoChuDeModel.getResult();
                                    listSachTheoChuDeAdapter = new itemBookAdapter(getActivity(),listSach);
                                    title_category.setText(category_name);
                                    recyclerView.setAdapter(listSachTheoChuDeAdapter);
                                }
                            }
                    ));
        }

        //back
        backButon = view.findViewById(R.id.back_theLoai);
        backButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheLoaiSachUserFragment theLoaiSachUserFragment = new TheLoaiSachUserFragment();

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.view_pager_trangchu, theLoaiSachUserFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}