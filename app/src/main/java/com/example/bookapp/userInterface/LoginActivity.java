package com.example.bookapp.userInterface;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookapp.R;
import com.example.bookapp.Service.ApiService;
import com.example.bookapp.Service.RetrofitClient;
import com.example.bookapp.utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    public EditText EditTextPassWord, EditTextEmail;
    public ImageView btn_eyes_VisibilityPassWord;
    public LinearLayout register_link;
    public Button btn_dangNhap;
    public ApiService apiService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        initControl();

    }

    private void initView() {
        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);

        EditTextPassWord = findViewById(R.id.edit_text_password);
        EditTextEmail = findViewById(R.id.emailLogin_input);
        btn_eyes_VisibilityPassWord = findViewById(R.id.password_eye_icon);
        register_link = findViewById(R.id.register_link);
        btn_dangNhap = findViewById(R.id.btnDangNhap);
    }

    private void initControl() {

        //ẩn mật khẩu
        btn_eyes_VisibilityPassWord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        EditTextPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        EditTextPassWord.setSelection(EditTextPassWord.getText().length());
                        btn_eyes_VisibilityPassWord.setImageResource(R.drawable.ic_visibility);
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        EditTextPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        EditTextPassWord.setSelection(EditTextPassWord.getText().length());
                        btn_eyes_VisibilityPassWord.setImageResource(R.drawable.ic_visibility_off);
                        return true;
                }
                return false;
            }
        });

        //chuyen toi trang dang ki

        register_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //dang nhap thanh cong
        btn_dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_email = EditTextEmail.getText().toString().trim();
                String str_passWord = EditTextPassWord.getText().toString().trim();

                if(TextUtils.isEmpty(str_email)) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(str_passWord)) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nập mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
                    compositeDisposable.add(apiService.dangnhap(str_email, str_passWord)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                               userModel -> {
                                    if(userModel.isSuccess()){
                                        Toast.makeText(LoginActivity.this, userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                        Utils.user_current = userModel.getResult().get(0);
                                        SharedPreferences preferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putInt("user_id",userModel.getResult().get(0).getUser_id());
                                        editor.apply();
                                        Intent intent = new Intent(getApplicationContext(), TrangChuUser.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LoginActivity.this, userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                               }, throwable -> {
                                        Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                            ));
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.user_current.getEmail() != null && Utils.user_current.getPassWord() != null) {
            EditTextEmail.setText(Utils.user_current.getEmail());
            EditTextPassWord.setText(Utils.user_current.getPassWord());
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}