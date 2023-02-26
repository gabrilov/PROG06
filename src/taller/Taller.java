package taller;

import vehiculo.Matricula;
import vehiculo.Vehiculo;

/**
 * Esta clase implementa los métodos para almacenar los datos de vehículos y
 * reparaciones en un taller. Por un lado, instancia objetos Taller individuales
 * y por otro, dispone de propiedades y métodos estáticos para las operaciones
 * relacionadas con el estado de la información que guarda el taller.
 * 
 * @author Gabriel Cubillos Rodríguez
 */
public class Taller {

    /**
     * Máximas reparaciones que se admiten en cada vehículo.
     */
    public static final int REPARACIONES_MAXIMAS = 3;
    /**
     * Número máximo de vehículos que se admite en el taller.
     */
    public static final int VEHICULOS_MAXIMOS = 5;
    /**
     * Esta es una variable fundamental para el proyecto. Se trata de un
     * registro que guarda todos los datos de los vehículos en el taller junto
     * con sus reparaciones. Es decir, es una base de datos con una serie de
     * datos con un índice único: la matrícula.
     * 
     * Los métodos más importantes de esta clase realizarán operaciones CRUD, 
     * esto es, de adición, actualización, de consulta y de supresión de elementos.
     *
     * Toda esta información se guarda en este array, en una propiedad de
     * clase.
     */
    private static final Taller[] REGISTRO = new Taller[VEHICULOS_MAXIMOS];

    /**
     * Propiedad de instancia correspondiente al vehículo y sus características.
     */
    private Vehiculo vehiculo;
    /**
     * Propiedad de instancia: Array que contiene reparaciones.
     */
    private final String[] reparaciones;

    /**
     * Constructor de la clase. Inicializamos las reparaciones.
     */
    public Taller() {
        this.reparaciones = new String[REPARACIONES_MAXIMAS];
    }

    /**
     * Obtiene el valor de las variable "reparaciones".
     *
     * @return la propiedad "reparaciones"
     */
    public String[] getReparaciones() {
        return reparaciones;
    }

    /**
     * Setter para la propiedad vehículo. Como el enunciado impide que se
     * instancie en el método Main, lo instanciamos en este método.
     *
     * @param matricula Matrícula del vehículo.
     * @param marca Marca del vehículo.
     * @param modelo Modelo del vehículo.
     * @param color Color del vehículo.
     */
    public void setVehiculo(
            Matricula matricula, String marca, String modelo, String color) {
        this.vehiculo = new Vehiculo(matricula, marca, modelo, color);
    }

    /**
     * Se trata de un setter para la propiedad "reparaciones". Como en ningún
     * caso se va a agregar más de una reparación en la misma operación, el
     * parámetro que se pasa en un String y no un array de cadenas. Este String
     * se agrega en la primera posición libre de la propiedad.
     *
     * @param reparacion Nombre de la reparación.
     * @return Un valor "true" si se ha producido la inserción en el array.
     */
    public boolean setReparacion(String reparacion) {
        int index = 0;
        while (index < this.reparaciones.length) {
            if (this.reparaciones[index] == null) {
                this.reparaciones[index] = reparacion;
                return true;
            }
            index++;
        }
        return false;
    }

    /* ------------------------------------------ */
 /* METODOS DE CONSULTA DEL ESTADO DE LA CLASE */
 /* ------------------------------------------ */
    /**
     * Este método informa si ya no hay espacio para más reparaciones.
     *
     * @return "true" si no hay espacio para más reparaciones.
     */
    public boolean estanReparacionesLlenas() {
        return numeroDeReparaciones() == REPARACIONES_MAXIMAS;
    }

    /**
     * Este método informa si ya no hay más espacio en el taller para nuevos
     * vehículos.
     *
     * @return "true" si no hay más espacio.
     */
    public static boolean estaTallerLleno() {
        return numeroDeVehiculosRegistrados() == VEHICULOS_MAXIMOS;
    }

    /**
     * Informa si existe un vehículo en el registro del taller.
     *
     * @param matricula Matrícula del vehículo a buscar.
     * @return "true" si existe.
     */
    public static boolean existeVehiculo(Matricula matricula) {
        for (Taller t : REGISTRO) {
            if (t != null
                    && t.vehiculo.getNumeroMatricula()
                            .equals(matricula.getNumero())) {
                return true;
            }
        }
        return false;
    }

    /* --------------------------------------------- */
 /* OPERACIONES CRUD SOBRE EL REGISTRO DEL TALLER */
 /* --------------------------------------------- */
    /**
     * Método de clase que busca en el REGISTRO una matrícula y devuelve el
     * vehículo que coincide con esa matrícula, junto con sus reparaciones.
     *
     * @param matricula Matrícula del vehículo a buscar.
     * @return Un objeto Taller o "null" si no se encuentra nada.
     */
    public static Taller buscarEnRegistroPorMatricula(Matricula matricula) {
        int index = 0;
        while (index < REGISTRO.length) {
            if (REGISTRO[index] != null
                    && REGISTRO[index].vehiculo.getNumeroMatricula()
                            .equals(matricula.getNumero())) {
                return REGISTRO[index];
            }
            index++;
        }
        return null;
    }

