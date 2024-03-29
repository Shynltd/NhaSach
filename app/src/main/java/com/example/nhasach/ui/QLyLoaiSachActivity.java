package com.example.nhasach.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
import com.example.nhasach.dao.NguoiDungDAO;
import com.example.nhasach.dao.TheLoaiDAO;
import com.example.nhasach.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class QLyLoaiSachActivity extends AppCompatActivity {
    private ListView lvQuanLyLoaiSach;
    private List<LoaiSach> listLoaiSach;
    private LvQLyLoaiSachAdapter LVQLyLoaiSachAdapter;
    private TheLoaiDAO theLoaiDAO;
    Intent intent;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_sach);
        iconBack();
        theLoaiDAO = new TheLoaiDAO(this);
        nguoiDungDAO=new NguoiDungDAO(this);
        lvQuanLyLoaiSach = findViewById(R.id.lvQLyLoaiSach);
        listLoaiSach = new ArrayList<>();
        listLoaiSach = theLoaiDAO.getAllLoaiSach();
        LVQLyLoaiSachAdapter = new LvQLyLoaiSachAdapter(this, listLoaiSach);
        lvQuanLyLoaiSach.setAdapter(LVQLyLoaiSachAdapter);
        lvQuanLyLoaiSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    intent = new Intent(QLyLoaiSachActivity.this, SuaTheLoaiActivity.class);
                    Bundle bundle = new Bundle();
                    LoaiSach loaiSach = listLoaiSach.get(i);
                    bundle.putSerializable("LoaiSach", loaiSach);
                    intent.putExtra("Bundle", bundle);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_qlyloaisach, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.them_loaisach:
                startActivity(new Intent(QLyLoaiSachActivity.this, ThemTheLoaiActivity.class));
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            listLoaiSach.clear();
            listLoaiSach = theLoaiDAO.getAllLoaiSach();
            LVQLyLoaiSachAdapter.dataSetChange(listLoaiSach);
        } catch (Exception e) {
            Log.e("BOOKKK", e.toString() + " ");
        }
    }
}
