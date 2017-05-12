package es.icane.metadatos;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
class SubsectionNotFoundException extends Exception {

    public SubsectionNotFoundException(Throwable cause) {
        super(cause);
    }

    public SubsectionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubsectionNotFoundException(String message) {
        super(message);
    }

    public SubsectionNotFoundException() {
    }
}
