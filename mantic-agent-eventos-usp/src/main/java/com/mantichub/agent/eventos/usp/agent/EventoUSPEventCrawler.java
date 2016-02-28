package com.mantichub.agent.eventos.usp.agent;

import static com.mantichub.core.util.HTMLUtils.dateFromRegex;
import static com.mantichub.core.util.HTMLUtils.doubleFromRegex;
import static com.mantichub.core.util.HTMLUtils.nonhtmlValueByPattern;
import static com.mantichub.core.util.HTMLUtils.valueByPattern;
import static com.mantichub.core.util.StringUtils.isNotBlank;

import java.util.Date;

import org.apache.jena.rdf.model.Resource;

import com.mantichub.core.agent.EventCrawler;
import com.mantichub.core.vocabulary.SCHEMA;

public class EventoUSPEventCrawler implements EventCrawler {

	private final String html;

	public static final String END_DATE_PATTERN = "<td>\\d{2}/\\d{2}/\\d{2} - (\\d{2}/\\d{2}/\\d{2}) \\| \\d{2}:\\d{2} - \\d{2}:\\d{2}<br>";
	public static final String END_TIME_PATTERN = "<td>\\d{2}/\\d{2}/\\d{2} - \\d{2}/\\d{2}/\\d{2} \\| \\d{2}:\\d{2} - (\\d{2}:\\d{2})<br>";
	public static final String LATITUDE_PATTERN = "maps\\?q=([^,]+)";
	public static final String LONGITITUDE_PATTERN = "maps\\?q=[^,]+,([^+]+)";
	public static final String OVERVIEW_PATTERN = "evento-conteudo\">(.+?)(?=<!--)";
	public static final String START_DATE_PATTERN = "<td>(\\d{2}/\\d{2}/\\d{2}) - \\d{2}/\\d{2}/\\d{2} \\| \\d{2}:\\d{2} - \\d{2}:\\d{2}<br>";
	public static final String START_TIME_PATTERN = "<td>\\d{2}/\\d{2}/\\d{2} - \\d{2}/\\d{2}/\\d{2} \\| (\\d{2}:\\d{2}) - \\d{2}:\\d{2}<br>";
	public static final String STREET_ADDRESS_PATTERN = "var endereco = '(([^,]+?), [\\d|s/n]+)";
	public static final String TITLE_PATTERN = "evento-titulo\\\"[^>]+>([^<]+)";
	public static final String FREE_EVENT_PATTERN = "(<strong>Evento Gratuito</strong>)";

	public EventoUSPEventCrawler(final String html) {
		this.html = html;
	}

	public String getStreetAddress() {
		return valueByPattern(html, STREET_ADDRESS_PATTERN);
	}

	public String getTitle() {
		return valueByPattern(html, TITLE_PATTERN);
	}

	public Double getLatitude() {
		return doubleFromRegex(html, LATITUDE_PATTERN);
	}

	public Double getLongitude() {
		return doubleFromRegex(html, LONGITITUDE_PATTERN);
	}

	public Resource getType() {
		return SCHEMA.ExhibitionEvent;
	}

	public Date getEndDate() {
		return dateFromRegex(html, END_DATE_PATTERN, "dd/MM/yy");
	}

	public Date getEndTime() {
		return dateFromRegex(html, END_TIME_PATTERN, "HH:mm");
	}

	public Date getStartDate() {
		return dateFromRegex(html, START_DATE_PATTERN, "dd/MM/yy");
	}

	public Date getStartTime() {
		return dateFromRegex(html, START_TIME_PATTERN, "HH:mm");
	}

	public String getOverview() {
		return nonhtmlValueByPattern(html, OVERVIEW_PATTERN);
	}

	public Double getPrice() {
		final String freeEvent = valueByPattern(html, FREE_EVENT_PATTERN);
		return isNotBlank(freeEvent) ? 0.00 : null;
	}

}
