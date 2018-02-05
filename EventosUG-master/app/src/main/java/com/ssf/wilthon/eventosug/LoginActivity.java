package com.ssf.wilthon.eventosug;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.ssf.wilthon.eventosug.clases.Usuario;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    public EditText txtUsuario;
    public EditText txtClave;
    public Button btnEntrar;
    Context ctx = this;

    public String url = "http://192.168.1.5/WsEventosUG/api/";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsuario = (EditText)  findViewById(R.id.txtUsuario);
        txtClave = (EditText)  findViewById(R.id.txtClave);
        btnEntrar = (Button) findViewById(R.id.btnLogin);
        btnEntrar.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        if (view == btnEntrar){
            attemptLogin();
        }
    }
    private void attemptLogin() {

        String usuario = txtUsuario.getText().toString();
        String clave = txtClave.getText().toString();

        View focusView = null;
        boolean cancel = false;
            if (TextUtils.isEmpty(clave) && !isPasswordValid(clave)) {
                txtClave.setError(getString(R.string.error_invalid_password));
                focusView = txtClave;
                cancel = true;
            }
            if (TextUtils.isEmpty(usuario)) {
                txtUsuario.setError(getString(R.string.error_field_required));
                focusView = txtUsuario;
                cancel = true;
            } else if (!isEmailValid(usuario)) {
                txtUsuario.setError(getString(R.string.error_invalid_email));
                focusView = txtUsuario;
                cancel = true;
            }
            if (cancel) {
                focusView.requestFocus();
            } else {
                Usuario objUsuario = new Usuario();
                objUsuario.Authenticate(this,usuario,clave);

            }
    }
    private boolean isEmailValid(String email) {
        return email.contains("@ug.edu.ec");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 7;
    }
}









