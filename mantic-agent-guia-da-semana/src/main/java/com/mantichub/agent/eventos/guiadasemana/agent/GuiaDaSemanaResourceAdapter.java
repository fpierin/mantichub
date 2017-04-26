package com.mantichub.agent.eventos.guiadasemana.agent;

import static com.mantichub.agent.eventos.guiadasemana.config.Configuration.BASE_URL;
import static com.mantichub.core.util.HTMLUtils.dateFromRegex;
import static com.mantichub.core.util.HTMLUtils.doubleFromRegex;
import static com.mantichub.core.util.HTMLUtils.trimValueByPattern;
import static com.mantichub.core.util.HTMLUtils.valueByPattern;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.Date;
import java.util.List;

import com.mantichub.commons.resource.ResourceInterface;
import com.mantichub.commons.resource.Resources;

public class GuiaDaSemanaResourceAdapter implements ResourceInterface {

	private static final long serialVersionUID = -2501631784307632357L;
	
	public static final String DESCRIPTION_PATTERN = "itemprop=\"description\">([^<]+)";
	public static final String END_DATE_PATTERN = "itemprop=\"endDate\" datetime=\"([^\"]+)\"";
	public static final String IMAGE_PATTERN = "itemprop=\"image\" content=\"([^\"]+)\"";
	public static final String LATITUDE_PATTERN = "itemprop=\"latitude\" content=\"([^\"]+)\"";
	public static final String LONGITITUDE_PATTERN = "itemprop=\"longitude\" content=\"([^\"]+)\"";
	public static final String START_DATE_PATTERN = "itemprop=\"startDate\" datetime=\"([^\"]+)\"";
	public static final String STREET_ADDRESS_PATTERN = "itemprop=\"address\" content=\"([^\"]+)\"";
	public static final String TITLE_PATTERN = "<a href=\"[^\"]+\" title=\"([^\"]+)\" itemprop=\"url\"></a>";
	public static final String URL_PATTERN = "<a href=\"([^\"]+)\" title=\"[^\"]+\" itemprop=\"url\"></a>";
	public static final String TYPE_PATTERN = "class=\"section-label section-[0-9]+\">([^<]+)";
	
	private String html;
	
	public GuiaDaSemanaResourceAdapter(final String html) {
		this.setHtml(html);
	}

	@Override
	public Date getEndDate() {
		return dateFromRegex(html, END_DATE_PATTERN, "yyyy-MM-dd'T'HH:mm");
	}
	
	@Override
	public Date getEndTime() {
		return dateFromRegex(html, END_DATE_PATTERN, "yyyy-MM-dd'T'HH:mm");
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
	public String getOverview() {
		return null;
	}

	@Override
	public Date getStartDate() {
		return dateFromRegex(html, START_DATE_PATTERN, "yyyy-MM-dd'T'HH:mm");
	}

	@Override
	public Date getStartTime() {
		return dateFromRegex(html, START_DATE_PATTERN, "yyyy-MM-dd'T'HH:mm");
	}

	@Override
	public String getStreetAddress() {
		return valueByPattern(html, STREET_ADDRESS_PATTERN);
	}

	@Override
	public String getTitle() {
		return trimValueByPattern(html, TITLE_PATTERN);
	}

	@Override
	public Resources getType() {
		try {
			final String v = valueByPattern(html, TYPE_PATTERN);
			if ("Cinema".equalsIgnoreCase(v)) {
				return Resources.ScreeningEvent;	
			}			
			if ("Exposição".equalsIgnoreCase(v)) {
				return Resources.ExhibitionEvent;	
			}		
			if ("Gastronomia".equalsIgnoreCase(v)) {
				return Resources.FoodEvent;	
			}
			if ("Música".equalsIgnoreCase(v) || "Shows".equalsIgnoreCase(v)) {
				return Resources.MusicEvent;	
			}
			if ("Na Cidade".equalsIgnoreCase(v)) {
				return Resources.SocialEvent;
			}
			if ("Teatro".equalsIgnoreCase(v)) {
				return Resources.TheaterEvent;	
			}
			if ("Restaurantes".equalsIgnoreCase(v)) {
				if (getDescription().toLowerCase().contains("festival")) {
					return Resources.Festival;
				}
			}
			System.out.println(v);
		}catch (final Exception e) {
		}
		return null;
	}

	@Override
	public Double getPrice() {
		return null;
	}
	
	@Override
	public String getUrl() {
		String url = valueByPattern(html, URL_PATTERN);
		if (isNotBlank(url)) {
			if (!url.contains(BASE_URL)) {
				url = BASE_URL + url;
			}
		}
		return url; 
	}

	@Override
	public String getCuisine() {
		return null;
	}

	@Override
	public String getDescription() {
		return trimValueByPattern(html, DESCRIPTION_PATTERN);
	}

	@Override
	public List<String> getOpeningHours() {
		return null;
	}

	@Override
	public String getPriceRange() {
		return null;
	}

	@Override
	public String getTelephone() {
		return null;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(final String html) {
		this.html = html;
	}

	@Override
	public String getImage() {
		return valueByPattern(html, IMAGE_PATTERN);
	}


}

