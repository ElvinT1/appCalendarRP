package com.ssf.wilthon.eventosug.clases;

/**
 * Created by Wilthon on 01/12/2017.
 */

public class Administrador extends Usuario{
    String nombre;
    String apellido;
    String cargo;
    String Estado;
    int cedula;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

}
