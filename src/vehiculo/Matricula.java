package vehiculo;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta clase es para instanciar un objeto que usaremos en la clase Vehiculo como
 * tipo de una propiedad.
 * Creamos así una propiedad validada para su uso en esa clase.
 * 
 * @author Gabriel Cubillos Rodríguez
 */
public class Matricula {
    /**
     * Expresión regular para filtrar los String que pretendan pasar por
     * matrículas, según las indicaciones del enunciado.
     */
    private final String REGEX_MATRICULA = "^[0-9]{4}[B-DF-HJ-NP-TV-Z]{3}$";
    /**
     * El número (número y letras, se entiende) de matrícula.
     */
    private String numero;

    /**
     * El constructor del objeto matrícula.
     * 
     * @param matriculaString Una cadena con el número de matricula
     * @throws IllegalArgumentException Se lanza esta excepción si el String
     * que se pasa por argumento no se corresponde con el patrón regex de la
     * matrícula.
     */
    public Matricula(String matriculaString)
            throws IllegalArgumentException {
        if (this.esValida(matriculaString)) {
            this.numero = matriculaString;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Obteiene el número de matrícula.
     * 
     * @return el valor de la propiedad "numero".
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Un método auxiliar para comprobar si una cadena se corresponde con
     * la expresión regular para la matrícula.
     * 
     * @param texto Texto que se pone a prueba.
     * @return "true" si la cadena es un valor válido para ser una matrícula.
     */
    private boolean esValida(String texto) {
        Pattern regex = Pattern.compile(REGEX_MATRICULA);
        Matcher m = regex.matcher(texto);
        return m.matches();
    }

    /**
     * Método para facilitar las comparaciones de igualdad entre objetos 
     * Matrícula, de forma que dos instancias con el mismo número tendrán
     * el mismo hash.
     * 
     * @return Un código hash para una instancia de la clase.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash * this.numero.hashCode();
    }

    /**
     * Método para poder comparar dos instancias de Matrícula en función de
     * sus números.
     * 
     * @param obj Otro objeto matrícula con el que se compara.
     * @return Un booleano que indica si existe o no igualdad.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matricula other = (Matricula) obj;
        return Objects.equals(this.numero, other.numero);
    }
    
    
}
