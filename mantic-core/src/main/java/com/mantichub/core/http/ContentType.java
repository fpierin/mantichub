/*
 * Copyright (c) 1996-2013 UOL Inc, Todos os direitos reservados
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
 *
 */
package com.mantichub.core.http;


/**
 * @author Felipe Pierin <fpierin@uolinc.com>
 *
 */
public enum ContentType {

	APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),
	JSON("application/json"),
	JSON_VND_RESOURCE("application/vnd.resource+json"),
	JSON_BILLING_V1("application/uol.com.br.billing-v1+json; charset=UTF-8;");

	private final String type;

	private ContentType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}

}