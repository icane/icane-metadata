package es.icane.metadatos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import es.icane.metadatos.adapters.DateAdapter;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
@XmlType
@XmlRootElement(name="timeSeries")
public class TimeSeries implements Serializable {

    private Integer id;
    private String group;
    private String subgroup;
    private String title;
    private int lft;
    private int rgt;
    private Integer parentId;
    private int depth;
    private boolean active;
    private String documentation;
    private String methodology;
    private String mapScope;
    private String topics;
    private String automatizedTopics;
    private String uriTag;
    private String uriTagEs;
    private String description;
    private String theme;
    private String language;
    private String publisher;
    private String license;
    private Date nextUpdate;
    private boolean deprecated;
    private boolean municipalGrouping;
    private String base;
    private boolean lastBase;
    private String sourceData;
    private String sourceUri;
    private String dataCompilation;
    private String sources;
    private TimePeriod initialPeriodComposite;
    private TimePeriod finalPeriodComposite;
    private String initialPeriod;
    private String finalPeriod;
    private String uri;
    private String metadataUri;
    private Date dataUpdate;
    private Date dateCreated;
    private Date lastUpdated;
    private Subsection subsection;
    private Category category;
    private DataSet dataSet;
    private DataProvider dataProvider;
    private Periodicity periodicity;
    private NodeType nodeType;
    private ReferenceArea referenceArea;
        
    private List<TimeSeries> children;
    private List<ApiUri> apiUris;
    private List<Measure> measures;
    private List<Link> links;
    private List<Reference> references;

    @XmlAttribute(required = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getMapScope() {
        return mapScope;
    }

    public void setMapScope(String mapScope) {
        this.mapScope = mapScope;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getAutomatizedTopics() {
        return automatizedTopics;
    }

    public void setAutomatizedTopics(String automatizedTopics) {
        this.automatizedTopics = automatizedTopics;
    }

    @XmlElement(required = true)
    public String getUriTag() {
        return uriTag;
    }

    public void setUriTag(String uriTag) {
        this.uriTag = uriTag;
    }

    @XmlElement(required = true)
    public String getUriTagEs() {
        return uriTagEs;
    }

    public void setUriTagEs(String uriTagEs) {
        this.uriTagEs = uriTagEs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @XmlJavaTypeAdapter(value = DateAdapter.class)
	public Date getNextUpdate() {
		return nextUpdate;
	}

	public void setNextUpdate(Date nextUpdate) {
		this.nextUpdate = nextUpdate;
	}

    public boolean isDeprecated() {
		return deprecated;
	}

	public void setDeprecated(boolean deprecated) {
		this.deprecated = deprecated;
	}

    public boolean isMunicipalGrouping() {
		return municipalGrouping;
	}

	public void setMunicipalGrouping(boolean municipalGrouping) {
		this.municipalGrouping = municipalGrouping;
	}

    public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

    public boolean isLastBase() {
		return lastBase;
	}

	public void setLastBase(boolean lastBase) {
		this.lastBase = lastBase;
	}

    public String getSourceData() {
        return sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    public String getSourceUri() {
        return sourceUri;
    }

    public void setSourceUri(String sourceUri) {
        this.sourceUri = sourceUri;
    }

    public String getDataCompilation() {
        return dataCompilation;
    }

    public void setDataCompilation(String dataCompilation) {
        this.dataCompilation = dataCompilation;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    @XmlElement(name = "initialPeriod")
	public TimePeriod getInitialPeriodComposite() {
		return initialPeriodComposite;
	}
		
	public void setInitialPeriodComposite(TimePeriod initialPeriodComposite) {
		this.initialPeriodComposite = initialPeriodComposite;
	}

	@XmlElement(name = "finalPeriod")
	public TimePeriod getFinalPeriodComposite() {
		return finalPeriodComposite;
	}

	public void setFinalPeriodComposite(TimePeriod finalPeriodComposite) {
		this.finalPeriodComposite = finalPeriodComposite;
	}

    @XmlElement(name = "initialPeriodDescription")
    public String getInitialPeriod() {
        return initialPeriod;
    }

    public void setInitialPeriod(String initialPeriod) {
        this.initialPeriod = initialPeriod;
    }

    @XmlElement(name = "finalPeriodDescription")
    public String getFinalPeriod() {
        return finalPeriod;
    }

    public void setFinalPeriod(String finalPeriod) {
        this.finalPeriod = finalPeriod;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMetadataUri() {
        return metadataUri;
    }

    public void setMetadataUri(String metadataUri) {
        this.metadataUri = metadataUri;
    }

    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public Date getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(Date dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @XmlElement(required = true)
    public Subsection getSubsection() {
        return subsection;
    }

    public void setSubsection(Subsection subsection) {
        this.subsection = subsection;
    }

    @XmlElement(required = true)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @XmlElement(required = true)
    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public DataProvider getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @XmlElement(required = true)
    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public ReferenceArea getReferenceArea() {
        return referenceArea;
    }

    public void setReferenceArea(ReferenceArea referenceArea) {
        this.referenceArea = referenceArea;
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

     /**
     * @return the measures
     */
    @XmlElementWrapper(name = "measures")
    @XmlElement(name = "measure")
    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }
	
	@XmlElementWrapper(name = "links")
    @XmlElement(name = "link")
    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
	    
    @XmlElementWrapper(name = "references")
    @XmlElement(name = "reference")
    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    @XmlElementWrapper(name = "apiUris")
    @XmlElement(name = "apiUri")

    public List<ApiUri> getApiUris() {
        return apiUris;
    }

    public void setApiUris(List<ApiUri> apiUris) {
        this.apiUris = apiUris;
    }

    @XmlElementWrapper(name = "children")
    @XmlElement(name = "timeSeries")
    public List<TimeSeries> getChildren() {
        return children;
    }

    public void setChildren(List<TimeSeries> children) {
        this.children = children;
    }

}
