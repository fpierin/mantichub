package com.mantichub.core.http;

import java.io.Serializable;

public class ServerResponse implements Serializable {

	private static final long serialVersionUID = 5262742308140865540L;

	private final int code;
	private final String content;

	public ServerResponse(final int code, final String content) {
		this.code = code;
		this.content = content;
	}

	public int getCode() {
		return code;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "ServerResponse [code=" + code + ", content=" + content + "]";
	}

}
