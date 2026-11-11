package mochila.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Mochila {
    
    public static class ElementoSeleccionado {
        private Objeto objeto;
        private double fraccion;

        public ElementoSeleccionado(Objeto objeto, double fraccion) {
            this.objeto = objeto;
            this.fraccion = fraccion;
        }

        public Objeto getObjeto() { return objeto; }
        public double getFraccion() { return fraccion; }
    }

    private double capacidadLimite;
    private String criterio;
    private List<ElementoSeleccionado> contenido;

    public Mochila(double capacidadLimite, String criterio) {
        this.capacidadLimite = capacidadLimite;
        this.criterio = criterio.toLowerCase();
        this.contenido = new ArrayList<>();
    }

    public void llenarMochila(List<Nodo> listaNodos) {
        listaNodos.sort(Comparator.comparingDouble(Nodo::getDistancia));

        double acumuladoActual = 0.0;

        for (Nodo nodo : listaNodos) {
            System.out.println("\nProcesando " + nodo.getNombre() + " (Distancia: " + nodo.getDistancia() + ")...");

            for (Objeto obj : nodo.getObjetos()) {
                if (acumuladoActual >= capacidadLimite) {
                    break;
                }

                double medida = criterio.equals("peso") ? obj.getPeso() : obj.getValor();
                double espacioDisponible = capacidadLimite - acumuladoActual;

                if (medida <= espacioDisponible) {
                    contenido.add(new ElementoSeleccionado(obj, 1.0));
                    acumuladoActual += medida;
                } else {
                    double fraccion = espacioDisponible / medida;
                    contenido.add(new ElementoSeleccionado(obj, fraccion));
                    acumuladoActual += espacioDisponible;
                    break;
                }
            }
        }
    }

    public void mostrarResumen() {
        System.out.println("\n--- RESUMEN DE LA MOCHILA (Criterio: " + criterio.toUpperCase() + ") ---");
        double pesoTotal = 0.0;
        double valorTotal = 0.0;

        for (ElementoSeleccionado elem : contenido) {
            Objeto obj = elem.getObjeto();
            double fraccion = elem.getFraccion();

            double pesoTomado = obj.getPeso() * fraccion;
            double valorTomado = obj.getValor() * fraccion;
            pesoTotal += pesoTomado;
            valorTotal += valorTomado;

            System.out.printf("- %s: Tomado %.1f%% -> Peso: %.2f, Valor: %.2f%n",
                    obj.getNombre(), fraccion * 100, pesoTomado, valorTomado);
        }

        System.out.printf("Total Acumulado -> Peso: %.2f | Valor: %.2f%n", pesoTotal, valorTotal);
    }
}