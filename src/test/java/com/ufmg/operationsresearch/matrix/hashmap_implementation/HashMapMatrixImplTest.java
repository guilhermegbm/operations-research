package com.ufmg.operationsresearch.matrix.hashmap_implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HashMapMatrixImplTest {

	private HashMapMatrixImpl mEmpty;
	private HashMapMatrixImpl mOtherEmpty;
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

		BigDecimal bdArrayOtherEmpty[][] = {};//Zero lines
		this.mOtherEmpty = new HashMapMatrixImpl(bdArrayOtherEmpty);

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
	public void testConstructorWithLinesAndColumnsThrowsInvalidParameterOnZeroLinesAndColumns() {
		Exception eLineZero = assertThrows(InvalidParameterException.class, () -> {
			new HashMapMatrixImpl(0, 1);
		});
		assertEquals("Number of lines (0) must be greater than or equal to 1", eLineZero.getMessage());

		Exception eColumnZero = assertThrows(InvalidParameterException.class, () -> {
			new HashMapMatrixImpl(1, 0);
		});
		assertEquals("Number of columns (0) must be greater than or equal to 1", eColumnZero.getMessage());
	}

	@Test
	public void testConstructorWithLinesAndColumnsThrowsInvalidParameterOnNegativeLinesAndColumns() {
		Exception eLineNegative = assertThrows(InvalidParameterException.class, () -> {
			new HashMapMatrixImpl(-1, 2);
		});
		assertEquals("Number of lines (-1) must be greater than or equal to 1", eLineNegative.getMessage());

		Exception eColumnNegative = assertThrows(InvalidParameterException.class, () -> {
			new HashMapMatrixImpl(2, -2);
		});

		assertEquals("Number of columns (-2) must be greater than or equal to 1", eColumnNegative.getMessage());
	}

	@Test
	public void testConstructorWithArrayParamGeneratedOtherEmptyMatrix() {
		assertEquals(0, this.mOtherEmpty.getLines());
		assertEquals(0, this.mOtherEmpty.getColumns());

		String expectedMatrixString = "[]";

		assertEquals(expectedMatrixString, this.mOtherEmpty.toString());
	}

	@Test
	public void testConstructorWithLinesAndColumnsGenerated4x4ZeroesMatrix() {
		assertEquals(4, this.m4x4Zeroes.getLines());
		assertEquals(4, this.m4x4Zeroes.getColumns());

		String expectedMatrixString = "|0 0 0 0 |\n" + "|0 0 0 0 |\n" + "|0 0 0 0 |\n" + "|0 0 0 0 |";

		assertEquals(expectedMatrixString, this.m4x4Zeroes.toString());
	}

	@Test
	public void testConstructorWithArrayParamGeneratedOther4x4ZeroesMatrix() {
		assertEquals(4, this.mOther4x4Zeroes.getLines());
		assertEquals(4, this.mOther4x4Zeroes.getColumns());

		String expectedMatrixString = "|0 0 0 0 |\n" + "|0 0 0 0 |\n" + "|0 0 0 0 |\n" + "|0 0 0 0 |";

		assertEquals(expectedMatrixString, this.mOther4x4Zeroes.toString());
	}

	@Test
	public void testConstructorWithArrayParamGenerated4x4OnesMatrix() {
		assertEquals(4, this.m4x4Ones.getLines());
		assertEquals(4, this.m4x4Ones.getColumns());

		String expectedMatrixString = "|1 1 1 1 |\n" + "|1 1 1 1 |\n" + "|1 1 1 1 |\n" + "|1 1 1 1 |";

		assertEquals(expectedMatrixString, this.m4x4Ones.toString());
	}

	@Test
	public void testConstructorWithArrayParamGenerated1x1Matrix() {
		assertEquals(1, this.m1x1.getLines());
		assertEquals(1, this.m1x1.getColumns());

		String expectedMatrixString = "|1 |";

		assertEquals(expectedMatrixString, this.m1x1.toString());
	}

	@Test
	public void testConstructorWithArrayParamGenerated1x2Matrix() {
		assertEquals(1, this.m1x2.getLines());
		assertEquals(2, this.m1x2.getColumns());

		String expectedMatrixString = "|1 2 |";

		assertEquals(expectedMatrixString, this.m1x2.toString());
	}

	@Test
	public void testConstructorWithArrayParamGenerated2x1Matrix() {
		assertEquals(2, this.m2x1.getLines());
		assertEquals(1, this.m2x1.getColumns());

		String expectedMatrixString = "|1 |\n" + "|3 |";

		assertEquals(expectedMatrixString, this.m2x1.toString());
	}

	@Test
	public void testConstructorWithArrayParamGenerated2x2Matrix() {
		assertEquals(2, this.m2x2.getLines());
		assertEquals(2, this.m2x2.getColumns());

		String expectedMatrixString = "|1 2 |\n" + "|3 4 |";

		assertEquals(expectedMatrixString, this.m2x2.toString());
	}

	@Test
	public void testConstructorWithArrayParamThrowsExceptionOnIncongruentLineVectors() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			BigDecimal bdArrayWithIncongruentLines[][] = { { BigDecimal.ONE, BigDecimal.ONE }, { BigDecimal.ONE, BigDecimal.ONE }, { BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE } };
			new HashMapMatrixImpl(bdArrayWithIncongruentLines);
		});

		assertEquals("The matrix passed as parameter has line vectors with different sizes", e.getMessage());
	}

	@Test
	public void testConstructorWithArrayParamThrowsInvalidParameterOnNullArray() {
		Exception eNullArray = assertThrows(InvalidParameterException.class, () -> {
			BigDecimal nullArr[][] = null;

			new HashMapMatrixImpl(nullArr);
		});

		assertEquals("values can not be null", eNullArray.getMessage());
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

		assertEquals("Line 2 out of bounds (limits are <1, 1>)", e.getMessage());
	}

	@Test
	public void testNegativeLineGetValueRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			this.m1x1.getValue(-3, 1);
		});

		assertEquals("Line -3 out of bounds (limits are <1, 1>)", e.getMessage());
	}

	@Test
	public void testColumnOutOfBoundsGetValueRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			this.m1x1.getValue(1, 2);
		});

		assertEquals("Column 2 out of bounds (limits are <1, 1>)", e.getMessage());
	}

	@Test
	public void testNegativeColumnGetValueRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			this.m1x1.getValue(1, -2);
		});

		assertEquals("Column -2 out of bounds (limits are <1, 1>)", e.getMessage());
	}

	@Test
	public void testSetValueOn1x2() {
		//Before being modified
		String originalMatrixString = "|1 2 |";
		assertEquals(originalMatrixString, this.m1x2.toString());

		//After being modified
		this.m1x2.setValue(1, 2, new BigDecimal("5"));

		assertEquals(1, this.m1x2.getLines());
		assertEquals(2, this.m1x2.getColumns());
		String modifiedMatrixString = "|1 5 |";
		assertEquals(modifiedMatrixString, this.m1x2.toString());

		//After being modified again
		this.m1x2.setValue(1, 1, new BigDecimal("9"));

		assertEquals(1, this.m1x2.getLines());
		assertEquals(2, this.m1x2.getColumns());
		modifiedMatrixString = "|9 5 |";
		assertEquals(modifiedMatrixString, this.m1x2.toString());
	}

	@Test
	public void testSetNullValueRaisesInvalidParameterException() {
		Exception eNullValue = assertThrows(InvalidParameterException.class, () -> {
			this.m1x1.setValue(1, 1, null);
		});

		assertEquals("Value can not be null", eNullValue.getMessage());
	}

	@Test
	public void testLineOutOfBoundsSetValueRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			this.m1x1.setValue(2, 1, BigDecimal.ZERO);
		});

		assertEquals("Line 2 out of bounds (limits are <1, 1>)", e.getMessage());
	}

	@Test
	public void testNegativeLineSetValueRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			this.m1x1.setValue(-3, 1, BigDecimal.ZERO);
		});

		assertEquals("Line -3 out of bounds (limits are <1, 1>)", e.getMessage());
	}

	@Test
	public void testColumnOutOfBoundsSetValueRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			this.m1x1.setValue(1, 2, BigDecimal.ZERO);
		});

		assertEquals("Column 2 out of bounds (limits are <1, 1>)", e.getMessage());
	}

	@Test
	public void testNegativeColumnSetValueRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			this.m1x1.setValue(1, -2, BigDecimal.ZERO);
		});

		assertEquals("Column -2 out of bounds (limits are <1, 1>)", e.getMessage());
	}

	public void testIsEmptyReturnsTrueOnZeroLines() {
		assertTrue(this.mOtherEmpty.isEmpty());

	}

	public void testIsEmptyReturnsTrueOnZeroColumns() {
		BigDecimal bdArrayOtherEmpty[][] = {};//Zero lines
		HashMapMatrixImpl mat = new HashMapMatrixImpl(bdArrayOtherEmpty);
		mat.setNumberOfLines(1);

		assertTrue(this.mOtherEmpty.isEmpty());

	}

	@Test
	public void testMultiplyLineByScalar() {
		this.m1x1.multiplyLineByScalar(1, new BigDecimal("1"));
		String expectedMatrixString1x1 = "|1 |";
		assertEquals(expectedMatrixString1x1, this.m1x1.toString());

		this.m1x2.multiplyLineByScalar(1, new BigDecimal("2"));
		String expectedMatrixString1x2 = "|2 4 |";
		assertEquals(expectedMatrixString1x2, this.m1x2.toString());

		this.m2x1.multiplyLineByScalar(2, new BigDecimal("3"));
		String expectedMatrixString2x1 = "|1 |\n" + "|9 |";
		assertEquals(expectedMatrixString2x1, this.m2x1.toString());

		this.m2x2.multiplyLineByScalar(2, new BigDecimal("4"));
		String expectedMatrixString2x2 = "|1 2 |\n" + "|12 16 |";
		assertEquals(expectedMatrixString2x2, this.m2x2.toString());
	}

	@Test
	public void testMultiplyLineByScalarThrowsLineOutOfBounds() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			this.m2x2.multiplyLineByScalar(3, new BigDecimal("5"));
		});

		assertEquals("Line 3 out of bounds (limits are <1, 2>)", e.getMessage());
	}

	@Test
	public void testAddMultipliedLineToTargetLineOn1x1() {
		this.m1x1.addMultipliedLineToTargetLine(1, new BigDecimal("5"), 1); //L1 = L1 + 5*L1
		String expectedMatrixString1x1 = "|6 |";
		assertEquals(expectedMatrixString1x1, this.m1x1.toString());
	}

	@Test
	public void testAddMultipliedLineToTargetLineOn1x2() {
		this.m1x2.addMultipliedLineToTargetLine(1, new BigDecimal("3"), 1); //L1 = L1 + 3*L1 
		String expectedMatrixString1x2 = "|4 8 |";
		assertEquals(expectedMatrixString1x2, this.m1x2.toString());
	}

	@Test
	public void testAddMultipliedLineToTargetLineOn2x1() {
		this.m2x1.addMultipliedLineToTargetLine(2, new BigDecimal("-0.5"), 1); //L2 = L2 + (-0.5)*L1
		String expectedMatrixString2x1 = "|1 |\n" + "|2.5 |";
		assertEquals(expectedMatrixString2x1, this.m2x1.toString());
	}

	@Test
	public void testAddMultipliedLineToTargetLineOn2x2() {
		this.m2x2.addMultipliedLineToTargetLine(2, new BigDecimal("-4"), 1); //L2 = L2 + (-4)*L1
		String expectedMatrixString2x2 = "|1 2 |\n" + "|-1 -4 |";
		assertEquals(expectedMatrixString2x2, this.m2x2.toString());
	}

	@Test
	public void testAddMultipliedLineToTargetLineThrowsTargetLineOutOfBounds() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			this.m2x2.addMultipliedLineToTargetLine(3, new BigDecimal("-2"), 1);
		});

		assertEquals("Target Line 3 out of bounds (limits are <1, 2>)", e.getMessage());
	}

	@Test
	public void testAddMultipliedLineToTargetLineThrowsLineToBeMultipliedOutOfBounds() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			this.m2x2.addMultipliedLineToTargetLine(1, new BigDecimal("3"), 4);
		});

		assertEquals("Line to be multiplied 4 out of bounds (limits are <1, 2>)", e.getMessage());
	}

	@Test
	public void testDuplicate1x2Matrix() {
		//Duplicating 1x2
		HashMapMatrixImpl duplicatedMatrix = (HashMapMatrixImpl) this.m1x2.duplicate();

		//Checking if duplicated Matrix was correctly duplicated
		assertEquals(this.m1x2.getLines(), duplicatedMatrix.getLines());
		assertEquals(this.m1x2.getColumns(), duplicatedMatrix.getColumns());
		assertEquals(this.m1x2.toString(), duplicatedMatrix.toString());
	}

	@Test
	public void testAlterDuplicate1x2MatrixDoesNotAlterOriginalMatrix() {
		//Duplicating 1x2
		HashMapMatrixImpl duplicatedMatrix = (HashMapMatrixImpl) this.m1x2.duplicate();

		//Modifying duplicated Matrix
		duplicatedMatrix.setValue(1, 2, new BigDecimal("5"));

		//Checking if duplicated Matrix was correctly modified
		assertEquals(1, duplicatedMatrix.getLines());
		assertEquals(2, duplicatedMatrix.getColumns());
		String modifiedDuplicatedMatrixString = "|1 5 |";
		assertEquals(modifiedDuplicatedMatrixString, duplicatedMatrix.toString());

		//Checking if original 1x2 Matrix was NOT modified
		assertEquals(1, this.m1x2.getLines());
		assertEquals(2, this.m1x2.getColumns());
		String originalMatrixString = "|1 2 |";
		assertEquals(originalMatrixString, this.m1x2.toString());
	}

	@Test
	public void testSetNumberOfLines() {
		assertEquals(1, this.m1x1.getLines());

		this.m1x1.setNumberOfLines(5);

		assertEquals(5, this.m1x1.getLines());
	}

	@Test
	public void testSetNegativeNumberOfLinesRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			this.m1x1.setNumberOfLines(-3);
		});

		assertEquals("Number of lines (-3) must be greater than or equal to 1", e.getMessage());
	}

	@Test
	public void testSetNumberOfColumns() {
		assertEquals(1, this.m1x1.getColumns());

		this.m1x1.setNumberOfColumns(4);

		assertEquals(4, this.m1x1.getColumns());
	}

	@Test
	public void testSetNegativeNumberOfColumnsRaisesException() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			this.m1x1.setNumberOfColumns(-2);
		});

		assertEquals("Number of columns (-2) must be greater than or equal to 1", e.getMessage());
	}
}
