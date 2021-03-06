package es.icane.metadatos.model;

import es.icane.metadatos.adapters.DateAdapter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
@XmlType
@XmlRootElement(name="dataSet")
public class DataSet implements Serializable {

    private Integer id;
    private String title;
    private String acronym;
    private String uriTag;
   
	
    private Date created;
    private Date lastUpdated;

    @XmlAttribute(required = true)
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @XmlElement(required = true)
    public String getUriTag() {
        return uriTag;
    }

    public void setUriTag(String uriTag) {
        this.uriTag = uriTag;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
    
   

    @Override
    public String toString() {
        return title + " (" + acronym + ')';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DataSet other = (DataSet) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.acronym == null) ? (other.acronym != null) : !this.acronym.equals(other.acronym)) {
            return false;
        }
        if ((this.uriTag == null) ? (other.uriTag != null) : !this.uriTag.equals(other.uriTag)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 31 * hash + (this.acronym != null ? this.acronym.hashCode() : 0);
        hash = 31 * hash + (this.uriTag != null ? this.uriTag.hashCode() : 0);
        return hash;
    }
}
