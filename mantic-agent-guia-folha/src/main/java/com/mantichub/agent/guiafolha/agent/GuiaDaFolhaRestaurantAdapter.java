package com.mantichub.agent.guiafolha.agent;

import static com.mantichub.core.util.HTMLUtils.doubleFromRegex;
import static com.mantichub.core.util.HTMLUtils.replace;
import static com.mantichub.core.util.HTMLUtils.trim;
import static com.mantichub.core.util.HTMLUtils.trimValueByPattern;
import static com.mantichub.core.util.HTMLUtils.valueByPattern;
import static com.mantichub.core.util.HTMLUtils.valueListByPattern;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.rdf.model.Resource;

import com.mantichub.agent.core.infra.Restaurant;
import com.mantichub.agent.guiafolha.utils.DatetimeUtils;
import com.mantichub.core.vocabulary.SCHEMA;

public class GuiaDaFolhaRestaurantAdapter implements Restaurant {
	
	private static final String CUISINE_PATTERN = "Restaurantes[^s]*<span>([^<]*)";
	private static final String DESCRIPTION_PATTERN = "js-content-article event__summary[^<]*<p>([^<]*)";
	private static final String LATITUDE_PATTERN = "<span class=\"js-map-address\" data-value=\"[^-]*([^\\s\\n]*)";
	private static final String LONGITITUDE_PATTERN = "<span class=\"js-map-address\" data-value=\"[^-]*-[^-]*([^\\s\\n]*)";
	private static final String OPENING_HOURS_PATTERN = "([\\w]{3}).: (\\d{1,2})h às (\\d{1,2})h";
	private static final String PRICE_RANGE_PATTERN = "Preço</td>[^\\$]*(\\$+)[^<]*";
	private static final String STREET_ADDRESS_PATTERN = "<span class=\"js-map-address\"[^>]*>([^-]*)";
	private static final String TELEPHONE_PATTERN = "Telefone[^td>]*td>[\\s\\n]+<td>[\\s\\n]+([0-9\\-]+)";
	private static final String TITLE_PATTERN = "content-header__title\">(.+?)</h1>";
	
	private final String html;
	private final String url;
	
	public GuiaDaFolhaRestaurantAdapter(final String url, final String html) {
		this.html = html;
		this.url = url;
	}

	public static void main(final String[] args) {
		final String html = "<tr id=\"hours\" class=\"table__content js-accordion-body\">\n" + "      <td colspan=\"2\">\n" + "        <ul class=\"list-info\">\n"
				+ "                                            <li class=\"list-info__item\">\n"
				+ "              <span class=\"list-info__text  list-info__text--active \">Qui.: 19h às 23h</span>\n" + "            </li>\n"
				+ "                                  <li class=\"list-info__item\">\n" + "              <span class=\"list-info__text \">Sex.: 19h às 23h</span>\n"
				+ "            </li>\n" + "                                  <li class=\"list-info__item\">\n"
				+ "              <span class=\"list-info__text \">Sab.: 12h às 16h</span>\n" + "            </li>\n"
				+ "                                  <li class=\"list-info__item\">\n" + "              <span class=\"list-info__text \">Dom.: 12h às 16h</span>\n"
				+ "            </li>\n" + "                                  <li class=\"list-info__item\">\n"
				+ "              <span class=\"list-info__text \">Seg.: Fechado</span>\n" + "            </li>\n"
				+ "                                  <li class=\"list-info__item\">\n" + "              <span class=\"list-info__text \">Ter.: Fechado</span>\n"
				+ "            </li>\n" + "                                  <li class=\"list-info__item\">\n"
				+ "              <span class=\"list-info__text \">Qua.: 19h às 23h</span>\n" + "            </li>\n" + "                  </ul>\n" + "      </td>\n" +
				"    </tr>";

		System.out.println(new GuiaDaFolhaRestaurantAdapter(html, null).getOpeningHours());
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
	public List<String> getOpeningHours() {
		final List<String[]> values = valueListByPattern(html, OPENING_HOURS_PATTERN, 3);
		if ((values != null) && !values.isEmpty()) {
			final List<String> valueList = new ArrayList<>();
			for (final String[] value : values) {
				final String day = DatetimeUtils.getWeekday(value[0]);
				final String startTime = twoDigitHour(value[1]);
				final String endTime = twoDigitHour(value[2]);
				if (isNotBlank(day) && isNotBlank(startTime) && isNotBlank(endTime)) {
					valueList.add(day + " " + startTime + "-" + endTime);
				}
			}
			return valueList;
		}
		return null;
	}
	
	private String twoDigitHour(final String value) {
		return (isNotBlank(value) && (value.length() > 1)) ? value + ":00" : "0" + value + ":00";
	}
	
	@Override
	public String getPriceRange() {
		return trimValueByPattern(html, PRICE_RANGE_PATTERN);
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
