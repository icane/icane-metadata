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
public class Fuente {

    private String id;
    private boolean explotacionMicrodatos;
    private ProductorFuente productor;
    private Estadistica producto;
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

    public ProductorFuente getProductor() {
        return productor;
    }

    public void setProductor(ProductorFuente productor) {
        this.productor = productor;
    }

    public boolean isExplotacionMicrodatos() {
        return explotacionMicrodatos;
    }

    public void setExplotacionMicrodatos(boolean explotacionMicrodatos) {
        this.explotacionMicrodatos = explotacionMicrodatos;
    }

    public Estadistica getProducto() {
        return producto;
    }

    public void setProducto(Estadistica producto) {
        this.producto = producto;
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
        return "Fuente{" + "id=" + id + ", explotacionMicrodatos=" + explotacionMicrodatos + ", productor=" + productor + ", producto=" + producto + ", created=" + created + ", lastUpdated=" + lastUpdated + '}';
    }
}
