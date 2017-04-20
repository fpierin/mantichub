package org.mantic.client.api.configuration;

import org.apache.http.client.HttpClient;
import org.mantic.datastore.client.api.DatastoreApi;
import org.mantic.datastore.client.api.DatastoreApiImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mantichub.core.http.HttpClientFactory;
import com.mantichub.core.serialization.JsonSerializationServiceImpl;
import com.mantichub.core.serialization.SerializationService;

@Configuration
public class DatastoreApiFactory {
	
	@Bean
	public DatastoreApi datastoreApi() {
		final HttpClient httpClient = HttpClientFactory.get(10, 10, 3);
		final SerializationService serializationService = new JsonSerializationServiceImpl();
		return new DatastoreApiImpl(httpClient, serializationService);
	}

}
