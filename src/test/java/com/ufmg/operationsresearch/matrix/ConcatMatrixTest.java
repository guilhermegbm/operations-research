package com.ufmg.operationsresearch.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ufmg.operationsresearch.matrix.hashmap_implementation.HashMapMatrixImpl;

public class ConcatMatrixTest {

	private HashMapMatrixImpl m1x1;
	private HashMapMatrixImpl m1x2;
	private HashMapMatrixImpl m2x1;
	private HashMapMatrixImpl m2x2;
	private HashMapMatrixImpl m2x3;
	private HashMapMatrixImpl m3x2;

	@BeforeEach
	public void init() {
		BigDecimal bdArray1x1[][] = { { new BigDecimal("1") } };
		BigDecimal bdArray1x2[][] = { { new BigDecimal("1"), new BigDecimal("2") } };
		BigDecimal bdArray2x1[][] = { { new BigDecimal("1") }, { new BigDecimal("3") } };
		BigDecimal bdArray2x2[][] = { { new BigDecimal("1"), new BigDecimal("2") }, { new BigDecimal("3"), new BigDecimal("4") } };
		BigDecimal bdArray2x3[][] = { { new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3") }, { new BigDecimal("4"), new BigDecimal("5"), new BigDecimal("6") } };
		BigDecimal bdArray3x2[][] = { { new BigDecimal("1"), new BigDecimal("2") }, { new BigDecimal("3"), new BigDecimal("4") }, { new BigDecimal("5"), new BigDecimal("6") } };

		this.m1x1 = new HashMapMatrixImpl(bdArray1x1);
		this.m1x2 = new HashMapMatrixImpl(bdArray1x2);
		this.m2x1 = new HashMapMatrixImpl(bdArray2x1);
		this.m2x2 = new HashMapMatrixImpl(bdArray2x2);
		this.m2x3 = new HashMapMatrixImpl(bdArray2x3);
		this.m3x2 = new HashMapMatrixImpl(bdArray3x2);
	}

	@Test
	public void testConcatRight2x2With2x1() {
		HashMapMatrixImpl concatedMatrix = (HashMapMatrixImpl) ConcatMatrix.concatRight(m2x2, m2x1);

		assertEquals(2, concatedMatrix.getLines());
		assertEquals(3, concatedMatrix.getColumns());

		String expectedMatrixString = "|1 2 1 |\n" + "|3 4 3 |";
		assertEquals(expectedMatrixString, concatedMatrix.toString());
	}

	@Test
	public void testConcatRight2x3With2x2() {
		HashMapMatrixImpl concatedMatrix = (HashMapMatrixImpl) ConcatMatrix.concatRight(m2x3, m2x2);

		assertEquals(2, concatedMatrix.getLines());
		assertEquals(5, concatedMatrix.getColumns());

		String expectedMatrixString = "|1 2 3 1 2 |\n" + "|4 5 6 3 4 |";
		assertEquals(expectedMatrixString, concatedMatrix.toString());
	}

	@Test
	public void testConcatRight2x2With1x1RaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			ConcatMatrix.concatRight(m2x2, m1x1);
		});

		assertEquals("Number of lines on m1 (2) is different from the number of lines on m2 (1)", e.getMessage());
	}

	@Test
	public void testConcatDown2x2With1x2() {
		HashMapMatrixImpl concatedMatrix = (HashMapMatrixImpl) ConcatMatrix.concatDown(m2x2, m1x2);

		assertEquals(3, concatedMatrix.getLines());
		assertEquals(2, concatedMatrix.getColumns());

		String expectedMatrixString = "|1 2 |\n" + "|3 4 |\n" + "|1 2 |";
		assertEquals(expectedMatrixString, concatedMatrix.toString());
	}

	@Test
	public void testConcatDown3x2With2x2() {
		HashMapMatrixImpl concatedMatrix = (HashMapMatrixImpl) ConcatMatrix.concatDown(m3x2, m2x2);

		assertEquals(5, concatedMatrix.getLines());
		assertEquals(2, concatedMatrix.getColumns());

		String expectedMatrixString = "|1 2 |\n" + "|3 4 |\n" + "|5 6 |\n" + "|1 2 |\n" + "|3 4 |";
		assertEquals(expectedMatrixString, concatedMatrix.toString());
	}

	@Test
	public void testConcatDown2x2With1x1RaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			ConcatMatrix.concatDown(m2x2, m1x1);
		});

		assertEquals("Number of columns on m1 (2) is different from the number of columns on m2 (1)", e.getMessage());
	}

	@Test
	public void testConcatLeft2x2With2x1() {
		HashMapMatrixImpl concatedMatrix = (HashMapMatrixImpl) ConcatMatrix.concatLeft(m2x2, m2x1);

		assertEquals(2, concatedMatrix.getLines());
		assertEquals(3, concatedMatrix.getColumns());

		String expectedMatrixString = "|1 1 2 |\n" + "|3 3 4 |";
		assertEquals(expectedMatrixString, concatedMatrix.toString());
	}

	@Test
	public void testConcatLeft2x3With2x2() {
		HashMapMatrixImpl concatedMatrix = (HashMapMatrixImpl) ConcatMatrix.concatLeft(m2x3, m2x2);

		assertEquals(2, concatedMatrix.getLines());
		assertEquals(5, concatedMatrix.getColumns());

		String expectedMatrixString = "|1 2 1 2 3 |\n" + "|3 4 4 5 6 |";
		assertEquals(expectedMatrixString, concatedMatrix.toString());
	}

	@Test
	public void testConcatLeft2x2With1x1RaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			ConcatMatrix.concatLeft(m2x2, m1x1);
		});

		assertEquals("Number of lines on m1 (1) is different from the number of lines on m2 (2)", e.getMessage());
	}

	@Test
	public void testConcatUp2x2With1x2() {
		HashMapMatrixImpl concatedMatrix = (HashMapMatrixImpl) ConcatMatrix.concatUp(m2x2, m1x2);

		assertEquals(3, concatedMatrix.getLines());
		assertEquals(2, concatedMatrix.getColumns());

		String expectedMatrixString = "|1 2 |\n" + "|1 2 |\n" + "|3 4 |";
		assertEquals(expectedMatrixString, concatedMatrix.toString());
	}

	@Test
	public void testConcatUp3x2With2x2() {
		HashMapMatrixImpl concatedMatrix = (HashMapMatrixImpl) ConcatMatrix.concatUp(m3x2, m2x2);

		assertEquals(5, concatedMatrix.getLines());
		assertEquals(2, concatedMatrix.getColumns());

		String expectedMatrixString = "|1 2 |\n" + "|3 4 |\n" + "|1 2 |\n" + "|3 4 |\n" + "|5 6 |";
		assertEquals(expectedMatrixString, concatedMatrix.toString());
	}

	@Test
	public void testConcatUp2x2With1x1RaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			ConcatMatrix.concatUp(m2x2, m1x1);
		});

		assertEquals("Number of columns on m1 (1) is different from the number of columns on m2 (2)", e.getMessage());
	}
}
