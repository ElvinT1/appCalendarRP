package com.ssf.wilthon.eventosug.ModeloDominio;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ssf.wilthon.eventosug.EventosActivity;
import com.ssf.wilthon.eventosug.InicioActivity;
import com.ssf.wilthon.eventosug.R;
import com.ssf.wilthon.eventosug.clases.Evento;
import com.ssf.wilthon.eventosug.clases.Usuario;
import com.ssf.wilthon.eventosug.modelo.ModelUsuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Wilthon on 02/02/2018.
 */

public class MDEvento {
    public String url = "http://192.168.1.5/WsEventosUG/api/";
    Evento evento =new Evento();
    public void GetEventos(final Context ctx, final ListView listView, String id_usuario){
        RequestQueue queue = Volley.newRequestQueue(ctx);
        url = url+"ConsultarEventos?id_usuario="+id_usuario+"&id_evento=0";
        JsonArrayRequest jsArrayRequestLogin = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<String> items = new ArrayList<String>();
                            Integer[] img = new Integer[response.length()];
                            ArrayAdapter<Evento> objAdap;
                            final ArrayList<Integer> codigos = new ArrayList<Integer>();
                            for (int i=0; i<= response.length();i++) {
                                JSONObject jsonEvento = response.getJSONObject(i);
                                evento.setId(jsonEvento.getInt("id_evento"));
                                evento.setNombre(jsonEvento.getString("Titulo"));
                                evento.setDetalle(jsonEvento.getString("Detalle"));
                                evento.setFecha_inicio(jsonEvento.getString("fecha_inicio"));
                                evento.setFecha_fin(jsonEvento.getString("fecha_fin"));
                                evento.toJSON();
                                //Toast.makeText(ctx, evento.getNombre(), Toast.LENGTH_LONG).show();
                                if (jsonEvento.getString("Preferencia") == "Universidad de Guayaquil")
                                    img[i] = R.drawable.uglogo;
                                else if (jsonEvento.getString("Preferencia") == "Facultad de Ciencias Matemáticas y Físicas")
                                    img[i] = R.drawable.fcmf;
                                else if (jsonEvento.getString("Preferencia") == "Carrera Ingenieria en sistemas")
                                    img[i] = R.drawable.logocisc;
                                else
                                    img[i] = R.drawable.docente;

                                items.add(evento.getNombre()+"\n"+"   Fecha: "+evento.getFecha_inicio().substring(0,10));
                                codigos.add(evento.getId());

                                objAdap =new ArrayAdapter(ctx,R.layout.filas,items);
                                listView.setAdapter(objAdap);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        int cod = codigos.get(i);
                                        //Toast.makeText(ctx,"Seleccionada: "+cod,Toast.LENGTH_LONG).show();
                                        Intent objEvento = new Intent(ctx, EventosActivity.class);
                                        objEvento.putExtra("evento", evento.toJSON());
                                        ctx.startActivity(objEvento);
                                    }
                                });
                            }

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
