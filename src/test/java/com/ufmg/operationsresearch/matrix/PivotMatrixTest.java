package com.ufmg.operationsresearch.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ufmg.operationsresearch.matrix.hashmap_implementation.HashMapMatrixImpl;

public class PivotMatrixTest {

	private HashMapMatrixImpl m2x3;
	private HashMapMatrixImpl m3x2;

	@BeforeEach
	public void init() {
		BigDecimal bdArray2x3[][] = { { new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3") }, { new BigDecimal("4"), new BigDecimal("5"), new BigDecimal("6") } };
		BigDecimal bdArray3x2[][] = { { new BigDecimal("1"), new BigDecimal("2") }, { new BigDecimal("3"), new BigDecimal("4") }, { new BigDecimal("5"), new BigDecimal("6") } };

		this.m2x3 = new HashMapMatrixImpl(bdArray2x3);
		this.m3x2 = new HashMapMatrixImpl(bdArray3x2);
	}

	@Test
	public void testSimplePivotOn2x3() {
		PivotMatrix.pivotCoordinate(this.m2x3, 1, 1);

		assertEquals(2, this.m2x3.getLines());
		assertEquals(3, this.m2x3.getColumns());

		String expectedMatrixString = "|1 2 3 |\n" + "|0 -3 -6 |";

		assertEquals(expectedMatrixString, this.m2x3.toString());
	}

	@Test
	public void testSimplePivotOn3x2() {
		PivotMatrix.pivotCoordinate(this.m3x2, 2, 2);
		System.out.println(this.m3x2.toString());

		assertEquals(3, this.m3x2.getLines());
		assertEquals(2, this.m3x2.getColumns());

		String expectedMatrixString = "|-0.5 0 |\n" + "|0.75 1 |\n" + "|0.5 0 |";

		assertEquals(expectedMatrixString, this.m3x2.toString());
	}

	@Test
	public void testDoublePivotOn2x3() {
		PivotMatrix.pivotCoordinate(this.m2x3, 1, 1);

		PivotMatrix.pivotCoordinate(this.m2x3, 2, 2);

		assertEquals(2, this.m2x3.getLines());
		assertEquals(3, this.m2x3.getColumns());

		String expectedMatrixString = "|1 0 -1 |\n" + "|0 1 2 |";

		assertEquals(expectedMatrixString, this.m2x3.toString());
	}

	@Test
	public void testLineOutOfBoundsPivotMatrixRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			PivotMatrix.pivotCoordinate(m2x3, 3, 2);
		});

		assertEquals("Line 3 out of bounds (limits are <1, 2>)", e.getMessage());
	}

	@Test
	public void testNegativeLinePivotMatrixRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			PivotMatrix.pivotCoordinate(m2x3, -3, 1);
		});

		assertEquals("Line -3 out of bounds (limits are <1, 2>)", e.getMessage());
	}

	@Test
	public void testColumnOutOfBoundsPivotMatrixRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			PivotMatrix.pivotCoordinate(m2x3, 2, 4);
		});

		assertEquals("Column 4 out of bounds (limits are <1, 3>)", e.getMessage());
	}

	@Test
	public void testNegativeColumnPivotMatrixRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			PivotMatrix.pivotCoordinate(m2x3, 2, -2);
		});

		assertEquals("Column -2 out of bounds (limits are <1, 3>)", e.getMessage());
	}
}
