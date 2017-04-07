/*
 * Copyright (c) 1996-2017 UOL Inc, Todos os direitos reservados
 *
 * Este arquivo e uma propriedade confidencial do Universo Online Inc. Nenhuma
 * parte do mesmo pode ser copiada, reproduzida, impressa ou transmitida por
 * qualquer meio sem autorizacao expressa e por escrito de um representante
 * legal do Universo Online Inc.
 *
 * All rights reserved
 *
 * This file is a confidential property of Universo Online Inc. No part of this
 * file may be reproduced or copied in any form or by any means without written
 * permission from an authorized person from Universo Online Inc.
 */
package com.mantichub.agent.guiafolha.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Felipe Pierin <fpierin@uolinc.com>
 *
 */
public class DatetimeUtils {
	
	private static Map<String, String> weekMap = null;

	private static Map<String, String> weekMap() {
		if (weekMap == null) {
			weekMap = new HashMap<>();
			weekMap.put("Seg", "Mo");
			weekMap.put("Ter", "Tu");
			weekMap.put("Qua", "We");
			weekMap.put("Qui", "Th");
			weekMap.put("Sex", "Fr");
			weekMap.put("Sab", "Sa");
			weekMap.put("Sáb", "Sa");
			weekMap.put("Dom", "Su");
		}
		return weekMap;
	};

	public static String getWeekday(final String day) {
		return weekMap().get(day);
	}


}
