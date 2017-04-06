package com.mantichub.agent.guiafolha.agent;

import static com.mantichub.core.util.HTMLUtils.doubleFromRegex;
import static com.mantichub.core.util.HTMLUtils.replace;
import static com.mantichub.core.util.HTMLUtils.trim;
import static com.mantichub.core.util.HTMLUtils.trimValueByPattern;
import static com.mantichub.core.util.HTMLUtils.valueByPattern;

import org.apache.jena.rdf.model.Resource;

import com.mantichub.agent.core.infra.Restaurant;
import com.mantichub.core.vocabulary.SCHEMA;

public class GuiaDaFolhaRestaurantAdapter implements Restaurant {
	
	private static final String CUISINE_PATTERN = "Restaurantes[^s]*<span>([^<]*)";
	private static final String DESCRIPTION_PATTERN = "js-content-article event__summary[^<]*<p>([^<]*)";
	private static final String STREET_ADDRESS_PATTERN = "<span class=\"js-map-address\"[^>]*>([^-]*)";
	private static final String TELEPHONE_PATTERN = "Telefone[^td>]*td>[\\s\\n]+<td>[\\s\\n]+([0-9\\-]+)";
	private static final String TITLE_PATTERN = "content-header__title\">(.+?)</h1>";
	private static final String LATITUDE_PATTERN = "<span class=\"js-map-address\" data-value=\"[^-]*([^\\s\\n]*)";
	private static final String LONGITITUDE_PATTERN = "<span class=\"js-map-address\" data-value=\"[^-]*-[^-]*([^\\s\\n]*)";
	
	private final String html;
	private final String url;
	
	public GuiaDaFolhaRestaurantAdapter(final String html, final String url) {
		this.html = html;
		this.url = url;
	}

	public static void main(final String[] args) {
		final String html = "<div class=\"content-header__labels\">\n" +
				"                    <a href=\"http://guia.folha.uol.com.br/busca/restaurantes\" class=\"label label--restaurantes\">\n" +
				"                        Restaurantes\n" +
				"                    </a>\n" +
				"                    <span>Italiana</span>\n" +
				"                  </div>";

		System.out.println(new GuiaDaFolhaRestaurantAdapter(html, null).getCuisine());
	}
	
	@Override
	public String getCuisine() {
		return trimValueByPattern(html, CUISINE_PATTERN, "\n| ", "  | ");
	}

	@Override
	public String getDescription() {
		return trimValueByPattern(html, DESCRIPTION_PATTERN, "\n| ", "  | ");
	}
	
	@Override
	public Double getLatitude() {
		return doubleFromRegex(html, LATITUDE_PATTERN);
	}

	@Override
	public Double getLongitude() {
		return doubleFromRegex(html, LONGITITUDE_PATTERN);
	}

	@Override
	public String getStreetAddress() {
		return trim(replace(valueByPattern(html, STREET_ADDRESS_PATTERN), "\n| ", "  | "));
	}

	@Override
	public String getTelephone() {
		return valueByPattern(html, TELEPHONE_PATTERN);
	}
	
	@Override
	public String getTitle() {
		return valueByPattern(html, TITLE_PATTERN);
	}

	@Override
	public Resource getType() {
		return SCHEMA.Restaurant;
	}
	
	@Override
	public String getUrl() {
		return url;
	}
	
}
