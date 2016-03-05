package com.mantichub.agent.eventos.usp.http;

import static org.apache.commons.lang3.StringEscapeUtils.unescapeHtml3;

import javax.inject.Inject;

import org.apache.http.client.HttpClient;

import com.mantichub.core.http.RestSupport;
import com.mantichub.core.http.ServerResponse;

public class EventosUspHttpClientImpl extends RestSupport implements EventosUspHttpClient {

	@Inject
	public EventosUspHttpClientImpl(final HttpClient httpClient) {
		super(httpClient);
	}

	public String htmlFromURL(final String url) {
		final ServerResponse httpResponse = read(url);
		final String message = httpResponse.getContent();
		return message;
	}

	public String unescapeHtmlFromURL(final String url) {
		return unescapeHtml3(htmlFromURL(url));
	}

}
