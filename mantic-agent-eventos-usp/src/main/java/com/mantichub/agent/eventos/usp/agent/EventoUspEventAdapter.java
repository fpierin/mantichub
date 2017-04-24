package com.mantichub.agent.eventos.usp.agent;

import static com.mantichub.core.util.HTMLUtils.dateFromRegex;
import static com.mantichub.core.util.HTMLUtils.doubleFromRegex;
import static com.mantichub.core.util.HTMLUtils.nonHtml;
import static com.mantichub.core.util.HTMLUtils.trimValueByPattern;
import static com.mantichub.core.util.HTMLUtils.valueByPattern;
import static com.mantichub.core.util.StringUtils.isNotBlank;

import java.util.Date;

import com.mantichub.commons.resource.Event;
import com.mantichub.commons.resource.Resources;

public class EventoUspEventAdapter implements Event {
	
	private final String html;
	private final String url;
	
	public static final String END_DATE_PATTERN = "<td>\\d{2}/\\d{2}/\\d{2} - (\\d{2}/\\d{2}/\\d{2}) \\| \\d{2}:\\d{2} - \\d{2}:\\d{2}<br";
	public static final String END_TIME_PATTERN = "<td>\\d{2}/\\d{2}/\\d{2} - \\d{2}/\\d{2}/\\d{2} \\| \\d{2}:\\d{2} - (\\d{2}:\\d{2})<br";
	public static final String END_TIME_PATTERN_2 = "<td>\\d{2}/\\d{2}/\\d{2} \\| \\d{2}:\\d{2} - (\\d{2}:\\d{2})<br";
	public static final String LATITUDE_PATTERN = "maps\\?q=([^,]+)";
	public static final String LONGITITUDE_PATTERN = "maps\\?q=[^,]+,([^+]+)";
	public static final String OVERVIEW_PATTERN = "evento-conteudo\">(.+?)(?=<!--)";
	public static final String START_DATE_PATTERN = "<td>(\\d{2}/\\d{2}/\\d{2})( - \\d{2}/\\d{2}/\\d{2})? \\| \\d{2}:\\d{2} - \\d{2}:\\d{2}<br";
	public static final String START_TIME_PATTERN = "<td>\\d{2}/\\d{2}/\\d{2} - \\d{2}/\\d{2}/\\d{2} \\| (\\d{2}:\\d{2}) - \\d{2}:\\d{2}<br";
	public static final String START_TIME_PATTERN_2 = "<td>\\d{2}/\\d{2}/\\d{2} \\| (\\d{2}:\\d{2}) - \\d{2}:\\d{2}<br";
	public static final String STREET_ADDRESS_PATTERN = "var endereco = '(([^,]+?), [\\d|s/n]+)";
	public static final String TITLE_PATTERN = "evento-titulo\\\"[^>]+>([^<]+)";
	public static final String FREE_EVENT_PATTERN = "(<strong>Evento Gratuito</strong>)";
	
	public EventoUspEventAdapter(final String url, final String html) {
		this.url = url;
		this.html = html;
	}
	
	@Override
	public String getStreetAddress() {
		return valueByPattern(html, STREET_ADDRESS_PATTERN);
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
	public Resources getType() {
		return Resources.ExhibitionEvent;
	}
	
	@Override
	public Date getEndDate() {
		Date endDate = dateFromRegex(html, END_DATE_PATTERN, "dd/MM/yy");
		if (endDate == null) {
			endDate = dateFromRegex(html, START_DATE_PATTERN, "dd/MM/yy");
		}
		return endDate;
	}
	
	@Override
	public Date getEndTime() {
		Date endTime = dateFromRegex(html, END_TIME_PATTERN, "HH:mm");
		if (endTime == null) {
			endTime = dateFromRegex(html, END_TIME_PATTERN_2, "HH:mm");
		}
		return endTime;
	}
	
	@Override
	public Date getStartDate() {
		return dateFromRegex(html, START_DATE_PATTERN, "dd/MM/yy");
	}
	
	@Override
	public Date getStartTime() {
		Date startTime = dateFromRegex(html, START_TIME_PATTERN, "HH:mm");
		if (startTime == null) {
			startTime = dateFromRegex(html, START_TIME_PATTERN_2, "HH:mm");
		}
		return startTime;		
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
		final String freeEvent = valueByPattern(html, FREE_EVENT_PATTERN);
		return isNotBlank(freeEvent) ? 0.00 : null;
	}
	
	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public String getDescription() {
		return getOverview();
	}
	
}
