package mochila.modelo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // EJEMPLO 2 DE LA GUÍA
        List<Objeto> objetosEjemplo2 = new ArrayList<>();
        objetosEjemplo2.add(new Objeto("A", 80.0, 20.0));
        objetosEjemplo2.add(new Objeto("B", 100.0, 10.0));
        objetosEjemplo2.add(new Objeto("C", 120.0, 30.0));

        Mochila mochila2 = new Mochila(25.0);
        mochila2.llenarMochila(objetosEjemplo2);
        mochila2.mostrarResultado();
    }
}