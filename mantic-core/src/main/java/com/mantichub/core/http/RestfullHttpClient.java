package com.mantichub.core.http;

public interface RestfullHttpClient {

	HttpResponse post(String endpoint, String body);

	HttpResponse get(String endpoint);

	void setEncoding(String encoding);

	void setContentType(String contentType);

}
