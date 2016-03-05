package com.mantichub.core.http;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;

public class RestSupport {

	private final HttpClient httpClient;

	protected final AtomicBoolean open = new AtomicBoolean(true);

	public RestSupport(final HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public ServerResponse read(final String uri) {
		final HttpGet httpGet = new HttpGet(uri);
		return invokeHttpServer(httpGet);
	}

	protected ServerResponse invokeHttpServer(final HttpRequestBase request) {
		try {
			request.addHeader("Connection", "close");
			final HttpResponse response = httpClient.execute(request);
			if (response != null) {
				final String content = read(request, response);
				return new ServerResponse(response.getStatusLine().getStatusCode(), content);
			}
			return null;
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			request.releaseConnection();
		}
	}

	protected String read(final HttpRequestBase request, final HttpResponse response) throws IllegalStateException, IOException {
		if (!open.get()) {
			throw new IllegalStateException("Http client is closed");
		}
		final HttpEntity entity = response.getEntity();
		if (entity != null) {
			try {
				return EntityUtils.toString(entity);
			} catch (final IOException ex) {
				throw ex;
			} catch (final RuntimeException ex) {
				request.abort();
				throw ex;
			}
		}
		return null;
	}

}
