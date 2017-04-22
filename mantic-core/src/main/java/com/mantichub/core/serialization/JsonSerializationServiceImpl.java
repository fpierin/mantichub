package com.mantichub.core.serialization;

import javax.inject.Named;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Named("serializationService")
public class JsonSerializationServiceImpl implements SerializationService {
	
	private final Gson gson;

	public JsonSerializationServiceImpl() {
		gson = new GsonBuilder().create();
	}
	
	@Override
	public String fromObject(final Object object) {
		return gson.toJson(object);
	}
	
	@Override
	public <T> T toObject(final String json, final Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

}
