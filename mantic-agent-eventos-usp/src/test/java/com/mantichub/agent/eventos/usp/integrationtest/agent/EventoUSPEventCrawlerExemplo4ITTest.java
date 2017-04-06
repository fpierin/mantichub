package com.mantichub.agent.eventos.usp.integrationtest.agent;

import static com.mantichub.agent.eventos.usp.infra.TestUtils.fromFile;
import static com.mantichub.core.util.ModelUtils.getRDFXMLFastWriter;

import org.apache.http.client.HttpClient;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.mantic.datastore.client.api.DatastoreApi;
import org.mantic.datastore.client.api.DatastoreApiImpl;

import com.mantichub.agent.core.builder.EventResourceBuilder;
import com.mantichub.agent.core.infra.Event;
import com.mantichub.agent.eventos.usp.agent.EventoUspEventAdapter;
import com.mantichub.commons.constant.MantichubConstants;
import com.mantichub.core.http.HttpClientFactory;
import com.mantichub.core.serialization.JsonSerializationServiceImpl;

public class EventoUSPEventCrawlerExemplo4ITTest {

	private final static String arquivo = "src/test/resources/exemplo8.html";
	
	public static void main(final String[] args) throws Exception {
		final HttpClient httpClient = HttpClientFactory.get(1, 1, 3);
		final DatastoreApi datastoreApi = new DatastoreApiImpl(httpClient, new JsonSerializationServiceImpl());
		final Event event = new EventoUspEventAdapter(fromFile(arquivo), null);
		final Model model = getRDFXMLFastWriter();
		System.out.println(event.getTitle());
		final Resource resource = new EventResourceBuilder(model, MantichubConstants.NAMESPACE)
				.price(event.getPrice())
				.endDate(event.getEndDate())
				.endTime(event.getEndTime())
				.latitude(event.getLatitude())
				.longitude(event.getLongitude())
				.serviceUrl("http://www.eventos.usp.br/?events=o-que-a-fisica-e-os-fisicos-podem-fazer-para-a-sua-saude")
				.overview(event.getOverview())
				.startDate(event.getStartDate())
				.startTime(event.getStartTime())
				.streetAddress(event.getStreetAddress())
				.title(event.getTitle())
				.create();
		datastoreApi.create(resource);
	}

}