package es.icane.metadatos.model;

import es.icane.metadatos.adapters.DateAdapter;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
@XmlType
@XmlRootElement(name="dataProvider")
public class DataProvider implements Serializable {

    private Integer id;
    private String title;
    private String dcuoo_id;
    private String acronym;
    private String uri;
    private Date created;
    private Date lastUpdated;

    public DataProvider() {

    }

    public DataProvider(String title, String acronym, String dcuoo_id, String uri, Date created, Date lastUpdated) {
        this.title = title;
        this.acronym = acronym;
        this.dcuoo_id = dcuoo_id;
        this.uri = uri;
        this.created = created;
        this.lastUpdated = lastUpdated;
    }

    @XmlAttribute(required = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDcuoo_id() {
        return dcuoo_id;
    }

    public void setDcuoo_id(String dcuoo_id) {
        this.dcuoo_id = dcuoo_id;
    }

    @Override
    public String toString() {
        if (acronym != null && !acronym.isEmpty()) {
            return title + " (" + acronym + ')';
        }
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DataProvider other = (DataProvider) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        return (this.acronym == null) ? other.acronym == null : this.acronym.equals(other.acronym);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 29 * hash + (this.acronym != null ? this.acronym.hashCode() : 0);
        return hash;
    }
}
