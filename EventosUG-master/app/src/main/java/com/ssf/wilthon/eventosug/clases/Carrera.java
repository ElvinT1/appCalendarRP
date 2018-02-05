package com.ssf.wilthon.eventosug.clases;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wilthon on 01/12/2017.
 */

public class Carrera {
    int id;
    String carrera;
    String estado;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String toJSON(){
        //Serializar
        JSONObject objJSON = new JSONObject();
        try {
            objJSON.put("id_carrera",getId());
            objJSON.put("nombre",getCarrera());
        } catch (JSONException e) {
            e.printStackTrace();
            return "{}";
        }
        return objJSON.toString();
    }


}
