package com.mantichub.agent.eventos.guiadasemana.agent;

import static com.mantichub.core.util.HTMLUtils.dateFromRegex;
import static com.mantichub.core.util.HTMLUtils.doubleFromRegex;
import static com.mantichub.core.util.HTMLUtils.nonhtmlValueByPattern;
import static com.mantichub.core.util.HTMLUtils.valueByPattern;
import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.rdf.model.Resource;

import com.mantichub.agent.core.infra.Event;
import com.mantichub.core.vocabulary.SCHEMA;

public class GuiaDaSemanaEventAdapter implements Event {

	private final String html;
	private final String url;

	public static final String END_DATE_PATTERN = "Data</strong>.+?\\-(\\d+ \\w+)";
	public static final String END_TIME_PATTERN = "<td>\\d{2}/\\d{2}/\\d{2} - \\d{2}/\\d{2}/\\d{2} \\| \\d{2}:\\d{2} - (\\d{2}:\\d{2})<br>";
	public static final String LATITUDE_PATTERN = "<meta itemprop=\"latitude\" content=\"(.+?)\">";
	public static final String LONGITITUDE_PATTERN = "<meta itemprop=\"longitude\" content=\"(.+?)\">";
	public static final String MAX_PRICE_PATTERN = "<span itemprop=\"priceRange\">.+?R\\$(\\d+[.]\\d+)[\\s\\r\\n]*?</span>";
	public static final String OVERVIEW_PATTERN = "<div itemprop=\"description\">(.*)</div>[\\s\\r\\n]*?<div class=\"borderEnd \">";
	public static final String SINGLE_DATE_PATTERN = "Data</strong>[\\s\\n]+?(.{1,3} .{1,2} .{1,3})[\\s\\n]+?</p>";
	public static final String START_DATE_PATTERN = "Data</strong>[\\s\\n]+?(\\d+ \\w+)";
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
		return doubleFromRegex(html, LATITUDE_PATTERN);
	}

	@Override
	public Double getLongitude() {
		return doubleFromRegex(html, LONGITITUDE_PATTERN);
	}

	@Override
	public Resource getType() {
		return SCHEMA.ExhibitionEvent;
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
		final Date dateWithYear = dateWithYear(SINGLE_DATE_PATTERN, "EEE dd MMM");
		return dateWithYear != null ? dateWithYear : dateWithYear(START_DATE_PATTERN, "dd MMM");
	}

	@Override
	public Date getStartTime() {
		return dateFromRegex(html, START_TIME_PATTERN, "HH:mm");
	}

	@Override
	public String getOverview() {
		return nonhtmlValueByPattern(html, OVERVIEW_PATTERN);
	}

	@Override
	public Double getPrice() {
		return doubleFromRegex(html, MAX_PRICE_PATTERN);
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
