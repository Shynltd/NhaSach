package com.example.nhasach.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.nhasach.R;
import com.example.nhasach.dao.HoaDonChiTietDAO;

import java.util.Calendar;

public class TkActivity extends AppCompatActivity {
    TextView tvHomNay, tvThangNay, tvNamNay;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tk);
        tvHomNay = findViewById(R.id.tvHomNay);
        tvThangNay = findViewById(R.id.tvThangNay);
        tvNamNay = findViewById(R.id.tvNamNay);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        double doanhthungay= hoaDonChiTietDAO.getDoanhThuTheoNgay(""+Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        tvHomNay.setText("Hôm nay: "+doanhthungay);
        int month= Calendar.getInstance().get(Calendar.MONTH)+1;
        double doanhthuthang=hoaDonChiTietDAO.getDoanhThuTheoThang(month+"");
        tvThangNay.setText("Tháng này: "+doanhthuthang+"");
        int year=Calendar.getInstance().get(Calendar.YEAR);
        double doanhthunam=hoaDonChiTietDAO.getDoanhThuTheoNam(year+"");
        tvNamNay.setText("Năm nay: "+doanhthunam+"");
    }
}
