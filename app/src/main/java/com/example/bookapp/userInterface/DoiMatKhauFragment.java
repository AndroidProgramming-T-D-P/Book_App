package com.example.bookapp.userInterface;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookapp.R;
import com.example.bookapp.Service.ApiService;
import com.example.bookapp.Service.RetrofitClient;
import com.example.bookapp.utils.Utils;
import com.google.android.gms.common.api.Api;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoiMatKhauFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoiMatKhauFragment extends Fragment {
    private ImageButton btnBack;
    private ImageView mat1, mat2, mat3;
    private EditText txtMatKhauMoi, txtMatKhauCu, txtConfirmMatKhauMoi;
    private AppCompatButton btnXacNhan;
    ApiService apiService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int userId;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DoiMatKhauFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoiMatKhauFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoiMatKhauFragment newInstance(String param1, String param2) {
        DoiMatKhauFragment fragment = new DoiMatKhauFragment();
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
        View view = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);


        btnBack = view.findViewById(R.id.backDoiMatKhau);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("user_id" , -1);

        mat1 = view.findViewById(R.id.password_eye_icon1);
        mat2 = view.findViewById(R.id.password_eye_icon2);
        mat3 = view.findViewById(R.id.password_eye_icon3);
        txtMatKhauCu = view.findViewById(R.id.editMatKhauCu);
        txtMatKhauMoi = view.findViewById(R.id.editMatKhauMoi);
        txtConfirmMatKhauMoi = view.findViewById(R.id.editNhapLaiMatKhauMoi);
        btnXacNhan = view.findViewById(R.id.btnCapNhatMatKhau);

        mat1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        txtMatKhauCu.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        txtMatKhauCu.setSelection(txtMatKhauCu.getText().length());
                        mat1.setImageResource(R.drawable.ic_visibility);
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        txtMatKhauCu.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        txtMatKhauCu.setSelection(txtMatKhauCu.getText().length());
                        mat1.setImageResource(R.drawable.ic_visibility_off);
                        return true;
                }
                return false;
            }
        });
        mat2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        txtMatKhauMoi.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        txtMatKhauMoi.setSelection(txtMatKhauMoi.getText().length());
                        mat2.setImageResource(R.drawable.ic_visibility);
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        txtMatKhauMoi.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        txtMatKhauMoi.setSelection(txtMatKhauMoi.getText().length());
                        mat2.setImageResource(R.drawable.ic_visibility_off);
                        return true;
                }
                return false;
            }
        });
        mat3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        txtConfirmMatKhauMoi.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        txtConfirmMatKhauMoi.setSelection(txtConfirmMatKhauMoi.getText().length());
                        mat3.setImageResource(R.drawable.ic_visibility);
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        txtConfirmMatKhauMoi.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        txtConfirmMatKhauMoi.setSelection(txtConfirmMatKhauMoi.getText().length());
                        mat3.setImageResource(R.drawable.ic_visibility_off);
                        return true;
                }
                return false;
            }
        });


        //Backend
        //Đổi mk
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matKhauCu = txtMatKhauCu.getText().toString().trim();
                String matKhauMoi = txtMatKhauMoi.getText().toString().trim();
                String ConfirmMatKhauMoi = txtConfirmMatKhauMoi.getText().toString().trim();
                if(TextUtils.isEmpty(matKhauCu)){
                    Toast.makeText(getContext(), "Bạn chưa nhập mật khẩu cũ", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(matKhauMoi)) {
                    Toast.makeText(getContext(), "Bạn chưa nhập mât khẩu mới", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(ConfirmMatKhauMoi)) {
                    Toast.makeText(getContext(), "Bạn chưa nập lại mật khẩu mới", Toast.LENGTH_SHORT).show();
                } else {
                    if(matKhauMoi.equals(ConfirmMatKhauMoi)){
                        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);
                        compositeDisposable.add(apiService.LayThongTinuser(userId)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        userModel -> {
                                            //Toast.makeText(getContext(), userModel.getResult().get(0).getPassWord(), Toast.LENGTH_SHORT).show();
                                            if(matKhauCu.equals(userModel.getResult().get(0).getUserPassWord())){
                                                compositeDisposable.add(apiService.CapNhatPassWord(matKhauMoi, userId)
                                                        .subscribeOn(Schedulers.io())
                                                        .observeOn(AndroidSchedulers.mainThread())
                                                        .subscribe(
                                                                userModel1 -> {
                                                                    if(userModel.isSuccess()){
                                                                        Toast.makeText(getContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    } else {
                                                                        Toast.makeText(getContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }, throwable -> {
                                                                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                                                }
                                                        ));
                                            } else {
                                                Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                                            }
                                        }, throwable -> {
                                            Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                ));
                    } else {
                        Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}