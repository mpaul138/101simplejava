package org.softlang.company.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.softlang.company.features.CompanyCreator;
import org.softlang.company.features.Cut;
import org.softlang.company.features.Total;
import org.softlang.company.model.Company;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;

import akka.actor.ActorSystem;

public class TestCut {
	public Company c = CompanyCreator.createCompany();

	@Test
	public void testCut() {
		ActorSystem system = ActorSystem.create("MySystem");
		Cut.cut(c);
		assertEquals(CompanyCreator.SALARY, Total.total(c) * 2, 1e-10);
	}

}
