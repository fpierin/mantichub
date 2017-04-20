package com.mantichub.agent.eventos.guiadasemana.agent;

import static com.mantichub.core.util.HTMLUtils.dateFromRegex;
import static com.mantichub.core.util.HTMLUtils.doubleFromRegex;
import static com.mantichub.core.util.HTMLUtils.hasPattern;
import static com.mantichub.core.util.HTMLUtils.nonHtml;
import static com.mantichub.core.util.HTMLUtils.trimValueByPattern;
import static com.mantichub.core.util.HTMLUtils.valueByPattern;
import static java.util.Calendar.DATE;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.mantichub.commons.resource.Event;
import com.mantichub.commons.resource.Resources;

public class GuiaDaSemanaEventAdapter implements Event {

	private final String html;
	private final String url;

	public static final String THEATER_PATTERN = "(class=\"section-26\">Teatro</a>)";
	public static final String END_DATE_PATTERN = "Data</strong>.+?\\-(\\d+ \\w+)";
	public static final String END_TIME_PATTERN = "<td>\\d{2}/\\d{2}/\\d{2} - \\d{2}/\\d{2}/\\d{2} \\| \\d{2}:\\d{2} - (\\d{2}:\\d{2})<br>";
	public static final String LATITUDE_PATTERN = "<meta itemprop=\"latitude\" content=\"(.+?)\">";
	public static final String LONGITITUDE_PATTERN = "<meta itemprop=\"longitude\" content=\"(.+?)\">";
	public static final String LATITUDE_PATTERN_2 = "&amp;lat=(-?[\\d\\.]+)&amp;lng=-?[\\d\\.]+";
	public static final String LONGITITUDE_PATTERN_2 = "&amp;lat=-?[\\d\\.]+&amp;lng=(-?[\\d\\.]+)";
	public static final String MAX_PRICE_PATTERN = "priceRange\">*[^R]*R\\$[^R]*R\\$(\\d+[.]\\d+)[^<]*?</span>";
	public static final String MIN_PRICE_PATTERN = "priceRange\">[^R]*?R\\$(\\d+[.]\\d+)[^<]*?</span>";
	public static final String FREE_PRICE_PATTERN = "priceRange\">[^G]*Grátis[^<]*</span>";
	public static final String OVERVIEW_PATTERN = "<div itemprop=\"description\">(.*)</div>[\\s\\r\\n]*?<div class=\"borderEnd \">";
	public static final String SINGLE_DATE_PATTERN = "Data</strong>[^\\d]*?(.{1,3} .{1,2} .{1,3})[\\s\\n]+?</p>";
	public static final String START_DATE_PATTERN = "Data</strong>[^\\d]*?(\\d+ \\w+)";
	public static final String START_DATE_PATTERN_2 = "Data</strong>[^\\d]*?(\\d+)";
	public static final String START_TIME_PATTERN = "<td>\\d{2}/\\d{2}/\\d{2} - \\d{2}/\\d{2}/\\d{2} \\| (\\d{2}:\\d{2}) - \\d{2}:\\d{2}<br>";
	public static final String STREET_ADDRESS_PATTERN = "<span itemprop=\"streetAddress\">(.+?)</span>";
	public static final String TITLE_PATTERN = "<h4>(.+?)</h4>";
	public static final String FREE_EVENT_PATTERN = "(<strong>Evento Gratuito</strong>)";


	public GuiaDaSemanaEventAdapter(final String url, final String html) {
		this.html = html;
		this.url = url;
	}

	@Override
	public String getStreetAddress() {
		String value = valueByPattern(html, STREET_ADDRESS_PATTERN);
		if (!StringUtils.isEmpty(value)) {
			value = value.trim();
			if (value.endsWith(",")) {
				value = value.substring(0, value.length() -1);
			}
		}
		return value;
	}

	@Override
	public String getTitle() {
		return valueByPattern(html, TITLE_PATTERN);
	}

	@Override
	public Double getLatitude() {
		return doubleFromRegex(html, LATITUDE_PATTERN_2, LATITUDE_PATTERN);
	}

	@Override
	public Double getLongitude() {
		return doubleFromRegex(html, LONGITITUDE_PATTERN_2, LONGITITUDE_PATTERN);
	}

	@Override
	public Resources getType() {
		final String v = valueByPattern(html, THEATER_PATTERN);
		if (isNotBlank(v)) {
			return Resources.TheaterEvent;	
		}
		return Resources.ExhibitionEvent;
	}

	@Override
	public Date getEndDate() {
		final Date dateWithYear = dateWithYear(SINGLE_DATE_PATTERN, "EEE dd MMM");
		return dateWithYear != null ? dateWithYear : dateWithYear(END_DATE_PATTERN, "dd MMM");
	}

	@Override
	public Date getEndTime() {
		return dateFromRegex(html, END_TIME_PATTERN, "HH:mm");
	}

	@Override
	public Date getStartDate() {
		Date dateWithYear = dateWithYear(SINGLE_DATE_PATTERN, "EEE dd MMM");
		dateWithYear = dateWithYear != null ? dateWithYear : dateWithYear(START_DATE_PATTERN, "dd MMM");
		if (dateWithYear != null) {
			return dateWithYear;
		}
		
		dateWithYear = dateWithYear(START_DATE_PATTERN_2, "dd");
		if (dateWithYear != null) {
			final Date endDate = getEndDate();
			if (endDate != null) {
				final Calendar c2 = Calendar.getInstance();
				c2.setTime(endDate);
				
				final Calendar c1 = Calendar.getInstance();
				c1.setTime(dateWithYear);
				c1.set(c2.get(YEAR), c2.get(MONTH), c1.get(DAY_OF_MONTH));
				return c1.getTime();
			}
		}
		
		return dateWithYear;
	}

	@Override
	public Date getStartTime() {
		return dateFromRegex(html, START_TIME_PATTERN, "HH:mm");
	}

	@Override
	public String getOverview() {
		final String[] replacements = {"\n| ", "\t| ", "&nbsp;| ", "&atilde;|ã",
				"&otilde;|õ", "&ecirc;|ê",
				"&aacute;|á", "&eacute;|é","&iacute;|í","&oacute;|ó","&uacute;|ú",
				"&ccedil;|ç", "&ndash;|–", "  | "};
		return nonHtml(trimValueByPattern(html, OVERVIEW_PATTERN, replacements));
	}

	@Override
	public Double getPrice() {
		Double doubleFromRegex = doubleFromRegex(html, MAX_PRICE_PATTERN);
		if (doubleFromRegex == null) {
			doubleFromRegex = doubleFromRegex(html, MIN_PRICE_PATTERN);
		}
		if (doubleFromRegex == null) {
			return hasPattern(html, FREE_PRICE_PATTERN) ? 0.00 : null;
		}
		return doubleFromRegex;
	}

	private Date dateWithYear(final String endDatePattern, final String dateFormat) {
		final Date dateFromRegex = dateFromRegex(html, endDatePattern, dateFormat, new Locale("pt", "BR"));
		if (dateFromRegex != null) {
			final Calendar c = Calendar.getInstance();
			c.setTime(dateFromRegex);
			c.set(Calendar.getInstance().get(YEAR), c.get(MONTH), c.get(DATE));
			return c.getTime();
		}
		return null;
	}
	
	@Override
	public String getUrl() {
		return url;
	}

}


