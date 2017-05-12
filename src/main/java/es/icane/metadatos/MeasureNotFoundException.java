package es.icane.metadatos;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */

class MeasureNotFoundException extends Exception {

	public MeasureNotFoundException(Throwable cause) {
		super(cause);
	}

	public MeasureNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MeasureNotFoundException(String message) {
		super(message);
	}

	public MeasureNotFoundException() {
	}
}
