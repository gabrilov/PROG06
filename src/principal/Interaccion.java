package principal;

import java.util.Scanner;
import vehiculo.Matricula;

/**
 *
 * @author Gabriel Cubillos Rodríguez
 */
class Interaccion {

    static String pedirUnTexto(String mensaje) {
        String texto = "";
        boolean hayValidacion = false;
        Scanner sc;
        while (!hayValidacion) {
            System.out.println(mensaje);
            sc = new Scanner(System.in);
            if (sc.hasNextLine()) {
                texto = sc.nextLine();
                if (texto.isBlank() || texto.isEmpty()) {
                    System.out.println("Esto no puede quedar en blanco");
                } else {
                    hayValidacion = true;
                }
            }
        }
        return texto;
    }

    static Matricula pedirMatricula() {
        boolean hayValidacion = false;
        Matricula matricula = null;
        while (!hayValidacion) {
            String texto = pedirUnTexto(
                    "Introduce la matrícula del vehículo (ej. 1234BCD):"
            ).toUpperCase();
            try {
                matricula = new Matricula(texto);
                hayValidacion = true;
            } catch (IllegalArgumentException e) {
                System.out.println("""
                                   El formato de la matrícula no es correcto.
                                   Son necesarios cuatro números seguidos de tres
                                   consonantes del alfabeto inglés (ej. 1234BCD)
                                   """);
            }
        }
        return matricula;
    }
    
     /**
     * Solicita un número al usuario dentro de un rango y lo devuelve tras
     * validar que es correcto.
     *
     * @param mensaje Mensaje que se muestra al usuario al momento de solicitar
     * el dato. Se realizan dos validaciones: si el dato introducido no es de
     * tipo int, o si el valor no está dentro de los márgenes pasados en los
     * parámetros, no se valida el dato y se solicita al usuario que lo
     * introduzca de nuevo.
     * @param minimo Límite inferior del rango.
     * @param maximo Límite superior del rango.
     * @return El número que introduce el usuario.
     */
    static int pedirUnNumero(String mensaje, int minimo, int maximo) {
        boolean hayValidacion = false;
        int input = 0;
        String mensajeDeError = """
                                \nNo has introducido un número dentro de
                                los márgenes. Repetimos.
                                """;
        Scanner sc;
        do {
            System.out.println(mensaje);
            sc = new Scanner(System.in);
            //System.out.println("[" + minimo + "-" + maximo + "]:");
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                // Validamos que el número está en los márgenes del rango
                if (input < minimo || input > maximo) {
                    System.out.println(mensajeDeError);
                } else {
                    hayValidacion = true;
                }
            } else {
                System.out.println(mensajeDeError);
            }
        } while (!hayValidacion);
        return input;
    }
    
     /**
     * Imprime un mensaje debajo de una línea punteada
     *
     * @param mensaje Mensaje a imprimir
     */
    static void imprimirMensaje(String mensaje) {
        System.out.println("-".repeat(100));
        System.out.println(mensaje);
    }
}
