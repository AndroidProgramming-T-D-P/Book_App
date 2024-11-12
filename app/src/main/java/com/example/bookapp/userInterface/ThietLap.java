package com.example.bookapp.userInterface;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookapp.R;

public class ThietLap extends AppCompatActivity {

    private ImageButton btnBack;
    private NumberPicker numberPicker;
    private Button btnXong;
    private TextView txtMT;

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

    }
}