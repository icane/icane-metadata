package es.icane.metadatos;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import es.icane.metadatos.model.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Metadata Client is used for accessing ICANE's Web Service methods.
 *
 * In a production environment, only uriTags should be used in all methods
 * (instead of numeric id's).
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
public class MetadataClient {

    private Client jerseyClient;
    private ClientConfig clientConfig;
    private WebResource webResource;
    private long connectionTimeout;
    private final String baseUrl;
    // Constants
    private final MediaType defaultMediaType = MediaType.APPLICATION_XML_TYPE;

    /**
     * Constructor.
     *
     * @param baseUrl base URL for the Web Service
     */
    public MetadataClient(String baseUrl) {
        this.baseUrl = baseUrl;
        clientConfig = new DefaultClientConfig();
        clientConfig.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 10000);
        jerseyClient = Client.create(clientConfig);
        webResource = jerseyClient.resource(baseUrl);
    }

    /**
     * Returns this client's base URL.
     * @return this client's base URL.
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * Returns this client's connection timeout, in milliseconds.
     *
     * The client will wait this long before timing out when connecting to the Web Service.
     *
     * @return this client's connection timeout, in milliseconds
     */
    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * Sets the connection timeout for this client.
     *
     * The client will wait this long before timing out when connecting to the Web Service.
     *
     * @param connectionTimeout the new connection timeout, in milliseconds
     * @return
     */
    public MetadataClient setConnectionTimeout(long connectionTimeout) {
        if (connectionTimeout < 0) {
            throw new IllegalArgumentException("connectionTimeout must be non-negative");
        }
        clientConfig.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, connectionTimeout);
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    /*
     * Time Series methods
     */
    /**
     * Retrieve a TimeSeries object by its section, subsection and category.
     *
     * @param category the TimeSeries category's uriTag
     * @param section the TimeSeries section's uriTag
     * @param subsection the TimeSeries subsection's uriTag
     * @param idOrUriTag either the String value of the TimeSeries numeric id
     * @return the TimeSeries object
     * @throws SeriesNotFoundException
     */
    public TimeSeries getTimeSeries(String category, String section, String subsection, String idOrUriTag) throws SeriesNotFoundException {
        try {
            return webResource.path(category).path(section).path(subsection).path(idOrUriTag).accept(defaultMediaType).get(TimeSeries.class);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

     /**
     * Retrieve a TimeSeries object by its section, subsection and category, including inactive series.
     *
     * @param category the TimeSeries category's uriTag
     * @param section the TimeSeries section's uriTag
     * @param subsection the TimeSeries subsection's uriTag
     * @param idOrUriTag either the String value of the TimeSeries numeric id
     * @param inactive if set to true, inactive series will be retrieved too
     * @return the TimeSeries object
     * @throws SeriesNotFoundException
     */
    public TimeSeries getTimeSeries(String category, String section, String subsection, String idOrUriTag, Boolean inactive) throws SeriesNotFoundException {
        try {
            MultivaluedMap queryParams = new MultivaluedMapImpl();
            queryParams.add("inactive", inactive.toString());
            return webResource.path(category).path(section).path(subsection).path(idOrUriTag).queryParams(queryParams).accept(defaultMediaType).get(TimeSeries.class);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Retrieve a TimeSeries object by its section, subsection and category.
     *
     * @param category the TimeSeries category
     * @param section the TimeSeries section
     * @param subsection the TimeSeries subsection
     * @param idOrUriTag either the String value of the TimeSeries numeric id
     * @return the TimeSeries object
     * @throws SeriesNotFoundException
     */
    public TimeSeries getTimeSeries(Category category, Section section, Subsection subsection, String idOrUriTag) throws SeriesNotFoundException {
        try {
            return webResource.path(category.getUriTag()).path(section.getUriTag()).path(subsection.getUriTag()).path(idOrUriTag).accept(defaultMediaType).get(TimeSeries.class);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Retreive a TimeSeries object by its section, subsection, category and dataSet
     * @param category the TimeSeries category's uriTag
     * @param section the TimeSeries section's uriTag
     * @param subsection the TimeSeries subsection's uriTag
     * @param dataSet the TimeSeries dataSet's uriTag
     * @param idOrUriTag either the String value of the TimeSeries numeric id
     * @return
     * @throws SeriesNotFoundException
     */
    public TimeSeries getTimeSeries(String category, String section, String subsection, String dataSet, String idOrUriTag) throws SeriesNotFoundException {
        try {
            return webResource.path(category).path(section).path(subsection).path(dataSet).path(idOrUriTag).accept(defaultMediaType).get(TimeSeries.class);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Retreive a TimeSeries object by its section, subsection, category and dataSet
     * @param category the TimeSeries category
     * @param section the TimeSeries section
     * @param subsection the TimeSeries subsection
     * @param dataSet the TimeSeries dataSet
     * @param idOrUriTag either the String value of the TimeSeries numeric id
     * @return
     * @throws SeriesNotFoundException
     */
    public TimeSeries getTimeSeries(Category category, Section section, Subsection subsection, DataSet dataSet, String idOrUriTag) throws SeriesNotFoundException {
        try {
            return webResource.path(category.getUriTag()).path(section.getUriTag()).path(subsection.getUriTag()).path(dataSet.getUriTag()).path(idOrUriTag).accept(defaultMediaType).get(TimeSeries.class);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Returns a TimeSeries, given its numeric id.
     *
     * This method is not guaranteed to work in a production environment.
     *
     * @param timeSeriesId the numeric id of the TimeSeries
     * @return the TimeSeries object with that id
     * @throws SeriesNotFoundException
     */
    public TimeSeries getTimeSeries(int timeSeriesId) throws SeriesNotFoundException {
        try {
            return webResource.path("time-series").path(String.valueOf(timeSeriesId)).accept(defaultMediaType).get(TimeSeries.class);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Returns a TimeSeries, given its URI tag.
     *
     * @param uriTag the URI tag of the TimeSeries
     * @return the TimeSeries object with that URI tag
     * @throws SeriesNotFoundException
     */
    public TimeSeries getTimeSeries(String uriTag) throws SeriesNotFoundException {
        try {
            return webResource.path("time-series").path(uriTag).accept(defaultMediaType).get(TimeSeries.class);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    public List<TimeSeries> getTimeSeriesBySectionAndCategory(String category, String section) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(category).path(section).path("time-series-list").accept(defaultMediaType).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    public List<TimeSeries> getTimeSeriesList(Category category, Section section) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(category.getUriTag()).path(section.getUriTag()).path("time-series-list").accept(defaultMediaType).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    public List<TimeSeries> getTimeSeriesBySubsection(String section, String subsection) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(section).path(subsection).path("time-series-list").accept(defaultMediaType).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    public List<TimeSeries> getTimeSeriesList(Section section, Subsection subsection) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        List<TimeSeries> timeSeriesList = new ArrayList<TimeSeries>();
        for (Category category : getCategories()) {
            timeSeriesList.addAll(getTimeSeriesList(category, section, subsection));
        }
        return timeSeriesList;
    }

    /**
     * Get a List of all the time series belonging to a given category.
     * @param category the category's uriTag
     * @return a List with all the time series matching the criteria
     * @throws SeriesNotFoundException
     */
    public List<TimeSeries> getTimeSeriesByCategory(String category) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(category).path("time-series-list").accept(defaultMediaType).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Get a List of all the time series belonging to a given section, subsection and category.
     * @param category the category
     * @param section the section
     * @param subsection the subsection
     * @return a List with all the time series matching the criteria
     * @throws SeriesNotFoundException
     */
    public List<TimeSeries> getTimeSeriesList(Category category, Section section, Subsection subsection) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(category.getUriTag()).path(section.getUriTag()).path(subsection.getUriTag()).path("time-series-list").accept(defaultMediaType).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Get a List of all the time series belonging to a given section, subsection and category (by their uriTags).
     * @param category the category's uriTag
     * @param section the section's uriTag
     * @param subsection the subsection's uriTag
     * @return a List with all the time series matching the criteria
     * @throws SeriesNotFoundException
     */
    public List<TimeSeries> getTimeSeriesList(String category, String section, String subsection) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(category).path(section).path(subsection).path("time-series-list").accept(defaultMediaType).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Get the parent of a given TimeSeries
     * @param timeSeries the TimeSeries whose parent to retrieve
     * @return its parent TimeSeries
     * @throws SeriesNotFoundException
     */
    public TimeSeries getParent(TimeSeries timeSeries) throws SeriesNotFoundException {
        try {
            return webResource.path(timeSeries.getCategory().getUriTag()).path(timeSeries.getSubsection().getSection().getUriTag()).path(timeSeries.getSubsection().getUriTag()).path(timeSeries.getUriTag()).path("parent").accept(defaultMediaType).get(TimeSeries.class);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Get all the ancestors of a given TimeSeries
     * @param timeSeries the TimeSeries whose ancestors to retrieve
     * @return a List with all its ancestors
     * @throws SeriesNotFoundException
     */
    public List<TimeSeries> getAncestors(TimeSeries timeSeries) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(timeSeries.getCategory().getUriTag()).path(timeSeries.getSubsection().getSection().getUriTag()).path(timeSeries.getSubsection().getUriTag()).path(timeSeries.getUriTag()).path("parents").accept(defaultMediaType).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    // Section methods
    /**
     * Retrieves a List of all the Sections.
     * @return a List of all the available Sections
     */
    public List<Section> getSections() {
        GenericType<List<Section>> genericType = new GenericType<List<Section>>() {
        };
        return webResource.path("sections").accept(defaultMediaType).get(genericType);
    }

    /**
     * Get a Map of all the sections, keyed by uriTag.
     * @return a Map with all the sections
     */
    public Map<String, Section> getSectionMap() {
        List<Section> sections = getSections();
        Map<String, Section> sectionMap = new LinkedHashMap<String, Section>();
        for (Section s : sections) {
            sectionMap.put(s.getUriTag(), s);
        }
        return sectionMap;
    }

    /**
     * Retrieves a Section by its URI tag.
     * @param uriTag the Section's URI tag to search for
     * @return the corresponding Section
     * @throws SectionNotFoundException
     */
    public Section getSection(String uriTag) throws SectionNotFoundException {
        try {
            return webResource.path("section").path(uriTag).accept(defaultMediaType).get(Section.class);
        } catch (UniformInterfaceException e) {
            throw new SectionNotFoundException();
        }
    }

    /*
     * Subsection methods
     */
    /**
     * Get a list of all the subsections of a given section.
     *
     * @param section the section's uriTag
     * @return a list of its subsections
     */
    public List<Subsection> getSubsections(String section) {
        GenericType<List<Subsection>> genericType = new GenericType<List<Subsection>>() {
        };
        return webResource.path("section").path(section).path("subsections").accept(defaultMediaType).get(genericType);
    }

    /**
     * Get a list of all the subsections of a given section.
     *
     * @param section the section
     * @return a list of its subsections
     */
    public List<Subsection> getSubsections(Section section) {
        GenericType<List<Subsection>> genericType = new GenericType<List<Subsection>>() {
        };
        return webResource.path("section").path(section.getUriTag()).path("subsections").accept(defaultMediaType).get(genericType);
    }

    /**
     * Get a map with all the subsections of a given section, keyed by uriTag.
     * @param section the section's uriTag
     * @return a Map containing all its subsections
     */
    public Map<String, Subsection> getSubsectionMap(String section) {
        List<Subsection> subsections = getSubsections(section);
        Map<String, Subsection> subsectionMap = new LinkedHashMap<String, Subsection>();
        for (Subsection s : subsections) {
            subsectionMap.put(s.getUriTag(), s);
        }
        return subsectionMap;
    }

    /*
     * Category methods
     */
    /**
     * Retrieves a List of all the categories.
     * @return a List of all the available categories
     */
    public List<Category> getCategories() {
        GenericType<List<Category>> genericType = new GenericType<List<Category>>() {
        };
        return webResource.path("categories").accept(defaultMediaType).get(genericType);
    }

    /**
     * Get a Map of all the categories, keyed by uriTag.
     * @return a Map with all the sections
     */
    public Map<String, Category> getCategoryMap() {
        List<Category> categories = getCategories();
        Map<String, Category> categoryMap = new LinkedHashMap<String, Category>();
        for (Category s : categories) {
            categoryMap.put(s.getUriTag(), s);
        }
        return categoryMap;
    }

    /**
     * Retrieve a category by its uriTag.
     * @param uriTag the uriTag of the Category
     * @return
     */
    public Category getCategory(String uriTag) throws CategoryNotFoundException {
        try {
            return webResource.path("category").path(uriTag).accept(defaultMediaType).get(Category.class);
        } catch (UniformInterfaceException e) {
            throw new CategoryNotFoundException();
        }
    }

    /**
     * Retrieve a subsection by its numeric id.
     * @param subsectionId the numeric id of the subsection
     * @return
     */
    public Subsection getSubsection(int subsectionId) throws SubsectionNotFoundException {
        try {
            return webResource.path("subsection").path(String.valueOf(subsectionId)).accept(defaultMediaType).get(Subsection.class);
        } catch (UniformInterfaceException e) {
            throw new SubsectionNotFoundException();
        }
    }

    /*
     * Reference area methods
     */
    /**
     * Retrieve a list of all the reference areas.
     * @return a List with ReferenceArea objects
     */
    public List<ReferenceArea> getReferenceAreas() {
        GenericType<List<ReferenceArea>> genericType = new GenericType<List<ReferenceArea>>() {
        };
        return webResource.path("reference-areas").accept(defaultMediaType).get(genericType);
    }

    /**
     * Retrieve a map of all the reference areas, keyed by uriTag.
     * @return a Map of ReferenceArea objects, keyed by their uriTag
     */
    public Map<String, ReferenceArea> getReferenceAreaMap() {
        List<ReferenceArea> referenceAreas = getReferenceAreas();
        Map<String, ReferenceArea> referenceAreaMap = new LinkedHashMap<String, ReferenceArea>();
        for (ReferenceArea r : referenceAreas) {
            referenceAreaMap.put(r.getUriTag(), r);
        }
        return referenceAreaMap;
    }

    /**
     * Retrieve a ReferenceArea by its uriTag.
     * @param uriTag the ReferenceArea's uriTag
     * @return
     */
    public Category getReferenceArea(String uriTag) throws ReferenceAreaNotFoundException {
        try {
            return webResource.path("reference-area").path(uriTag).accept(defaultMediaType).get(Category.class);
        } catch (UniformInterfaceException e) {
            throw new ReferenceAreaNotFoundException();
        }
    }

    /*
     * Data set methods
     */
    /**
     * Retrieve a list of all the data sets.
     * @return a List with DataSet objects
     */
    public List<DataSet> getDataSets() {
        GenericType<List<DataSet>> genericType = new GenericType<List<DataSet>>() {
        };
        return webResource.path("data-sets").accept(defaultMediaType).get(genericType);
    }

    /**
     * Retrieve a map of all the data sets, keyed by uriTag.
     * @return a Map of DataSet objects, keyed by their uriTag
     */
    public Map<String, DataSet> getDataSetMap() {
        List<DataSet> dataSets = getDataSets();
        Map<String, DataSet> dataSetMap = new LinkedHashMap<String, DataSet>();
        for (DataSet r : dataSets) {
            dataSetMap.put(r.getUriTag(), r);
        }
        return dataSetMap;
    }

    /**
     * Retrieve a DataSet by its uriTag.
     * @param uriTag the DataSet's uriTag
     * @return
     */
    public DataSet getDataSet(String uriTag) throws DataSetNotFoundException {
        try {
            return webResource.path("data-set").path(uriTag).accept(defaultMediaType).get(DataSet.class);
        } catch (UniformInterfaceException e) {
            throw new DataSetNotFoundException();
        }
    }

    /*
     * Periodicity methods
     */
    /**
     * Retrieve a list of all the data sets.
     * @return a List with DataSet objects
     */
    public List<Periodicity> getPeriodicities() {
        GenericType<List<Periodicity>> genericType = new GenericType<List<Periodicity>>() {
        };
        return webResource.path("periodicities").accept(defaultMediaType).get(genericType);
    }

    /*
     * Source methods
     */
    /**
     * Retrieve a list of all the sources.
     * @return a List with Source objects
     */
    public List<Source> getSources() {
        GenericType<List<Source>> genericType = new GenericType<List<Source>>() {
        };
        return webResource.path("sources").accept(defaultMediaType).get(genericType);
    }

    /*
     * Data provider methods
     */
    /**
     * Retrieve a list of all the data providers.
     * @return a List with DataProvider objects
     */
    public List<DataProvider> getDataProviders() {
        GenericType<List<DataProvider>> genericType = new GenericType<List<DataProvider>>() {
        };
        return webResource.path("data-providers").accept(defaultMediaType).get(genericType);
    }
}
