package com.mantichub.agent.generaltypes.agent;

import static com.mantichub.core.util.ModelUtils.getRDFXMLFastWriter;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.mantic.datastore.client.api.DatastoreApi;
import org.mantic.datastore.client.api.DatastoreApiImpl;

import com.mantichub.agent.core.infra.ResourceCreator;
import com.mantichub.agent.generaltypes.trainstation.TrainStationAdapter;
import com.mantichub.agent.generaltypes.trainstation.TrainStations;
import com.mantichub.core.http.HttpClientFactory;
import com.mantichub.core.serialization.JsonSerializationServiceImpl;
import com.mantichub.core.serialization.SerializationService;

public class GeneralTypesAgent {
	
	private final DatastoreApi datastoreApi;
	private Model model;
	
	public GeneralTypesAgent(final DatastoreApi datastoreApi, final Model model) {
		this.datastoreApi = datastoreApi;
		this.model = model;
	}

	public static void main(final String[] args) throws Exception {
		final HttpClient httpClient = HttpClientFactory.get(10, 10, 3);
		final SerializationService serializationService = new JsonSerializationServiceImpl();
		final DatastoreApi datastoreApi = new DatastoreApiImpl(httpClient, serializationService);
		final GeneralTypesAgent generalTypesAgent = new GeneralTypesAgent(datastoreApi, getRDFXMLFastWriter());
		generalTypesAgent.updateTrainStations();
	}

	public void updateTrainStations() throws Exception {
		final List<TrainStationAdapter> trainStations = TrainStations.list();
		for (final TrainStationAdapter trainStation : trainStations) {
			final Resource resource = ResourceCreator.build(model, trainStation);
			datastoreApi.create(resource);
		}
	}
	

}
