package com.mantichub.core.http;

import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClientFactory {

	public static HttpClient get(final int maxConnectionTotal, final int maxConnectionPerRoute, final int retryAttempts) {
		final HttpClientBuilder builder = getBuilder(retryAttempts);
		builder.setConnectionManager(getConnectionManager(maxConnectionTotal, maxConnectionPerRoute));
		return builder.build();
	}

	private static HttpClientBuilder getBuilder(final int retryAttempts) {
		final HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setRetryHandler(new DefaultHttpRequestRetryHandler(retryAttempts, false));
		builder.useSystemProperties();
		return builder;
	}

	private static PoolingHttpClientConnectionManager getConnectionManager(final int maxTotal, final int maxPerRoute) {
		return getConnectionManager(maxTotal, maxPerRoute, null);
	}

	private static PoolingHttpClientConnectionManager getConnectionManager(final int maxTotal, final int maxPerRoute, final SSLConnectionSocketFactory sslConnectionFactory) {
		PoolingHttpClientConnectionManager cm = null;
		if (sslConnectionFactory != null) {
			final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create().register("https", sslConnectionFactory).build();
			cm = new PoolingHttpClientConnectionManager(registry);
		} else {
			cm = new PoolingHttpClientConnectionManager();
		}

		cm.setMaxTotal(maxTotal);
		cm.setDefaultMaxPerRoute(maxPerRoute);
		cm.closeExpiredConnections();
		return cm;
	}

}
