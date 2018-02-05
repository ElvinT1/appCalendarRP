package com.ssf.wilthon.eventosug.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Wilthon on 09/12/2017.
 */

public class ModelUsuario {

    String sql="";
    String[] arg;
    Conexion con;
    SQLiteDatabase BD;
    ContentValues registro;

    public void Insert(Context ctx,int id){
        con = new Conexion(ctx);
        BD = con.getWritableDatabase();
        if (BD != null) {
            registro = new ContentValues();
            registro.put("id_usuario", id);
            registro.put("estado", 1);
            BD.insert("usuario", null, registro);
            BD.close();
        }
    }
    public void Inactivo(int id){
        registro = new ContentValues();
        registro.put("estado", "I");
        //where
        arg = new String[]{String.valueOf(id)};
        BD.update("usuario", registro, "id_usuario =?", arg);
        BD.close();
    }
    public void Eliminar(Context ctx, int id){
        con = new Conexion(ctx);
        BD = con.getWritableDatabase();
        arg = new String[]{String.valueOf(id)};
        BD.delete("usuario", "id_usuario =?", arg);
        BD.close();
    }

    public Cursor consulta(Context ctx ,int id){
        con = new Conexion(ctx);
        BD = con.getWritableDatabase();
        arg = new String[]{String.valueOf(id)};

        sql="select estado from usuario where id_usuario=?";
        Cursor fila = BD.rawQuery(sql, arg);
        return fila;
    }
    public Cursor ValidaLogin(Context ctx){
        con = new Conexion(ctx);
        BD = con.getWritableDatabase();

        sql="select estado, id_usuario from usuario";
        Cursor fila = BD.rawQuery(sql, arg);
        return fila;
    }

}



























