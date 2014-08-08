package org.softlang.company.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
import org.softlang.company.features.Cut;
import org.softlang.company.features.Total;

public class TestCut {

	@Test
	public void testCut() {
		String file = "inputs" + File.separator + "sampleCompany.json";
		String file2 = "outputs" + File.separator + "outputCompany.json";

		File in = new File(file);
		File out = new File(file2);
		new File("outputs").mkdir();

		assertEquals(326927.0, Total.total(in), 0.0);
		Cut.cut(in, out);
		assertEquals(Total.total(in) / 2, Total.total(out), 0.0);

	}
}