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
@XmlRootElement(name="link")
public class Link implements Serializable {

    private Integer id;
    private String title;
    private String uri;
    private String rdfShortUri;
    private String rdfUri;
    private LinkType linkType;
    private String node;
    private String nodeUriTag;
    private String section;
    private String sectionUriTag;
    private String subsection;
    private String subsectionUriTag;
    private String dataSet;
    private String dataSetUriTag;
    private String referenceArea;
    private String referenceAreaUriTag;
    private Date created;
    private Date lastUpdated;

    public Link() {

    }

    public Link(String title, String uri, LinkType linkType, Date lastUpdated, Date created, TimeSeries node) {
        this.title = title;
        this.uri = uri;
        this.linkType = linkType;
        this.lastUpdated = lastUpdated;
        this.created = created;
        this.nodeUriTag = node.getUriTag();
    }

    public Link(String title, String uri, LinkType linkType, Date lastUpdated, Date created, Section section) {
        this.title = title;
        this.uri = uri;
        this.linkType = linkType;
        this.lastUpdated = lastUpdated;
        this.created = created;
        this.sectionUriTag = section.getUriTag();
    }

    public Link(String title, String uri, LinkType linkType, Date lastUpdated, Date created, Subsection subsection) {
        this.title = title;
        this.uri = uri;
        this.linkType = linkType;
        this.lastUpdated = lastUpdated;
        this.created = created;
        this.subsectionUriTag = subsection.getUriTag();
    }

    public Link(String title, String uri, LinkType linkType, Date lastUpdated, Date created, DataSet dataSet) {
        this.title = title;
        this.uri = uri;
        this.linkType = linkType;
        this.lastUpdated = lastUpdated;
        this.created = created;
        this.dataSetUriTag = dataSet.getUriTag();
    }

    public Link(String title, String uri, LinkType linkType, Date lastUpdated, Date created, ReferenceArea referenceArea) {
        this.title = title;
        this.uri = uri;
        this.linkType = linkType;
        this.lastUpdated = lastUpdated;
        this.created = created;
        this.referenceAreaUriTag = referenceArea.getUriTag();
    }

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

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getNodeUriTag() {
        return nodeUriTag;
    }

    public void setNodeUriTag(String nodeUriTag) {
        this.nodeUriTag = nodeUriTag;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSectionUriTag() {
        return sectionUriTag;
    }

    public void setSectionUriTag(String sectionUriTag) {
        this.sectionUriTag = sectionUriTag;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getSubsectionUriTag() {
        return subsectionUriTag;
    }

    public void setSubsectionUriTag(String subsectionUriTag) {
        this.subsectionUriTag = subsectionUriTag;
    }

    public String getDataSet() {
        return dataSet;
    }

    public void setDataSet(String dataSet) {
        this.dataSet = dataSet;
    }

    public String getDataSetUriTag() {
        return dataSetUriTag;
    }

    public void setDataSetUriTag(String dataSetUriTag) {
        this.dataSetUriTag = dataSetUriTag;
    }

    public String getReferenceArea() {
        return referenceArea;
    }

    public void setReferenceArea(String referenceArea) {
        this.referenceArea = referenceArea;
    }

    public String getReferenceAreaUriTag() {
        return referenceAreaUriTag;
    }

    public void setReferenceAreaUriTag(String referenceAreaUriTag) {
        this.referenceAreaUriTag = referenceAreaUriTag;
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
