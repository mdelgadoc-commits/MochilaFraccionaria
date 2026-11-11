package mochila.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Mochila {

    public static class ElementoSeleccionado {
        private Objeto objeto;
        private double fraccion; // 1.0 para completo, < 1.0 para fraccional

        public ElementoSeleccionado(Objeto objeto, double fraccion) {
            this.objeto = objeto;
            this.fraccion = fraccion;
        }

        public Objeto getObjeto() { return objeto; }
        public double getFraccion() { return fraccion; }
    }

    private double capacidadMaxima;
    private List<ElementoSeleccionado> seleccionados;

    public Mochila(double capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.seleccionados = new ArrayList<>();
    }

    public void llenarMochila(List<Objeto> objetos) {
        // Criterio Voraz (Greedy): Ordenar de MAYOR a MENOR por relación Valor/Peso
        objetos.sort(Comparator.comparingDouble(Objeto::getRelacionValorPeso).reversed());

        double pesoActual = 0.0;

        for (Objeto obj : objetos) {
            if (pesoActual >= capacidadMaxima) {
                break;
            }

            double espacioLibre = capacidadMaxima - pesoActual;

            if (obj.getPeso() <= espacioLibre) {
                // Entra completo
                seleccionados.add(new ElementoSeleccionado(obj, 1.0));
                pesoActual += obj.getPeso();
            } else {
                // Entra solo una fracción
                double fraccion = espacioLibre / obj.getPeso();
                seleccionados.add(new ElementoSeleccionado(obj, fraccion));
                pesoActual += espacioLibre;
                break;
            }
        }
    }

    public void mostrarResultado() {
        System.out.println("Objetos seleccionados:");
        double valorTotal = 0.0;

        for (ElementoSeleccionado elem : seleccionados) {
            Objeto obj = elem.getObjeto();
            double f = elem.getFraccion();
            double valorAportado = obj.getValor() * f;
            valorTotal += valorAportado;

            if (f == 1.0) {
                System.out.printf("%s completo%n", obj.getNombre());
            } else {
                // Muestra la fracción formateada (ejemplo: 20/30 Parte del objeto C)
                int pesoParte = (int) Math.round(obj.getPeso() * f);
                int pesoTotalObj = (int) Math.round(obj.getPeso());
                System.out.printf("%d/%d Parte del objeto %s%n", pesoParte, pesoTotalObj, obj.getNombre());
            }
        }
        System.out.printf("Valor total aproximado: %.0f%n", valorTotal);
    }
}