package es.icane.metadatos.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */

@XmlType
public class ApiUri implements Serializable {
    private String format;
    private String uri;

    public ApiUri() {

    }

    @XmlAttribute
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @XmlValue
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return format + "(" + uri + ")";
    }
}
