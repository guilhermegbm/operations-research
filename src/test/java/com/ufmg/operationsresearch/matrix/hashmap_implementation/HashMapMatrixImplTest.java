package com.ufmg.operationsresearch.matrix.hashmap_implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ufmg.operationsresearch.matrix.Coordinate;

public class HashMapMatrixImplTest {

	private HashMapMatrixImpl mEmpty;
	private HashMapMatrixImpl mOtherEmpty;
	private HashMapMatrixImpl mAnotherEmpty;
	private HashMapMatrixImpl m4x4Zeroes;
	private HashMapMatrixImpl mOther4x4Zeroes;
	private HashMapMatrixImpl m4x4Ones;
	private HashMapMatrixImpl m1x1;
	private HashMapMatrixImpl m1x2;
	private HashMapMatrixImpl m2x1;
	private HashMapMatrixImpl m2x2;

	@BeforeEach
	public void init() {

		this.mEmpty = new HashMapMatrixImpl();
		this.mOtherEmpty = new HashMapMatrixImpl(0, 0);

		BigDecimal bdArrayOtherEmpty[][] = {};//Zero lines
		this.mAnotherEmpty = new HashMapMatrixImpl(bdArrayOtherEmpty);

		this.m4x4Zeroes = new HashMapMatrixImpl(4, 4);

		BigDecimal bdArray4x4Zeroes[][] = { { BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO }, { BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO }, { BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO },
				{ BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO } };

		this.mOther4x4Zeroes = new HashMapMatrixImpl(bdArray4x4Zeroes);

		BigDecimal bdArray4x4Ones[][] = { { BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE }, { BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE }, { BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE },
				{ BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE } };

		this.m4x4Ones = new HashMapMatrixImpl(bdArray4x4Ones);

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
	public void testConstructorWithNoParamsGeneratedEmptyMatrix() {
		assertEquals(0, this.mEmpty.getLines());
		assertEquals(0, this.mEmpty.getColumns());

		String expectedMatrixString = "[]";

		assertEquals(expectedMatrixString, this.mEmpty.toString());
	}

	@Test
	public void testConstructorWithLinesAndColumnsGeneratedOtherEmptyMatrix() {
		assertEquals(0, this.mOtherEmpty.getLines());
		assertEquals(0, this.mOtherEmpty.getColumns());

		String expectedMatrixString = "[]";

		assertEquals(expectedMatrixString, this.mOtherEmpty.toString());
	}

	@Test
	public void testConstructorWithArrayParamGeneratedAnotherEmptyMatrix() {
		assertEquals(0, this.mAnotherEmpty.getLines());
		assertEquals(0, this.mAnotherEmpty.getColumns());

		String expectedMatrixString = "[]";

		assertEquals(expectedMatrixString, this.mAnotherEmpty.toString());
	}

	@Test
	public void testConstructorWithLinesAndColumnsGenerated4x4ZeroesMatrix() {
		assertEquals(4, this.m4x4Zeroes.getLines());
		assertEquals(4, this.m4x4Zeroes.getColumns());

		//System.out.println(this.m4x4Zeroes.toString());

		String expectedMatrixString = "|0 0 0 0 |\r\n" + "|0 0 0 0 |\r\n" + "|0 0 0 0 |\r\n" + "|0 0 0 0 |";

		//assertTrue(expectedMatrixString.equals(this.m4x4Zeroes.toString()));
	}

	@Test
	public void testConstructorWithArrayParamGenerated4x4ZeroesMatrix() {
		assertEquals(4, this.mOther4x4Zeroes.getLines());
		assertEquals(4, this.mOther4x4Zeroes.getColumns());

		//System.out.println(this.mOther4x4Zeroes.toString());

		String expectedMatrixString = "|0 0 0 0 |\r\n" + "|0 0 0 0 |\r\n" + "|0 0 0 0 |\r\n" + "|0 0 0 0 |";

		//assertTrue(expectedMatrixString.equals(this.mOther4x4Zeroes.toString()));
	}

	@Test
	public void testConstructorWithArrayParamGenerated4x4OnesMatrix() {
		assertEquals(4, this.m4x4Ones.getLines());
		assertEquals(4, this.m4x4Ones.getColumns());

		//System.out.println(this.m4x4Ones.toString());

		String expectedMatrixString = "|1 1 1 1 |\r\n" + "|1 1 1 1 |\r\n" + "|1 1 1 1 |\r\n" + "|1 1 1 1 |";

		//assertTrue(expectedMatrixString.equals(this.m4x4Ones.toString()));
	}

	@Test
	public void testConstructorWithArrayParamGenerated1x1Matrix() {
		assertEquals(1, this.m1x1.getLines());
		assertEquals(1, this.m1x1.getColumns());

		//System.out.println(this.m1x1.toString());

		String expectedMatrixString = "|1 |";

		assertTrue(expectedMatrixString.equals(this.m1x1.toString()));
	}

	@Test
	public void testConstructorWithArrayParamGenerated1x2Matrix() {
		assertEquals(1, this.m1x2.getLines());
		assertEquals(2, this.m1x2.getColumns());

		//System.out.println(this.m1x2.toString());

		String expectedMatrixString = "|1 |";

		//assertTrue(expectedMatrixString.equals(this.m1x2.toString()));
	}

	@Test
	public void testConstructorWithArrayParamGenerated2x1Matrix() {
		assertEquals(2, this.m2x1.getLines());
		assertEquals(1, this.m2x1.getColumns());

		//System.out.println(this.m2x1.toString());

		String expectedMatrixString = "|1 |";

		//assertTrue(expectedMatrixString.equals(this.m2x1.toString()));
	}

	@Test
	public void testConstructorWithArrayParamGenerated2x2Matrix() {
		assertEquals(2, this.m2x2.getLines());
		assertEquals(2, this.m2x2.getColumns());

		//System.out.println(this.m2x2.toString());

		String expectedMatrixString = "|1 2 |\r\n" + "|3 4 |";

		//assertTrue(expectedMatrixString.equals(this.m2x2.toString()));
	}

	@Test
	public void testGetValueOn1x1() {
		assertEquals(0, new BigDecimal("1").compareTo(this.m1x1.getValue(1, 1)));
	}

	@Test
	public void testGetValueOn1x2() {
		assertEquals(0, new BigDecimal("2").compareTo(this.m1x2.getValue(1, 2)));
	}

	@Test
	public void testGetValueOn2x1() {
		assertEquals(0, new BigDecimal("3").compareTo(this.m2x1.getValue(2, 1)));
	}

	@Test
	public void testGetValueOn2x2() {
		assertEquals(0, new BigDecimal("4").compareTo(this.m2x2.getValue(2, 2)));
	}

	@Test
	public void testLineOutOfBoundsGetValueRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			this.m1x1.getValue(2, 1);
		});

		assertEquals("Line out of bounds", e.getMessage());
	}

	@Test
	public void testColumnOutOfBoundsGetValueRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			this.m1x1.getValue(1, 2);
		});

		assertEquals("Column out of bounds", e.getMessage());
	}
}
