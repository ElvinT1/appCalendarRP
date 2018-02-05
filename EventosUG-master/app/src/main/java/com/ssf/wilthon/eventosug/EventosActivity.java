package com.ssf.wilthon.eventosug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class EventosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        Intent objIntent;
        objIntent = getIntent();
        JSONObject objJSON = null;

        try {
            objJSON = new JSONObject(objIntent.getStringExtra("evento"));
            String cod = objJSON.getString("id_evento");
            Toast.makeText(this, cod, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
