package es.icane.metadatos.dto;

import es.icane.metadatos.adapters.DateAdapter;
import es.icane.metadatos.model.*;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by emm13775 on 23/12/15.
 */

@XmlType
@XmlRootElement(name="timeSeries")
public class TimeSeriesDTO implements Serializable {

    private Integer id;
    private String code;
    private String uriTag;
    private String uriTagEs;
    private String title;
    private int lft, rgt, depth;
    private boolean active;
    private Date dataUpdate;
    private String documentation;
    private String methodology;
    private String mapScope;
    private String topics;
    private String description;
    private String theme;
    private String language;
    private String publisher;
    private String license;
    private Date nextUpdate;
    private Date dateCreated;
    private Date lastUpdated;
    private boolean deprecated;
    private boolean municipalGrouping;
    private String base;
    private Subsection subsection;
    private Category category;
    private DataSet dataSet;
    private DataProvider dataProvider;
    private String sourceUri;
    private String dataCompilation;
    private String sourceData;
    private Periodicity periodicity;
    private ReferenceArea referenceArea;
    private TimePeriod initialPeriodComposite;
    private TimePeriod finalPeriodComposite;
    private Integer parentId;
    private NodeType nodeType;

    public TimeSeriesDTO() {

    }

public TimeSeriesDTO(TimeSeries timeSeries){
    this.id = timeSeries.getId();
    this.code = timeSeries.getCode();
    this.uriTag = timeSeries.getUriTag();
    this.uriTagEs = timeSeries.getUriTagEs();
    this.title = timeSeries.getTitle();
    this.lft = timeSeries.getLft();
    this.rgt = timeSeries.getRgt();
    this.depth = timeSeries.getDepth();
    this.active = timeSeries.isActive();
    this.dataUpdate = timeSeries.getDataUpdate();
    this.documentation = timeSeries.getDocumentation();
    this.methodology = timeSeries.getMethodology();
    this.mapScope = timeSeries.getMapScope();
    this.topics = timeSeries.getTopics();
    this.description = timeSeries.getDescription();
    this.theme = timeSeries.getTheme();
    this.language = timeSeries.getLanguage();
    this.publisher = timeSeries.getPublisher();
    this.license = timeSeries.getLicense();
    this.nextUpdate = timeSeries.getNextUpdate();
    this.dateCreated = timeSeries.getDateCreated();
    this.lastUpdated = timeSeries.getLastUpdated();
    this.deprecated = timeSeries.isDeprecated();
    this.municipalGrouping = timeSeries.isMunicipalGrouping();
    this.base = timeSeries.getBase();
    this.subsection = timeSeries.getSubsection();
    this.category = timeSeries.getCategory();
    this.dataSet = timeSeries.getDataSet();
    this.dataProvider = timeSeries.getDataProvider();
    this.sourceUri = timeSeries.getSourceUri();
    this.dataCompilation = timeSeries.getDataCompilation();
    this.sourceData = timeSeries.getSourceData();
    this.periodicity = timeSeries.getPeriodicity();
    this.referenceArea = timeSeries.getReferenceArea();
    this.initialPeriodComposite = timeSeries.getInitialPeriodComposite();
    this.finalPeriodComposite = timeSeries.getFinalPeriodComposite();
    this.parentId = timeSeries.getParentId();
    this.nodeType = timeSeries.getNodeType();

}
    @XmlAttribute(required = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUriTag() {
        return uriTag;
    }

    public void setUriTag(String uriTag) {
        this.uriTag = uriTag;
    }

    public String getUriTagEs() {
        return uriTagEs;
    }

    public void setUriTagEs(String uriTagEs) {
        this.uriTagEs = uriTagEs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @XmlAttribute(required = true)
    public int getLft() {
        return lft;
    }

    public void setLft(int lft) {
        this.lft = lft;
    }
    @XmlAttribute(required = true)
    public int getRgt() {
        return rgt;
    }

    public void setRgt(int rgt) {
        this.rgt = rgt;
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

    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public Date getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(Date dataUpdate) {
        this.dataUpdate = dataUpdate;
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

    @XmlElement(required = true)
    public Subsection getSubsection() {
        return subsection;
    }

    public void setSubsection(Subsection subsection) {
        this.subsection = subsection;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

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

    public String getSourceData() {
        return sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public ReferenceArea getReferenceArea() {
        return referenceArea;
    }

    public void setReferenceArea(ReferenceArea referenceArea) {
        this.referenceArea = referenceArea;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
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
        final TimeSeriesDTO other = (TimeSeriesDTO) obj;
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
}
