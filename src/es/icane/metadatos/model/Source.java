package es.icane.metadatos.model;

import es.icane.metadatos.adapters.DateAdapter;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
@XmlType
public class Source implements Serializable {

    private Integer id;
    private DataProvider dataProvider;
    private DataSet dataSet;
    private String uri;
    private String dataCompilation;
    private Integer relevanceOrder;
    private Date created;
    private Date lastUpdated;
    
    @XmlAttribute
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

    public String getDataCompilation() {
        return dataCompilation;
    }

    public void setDataCompilation(String dataCompilation) {
        this.dataCompilation = dataCompilation;
    }

    public DataProvider getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public Integer getRelevanceOrder() {
        return relevanceOrder;
    }

    public void setRelevanceOrder(Integer relevanceOrder) {
        this.relevanceOrder = relevanceOrder;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Source{" + "id=" + id + ", dataProvider=" + dataProvider + ", dataSet=" + dataSet + ", uri=" + uri + ", dataCompilation=" + dataCompilation + ", relevanceOrder=" + relevanceOrder + ", created=" + created + ", lastUpdated=" + lastUpdated + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Source other = (Source) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.dataProvider != other.dataProvider && (this.dataProvider == null || !this.dataProvider.equals(other.dataProvider))) {
            return false;
        }
        if (this.dataSet != other.dataSet && (this.dataSet == null || !this.dataSet.equals(other.dataSet))) {
            return false;
        }
        if ((this.dataCompilation == null) ? (other.dataCompilation != null) : !this.dataCompilation.equals(other.dataCompilation)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.dataProvider != null ? this.dataProvider.hashCode() : 0);
        hash = 29 * hash + (this.dataSet != null ? this.dataSet.hashCode() : 0);
        hash = 29 * hash + (this.dataCompilation != null ? this.dataCompilation.hashCode() : 0);
        return hash;
    }
}
