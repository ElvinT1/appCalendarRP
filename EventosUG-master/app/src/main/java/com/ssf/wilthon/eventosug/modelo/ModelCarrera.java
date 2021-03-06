package com.ssf.wilthon.eventosug.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Wilthon on 10/12/2017.
 */

public class ModelCarrera {

    String sql="";
    String[] arg;
    Conexion con;
    SQLiteDatabase BD;
    ContentValues registro;

    public void Insert(Context ctx){
        con = new Conexion(ctx);
        BD = con.getWritableDatabase();
        if (BD != null) {
            registro = new ContentValues();
            registro.put("id_carrera", 1);
            registro.put("nombre", "CISC");
            registro.put("id_facultad", "1");
            registro.put("estado", "A");
            BD.insert("usuario", null, registro);

            registro = new ContentValues();
            registro.put("id_carrera", 2);
            registro.put("nombre", "CINT");
            registro.put("id_facultad", "1");
            registro.put("estado", "A");
            BD.insert("usuario", null, registro);

            registro = new ContentValues();
            registro.put("id_carrera", 3);
            registro.put("nombre", "CIC");
            registro.put("id_facultad", "1");
            registro.put("estado", "A");
            BD.insert("usuario", null, registro);
            BD.close();
        }
    }
    public void Modificar(int id){
        registro = new ContentValues();
        registro.put("correo", "juan@ug.edu.ec");
        //where
        arg = new String[]{String.valueOf(id), "juan"};
        BD.update("usuario", registro, "id =?", arg);
        BD.close();
    }
    public void Inactivo(int id){
        registro = new ContentValues();
        registro.put("estado", "I");
        //where
        arg = new String[]{String.valueOf(id)};
        BD.update("usuario", registro, "id =?", arg);
        BD.close();
    }
    public void Eliminar(int id){
        arg = new String[]{String.valueOf(id)};
        BD.delete("usuario", "id =?", arg);
        BD.close();
    }
    public Cursor consulta(String correo, Context ctx){
        con = new Conexion(ctx);
        BD = con.getWritableDatabase();
        arg = new String[]{correo};

        sql="select correo, clave, estado from usuario where correo=?";
        Cursor fila = BD.rawQuery(sql, arg);
        return fila;
    }
}
