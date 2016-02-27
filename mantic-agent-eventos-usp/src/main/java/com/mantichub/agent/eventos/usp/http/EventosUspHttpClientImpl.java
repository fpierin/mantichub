package com.mantichub.agent.eventos.usp.http;

import static org.apache.commons.lang3.StringEscapeUtils.unescapeHtml3;

import javax.inject.Inject;

import com.mantichub.core.http.HttpResponse;
import com.mantichub.core.http.RestfullHttpClient;

public class EventosUspHttpClientImpl implements EventosUspHttpClient {

	private final RestfullHttpClient restfullHttpClient;

	@Inject
	public EventosUspHttpClientImpl(final RestfullHttpClient restfullHttpClient) {
		this.restfullHttpClient = restfullHttpClient;
	}

	public String htmlFromURL(final String url) {
		final HttpResponse httpResponse = restfullHttpClient.get(url);
		final String message = httpResponse.getMessage();
		return message;
	}

	public String unescapeHtmlFromURL(final String url) {
		return unescapeHtml3(htmlFromURL(url));
	}

}
