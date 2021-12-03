package com.ufmg.operationsresearch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TesteServiceTest {

	@Autowired
	private TesteService testeService;

	@BeforeEach
	public void init() {

	}

	@Test
	public void testSomaRetornaValoresCorretosSimples() {
		assertEquals(0, this.testeService.soma(0, 0));
		assertEquals(1, this.testeService.soma(1, 0));
		assertEquals(1, this.testeService.soma(0, 1));
		assertEquals(2, this.testeService.soma(1, 1));
	}

	@Test
	public void testSomaRetornaValoresCorretosComplexo() {
		assertEquals(33, this.testeService.soma(14, 19));
		assertEquals(932, this.testeService.soma(515, 417));
		assertEquals(70, this.testeService.soma(44, 26));
		assertEquals(29, this.testeService.soma(27, 2));
	}
}
