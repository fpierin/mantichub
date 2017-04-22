package com.mantichub.core.serialization;

public interface SerializationService {
	
	String fromObject(Object object);

	<T> T toObject(String json, Class<T> clazz);

}
