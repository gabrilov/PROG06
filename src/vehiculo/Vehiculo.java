package vehiculo;

import java.util.Objects;

/**
 * Clase para el POJO vehículo
 * 
 * @author Gabriel Cubillos Rodríguez
 */
public class Vehiculo {

    // Propiedades de instancia
    private Matricula matricula;
    private String marca;
    private String modelo;
    private String color;

    /**
     * Constructor del vehículo, empleando todos sus atributos.
     * 
     * @param matricula
     * @param marca
     * @param modelo
     * @param color 
     */
    public Vehiculo(
            Matricula matricula, String marca, String modelo, String color) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }

    // Getters
    
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    /**
     * En vez de usar un getter para la propiedad de la clase "matricula",
     * usamos otro que nos devuelve directamente un String con el número de
     * la matrícula, y que será el que usemos en las operaciones de gestión
     * de los datos de los vehículos.
     * @return 
     */
    public String getNumeroMatricula() {
        return matricula.getNumero();
    }

    /**
     * Método para facilitar las comparaciones de igualdad entre objetos
     * Vehículo, de forma que dos instancias con la misma matrícula tendrán el
     * mismo hash.
     *
     * @return Un código hash para una instancia de la clase.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash * this.matricula.hashCode();
    }

    /**
     * Método para poder comparar dos instancias de Vehículo en función de sus
     * matrículas, en vez de sus referencias a las posiciones de memoria.
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
        final Vehiculo other = (Vehiculo) obj;
        return Objects.equals(this.matricula, other.matricula);
    }

}
