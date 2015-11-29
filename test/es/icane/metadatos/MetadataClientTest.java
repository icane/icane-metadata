package es.icane.metadatos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import es.icane.metadatos.model.DataProvider;
import es.icane.metadatos.model.DataSet;
import es.icane.metadatos.model.NodeType;
import es.icane.metadatos.model.Periodicity;
import es.icane.metadatos.model.TimePeriod;
import es.icane.metadatos.model.TimeSeries;

public class MetadataClientTest {

	@Test
	public void getNodeTypesShouldReturnTenElementsList() {

		// MyClass is tested
		String baseUrl = "http://www.icane.es/metadata/api";
		MetadataClient metadataClient = new MetadataClient(baseUrl);

		List<NodeType> nodeTypes = metadataClient.getNodeTypes();


		// assert statements
		assertEquals("Size must be ten", 10, nodeTypes.size());

	}

	@Test
	public void getTimePeriodsShouldReturnList() {

		// MyClass is tested
		String baseUrl = "http://www.icane.es/metadata/api";
		MetadataClient metadataClient = new MetadataClient(baseUrl);

		List<TimePeriod> timePeriods = metadataClient.getTimePeriods();


		// assert statements
		assertTrue("Size must be greater than 100", timePeriods.size() > 100);
		assertEquals("First element is id 246",246, timePeriods.get(0).getId().intValue());
	}

	@Test
	public void getDataProvidersShouldReturnList() {

		// MyClass is tested
		String baseUrl = "http://www.icane.es/metadata/api";
		MetadataClient metadataClient = new MetadataClient(baseUrl);

		List<DataProvider> dataProviders = metadataClient.getDataProviders();

		// assert statements
		assertTrue("Size must be greater than 100", dataProviders.size() > 100);
		assertEquals("First element has acronym INE", "INE", dataProviders.get(0).getAcronym());
	}

	@Test
	public void getDataSetsShouldReturnList() {

		// MyClass is tested
		String baseUrl = "http://www.icane.es/metadata/api";
		MetadataClient metadataClient = new MetadataClient(baseUrl);

		List<DataSet> dataSets = metadataClient.getDataSets();

		// assert statements
		assertTrue("Size must be greater than 100", dataSets.size() > 100);
		assertEquals("20th element has acronym CENSO", "CENSO", dataSets.get(19).getAcronym());
	}

	@Test
	public void getPeriodicitiesShouldReturnList() {

		// MyClass is tested
		String baseUrl = "http://www.icane.es/metadata/api";
		MetadataClient metadataClient = new MetadataClient(baseUrl);

		List<Periodicity> periodicities = metadataClient.getPeriodicities();

		// assert statements
		assertTrue("Size must be greater than 10", periodicities.size() > 10);
		assertEquals("10th element has uriTag decennial", "decennial", periodicities.get(9).getUriTag());
	}

	@Test
	public void getPeriodicityShouldReturnElement() {

		// MyClass is tested
		String baseUrl = "http://www.icane.es/metadata/api";
		MetadataClient metadataClient = new MetadataClient(baseUrl);

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

		// MyClass is tested
		String baseUrl = "http://www.icane.es/metadata/api";
		MetadataClient metadataClient = new MetadataClient(baseUrl);

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

}
