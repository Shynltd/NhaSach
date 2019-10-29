package com.example.nhasach.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nhasach.R;
import com.example.nhasach.adapter.SpThemSachAdapter;
import com.example.nhasach.dao.SachDAO;
import com.example.nhasach.dao.TheLoaiDAO;
import com.example.nhasach.model.LoaiSach;
import com.example.nhasach.model.Sach;

import java.util.ArrayList;
import java.util.List;


public class ThemSachActivity extends AppCompatActivity {
    Spinner spinner;
    TheLoaiDAO theLoaiDAO;
    SachDAO sachDAO;
    List<LoaiSach> listTheLoaiSach;
    Intent intent;
    private Button btnThemSach;
    private EditText edMaSach, edTenSach, edTacGia, edNXB, edGia, edSoLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sach);
        init();
        listTheLoaiSach = theLoaiDAO.getAllLoaiSach();
        SpThemSachAdapter SPThemSachAdapter = new SpThemSachAdapter(this, listTheLoaiSach);
        spinner.setAdapter(SPThemSachAdapter);
        btnThemSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isThemSach()) {
                    Toast.makeText(ThemSachActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThemSachActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void finishActivity(View v) {
        finish();
    }

    private boolean isThemSach() {
        LoaiSach loaiSach = (LoaiSach) spinner.getSelectedItem();
        String maSach = edMaSach.getText().toString();
        String tenSach = edTenSach.getText().toString();
        String tacGia = edTacGia.getText().toString();
        String nxb = edNXB.getText().toString();
        double gia = Double.parseDouble(edGia.getText().toString());
        int soLuong = Integer.parseInt(edSoLuong.getText().toString());
        Log.e("BUGG", loaiSach.getMaTheLoai() + "");
        long result = sachDAO.insertSach(new Sach(maSach, loaiSach.getMaTheLoai(), tenSach, tacGia, nxb, gia, soLuong));

        return result > 0;
    }

    private void init() {
        spinner = findViewById(R.id.spTheLoaiSach_themsach);
        theLoaiDAO = new TheLoaiDAO(this);
        listTheLoaiSach = new ArrayList<>();
        btnThemSach = findViewById(R.id.btnSuaSach);
        edMaSach = findViewById(R.id.edMaSach);
        edTenSach = findViewById(R.id.edTenSach);
        edNXB = findViewById(R.id.edNXB);
        edTacGia = findViewById(R.id.edTacGia);
        edGia = findViewById(R.id.edGia);
        edSoLuong = findViewById(R.id.edSoLuong);
        sachDAO = new SachDAO(this);


    }

    public void themTheLoai(View view) {
        startActivity(new Intent(ThemSachActivity.this,ThemTheLoaiActivity.class));
    }
}
