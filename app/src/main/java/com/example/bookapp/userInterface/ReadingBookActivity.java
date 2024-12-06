package com.example.bookapp.userInterface;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

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
import com.github.barteksc.pdfviewer.PDFView;
//import com.joanzapata.pdfview.PDFView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReadingBookActivity extends AppCompatActivity {

    private Toolbar headerToolbar;
    private boolean isHeaderVisible = false;
    //private NestedScrollView scrollView;
    //private LinearLayout content;
    private GestureDetector gestureDetector;
    private ImageButton backBtn;
    int id_book = 0;
    String file_pdf;
    ApiService apiService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<Photo> listSach;
    //PDFView pdfView;

    PDFView pdfView;

    @SuppressLint("ClickableViewAccessibility")
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
        //scrollView = findViewById(R.id.content_readBook);
        //content = findViewById(R.id.content_readBook);
        backBtn = findViewById(R.id.btn_back_readBook);
        pdfView = findViewById(R.id.pdfView);

        //headerToolbar.setVisibility(View.GONE);

        id_book = getIntent().getIntExtra("book_id", -1);
        Toast.makeText(this, id_book+"", Toast.LENGTH_SHORT).show();

        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);

        compositeDisposable.add(apiService.getNoiDungSach(id_book)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        photoModel -> {
                            if(photoModel.isSuccess()){
                                listSach = photoModel.getResult();
                                file_pdf = Utils.BASE_URL + listSach.get(0).getFile_path();
                                Log.d("Thông báo", file_pdf+"");
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
        //scrollView.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));
        findViewById(R.id.main).setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));
        //pdfView.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));


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

    private void ShowNoiDung(final String filePdf) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(filePdf).build();
                    Response response = client.newCall(request).execute();

                    if (!response.isSuccessful()) {
                        // Nếu không thành công, hiển thị thông báo lỗi
                        runOnUiThread(() -> Toast.makeText(ReadingBookActivity.this, "Không thể tải sách. Vui lòng thử lại.", Toast.LENGTH_SHORT).show());
                        return;
                    }

                    InputStream inputStream = response.body().byteStream();

                    //hiển thị pdf
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdfView.fromStream(inputStream)
                                    .enableSwipe(true)
                                    .swipeHorizontal(false)
                                    .enableDoubletap(true)
                                    .load();
                        }
                    });
                } catch (IOException e) {
                    // Xử lý khi xảy ra lỗi trong quá trình tải file
                    runOnUiThread(() -> Toast.makeText(ReadingBookActivity.this, "Lỗi khi tải sách.", Toast.LENGTH_SHORT).show());
                    Log.e("Error", "Lỗi tải file PDF: ", e);
                }
            }
        }).start();
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