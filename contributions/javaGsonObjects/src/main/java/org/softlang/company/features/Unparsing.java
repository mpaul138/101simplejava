package org.softlang.company.features;

import java.io.FileWriter;
import java.io.IOException;

import org.softlang.company.model.Company;

import com.google.gson.Gson;

public class Unparsing {
	public static void unparseToFile(Company c, String file) throws IOException {
		Gson gson = new Gson();
		FileWriter writer = new FileWriter(file);
		String json = gson.toJson(c);
		writer.write(json);
		writer.close();
	}
}
