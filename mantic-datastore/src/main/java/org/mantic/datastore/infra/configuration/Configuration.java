package org.mantic.datastore.infra.configuration;

public class Configuration {
		
	public static final String BASIC_SPARQL_QUERY = 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
			"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"PREFIX iweb:<http://integraweb.ddns.net/>\n" + 
			"PREFIX schema:<http://schema.org/>\n" + 
			"SELECT DISTINCT " + 
				"?title ?latitude ?longitude ?startDate ?endDate ?startTime ?endTime " +
				"?cuisine ?description ?priceRange ?telephone ?overview ?streetAddress " +
				"?price ?type ?url ?image \n" +
			"WHERE {\n" + 
			"	?s schema:title ?titleObj ;\n" + 
			"	schema:latitude ?latitudeObj ;\n" + 
			"	schema:longitude ?longitudeObj ;\n" +
			"	rdf:type {rdfType} ;\n" +
			"	rdf:type ?typeObj .\n" +
			"	?typeObj rdfs:subClassOf ?subClassObj .\n" +
			"	OPTIONAL { ?s schema:cuisine ?cuisineObj }\n" +
			"	OPTIONAL { ?s schema:description ?descriptionObj }\n" +
			"	OPTIONAL { ?s schema:endDate ?endDateObj }\n" + 
			"	OPTIONAL { ?s schema:endTime ?endTimeObj }	\n" +
			"	OPTIONAL { ?s schema:overview ?overviewObj }\n" +
			"	OPTIONAL { ?s schema:price ?priceObj }	\n" +
			"	OPTIONAL { ?s schema:priceRange ?priceRangeObj }\n" +
			"	OPTIONAL { ?s schema:startDate ?startDateObj }\n" + 
			"	OPTIONAL { ?s schema:startTime ?startTimeObj }\n" + 
			"	OPTIONAL { ?s schema:streetAddress ?streetAddressObj }	\n" +
			"	OPTIONAL { ?s schema:telephone ?telephoneObj }\n" +
			"	OPTIONAL { ?s schema:serviceURL ?urlObj }\n" +
			"	OPTIONAL { ?s schema:image ?imageObj }\n" +
			"	values ?subClassObj { schema:Event schema:FoodEstablishment schema:CivicStructure } \n" +
			"	BIND (str(?titleObj) as ?title)\n" + 
			"	BIND (str(?latitudeObj) as ?latitude)\n" + 
			"	BIND (str(?longitudeObj) as ?longitude)\n" + 
			"	BIND (str(?cuisineObj) as ?cuisine)\n" +
			"	BIND (str(?descriptionObj) as ?description)\n" +
			"	BIND (str(?endDateObj) as ?endDate)\n" +
			"	BIND (str(?overviewObj) as ?overview)\n" +
			"	BIND (str(?priceObj) as ?price)\n" +
			"	BIND (str(?priceRangeObj) as ?priceRange)\n" +
			"	BIND (str(?startDateObj) as ?startDate)\n" +
			"	BIND (str(?streetAddressObj) as ?streetAddress)\n" +
			"	BIND (str(?telephoneObj) as ?telephone)\n" +
			"	BIND (str(?urlObj) as ?url)\n" +
			"	BIND (str(?imageObj) as ?image)\n" +
			"	BIND ( strafter(strafter( str(?typeObj), \"http://\" ),\"/\") as ?type )\n" +
			"	BIND ( strafter( str(?endTimeObj), \"T\" ) as ?endTime )\n" + 
			"	BIND ( strafter( str(?startTimeObj), \"T\" ) as ?startTime )\n" +
			"	FILTER NOT EXISTS {\n" + 
			"		?s rdf:type ?subtype .\n" + 
			"		?subtype rdfs:subClassOf* ?typeObj .\n" + 
			"		filter ( ?subtype != ?typeObj )\n" + 
			"	}\n" +
			"{filtering} " +
			"}\n" +
			"{limit}";
}
