package com.mantichub.core.app;

import static com.mantichub.core.util.DateUtils.getDate;
import static com.mantichub.core.util.ModelUtils.getFastWriter;
import static com.mantichub.core.util.ModelUtils.getNsPrefixMap;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFFormat;

import com.mantichub.core.builder.EventBuilder;
import com.mantichub.core.vocabulary.SCHEMA;

/**
 * Hello world!
 *
 */
public class App {
	private final static String projectNS = "http://www.wemantic.com/events#";

	public static void main(final String[] args) throws Exception {
		final RDFFormat rdfFormat = RDFFormat.RDFXML;
		final Model model = getFastWriter(rdfFormat, getNsPrefixMap("schema", SCHEMA.NS));
		new EventBuilder(model, projectNS)
		.resource(projectNS + "MACRecebeexposicaoACasa")
		.type(SCHEMA.ExhibitionEvent)
		.addressRegion("SP")
		.addressLocality("São Paulo")
		.endDate(getDate("2016-07-31", "yyyy-MM-dd"))
		.endTime(getDate("18:00:00", "HH:mm:ss"))
		.latitude(-23.587282)
		.longitude(-46.652424)
		.overview(getOverview())
		.price(0.00)
		.serviceUrl("http://www.eventos.usp.br/?events=mac-recebe-exposicao-a-casa")
		.startDate(getDate("2015-09-12", "yyyy-MM-dd"))
		.startTime(getDate("11:00:00", "HH:mm:ss"))
		.streetAddress("Avenida Pedro Álvares Cabral, 1301")
		.title("MAC recebe a exposição \"A Casa\"")
		.create();
		model.write(System.out, rdfFormat.getLang().getName());
	}

	private static String getOverview() {
		return "A curadora Katia Canton, vice-diretora do Museu de Arte Contemporânea (MAC) da USP, se inspira no poema de Vinícius de Moraes para a exposição A Casa que será inaugurada no dia 12 de setembro, a partir das 11 horas.\n"
				+ "\n"
				+ "A Casa apresenta 18 obras que pertencem ao acervo do MAC, dos artistas Alexander Calder, Leda Catunda, Alex Flemming, Iran do Espírito Santo, Cildo Meireles, Nina Moraes, Alex Vallauri, Flávio Cerqueira, Barrão, Regina Silveira, Maria Tomaselli, Camille Kachani, Ângelo Venosa, José Carratu e Ana Teixeira. Distribuídas não por ordem cronológica ou autoral, mas pela representação dos papéis que cada uma cumpriria em sua função de domesticidade, as obras permitem pensar possibilidades, por exemplo, caso o sofá de Regina Silveira fosse feito para sentar, ou a vitrola de Iran do Espírito Santo fosse pensada para tocar discos. Ou ainda as escumadeiras espetadas no cordeiro, de Alex Flemming, fossem feitas para fritar, utilizando para isso o fogão de Alex Vallauri.\n"
				+ "\n"
				+ "A exposição integra a pesquisa Temas da Arte Contemporânea, em que Katia discute como as novas propostas de apreciação e aprendizado da arte podem ser realizadas sob uma variedade de recortes, com temporalidades, temas e enunciados diferentes e muitas vezes justapostos.\n"
				+ "\n" + "O MAC USP Ibirapuera fica localizado na Avenida Pedro Álvares Cabral, 1301 em São Paulo. A exposição será exibida de terça a domingo das 10 às 18 horas. Entrada gratuita.";
	}
}