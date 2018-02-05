package com.ssf.wilthon.eventosug.clases;

import android.content.Context;
import android.database.Cursor;
import android.widget.ListView;

import com.ssf.wilthon.eventosug.ModeloDominio.MDEvento;
import com.ssf.wilthon.eventosug.modelo.EventoLD;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wilthon on 19/01/2018.
 */

public class Evento {
    int id;
    String nombre;
    String detalle;
    String fecha_inicio;
    String fecha_fin;
    String estado;

    public int getId() {  return id;  }
    public void setId(int id) { this.id = id;  }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre;  }

    public String getDetalle() { return detalle;  }
    public void setDetalle(String detalle) {  this.detalle = detalle;  }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_inicio() { return fecha_inicio;  }
    public void setFecha_inicio(String fecha_inicio) { this.fecha_inicio = fecha_inicio; }

    public String getFecha_fin() { return fecha_fin; }
    public void setFecha_fin(String fecha_fin) { this.fecha_fin = fecha_fin; }

    public void GetEventos(Context ctx, ListView listView, String id_usuario){
        MDEvento eventodb = new MDEvento();
        eventodb.GetEventos(ctx,listView,id_usuario);
    }

    public Cursor getEvento(Context ctx){
        EventoLD objEvento =  new EventoLD();
        objEvento.Insert(ctx);
        Cursor fila = objEvento.consulta(ctx);
        if (fila != null) {
            return fila;
        }else{
            return null;
        }
    }

    public String toJSON(){
        //Serializar
        JSONObject objJSON = new JSONObject();
        try {
            objJSON.put("id_evento",getId());
            objJSON.put("titulo",getNombre());
        } catch (JSONException e) {
            e.printStackTrace();
            return "{}";
        }
        return objJSON.toString();
    }

}
