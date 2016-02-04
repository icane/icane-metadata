package es.icane.metadatos.model;

import es.icane.metadatos.adapters.DateAdapter;


import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType
@XmlRootElement(name="measure")
public class Measure implements Serializable {

    private Integer id;
    private String code;
    private String title;
    private String unit;
    private UnitOfMeasure unitOfMeasure;
    private String node;
    private String nodeUriTag;
    private boolean defaultMeasure = false;
    private Integer ordinal;
    private String mapType = null;
    private Date created;
    private Date lastUpdated;


    public Measure() {

    }
    public Measure(String code, String title, UnitOfMeasure unitOfMeasure, String nodeUriTag, boolean defaultMeasure, Integer ordinal, String mapType, Date created, Date lastUpdated) {
        this.code = code;
        this.title = title;
        this.unitOfMeasure = unitOfMeasure;
        this.nodeUriTag = nodeUriTag;
        this.defaultMeasure = defaultMeasure;
        this.ordinal = ordinal;
        this.mapType = mapType;
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

    public String getNodeUriTag() {
        return nodeUriTag;
    }

    public void setNodeUriTag(String nodeUriTag) {
        this.nodeUriTag = nodeUriTag;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isDefaultMeasure() {
        return defaultMeasure;
    }

    public void setDefaultMeasure(boolean defaultMeasure) {
        this.defaultMeasure = defaultMeasure;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.code != null ? this.code.hashCode() : 0);
        hash = 37 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 37 * hash + (this.unit != null ? this.unit.hashCode() : 0);
        hash = 37 * hash + (this.defaultMeasure ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Measure other = (Measure) obj;
        if (this.id != other.id &&
                (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.code == null) ? (other.code != null)
                : !this.code.equals(other.code)) {
            return false;
        }
        if ((this.title == null) ? (other.title != null)
                : !this.title.equals(other.title)) {
            return false;
        }
        if ((this.unit == null) ? (other.unit != null)
                : !this.unit.equals(other.unit)) {
            return false;
        }
        if (this.defaultMeasure != other.defaultMeasure) {
            return false;
        }
        return true;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
        this.nodeUriTag = node;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getMapType() {
        if (mapType != null && mapType.isEmpty()) {
            mapType = null;
        }
        return mapType;
    }

    public void setMapType(String mapType) {
        if (mapType != null && mapType.isEmpty()) {
            this.mapType = null;
        } else {
            this.mapType = mapType;
        }
    }
}
