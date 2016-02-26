package com.mantichub.core.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.riot.RDFFormat;

public class ModelUtils {

	public static Model getFastWriter(final RDFFormat rdfFormat) {
		final Model model = ModelFactory.createDefaultModel();
		final RDFWriter rdfWriter = model.getWriter(rdfFormat.getLang().getName());
		rdfWriter.setProperty("allowBadURIs", "true");
		rdfWriter.setProperty("relativeURIs", "");
		rdfWriter.setProperty("tab", "0");
		return model;
	}

	public static Model getFastWriter(final RDFFormat rdfFormat, final Map<String, String> nsPrefixes) {
		final Model model = getFastWriter(rdfFormat);
		model.setNsPrefixes(nsPrefixes);
		return model;
	}

	public static Map<String, String> getNsPrefixMap(final String nsName, final String nsUri) {
		final Map<String, String> nsPrefixes = new HashMap<String, String>();
		nsPrefixes.put(nsName, nsUri);
		return nsPrefixes;
	}

}
