package es.icane.metadatos.modelo;

import es.icane.metadatos.DateAdapter;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
@XmlType
public class AmbitoTerritorial {

    private String id;
    private String descripcion;
    private List<String> enlaces;
    private Date created;
    private Date lastUpdated;

    @XmlAttribute
    @XmlID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "dateCreated")
    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @XmlElementWrapper
    @XmlElement(name = "enlace")
    public List<String> getEnlaces() {
        return enlaces;
    }

    public void setEnlaces(List<String> enlaces) {
        this.enlaces = enlaces;
    }

    @Override
    public String toString() {
        return "AmbitoTerritorial{" + "id=" + id + ", descripcion=" + descripcion + ", enlaces=" + enlaces + ", created=" + created + ", lastUpdated=" + lastUpdated + '}';
    }
}
