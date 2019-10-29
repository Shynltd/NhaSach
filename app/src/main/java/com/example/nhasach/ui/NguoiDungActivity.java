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
import com.example.nhasach.adapter.LvDanhSachNguoiDungAdapter;
import com.example.nhasach.dao.NguoiDungDAO;
import com.example.nhasach.model.NguoiDung;
import java.util.ArrayList;
import java.util.List;

public class NguoiDungActivity extends AppCompatActivity {
    ListView lvNguoiDung;
    NguoiDungDAO nguoiDungDAO;
    List<NguoiDung> listNguoiDung;
    LvDanhSachNguoiDungAdapter LVDanhSachNguoiDungAdapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        iconBack();
        init();
        LVDanhSachNguoiDungAdapter = new LvDanhSachNguoiDungAdapter(this, listNguoiDung);
        lvNguoiDung.setAdapter(LVDanhSachNguoiDungAdapter);

        lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    NguoiDung nguoiDungSelect = listNguoiDung.get(i);
                    intent = new Intent(NguoiDungActivity.this, UpdateNguoiDungActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("NguoiDung", nguoiDungSelect);
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

    private void init() {
        lvNguoiDung = findViewById(R.id.lvNguoiDung);
        nguoiDungDAO = new NguoiDungDAO(NguoiDungActivity.this);
        listNguoiDung = new ArrayList<>();
        listNguoiDung = nguoiDungDAO.getAllNguoiDung();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nguoidung, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.add_nguoidung:
                startActivity(new Intent(NguoiDungActivity.this, ThemNguoiDungActivity.class));
                break;


            case R.id.changepass_nguoidung:
                startActivity(new Intent(NguoiDungActivity.this, DoiMatKhauActivity.class));
                break;
            case R.id.logout_nguoidung:
                Intent intentLogOut = new Intent(NguoiDungActivity.this, LoginActivity.class);
                intentLogOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentLogOut);
                NguoiDungActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        listNguoiDung.clear();
        listNguoiDung = nguoiDungDAO.getAllNguoiDung();
        LVDanhSachNguoiDungAdapter.changeDataset(listNguoiDung);

    }
}