    /**
     * Agrega un vehículo al registro del taller.
     *
     * @return Devuelve un valor "true" si la inserción se realiza
     * correctamente.
     */
    public boolean addRegistro() {
        int index = 0;
        while (index < REGISTRO.length) {
            if (REGISTRO[index] == null) {
                REGISTRO[index] = this;
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * Actualiza los datos de un vehículo y sus reparaciones en el registro del
     * taller.
     */
    public void updateTallerToRegistro() {
        int index = obtenerIndiceDelVehiculo();
        REGISTRO[index] = this;
    }

    /**
     * Elimina un vehículo del REGISTRO.
     *
     * @param matricula Matrícula del vehículo a eliminar.
     * @return "True" si el vehículo existe en el taller y se realiza la
     * elminacíon. Si el vehículo no existe, devuelve "false".
     */
    public static boolean removeVehiculo(Matricula matricula) {
        Taller vehiculo = buscarEnRegistroPorMatricula(matricula);
        if (vehiculo == null) {
            return false;
        }
        int index = vehiculo.obtenerIndiceDelVehiculo();
        REGISTRO[index] = null;
        ordenarPosiciones();
        return true;
    }

    /* ------------------------------ */
 /* METODOS AUXILIARES DE LA CLASE */
 /* ------------------------------ */
    /**
     * Obtiene el índice en el registro del último espacio ocupado por un
     * vehículo.
     *
     * @return Índice del elemento en el array del registro.
     */
    private int obtenerIndiceDelVehiculo() {
        int index = 0;
        while (index < REGISTRO.length) {
            if (REGISTRO[index] == this) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Cuenta el número de reparaciones que tiene el vehículo del objeto Taller
     * instanciado.
     *
     * @return Número de reparaciones de un vehículo.
     */
    private int numeroDeReparaciones() {
        int numeroDeReparaciones = 0;
        for (Taller t : REGISTRO) {
            if (t != null && t.vehiculo == this.vehiculo) {
                for (String reparacion : t.reparaciones) {
                    if (reparacion != null) {
                        numeroDeReparaciones++;
                    }
                }
            }
        }
        return numeroDeReparaciones;
    }

    /**
     * Método estático que devuelve el número de vehículos en el REGISTRO.
     *
     * @return Un entero que indica el número de posiciones del array que no son
     * nulas.
     */
    private static int numeroDeVehiculosRegistrados() {
        int numeroDeVehiculos = 0;
        for (Taller t : REGISTRO) {
            if (t != null) {
                numeroDeVehiculos++;
            }
        }
        return numeroDeVehiculos;
    }

    /**
     * Ordena las posiciones del registro conforme indica el enunciado, de forma
     * que si existen posiciones nulas se ubicarán siempre al final del array.
     */
    private static void ordenarPosiciones() {
        for (int i = 1; i < REGISTRO.length; i++) {
            if (REGISTRO[i - 1] == null && REGISTRO[i] != null) {
                REGISTRO[i - 1] = REGISTRO[i];
                REGISTRO[i] = null;
            }
        }
    }

    /* ----------------------------------------- */
 /* METODOS toString PARA MOSTRAR INFORMACION */
 /* ----------------------------------------- */
    /**
     * Crea una cadena con información sobre todos los vehículos del REGISTRO y
     * sus reparaciones.
     *
     * @return Un String con la información.
     */
    public String registroTallerToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < REGISTRO.length; i++) {
            if (REGISTRO[i] != null) {
                sb.append("\nVehículo ")
                        .append(i + 1)
                        .append(" en el taller:\n")
                        .append(REGISTRO[i].toString());
            }
        }
        return sb.toString();
    }

    /**
     * Crea una cadena con información sobre todas las reparaciones.
     *
     * @return Un String con la información.
     */
    private String reparacionesToString() {
        StringBuilder sb = new StringBuilder();
        if (this.reparaciones[0] == null) {
            sb.append("Este vehículo no tiene reparaciones\n");
        } else {
            sb.append("Reparaciones:\n");
            for (int i = 0; i < numeroDeReparaciones(); i++) {
                sb.append(String.format("""
                        Reparación %d: %s
                      """,
                        i + 1, this.reparaciones[i]));
            }
        }
        return sb.toString();
    }

    /**
     * Crea una cadena con información sobre la instancia de la clase: un
     * vehículo y las reparaciones.
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("""
                                Matrícula: %s
                                Marca: %s
                                Modelo: %s
                                Color: %s
                             """,
                this.vehiculo.getNumeroMatricula(),
                this.vehiculo.getMarca(),
                this.vehiculo.getModelo(),
                this.vehiculo.getColor()));
        sb.append(reparacionesToString());
        return sb.toString();
    }

}
