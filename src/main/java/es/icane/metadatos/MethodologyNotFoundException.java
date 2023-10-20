package es.icane.metadatos;

/**
 *
 * @author Servicio de Informática y Banco de Datos <sibd@cantabria.es>
 */
public class MethodologyNotFoundException extends Exception {
    
    public MethodologyNotFoundException(Throwable cause) {
		super(cause);
	}

	public MethodologyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MethodologyNotFoundException(String message) {
		super(message);
	}

	public MethodologyNotFoundException() {
	}

}
