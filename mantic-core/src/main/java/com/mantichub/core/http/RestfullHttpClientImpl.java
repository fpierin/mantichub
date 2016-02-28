package com.mantichub.core.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;

import com.mantichub.core.util.StringUtils;

public class RestfullHttpClientImpl implements RestfullHttpClient {

	private static final String HTTP_GET = "GET";
	private static final String HTTP_POST = "POST";

	private String encoding = "UTF-8";
	private String contentType = null;
	private boolean encodeBody = false;
	private final int timeout = 0;

	public HttpResponse post(final String endpoint, final String body) {
		return httpRequest(HTTP_POST, endpoint, body);
	}

	public HttpResponse get(final String endpoint) {
		return httpRequest(HTTP_GET, endpoint, null);
	}

	private HttpResponse httpRequest(final String requestMethod, final String endpoint, final String body) {
		try {
			final URL url = new URL(endpoint);
			final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(requestMethod);
			connection.setConnectTimeout(timeout);

			if (contentType != null) {
				connection.setRequestProperty("Content-Type", contentType);
			}

			if (!"GET".equalsIgnoreCase(requestMethod)) {
				connection.setDoOutput(true);
				String messageBody = body.trim();
				if (encodeBody) {
					messageBody = URLEncoder.encode(messageBody, encoding);
				}
				final OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
				osw.write(messageBody);
				osw.close();
			}

			final int responseCode = connection.getResponseCode();
			InputStream is;
			if (responseCode < 400) {
				is = connection.getInputStream();
			} else {
				is = connection.getErrorStream();
			}
			final String responseMessage = StringUtils.convertStreamToString(is, encoding);

			final HttpResponse httpResponse = new HttpResponse(responseCode, responseMessage);
			// log.debug("RestfullHttpClientImpl.httpRequest() - end");
			return httpResponse;
		} catch (final MalformedURLException ex) {
			// log.error(ex);
			throw new RuntimeException(ex);
		} catch (final SocketTimeoutException ex) {
			// log.error(ex);
			throw new RuntimeException(ex);
		} catch (final IOException ex) {
			// log.error(ex);
			throw new RuntimeException(ex);
		} catch (final Exception ex) {
			// log.error(ex);
			throw new RuntimeException(ex);
		}
	}

	public void setEncoding(final String encoding) {
		this.encoding = encoding;
	}

	public void setContentType(final String contentType) {
		this.contentType = contentType;
	}

	public boolean encodeBody() {
		return encodeBody;
	}

	public void setEncodeBody(final boolean encodeBody) {
		this.encodeBody = encodeBody;
	}

}