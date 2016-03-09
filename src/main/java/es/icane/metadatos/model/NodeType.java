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
@XmlRootElement(name="nodeType")
public class NodeType implements Serializable {
    //

    private static final String SERIES = "time-series";
    private static final String THEME = "theme";
    private static final String FOLDER = "folder";
    private static final String DATA_SET = "data-set";
    private static final String DOCUMENT = "document";
    private static final String NON_OLAP_NATIVE = "non-olap-native";
    //
    private Integer id;
    private String title;
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

    @Override
    public String toString() {
        return uriTag;
    }

    public boolean isSeries() {
        return SERIES.equals(uriTag);
    }

    public boolean isTheme() {
        return THEME.equals(uriTag);
    }

    public boolean isDocument() {
        return DOCUMENT.equals(uriTag);
    }

    public boolean isFolder() {
        return FOLDER.equals(uriTag);
    }

    public boolean isDataSet() {
        return DATA_SET.equals(uriTag);
    }

    public boolean isNonOlapNative() {
        return NON_OLAP_NATIVE.equals(uriTag);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NodeType other = (NodeType) obj;
        return (this.uriTag == null) ? other.uriTag == null : this.uriTag.equals(other.uriTag);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.uriTag != null ? this.uriTag.hashCode() : 0);
        return hash;
    }
}
