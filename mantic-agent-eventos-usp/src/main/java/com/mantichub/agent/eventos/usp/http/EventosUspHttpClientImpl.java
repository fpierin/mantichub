package com.mantichub.agent.eventos.usp.http;

import static org.apache.commons.lang3.StringEscapeUtils.unescapeHtml3;

import javax.inject.Inject;

import org.apache.http.client.HttpClient;

import com.mantichub.core.http.RestSupport;
import com.mantichub.core.http.ServerResponse;
import com.mantichub.core.serialization.SerializationService;

public class EventosUspHttpClientImpl extends RestSupport implements EventosUspHttpClient {
	
	@Inject
	public EventosUspHttpClientImpl(final HttpClient httpClient, final SerializationService serializationService) {
		super(httpClient, serializationService);
	}
	
	@Override
	public String htmlFromURL(final String url) {
		final ServerResponse httpResponse = read(url);
		final String message = httpResponse.getContent();
		return message;
	}
	
	@Override
	public String unescapeHtmlFromURL(final String url) {
		return unescapeHtml3(htmlFromURL(url));
	}
	
}
