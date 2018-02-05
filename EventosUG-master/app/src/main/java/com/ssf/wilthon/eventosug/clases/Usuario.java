package com.ssf.wilthon.eventosug.clases;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ssf.wilthon.eventosug.ModeloDominio.MDUsuario;
import com.ssf.wilthon.eventosug.modelo.ModelUsuario;

/**
 * Created by Wilthon on 16/11/2017.
 */

public class Usuario {
    String Cedula;
    String usuario;
    String clave;

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCedula() { return Cedula; }
    public void setCedula(String cedula) { Cedula = cedula; }

    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }

    //JSON
    public String toJSON(){
        //Serializar
        JSONObject objJSON = new JSONObject();
        try {
            objJSON.put("user",getUsuario());
            objJSON.put("pass",getClave());
            objJSON.put("cedula",getCedula());
        } catch (JSONException e) {
            e.printStackTrace();
            return "{}";
        }
        return objJSON.toString();
    }

    public void LogOut(Context ctx, int id){
        ModelUsuario objModelUsuario = new ModelUsuario();
        objModelUsuario.Eliminar(ctx, id);
    }

    public boolean ValidaLogin(Context ctx){
        boolean valida = false;
        ModelUsuario objModelUsuario = new ModelUsuario();
        Cursor fila = objModelUsuario.ValidaLogin(ctx);
        if (fila != null) {
            if (fila.moveToFirst()) {
                String estado = fila.getString(0);
                setCedula(fila.getString(1));
                if (estado.equals("1"))
                    valida = true;
                else
                    valida = false;
                fila.close();
            } else {
                valida=false;
            }
        }
        return valida;
    }

    public void Authenticate(Context ctx, String user, String pass) {
        MDUsuario usuario = new MDUsuario();
        usuario.Authenticate(ctx, user, pass);
    }
}
