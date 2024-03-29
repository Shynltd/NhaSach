package com.example.nhasach.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nhasach.R;
import com.example.nhasach.adapter.LvQLyLoaiSachAdapter;
import com.example.nhasach.adapter.LvQLySachAdapter;
import com.example.nhasach.dao.NguoiDungDAO;
import com.example.nhasach.dao.SachDAO;
import com.example.nhasach.model.Sach;

import java.util.ArrayList;
import java.util.List;


public class QLySachActivity extends AppCompatActivity {
    List<Sach> listSach;
    ListView lvQuanLySach;
    SachDAO sachDAO;
    LvQLySachAdapter lvQLySachAdapter;
    Intent intent;
    Bundle bundle;

    NguoiDungDAO nguoiDungDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        iconBack();
        listSach = new ArrayList<>();
        sachDAO = new SachDAO(this);
        nguoiDungDAO = new NguoiDungDAO(this);
        lvQuanLySach = findViewById(R.id.lv_QLySach);
        listSach = sachDAO.getAllSach();
        lvQLySachAdapter = new LvQLySachAdapter(this, listSach);

        lvQuanLySach.setAdapter(lvQLySachAdapter);
        lvQuanLySach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    intent = new Intent(QLySachActivity.this, SuaSachActivity.class);
                    Bundle bundle = new Bundle();
                    Sach sach = sachDAO.getAllSach().get(i);
                    bundle.putSerializable("Sach", sach);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
            }
        });

    }

    private void iconBack() {
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listSach.clear();
        listSach = sachDAO.getAllSach();
        lvQLySachAdapter.ondatasetchanged(listSach);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quanlisach, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.themSach:
                startActivity(new Intent(QLySachActivity.this, ThemSachActivity.class));
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
