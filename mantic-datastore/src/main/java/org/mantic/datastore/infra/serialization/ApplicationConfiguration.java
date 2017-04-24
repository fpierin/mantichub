package org.mantic.datastore.infra.serialization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mantichub.core.serialization.JsonSerializationServiceImpl;
import com.mantichub.core.serialization.SerializationService;

@Configuration
public class ApplicationConfiguration {

	public ApplicationConfiguration() {
	}
	
	@Bean()
	public SerializationService serializationService() {
		return new JsonSerializationServiceImpl();
	}

}
