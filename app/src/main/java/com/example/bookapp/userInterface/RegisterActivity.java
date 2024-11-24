package com.example.bookapp.userInterface;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookapp.R;

public class RegisterActivity extends AppCompatActivity {

    public LinearLayout login_link;
    public ImageView btn_eyes_VisibilityPassWord, btn_eyes_VisibilityConfirmPassWord;
    public EditText EditTextPassWord, EditTextConfirmPassWord;

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

        //Chuyen toi trang dang nhap
        login_link = findViewById(R.id.login_link);
        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        EditTextPassWord = findViewById(R.id.edit_text_password);
        EditTextConfirmPassWord = findViewById(R.id.edit_text_confirmPassword);

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
}