package com.ufmg.operationsresearch.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Map.Entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ufmg.operationsresearch.matrix.hashmap_implementation.HashMapMatrixImpl;

public class ConcatMatrixTest {

	private HashMapMatrixImpl m1x1;
	private HashMapMatrixImpl m1x2;
	private HashMapMatrixImpl m2x1;
	private HashMapMatrixImpl m2x2;

	@BeforeEach
	public void init() {
		BigDecimal bdArray1x1[][] = { { new BigDecimal("1") } };
		BigDecimal bdArray1x2[][] = { { new BigDecimal("1"), new BigDecimal("2") } };
		BigDecimal bdArray2x1[][] = { { new BigDecimal("1") }, { new BigDecimal("3") } };
		BigDecimal bdArray2x2[][] = { { new BigDecimal("1"), new BigDecimal("2") }, { new BigDecimal("3"), new BigDecimal("4") } };

		this.m1x1 = new HashMapMatrixImpl(bdArray1x1);
		this.m1x2 = new HashMapMatrixImpl(bdArray1x2);
		this.m2x1 = new HashMapMatrixImpl(bdArray2x1);
		this.m2x2 = new HashMapMatrixImpl(bdArray2x2);
	}

	@Test
	public void testConcatRight2x2With2x1() {
		HashMapMatrixImpl concatedMatrix = (HashMapMatrixImpl) ConcatMatrix.concatRight(m2x2, m2x1);

		assertEquals(2, concatedMatrix.getLines());
		assertEquals(3, concatedMatrix.getColumns());

		String expectedMatrixString = "|1 2 1 |\n" + "|3 4 3 |";
		assertEquals(expectedMatrixString, concatedMatrix.toString());
	}
}
