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

    private double capacidadMaxima;
    private List<ElementoSeleccionado> seleccionados;

    public Mochila(double capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.seleccionados = new ArrayList<>();
    }

    public void llenarMochila(List<Objeto> objetos) {
        // Clonar la lista para ordenar sin alterar el orden original ingresado por el usuario
        List<Objeto> copiaObjetos = new ArrayList<>(objetos);
        copiaObjetos.sort(Comparator.comparingDouble(Objeto::getRelacionValorPeso).reversed());

        double pesoActual = 0.0;

        for (Objeto obj : copiaObjetos) {
            if (pesoActual >= capacidadMaxima) {
                break;
            }

            double espacioLibre = capacidadMaxima - pesoActual;

            if (obj.getPeso() <= espacioLibre) {
                seleccionados.add(new ElementoSeleccionado(obj, 1.0));
                pesoActual += obj.getPeso();
            } else {
                double fraccion = espacioLibre / obj.getPeso();
                seleccionados.add(new ElementoSeleccionado(obj, fraccion));
                pesoActual += espacioLibre;
                break;
            }
        }
    }

    public void mostrarResultado(List<Objeto> objetosIngresados) {
        System.out.println("\n OBJETOS INGRESADOS EN EL SISTEMA ");
        for (Objeto obj : objetosIngresados) {
            System.out.printf("- Objeto: %s | Valor: %.2f | Peso: %.2f | Densidad (Valor/Peso): %.2f%n",
                    obj.getNombre(), obj.getValor(), obj.getPeso(), obj.getRelacionValorPeso());
        }

        System.out.println("\n RESULTADO DE LA SELECCION (GREEDY) ");
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
                int pesoParte = (int) Math.round(obj.getPeso() * f);
                int pesoTotalObj = (int) Math.round(obj.getPeso());
                System.out.printf("%d/%d Parte del objeto %s%n", pesoParte, pesoTotalObj, obj.getNombre());
            }
        }
        System.out.printf("Valor total obtenido: %.2f%n", valorTotal);
    }
}