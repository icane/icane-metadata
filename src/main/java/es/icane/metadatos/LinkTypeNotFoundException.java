package es.icane.metadatos;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
class LinkTypeNotFoundException extends Exception {

    public LinkTypeNotFoundException(Throwable cause) {
        super(cause);
    }

    public LinkTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LinkTypeNotFoundException(String message) {
        super(message);
    }

    public LinkTypeNotFoundException() {
    }
}
