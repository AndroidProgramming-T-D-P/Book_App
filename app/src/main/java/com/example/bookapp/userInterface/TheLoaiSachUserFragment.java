package com.example.bookapp.userInterface;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.bookapp.R;
import com.example.bookapp.Service.ApiService;
import com.example.bookapp.Service.RetrofitClient;
import com.example.bookapp.adapters.LoaiSachAdapter;
import com.example.bookapp.models.LoaiSach;
import com.example.bookapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TheLoaiSachUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TheLoaiSachUserFragment extends Fragment {

    LoaiSachAdapter loaiSachAdapter;
    List<LoaiSach> listLoaiSach;
    ListView listView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiService apiService;
    String category_name;
            int category_id;

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

        //Khởi tạo list
        listLoaiSach = new ArrayList<>();

        listView = view.findViewById(R.id.list_view_the_loai);

        //Call
        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);
        getLoaiSach();

        //click item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Lấy item tại vị trí position

                category_name = listLoaiSach.get(position).getCategory_name().trim();

                Log.d("chao cac ban", category_name);
                compositeDisposable.add(apiService.getidsach(category_name)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                loaiSachModel -> {
                                    if(loaiSachModel.isSuccess()){
                                        LoaiSach loaiSach = loaiSachModel.getResult().get(0);
                                        category_id = loaiSach.getCategory_id();
                                        // Tạo Fragment và truyền dữ liệu qua Bundle
                                        SachTheoChuDeFragment sachTheoChuDeFragment = new SachTheoChuDeFragment();
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("CATEGORY_ID", category_id);
                                        bundle.putString("CATEGORY_NAME", category_name);
                                        // Truyền category_id vào bundle
                                        sachTheoChuDeFragment.setArguments(bundle);

                                        requireActivity().getSupportFragmentManager().beginTransaction()
                                                .replace(R.id.view_pager_trangchu, sachTheoChuDeFragment)
                                                .addToBackStack(null)
                                                .commit();
                                        Log.d("OkOK", category_id+"");
                                    } else {
                                        Log.d("DEBUG", "API Failed: " + loaiSachModel.getMessage());
                                    }
                                }, throwable -> {
                                    Log.e("DEBUG", "Error: " + throwable.getMessage());
                                }
                        ));
            }
        });

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

    private void getLoaiSach() {
        compositeDisposable.add(apiService.getloaisach()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSachModel -> {
                            if(loaiSachModel.isSuccess()) {
                                listLoaiSach = loaiSachModel.getResult();
                                //khởi tạo adapter
                                loaiSachAdapter = new LoaiSachAdapter(requireContext(), listLoaiSach);
                                listView.setAdapter(loaiSachAdapter);

                                Log.d("thongbao",listLoaiSach.get(0).getCategory_name());
                            }
                        }
                ));
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

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}