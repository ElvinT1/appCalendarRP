package com.ssf.wilthon.eventosug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ssf.wilthon.eventosug.clases.Usuario;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Usuario usuario = new Usuario();
        if (usuario.ValidaLogin(this)){
            Intent objInicio = new Intent(this, InicioActivity.class);
            objInicio.putExtra("usuario", usuario.toJSON());
            startActivity(objInicio);
            finish();
        }

        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        if (view == btnSiguiente){
            Intent objLogin = new Intent(this, LoginActivity.class);
            startActivity(objLogin);
            finish();
        }
    }
}
