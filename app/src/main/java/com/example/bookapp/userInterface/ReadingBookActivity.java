package com.example.bookapp.userInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;

import com.example.bookapp.R;

public class ReadingBookActivity extends AppCompatActivity {

    private Toolbar headerToolbar;
    private boolean isHeaderVisible = false;
    private NestedScrollView scrollView;
    private GestureDetector gestureDetector;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reading_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        headerToolbar = findViewById(R.id.header_ReadBook);
        scrollView = findViewById(R.id.content_readBook);
        backBtn = findViewById(R.id.btn_back_readBook);

        //Click scroll view de xuat hien header
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
                visibleHeader();
                return true;
            }
        });
        scrollView.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));

        //Thoat
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadingBookActivity.this, TrangChuUser.class);
                startActivity(intent);
            }
        });

    }

    private void visibleHeader() {
        if(isHeaderVisible) {
            headerToolbar.setVisibility(View.GONE);
        } else {
            headerToolbar.setVisibility(View.VISIBLE);
        }
        isHeaderVisible = !isHeaderVisible;
    }
}