package es.icane.metadatos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.*;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.GenericType;
//import com.sun.jersey.api.client.UniformInterfaceException;
//import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import es.icane.metadatos.dto.TimeSeriesDTO;
import es.icane.metadatos.model.*;

/**
 * Metadata Client is used for accessing ICANE's Web Service methods.
 * <p/>
 * In a production environment, only uriTags should be used in all methods
 * (instead of numeric id's).
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
@SuppressWarnings("ALL")
public class MetadataClient {

    private final String baseUrl;
    // Constants
    private final MediaType defaultMediaType = MediaType.APPLICATION_XML_TYPE;

    private ClientConfig clientConfig;
    private WebResource webResource;
    private long connectionTimeout;

    /**
     * Constructor.
     *
     * @param baseUrl base URL for the Web Service
     */
    public MetadataClient(String baseUrl) {
        this.baseUrl = baseUrl;
        clientConfig = new DefaultClientConfig();
        clientConfig.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 10000);
        Client jerseyClient = Client.create(clientConfig);
        webResource = jerseyClient.resource(baseUrl);
    }

    /**
     * Returns this client's base URL.
     *
     * @return this client's base URL.
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * Returns this client's connection timeout, in milliseconds.
     * <p/>
     * The client will wait this long before timing out when connecting to the Web Service.
     *
     * @return this client's connection timeout, in milliseconds
     */
    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * Sets the connection timeout for this client.
     * <p/>
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
     * @param category   the TimeSeries category's uriTag
     * @param section    the TimeSeries section's uriTag
     * @param subsection the TimeSeries subsection's uriTag
     * @param idOrUriTag either the String value of the TimeSeries numeric id
     * @return the TimeSeries object
     * @throws SeriesNotFoundException
     */
    public TimeSeries getTimeSeries(String category, String section, String subsection,
                                    String idOrUriTag) throws SeriesNotFoundException {
        try {
            return webResource.path(category).path(section).path(subsection).path(idOrUriTag)
                    .accept(defaultMediaType).get(TimeSeries.class);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Retrieve a TimeSeries object by its section, subsection and category, including inactive series.
     *
     * @param category   the TimeSeries category's uriTag
     * @param section    the TimeSeries section's uriTag
     * @param subsection the TimeSeries subsection's uriTag
     * @param idOrUriTag either the String value of the TimeSeries numeric id
     * @param inactive   if set to true, inactive series will be retrieved too
     * @return the TimeSeries object
     * @throws SeriesNotFoundException
     */
    public TimeSeries getTimeSeries(String category, String section, String subsection, String idOrUriTag,
                                    Boolean inactive) throws SeriesNotFoundException {
        try {
            MultivaluedMap queryParams = new MultivaluedMapImpl();
            queryParams.add("inactive", inactive.toString());
            return webResource.path(category).path(section).path(subsection).path(idOrUriTag)
                    .queryParams(queryParams).accept(defaultMediaType).get(TimeSeries.class);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Retrieve a TimeSeries object by its section, subsection and category.
     *
     * @param category   the TimeSeries category
     * @param section    the TimeSeries section
     * @param subsection the TimeSeries subsection
     * @param idOrUriTag either the String value of the TimeSeries numeric id
     * @return the TimeSeries object
     * @throws SeriesNotFoundException
     */
    public TimeSeries getTimeSeries(Category category, Section section,
                                    Subsection subsection, String idOrUriTag) throws SeriesNotFoundException {
        try {
            return webResource.path(category.getUriTag()).path(section.getUriTag())
                    .path(subsection.getUriTag()).path(idOrUriTag).accept(defaultMediaType).get(TimeSeries.class);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Returns a TimeSeries, given its numeric id.
     * <p/>
     * This method is not guaranteed to work in a production environment.
     *
     * @param timeSeriesId the numeric id of the TimeSeries
     * @return the TimeSeries object with that id
     * @throws SeriesNotFoundException
     */
    public TimeSeries getTimeSeries(int timeSeriesId) throws SeriesNotFoundException {
        try {
            return webResource.path("time-series").path(String.valueOf(timeSeriesId))
                    .accept(defaultMediaType).get(TimeSeries.class);
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

    public List<TimeSeries> getTimeSeriesBySectionAndCategory(String category, String section)
            throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(category).path(section).path("time-series-list").
                    accept(defaultMediaType).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    public List<TimeSeries> getTimeSeriesList(Category category, Section section) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(category.getUriTag()).path(section.getUriTag())
                    .path("time-series-list").accept(defaultMediaType).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    public List<TimeSeries> getTimeSeriesBySubsection(String section, String subsection) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(section).path(subsection)
                    .path("time-series-list").accept(defaultMediaType).get(genericType);
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
     *
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
     *
     * @param category   the category
     * @param section    the section
     * @param subsection the subsection
     * @return a List with all the time series matching the criteria
     * @throws SeriesNotFoundException
     */
    public List<TimeSeries> getTimeSeriesList(Category category, Section section, Subsection subsection)
            throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(category.getUriTag()).path(section.getUriTag())
                    .path(subsection.getUriTag()).path("time-series-list").accept(defaultMediaType).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Get a List of all the time series belonging to a given section, subsection and category (by their uriTags).
     *
     * @param category   the category's uriTag
     * @param section    the section's uriTag
     * @param subsection the subsection's uriTag
     * @return a List with all the time series matching the criteria
     * @throws SeriesNotFoundException
     */
    public List<TimeSeries> getTimeSeriesList(String category, String section, String subsection)
            throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(category).path(section).path(subsection)
                    .path("time-series-list").accept(defaultMediaType).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Get a List of all the time series belonging to a given dataset.
     *
     * @param dataSet the dataset
     * @return a List with all the time series matching the criteria
     * @throws SeriesNotFoundException
     */
    public List<TimeSeries> getTimeSeriesList(DataSet dataSet) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path("data-set").path(dataSet.getUriTag())
                    .path("time-series-list").accept(defaultMediaType).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Get a List of all the time series belonging to a given dataset (by its uriTag).
     *
     * @param dataSet the subsection's dataset
     * @return a List with all the time series matching the criteria
     * @throws SeriesNotFoundException
     */
    public List<TimeSeries> getTimeSeriesList(String dataSet) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path("data-set").path(dataSet)
                    .path("time-series-list").accept(defaultMediaType).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }


    /**
     * Get the parent of a given TimeSeries
     *
     * @param timeSeries the TimeSeries whose parent to retrieve
     * @return its parent TimeSeries
     * @throws SeriesNotFoundException
     */
    public TimeSeries getParent(TimeSeries timeSeries) throws SeriesNotFoundException {
        try {
            return webResource.path(timeSeries.getCategory().getUriTag()).path(timeSeries.getSubsection()
                    .getSection().getUriTag()).path(timeSeries.getSubsection().getUriTag())
                    .path(timeSeries.getUriTag()).path("parent").accept(defaultMediaType).get(TimeSeries.class);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    /**
     * Get all the ancestors of a given TimeSeries
     *
     * @param timeSeries the TimeSeries whose ancestors to retrieve
     * @return a List with all its ancestors
     * @throws SeriesNotFoundException
     */
    public List<TimeSeries> getAncestors(TimeSeries timeSeries) throws SeriesNotFoundException {
        GenericType<List<TimeSeries>> genericType = new GenericType<List<TimeSeries>>() {
        };
        try {
            return webResource.path(timeSeries.getCategory().getUriTag()).path(timeSeries.getSubsection()
                    .getSection().getUriTag()).path(timeSeries.getSubsection().getUriTag())
                    .path(timeSeries.getUriTag()).path("parents").accept(defaultMediaType).get(genericType);
        } catch (UniformInterfaceException e) {
            throw new SeriesNotFoundException(e);
        }
    }

    // Section methods

    /**
     * Retrieves a List of all the Sections.
     *
     * @return a List of all the available Sections
     */
    public List<Section> getSections() {
        GenericType<List<Section>> genericType = new GenericType<List<Section>>() {
        };
        return webResource.path("sections").accept(defaultMediaType).get(genericType);
    }

    /**
     * Get a Map of all the sections, keyed by uriTag.
     *
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
     *
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
    public List<Subsection> getSubsections(String subSection) {
        GenericType<List<Subsection>> genericType = new GenericType<List<Subsection>>() {
        };
        return webResource.path("section").path(subSection).path("subsections").accept(defaultMediaType).get(genericType);
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
        return webResource.path("section").path(section.getUriTag()).path("subsections")
                .accept(defaultMediaType).get(genericType);
    }

    /**
     * Get a map with all the subsections of a given section, keyed by uriTag.
     *
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
     *
     * @return a List of all the available categories
     */
    public List<Category> getCategories() {
        GenericType<List<Category>> genericType = new GenericType<List<Category>>() {
        };
        return webResource.path("categories").accept(defaultMediaType).get(genericType);
    }

    /**
     * Get a Map of all the categories, keyed by uriTag.
     *
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
     *
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
     *
     * @param subsectionId the numeric id of the subsection
     * @return
     */
    public Subsection getSubsection(int subsectionId) throws SubsectionNotFoundException {
        try {
            return webResource.path("subsection").path(String.valueOf(subsectionId))
                    .accept(defaultMediaType).get(Subsection.class);
        } catch (UniformInterfaceException e) {
            throw new SubsectionNotFoundException();
        }
    }

	/*
	 * Reference area methods
	 */

    /**
     * Retrieve a list of all the reference areas.
     *
     * @return a List with ReferenceArea objects
     */
    public List<ReferenceArea> getReferenceAreas() {
        GenericType<List<ReferenceArea>> genericType = new GenericType<List<ReferenceArea>>() {
        };
        return webResource.path("reference-areas").accept(defaultMediaType).get(genericType);
    }

    /**
     * Retrieve a map of all the reference areas, keyed by uriTag.
     *
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
     *
     * @param uriTag the ReferenceArea's uriTag
     * @return
     */
    public ReferenceArea getReferenceArea(String uriTag) throws ReferenceAreaNotFoundException {
        try {
            return webResource.path("reference-area").path(uriTag)
                    .accept(defaultMediaType).get(ReferenceArea.class);
        } catch (UniformInterfaceException e) {
            throw new ReferenceAreaNotFoundException();
        }
    }

    /**
     * Update ReferenceArea data from model object
     *
     * @param referenceArea a ReferenceArea object with the data to update.
     * @return a ReferenceArea object
     */

    public ReferenceArea updateReferenceArea(ReferenceArea referenceArea) {

        ClientResponse cr = webResource.path("reference-area").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, referenceArea);
        return cr.getEntity(ReferenceArea.class);
    }

    /**
     * Create ReferenceArea data from model object
     *
     * @param referenceArea a ReferenceArea object with the data to create.
     * @return a ReferenceArea object
     */

    public ReferenceArea createReferenceArea(ReferenceArea referenceArea) {
        //TimeSeriesDTO timeSeriesDTO = new ReferenceAreaDTO(timeSeries);
        ClientResponse cr = webResource.path("reference-area").type(defaultMediaType).accept(defaultMediaType).
                post(ClientResponse.class, referenceArea);
        return cr.getEntity(ReferenceArea.class);
    }

    /**
     * Delete ReferenceArea data from model object
     *
     * @param id the ReferenceArea's id
     * @return a ReferenceArea object
     */

    public void deleteReferenceArea(int id) throws ReferenceAreaNotFoundException {

        try {
            webResource.path("reference-area").path(String.valueOf(id)).type(defaultMediaType).accept(defaultMediaType).
                    delete(ReferenceArea.class);
        }
        catch (UniformInterfaceException e ) {
            System.out.println(e.getResponse());
        }


    }

	/*
	 * Data set methods
	 */

    /**
     * Retrieve a list of all the data sets.
     *
     * @return a List with DataSet objects
     */
    public List<DataSet> getDataSets() {
        GenericType<List<DataSet>> genericType = new GenericType<List<DataSet>>() {
        };
        return webResource.path("data-sets").accept(defaultMediaType).get(genericType);
    }

    /**
     * Retrieve a map of all the data sets, keyed by uriTag.
     *
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
     *
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
     *
     * @return a List with DataSet objects
     */
    public List<Periodicity> getPeriodicities() {
        GenericType<List<Periodicity>> genericType = new GenericType<List<Periodicity>>() {
        };
        return webResource.path("periodicities").accept(defaultMediaType).get(genericType);
    }

    /**
     * Retrieve a Periodicity by its uriTag.
     *
     * @param uriTag the DataSet's uriTag
     * @return
     */
    public Periodicity getPeriodicity(String uriTag) throws PeriodicityNotFoundException {
        try {
            return webResource.path("periodicity").path(uriTag).accept(defaultMediaType).get(Periodicity.class);
        } catch (UniformInterfaceException e) {
            throw new PeriodicityNotFoundException();
        }
    }

    /**
     * Update Periodicity data from model object
     *
     * @param periodicity a Periodicity object with the data to update.
     * @return a Periodicity object
     */

    public Periodicity updatePeriodicity(Periodicity periodicity) {

        ClientResponse cr = webResource.path("periodicity").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, periodicity);
        return cr.getEntity(Periodicity.class);
    }

    /**
     * Create Measure data from model object
     *
     * @param measure a Measure object with the data to create.
     * @return a Measure object
     */

    public Periodicity createPeriodicity(Periodicity periodicity) {
        ClientResponse cr = webResource.path("periodicity").type(defaultMediaType).accept(defaultMediaType).
                post(ClientResponse.class, periodicity);
        return cr.getEntity(Periodicity.class);
    }

    /**
     * Delete Periodicity data from model object
     *
     * @param id the Periodicity's id
     * @return a Periodicity object
     */

    public void deletePeriodicity(Integer id) throws PeriodicityNotFoundException {

        try {
            webResource.path("periodicity").path(String.valueOf(id)).type(defaultMediaType).accept(defaultMediaType).
                    delete(Periodicity.class);
        }
        catch (UniformInterfaceException e ) {
            System.out.println(e.getResponse());
        }


    }

	/*
	 * TimePeriod methods
	 */

    /**
     * Retrieve a list of all the sources.
     *
     * @return a List with Source objects
     */
    public List<TimePeriod> getTimePeriods() {
        GenericType<List<TimePeriod>> genericType = new GenericType<List<TimePeriod>>() {
        };
        return webResource.path("time-periods").accept(defaultMediaType).get(genericType);
    }

    /**
     * Retrieve a TimePeriod by its uriTag.
     *
     * @param uriTag the TimePeriod's uriTag
     * @return
     */
    public TimePeriod getTimePeriod(int id) throws TimePeriodNotFoundException {
        try {
            return webResource.path("time-period").path(String.valueOf(id))
                    .accept(defaultMediaType).get(TimePeriod.class);
        } catch (UniformInterfaceException e) {
            throw new TimePeriodNotFoundException();
        }
    }

    /**
     * Update TimePeriod data from model object
     *
     * @param timePeriod a TimePeriod object with the data to update.
     * @return a TimePeriod object
     */

    public TimePeriod updateTimePeriod(TimePeriod timePeriod) {

        ClientResponse cr = webResource.path("time-period").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, timePeriod);
        return cr.getEntity(TimePeriod.class);
    }

    /**
     * Create Measure data from model object
     *
     * @param measure a Measure object with the data to create.
     * @return a Measure object
     */

    public TimePeriod createTimePeriod(TimePeriod timePeriod) {
        ClientResponse cr = webResource.path("time-period").type(defaultMediaType).accept(defaultMediaType).
                post(ClientResponse.class, timePeriod);
        return cr.getEntity(TimePeriod.class);
    }

    /**
     * Delete TimePeriod data from model object
     *
     * @param id the TimePeriod's id
     * @return a TimePeriod object
     */

    public void deleteTimePeriod(int id) throws TimePeriodNotFoundException {

        try {
            webResource.path("time-period").path(String.valueOf(id)).type(defaultMediaType).accept(defaultMediaType).
                    delete(TimePeriod.class);
        }
        catch (UniformInterfaceException e ) {
            System.out.println(e.getResponse());
        }


    }

	/*
	 * NodeType methods
	 */

    /**
     * Retrieve a NodeTYpe by its uriTag.
     *
     * @param id the NodeTYpe's uriTag
     * @return
     */
    public NodeType getNodeType(String uriTag) throws NodeTypeNotFoundException {
        try {
            return webResource.path("node-type").path(uriTag).accept(defaultMediaType).get(NodeType.class);
        } catch (UniformInterfaceException e) {
            throw new NodeTypeNotFoundException();
        }
    }

    /**
     * Retrieve a list of all the sources.
     *
     * @return a List with Source objects
     */
    public List<NodeType> getNodeTypes() {
        GenericType<List<NodeType>> genericType = new GenericType<List<NodeType>>() {
        };
        return webResource.path("node-types").accept(defaultMediaType).get(genericType);
    }

    /**
     * Update NodeType data from model object
     *
     * @param nodeType a NodeType object with the data to update.
     * @return a NodeType object
     */

    public NodeType updateNodeType(NodeType nodeType) {

        ClientResponse cr = webResource.path("node-type").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, nodeType);
        return cr.getEntity(NodeType.class);
    }

    /**
     * Create Measure data from model object
     *
     * @param measure a Measure object with the data to create.
     * @return a Measure object
     */

    public NodeType createNodeType(NodeType nodeType) {
        //TimeSeriesDTO timeSeriesDTO = new NodeTypeDTO(timeSeries);
        ClientResponse cr = webResource.path("node-type").type(defaultMediaType).accept(defaultMediaType).
                post(ClientResponse.class, nodeType);
        return cr.getEntity(NodeType.class);
    }

    /**
     * Delete NodeType data from model object
     *
     * @param id the NodeType's id
     * @return a NodeType object
     */

    public void deleteNodeType(Integer id) throws NodeTypeNotFoundException {

        try {
            webResource.path("node-type").path(String.valueOf(id)).type(defaultMediaType).accept(defaultMediaType).
                    delete(NodeType.class);
        }
        catch (UniformInterfaceException e ) {
            System.out.println(e.getResponse());
        }


    }
    

	/*
	 * Data provider methods
	 */

    /**
     * Retrieve a list of all the data providers.
     *
     * @return a List with DataProvider objects
     */
    public List<DataProvider> getDataProviders() {
        GenericType<List<DataProvider>> genericType = new GenericType<List<DataProvider>>() {
        };
        return webResource.path("data-providers").accept(defaultMediaType).get(genericType);
    }

    /**
     * Retrieve a DataProvider by  id.
     *
     * @param id of the data provider
     * @return
     */
    public DataProvider getDataProvider(int id) throws DataProviderNotFoundException {
        try {
            return webResource.path("data-provider").path(String.valueOf(id)).accept(defaultMediaType).get(DataProvider.class);
        } catch (UniformInterfaceException e) {
            throw new DataProviderNotFoundException();
        }
    }

    /**
     * Update TimeSeries data from model object
     *
     * @param timeSeries a TimeSeries object with the data to update.
     * @return a TimeSeries object
     */
    public TimeSeries updateTimeSeries(TimeSeries timeSeries) {
        TimeSeriesDTO timeSeriesDTO = new TimeSeriesDTO(timeSeries);
        ClientResponse cr = webResource.path("time-series").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, timeSeriesDTO);
        return cr.getEntity(TimeSeries.class);
    }

    /**
     * Retrieve a Measure by its id.
     *
     * @param id the Measure's id
     * @return
     */
    public Measure getMeasure(int id) throws MeasureNotFoundException {
        try {
            return webResource.path("measure").path(String.valueOf(id)).accept(defaultMediaType).get(Measure.class);
        } catch (UniformInterfaceException e) {
            throw new MeasureNotFoundException();
        }
    }

    /**
     * Retrieve a list of all the measures.
     *
     * @return a List with Measure objects
     */
    public List<Measure> getMeasures() {
        GenericType<List<Measure>> genericType = new GenericType<List<Measure>>() {
        };
        return webResource.path("measures").accept(defaultMediaType).get(genericType);
    }

    /**
     * Update Measure data from model object
     *
     * @param measure a Measure object with the data to update.
     * @return a Measure object
     */

    public Measure updateMeasure(Measure measure) {

        ClientResponse cr = webResource.path("measure").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, measure);
        return cr.getEntity(Measure.class);
    }

    /**
     * Create Measure data from model object
     *
     * @param measure a Measure object with the data to create.
     * @return a Measure object
     */

    public Measure createMeasure(Measure measure) {
        //TimeSeriesDTO timeSeriesDTO = new MeasureDTO(timeSeries);
        ClientResponse cr = webResource.path("measure").type(defaultMediaType).accept(defaultMediaType).
                post(ClientResponse.class, measure);
        return cr.getEntity(Measure.class);
    }

    /**
     * Delete Measure data from model object
     *
     * @param id the Measure's id
     * @return a Measure object
     */

    public void deleteMeasure(int id) throws MeasureNotFoundException {

        try {
           webResource.path("measure").path(String.valueOf(id)).type(defaultMediaType).accept(defaultMediaType).
                    delete(Measure.class);
        }
        catch (UniformInterfaceException e ) {
            System.out.println(e.getResponse());
        }


    }

    /**
     * Retrieve a UnitOfMeasure by its id.
     *
     * @param id the UnitOfMeasure's id
     * @return
     */
    public UnitOfMeasure getUnitOfMeasure(int id) throws UnitOfMeasureNotFoundException {
        try {
            return webResource.path("unit-of-measure").path(String.valueOf(id)).accept(defaultMediaType).get(UnitOfMeasure.class);
        } catch (UniformInterfaceException e) {
            throw new UnitOfMeasureNotFoundException();
        }
    }
    
    
    /*
	 * Unit of measure methods
	 */

    /**
     * Retrieve a list of all the Units of measure.
     *
     * @return a List with UnitOfMeasure objects
     */
    public List<UnitOfMeasure> getUnitsOfMeasure() {
        GenericType<List<UnitOfMeasure>> genericType = new GenericType<List<UnitOfMeasure>>() {
        };
        return webResource.path("units-of-measure").accept(defaultMediaType).get(genericType);
    }


    /**
     * Retrieve a Reference by its id.
     *
     * @param id the Reference's id
     * @return
     */
    public Reference getReference(int id) throws ReferenceNotFoundException {
        try {
            return webResource.path("reference").path(String.valueOf(id)).accept(defaultMediaType).get(Reference.class);
        } catch (UniformInterfaceException e) {
            throw new ReferenceNotFoundException();
        }
    }

     /**
     * Retrieve a list of all the References.
     *
     * @return a List with Reference objects
     */
    public List<Reference> getReferences() {
        GenericType<List<Reference>> genericType = new GenericType<List<Reference>>() {
        };
        return webResource.path("references").accept(defaultMediaType).get(genericType);
    }


    /**
     * Update Reference data from model object
     *
     * @param reference a Reference object with the data to update.
     * @return a Reference object
     */

    public Reference updateReference(Reference reference) {

        ClientResponse cr = webResource.path("reference").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, reference);
        return cr.getEntity(Reference.class);
    }

    /**
     * Create Reference data from model object
     *
     * @param reference a Reference object with the data to create.
     * @return a Reference object
     */

    public Reference createReference(Reference reference) {
        //TimeSeriesDTO timeSeriesDTO = new ReferenceDTO(timeSeries);
        ClientResponse cr = webResource.path("reference").type(defaultMediaType).accept(defaultMediaType).
                post(ClientResponse.class, reference);
        return cr.getEntity(Reference.class);
    }

    /**
     * Delete Reference data from model object
     *
     * @param id the Reference's id
     * @return a Reference object
     */

    public void deleteReference(int id) throws ReferenceNotFoundException {

        try {
            webResource.path("reference").path(String.valueOf(id)).type(defaultMediaType).accept(defaultMediaType).
                    delete(Reference.class);
        }
        catch (UniformInterfaceException e ) {
            System.out.println(e.getResponse());
        }


    }

    /**
     * Retrieve a Link by its id.
     *
     * @param id the Link's id
     * @return
     */
    public Link getLink(int id) throws LinkNotFoundException {
        try {
            return webResource.path("link").path(String.valueOf(id)).accept(defaultMediaType).get(Link.class);
        } catch (UniformInterfaceException e) {
            throw new LinkNotFoundException();
        }
    }

    /**
     * Retrieve a list of all the Links.
     *
     * @return a List with Link objects
     */
    public List<Link> getLinks() {
        GenericType<List<Link>> genericType = new GenericType<List<Link>>() {
        };
        return webResource.path("links").accept(defaultMediaType).get(genericType);
    }


    /**
     * Update Link data from model object
     *
     * @param link a Link object with the data to update.
     * @return a Link object
     */

    public Link updateLink(Link link) {

        ClientResponse cr = webResource.path("link").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, link);
        return cr.getEntity(Link.class);
    }

    /**
     * Create Link data from model object
     *
     * @param link a Link object with the data to create.
     * @return a Link object
     */

    public Link createLink(Link link) {
        //TimeSeriesDTO timeSeriesDTO = new LinkDTO(timeSeries);
        ClientResponse cr = webResource.path("link").type(defaultMediaType).accept(defaultMediaType).
                post(ClientResponse.class, link);
        return cr.getEntity(Link.class);
    }

    /**
     * Delete Link data from model object
     *
     * @param id the Link's id
     * @return a Link object
     */

    public void deleteLink(int id) throws LinkNotFoundException {

        try {
            webResource.path("link").path(String.valueOf(id)).type(defaultMediaType).accept(defaultMediaType).
                    delete(Link.class);
        }
        catch (UniformInterfaceException e ) {
            System.out.println(e.getResponse());
        }


    }

    /**
     * Retrieve a LinkType by its id.
     *
     * @param id the LinkType's id
     * @return
     */
    public LinkType getLinkType(int id) throws LinkTypeNotFoundException {
        try {
            return webResource.path("link-type").path(String.valueOf(id)).accept(defaultMediaType).get(LinkType.class);
        } catch (UniformInterfaceException e) {
            throw new LinkTypeNotFoundException();
        }
    }

    /**
     * Retrieve a list of all the Link Types.
     *
     * @return a List with LinkType objects
     */
    public List<LinkType> getLinkTypes() {
        GenericType<List<LinkType>> genericType = new GenericType<List<LinkType>>() {
        };
        return webResource.path("link-types").accept(defaultMediaType).get(genericType);
    }

    /**
     * Update LinkType data from model object
     *
     * @param linkType a LinkType object with the data to update.
     * @return a LinkType object
     */

    public LinkType updateLinkType(LinkType linkType) {

        ClientResponse cr = webResource.path("link-type").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, linkType);
        return cr.getEntity(LinkType.class);
    }

    /**
     * Create Measure data from model object
     *
     * @param measure a Measure object with the data to create.
     * @return a Measure object
     */

    public LinkType createLinkType(LinkType linkType) {
        ClientResponse cr = webResource.path("link-type").type(defaultMediaType).accept(defaultMediaType).
                post(ClientResponse.class, linkType);
        return cr.getEntity(LinkType.class);
    }

    /**
     * Delete LinkType data from model object
     *
     * @param id the LinkType's id
     * @return a LinkType object
     */

    public void deleteLinkType(int id) throws LinkTypeNotFoundException {

        try {
            webResource.path("node-type").path(String.valueOf(id)).type(defaultMediaType).accept(defaultMediaType).
                    delete(LinkType.class);
        }
        catch (UniformInterfaceException e ) {
            System.out.println(e.getResponse());
        }


    }
    

    /**
     * Create UnitOfMeasure data from model object
     *
     * @param unitOfMeasure a UnitOfMeasure object with the data to create.
     * @return a UnitOfMeasure object
     */

    public UnitOfMeasure createUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        //TimeSeriesDTO timeSeriesDTO = new UnitOfMeasureDTO(timeSeries);
        ClientResponse cr = webResource.path("unit-of-measure").type(defaultMediaType).accept(defaultMediaType).
                post(ClientResponse.class, unitOfMeasure);
        return cr.getEntity(UnitOfMeasure.class);
    }

    /**
     * Delete UnitOfMeasure data from model object
     *
     * @param id the UnitOfMeasure's id
     * @return a UnitOfMeasure object
     */

    public void deleteUnitOfMeasure(int id) throws UnitOfMeasureNotFoundException {

        try {
            webResource.path("unit-of-measure").path(String.valueOf(id)).type(defaultMediaType).accept(defaultMediaType).
                    delete(UnitOfMeasure.class);
        }
        catch (UniformInterfaceException e ) {
            System.out.println(e.getResponse());
        }

    }

    /**
     * Update UnitOfMeasure data from model object
     *
     * @param unitOfMeasure a UnitOfMeasure object with the data to update.
     * @return a UnitOfMeasure object
     */

    public UnitOfMeasure updateUnitOfMeasure(UnitOfMeasure unitOfMeasure) {

        ClientResponse cr = webResource.path("unit-of-measure").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, unitOfMeasure);
        return cr.getEntity(UnitOfMeasure.class);
    }

    /**
     * Create Subsection data from model object
     *
     * @param subsection a Subsection object with the data to create.
     * @return a Subsection object
     */

    public Subsection createSubsection(Subsection subsection) {
        ClientResponse cr = webResource.path("subsection").type(defaultMediaType).accept(defaultMediaType).
                post(ClientResponse.class, subsection);
        return cr.getEntity(Subsection.class);
    }

    /**
     * Delete Subsection data from model object
     *
     * @param id the Subsection's id
     * @return a Subsection object
     */

    public void deleteSubsection(int id) throws SubsectionNotFoundException {

        try {
            webResource.path("subsection").path(String.valueOf(id)).type(defaultMediaType).accept(defaultMediaType).
                    delete(Subsection.class);
        }
        catch (UniformInterfaceException e ) {
            System.out.println(e.getResponse());
        }

    }

    /**
     * Update Subsection data from model object
     *
     * @param subsection a Subsection object with the data to update.
     * @return a Subsection object
     */

    public Subsection updateSubsection(Subsection subsection) {

        ClientResponse cr = webResource.path("subsection").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, subsection);
        return cr.getEntity(Subsection.class);
    }

    /**
     * Create Section data from model object
     *
     * @param section a Section object with the data to create.
     * @return a Section object
     */

    public Section createSection(Section section) {
        ClientResponse cr = webResource.path("section").type(defaultMediaType).accept(defaultMediaType).
                post(ClientResponse.class, section);
        return cr.getEntity(Section.class);
    }

    /**
     * Update Section data from model object
     *
     * @param section a Section object with the data to update.
     * @return a Section object
     */

    public Section updateSection(Section section) {

        ClientResponse cr = webResource.path("section").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, section);
        return cr.getEntity(Section.class);
    }

    /**
     * Delete Section data from model object
     *
     * @param id the Section's id
     * @return a Section object
     */

    public void deleteSection(int id) throws SectionNotFoundException {

        try {
            webResource.path("section").path(String.valueOf(id)).type(defaultMediaType).accept(defaultMediaType).
                    delete(Section.class);
        } catch (UniformInterfaceException e) {
            System.out.println(e.getResponse());
        }

    }

    /**
     * Create DataSet data from model object
     *
     * @param dataSet a DataSet object with the data to create.
     * @return a DataSet object
     */

    public DataSet createDataSet(DataSet dataSet) {
        ClientResponse cr = webResource.path("data-set").type(defaultMediaType).accept(defaultMediaType).
                post(ClientResponse.class, dataSet);
        return cr.getEntity(DataSet.class);
    }

    /**
     * Delete DataSet data from model object
     *
     * @param id the DataSet's id
     * @return a DataSet object
     */

    public void deleteDataSet(int id) throws DataSetNotFoundException {

        try {
            webResource.path("data-set").path(String.valueOf(id)).type(defaultMediaType).accept(defaultMediaType).
                    delete(DataSet.class);
        } catch (UniformInterfaceException e) {
            System.out.println(e.getResponse());
        }

    }

    /**
     * Update DataSet data from model object
     *
     * @param dataSet a DataSet object with the data to update.
     * @return a DataSet object
     */

    public DataSet updateDataSet(DataSet dataSet) {

        ClientResponse cr = webResource.path("data-set").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, dataSet);
        return cr.getEntity(DataSet.class);
    }

    /**
     * Create DataProvider data from model object
     *
     * @param dataProvider a DataProvider object with the data to create.
     * @return a DataProvider object
     */

    public DataProvider createDataProvider(DataProvider dataProvider) {
        ClientResponse cr = webResource.path("data-provider").type(defaultMediaType).accept(defaultMediaType).
                post(ClientResponse.class, dataProvider);
        return cr.getEntity(DataProvider.class);
    }

    /**
     * Delete DataProvider data from model object
     *
     * @param id the DataProvider's id
     * @return a DataProvider object
     */

    public void deleteDataProvider(int id) throws DataProviderNotFoundException {

        try {
            webResource.path("data-provider").path(String.valueOf(id)).type(defaultMediaType).accept(defaultMediaType).
                    delete(DataProvider.class);
        } catch (UniformInterfaceException e) {
            System.out.println(e.getResponse());
        }

    }

    /**
     * Update DataProvider data from model object
     *
     * @param dataProvider a DataProvider object with the data to update.
     * @return a DataProvider object
     */

    public DataProvider updateDataProvider(DataProvider dataProvider) {

        ClientResponse cr = webResource.path("data-provider").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, dataProvider);
        return cr.getEntity(DataProvider.class);
    }

    /**
     * Create Category data from model object
     *
     * @param category a Category object with the data to create.
     * @return a Category object
     */

    public Category createCategory(Category category) {
        ClientResponse cr = webResource.path("category").type(defaultMediaType).accept(defaultMediaType).
                post(ClientResponse.class, category);
        return cr.getEntity(Category.class);
    }

    /**
     * Delete Category data from model object
     *
     * @param id the Category's id
     * @return a Category object
     */

    public void deleteCategory(int id) throws CategoryNotFoundException {

        try {
            webResource.path("category").path(String.valueOf(id)).type(defaultMediaType).accept(defaultMediaType).
                    delete(Category.class);
        } catch (UniformInterfaceException e) {
            System.out.println(e.getResponse());
        }

    }

    /**
     * Update Category data from model object
     *
     * @param category a Category object with the data to update.
     * @return a Category object
     */

    public Category updateCategory(Category category) {

        ClientResponse cr = webResource.path("category").type(defaultMediaType).accept(defaultMediaType).
                put(ClientResponse.class, category);
        return cr.getEntity(Category.class);
    }



}
