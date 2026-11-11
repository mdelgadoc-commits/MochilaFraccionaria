package mochila.modelo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Nodo nodoCercano = new Nodo("Nodo A", 5.0);
        Nodo nodoLejano = new Nodo("Nodo B", 15.0);

        nodoCercano.agregarObjeto(new Objeto("Elemento 1", 30.0, 15.0));
        nodoCercano.agregarObjeto(new Objeto("Elemento 2", 20.0, 10.0));
        nodoCercano.agregarObjeto(new Objeto("Elemento 3", 15.0, 8.0));

        List<Nodo> listaNodos = new ArrayList<>();
        listaNodos.add(nodoLejano);
        listaNodos.add(nodoCercano);

        System.out.println("=== CASO 1: Límite por Peso (40.0) ===");
        Mochila mochilaPeso = new Mochila(40.0, "peso");
        mochilaPeso.llenarMochila(listaNodos);
        mochilaPeso.mostrarResumen();

        System.out.println("\n=== CASO 2: Límite por Valor (18.0) ===");
        Mochila mochilaValor = new Mochila(18.0, "valor");
        mochilaValor.llenarMochila(listaNodos);
        mochilaValor.mostrarResumen();
    }
}