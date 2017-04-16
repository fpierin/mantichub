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

## Recupera todos os eventos de Teatro
http://localhost:8080/api/triplestore?query=PREFIX%20rdf%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20*%20WHERE%20%7B%20%0A%09%3Fa%20rdf%3Atype%20schema%3ATheaterEvent%20%0A%7D

PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT * WHERE { 
	?a rdf:type schema:TheaterEvent 
}

## Recupera todos os eventos de Exibição
http://localhost:8080/api/triplestore?query=PREFIX%20rdf%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20*%20WHERE%20%7B%20%0A%09%3Fa%20rdf%3Atype%20schema%3AExhibitionEvent%20%0A%7D

## Recupera todos os urls ordenando por url
http://localhost:8080/api/triplestore?query=PREFIX%20rdf%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20%3Furl%20WHERE%20%7B%20%0A%09%3Fs%20schema%3AserviceURL%20%3FserviceURL%20%0A%09bind(%20str(%3FserviceURL)%20as%20%3Furl%20)%20%20%0A%7D

PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?url WHERE { 
	?s schema:serviceURL ?serviceURL 
	bind( str(?serviceURL) as ?url )  
}

## Recupera todos os urls agrupando por dominio
http://localhost:8080/api/triplestore?query=PREFIX%20rdf%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20%3Fpfix%20(count(%3Fs)%20as%20%3Fcount)%20WHERE%20%7B%20%0A%09%3Fs%20schema%3AserviceURL%20%3FserviceURL%20%0A%09bind(%20strbefore(strafter(%20str(%3FserviceURL)%2C%20%22%2F%2F%22%20)%2C%20%22%2F%22)%20as%20%3Fpfix%20)%20%20%0A%7D%0AGROUP%20BY%20(%3Fpfix)%20%0AORDER%20BY%20DESC%20(%3Fcount)

PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?pfix (count(?s) as ?count) WHERE { 
	?s schema:serviceURL ?serviceURL 
	bind( strbefore(strafter( str(?serviceURL), "//" ), "/") as ?pfix )  
}
GROUP BY (?pfix) 
ORDER BY DESC (?count)

## Conta itens na base de dados
http://localhost:8080/api/triplestore?query=PREFIX%20rdf%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20%3Fo%20(count(%3Fs)%20as%20%3Fcount)%20WHERE%20%7B%20%0A%09%3Fs%20rdf%3Atype%20%3Fo%0A%7D%20%0AGROUP%20BY%20(%3Fo)%20%0AORDER%20BY%20DESC%20(%3Fcount)%0A

PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?o (count(?s) as ?count) WHERE { 
	?s rdf:type ?o
} 
GROUP BY (?o) 
ORDER BY DESC (?count)

## Busca todos os eventos cadastrados
http://localhost:8080/api/triplestore?query=PREFIX%20rdf%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20*%20WHERE%20%7B%20%0A%09%3Fa%20rdf%3Atype%20schema%3ATheaterEvent%20%0A%7D

PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?tit ?lat ?lon ?sDate ?eDate ?sTime ?eTime WHERE {
	?s schema:title ?title ;
	schema:latitude ?latitude ;
	schema:longitude ?longitude .
	OPTIONAL { ?s schema:startDate ?startDate }
	OPTIONAL { ?s schema:endDate ?endDate }
	OPTIONAL { ?s schema:startTime ?startTime }
	OPTIONAL { ?s schema:endTime ?endTime }	
	BIND (str(?title) as ?tit)
	BIND (str(?latitude) as ?lat)
	BIND (str(?longitude) as ?lon)
	BIND (str(?startDate) as ?sDate)
	BIND (str(?endDate) as ?eDate)
	bind( strafter( str(?startTime), "T" ) as ?sTime )
	bind( strafter( str(?endTime), "T" ) as ?eTime )
}
ORDER BY DESC (?lat)

## Busca eventos em uma latitude e longitude especifica (agenda de eventos de um local)
http://localhost:8080/api/triplestore?query=PREFIX%20rdf%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20*%20WHERE%20%7B%20%0A%09%3Fa%20rdf%3Atype%20schema%3ATheaterEvent%20%0A%7D

PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?tit WHERE {
	?s schema:title ?title ;
	schema:latitude ?latitude ;
	schema:longitude ?longitude ;
	bind (str(?title) as ?tit)
	bind (str(?latitude) as ?lat)
	bind (str(?longitude) as ?lon)
	FILTER (?lat = '-21.171446' && ?lon = '-47.860565')
}

