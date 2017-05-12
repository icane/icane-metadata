package es.icane.metadatos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.ListIterator;

import org.junit.BeforeClass;
import org.junit.Test;

import es.icane.metadatos.model.DataProvider;
import es.icane.metadatos.model.DataSet;
import es.icane.metadatos.model.NodeType;
import es.icane.metadatos.model.Periodicity;
import es.icane.metadatos.model.TimePeriod;
import es.icane.metadatos.model.TimeSeries;

public class MetadataClientTest {

	private static MetadataClient metadataClient;

	@BeforeClass
	public static void setUp() {
		String baseUrl = "http://www.icane.es/metadata/api";
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
		assertEquals("First element is id 246", 246, timePeriods.get(0).getId().intValue());
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
		assertEquals("Resource type of reference is publication", "Publicación", timeSeries.getReferences().get(0).getResourceType().toString());

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

		for (ListIterator<TimeSeries> it = ancestors.listIterator(ancestors.size()); it.hasPrevious(); ) {
			TimeSeries ancestor = it.previous();

		}

		//TODO: finalize test


	}

}
