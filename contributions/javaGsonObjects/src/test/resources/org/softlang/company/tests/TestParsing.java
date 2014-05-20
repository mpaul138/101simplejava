package org.softlang.company.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.softlang.company.features.Parsing;

public class TestParsing {

	@Test
	public void test() {
		String in = "inputs" + File.separator + "sampleCompany.json";
		try {
			Parsing.parseFromFile(in);
		} catch (IOException e) {
			fail();
		}
	}

}
