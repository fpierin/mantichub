package com.mantichub.agent.eventos.guiadasemana.config;

public class Configuration {

	public static final int MAX_CONNECTION_TOTAL = 10;
	public static final int MAX_CONNECTION_PER_ROUTE = 10;
	public static final int MAX_RETRY_ATTEMPTS = 3;
	public static final int THREADS = 2;
	public static final boolean USE_PARALLELL_CALLS = false;
	
	public static final String NEXT_PAGE_URL = "(/sao-paulo/agenda\\?page=\\d+).*";
	public static final String BASE_URL = "http://www.guiadasemana.com.br";
	public static final String PORTAL_URL = BASE_URL + "/sao-paulo/agenda?page=1";
	public static final String EVENT_URL_PATTERN = "href=\"(/sao-paulo/.+?)\" title=\"";

}
