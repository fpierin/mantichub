package com.mantichub.core.agent;

import org.apache.jena.rdf.model.Model;

public interface Agent {

	Model retrieve() throws Exception;

}
