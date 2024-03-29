package com.example.nhasach.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nhasach.R;
import com.example.nhasach.dao.TheLoaiDAO;
import com.example.nhasach.model.LoaiSach;

public class ThemTheLoaiActivity extends AppCompatActivity {
    EditText edMaTheLoai,edTenTheLoai,edViTri,edMoTa;
    Button btnThem;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_the_loai);
        init();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themTheLoai();
            }
        });
    }

    private void themTheLoai() {
        String maTheLoai=edMaTheLoai.getText().toString();
        String tenTheLoai=edTenTheLoai.getText().toString();
        int viVri=Integer.parseInt(edViTri.getText().toString());
        String moTa=edMoTa.getText().toString();
        long result=theLoaiDAO.insertTheLoai(new LoaiSach(maTheLoai,tenTheLoai,moTa,viVri));
        if(result>0){
            Toast.makeText(this, "Thêm thể loại thành công ", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "Thêm thể loại không thành công ", Toast.LENGTH_SHORT).show();
        }

    }

    private void init() {
        edMaTheLoai=findViewById(R.id.edMaTheLoai);
        edTenTheLoai=findViewById(R.id.edTenTheLoai);
        edViTri=findViewById(R.id.edViTri);
        btnThem=findViewById(R.id.btnSua);
        edMoTa=findViewById(R.id.edMoTa);
        theLoaiDAO=new TheLoaiDAO(this);
    }
    public void finishActivity(View view){
        finish();
    }

}
