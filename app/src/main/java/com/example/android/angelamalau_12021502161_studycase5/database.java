package com.example.android.angelamalau_12021502161_studycase5;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Angela Malau on 25/03/2018.
 */

public class database extends SQLiteOpenHelper {
    //deklarasi variable yang digunakan
    Context context;
    SQLiteDatabase db;

    public static final String nama_db = "listtodo.db";
    public static final String nama_tabel = "daftartodo";
    public static final String kolom1 = "todo";
    public static final String kolom2 = "description";
    public static final String kolom3 = "priority";

    //konstruktor
    public database(Context context) {
        super(context, nama_db, null, 1);
        this.context = context;
        db = this.getWritableDatabase();
        db.execSQL("create table if not exists "+nama_tabel+" (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }

    //saat database dibuat
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+nama_tabel+" (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+nama_tabel);
        onCreate(sqLiteDatabase);
    }

    //method menambahkan data pada database
    public boolean inputdata(addData list){
        //menambahkan dan menyesuaikan kolom dengan nilainya
        ContentValues nilai = new ContentValues();
        nilai.put(kolom1, list.getTodo());
        nilai.put(kolom2, list.getDesc());
        nilai.put(kolom3, list.getPrior());
        long hasil = db.insert(nama_tabel, null, nilai);
        if (hasil ==- 1){
            return false;
        } else {
            return true;
        }
    }

    //method menghapus data pada database
    public boolean deletedata(String ToDo) {
        return db.delete(nama_tabel, kolom1+"=\""+ToDo+"\"", null)>0;
    }

    //method untuk akses dan baca data dari database
    public void readdata(ArrayList<addData> daftar){
        Cursor cursor = this.getReadableDatabase().rawQuery("select todo, description, priority from "+nama_tabel, null);
        while (cursor.moveToNext()){
            daftar.add(new addData(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
}
