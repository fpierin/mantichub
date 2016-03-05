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

public enum Charset {

	UTF_8("UTF-8"),
	ISO_8859_1("ISO-8859-1");

	private final String type;

	private Charset(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
	
	public String type() {
		return type;
	}

}