package es.icane.metadatos;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import es.icane.metadatos.model.*;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class MetadataClientTest {

	private static MetadataClient metadataClient;

	@BeforeClass
	public static void setUp() {
		String baseUrl = "http://marhaus.icane.es/metadata/api";
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
		assertTrue("Size must be equal or greater than ten", nodeTypes.size()>=10);

	}

    @Test
    public void updateNodeTypeWithItselfShouldReturnOk() {
        NodeType nodeType = null;
        NodeType updatedNodeType = null;

        try {
            nodeType = metadataClient.getNodeType("subsection");
        } catch (NodeTypeNotFoundException e) {
            e.printStackTrace();
        }

        metadataClient.updateNodeType(nodeType);

        try {
            updatedNodeType = metadataClient.getNodeType("subsection");
        } catch (NodeTypeNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements

        assert nodeType != null;
        assert updatedNodeType != null;
        assertEquals("Title should be the same ",
                updatedNodeType.getTitle(), nodeType.getTitle());
        assertEquals("UriTag should be the same ",
                updatedNodeType.getUriTag(), nodeType.getUriTag());

    }

    @Test
    public void createAndDeleteNodeTypeShouldReturnOk() {

        NodeType createdNodeType = null;
        NodeType retrievedNodeType = null;

        NodeType nodeType = new NodeType("tipo de nodo", "tipoPrueba", new Date(), new Date());
        createdNodeType = metadataClient.createNodeType(nodeType);
        try {
            retrievedNodeType = metadataClient.getNodeType(createdNodeType.getUriTag());
        } catch (NodeTypeNotFoundException e) {
            e.printStackTrace();
        }
        assert retrievedNodeType != null;
        assertEquals("Title should be the same ",
                createdNodeType.getTitle(), retrievedNodeType.getTitle());
        assertEquals("UriTag should be the same ",
                createdNodeType.getUriTag(), retrievedNodeType.getUriTag());


        try {
            metadataClient.deleteNodeType(createdNodeType.getId());
        } catch (NodeTypeNotFoundException e) {
            e.printStackTrace();
        }


    }

	@Test
	public void getTimePeriodsShouldReturnList() {

		List<TimePeriod> timePeriods = metadataClient.getTimePeriods();


		// assert statements
		assertTrue("Size must be greater than 100", timePeriods.size() > 100);
		assertEquals("First element is id 246",246, timePeriods.get(0).getId().intValue());
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
    public void updateTimePeriodWithItselfShouldReturnOk() {
        TimePeriod timePeriod = null;
        TimePeriod updatedTimePeriod = null;

        try {
            timePeriod = metadataClient.getTimePeriod(465);
        } catch (TimePeriodNotFoundException e) {
            e.printStackTrace();
        }

        metadataClient.updateTimePeriod(timePeriod);

        try {
            updatedTimePeriod = metadataClient.getTimePeriod(465);
        } catch (TimePeriodNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements

        assert timePeriod != null;
        assert updatedTimePeriod != null;
        assertEquals("Start year should be the same ",
                updatedTimePeriod.getStartYear(), timePeriod.getStartYear());
        assertEquals("End year should be the same ",
                updatedTimePeriod.getEndYear(), timePeriod.getEndYear());

    }

    @Test
    public void createAndDeleteTimePeriodShouldReturnOk() {

        TimePeriod createdTimePeriod = null;
        TimePeriod retrievedTimePeriod = null;

        TimePeriod timePeriod = new TimePeriod(1, null, 2018, null, null, null, "year", new Date(), new Date());
        createdTimePeriod = metadataClient.createTimePeriod(timePeriod);
        try {
            retrievedTimePeriod = metadataClient.getTimePeriod(createdTimePeriod.getId());
        } catch (TimePeriodNotFoundException e) {
            e.printStackTrace();
        }
        assert retrievedTimePeriod != null;
        assertEquals("Start month should be the same ",
                createdTimePeriod.getStartMonth(), retrievedTimePeriod.getStartMonth());
        assertEquals("Start year should be the same ",
                createdTimePeriod.getStartYear(), retrievedTimePeriod.getStartYear());


        try {
            metadataClient.deleteTimePeriod(createdTimePeriod.getId());
        } catch (TimePeriodNotFoundException e) {
            e.printStackTrace();
        }


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
		assertEquals("20th element has acronym CENSO", "CENSO", dataSets.get(19).getAcronym());
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
    public void updatePeriodicityWithItselfShouldReturnOk() {
        Periodicity periodicity = null;
        Periodicity updatedPeriodicity = null;

        try {
            periodicity = metadataClient.getPeriodicity("monthly");
        } catch (PeriodicityNotFoundException e) {
            e.printStackTrace();
        }

        metadataClient.updatePeriodicity(periodicity);

        try {
            updatedPeriodicity = metadataClient.getPeriodicity("monthly");
        } catch (PeriodicityNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements

        assert periodicity != null;
        assert updatedPeriodicity != null;
        assertEquals("Title should be the same ",
                updatedPeriodicity.getTitle(), periodicity.getTitle());
        assertEquals("UriTag should be the same ",
                updatedPeriodicity.getUriTag(), periodicity.getUriTag());

    }

    @Test
    public void createAndDeletePeriodicityShouldReturnOk() {

        Periodicity createdPeriodicity = null;
        Periodicity retrievedPeriodicity = null;
        Periodicity periodicity = new Periodicity("periocididad de prueba", "http://purl.org/cld/freq/prueba", "prueba-period", new Date(), new Date());
        createdPeriodicity = metadataClient.createPeriodicity(periodicity);
        try {
            retrievedPeriodicity = metadataClient.getPeriodicity(createdPeriodicity.getUriTag());
        } catch (PeriodicityNotFoundException e) {
            e.printStackTrace();
        }
        assert retrievedPeriodicity != null;
        assertEquals("Title should be the same ",
                createdPeriodicity.getTitle(), retrievedPeriodicity.getTitle());
        assertEquals("UriTag should be the same ",
                createdPeriodicity.getUriTag(), retrievedPeriodicity.getUriTag());


        try {
            metadataClient.deletePeriodicity(createdPeriodicity.getId());
        } catch (PeriodicityNotFoundException e) {
            e.printStackTrace();
        }
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
        assertEquals("Initial time period id must be 575", 575, (int) timeSeries2.getFinalPeriodComposite().getId());

	}

    @Test
    public void updateTimeSeriesWithSimpleDataShouldReturnOk() {

		TimeSeries timeSeries = new TimeSeries();
		timeSeries.setId(5930);
		timeSeries.setCode("put-test-xml-object");
        metadataClient.updateTimeSeries(timeSeries);

        TimeSeries retrievedTimeSeries = null;
        try {
            retrievedTimeSeries = metadataClient.getTimeSeries("industrial-production-index-base-2010");

        } catch (SeriesNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // assert statement
        assert retrievedTimeSeries != null;
        assertEquals("Description should be equal to put-test-object",
                "put-test-xml-object",  retrievedTimeSeries.getCode());


    }

    @After
    public void restoreCode() {
        TimeSeries timeSeries = new TimeSeries();
        timeSeries.setId(5930);
        timeSeries.setCode("none");
        metadataClient.updateTimeSeries(timeSeries);
    }

    @Test
    public void updateTimeSeriesWithComplexDataShouldReturnOk() {
        Subsection subsection = null;
        try {
           subsection = metadataClient.getSubsection(10);
        } catch (SubsectionNotFoundException e) {
            e.printStackTrace();
        }

        TimeSeries timeSeries = new TimeSeries();
        timeSeries.setId(5930);
        timeSeries.setSubsection(subsection);
        metadataClient.updateTimeSeries(timeSeries);

        TimeSeries retrievedTimeSeries = null;
        try {
            retrievedTimeSeries = metadataClient.getTimeSeries("industrial-production-index-base-2010");

        } catch (SeriesNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // assert statement
        assert retrievedTimeSeries != null;
        assertEquals("New subsection uri-tag should be equal to primary-sector",
                "primary-sector",  retrievedTimeSeries.getSubsection().getUriTag());


    }

    @After
    public void restoreSubsection() {
        Subsection subsection = null;
        try {
            subsection = metadataClient.getSubsection(11);
        } catch (SubsectionNotFoundException e) {
            e.printStackTrace();
        }
        TimeSeries timeSeries = new TimeSeries();
        timeSeries.setId(5930);
        timeSeries.setSubsection(subsection);
        metadataClient.updateTimeSeries(timeSeries);
    }

    @Test
    public void updateTimeSeriesWithItselfShouldReturnOk() {
        List<String>  uriTags = asList("real-economic-destination-index-base-2010",
                                        "bankruptcy-statistics-procedure-existence",
                                         "industrial-production-index-base-2010");

        TimeSeries timeSeries = null;
        for (String uriTag : uriTags) {
            try {
                timeSeries = metadataClient.getTimeSeries(uriTag);


            } catch (SeriesNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            metadataClient.updateTimeSeries(timeSeries);

            TimeSeries retrievedTimeSeries = null;
            try {
                retrievedTimeSeries = metadataClient.getTimeSeries(uriTag);

            } catch (SeriesNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            // assert statement
            assert retrievedTimeSeries != null;
            assert timeSeries != null;
            assertEquals("Time series uriTags should be equal",
                    timeSeries.getUriTag(), retrievedTimeSeries.getUriTag());
        }

    }

    @Test
    public void updateMeasureShouldReturnOk() {
        Measure measure = null;
        Measure updatedMeasure = null;
        TimeSeries timeSeries = null;
        try {
            measure = metadataClient.getMeasure(1);
        } catch (MeasureNotFoundException e) {
            e.printStackTrace();
        }
        try {
            timeSeries = metadataClient.getTimeSeries("real-economic-destination-index-base-2010");
        } catch (SeriesNotFoundException e) {
            e.printStackTrace();
        }

        assert timeSeries != null;
        assert measure != null;
        measure.setNode(timeSeries.getUriTag());
        measure.setTitle("prueba");
        metadataClient.updateMeasure(measure);

        try {
            updatedMeasure = metadataClient.getMeasure(1);
        } catch (MeasureNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert updatedMeasure != null;
        assertEquals("Associated Time series uriTags should be equal",
                timeSeries.getUriTag(), updatedMeasure.getNode());
        assertEquals("Title should be equal to prueba",
                updatedMeasure.getTitle(), "prueba");

    }

    @After
    public void restoreMeasure() {
        TimeSeries timeSeries = null;
        Measure measure = null;
        try {
            timeSeries = metadataClient.getTimeSeries(2901);
        } catch (SeriesNotFoundException e) {
            e.printStackTrace();
        }
        try {
            measure = metadataClient.getMeasure(1);
        } catch (MeasureNotFoundException e) {
            e.printStackTrace();
        }
        assert timeSeries != null;
        assert measure != null;
        measure.setNode(timeSeries.getUriTag());
        measure.setTitle("Parados");
        metadataClient.updateMeasure(measure);
    }

    @Test
    public void updateMeasureWithItselfShouldReturnOk() {
        Measure measure = null;
        Measure updatedMeasure = null;

        try {
            measure = metadataClient.getMeasure(1);
        } catch (MeasureNotFoundException e) {
            e.printStackTrace();
        }

        metadataClient.updateMeasure(measure);

        try {
            updatedMeasure = metadataClient.getMeasure(1);
        } catch (MeasureNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements

        assert measure != null;
        assert updatedMeasure != null;
        assertEquals("Title should be the same ",
                updatedMeasure.getTitle(), measure.getTitle());
        assertEquals("Node should be the same ",
                updatedMeasure.getNode(), measure.getNode());

    }

    @Test
    public void createAndDeleteMeasureShouldReturnOk() {
        UnitOfMeasure unitOfMeasure = null;
        Measure createdMeasure;
        Measure retrievedMeasure = null;
        try {
            unitOfMeasure = metadataClient.getUnitOfMeasure(1);
        } catch (UnitOfMeasureNotFoundException e) {
            e.printStackTrace();
        }

        Measure measure = new Measure("códigoDePrueba", "Medida de prueba", unitOfMeasure, "real-economic-destination-index-base-2010", false, 1, null, new Date(), new Date());
        createdMeasure = metadataClient.createMeasure(measure);
        try {
            retrievedMeasure = metadataClient.getMeasure(createdMeasure.getId());
        } catch (MeasureNotFoundException e) {
            e.printStackTrace();
        }
        assert retrievedMeasure != null;
        assertEquals("Title should be the same ",
                createdMeasure.getTitle(), retrievedMeasure.getTitle());
        assertEquals("Node should be the same ",
                createdMeasure.getNode(), retrievedMeasure.getNode());
        assertEquals("UnitOfMeasure should be the same ",
                createdMeasure.getUnitOfMeasure().getId(), retrievedMeasure.getUnitOfMeasure().getId());

        try {
            metadataClient.deleteMeasure(createdMeasure.getId());
        } catch (MeasureNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void updateReferenceWithItselfShouldReturnOk() {
        Reference reference = null;
        Reference updatedReference = null;

        try {
            reference = metadataClient.getReference(9);
        } catch (ReferenceNotFoundException e) {
            e.printStackTrace();
        }
        assert reference != null;
        reference.setNodeUriTag(reference.getNode());
        metadataClient.updateReference(reference);

        try {
            updatedReference = metadataClient.getReference(9);
        } catch (ReferenceNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements

        assert updatedReference != null;
        assertEquals("Title should be the same ",
                updatedReference.getTitle(), reference.getTitle());
        assertEquals("Node should be the same ",
                updatedReference.getNode(), reference.getNode());

    }

    @Test
    public void updateReferenceShouldReturnOk() {
        Reference reference = null;
        Reference updatedReference = null;
        TimeSeries timeSeries = null;
        try {
            reference = metadataClient.getReference(9);
        } catch (ReferenceNotFoundException e) {
            e.printStackTrace();
        }
        try {
            timeSeries = metadataClient.getTimeSeries("real-economic-destination-index-base-2010");
        } catch (SeriesNotFoundException e) {
            e.printStackTrace();
        }

        assert timeSeries != null;
        assert reference != null;
        reference.setNodeUriTag(timeSeries.getUriTag());
        reference.setResourceType(ResourceType.OPENDATA);
        reference.setTitle("TÍTULO DE PRUEBA");
        metadataClient.updateReference(reference);

        try {
            updatedReference = metadataClient.getReference(9);
        } catch (ReferenceNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert updatedReference != null;
        assertEquals("Associated Time series uriTags should be equal",
                timeSeries.getUriTag(), updatedReference.getNode());
        assertEquals("ResourceType should be equal to opendata",
                updatedReference.getResourceType().toString(), "Correspondencia en portal de datos abiertos");
        assertEquals("Title should be equal to test string", updatedReference.getTitle(), "TÍTULO DE PRUEBA");
    }

    @After
    public void restoreReference() {
        TimeSeries timeSeries = null;
        Reference reference = null;
        try {
            timeSeries = metadataClient.getTimeSeries(163);
        } catch (SeriesNotFoundException e) {
            e.printStackTrace();
        }
        try {
            reference = metadataClient.getReference(9);
        } catch (ReferenceNotFoundException e) {
            e.printStackTrace();
        }
        assert timeSeries != null;
        assert reference != null;
        reference.setNodeUriTag(timeSeries.getUriTag());
        reference.setTitle("Contabilidad Trimestral de Cantabria");
        reference.setResourceType(ResourceType.PUBLICATION);
        metadataClient.updateReference(reference);
    }

    @Test
    public void createAndDeleteReferenceShouldReturnOk() {

        Reference createdReference;
        Reference retrievedReference = null;
        TimeSeries node = null;
        try {
            node = metadataClient.getTimeSeries("real-economic-destination-index-base-2010");
        } catch (SeriesNotFoundException e) {
            e.printStackTrace();
        }

        Reference reference = new Reference("título de prueba", "http://datos.icane.es", node, ResourceType.STATISTICAL_OPERATION, new Date(), new Date());
        createdReference = metadataClient.createReference(reference);
        try {
            retrievedReference = metadataClient.getReference(createdReference.getId());
        } catch (ReferenceNotFoundException e) {
            e.printStackTrace();
        }
        assert retrievedReference != null;
        assertEquals("Title should be the same ",
                createdReference.getTitle(), retrievedReference.getTitle());
        assertEquals("Node should be the same ",
                createdReference.getNode(), retrievedReference.getNode());
        assertEquals("Resource type should be the same ",
                createdReference.getResourceType().toString(), retrievedReference.getResourceType().toString());

        try {
            metadataClient.deleteReference(createdReference.getId());
        } catch (ReferenceNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    @Test
    public void getReferencesShouldReturnList() {

        List<Reference> references = metadataClient.getReferences();

        // assert statements
        assertTrue("Size must be greater than 10", references.size() > 10);
        assertEquals("1st element has title Contabilidad Trimestal de Cantabria", "Contabilidad Trimestral de Cantabria", references.get(0).getTitle());
    }

    @Test
    public void updateLinkWithItselfShouldReturnOk() {
        Link link = null;
        Link updatedLink = null;

        try {
            link = metadataClient.getLink(472);
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }
        assert link != null;
        link.setSectionUriTag(link.getSection());

        metadataClient.updateLink(link);

        try {
            updatedLink = metadataClient.getLink(472);
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements

        assert updatedLink != null;
        assertEquals("Title should be the same ",
                updatedLink.getTitle(), link.getTitle());
        assertEquals("Section should be the same ",
                updatedLink.getSection(), link.getSection());

    }

    @Test
    public void updateLinkWithNodeShouldReturnOk() {
        Link link = null;
        Link updatedLink = null;
        TimeSeries timeSeries = null;
        LinkType linkType = null;

        try {
            linkType = metadataClient.getLinkType(2);
        } catch (LinkTypeNotFoundException e) {
            e.printStackTrace();
        }

        try {
            link = metadataClient.getLink(727);
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }
        try {
            timeSeries = metadataClient.getTimeSeries("real-economic-destination-index-base-2010");
        } catch (SeriesNotFoundException e) {
            e.printStackTrace();
        }

        assert timeSeries != null;
        assert link != null;
        link.setNodeUriTag(timeSeries.getUriTag());
        link.setLinkType(linkType);
        link.setTitle("INE MODIFICADO");
        metadataClient.updateLink(link);

        try {
            updatedLink = metadataClient.getLink(727);
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }
      
        // assert statements
        assert updatedLink != null;
        assertEquals("Associated Time series uriTags should be equal",
                timeSeries.getUriTag(), updatedLink.getNode());
        assertEquals("LinkType should be equal",
                updatedLink.getLinkType(), linkType);

    }

    @After
    public void restoreLink() {
        TimeSeries timeSeries = null;
        Link link = null;
        LinkType linkType = null;

        try {
            linkType = metadataClient.getLinkType(6);
        } catch (LinkTypeNotFoundException e) {
            e.printStackTrace();
        }
        try {
            timeSeries = metadataClient.getTimeSeries(112);
        } catch (SeriesNotFoundException e) {
            e.printStackTrace();
        }
        try {
            link = metadataClient.getLink(727);
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }
        assert timeSeries != null;
        assert link != null;
        link.setNodeUriTag(timeSeries.getUriTag());
        link.setLinkType(linkType);
        link.setTitle("INE");
        metadataClient.updateLink(link);
    }

    @Test
    public void updateLinkWithReferenceAreaShouldReturnOk() {
        Link link = null;
        Link updatedLink = null;
        ReferenceArea referenceArea = null;
        LinkType linkType = null;

        try {
            linkType = metadataClient.getLinkType(2);
        } catch (LinkTypeNotFoundException e) {
            e.printStackTrace();
        }

        try {
            link = metadataClient.getLink(727);
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }
        try {
            referenceArea = metadataClient.getReferenceArea("municipal");
        } catch (ReferenceAreaNotFoundException e) {
            e.printStackTrace();
        }

        assert link != null;
        link.setNodeUriTag(null);
        assert referenceArea != null;
        link.setReferenceAreaUriTag(referenceArea.getUriTag());
        link.setLinkType(linkType);
        link.setUri("http://urideprueba.com");
        link.setTitle("INE MODIFICADO");
        metadataClient.updateLink(link);

        try {
            updatedLink = metadataClient.getLink(727);
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert updatedLink != null;
        assertEquals("Associated ReferenceArea uriTags should be equal",
                referenceArea.getUriTag(), updatedLink.getReferenceArea());
        assertEquals("LinkType should be equal",
                updatedLink.getLinkType(), linkType);
        assertEquals("Title should be equal to test string", "INE MODIFICADO",  updatedLink.getTitle());
        assertEquals("URI should be equal to test string", "http://urideprueba.com",  updatedLink.getUri());

    }


    @After
    public void restoreLinkToNode() {
        TimeSeries timeSeries = null;
        Link link = null;
        LinkType linkType = null;

        try {
            linkType = metadataClient.getLinkType(6);
        } catch (LinkTypeNotFoundException e) {
            e.printStackTrace();
        }
        try {
            timeSeries = metadataClient.getTimeSeries(112);
        } catch (SeriesNotFoundException e) {
            e.printStackTrace();
        }
        try {
            link = metadataClient.getLink(727);
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }
        assert link != null;
        link.setReferenceAreaUriTag(null);
        assert timeSeries != null;
        link.setNodeUriTag(timeSeries.getUriTag());
        link.setUri("http://www.ine.es/jaxi/menu.do?type=pcaxis&path=%2Ft20%2Fp318&file=inebase&L=0");

        link.setLinkType(linkType);
        link.setTitle("INE");
        metadataClient.updateLink(link);
    }

    @Test
    public void createAndDeleteLinkShouldReturnOk() {

        Link createdLink;
        Link retrievedLink = null;
        LinkType linkType = null;
        ReferenceArea referenceArea = null;

        try {
            referenceArea = metadataClient.getReferenceArea("municipal");
        } catch (ReferenceAreaNotFoundException e) {
            e.printStackTrace();
        }

        try {
            linkType = metadataClient.getLinkType(3);
        } catch (LinkTypeNotFoundException e) {
            e.printStackTrace();
        }

        Link link = new Link("Google", "http://www.google.com", linkType, new Date(), new Date(), referenceArea);
        createdLink = metadataClient.createLink(link);
        try {
            retrievedLink = metadataClient.getLink(createdLink.getId());
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }
        assert retrievedLink != null;
        assertEquals("Title should be the same ",
                createdLink.getTitle(), retrievedLink.getTitle());
        assertEquals("ReferenceArea should be the same ",
                createdLink.getReferenceArea(), retrievedLink.getReferenceArea());
        assertEquals("Link type should be the same ",
                createdLink.getLinkType().toString(), retrievedLink.getLinkType().toString());

        try {
            metadataClient.deleteLink(createdLink.getId());
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getLinkTypesShouldReturnEightElementsList() {

        List<LinkType> linkTypes = metadataClient.getLinkTypes();


        // assert statements
        assertTrue("Size must be equal or greater than eight", linkTypes.size() >=8);

    }

    @Test
    public void createAndDeleteLinkAssociatedToNodeShouldReturnOk() {

        Link createdLink;
        Link retrievedLink = null;
        TimeSeries node = null;
        LinkType linkType = null;

        try {
            linkType = metadataClient.getLinkType(3);
        } catch (LinkTypeNotFoundException e) {
            e.printStackTrace();
        }

        try {
            node = metadataClient.getTimeSeries("real-economic-destination-index-base-2010");
        } catch (SeriesNotFoundException e) {
            e.printStackTrace();
        }

        Link link = new Link("Google", "http://www.google.com", linkType, new Date(), new Date(), node);
        createdLink = metadataClient.createLink(link);
        try {
            retrievedLink = metadataClient.getLink(createdLink.getId());
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }
        assert retrievedLink != null;
        assertEquals("Title should be the same ",
                createdLink.getTitle(), retrievedLink.getTitle());
        assertEquals("Node should be the same ",
                createdLink.getNode(), retrievedLink.getNode());
        assertEquals("Link type should be the same ",
                createdLink.getLinkType().toString(), retrievedLink.getLinkType().toString());

        try {
            metadataClient.deleteLink(createdLink.getId());
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getUnitsOfMeasureShouldReturnList() {

        List<UnitOfMeasure> unitsOfMeasure = metadataClient.getUnitsOfMeasure();


        // assert statements
        assertTrue("Size must be greater than 100", unitsOfMeasure.size() > 100);
        assertEquals("First element title is Años","Años", unitsOfMeasure.get(0).getTitle());
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
    public void updateReferenceAreaWithItselfShouldReturnOk() {
        ReferenceArea referenceArea = null;
        ReferenceArea updatedReferenceArea = null;

        try {
            referenceArea = metadataClient.getReferenceArea("municipal");
        } catch (ReferenceAreaNotFoundException e) {
            e.printStackTrace();
        }

        metadataClient.updateReferenceArea(referenceArea);

        try {
            updatedReferenceArea = metadataClient.getReferenceArea("municipal");
        } catch (ReferenceAreaNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements

        assert referenceArea != null;
        assert updatedReferenceArea != null;
        assertEquals("Title should be the same ",
                updatedReferenceArea.getTitle(), referenceArea.getTitle());
        assertEquals("UriTag should be the same ",
                updatedReferenceArea.getUriTag(), referenceArea.getUriTag());
        assertTrue("Reference Area municipal links should be greated than 1", updatedReferenceArea.getLinks().size() > 1);

    }

    @Test
    public void createAndDeleteReferenceAreaShouldReturnOk() {

        ReferenceArea createdReferenceArea = null;
        ReferenceArea retrievedReferenceArea = null;
        ReferenceArea referenceArea = new ReferenceArea("ámbito territorial", "territorial-prueba", new Date(), new Date());

        
        createdReferenceArea = metadataClient.createReferenceArea(referenceArea);
        try {
            retrievedReferenceArea = metadataClient.getReferenceArea(createdReferenceArea.getUriTag());
        } catch (ReferenceAreaNotFoundException e) {
            e.printStackTrace();
        }
        assert retrievedReferenceArea != null;
        assertEquals("Title should be the same ",
                createdReferenceArea.getTitle(), retrievedReferenceArea.getTitle());
        assertEquals("UriTag should be the same ",
                createdReferenceArea.getUriTag(), retrievedReferenceArea.getUriTag());


        try {
            metadataClient.deleteReferenceArea(createdReferenceArea.getId());
        } catch (ReferenceAreaNotFoundException e) {
            e.printStackTrace();
        }


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
        assertEquals("First element title is DBpedia","DBpedia", links.get(0).getTitle());
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
    public void updateLinkTypeWithItselfShouldReturnOk() {
        LinkType linkType = null;
        LinkType updatedLinkType = null;

        try {
            linkType = metadataClient.getLinkType(1);
        } catch (LinkTypeNotFoundException e) {
            e.printStackTrace();
        }

        metadataClient.updateLinkType(linkType);

        try {
            updatedLinkType = metadataClient.getLinkType(1);
        } catch (LinkTypeNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements

        assert linkType != null;
        assert updatedLinkType != null;
        assertEquals("Title should be the same ",
                updatedLinkType.getTitle(), linkType.getTitle());

    }

    @Test
    public void createAndDeleteLinkTypeShouldReturnOk() {

        LinkType createdLinkType = null;
        LinkType retrievedLinkType = null;
        LinkType linkType = new LinkType("enlace FTP", null, null, new Date(), new Date());
        createdLinkType = metadataClient.createLinkType(linkType);
        try {
            retrievedLinkType = metadataClient.getLinkType(createdLinkType.getId());
        } catch (LinkTypeNotFoundException e) {
            e.printStackTrace();
        }
        assert retrievedLinkType != null;
        assertEquals("Title should be the same ",
                createdLinkType.getTitle(), retrievedLinkType.getTitle());
        assertEquals("UriTag should be the same ",
                createdLinkType.getId(), retrievedLinkType.getId());


        try {
            metadataClient.deleteLinkType(createdLinkType.getId());
        } catch (LinkTypeNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void getReferenceShouldReturnElement() {

        Reference reference = null;
        try {
            reference = metadataClient.getReference(9);
        } catch (ReferenceNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // assert statements
        assert reference != null;
        assertEquals("id = 9 title must be Contabilidad Trimestral de Cantabria", "Contabilidad Trimestral de Cantabria", reference.getTitle());

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
        assertEquals("Size must be 4", 4, categories.size());
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
    public void createAndDeleteUnitOfMeasureShouldReturnOk() {
        UnitOfMeasure createdUnitOfMeasure;
        UnitOfMeasure retrievedUnitOfMeasure = null;

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure(new Date(), new Date(), "symbol", "Unidad de medida de prueba");
        createdUnitOfMeasure = metadataClient.createUnitOfMeasure(unitOfMeasure);

        try {
            retrievedUnitOfMeasure = metadataClient.getUnitOfMeasure(createdUnitOfMeasure.getId());
        } catch (UnitOfMeasureNotFoundException e) {
            e.printStackTrace();
        }

        //assert statements
        assert retrievedUnitOfMeasure != null;
        assertEquals("Title should be the same ",
                createdUnitOfMeasure.getTitle(), retrievedUnitOfMeasure.getTitle());
        assertEquals("Node should be the same ",
                createdUnitOfMeasure.getId(), retrievedUnitOfMeasure.getId());

        try {
            metadataClient.deleteUnitOfMeasure(createdUnitOfMeasure.getId());
        } catch (UnitOfMeasureNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUnitOfMeasureWithItselfShouldReturnOk() {
        UnitOfMeasure unitOfMeasure = null;
        UnitOfMeasure updatedUnitOfMeasure = null;

        try {
            unitOfMeasure = metadataClient.getUnitOfMeasure(1);
        } catch (UnitOfMeasureNotFoundException e) {
            e.printStackTrace();
        }

        metadataClient.updateUnitOfMeasure(unitOfMeasure);

        try {
            updatedUnitOfMeasure = metadataClient.getUnitOfMeasure(1);
        } catch (UnitOfMeasureNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert updatedUnitOfMeasure != null;
        assert unitOfMeasure != null;
        assertEquals("Title should be the same ",
                updatedUnitOfMeasure.getTitle(), unitOfMeasure.getTitle());
        assertEquals("Node should be the same ",
                updatedUnitOfMeasure.getId(), unitOfMeasure.getId());

    }

    @Test
    public void createAndDeleteSubsectionShouldReturnOk() {

        Subsection createdSubsection;
        Subsection retrievedSubsection = null;
        Section section = null;

        try {
            section = metadataClient.getSection("economy");
        } catch (SectionNotFoundException e) {
            e.printStackTrace();
        }

        Subsection subsection = new Subsection("Subsección de prueba", "PRUEBA", "prueba", "tema, prueba, subsección", "código", section, new Date(), new Date());
        createdSubsection = metadataClient.createSubsection(subsection);

        try {
            retrievedSubsection = metadataClient.getSubsection(createdSubsection.getId());
        } catch (SubsectionNotFoundException e) {
            e.printStackTrace();
        }

        //assert statements
        assert retrievedSubsection != null;
        assertEquals("Title should be the same ",
                createdSubsection.getTitle(), retrievedSubsection.getTitle());
        assertEquals("Node should be the same ",
                createdSubsection.getId(), retrievedSubsection.getId());

        try {
            metadataClient.deleteSubsection(createdSubsection.getId());
        } catch (SubsectionNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateSubsectionWithItselfShouldReturnOk() {
        Subsection subsection = null;
        Subsection updatedSubsection = null;

        try {
            subsection = metadataClient.getSubsection(1);
        } catch (SubsectionNotFoundException e) {
            e.printStackTrace();
        }

        metadataClient.updateSubsection(subsection);

        try {
            updatedSubsection = metadataClient.getSubsection(1);
        } catch (SubsectionNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert updatedSubsection != null;
        assert subsection != null;
        assertEquals("Title should be the same ",
                updatedSubsection.getTitle(), subsection.getTitle());
        assertEquals("Node should be the same ",
                updatedSubsection.getId(), subsection.getId());

    }

    @Test
    public void createAndDeleteSectionShouldReturnOk() {

        Section createdSection;
        Section retrievedSection = null;

        Section section = new Section("Sección de prueba", "PRUEBA", "prueba", "tema, prueba, subsección", "código", new Date(), new Date());
        createdSection = metadataClient.createSection(section);

        try {
            retrievedSection = metadataClient.getSection(createdSection.getUriTag());
        } catch (SectionNotFoundException e) {
            e.printStackTrace();
        }

        //assert statements
        assert retrievedSection != null;
        assertEquals("Title should be the same ",
                createdSection.getTitle(), retrievedSection.getTitle());
        assertEquals("Code should be the same ",
                createdSection.getCode(), retrievedSection.getCode());

        try {
            metadataClient.deleteSection(createdSection.getId());
        } catch (SectionNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateSectionWithItselfShouldReturnOk() {
        Section section = null;
        Section updatedSection = null;

        try {
            section = metadataClient.getSection("economy");
        } catch (SectionNotFoundException e) {
            e.printStackTrace();
        }

        metadataClient.updateSection(section);

        try {
            updatedSection = metadataClient.getSection("economy");
        } catch (SectionNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert updatedSection != null;
        assert section != null;
        assertEquals("Title should be the same ",
                updatedSection.getTitle(), section.getTitle());
        assertEquals("Node should be the same ",
                updatedSection.getId(), section.getId());

    }

    @Test
    public void createAndDeleteDataSetShouldReturnOk() {

        DataSet createdDataSet;
        DataSet retrievedDataSet = null;

        DataSet dataSet = new DataSet("DataSet de prueba", "PRUEBA", "uri-tag-data-set-prueba", new Date(), new Date());
        createdDataSet = metadataClient.createDataSet(dataSet);

        try {
            retrievedDataSet = metadataClient.getDataSet(createdDataSet.getUriTag());
        } catch (DataSetNotFoundException e) {
            e.printStackTrace();
        }

        //assert statements
        assert retrievedDataSet != null;
        assertEquals("Title should be the same ",
                createdDataSet.getTitle(), retrievedDataSet.getTitle());
        assertEquals("Node should be the same ",
                createdDataSet.getId(), retrievedDataSet.getId());

        try {
            metadataClient.deleteDataSet(createdDataSet.getId());
        } catch (DataSetNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void createAndDeleteCategoryShouldReturnOk() {

        Category createdCategory;
        Category retrievedCategory = null;

        Category category = new Category("Categoría nueva", "CATEGORIA", "C", "new-category", new Date(), new Date());
        createdCategory = metadataClient.createCategory(category);

        try {
            retrievedCategory = metadataClient.getCategory(createdCategory.getUriTag());
        } catch (CategoryNotFoundException e) {
            e.printStackTrace();
        }

        //assert statements
        assert retrievedCategory != null;
        assertEquals("Title should be the same ",
                createdCategory.getTitle(), retrievedCategory.getTitle());
        assertEquals("Node should be the same ",
                createdCategory.getId(), retrievedCategory.getId());

        try {
            metadataClient.deleteCategory(createdCategory.getId());
        } catch (CategoryNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createAndDeleteDataProviderShouldReturnOk() {

        DataProvider createdDataProvider;
        DataProvider retrievedDataProvider = null;

        DataProvider dataProvider = new DataProvider("Nuevo data provider", "NUEVO", "A000035", "http://www.google.com", new Date(), new Date());
        createdDataProvider = metadataClient.createDataProvider(dataProvider);

        try {
            retrievedDataProvider = metadataClient.getDataProvider(createdDataProvider.getId());
        } catch (DataProviderNotFoundException e) {
            e.printStackTrace();
        }

        //assert statements
        assert retrievedDataProvider != null;
        assertEquals("Title should be the same ",
                createdDataProvider.getTitle(), retrievedDataProvider.getTitle());
        assertEquals("Id should be the same ",
                createdDataProvider.getId(), retrievedDataProvider.getId());

        try {
            metadataClient.deleteDataProvider(createdDataProvider.getId());
        } catch (DataProviderNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateDataSetWithItselfShouldReturnOk() {
        DataSet dataSet = null;
        DataSet updatedDataSet = null;

        try {
            dataSet = metadataClient.getDataSet("water");
        } catch (DataSetNotFoundException e) {
            e.printStackTrace();
        }

        metadataClient.updateDataSet(dataSet);

        try {
            updatedDataSet = metadataClient.getDataSet("water");
        } catch (DataSetNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert updatedDataSet != null;
        assert dataSet != null;
        assertEquals("Title should be the same ",
                updatedDataSet.getTitle(), updatedDataSet.getTitle());
        assertEquals("Node should be the same ",
                updatedDataSet.getId(),dataSet.getId());

    }

    @Test
    public void updateCategoryWithItselfShouldReturnOk() {
        Category category = null;
        Category updatedCategory = null;

        try {
            category = metadataClient.getCategory("regional-data");
        } catch (CategoryNotFoundException e) {
            e.printStackTrace();
        }

        metadataClient.updateCategory(category);

        try {
            updatedCategory = metadataClient.getCategory("regional-data");
        } catch (CategoryNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert updatedCategory != null;
        assert category != null;
        assertEquals("Title should be the same ",
                updatedCategory.getTitle(), updatedCategory.getTitle());
        assertEquals("Node should be the same ",
                updatedCategory.getId(),category.getId());

    }

    @Test
    public void updateDataProviderWithItselfShouldReturnOk() {
        DataProvider dataProvider = null;
        DataProvider updatedDataProvider = null;

        try {
            dataProvider = metadataClient.getDataProvider(1);
        } catch (DataProviderNotFoundException e) {
            e.printStackTrace();
        }

        metadataClient.updateDataProvider(dataProvider);

        try {
            updatedDataProvider = metadataClient.getDataProvider(1);
        } catch (DataProviderNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
        assert updatedDataProvider != null;
        assert dataProvider != null;
        assertEquals("Title should be the same ",
                updatedDataProvider.getTitle(), updatedDataProvider.getTitle());
        assertEquals("Node should be the same ",
                updatedDataProvider.getId(),dataProvider.getId());

    }

}