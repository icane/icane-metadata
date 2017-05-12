package es.icane.metadatos;

/**
 * Created by emm13775 on 1/04/16.
 */
public class NodeTypeNotFoundException extends Exception {
    public NodeTypeNotFoundException(Throwable cause) {
        super(cause);
    }

    public NodeTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NodeTypeNotFoundException(String message) {
        super(message);
    }

    public NodeTypeNotFoundException() {
    }
}