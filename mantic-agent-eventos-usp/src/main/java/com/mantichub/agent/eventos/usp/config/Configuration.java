package com.mantichub.agent.eventos.usp.config;

import org.apache.jena.riot.RDFFormat;

public class Configuration {

	public static final RDFFormat RDF_Format = RDFFormat.RDFXML;
	public static final String PORTAL_URL = "http://www.eventos.usp.br/";
	public static final String projectNS = "http://www.wemantic.com/events#";;
	public static final int THREAD_AMOUNT = 10;

	public static final String EVENT_URL_PATTERN = "href=\"(http://www.eventos.usp.br/\\?events=(.*?))\"";
	public static final String STREET_ADDRESS_PATTERN = "var endereco = \\'(.*?)\\s*CEP";
	public static final String TITLE_PATTERN = "class=\"evento-titulo\".*\\\">(.*)<\\/div>";
	public static final long REQUEST_TIMEOUT = 100000;

}
