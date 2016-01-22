package es.icane.metadatos.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlRootElement(name="resourceType")
public enum ResourceType {
	//TODO : pasar las descripciones de las enumeraciones a un archivo de properties
		PUBLICATION("Publicación"),
		STATISTICAL_OPERATION("Operación estadística"),
		OPENDATA("Correspondencia en portal de datos abiertos"),
		APPLICATION("Aplicación o sistema software asociado"),
		GENERIC("Recurso de información genérico");

		String description;
		
		ResourceType(String description) {
			this.description = description;
		}
		
		@Override
		public String toString() {
			return this.description;
		}
}
