package com.example.bookapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookapp.databinding.ActivityPdfDetailBinding;

public class PdfDetailActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    //view binding
    private ActivityPdfDetailBinding binding;

    //pdf id, get from intent
    String bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //handle click, open to view pdf
        binding.readBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PdfDetailActivity.this, PdfViewActivity.class);
                intent1.putExtra("bookId",bookId);
                startActivity(intent1);
            }
        });

        //handle click, back to list admin pdf
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PdfDetailActivity.this, PdfListAdminActivity.class);
                startActivity(intent1);
            }
        });

        //handle click, download
        binding.downloadBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDownloadOptionDialog();
            }
        });
    }

    private void showDownloadOptionDialog() {
        // Tạo ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Downloading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Kiểu thanh xoay vòng tròn
        progressDialog.setCancelable(false); // Không cho phép người dùng đóng dialog bằng cách nhấn ra ngoài
        progressDialog.show();

        // Giả lập quá trình tải (5 giây)
        new Handler().postDelayed(() -> {
            // Khi tải xong, thay đổi nội dung hộp thoại
            progressDialog.setTitle("Notification");
            progressDialog.setMessage("Download success");

            // Giữ thông báo hiển thị trong 2 giây rồi đóng hộp thoại
            new Handler().postDelayed(() -> progressDialog.dismiss(), 2000);
        }, 5000);
    }
}