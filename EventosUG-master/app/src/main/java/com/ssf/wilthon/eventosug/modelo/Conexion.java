package com.ssf.wilthon.eventosug.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Wilthon on 19/11/2017.
 */

public class Conexion extends SQLiteOpenHelper{

    String sql="";

    public Conexion(Context context) {
        super(context, "dbevento", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sql = "create table usuario(id_usuario integer primary key, estado integer)";
        sqLiteDatabase.execSQL(sql);
        sql = "create table evento(id_evento integer primary key, nombre text, fecha_creacion text, estado text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sql = "drop table if exists usuario";
        sqLiteDatabase.execSQL(sql);
        sql = "create table usuario(id_usuario integer primary key, estado integer)";
        sqLiteDatabase.execSQL(sql);
    }
}
