package es.icane.metadatos;

/**
 * Created by emm13775 on 23/12/2015.
 */
class DataProviderNotFoundException extends Exception {

    public DataProviderNotFoundException(Throwable cause) {
        super(cause);
    }

    public DataProviderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataProviderNotFoundException(String message) {
        super(message);
    }

    public DataProviderNotFoundException() {
    }
}
