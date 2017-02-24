package com.mantichub.agent.core.http;

import static org.apache.commons.lang3.StringEscapeUtils.unescapeHtml3;

import javax.inject.Inject;

import org.apache.http.client.HttpClient;

import com.mantichub.core.http.RestfulSupport;
import com.mantichub.core.http.ServerResponse;
import com.mantichub.core.serialization.SerializationService;

public class HttpAgentImpl extends RestfulSupport implements HttpAgent {
	
	@Inject
	public HttpAgentImpl(final HttpClient httpClient, final SerializationService serializationService) {
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
