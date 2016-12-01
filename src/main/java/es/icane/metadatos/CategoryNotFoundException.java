package es.icane.metadatos;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException(Throwable cause) {
        super(cause);
    }

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException() {
    }
}
