package com.ssf.wilthon.eventosug;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import com.ssf.wilthon.eventosug.clases.Evento;
import com.ssf.wilthon.eventosug.clases.Usuario;

import java.util.ArrayList;

public class InicioActivity extends AppCompatActivity{

    public ImageView img;
    private Toolbar toolbar;
    private ArrayList<Integer> codigos;
    private ArrayList<String> lista;
    ListView lstEventos;

    Usuario objUsuario=new Usuario();
    Evento objevento = new Evento();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        lstEventos = (ListView)findViewById(R.id.lstEventos);
        toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        Cursor cursor = objevento.getEvento(this);
        lista = new ArrayList<String>();
        codigos=new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                codigos.add(cursor.getInt(0));
                lista.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        Intent objIntent;
        objIntent = getIntent();
        try {
            JSONObject objJSON = new JSONObject(objIntent.getStringExtra("usuario"));
            objUsuario.setCedula(objJSON.getString("cedula"));
            Toast.makeText(this, objUsuario.getCedula(),Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, e.toString(),Toast.LENGTH_LONG).show();
        }
        objevento.GetEventos(this,lstEventos,objUsuario.getCedula());
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.usuario:

                return true;
            case R.id.preferencias:

                return true;
            case R.id.cerrarSesion:
                objUsuario.LogOut(this, Integer.parseInt(objUsuario.getCedula()));
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
