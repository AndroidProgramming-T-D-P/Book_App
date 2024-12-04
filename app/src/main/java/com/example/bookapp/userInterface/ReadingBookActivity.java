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
import com.example.bookapp.Service.ApiService;
import com.example.bookapp.Service.RetrofitClient;
import com.example.bookapp.models.Photo;
import com.example.bookapp.utils.Utils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ReadingBookActivity extends AppCompatActivity {

    private Toolbar headerToolbar;
    private boolean isHeaderVisible = false;
    private NestedScrollView scrollView;
    private GestureDetector gestureDetector;
    private ImageButton backBtn;
    int id_book = 0;
    String file_pdf;
    ApiService apiService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<Photo> listSach;

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

        id_book = getIntent().getIntExtra("book_id", -1);

        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);

        compositeDisposable.add(apiService.getNoiDungSach(id_book)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        photoModel -> {
                            if(photoModel.isSuccess()){
                                listSach = photoModel.getResult();
                                file_pdf = listSach.get(0).getFile_path();
                                ShowNoiDung(file_pdf);
                            }
                        }
                ));
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
                intent.putExtra("book_id",id_book);
                startActivity(intent);
            }
        });



    }

    private void ShowNoiDung(String filePdf) {
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