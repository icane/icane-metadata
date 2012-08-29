package es.icane.metadatos.modelo;

import es.icane.metadatos.DateAdapter;
import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
@XmlType
public class ProductorFuente {

    private String id;
    private String descripcion;
    private String abreviatura;
    private String enlace;
    private Date created;
    private Date lastUpdated;
    
    @XmlAttribute
    @XmlID
    public String getId() {
        return id;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlJavaTypeAdapter(value = DateAdapter.class)
    @XmlElement(name = "dateCreated")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "ProductorFuente{" + "id=" + id + ", descripcion=" + descripcion + ", abreviatura=" + abreviatura + ", enlace=" + enlace + ", created=" + created + ", lastUpdated=" + lastUpdated + '}';
    }
}
