package com.example.bookapp.userInterface;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookapp.R;
import com.example.bookapp.Service.ApiService;
import com.example.bookapp.Service.RetrofitClient;
import com.example.bookapp.utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileUserFragment extends Fragment {

    private ImageView btnUpDateUserName;
    private TextView txtShowUserName, txtShowEmail, txtShowFirstEmail, txtShowId, txtBtnDoiMatKhau, txtThanhVienNhom;
    private EditText editUserName;
    private Button btnSave, btnCancel;

    private LinearLayout linearLayout, logout, editUserNameLayout;
    int userId;
    ApiService apiService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileUserFragment newInstance(String param1, String param2) {
        ProfileUserFragment fragment = new ProfileUserFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile_user, container, false);
        linearLayout = view.findViewById(R.id.thietLapMucTieu);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ThietLap.class);
                startActivity(intent);
            }
        });

        logout = view.findViewById(R.id.Linear_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);

        //Ánh xạ
        btnUpDateUserName = view.findViewById(R.id.btnUpDateUserName);
        txtShowUserName = view.findViewById(R.id.txtShowUserName);
        txtShowEmail = view.findViewById(R.id.txtShowEmail);
        txtShowFirstEmail = view.findViewById(R.id.txtShowFirtEmail);
        txtShowId = view.findViewById(R.id.txtShowId);
        editUserName = view.findViewById(R.id.etUserName);
        btnSave = view.findViewById(R.id.btnSave);
        btnCancel = view.findViewById(R.id.btnCancel);
        editUserNameLayout = view.findViewById(R.id.editUserNameLayout);
        txtBtnDoiMatKhau =view.findViewById(R.id.gotoInterfaceDoiMatKhau);

        btnUpDateUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editUserNameLayout.setVisibility(View.VISIBLE);
                editUserName.setText(txtShowUserName.getText());

                txtShowUserName.setVisibility(View.GONE);
                btnUpDateUserName.setVisibility(View.GONE);

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtShowUserName.setText(editUserName.getText().toString());
                editUserNameLayout.setVisibility(View.GONE);
                txtShowUserName.setVisibility(View.VISIBLE);
                btnUpDateUserName.setVisibility(View.VISIBLE);

                String newUserName = editUserName.getText().toString().trim();

                compositeDisposable.add(apiService.CapNhatUserName(newUserName, userId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userModel -> {
                                    if(userModel.isSuccess()){
                                        Toast.makeText(getContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getContext(),userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }, throwable -> {
                                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUserNameLayout.setVisibility(View.GONE);
                txtShowUserName.setVisibility(View.VISIBLE);
                btnUpDateUserName.setVisibility(View.VISIBLE);
            }
        });

        txtBtnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoiMatKhauFragment doiMatKhauFragment = new DoiMatKhauFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.view_pager_trangchu, doiMatKhauFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        //Backend
        //lấy id người dùng theo phiên đăng nhập
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("user_id" , -1);

        //đổ thông tin người dùng ra giao diện

        compositeDisposable.add(apiService.LayThongTinuser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                   userModel -> {
                       if(userModel.isSuccess()) {
                           String email = userModel.getResult().get(0).getEmail();
                           txtShowUserName.setText(userModel.getResult().get(0).getUserName());
                           txtShowEmail.setText(email);
                           txtShowId.setText("ID: " + userModel.getResult().get(0).getUser_id());
                           // lấy phần trước của email
                           int dem = email.indexOf('@');
                           String firstEmail = email.substring(0,dem);
                           txtShowFirstEmail.setText('('+firstEmail+')');
                       }
                   }   , throwable -> {
                            Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));

        txtThanhVienNhom = view.findViewById(R.id.thanhviennhom);
        txtThanhVienNhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThanhVienNhomFragment thanhVienNhomFragment = new ThanhVienNhomFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.view_pager_trangchu, thanhVienNhomFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}