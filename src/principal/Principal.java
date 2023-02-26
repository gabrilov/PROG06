package principal;

import taller.Taller;
import vehiculo.Matricula;

/**
 * Clase principal con el método main. En esta clase se implementa la
 * interacción con el usuario en pantalla y se llama a los métodos
 * correspondientes según las opciones que elija.
 *
 * @author Gabriel Cubillos Rodríguez
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean continua = true;
        while (continua) {
            System.out.println("*".repeat(20));
            String textoMenu = """
                           \nBienvenido a mi taller. Elige tu opción.
                           1.- Nuevo vehículo
                           2.- Listado de vehículos
                           3.- Buscar vehículo
                           4.- Nueva reparación
                           5.- Listado de reparaciones
                           6.- Eliminar vehículo
                           0.- Salir
                           """;

            int opcion = Interaccion.pedirUnNumero(textoMenu, 0, 6);
            Taller taller = new Taller();

            switch (opcion) {
                // SALIR DEL PROGRAMA
                case 0 -> {
                    System.out.println("Has elegido salir. ¡Adiós!");
                    continua = false;
                }
                // NUEVO VEHICULO
                case 1 -> {
                    // Primera validación: ¿está el taller lleno?
                    if (Taller.estaTallerLleno()) {
                        System.out.println("El taller está lleno, no cabe un coche más.");
                    } else {
                        Interaccion.imprimirMensaje("Nuevo vehículo");
                        Matricula matricula = Interaccion.pedirMatricula();
                        // Segunda validación: ¿Está este coche en el taller?
                        if (Taller.existeVehiculo(matricula)) {
                            System.out.println("Este coche ya está en el taller.");
                        } else {
                            String marca = Interaccion.pedirUnTexto("Marca del vehículo:");
                            String modelo = Interaccion.pedirUnTexto("Modelo del vehículo:");
                            String color = Interaccion.pedirUnTexto("Color del vehículo:");
                            taller.setVehiculo(matricula, marca, modelo, color);
                            // Aprovechamos que addRegistro() devuelve un booleano si hay éxito
                            System.out.println(taller.addRegistro()
                                    ? "Vehículo añadido al taller"
                                    : "Ha ocurrido un error y no se ha añadido");
                        }
                    }
                }
                // LISTADO DE VEHÍCULOS
                case 2 ->
                    System.out.println(taller.registroTallerToString());
                // BUSCAR VEHÍCULO
                case 3 -> {
                    System.out.println("Búsqueda de vehículo");
                    Matricula matricula = Interaccion.pedirMatricula();
                    taller = Taller.buscarEnRegistroPorMatricula(matricula);
                    if (taller != null) {
                        System.out.println("\nResultado:");
                        System.out.println(taller.toString());
                    } else {
                        System.out.println("No se encuentra el vehículo");
                    }
                }
                // NUEVA REPARACIÓN
                /*
                La actualización del vehículo en el REGISTRO se realiza así:
                - Buscamos el objeto taller con la matrícula indicada en el 
                array REGISTRO y lo copiamos a la variable "taller".
                - Agregamos a la propiedad reparaciones[] de "taller" una
                nueva reparación.
                - Actualizamos el objeto en el array REGISTRO: buscamos el lugar
                que ocupaba originalmente y sobreescribimos la posición con el
                objeto "taller" que acabamos de modificar.
                 */
                case 4 -> {
                    Matricula matricula = Interaccion.pedirMatricula();
                    taller = Taller.buscarEnRegistroPorMatricula(matricula);
                    // Primera validación: ¿Está el vehículo en el taller?
                    if (taller == null) {
                        System.out.println("\nEse vehículo no está en el taller");
                        // Segunda validación: ¿Hay lugar para una reparación más?
                    } else if (taller.estanReparacionesLlenas()) {
                        System.out.println("Este coche ya tiene el máximo de reparaciones");
                    } else {
                        String reparacion = Interaccion.pedirUnTexto(
                                "Introduce el nombre de la reparación a añadir:");
                        taller.setReparacion(reparacion);
                        taller.updateTallerToRegistro();
                        System.out.println("Reparación añadida");
                    }
                }
                // LISTADO DE REPARACIONES
                case 5 -> {
                    Matricula matricula = Interaccion.pedirMatricula();
                    taller = Taller.buscarEnRegistroPorMatricula(matricula);
                    // Validamos que devolvió la búsqueda
                    System.out.println(taller == null
                            ? "\nEse vehículo no está en el taller"
                            : taller.toString());
                }
                // ELIMINAR VEHÍCULO
                case 6 -> {
                    Matricula matricula = Interaccion.pedirMatricula();
                    // Usamos el booleano que devuelve el método para informar
                    // sobre el resultado
                    System.out.println(Taller.removeVehiculo(matricula)
                            ? "Vehículo eliminado"
                            : "Este vehículo no existe");
                }
            }
        }
    }
}
