package mochila.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" SISTEMA DE MOCHILA FRACCIONAL (GREEDY)");
        System.out.print("Ingrese la capacidad maxima de la mochila: ");
        double capacidad = scanner.nextDouble();

        System.out.print("¿Cuantos objetos desea ingresar?: ");
        int cantidad = scanner.nextInt();

        List<Objeto> objetos = new ArrayList<>();

        for (int i = 1; i <= cantidad; i++) {
            System.out.println("\n Datos del Objeto " + i + "..");
            System.out.print("Nombre/Identificador: ");
            String nombre = scanner.next();

            System.out.print("Valor: ");
            double valor = scanner.nextDouble();

            System.out.print("Peso: ");
            double peso = scanner.nextDouble();

            objetos.add(new Objeto(nombre, valor, peso));
        }

        Mochila mochila = new Mochila(capacidad);
        mochila.llenarMochila(objetos);
        mochila.mostrarResultado(objetos);

        scanner.close();
    }
}