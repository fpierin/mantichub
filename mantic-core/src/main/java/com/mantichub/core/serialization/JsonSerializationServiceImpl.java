package com.mantichub.core.serialization;

import javax.inject.Named;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Named("SerializationService")
public class JsonSerializationServiceImpl implements SerializationService {
	
	private final Gson gson;

	public JsonSerializationServiceImpl() {
		gson = new GsonBuilder().create();
	}
	
	@Override
	public String fromObject(final Object object) {
		return gson.toJson(object);
	}

}
