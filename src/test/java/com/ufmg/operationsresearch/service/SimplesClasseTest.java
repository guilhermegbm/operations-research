package com.ufmg.operationsresearch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimplesClasseTest {
	@BeforeEach
	public void init() {
		System.out.println("Passou fixture");
	}

	@Test
	public void teste1() {
		String batata = "Batata";

		assertEquals("Batata", batata);
	}

	@Test
	public void teste2() {
		String teste = "Teste";

		assertEquals("Teste", teste);
	}
}
