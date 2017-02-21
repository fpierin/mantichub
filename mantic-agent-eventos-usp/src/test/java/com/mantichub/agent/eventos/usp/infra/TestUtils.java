package com.mantichub.agent.eventos.usp.infra;

import java.io.File;
import java.util.Scanner;

public class TestUtils {

	public static String fromFile(final String arquivo) throws Exception {
		final Scanner scanner = new Scanner(new File(arquivo));
		final String text = scanner.useDelimiter("\\A").next();
		scanner.close();
		return text;
	}

}
