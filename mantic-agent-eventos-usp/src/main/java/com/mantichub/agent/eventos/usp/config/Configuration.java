package com.mantichub.agent.eventos.usp.config;

import org.apache.jena.riot.RDFFormat;

public class Configuration {

	public static final RDFFormat RDF_Format = RDFFormat.RDFXML;
	public static final String PORTAL_URL = "http://www.eventos.usp.br/";
	public static final String projectNS = "http://www.wemantic.com/events#";;

	public static final String EVENT_URL_PATTERN = "href=\"(http://www.eventos.usp.br/\\?events=(.*?))\"";
	public static final String STREET_ADDRESS_PATTERN = "var endereco = \\'(.*?)\\'";
	public static final String TITLE_PATTERN = "<div class=\"evento-titulo\" style=\"width:470px;\">(.*?)</div>";

}
