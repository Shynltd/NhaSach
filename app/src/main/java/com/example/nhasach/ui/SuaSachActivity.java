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

import java.util.List;

public class SuaSachActivity extends AppCompatActivity {
    Intent intent;
    Spinner spinner;
    TheLoaiDAO theLoaiDAO;
    List<LoaiSach> listTheLoaiSach;
    private Button btnSuaSach;
    SachDAO sachDAO;
    private EditText edMaSach, edTenSach, edTacGia, edNXB, edGia, edSoLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_sach);
        init();
        listTheLoaiSach = theLoaiDAO.getAllLoaiSach();
        SpThemSachAdapter SPThemSachAdapter = new SpThemSachAdapter(this, listTheLoaiSach);
        spinner.setAdapter(SPThemSachAdapter);
        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        Sach sach = (Sach) bundle.getSerializable("Sach");

        if (sach != null) {
            edMaSach.setText(sach.getMasach());
            edTenSach.setText(sach.getTensach());
            edTacGia.setText(sach.getTacgia());
            edNXB.setText(sach.getNxb());
            edGia.setText(sach.getGiabia()+"");
            edSoLuong.setText(sach.getSoluong()+"");
            spinner.setSelection(getIndexMaTheLoai(sach.getMatheloai()));
            edMaSach.setEnabled(false);
        }
        btnSuaSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suaSach();
            }
        });
    }

    private void suaSach() {
        LoaiSach loaiSach = (LoaiSach) spinner.getSelectedItem();
        String maSach = edMaSach.getText().toString();
        String tenSach = edTenSach.getText().toString();
        String tacGia = edTacGia.getText().toString();
        String nxv = edNXB.getText().toString();
        double gia = Double.parseDouble(edGia.getText().toString());
        int soLuong = Integer.parseInt(edSoLuong.getText().toString());
        long result = sachDAO.updateSach(new Sach(maSach, loaiSach.getMaTheLoai(), tenSach, tacGia, nxv, gia, soLuong));
        if(result>0){
            Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "Sửa thất bại", Toast.LENGTH_SHORT).show();

        }
    }

    private int getIndexMaTheLoai(String maTheLoai) {
        for (int i = 0; i < listTheLoaiSach.size(); i++) {
            if (maTheLoai.equalsIgnoreCase(listTheLoaiSach.get(i).getMaTheLoai())) {
                return i;
            }
        }
        return -1;
    }

    private void init() {
        spinner = findViewById(R.id.spTheLoaiSach);
        theLoaiDAO = new TheLoaiDAO(this);
        edMaSach = findViewById(R.id.edMaSach);
        edTenSach = findViewById(R.id.edTenSach);
        edNXB = findViewById(R.id.edNXB);
        edTacGia = findViewById(R.id.edTacGia);
        edGia = findViewById(R.id.edGia);
        edSoLuong = findViewById(R.id.edSoLuong);
        btnSuaSach = findViewById(R.id.btnSuaSach);
        sachDAO=new SachDAO(this);

    }
    public void finishActivity(View view){
        finish();
    }
}
