package es.icane.metadatos.model;

import es.icane.metadatos.adapters.DateAdapter;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
@XmlType
public class TimeSeries implements Serializable {

    private Integer id;
    private String code;
    private String uriTag;
    private String title;
    private int lft, rgt;
    private boolean active;
    private String uri;
    private Date dataUpdate;
    private String documentation;
    private String topics;
    private Subsection subsection;
    private Category category;
    private DataSet dataSet;
    private Periodicity periodicity;
    private String initialPeriod, finalPeriod;
    private List<Source> sources;
    private List<Link> links;
    private ReferenceArea referenceArea;
    private Integer parentId;
    private NodeType nodeType;
    private List<TimeSeries> children;
    private List<ApiUri> apiUris;

    public String getCode() {
        return code;
    }

    public void setCode(String codigo) {
        this.code = codigo;
    }

    @XmlAttribute(required = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(required = true)
    public int getLft() {
        return lft;
    }

    public void setLft(int lft) {
        this.lft = lft;
    }

    @XmlElement(required = true)
    public int getRgt() {
        return rgt;
    }

    public void setRgt(int rgt) {
        this.rgt = rgt;
    }

    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public Date getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(Date dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @XmlElement(required = true)
    public Subsection getSubsection() {
        return subsection;
    }

    public void setSubsection(Subsection subsection) {
        this.subsection = subsection;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @XmlElementWrapper(name = "sources")
    @XmlElement(name = "source")
    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    @XmlElementWrapper(name = "links")
    @XmlElement(name = "link")
    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public ReferenceArea getReferenceArea() {
        return referenceArea;
    }

    public void setReferenceArea(ReferenceArea referenceArea) {
        this.referenceArea = referenceArea;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @XmlElement(name = "finalPeriodDescription")
    public String getFinalPeriod() {
        return finalPeriod;
    }

    public void setFinalPeriod(String finalPeriod) {
        this.finalPeriod = finalPeriod;
    }

    @XmlElement(name = "initialPeriodDescription")
    public String getInitialPeriod() {
        return initialPeriod;
    }

    public void setInitialPeriod(String initialPeriod) {
        this.initialPeriod = initialPeriod;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @XmlElement(required = true)
    public String getUriTag() {
        return uriTag;
    }

    public void setUriTag(String uriTag) {
        this.uriTag = uriTag;
    }

    @XmlElement(required = true)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @XmlElement(required = true)
    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    @XmlElementWrapper(name = "apiUris")
    @XmlElement(name = "apiUri")
    public List<ApiUri> getApiUris() {
        return apiUris;
    }

    public void setApiUris(List<ApiUri> apiUris) {
        this.apiUris = apiUris;
    }

    @Override
    public String toString() {
        return this.title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeSeries other = (TimeSeries) obj;
        if (this.id != other.id
                && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.uriTag == null) ? (other.uriTag != null) : !this.uriTag.
                equals(other.uriTag)) {
            return false;
        }
        if (this.subsection != other.subsection && (this.subsection == null
                || !this.subsection.equals(other.subsection))) {
            return false;
        }
        if (this.category != other.category && (this.category == null
                || !this.category.equals(other.category))) {
            return false;
        }
        if (this.dataSet != other.dataSet && (this.dataSet == null
                || !this.dataSet.equals(other.dataSet))) {
            return false;
        }
        if (this.nodeType != other.nodeType && (this.nodeType == null
                || !this.nodeType.equals(other.nodeType))) {
            return false;
        }
        return true;
    }

    @XmlElementWrapper(name = "children")
    @XmlElement(name = "timeSeries")
    public List<TimeSeries> getChildren() {
        return children;
    }

    public void setChildren(List<TimeSeries> children) {
        this.children = children;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 31 * hash + (this.uriTag != null ? this.uriTag.hashCode() : 0);
        hash = 31 * hash + (this.subsection != null ? this.subsection.hashCode()
                            : 0);
        hash = 31 * hash
                + (this.category != null ? this.category.hashCode() : 0);
        hash = 31 * hash + (this.dataSet != null ? this.dataSet.hashCode() : 0);
        hash = 31 * hash
                + (this.nodeType != null ? this.nodeType.hashCode() : 0);
        return hash;
    }
}
