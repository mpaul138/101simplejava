package org.softlang.company.features;

import java.io.FileReader;
import java.io.IOException;

import org.softlang.company.model.Company;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Parsing {
	public static Company parseFromFile(String file) throws IOException {
		Gson gson = new Gson();
		JsonReader read = new JsonReader(new FileReader(file));
		Company res = new Company("");
		res = gson.fromJson(read, res.getClass());
		read.close();
		return res;
	}
}
