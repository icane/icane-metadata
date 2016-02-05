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
		String baseUrl = "http://localhost:8080/metadata/api";
		metadataClient = new MetadataClient(baseUrl);
	}

	@Test
	public void getNodeTypesShouldReturnTenElementsList() {

		List<NodeType> nodeTypes = metadataClient.getNodeTypes();


		// assert statements
		assertEquals("Size must be ten", 10, nodeTypes.size());

	}

	@Test
	public void getTimePeriodsShouldReturnList() {

		List<TimePeriod> timePeriods = metadataClient.getTimePeriods();


		// assert statements
		assertTrue("Size must be greater than 100", timePeriods.size() > 100);
		assertEquals("First element is id 246",246, timePeriods.get(0).getId().intValue());
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
		assertEquals("Initial time period id must be 259", 259, (int) timeSeries1.getInitialPeriodComposite().getId());
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

        measure.setNode(timeSeries.getUriTag());
        measure.setTitle("prueba");
        metadataClient.updateMeasure(measure);

        try {
            updatedMeasure = metadataClient.getMeasure(1);
        } catch (MeasureNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements
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

        assertEquals("Title should be the same ",
                updatedMeasure.getTitle(), measure.getTitle());
        assertEquals("Node should be the same ",
                updatedMeasure.getNode(), measure.getNode());

    }

    @Test
    public void createAndDeleteMeasureShouldReturnOk() {
        UnitOfMeasure unitOfMeasure = null;
        Measure createdMeasure = null;
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
        reference.setNodeUriTag(reference.getNode());
        metadataClient.updateReference(reference);

        try {
            updatedReference = metadataClient.getReference(9);
        } catch (ReferenceNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements

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
        reference.setNodeUriTag(timeSeries.getUriTag());
        reference.setTitle("Contabilidad Trimestral de Cantabria");
        reference.setResourceType(ResourceType.PUBLICATION);
        metadataClient.updateReference(reference);
    }

    @Test
    public void createAndDeleteReferenceShouldReturnOk() {

        Reference createdReference = null;
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
    public void updateLinkWithItselfShouldReturnOk() {
        Link link = null;
        Link updatedLink = null;

        try {
            link = metadataClient.getLink(472);
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }
        link.setSectionUriTag(link.getSection());

        metadataClient.updateLink(link);

        try {
            updatedLink = metadataClient.getLink(472);
        } catch (LinkNotFoundException e) {
            e.printStackTrace();
        }

        // assert statements

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

        link.setNodeUriTag(null);
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
        link.setReferenceAreaUriTag(null);
        link.setNodeUriTag(timeSeries.getUriTag());
        link.setUri("http://www.ine.es/jaxi/menu.do?type=pcaxis&path=%2Ft20%2Fp318&file=inebase&L=0");

        link.setLinkType(linkType);
        link.setTitle("INE");
        metadataClient.updateLink(link);
    }

    @Test
    public void createAndDeleteLinkShouldReturnOk() {

        Link createdLink = null;
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
        assertEquals("Size must be eight", 8, linkTypes.size());

    }

    @Test
    public void createAndDeleteLinkAssociatedToNodeShouldReturnOk() {

        Link createdLink = null;
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


}