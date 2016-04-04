package es.icane.metadatos;

/**
 * Created by emm13775 on 1/04/16.
 */
public class TimePeriodNotFoundException extends Exception {
    public TimePeriodNotFoundException(Throwable cause) {
        super(cause);
    }

    public TimePeriodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimePeriodNotFoundException(String message) {
        super(message);
    }

    public TimePeriodNotFoundException() {
    }
}