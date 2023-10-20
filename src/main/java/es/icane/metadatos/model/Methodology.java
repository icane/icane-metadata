package es.icane.metadatos.model;

import es.icane.metadatos.adapters.DateAdapter;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Servicio de Informática y Banco de Datos <sibd@cantabria.es>
 */
@XmlType
@XmlRootElement(name="methodology")
public class Methodology implements Serializable {

    private Integer id;
    private String title;
    private String uri;
    private String group;
    private Integer ordenation;

    private DataSet dataSet;
    private Date created;
    private Date lastUpdated;

    public Methodology() {

    }

    public Methodology (Integer id, String title, String uri, String group,
                        Integer ordenation, DataSet dataSet, Date created, Date lastUpdated) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.group = group;
        this.ordenation = ordenation;
        this.dataSet = dataSet;
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

    @XmlElement(required = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(required = true)
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @XmlElement(required = true)
    public Integer getOrdenation() {
        return ordenation;
    }

    public void setOrdenation(Integer ordenation) {
        this.ordenation = ordenation;
    }

    @XmlElement(required = true)
    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
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

    @Override
    public String toString() {
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
        final Methodology other = (Methodology) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
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
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 79 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 79 * hash + (this.uri != null ? this.uri.hashCode() : 0);
        return hash;
    }

}
