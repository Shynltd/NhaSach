package com.example.nhasach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nhasach.database.DatabaseHelper;
import com.example.nhasach.model.HoaDonChiTiet;

public class HoaDonChiTietDAO {
    public static final String TABLE_NAME = "HoaDonChiTiet";
    public static final String SQL_HOA_DON_CHI_TIET = "" +
            "CREATE TABLE HoaDonChiTiet (mahdct integer primary key autoincrement, mahoadon text, masach text, soluong integer)";
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public HoaDonChiTietDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mahoadon", hoaDonChiTiet.getHoaDon().getMaHoaDon());
        contentValues.put("masach", hoaDonChiTiet.getSach().getMasach());
        contentValues.put("soluong", hoaDonChiTiet.getSoLuongMua());
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public int updateHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mahoadon", hoaDonChiTiet.getHoaDon().getMaHoaDon());
        contentValues.put("masach", hoaDonChiTiet.getSach().getMasach());
        contentValues.put("soluong", hoaDonChiTiet.getSoLuongMua());
        return db.update(TABLE_NAME, contentValues, "maHDCT=?", new String[]{hoaDonChiTiet.getMaHDCT() + ""});
    }

    public double getDoanhThuTheoNgay(String ngaymua) {
        double doanhThu = 0;
        String sSQL = "select sum(tongtien) from(select sum(Sach.giabia* HoaDonChiTiet.soLuong) as tongtien from HoaDon inner join HoaDonChiTiet on HoaDon.mahoadon=HoaDonChiTiet.mahoadon inner join Sach on Sach.masach=HoaDonChiTiet.masach where strftime('%d', HoaDon.ngaymua=?)  group by HoaDonChiTiet.masach)";
        Cursor c = db.rawQuery(sSQL, new String[]{ngaymua});
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            Log.e("Doanhthu",doanhThu+"");
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
    public double getDoanhThuTheoThang(String thangmua){
        double doanhThu = 0;
        String sSQL = "select sum(tongtien) from(select sum(Sach.giabia* HoaDonChiTiet.soLuong) as tongtien from HoaDon inner join HoaDonChiTiet on HoaDon.mahoadon=HoaDonChiTiet.mahoadon inner join Sach on Sach.masach=HoaDonChiTiet.masach where strftime('%m', HoaDon.ngaymua=?)  group by HoaDonChiTiet.masach)";
        Cursor c = db.rawQuery(sSQL, new String[]{thangmua});
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            Log.e("Doanhthu",doanhThu+"");
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
    public double getDoanhThuTheoNam(String nammua){
        double doanhThu = 0;
        String sSQL = "select sum(tongtien) from(select sum(Sach.giabia* HoaDonChiTiet.soLuong) as tongtien from HoaDon inner join HoaDonChiTiet on HoaDon.mahoadon=HoaDonChiTiet.mahoadon inner join Sach on Sach.masach=HoaDonChiTiet.masach where strftime('%Y', HoaDon.ngaymua=?)  group by HoaDonChiTiet.masach)";
        Cursor c = db.rawQuery(sSQL, new String[]{nammua});
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            doanhThu = c.getDouble(0);
            Log.e("Doanhthu",doanhThu+"");
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }

}

