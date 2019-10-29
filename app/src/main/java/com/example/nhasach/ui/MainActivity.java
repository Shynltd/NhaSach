package com.example.nhasach.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nhasach.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void thongKe(View view) {
        startActivity(new Intent(MainActivity.this,TkActivity.class));
    }

    public void sachBanChay(View view) {
        startActivity(new Intent(MainActivity.this,TopSachBanChayActivity.class));
    }

    public void hoaDon(View view) {
        startActivity(new Intent(MainActivity.this,ThemHoaDonActivity.class));
    }

    public void sach(View view) {
        startActivity(new Intent(MainActivity.this,QLySachActivity.class));
    }

    public void theLoai(View view) {
        startActivity(new Intent(MainActivity.this,QLyLoaiSachActivity.class));
    }

    public void nguoiDung(View view) {
        startActivity(new Intent(MainActivity.this,NguoiDungActivity.class));
    }
}
