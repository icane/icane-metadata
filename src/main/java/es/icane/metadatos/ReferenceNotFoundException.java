package es.icane.metadatos;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
class ReferenceNotFoundException extends Exception {

    public ReferenceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ReferenceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReferenceNotFoundException(String message) {
        super(message);
    }

    public ReferenceNotFoundException() {
    }
}
