package com.ssf.wilthon.eventosug.ModeloDominio;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ssf.wilthon.eventosug.InicioActivity;
import com.ssf.wilthon.eventosug.clases.Usuario;
import com.ssf.wilthon.eventosug.modelo.ModelUsuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wilthon on 31/01/2018.
 */

public class MDUsuario {

    public String url = "http://192.168.1.5/WsEventosUG/api/";

    public void Authenticate(final Context ctx, String user, String pass){
        RequestQueue queue = Volley.newRequestQueue(ctx);
        url = url+"ConsultarLogin?correo="+user+"&clave="+pass;
        JsonArrayRequest jsArrayRequestLogin = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject mJsonObjectProperty = response.getJSONObject(0);
                            Usuario usuario = new Usuario();
                            usuario.setCedula(mJsonObjectProperty.getString("id_usuario"));
                            usuario.setUsuario(mJsonObjectProperty.getString("correo"));
                            usuario.setClave(mJsonObjectProperty.getString("clave"));
                            usuario.toJSON();
                            //Toast.makeText(ctx,mJsonObjectProperty.getString("id_usuario"),Toast.LENGTH_LONG).show();
                            ModelUsuario objModelUsuario = new ModelUsuario();
                            Cursor fila = objModelUsuario.consulta(ctx, Integer.parseInt(usuario.getCedula()));
                            if (fila != null) {
                                if (!fila.moveToFirst())
                                    objModelUsuario.Insert(ctx,Integer.parseInt(usuario.getCedula()));
                            }
                            Intent objInicio = new Intent(ctx, InicioActivity.class);
                            objInicio.putExtra("usuario", usuario.toJSON());
                            ctx.startActivity(objInicio);

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ctx,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        jsArrayRequestLogin.setRetryPolicy(new DefaultRetryPolicy(500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsArrayRequestLogin);
    }
}
