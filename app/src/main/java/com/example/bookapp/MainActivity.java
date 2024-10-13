package com.example.bookapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import UserInterface.DashboardUserActivity;
import UserInterface.UserMainActivity;

public class MainActivity extends AppCompatActivity {


   /// private ActivityMainBinding binding;

    private Button btnlogin;
    private  Button btnskiplogin;


    private Button btntestUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ///binding = ActivityMainBinding.inflate(getLayoutInflater());
        ///setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        btnlogin = (Button) findViewById(R.id.loginBtn);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnskiplogin = (Button) findViewById(R.id.skipBtn);
        btnskiplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DashboardUserActivity.class);
                startActivity(intent);
            }
        });
        //cap nhat



        //Text Giao dien User
        btntestUser = (Button) findViewById(R.id.button2);
        btntestUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserMainActivity.class);
                startActivity(intent);
            }
        });
    }
}