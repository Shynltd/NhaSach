package com.example.nhasach.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.nhasach.dao.HoaDonChiTietDAO;
import com.example.nhasach.dao.HoaDonDAO;
import com.example.nhasach.dao.NguoiDungDAO;
import com.example.nhasach.dao.SachDAO;
import com.example.nhasach.dao.TheLoaiDAO;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbBookManager";
    public static final int version = 13;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NguoiDungDAO.SQL_NGUOI_DUNG);
        sqLiteDatabase.execSQL(TheLoaiDAO.SQL_THE_LOAI);
        sqLiteDatabase.execSQL(SachDAO.SQL_SACH);
        sqLiteDatabase.execSQL(HoaDonDAO.SQL_HOA_DON);
        sqLiteDatabase.execSQL(HoaDonChiTietDAO.SQL_HOA_DON_CHI_TIET);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NguoiDungDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TheLoaiDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +SachDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HoaDonChiTietDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +HoaDonDAO.TABLE_NAME);


        onCreate(sqLiteDatabase);
    }
}
