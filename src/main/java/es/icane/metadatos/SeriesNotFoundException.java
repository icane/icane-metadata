package es.icane.metadatos;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
class SeriesNotFoundException extends Exception {

    public SeriesNotFoundException(Throwable cause) {
        super(cause);
    }

    public SeriesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeriesNotFoundException(String message) {
        super(message);
    }

    public SeriesNotFoundException() {
    }
}
