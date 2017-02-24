package com.mantichub.core.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import com.mantichub.core.constant.Charset;
import com.mantichub.core.constant.ContentType;
import com.mantichub.core.serialization.SerializationService;

public class RestfulSupport {

	private final HttpClient httpClient;

	protected final AtomicBoolean open = new AtomicBoolean(true);
	
	private final SerializationService serializationService;

	public RestfulSupport(final HttpClient httpClient, final SerializationService serializationService) {
		this.httpClient = httpClient;
		this.serializationService = serializationService;
	}

	public ServerResponse read(final String uri) {
		final HttpGet httpGet = new HttpGet(uri);
		return invokeHttpServer(httpGet, getHeaders());
	}
	
	public ServerResponse post(final String uri, final Object object) {
		final HttpPost httpPost = new HttpPost(uri);
		if (object != null) {
			final String charset = getCharset().toString();
			final String content = serializationService.fromObject(object);
			final HttpEntity entity = new StringEntity(content, charset);
			httpPost.setEntity(entity);
		}
		return invokeHttpServer(httpPost, getHeaders());
	}
	
	protected ServerResponse invokeHttpServer(final HttpRequestBase request, final Map<String, String> headers) {
		try {
			request.addHeader("Connection", "close");
			if ((headers != null) && (headers.size() > 0)) {
				final Set<Entry<String, String>> entrySet = headers.entrySet();
				final Iterator<Entry<String, String>> iterator = entrySet.iterator();
				while (iterator.hasNext()) {
					final Entry<String, String> next = iterator.next();
					request.addHeader(next.getKey(), next.getValue());
				}
			}
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
	
	protected Map<String, String> getHeaders() {
		final Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", getContentType().toString());
		set(headers);
		return headers;
	}

	protected void set(final Map<String, String> headers) {
	}
	
	protected ContentType getContentType() {
		return ContentType.JSON;
	}

	protected Charset getCharset() {
		return Charset.UTF_8;
	}

}
