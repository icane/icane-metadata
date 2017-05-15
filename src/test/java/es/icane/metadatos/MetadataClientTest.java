package es.icane.metadatos;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import es.icane.metadatos.model.*;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class MetadataClientTest {

    private static MetadataClient metadataClient;

    @BeforeClass
    public static void setUp() {
        String baseUrl = "http://www.icane.es.es/metadata/api";
        metadataClient = new MetadataClient(baseUrl);
    }

    @Test
    public void getNodeTypeShouldReturnElement() {

        NodeType nodeType = null;
        try {
            nodeType = metadataClient.getNodeType("section");
        } catch (NodeTypeNotFoundException e) {
            // TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // assert statements
        assert timePeriod != null;
        assertEquals("Time period startYear must be 2010", 2010, timePeriod.getStartYear().intValue());
        assertEquals("Time period endYear must be 2011", 2011, timePeriod.getEndYear().intValue());

    }


    @Test
    public void getDataProvidersShouldReturnList() {

        List<DataProvider> dataProviders = metadataClient.getDataProviders();

        // assert statements
        assertTrue("Size must be greater than 100", dataProviders.size() > 100);
        assertEquals("First element has acronym INE", "INE", dataProviders.get(0).getAcronym());
    }

    @Test
    public void getDataSetsShouldReturnList() {

        List<DataSet> dataSets = metadataClient.getDataSets();

        // assert statements
        assertTrue("Size must be greater than 100", dataSets.size() > 100);
        assertEquals("20th element has acronym CGN", "CGN", dataSets.get(19).getAcronym());
    }

    @Test
    public void getPeriodicitiesShouldReturnList() {

        List<Periodicity> periodicities = metadataClient.getPeriodicities();

        // assert statements
        assertTrue("Size must be greater than 10", periodicities.size() > 10);
        assertEquals("10th element has uriTag decennial", "decennial", periodicities.get(9).getUriTag());
    }

    @Test
    public void getPeriodicityShouldReturnElement() {


        Periodicity periodicity = null;
        try {
            periodicity = metadataClient.getPeriodicity("annual");
        } catch (PeriodicityNotFoundException e) {
            // TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // assert statement
        assert timeSeries1 != null;
        assertEquals("Initial time period id must be 259", 259, (int) timeSeries1.getInitialPeriodComposite().getId());
        assert timeSeries2 != null;
        assertEquals("Initial time period id must be 649", 649, (int) timeSeries2.getFinalPeriodComposite().getId());

    }


    @Test
    public void getReferencesShouldReturnList() {

        List<Reference> references = metadataClient.getReferences();

        // assert statements
        assertTrue("Size must be greater than 10", references.size() > 10);
        assertEquals("1st element has uri http://www.icane.es/publications#quarterly-accounting-base-2008-nace09", "http://www.icane.es/publications#quarterly-accounting-base-2008-nace09", references.get(0).getUri());
    }



    @Test
    public void getLinkTypesShouldReturnEightElementsList() {

        List<LinkType> linkTypes = metadataClient.getLinkTypes();


        // assert statements
        assertTrue("Size must be equal or greater than eight", linkTypes.size() >= 8);

    }



    @Test
    public void getUnitsOfMeasureShouldReturnList() {

        List<UnitOfMeasure> unitsOfMeasure = metadataClient.getUnitsOfMeasure();


        // assert statements
        assertTrue("Size must be greater than 100", unitsOfMeasure.size() > 100);
        assertEquals("First element title is Años", "Años", unitsOfMeasure.get(0).getTitle());
    }

    @Test
    public void getMeasuresShouldReturnList() {

        List<Measure> measures = metadataClient.getMeasures();

        // assert statements
        assertTrue("Size must be greater than 100", measures.size() > 100);
        assertEquals("First element has title Parados", "Parados", measures.get(0).getTitle());
    }

    @Test
    public void getCategoryShouldReturnElement() {

        Category category = null;
        try {
            category = metadataClient.getCategory("regional-data");
        } catch (CategoryNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // assert statements
        assert category != null;
        assertEquals("regional-data title must be Datos regionales", "Datos regionales", category.getTitle());

    }

    @Test
    public void getSubsectionShouldReturnElement() {

        Subsection subsection = null;
        try {
            subsection = metadataClient.getSubsection(1);
        } catch (SubsectionNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // assert statements
        assert subsection != null;
        assertEquals("id = 1 title must be Cifras de población", "Cifras de población", subsection.getTitle());

    }

    @Test
    public void getReferenceAreaShouldReturnElement() {

        ReferenceArea referenceArea = null;
        try {
            referenceArea = metadataClient.getReferenceArea("regional");
        } catch (ReferenceAreaNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // assert statements
        assert referenceArea != null;
        assertEquals("regional title must be Regional", "Regional", referenceArea.getTitle());

    }


    @Test
    public void getDataSetShouldReturnElement() {

        DataSet dataSet = null;
        try {
            dataSet = metadataClient.getDataSet("musical-arts");
        } catch (DataSetNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // assert statements
        assert dataSet != null;
        assertEquals("musical-arts acronym must be AAMM", "AAMM", dataSet.getAcronym());

    }

    @Test
    public void getDataProviderShouldReturnElement() {

        DataProvider dataProvider = null;
        try {
            dataProvider = metadataClient.getDataProvider(1);
        } catch (DataProviderNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // assert statements
        assert dataProvider != null;
        assertEquals("musical-arts acronym must be INE", "INE", dataProvider.getAcronym());

    }

    @Test
    public void getMeasureShouldReturnElement() {

        Measure measure = null;
        try {
            measure = metadataClient.getMeasure(777);
        } catch (MeasureNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // assert statements
        assert measure != null;
        assertEquals("id = 777 title must be Tasa de variación mensual", "Tasa de variación mensual", measure.getTitle());

    }

    @Test
    public void getUnitOfMeasureShouldReturnElement() {

        UnitOfMeasure unitOfMeasure = null;
        try {
            unitOfMeasure = metadataClient.getUnitOfMeasure(125);
        } catch (UnitOfMeasureNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // assert statements
        assert unitOfMeasure != null;
        assertEquals("id = 125 title must be Número de días", "Número de días", unitOfMeasure.getTitle());

    }

    @Test
    public void getLinkShouldReturnElement() {

        Link link = null;
        try {
            link = metadataClient.getLink(473);
        } catch (LinkNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // assert statements
        assert link != null;
        assertEquals("id = 473 title must be DBpedia ES", "DBpedia ES", link.getTitle());

    }

    @Test
    public void getLinksShouldReturnList() {

        List<Link> links = metadataClient.getLinks();


        // assert statements
        assertTrue("Size must be greater than 50", links.size() > 50);
        assertEquals("First element title is DBpedia", "DBpedia", links.get(0).getTitle());
    }

    @Test
    public void getLinkTypeShouldReturnElement() {

        LinkType linkType = null;
        try {
            linkType = metadataClient.getLinkType(1);
        } catch (LinkTypeNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // assert statements
        assert linkType != null;
        assertEquals("id = 1 title must be HTTP", "HTTP", linkType.getTitle());

    }


    @Test
    public void getReferenceShouldReturnElement() {

        Reference reference = null;
        try {
            reference = metadataClient.getReference(136);
        } catch (ReferenceNotFoundException e) {
            // TODO Auto-generated catch block
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
            section = metadataClient.getSection("economy");
        } catch (SectionNotFoundException e) {
            // TODO Auto-generated catch block
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
    public void getCategoriesShouldReturnList() {

        List<Category> categories = metadataClient.getCategories();

        // assert statements
        assertEquals("Size must be 5", 5, categories.size());
        assertEquals("First element has title Datos regionales", "Datos regionales", categories.get(0).getTitle());
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

        List<Subsection> subsections = metadataClient.getSubsections("economy");

        // assert statements
        assertTrue("Size must be greater than 100", subsections.size() > 10);
        assertEquals("First element has title Cuentas Económicas", "Cuentas Económicas", subsections.get(0).getTitle());
        assertEquals("First element has id = 6", 6, subsections.get(0).getId().intValue());

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
        assertTrue("Size of ancestorLinks must be 4", ancestorLinks.size() == 4);

    }


}


