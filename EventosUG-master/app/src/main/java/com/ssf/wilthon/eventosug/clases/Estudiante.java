package com.ssf.wilthon.eventosug.clases;

/**
 * Created by Wilthon on 19/11/2017.
 */

public class Estudiante extends Usuario{
    String nombre;
    String apellido;
    String Estado;
    int cedula;

    public String getNombre() { return nombre;  }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEstado() { return Estado; }
    public void setEstado(String estado) { Estado = estado; }

}
