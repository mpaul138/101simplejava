package org.softlang.company.features;

import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;

public class Total {

	public static double total(JsonNode node) {
		double result = 0.0;
		if (node != null) {
			if (node.get("salary") != null)
				result += node.get("salary").asDouble();
			else {
				if (node.get("departments") != null) {
					Iterator<JsonNode> iterator = node.get("departments")
							.elements();
					while (iterator.hasNext()) {
						result += total(iterator.next());
					}
				}
				if (node.get("employees") != null) {
					Iterator<JsonNode> iterator = node.get("employees")
							.elements();
					while (iterator.hasNext()) {
						result += total(iterator.next());
					}
				}
			}
			if (node.get("manager") != null) {
				result += total(node.get("manager"));
			}
		}
		return result;
	}

}