package com.mantichub.agent.eventos.guiadasemana.config;

public class Configuration {

	public static final String NEXT_PAGE_URL = "(/sao-paulo/agenda\\?page=\\d+).*";
	public static final String BASE_URL = "http://www.guiadasemana.com.br";
	public static final String PORTAL_URL = BASE_URL + "/sao-paulo/agenda?page=1";
	public static final String EVENT_URL_PATTERN = "href=\"(/sao-paulo/.+?)\" title=\"";	

}
