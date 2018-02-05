package com.ssf.wilthon.eventosug.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Wilthon on 19/01/2018.
 */

public class EventoLD {

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
            registro.put("id_evento", 1);
            registro.put("nombre", "Suspension clases");
            registro.put("fecha_creacion", "10/01/2018");
            registro.put("estado", "A");
            BD.insert("evento", null, registro);

            registro.put("id_evento", 2);
            registro.put("nombre", "vacaciones carnaval");
            registro.put("fecha_creacion", "12/02/2018");
            registro.put("estado", "A");
            BD.insert("evento", null, registro);

            registro.put("id_evento", 3);
            registro.put("nombre", "vacaciones san valentin");
            registro.put("fecha_creacion", "14/02/2018");
            registro.put("estado", "I");
            BD.insert("evento", null, registro);

            BD.close();
        }
    }
    public void Inactivo(int id){
        registro = new ContentValues();
        registro.put("estado", "I");
        //where
        arg = new String[]{String.valueOf(id)};
        BD.update("evento", registro, "id =?", arg);
        BD.close();
    }
    public void Eliminar(int id){
        arg = new String[]{String.valueOf(id)};
        BD.delete("evento", "id =?", arg);
        BD.close();
    }

    public Cursor consulta(Context ctx){
        con = new Conexion(ctx);
        BD = con.getWritableDatabase();
        arg = new String[]{"A"};

        sql="select id_evento, nombre, fecha_creacion, estado from evento where estado=?";
        Cursor fila = BD.rawQuery(sql, arg);
        return fila;
    }

}
