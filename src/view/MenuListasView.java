package view;

import java.util.Scanner;
import model.structures.ListaSimple;

public class MenuListasView {

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        ListaSimple<String> lista = new ListaSimple<>();
        int opcion = 0;

        do {
            System.out.println("\n=================================");
            System.out.println("      MENÚ - PAPELERÍA     ");
            System.out.println("=================================");
            System.out.println("1. Agregar elemento");
            System.out.println("2. Buscar elemento");
            System.out.println("3. Listar elementos");
            System.out.println("4. Eliminar elemento");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el valor o elemento a agregar: ");
                        String datoAgregar = scanner.nextLine();
                        lista.insertarFinal(datoAgregar);
                        System.out.println(">> ¡Elemento agregado exitosamente!");
                        break;

                    case 2:
                        System.out.print("Ingrese el valor que desea buscar: ");
                        String datoBuscar = scanner.nextLine();
                        String encontrado = lista.buscarPorValor(datoBuscar);

                        if (encontrado != null) {
                            System.out.println(">> ¡Encontrado! El elemento '" + encontrado + "' sí está en la lista.");
                        } else {
                            System.out.println(">> El elemento no se encuentra en la lista.");
                        }
                        break;

                    case 3:
                        System.out.println("\n--- ELEMENTOS DE LA LISTA (Total: " + lista.size() + ") ---");
                        if (lista.estaVacia()) {
                            System.out.println("La lista está vacía actualmente.");
                        } else {
                            lista.listar();
                        }
                        break;

                    case 4:
                        System.out.print("Ingrese el valor y/o el elemento que desea eliminar: ");
                        String datoEliminar = scanner.nextLine();
                        boolean eliminado = lista.eliminar(datoEliminar);

                        if (eliminado) {
                            System.out.println(">> ¡Elemento eliminado correctamente!");
                        } else {
                            System.out.println(">> No se pudo eliminar: el elemento no existe en la lista.");
                        }
                        break;

                    case 5:
                        System.out.println("Saliendo del Sistema de Papelería...");
                        break;

                    default:
                        System.out.println(">> Opción inválida. Por favor, elija un número entre 1 y 5.");
                }
            } else {
                System.out.println(">> Entrada no válida. Ingrese un número entero.");
                scanner.next(); // Limpiar la entrada incorrecta
            }

        } while (opcion != 5);

    }
}