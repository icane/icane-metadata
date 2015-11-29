package es.icane.metadatos;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
public class SectionNotFoundException extends Exception {

    public SectionNotFoundException(Throwable cause) {
        super(cause);
    }

    public SectionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SectionNotFoundException(String message) {
        super(message);
    }

    public SectionNotFoundException() {
    }
}
