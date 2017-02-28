package com.mantichub.agent.eventos.guiadasemana.config;

public class Configuration {

	public static final String PORTAL_URL = "http://www.eventos.usp.br/";
	public static final String EVENT_URL_PATTERN = "href=\"(http://www.eventos.usp.br/\\?events=(.*?))\"";
	public static final String STREET_ADDRESS_PATTERN = "var endereco = \\'(.*?)\\s*CEP";
	public static final String TITLE_PATTERN = "class=\"evento-titulo\".*\\\">(.*)<\\/div>";
	

}
