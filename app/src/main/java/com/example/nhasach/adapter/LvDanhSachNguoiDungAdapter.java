package com.example.nhasach.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.nhasach.R;
import com.example.nhasach.dao.NguoiDungDAO;
import com.example.nhasach.model.NguoiDung;

import java.util.List;

public class LvDanhSachNguoiDungAdapter extends BaseAdapter {
    NguoiDungDAO nguoiDungDAO;
    private Context context;
    private List<NguoiDung> listNguoiDung;

    public LvDanhSachNguoiDungAdapter(Context context, List<NguoiDung> listNguoiDung) {
        this.context = context;
        this.listNguoiDung = listNguoiDung;
        nguoiDungDAO = new NguoiDungDAO(context);
    }

    @Override
    public int getCount() {
        return listNguoiDung.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        NguoiDungHolder nguoiDungHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lv_danhsachnguoidung, viewGroup, false);
            nguoiDungHolder = new NguoiDungHolder();
            nguoiDungHolder.tvTen = view.findViewById(R.id.tvTenNguoiDung);
            nguoiDungHolder.tvSoDienThoai = view.findViewById(R.id.tvSDTNguoiDung);
            nguoiDungHolder.imgXoa = view.findViewById(R.id.imgXoaND);
            view.setTag(nguoiDungHolder);
        } else {
            nguoiDungHolder = (NguoiDungHolder) view.getTag();
        }
        nguoiDungHolder.tvSoDienThoai.setText(listNguoiDung.get(i).getPhone());
        nguoiDungHolder.tvTen.setText(listNguoiDung.get(i).getFullname());
        nguoiDungHolder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NguoiDung nguoiDung = listNguoiDung.get(i);

                    int result = nguoiDungDAO.xoaNguoiDung(nguoiDung.getUsername());
                    if (result > 0) {
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        listNguoiDung.remove(nguoiDung);
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "Không được xóa Admin", Toast.LENGTH_SHORT).show();
                    }



            }
        });
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<NguoiDung> nguoiDungList) {
        this.listNguoiDung = nguoiDungList;
        notifyDataSetChanged();
    }

    public class NguoiDungHolder {
        TextView tvTen, tvSoDienThoai;
        ImageView imgXoa;
    }
}
