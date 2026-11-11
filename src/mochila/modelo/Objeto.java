package mochila.modelo;

public class Objeto {
    private String nombre;
    private double valor;
    private double peso;

    public Objeto(String nombre, double valor, double peso) {
        this.nombre = nombre;
        this.valor = valor;
        this.peso = peso;
    }

    public String getNombre() { return nombre; }
    public double getValor() { return valor; }
    public double getPeso() { return peso; }

    // Retorna la densidad (cuánto valor aporta por cada unidad de peso)
    public double getRelacionValorPeso() {
        return valor / peso;
    }
}