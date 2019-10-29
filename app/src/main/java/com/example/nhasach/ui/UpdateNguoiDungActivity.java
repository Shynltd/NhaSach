package com.example.nhasach.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhasach.R;
import com.example.nhasach.dao.NguoiDungDAO;
import com.example.nhasach.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class UpdateNguoiDungActivity extends AppCompatActivity {
    EditText edSDT, edHoVaTen;
    AutoCompleteTextView edTenDangNhap;
    Button btnSave;
    NguoiDungDAO nguoiDungDAO;
    List<NguoiDung> nguoiDungList;
    List<String> listTenNguoiDung;
    NguoiDung nguoiDung = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nguoi_dung);
        init();
        Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null) {
            nguoiDung = (NguoiDung) bundle.getSerializable("NguoiDung");
            edTenDangNhap.setText(nguoiDung.getUsername());
            edTenDangNhap.setEnabled(false);
        }
        nguoiDungList = nguoiDungDAO.getAllNguoiDung();
        for (int i = 0; i < nguoiDungList.size(); i++) {
            String tenNguoiDung = nguoiDungList.get(i).getUsername();
            listTenNguoiDung.add(tenNguoiDung);
        }
        edTenDangNhap.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listTenNguoiDung));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edSDT.getText().toString().equals("") || edHoVaTen.getText().toString().equals("")) {
                    Toast.makeText(UpdateNguoiDungActivity.this, "Vui lòng không để trống !", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (nguoiDungDAO.isChangeI(edTenDangNhap.getText().toString(),edSDT.getText().toString(), edHoVaTen.getText().toString()) > 0) {
                        Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void init() {
        edTenDangNhap = findViewById(R.id.edTenDangNhap);
        edSDT = findViewById(R.id.edSuaSDT);
        edHoVaTen = findViewById(R.id.edSuaHoTen);
        btnSave = findViewById(R.id.btnSave);
        nguoiDungDAO = new NguoiDungDAO(UpdateNguoiDungActivity.this);
        nguoiDungList = new ArrayList<>();
        listTenNguoiDung = new ArrayList<>();
    }
    public void finishActivitiy(View view) {
        finish();
    }
}
