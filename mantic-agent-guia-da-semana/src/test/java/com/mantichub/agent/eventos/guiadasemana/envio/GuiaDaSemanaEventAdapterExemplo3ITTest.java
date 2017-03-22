package com.mantichub.agent.eventos.guiadasemana.envio;

import static com.mantichub.core.util.ModelUtils.getRDFXMLFastWriter;

import org.apache.http.client.HttpClient;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.mantic.datastore.client.api.DatastoreApi;
import org.mantic.datastore.client.api.DatastoreApiImpl;

import com.mantichub.agent.core.builder.EventBuilder;
import com.mantichub.agent.core.infra.EventResource;
import com.mantichub.agent.eventos.guiadasemana.agent.GuiaDaSemanaEventAdapter;
import com.mantichub.agent.eventos.guiadasemana.infra.TestUtils;
import com.mantichub.commons.constant.MantichubConstants;
import com.mantichub.core.http.HttpClientFactory;
import com.mantichub.core.serialization.JsonSerializationServiceImpl;

public class GuiaDaSemanaEventAdapterExemplo3ITTest {
	
	private final static String arquivo = "src/test/resources/evento3.html";

	public static void main(final String[] args) throws Exception {
		final HttpClient httpClient = HttpClientFactory.get(1, 1, 3);
		final DatastoreApi datastoreApi = new DatastoreApiImpl(httpClient, new JsonSerializationServiceImpl());
		final EventResource event = new GuiaDaSemanaEventAdapter(TestUtils.fromFile(arquivo));
		final Model model = getRDFXMLFastWriter();
		System.out.println(event.getTitle());
		final Resource resource = new EventBuilder(model, MantichubConstants.NAMESPACE)
				.price(event.getPrice())
				.endDate(event.getEndDate())
				.endTime(event.getEndTime())
				.latitude(event.getLatitude())
				.longitude(event.getLongitude())
				.serviceUrl("http://www.guiadasemana.com.br/sao-paulo/shows/evento/fabio-jr.-em-sao-paulo")
				.overview(event.getOverview())
				.startDate(event.getStartDate())
				.startTime(event.getStartTime())
				.streetAddress(event.getStreetAddress())
				.type(event.getType())
				.title(event.getTitle())
				.create();
		datastoreApi.create(resource);
	}
	
}