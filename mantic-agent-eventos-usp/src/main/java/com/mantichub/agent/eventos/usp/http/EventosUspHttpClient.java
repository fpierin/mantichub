package com.mantichub.agent.eventos.usp.http;

public interface EventosUspHttpClient {

	String htmlFromURL(String url);

	String unescapeHtmlFromURL(String url);

}
