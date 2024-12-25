package com.example.bookapp.userInterface;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.Interface.ItemClickListener;
import com.example.bookapp.R;
import com.example.bookapp.Service.ApiService;
import com.example.bookapp.Service.RetrofitClient;
import com.example.bookapp.adapters.SachDaDocAdapter;
import com.example.bookapp.adapters.itemBookAdapter;
import com.example.bookapp.models.Photo;
import com.example.bookapp.utils.Utils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThietLap extends AppCompatActivity{

    private ImageButton btnBack;
    private NumberPicker numberPicker;
    private Button btnXong;
    private TextView txtMT;
    private RecyclerView recyclerView;
    private ApiService apiService;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    int user_id;
    private List<Photo> list;
    private SachDaDocAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thiet_lap);



        // Tham chiếu đến NumberPicker trong layout
        numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setWrapSelectorWheel(true);
        // Thiết lập giá trị tối thiểu và tối đa
        numberPicker.setMinValue(0);  // Giá trị tối thiểu
        numberPicker.setMaxValue(60); // Giá trị tối đa (có thể thay đổi theo nhu cầu)



        btnXong = (Button)findViewById(R.id.btnDone);
        txtMT = (TextView)findViewById(R.id.txtMuctieu);
        // Lắng nghe sự kiện nhấn nút "Xong"
        btnXong.setOnClickListener(view -> {
            int selectedValue = numberPicker.getValue(); // Lấy giá trị hiện tại của NumberPicker
            txtMT.setText("Bạn còn " + selectedValue + " phút nữa để đạt mục tiêu"); // Cập nhật TextView
        });

        btnBack = findViewById(R.id.backButtonThietLapMucTieu);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThietLap.this, TrangChuUser.class);
                startActivity(intent);
            }
        });

        //ánh xạ
        recyclerView = findViewById(R.id.recyclerViewSachDaDoc);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);

        recyclerView.setLayoutManager(gridLayoutManager);

        //backend
        SharedPreferences preferences = this.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        user_id = preferences.getInt("user_id", -1);
        //Toast.makeText(this,user_id+"", Toast.LENGTH_SHORT).show();
        //load list sách đã đọc
        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);
        compositeDisposable.add(apiService.getSachDaDoc(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        photoModel -> {
                            list = photoModel.getResult();
                            adapter = new SachDaDocAdapter(this, list);
                            recyclerView.setAdapter(adapter);
                        }
                ));

    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

//    @Override
//    public void OnItemClick(Photo photo) {
//        ViewBookFragment viewBookFragment = ViewBookFragment.newInstance(photo);
//        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.view_pager_trangchu, viewBookFragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
//
//    @Override
//    public void onclick(View view, int pos, boolean isLongClick) {
//
//    }
}