package es.icane.metadatos.model;

import es.icane.metadatos.adapters.DateAdapter;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
public class Link implements Serializable {

    private Integer id;
    private String title;
    private String rdfShortUri;
    private String rdfUri;
    private String uri;
    private Date created;
    private Date lastUpdated;

    @XmlAttribute
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @XmlAttribute
    public String getRdfShortUri() {
        return rdfShortUri;
    }

    public void setRdfShortUri(String rdfShortUri) {
        this.rdfShortUri = rdfShortUri;
    }

    @XmlAttribute
    public String getRdfUri() {
        return rdfUri;
    }

    public void setRdfUri(String rdfUri) {
        this.rdfUri = rdfUri;
    }

    @XmlElement(required = true)
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @XmlElement(required = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return uri;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Link other = (Link) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        if ((this.rdfShortUri == null) ? (other.rdfShortUri != null) : !this.rdfShortUri.equals(other.rdfShortUri)) {
            return false;
        }
        if ((this.rdfUri == null) ? (other.rdfUri != null) : !this.rdfUri.equals(other.rdfUri)) {
            return false;
        }
        if ((this.uri == null) ? (other.uri != null) : !this.uri.equals(other.uri)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 29 * hash + (this.rdfShortUri != null ? this.rdfShortUri.hashCode() : 0);
        hash = 29 * hash + (this.rdfUri != null ? this.rdfUri.hashCode() : 0);
        hash = 29 * hash + (this.uri != null ? this.uri.hashCode() : 0);
        return hash;
    }
}
