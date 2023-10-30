package com.example.adminpet;

public class Mascota {

    private String ruta;
    private String nombre;
    private String edad;

    public Mascota(String ruta, String nombre, String edad) {
        this.ruta = ruta;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
