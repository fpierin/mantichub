package com.mantichub.agent.core.http;

public interface HttpAgent {

	String htmlFromURL(String url);

	String unescapeHtmlFromURL(String url);

}
