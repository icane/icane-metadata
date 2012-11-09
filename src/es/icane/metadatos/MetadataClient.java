package es.icane.metadatos;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import es.icane.metadatos.model.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MediaType;

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
     * @param section the TimeSeries section uriTag
     * @param subsection the TimeSeries subsection uriTag
     * @param category the TimeSeries category uriTag
     * @param idOrUriTag either the String value of the TimeSeries numeric id
     * @return the TimeSeries object
     * @throws SeriesNotFoundException 
     */
    public TimeSeries getTimeSeries(String section, String subsection, String category, String idOrUriTag) throws SeriesNotFoundException {
        try {
            return webResource.path(section).path(subsection).path(category).path(idOrUriTag).accept(MediaType.APPLICATION_XML_TYPE).get(TimeSeries.class);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Retreive a TimeSeries object by its section, subsection, category and dataSet
     * @param section the TimeSeries section uriTag
     * @param subsection the TimeSeries subsection uriTag
     * @param category the TimeSeries category uriTag
     * @param dataSet the TimeSeries dataSet uriTag
     * @param idOrUriTag either the String value of the TimeSeries numeric id
     * @return
     * @throws SeriesNotFoundException 
     */
    public TimeSeries getTimeSeries(String section, String subsection, String category, String dataSet, String idOrUriTag) throws SeriesNotFoundException {
        try {
            return webResource.path(section).path(subsection).path(category).path(dataSet).path(idOrUriTag).accept(MediaType.APPLICATION_XML_TYPE).get(TimeSeries.class);
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
    @Deprecated
    public TimeSeries getTimeSeries(int timeSeriesId) throws SeriesNotFoundException {
        try {
            return webResource.path("time-series").path(String.valueOf(timeSeriesId)).accept(MediaType.APPLICATION_XML_TYPE).get(TimeSeries.class);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Retrieves all the TimeSeries in the database.
     * 
     * This method will potentially generate a lot of traffic.
     * 
     * @return a List of all the series in the database
     */
    @Deprecated
    public List<TimeSeries> getAllTimeSeries() {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        return webResource.path("time-series").accept(MediaType.APPLICATION_XML_TYPE).get(genericType);
    }

    public List<TimeSeries> getTimeSeriesBySection(String section) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(section).path("time-series").accept(MediaType.APPLICATION_XML_TYPE).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }
    
    public List<TimeSeries> getTimeSeriesBySubsection(String section, String subsection) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(section).path(subsection).path("time-series-list").accept(MediaType.APPLICATION_XML_TYPE).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }
    
    /**
     * Get a List of all the time series belonging to a given section, subsection and category.
     * @param section the section's uriTag
     * @param subsection the subsection's uriTag
     * @param category the category's uriTag
     * @return a List with all the time series matching the criteria
     * @throws SeriesNotFoundException 
     */
    public List<TimeSeries> getTimeSeriesByCategory(String section, String subsection, String category) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(section).path(subsection).path(category).path("time-series-list").accept(MediaType.APPLICATION_XML_TYPE).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    public List<TimeSeries> getTimeSeriesAncestors(TimeSeries timeSeries) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(timeSeries.getCategory().getUriTag()).path(timeSeries.getSubsection().getUriTag()).path(String.valueOf(timeSeries.getId())).path("ancestors").accept(MediaType.APPLICATION_XML_TYPE).get(genericType);
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
        return webResource.path("sections").accept(MediaType.APPLICATION_XML_TYPE).get(genericType);
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
    /*public Section getSection(String uriTag) throws SectionNotFoundException {
    try {
    return webResource.path(uriTag).accept(MediaType.APPLICATION_XML_TYPE).get(Section.class);
    } catch (UniformInterfaceException e) {
    throw new SectionNotFoundException();
    }
    }*/
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
        return webResource.path(section).path("subsections").accept(MediaType.APPLICATION_XML_TYPE).get(genericType);
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
        return webResource.path("categories").accept(MediaType.APPLICATION_XML_TYPE).get(genericType);
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
    // Reference area methods
}
