package es.icane.metadatos;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
class DataSetNotFoundException extends Exception {

    public DataSetNotFoundException(Throwable cause) {
        super(cause);
    }

    public DataSetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSetNotFoundException(String message) {
        super(message);
    }

    public DataSetNotFoundException() {
    }
}
