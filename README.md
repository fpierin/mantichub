# mantichub

{"query":"SELECT * { ?s ?p ?o }"}

{"query":"PREFIX rdfs:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT * WHERE { ?s rdfs:type schema:Event }"}

{"query":"PREFIX rdfs:\u003chttp://www.w3.org/1999/02/22-rdf-syntax-ns#\u003e\nPREFIX mantichub:\u003chttp://www.wemantic.com/events#\u003e\nPREFIX schema:\u003chttp://schema.org/\u003e\nSELECT * WHERE { ?s rdfs:type schema:Event }"}


## Recupera todos os objetos filtrando por titulo latitude e longitude
http://localhost:8080/api/triplestore?query=PREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20%3Ftitle%20%3Flatitude%20%3Flongitude%20WHERE%20%7B%20%0A%09%3Fa%20rdfs%3Atype%20%3Fo%20%3B%0A%09schema%3Atitle%20%3Ftitle%20%3B%0A%09schema%3Alatitude%20%3Flatitude%20%3B%0A%09schema%3Alongitude%20%3Flongitude%20.%20%0A%7D

PREFIX rdfs:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?title ?latitude ?longitude WHERE { 
	?a rdfs:type ?o ;
	schema:title ?title ;
	schema:latitude ?latitude ;
	schema:longitude ?longitude . 
}

## Recupera todos os objetos filtrando por titulo latitude longitude e ordena por latitude
http://localhost:8080/api/triplestore?query=PREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20%3Ftitle%20%3Flatitude%20%3Flongitude%20WHERE%20%7B%20%0A%09%3Fa%20rdfs%3Atype%20%3Fo%20%3B%0A%09schema%3Atitle%20%3Ftitle%20%3B%0A%09schema%3Alatitude%20%3Flatitude%20%3B%0A%09schema%3Alongitude%20%3Flongitude%20.%20%0A%7D%20ORDER%20BY%20ASC(%3Flatitude)

PREFIX rdfs:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?title ?latitude ?longitude WHERE { 
	?a rdfs:type ?o ;
	schema:title ?title ;
	schema:latitude ?latitude ;
	schema:longitude ?longitude . 
} ORDER BY ASC(?latitude)

## Recupera todos os objetos filtrando por titulo latitude longitude, ordena por latitude e informa origem do dado
http://localhost:8080/api/triplestore?query=PREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20%3FserviceURL%20%3Flatitude%20%3Flongitude%20WHERE%20%7B%20%0A%09%3Fa%20rdfs%3Atype%20%3Fo%20%3B%0A%09schema%3AserviceURL%20%3FserviceURL%20%3B%0A%09schema%3Alatitude%20%3Flatitude%20%3B%0A%09schema%3Alongitude%20%3Flongitude%20.%20%0A%7D%20ORDER%20BY%20ASC(%3Flatitude)

PREFIX rdfs:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?serviceURL ?latitude ?longitude WHERE { 
	?a rdfs:type ?o ;
	schema:serviceURL ?serviceURL ;
	schema:latitude ?latitude ;
	schema:longitude ?longitude . 
} ORDER BY ASC(?latitude)



## Uso de optional
PREFIX rdfs:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT * WHERE { 
	?a rdfs:type schema:Restaurant ;
	schema:title ?title .
	OPTIONAL { ?a schema:latitude ?latitude } 
}

## Recupera todos os objetos filtrando por titulo tipo latitude longitude e ordena por latitude
http://localhost:8080/api/triplestore?query=PREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20%3Ftitle%20%3Fo%20%3Flatitude%20%3Flongitude%20WHERE%20%7B%20%0A%09%3Fa%20rdfs%3Atype%20%3Fo%20%3B%0A%09schema%3Atitle%20%3Ftitle%20%3B%0A%09schema%3AserviceURL%20%3FserviceURL%20%3B%0A%09schema%3Alatitude%20%3Flatitude%20%3B%0A%09schema%3Alongitude%20%3Flongitude%20.%20%0A%7D%20ORDER%20BY%20ASC(%3Flatitude)

PREFIX rdfs:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?title ?o ?latitude ?longitude WHERE { 
	?a rdfs:type ?o ;
	schema:title ?title ;
	schema:serviceURL ?serviceURL ;
	schema:latitude ?latitude ;
	schema:longitude ?longitude . 
} ORDER BY ASC(?latitude)

## Recupera todos os objetos filtrando por titulo latitude longitude e ordena por latitude restringindo por tipo
http://localhost:8080/api/triplestore?query=PREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20%3Ftitle%20%3Fo%20%3Flatitude%20%3Flongitude%20WHERE%20%7B%20%0A%09%3Fa%20a%20rdfs%3AResource%20%3B%0A%09schema%3Atitle%20%3Ftitle%20%3B%0A%09schema%3AserviceURL%20%3FserviceURL%20%3B%0A%09schema%3Alatitude%20%3Flatitude%20%3B%0A%09schema%3Alongitude%20%3Flongitude%20.%20%0A%7D%20ORDER%20BY%20ASC(%3Flatitude)

PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?title ?latitude ?longitude WHERE { 
	?a rdf:type rdfs:Resource ;
	schema:title ?title ;
	schema:serviceURL ?serviceURL ;
	schema:latitude ?latitude ;
	schema:longitude ?longitude . 
} ORDER BY ASC(?latitude)

## Recupera todos os objetos filtrando por titulo tipo latitude longitude e ordena por latitude restringindo por tipo
http://localhost:8080/api/triplestore?query=PREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20%3Ftitle%20%3Fo%20%3Flatitude%20%3Flongitude%20WHERE%20%7B%20%0A%09%3Fa%20a%20rdfs%3AResource%20%3B%0A%09schema%3Atitle%20%3Ftitle%20%3B%0A%09schema%3AserviceURL%20%3FserviceURL%20%3B%0A%09schema%3Alatitude%20%3Flatitude%20%3B%0A%09schema%3Alongitude%20%3Flongitude%20.%20%0A%7D%20ORDER%20BY%20ASC(%3Flatitude)

PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?title ?latitude ?longitude ?serviceURL WHERE { 
	?a rdf:type rdfs:Resource ;
	schema:title ?title ;
	schema:serviceURL ?serviceURL ;
	schema:latitude ?latitude ;
	schema:longitude ?longitude . 
} ORDER BY ASC(?latitude)

## Recupera todos os objetos filtrando por titulo tipo latitude longitude e ordena por latitude restringindo por tipo
http://localhost:8080/api/triplestore?query=PREFIX%20rdf%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20%3FserviceURL%20%3Flatitude%20%3Flongitude%20WHERE%20%7B%20%0A%09%3Fa%20rdf%3Atype%20rdfs%3AResource%20%3B%0A%09schema%3Atitle%20%3Ftitle%20%3B%0A%09schema%3AserviceURL%20%3FserviceURL%20%3B%0A%09schema%3Alatitude%20%3Flatitude%20%3B%0A%09schema%3Alongitude%20%3Flongitude%20.%20%0A%7D%20ORDER%20BY%20ASC(%3Flatitude)

PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?serviceURL ?latitude ?longitude WHERE { 
	?a rdf:type rdfs:Resource ;
	schema:title ?title ;
	schema:serviceURL ?serviceURL ;
	schema:latitude ?latitude ;
	schema:longitude ?longitude . 
} ORDER BY ASC(?latitude)