## Busca eventos com datas uma latitude e longitude especifica (agenda de eventos de um local)
http://localhost:8080/api/triplestore?query=PREFIX%20rdf%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20%3Ftit%20%3FsDate%20%3FeDate%20%3FsTime%20%3FeTime%20WHERE%20%7B%0A%09%3Fs%20schema%3Atitle%20%3Ftitle%20%3B%0A%09schema%3Alatitude%20%3Flatitude%20%3B%0A%09schema%3Alongitude%20%3Flongitude%20.%0A%09OPTIONAL%20%7B%20%3Fs%20schema%3AstartDate%20%3FstartDate%20%7D%0A%09OPTIONAL%20%7B%20%3Fs%20schema%3AendDate%20%3FendDate%20%7D%0A%09OPTIONAL%20%7B%20%3Fs%20schema%3AstartTime%20%3FstartTime%20%7D%0A%09OPTIONAL%20%7B%20%3Fs%20schema%3AendTime%20%3FendTime%20%7D%09%0A%09BIND%20(str(%3Ftitle)%20as%20%3Ftit)%0A%09BIND%20(str(%3Flatitude)%20as%20%3Flat)%0A%09BIND%20(str(%3Flongitude)%20as%20%3Flon)%0A%09BIND%20(str(%3FstartDate)%20as%20%3FsDate)%0A%09BIND%20(str(%3FendDate)%20as%20%3FeDate)%0A%09bind(%20strafter(%20str(%3FstartTime)%2C%20%22T%22%20)%20as%20%3FsTime%20)%0A%09bind(%20strafter(%20str(%3FendTime)%2C%20%22T%22%20)%20as%20%3FeTime%20)%0A%09FILTER%20(%3Flat%20%3D%20%27-21.171446%27%20%26%26%20%3Flon%20%3D%20%27-47.860565%27)%0A%7D%0AORDER%20BY%20DESC%20(%3FstartDate)

PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?tit ?sDate ?eDate ?sTime ?eTime WHERE {
	?s schema:title ?title ;
	schema:latitude ?latitude ;
	schema:longitude ?longitude .
	OPTIONAL { ?s schema:startDate ?startDate }
	OPTIONAL { ?s schema:endDate ?endDate }
	OPTIONAL { ?s schema:startTime ?startTime }
	OPTIONAL { ?s schema:endTime ?endTime }	
	BIND (str(?title) as ?tit)
	BIND (str(?latitude) as ?lat)
	BIND (str(?longitude) as ?lon)
	BIND (str(?startDate) as ?sDate)
	BIND (str(?endDate) as ?eDate)
	bind( strafter( str(?startTime), "T" ) as ?sTime )
	bind( strafter( str(?endTime), "T" ) as ?eTime )
	FILTER (?lat = '-21.171446' && ?lon = '-47.860565')
}
ORDER BY DESC (?startDate)

## Recupera todos os urls com data inicio, fim, latitude e longitude
http://localhost:8080/api/triplestore?query=PREFIX%20rdf%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20%3Furl%20WHERE%20%7B%20%0A%09%3Fs%20schema%3AserviceURL%20%3FserviceURL%20%0A%09bind(%20str(%3FserviceURL)%20as%20%3Furl%20)%20%20%0A%7D

PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?startDate ?endDate ?url WHERE { 
	?s schema:serviceURL ?serviceURL ;
	OPTIONAL { ?s schema:startDate ?startDate }
	OPTIONAL { ?s schema:endDate ?endDate }
	bind( str(?serviceURL) as ?url )  
}

## Busca tudo num raio de 1km
http://localhost:8080/api/triplestore?query=PREFIX%20rdf%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20%3Ftit%20%3Flat%20%3Flon%20%3FsDate%20%3FeDate%20%3FsTime%20%3FeTime%20WHERE%20%7B%0A%09%3Fs%20schema%3Atitle%20%3Ftitle%20%3B%0A%09schema%3Alatitude%20%3Flatitude%20%3B%0A%09schema%3Alongitude%20%3Flongitude%20.%0A%09OPTIONAL%20%7B%20%3Fs%20schema%3AstartDate%20%3FstartDate%20%7D%0A%09OPTIONAL%20%7B%20%3Fs%20schema%3AendDate%20%3FendDate%20%7D%0A%09OPTIONAL%20%7B%20%3Fs%20schema%3AstartTime%20%3FstartTime%20%7D%0A%09OPTIONAL%20%7B%20%3Fs%20schema%3AendTime%20%3FendTime%20%7D%09%0A%09BIND%20(str(%3Ftitle)%20as%20%3Ftit)%0A%09BIND%20(str(%3Flatitude)%20as%20%3Flat)%0A%09BIND%20(str(%3Flongitude)%20as%20%3Flon)%0A%09BIND%20(str(%3FstartDate)%20as%20%3FsDate)%0A%09BIND%20(str(%3FendDate)%20as%20%3FeDate)%0A%09bind(%20strafter(%20str(%3FstartTime)%2C%20%22T%22%20)%20as%20%3FsTime%20)%0A%09bind(%20strafter(%20str(%3FendTime)%2C%20%22T%22%20)%20as%20%3FeTime%20)%0A%09FILTER%20(%3Flat%20%3E%20%27-23.625810983940813%27%20%26%26%20%3Flat%20%3C%20%27-23.64379741605919%27)%0A%09FILTER%20(%3Flon%20%3E%20%27-46.63022315780229%27%20%26%26%20%3Flon%20%3C%20%27-46.6498564421977%27)%0A%7D%0AORDER%20BY%20(%3Flat)

PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?tit ?lat ?lon ?sDate ?eDate ?sTime ?eTime WHERE {
	?s schema:title ?title ;
	schema:latitude ?latitude ;
	schema:longitude ?longitude .
	OPTIONAL { ?s schema:startDate ?startDate }
	OPTIONAL { ?s schema:endDate ?endDate }
	OPTIONAL { ?s schema:startTime ?startTime }
	OPTIONAL { ?s schema:endTime ?endTime }	
	BIND (str(?title) as ?tit)
	BIND (str(?latitude) as ?lat)
	BIND (str(?longitude) as ?lon)
	BIND (str(?startDate) as ?sDate)
	BIND (str(?endDate) as ?eDate)
	bind( strafter( str(?startTime), "T" ) as ?sTime )
	bind( strafter( str(?endTime), "T" ) as ?eTime )
	FILTER (?lat > '-23.625810983940813' && ?lat < '-23.64379741605919')
	FILTER (?lon > '-46.63022315780229' && ?lon < '-46.6498564421977')
}
ORDER BY (?lat)

## Busca tudo num raio de 500m
http://localhost:8080/api/triplestore?query=PREFIX%20rdf%3A%3Chttp%3A%2F%2Fwww.w3.org%2F1999%2F02%2F22-rdf-syntax-ns%23%3E%0APREFIX%20rdfs%3A%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20mantichub%3A%3Cchttp%3A%2F%2Fwww.wemantic.com%2Fevents%23%3E%0APREFIX%20schema%3A%3Chttp%3A%2F%2Fschema.org%2F%3E%0ASELECT%20%3Ftit%20%3Flat%20%3Flon%20%3FsDate%20%3FeDate%20%3FsTime%20%3FeTime%20WHERE%20%7B%0A%09%3Fs%20schema%3Atitle%20%3Ftitle%20%3B%0A%09schema%3Alatitude%20%3Flatitude%20%3B%0A%09schema%3Alongitude%20%3Flongitude%20.%0A%09OPTIONAL%20%7B%20%3Fs%20schema%3AstartDate%20%3FstartDate%20%7D%0A%09OPTIONAL%20%7B%20%3Fs%20schema%3AendDate%20%3FendDate%20%7D%0A%09OPTIONAL%20%7B%20%3Fs%20schema%3AstartTime%20%3FstartTime%20%7D%0A%09OPTIONAL%20%7B%20%3Fs%20schema%3AendTime%20%3FendTime%20%7D%09%0A%09BIND%20(str(%3Ftitle)%20as%20%3Ftit)%0A%09BIND%20(str(%3Flatitude)%20as%20%3Flat)%0A%09BIND%20(str(%3Flongitude)%20as%20%3Flon)%0A%09BIND%20(str(%3FstartDate)%20as%20%3FsDate)%0A%09BIND%20(str(%3FendDate)%20as%20%3FeDate)%0A%09bind(%20strafter(%20str(%3FstartTime)%2C%20%22T%22%20)%20as%20%3FsTime%20)%0A%09bind(%20strafter(%20str(%3FendTime)%2C%20%22T%22%20)%20as%20%3FeTime%20)%0A%09FILTER%20(%3Flat%20%3E%20%27-23.625810983940813%27%20%26%26%20%3Flat%20%3C%20%27-23.64379741605919%27)%0A%09FILTER%20(%3Flon%20%3E%20%27-46.63022315780229%27%20%26%26%20%3Flon%20%3C%20%27-46.6498564421977%27)%0A%7D%0AORDER%20BY%20(%3Flat)

PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
PREFIX mantichub:<chttp://www.wemantic.com/events#>
PREFIX schema:<http://schema.org/>
SELECT ?tit ?lat ?lon ?sDate ?eDate ?sTime ?eTime WHERE {
	?s schema:title ?title ;
	schema:latitude ?latitude ;
	schema:longitude ?longitude .
	OPTIONAL { ?s schema:startDate ?startDate }
	OPTIONAL { ?s schema:endDate ?endDate }
	OPTIONAL { ?s schema:startTime ?startTime }
	OPTIONAL { ?s schema:endTime ?endTime }	
	BIND (str(?title) as ?tit)
	BIND (str(?latitude) as ?lat)
	BIND (str(?longitude) as ?lon)
	BIND (str(?startDate) as ?sDate)
	BIND (str(?endDate) as ?eDate)
	bind( strafter( str(?startTime), "T" ) as ?sTime )
	bind( strafter( str(?endTime), "T" ) as ?eTime )
	FILTER (?lat > '-23.630307591970407' && ?lat < '-23.639300808029596')
	FILTER (?lon > '-46.635131478901144' && ?lon < '-46.64494812109885')
}
ORDER BY (?lat) 
