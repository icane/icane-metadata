package es.icane.metadatos;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */

public class PeriodicityNotFoundException extends Exception {

	public PeriodicityNotFoundException(Throwable cause) {
		super(cause);
	}

	public PeriodicityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PeriodicityNotFoundException(String message) {
		super(message);
	}

	public PeriodicityNotFoundException() {
	}
}
