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
@XmlRootElement(name="subsection")
public class Subsection implements Serializable {

    private Integer id;
    private String title;
    private String acronym;
    private String uriTag;
    private String uri;
    private String topics;
    private String automatizedTopics;

    private String code;
    private Section section;
    private List<Link> links;
    private Date created;
    private Date lastUpdated;

    public Subsection () {

    }

    public Subsection (Integer id, String title, String acronym, String uriTag, String uri, String topics,
                       String automatizedTopics, String code, Section section, Date created, Date lastUpdated) {
        this.id = id;
        this.title = title;
        this.acronym = acronym;
        this.uriTag = uriTag;
        this.uri = uri;
        this.topics = topics;
        this.automatizedTopics = automatizedTopics;
        this.code = code;
        this.section = section;
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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
    
    @XmlElementWrapper(name = "links")
    @XmlElement(name = "link")
    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAutomatizedTopics() {
        return automatizedTopics;
    }

    public void setAutomatizedTopics(String automatizedTopics) {
        this.automatizedTopics = automatizedTopics;
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
        final Subsection other = (Subsection) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        if ((this.uriTag == null) ? (other.uriTag != null) : !this.uriTag.equals(other.uriTag)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 79 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 79 * hash + (this.uriTag != null ? this.uriTag.hashCode() : 0);
        return hash;
    }
}
