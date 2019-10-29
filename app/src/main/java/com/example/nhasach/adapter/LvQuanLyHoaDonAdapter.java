package com.example.nhasach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhasach.R;
import com.example.nhasach.dao.HoaDonDAO;
import com.example.nhasach.model.HoaDon;

import java.util.List;


public class LvQuanLyHoaDonAdapter extends BaseAdapter {
    private Context context;
    List<HoaDon>hoaDonList;

    public LvQuanLyHoaDonAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return hoaDonList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        QLyHoaDonHolder qLyHoaDonHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lv_hoadon, viewGroup, false);
            qLyHoaDonHolder = new QLyHoaDonHolder();
            qLyHoaDonHolder.tvSTT = view.findViewById(R.id.tvNgayThangNam);
            qLyHoaDonHolder.tvNgayThangNam = view.findViewById(R.id.tvSTT);
            view.setTag(qLyHoaDonHolder);
        } else {
            qLyHoaDonHolder = (QLyHoaDonHolder) view.getTag();
        }
        qLyHoaDonHolder.tvSTT.setText("1");
        qLyHoaDonHolder.tvNgayThangNam.setText("2-2-2222");
        return view;
    }

    public class QLyHoaDonHolder {
        TextView tvSTT, tvNgayThangNam;
    }
}
