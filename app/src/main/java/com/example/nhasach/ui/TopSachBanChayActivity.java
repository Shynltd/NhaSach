package com.example.nhasach.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.nhasach.R;
import com.example.nhasach.adapter.LvQLySachAdapter;
import com.example.nhasach.dao.SachDAO;
import com.example.nhasach.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class TopSachBanChayActivity extends AppCompatActivity {
    LvQLySachAdapter lvQLySachAdapter;
    SachDAO sachDAO;
    List<Sach> listSach;
    EditText edSachBanChay;
    Button btnSachBanChay;
    ListView lvSachBanChay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_sach_ban_chay);
        edSachBanChay = findViewById(R.id.edSachBanChay);
        btnSachBanChay = findViewById(R.id.btnSachBanChay);
        lvSachBanChay = findViewById(R.id.lvSachBanChay);
        sachDAO = new SachDAO(this);
        listSach = new ArrayList<>();
        btnSachBanChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String month = edSachBanChay.getText().toString();
                listSach = sachDAO.getSachTop10(month);
                if (listSach.size() > 0) {
                    lvQLySachAdapter = new LvQLySachAdapter(TopSachBanChayActivity.this, listSach);
                    lvSachBanChay.setAdapter(lvQLySachAdapter);
                }else{
                    listSach.clear();
                    lvQLySachAdapter=new LvQLySachAdapter(TopSachBanChayActivity.this,listSach);
                    lvSachBanChay.setAdapter(lvQLySachAdapter);
                }
            }
        });
    }
}
