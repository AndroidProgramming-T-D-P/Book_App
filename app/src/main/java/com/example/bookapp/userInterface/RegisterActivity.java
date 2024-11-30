package com.example.bookapp.userInterface;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.bookapp.service.ApiService;
import com.example.bookapp.service.RetrofitClient;
import com.example.bookapp.utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    public LinearLayout login_link;
    public ImageView btn_eyes_VisibilityPassWord, btn_eyes_VisibilityConfirmPassWord;
    public EditText EditTextPassWord, EditTextConfirmPassWord, email, username;
    public Button btn_DangKy;
    public ApiService apiService;
CompositeDisposable compositeDisposable = new CompositeDisposable();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        initControl();

    }

    private void initControl() {
        //Chuyen toi trang dang nhap
        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangki();
            }
        });
    }

    private void dangki() {
        String str_email = email.getText().toString().trim();
        String str_pass = EditTextPassWord.getText().toString().trim();
        String str_repass = EditTextConfirmPassWord.getText().toString().trim();
        String str_username = username.getText().toString().trim();

        if(TextUtils.isEmpty(str_username)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(str_email)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(str_pass)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(str_repass)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
        } else {
            if(str_pass.equals(str_repass)) {
                //Post Data
                compositeDisposable.add(apiService.dangki(str_email, str_pass, str_repass)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            userModel -> {
                                if(userModel.isSuccess()) {
                                    Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }, throwable -> {
                                    Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));
            } else {
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initView() {
        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);

        EditTextPassWord = findViewById(R.id.edit_text_password);
        EditTextConfirmPassWord = findViewById(R.id.edit_text_confirmPassword);
        email = findViewById(R.id.email_input);
        username = findViewById(R.id.username_input);
        login_link = findViewById(R.id.login_link);
        btn_DangKy = findViewById(R.id.btn_DangKy);

        //Che mật khẩu lại
        btn_eyes_VisibilityPassWord = findViewById(R.id.password_eye_icon);
        btn_eyes_VisibilityConfirmPassWord = findViewById(R.id.confirmPassword_eye_icon);

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

        btn_eyes_VisibilityConfirmPassWord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        EditTextConfirmPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        EditTextConfirmPassWord.setSelection(EditTextConfirmPassWord.getText().length());
                        btn_eyes_VisibilityConfirmPassWord.setImageResource(R.drawable.ic_visibility);
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        EditTextConfirmPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        EditTextConfirmPassWord.setSelection(EditTextConfirmPassWord.getText().length());
                        btn_eyes_VisibilityConfirmPassWord.setImageResource(R.drawable.ic_visibility_off);
                        return true;
                }
                return false;
            }
        });
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}