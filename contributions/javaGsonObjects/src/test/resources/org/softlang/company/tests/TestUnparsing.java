package org.softlang.company.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.softlang.company.features.Parsing;
import org.softlang.company.features.Unparsing;
import org.softlang.company.model.Company;

public class TestUnparsing {

	@Test
	public void test() {
		String out = "outputs" + File.separator + "outputCompany.json";
		String in = "inputs" + File.separator + "sampleCompany.json";
		Company c;
		try {
			c = Parsing.parseFromFile(in);
			Unparsing.unparseToFile(c, out);
			BufferedReader output = new BufferedReader(new FileReader(new File(
					out)));
			BufferedReader input = new BufferedReader(new FileReader(new File(
					in)));
			assertEquals(0, output.readLine().compareTo(input.readLine()));
			output.close();
			input.close();
		} catch (IOException e) {
			fail();
		}

	}
}
