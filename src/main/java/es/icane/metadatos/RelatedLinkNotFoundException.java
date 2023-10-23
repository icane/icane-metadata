package es.icane.metadatos;

/**
 *
 * @author Servicio de Informática y Banco de Datos <sibd@cantabria.es>
 */

public class RelatedLinkNotFoundException extends Exception {
    public RelatedLinkNotFoundException(Throwable cause) {
        super(cause);
    }

    public RelatedLinkNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RelatedLinkNotFoundException(String message) {
        super(message);
    }

    public RelatedLinkNotFoundException() {
    }
}
