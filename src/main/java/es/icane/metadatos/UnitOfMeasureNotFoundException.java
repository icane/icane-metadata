package es.icane.metadatos;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */

class UnitOfMeasureNotFoundException extends Exception {

	public UnitOfMeasureNotFoundException(Throwable cause) {
		super(cause);
	}

	public UnitOfMeasureNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnitOfMeasureNotFoundException(String message) {
		super(message);
	}

	public UnitOfMeasureNotFoundException() {
	}
}
