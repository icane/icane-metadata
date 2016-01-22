package es.icane.metadatos;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
public class LinkNotFoundException extends Exception {

    public LinkNotFoundException(Throwable cause) {
        super(cause);
    }

    public LinkNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LinkNotFoundException(String message) {
        super(message);
    }

    public LinkNotFoundException() {
    }
}
