package com.mantichub.agent.core.infra;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

public interface Agent {

	Model retrieve() throws Exception;

	Model retrieve(int ammount) throws Exception;
	
	Resource resourceFromHtml(String url, Model model, String html);

}
