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
    private String press_note;
    private boolean historical;
    private Subsection subsection;
    private List<Methodology> methodologies;
    private List<RelatedLink> relatedLinks;
    private Date created;
    private Date lastUpdated;

    public DataSet () {

    }

    public DataSet (Integer id, String title, String acronym, String uriTag,
                    String press_note, boolean historical, Subsection subsection,
                    Date created, Date lastUpdated) {
        this.id = id;
        this.title = title;
        this.acronym = acronym;
        this.uriTag = uriTag;
        this.press_note = press_note;
        this.historical = historical;
        this.subsection = subsection;
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

    @XmlElement(name = "dateCreated")
    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @XmlElement(required = true)
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

    @XmlElement(required = true)
    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getPress_note() {
        return press_note;
    }
    
    public void setPress_note(String press_note) {
        this.press_note = press_note;
    }
   
    public boolean isHistorical() {
        return historical;
    }

    public void setHistorical(boolean historical) {
        this.historical = historical;
    }

    public Subsection getSubsection() {
        return subsection;
    }

    public void setSubsection(Subsection subsection) {
        this.subsection = subsection;
    }

    @XmlElementWrapper(name = "methodologies")
    @XmlElement(name = "methodology")
    public List<Methodology> getMethodologies() {
        return methodologies;
    }

    public void setMethodologies(List<Methodology> methodologies) {
        this.methodologies = methodologies;
    }

    @XmlElementWrapper(name = "relatedLinks")
    @XmlElement(name = "relatedLink")
    public List<RelatedLink> getRelatedLinks() {
        return relatedLinks;
    }

    public void setRelatedLinks(List<RelatedLink> relatedLinks) {
        this.relatedLinks = relatedLinks;
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
