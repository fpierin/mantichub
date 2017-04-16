package com.mantichub.agent.core.constant;

import org.apache.jena.riot.RDFFormat;

public class AgentConfiguration {
	
	public static final RDFFormat RDF_FORMAT = RDFFormat.RDFXML;
	public static final int THREAD_AMOUNT = 10;
	public static final long REQUEST_TIMEOUT = 1000 * 20;

}
