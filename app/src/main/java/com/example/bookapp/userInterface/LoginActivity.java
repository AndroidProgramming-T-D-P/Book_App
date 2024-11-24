package com.example.bookapp.userInterface;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookapp.R;

public class LoginActivity extends AppCompatActivity {

    public EditText EditTextPassWord;
    public ImageView btn_eyes_VisibilityPassWord;
    public LinearLayout register_link;
    public Button btn_dangNhap;

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

        EditTextPassWord = findViewById(R.id.edit_text_password);
        btn_eyes_VisibilityPassWord = findViewById(R.id.password_eye_icon);

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
        register_link = findViewById(R.id.register_link);
        register_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //dang nhap thanh cong
        btn_dangNhap = findViewById(R.id.btnDangNhap);
        btn_dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, TrangChuUser.class);
                startActivity(intent);
            }
        });
    }
}