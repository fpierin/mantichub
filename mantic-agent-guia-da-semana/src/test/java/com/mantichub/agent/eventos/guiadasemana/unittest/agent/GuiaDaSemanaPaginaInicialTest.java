package com.mantichub.agent.eventos.guiadasemana.unittest.agent;

import static com.mantichub.agent.eventos.guiadasemana.infra.TestUtils.fromFile;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.mantichub.core.util.HTMLUtils;

public class GuiaDaSemanaPaginaInicialTest {

	private final static String arquivo = "src/test/resources/capa.html";

	@Test
	public void verificaPrice() throws Exception {
//"<a href=\"" title=\"A Vida Lá Fora: O Cinema de Jean Renoir\" itemprop=\"url\"></a>";
		
//		String a ="<article class=\"search-result\" itemprop=\"itemListElement\" itemscope=\"\" itemtype=\"http://schema.org/Event http://schema.org/ListItem\">\n" + 
//		"          <meta itemprop=\"position\" content=\"1\">\n" + 
//		"          <a href=\"/sao-paulo/cinema/evento/a-vida-la-fora-o-cinema-de-jean-renoir-centro-cultural-banco-do-brasil-01-02-2017\" title=\"A Vida Lá Fora: O Cinema de Jean Renoir\" itemprop=\"url\"></a>\n" + 
//		"          <div class=\"box-content\">\n" + 
//		"            <div class=\"box-image\">\n" + 
//		"              <img class=\"lazy\" alt=\"\" data-original=\"https://gds-wifmtpphmjvvgffvmg.netdna-ssl.com/contentFiles/image/2017/01/EVT/thumbnail/63217_w380h235_1485886972besta.jpg\" src=\"https://gds-wifmtpphmjvvgffvmg.netdna-ssl.com/contentFiles/image/2017/01/EVT/thumbnail/63217_w380h235_1485886972besta.jpg\" style=\"display: inline;\">\n" + 
//		"            </div>\n" + 
//		"            <div class=\"box-text\">\n" + 
//		"                        <p class=\"section-label section-6\">Cinema</p>\n" + 
//		"              <h2 itemprop=\"name\">A Vida Lá Fora: O Cinema de Jean Renoir</h2>\n" + 
//		"              <p itemprop=\"description\">CCBB recebe retrospectiva com 30 filmes do cineasta francês</p>\n" + 
//		"              <p><span class=\"date\">01-27 Fev</span></p>\n" + 
//		"              <p class=\"address\" itemprop=\"location\" itemscope=\"\" itemtype=\"http://schema.org/Place\">\n" + 
//		"                <i class=\"fa fa-map-marker\"></i> <span itemprop=\"name\">Centro Cultural Banco do Brasil</span>\n" + 
//		"                <meta itemprop=\"address\" content=\"Rua Álvares Penteado, 112\">\n" + 
//		"                <span itemprop=\"geo\" itemscope=\"\" itemtype=\"http://schema.org/GeoCoordinates\">\n" + 
//		"                <meta itemprop=\"latitude\" content=\"-23.547497\">\n" + 
//		"                <meta itemprop=\"longitude\" content=\"-46.634674\"></span>\n" + 
//		"              </p>            </div>\n" + 
//		"          </div>\n" + 
//		"          <meta itemprop=\"image\" content=\"https://gds-wifmtpphmjvvgffvmg.netdna-ssl.com/contentFiles/image/2017/01/EVT/thumbnail/63217_w380h235_1485886972besta.jpg\">                        <meta itemprop=\"startDate\" datetime=\"2017-02-01T00:00\" content=\"2017-02-01\">\n" + 
//		"              <meta itemprop=\"endDate\" datetime=\"2017-02-27T23:59\" content=\"2017-02-27\">\n" + 
//		"                  </article>";
//		public static final String EVENT_URL_PATTERN = "href=\"(http://www.eventos.usp.br/\\?events=(.*?))\"";
//		final Set<String> urlSet = HTMLUtils.setByPattern(a, "href=\"(.*?)\" title=\"");
		final Set<String> urlSet = HTMLUtils.setByPattern(fromFile(arquivo), "href=\"(/sao-paulo/.+?)\" title=\"");
//		System.out.println(urlSet);
//		/sao-paulo/exposicao/evento/icones-de-hollywood-espaco-cultural-porto-seguro-17-02-2017
//		/sao-paulo/agenda?page=2" aria-label="Próxima
		final Pattern pattern = Pattern.compile("(/sao-paulo/agenda\\?page=\\d+).*");
		for (String string : urlSet) {
			final Matcher matcher = pattern.matcher(string);
			while (matcher.find()) {
				final String node = matcher.group(1);
				urlSet.remove(string);
				System.out.println(node);
			}
		}
	}

}
