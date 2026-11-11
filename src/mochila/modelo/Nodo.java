package mochila.modelo;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
    private String nombre;
    private double distancia;
    private List<Objeto> objetos;

    public Nodo(String nombre, double distancia) {
        this.nombre = nombre;
        this.distancia = distancia;
        this.objetos = new ArrayList<>();
    }
    public void agregarObjeto(Objeto objeto) {
        this.objetos.add(objeto);
    }

    public String getNombre() { return nombre; }
    public double getDistancia() { return distancia; }
    public List<Objeto> getObjetos() { return objetos; }
}