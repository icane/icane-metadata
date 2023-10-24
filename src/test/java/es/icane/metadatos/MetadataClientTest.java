package es.icane.metadatos;

// import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
// import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import es.icane.metadatos.model.*;
// import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class MetadataClientTest {

    private static MetadataClient metadataClient;

    @BeforeClass
    public static void setUp() {
        String baseUrl = "http://www.icane.es/proyectoweb-metadata/api";
        metadataClient = new MetadataClient(baseUrl);
    }

    /* Category tests */
    @Test
    public void getCategoryShouldReturnElement() {

        Category category = null;
        try {
            category = metadataClient.getCategory("datos-regionales");
        } catch (CategoryNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert category != null;
        assertEquals("regional-data title must be Datos regionales", "Datos regionales", category.getTitle());
        assert category.getAutomatizedTopics() != null;
    
    }

    @Test
    public void getCategoriesShouldReturnList() {

        List<Category> categories = metadataClient.getCategories();

        // assert statements
        assertEquals("Size must be 5", 5, categories.size());
        assertEquals("First element has title Datos regionales", "Datos regionales", categories.get(0).getTitle());
    }
    /* End Category tests */

    /* DataProvider tests */
    @Test
    public void getDataProvidersShouldReturnList() {

        List<DataProvider> dataProviders = metadataClient.getDataProviders();

        // assert statements
        assertTrue("Size must be greater than 100", dataProviders.size() > 100);
        assertEquals("First element has acronym INE", "INE", dataProviders.get(0).getAcronym());
    }

    @Test
    public void getDataProviderShouldReturnElement() {

        DataProvider dataProvider = null;
        try {
            dataProvider = metadataClient.getDataProvider(1);
        } catch (DataProviderNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert dataProvider != null;
        assertEquals("musical-arts acronym must be INE", "INE", dataProvider.getAcronym());
    }
    /* End DataProvider tests */

    /* Dataset tests */
    @Test
    public void getDataSetsShouldReturnList() {

        List<DataSet> dataSets = metadataClient.getDataSets();

        // assert statements
        assertTrue("Size must be greater than 100", dataSets.size() > 100);
        assertEquals("20th element has acronym CGPJ", "CGPJ", dataSets.get(19).getAcronym());
    }

    @Test
    public void getDataSetShouldReturnElement() {

        DataSet dataSet = null;
        String subsectionUriTag = null;
        List<Methodology> methodologies = null;
        List<RelatedLink> relatedLinks = null;
        try {
            dataSet = metadataClient.getDataSet("actividades-id");
            subsectionUriTag = dataSet.getSubsection().getUriTag().toString();
            methodologies = dataSet.getMethodologies();
            relatedLinks = dataSet.getRelatedLinks();
        } catch (DataSetNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert dataSet != null;
        assertEquals("actividades-id acronym must be ID", "ID", dataSet.getAcronym());
        assertEquals("id-innovacion-tic-empresas", subsectionUriTag);
        assertTrue("number of methodologies must be greater than 2", methodologies.size() > 2);
        assertTrue("number of related links must be 1", relatedLinks.size() == 1);
    }
    /* End Dataset tests */

    /* Link tests */
    @Test
    public void getLinksShouldReturnList() {

        List<Link> links = metadataClient.getLinks();


        // assert statements
        assertTrue("Size must be greater than 50", links.size() > 50);
        assertEquals("First element title is DBpedia", "DBpedia", links.get(0).getTitle());
    }

    @Test
    public void getLinkShouldReturnElement() {

        Link link = null;
        try {
            link = metadataClient.getLink(473);
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert link != null;
        assertEquals("id = 473 title must be DBpedia ES", "DBpedia ES", link.getTitle());
    
    }
    /* End Link tests */

    /* LinkType tests */
    @Test
    public void getLinkTypesShouldReturnEightElementsList() {

        List<LinkType> linkTypes = metadataClient.getLinkTypes();

        // assert statements
        assertTrue("Size must be equal or greater than eight", linkTypes.size() >= 8);

    }

    @Test
    public void getLinkTypeShouldReturnElement() {

        LinkType linkType = null;
        try {
            linkType = metadataClient.getLinkType(1);
        } catch (LinkTypeNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert linkType != null;
        assertEquals("id = 1 title must be HTTP", "HTTP", linkType.getTitle());

    }
    /* End LinkType tests */

    /* Measure tests */
    @Test
    public void getMeasuresShouldReturnList() {

        List<Measure> measures = metadataClient.getMeasures();

        // assert statements
        assertTrue("Size must be greater than 100", measures.size() > 100);
        assertEquals("First element has title Parados", "Parados", measures.get(0).getTitle());
    
    }

    @Test
    public void getMeasureShouldReturnElement() {

        Measure measure = null;
        try {
            measure = metadataClient.getMeasure(777);
        } catch (MeasureNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert measure != null;
        assertEquals("id = 777 title must be Tasa de variación mensual", "Tasa de variación mensual", measure.getTitle());

    }
    /* End Measure tests */

    @Test
    public void getNodeTypeShouldReturnElement() {

        NodeType nodeType = null;
        try {
            nodeType = metadataClient.getNodeType("section");
        } catch (NodeTypeNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert nodeType != null;
        assertEquals("section title must be Sección", "Sección", nodeType.getTitle());

    }

    @Test
    public void getNodeTypesShouldReturnTenElementList() {

        List<NodeType> nodeTypes = metadataClient.getNodeTypes();

        // assert statements
        assertTrue("Size must be equal or greater than ten", nodeTypes.size() >= 10);

    }


    @Test
    public void getTimePeriodsShouldReturnList() {

        List<TimePeriod> timePeriods = metadataClient.getTimePeriods();

        // assert statements
        assertTrue("Size must be greater than 100", timePeriods.size() > 100);
        assertEquals("First element is id 246", 246, timePeriods.get(0).getId().intValue());
    }

    @Test
    public void getTimePeriodShouldReturnElement() {

        TimePeriod timePeriod = null;
        try {
            timePeriod = metadataClient.getTimePeriod(465);
        } catch (TimePeriodNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert timePeriod != null;
        assertEquals("Time period startYear must be 2010", 2010, timePeriod.getStartYear().intValue());
        assertEquals("Time period endYear must be 2011", 2011, timePeriod.getEndYear().intValue());

    }

    @Test
    public void getPeriodicitiesShouldReturnList() {

        List<Periodicity> periodicities = metadataClient.getPeriodicities();

        // assert statements
        assertTrue("Size must be greater than 10", periodicities.size() > 10);
        assertEquals("10th element has uriTag decenal", "decenal", periodicities.get(9).getUriTag());
    }

    @Test
    public void getPeriodicityShouldReturnElement() {


        Periodicity periodicity = null;
        try {
            periodicity = metadataClient.getPeriodicity("anual");
        } catch (PeriodicityNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert periodicity != null;
        assertEquals("Annual title must be Anual", "Anual", periodicity.getTitle());
    }


    @Test
    public void getUriTagEsShouldReturnACorrectUriTag() {

        TimeSeries timeSeries = null;
        try {
            timeSeries = metadataClient.getTimeSeries("deaths-age-average");
        } catch (SeriesNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert timeSeries != null;
        assertEquals("UritagEs must be correct", "defunciones-edad-media", timeSeries.getUriTagEs());

    }

    @Test
    public void getTimeSeriesReferencesShouldReturnAList() {

        TimeSeries timeSeries = null;
        try {
            timeSeries = metadataClient.getTimeSeries("quarterly-accounting-cantabria-base-2010-current-prices");
        } catch (SeriesNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert timeSeries != null;
        assertTrue("References must not be empty", timeSeries.getReferences().size() >= 1);
        assertEquals("Resource type of reference is publication", "Publicación",
                timeSeries.getReferences().get(0).getResourceType().toString());

    }

    @Test
    public void getTimeSeriesBaseShouldReturnStringValue() {

        TimeSeries timeSeries = null;
        try {
            timeSeries = metadataClient.getTimeSeries("quarterly-accounting-cantabria-base-2010-current-prices");
        } catch (SeriesNotFoundException e) {
            e.printStackTrace();
        }

        // assert statement
        assert timeSeries != null;
        assertEquals("Base must be 2010", "2010", timeSeries.getBase());

    }

    @Test
    public void getTimeSeriesInitialPeriodShouldReturnTimePeriodObject() {

        TimeSeries timeSeries1 = null;
        TimeSeries timeSeries2 = null;

        try {
            timeSeries1 = metadataClient.getTimeSeries("real-economic-destination-index-base-2010");
            timeSeries2 = metadataClient.getTimeSeries("primary-care-social-services");
        } catch (SeriesNotFoundException e) {
            e.printStackTrace();
        }

        // assert statement
        assert timeSeries1 != null;
        assertEquals("Initial time period id must be 259", 259, (int) timeSeries1.getInitialPeriodComposite().getId());
        assert timeSeries2 != null;
        assertEquals("Final time period id must be 822", 822, (int) timeSeries2.getFinalPeriodComposite().getId());

    }

    @Test
    public void getReferencesShouldReturnList() {

        List<Reference> references = metadataClient.getReferences();

        // assert statements
        assertTrue("Size must be greater than 10", references.size() > 10);
        assertEquals("1st element has uri http://www.icane.es/publications#quarterly-accounting-base-2008-nace09", "http://www.icane.es/publications#quarterly-accounting-base-2008-nace09", references.get(0).getUri());
    
    }

    @Test
    public void getUnitsOfMeasureShouldReturnList() {

        List<UnitOfMeasure> unitsOfMeasure = metadataClient.getUnitsOfMeasure();

        // assert statements
        assertTrue("Size must be greater than 100", unitsOfMeasure.size() > 100);
        assertEquals("First element title is Años", "Años", unitsOfMeasure.get(0).getTitle());
    }

    @Test
    public void getSubsectionShouldReturnElement() {

        Subsection subsection = null;
        try {
            subsection = metadataClient.getSubsection(1);
        } catch (SubsectionNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert subsection != null;
        assertEquals("id = 1 title must be Análisis demográficos", "Análisis demográficos", subsection.getTitle());

    }

    @Test
    public void getReferenceAreaShouldReturnElement() {

        ReferenceArea referenceArea = null;
        try {
            referenceArea = metadataClient.getReferenceArea("regional");
        } catch (ReferenceAreaNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert referenceArea != null;
        assertEquals("regional title must be Regional", "Regional", referenceArea.getTitle());

    }

    @Test
    public void getUnitOfMeasureShouldReturnElement() {

        UnitOfMeasure unitOfMeasure = null;
        try {
            unitOfMeasure = metadataClient.getUnitOfMeasure(125);
        } catch (UnitOfMeasureNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert unitOfMeasure != null;
        assertEquals("id = 125 title must be Número de días", "Número de días", unitOfMeasure.getTitle());

    }

    @Test
    public void getReferenceShouldReturnElement() {

        Reference reference = null;
        try {
            reference = metadataClient.getReference(136);
        } catch (ReferenceNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert reference != null;
        assertEquals("id = 136 uri must be http://www.icane.es/publications#quarterly-accounting-base-2008-nace09", "http://www.icane.es/publications#quarterly-accounting-base-2008-nace09", reference.getUri());

    }

    @Test
    public void getSectionShouldReturnElement() {

        Section section = null;
        try {
            section = metadataClient.getSection("economia");
        } catch (SectionNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert section != null;
        assertEquals("economy title must be Economía", "Economía", section.getTitle());

    }

    @Test
    public void getSectionsShouldReturnList() {

        List<Section> sections = metadataClient.getSections();

        // assert statements
        assertTrue("Size must be greater than 2", sections.size() > 2);
        assertEquals("First element has title Población", "Población", sections.get(0).getTitle());
    }

    @Test
    public void getReferenceAreasShouldReturnList() {

        List<ReferenceArea> referenceAreas = metadataClient.getReferenceAreas();

        // assert statements
        assertTrue("Size must be greater than 5", referenceAreas.size() > 5);
        assertEquals("Second element has title Regional", "Regional", referenceAreas.get(1).getTitle());
    }

    @Test
    public void getSubsectionsShouldReturnList() {

        List<Subsection> subsections = metadataClient.getSubsections("economia");

        // assert statements
        assertTrue("Size must be equal to 5", subsections.size() == 5);
        assertEquals("First element has title Cuentas Económicas", "Cuentas Económicas", subsections.get(0).getTitle());
        assertEquals("First element has id = 6", 6, subsections.get(0).getId().intValue());

    }

    @Test
    public void getMethodologyShouldReturnElement() {

        Methodology methodology = null;
        try {
            methodology = metadataClient.getMethodology(2);
        } catch (MethodologyNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert methodology != null;
        assertEquals("Title must be Metodología. Base 2000 CNAE 93", "Metodología. Base 2000 CNAE 93", methodology.getTitle());
        assert methodology.getUri() != null;
    
    }

    @Test
    public void getAncestorsShouldReturnAncestorList() {

        TimeSeries timeSeries = null;
        List<TimeSeries> ancestors = null;

        try {
            timeSeries = metadataClient.getTimeSeries("budgets-settlement-group-local-entities-municipality-commonwealth-income");
            ancestors = metadataClient.getAncestors(timeSeries);
        } catch (SeriesNotFoundException e) {
            e.printStackTrace();
        }

        ancestors.remove(0);
        List<Hyperlink> ancestorLinks = new ArrayList<Hyperlink>(ancestors.size());
        ancestorLinks.add(new Hyperlink(timeSeries.getSubsection().getSection().getTitle(), timeSeries.getSubsection().getSection().getUri()));
        ancestorLinks.add(new Hyperlink(timeSeries.getSubsection().getTitle(), timeSeries.getSubsection().getUri()));
        for (ListIterator<TimeSeries> it = ancestors.listIterator(ancestors.size()); it.hasPrevious(); ) {
            TimeSeries ancestor = it.previous();
            ancestorLinks.add(new Hyperlink(ancestor.getTitle(), ancestor.getUri()));
        }
        assertTrue("Size of ancestors must be 1", ancestors.size() == 1);
        assertEquals("First element has title Sector público", "Sector público", ancestorLinks.get(0).getTitle());

    }

}
