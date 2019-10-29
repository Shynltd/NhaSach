package com.example.nhasach.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nhasach.R;
import com.example.nhasach.dao.TheLoaiDAO;
import com.example.nhasach.model.LoaiSach;

public class SuaTheLoaiActivity extends AppCompatActivity {
    Intent intent;
    Bundle bundle;
    EditText edMaTheLoai,edTenTheLoai,edViTri,edMoTa;
    Button btnHuy,btnSua,btnShow;
    TheLoaiDAO theLoaiDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_the_loai);
        init();
        intent=getIntent();
        bundle=intent.getBundleExtra("Bundle");
        if(bundle!=null){
            LoaiSach loaiSach= (LoaiSach) bundle.getSerializable("LoaiSach");
            edMaTheLoai.setText(loaiSach.getMaTheLoai());
            edMoTa.setText(loaiSach.getMoTa());
            edTenTheLoai.setText(loaiSach.getTenTheLoai());
            edViTri.setText(loaiSach.getVitri()+"");
            edMaTheLoai.setEnabled(false);
        }
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maTheLoai=edMaTheLoai.getText().toString();
                String moTa=edMoTa.getText().toString();
                String tenTheLoai=edTenTheLoai.getText().toString();
                int viTri= Integer.parseInt(edViTri.getText().toString());
                LoaiSach loaiSach=new LoaiSach(maTheLoai,tenTheLoai,moTa,viTri);
                long result= theLoaiDAO.updateTheLoai(loaiSach);
                if(result>0){
                    Toast.makeText(SuaTheLoaiActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(SuaTheLoaiActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SuaTheLoaiActivity.this, "Bạn đã hủy thay đổi thông tin thể loại", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SuaTheLoaiActivity.this, "Danh sách thể loại", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void init() {
        edMaTheLoai=findViewById(R.id.edMaTheLoai);
        edTenTheLoai=findViewById(R.id.edTenTheLoai);
        edViTri=findViewById(R.id.edViTri);
        btnShow=findViewById(R.id.btnShow);
        btnHuy =findViewById(R.id.btnHuy);
        edMoTa=findViewById(R.id.edMoTa);
        theLoaiDAO=new TheLoaiDAO(this);
        btnSua=findViewById(R.id.btnSua);
    }
}
