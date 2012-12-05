package es.icane.metadatos;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
public class ReferenceAreaNotFoundException extends Exception {

    public ReferenceAreaNotFoundException(Throwable cause) {
        super(cause);
    }

    public ReferenceAreaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReferenceAreaNotFoundException(String message) {
        super(message);
    }

    public ReferenceAreaNotFoundException() {
    }
}
