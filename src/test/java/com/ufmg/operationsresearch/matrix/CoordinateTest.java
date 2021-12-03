package com.ufmg.operationsresearch.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoordinateTest {
	Coordinate c11;
	Coordinate c12;
	Coordinate c21;
	Coordinate c22;

	@BeforeEach
	public void init() {
		this.c11 = new Coordinate(1, 1);
		this.c12 = new Coordinate(1, 2);
		this.c21 = new Coordinate(2, 1);
		this.c22 = new Coordinate(2, 2);
	}

	@Test
	public void testConstructorBuildsCoordinatesSucessfully() {

		assertNotNull(c11);

		assertNotNull(c12);

		assertNotNull(c21);

		assertNotNull(c22);
	}

	@Test
	public void testConstructorThrowsExceptionNullLine() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			new Coordinate(null, 10);
		});

		assertEquals("The line value can not be null", e.getMessage());
	}

	@Test
	public void testConstructorThrowsExceptionNullColumn() {
		Exception e = assertThrows(InvalidParameterException.class, () -> {
			new Coordinate(12, null);
		});

		assertEquals("The column value can not be null", e.getMessage());
	}

	@Test
	public void testConstructorThrowsExceptionLineSmallerThanOne() {
		String expectedMessage = "The line value must be greater than or equal to 1";

		Exception e1 = assertThrows(InvalidParameterException.class, () -> {
			new Coordinate(0, 10);
		});

		assertEquals(expectedMessage, e1.getMessage());

		Exception e2 = assertThrows(InvalidParameterException.class, () -> {
			new Coordinate(-5, 4);
		});

		assertEquals(expectedMessage, e2.getMessage());
	}

	@Test
	public void testConstructorThrowsExceptionColumnSmallerThanOne() {
		String expectedMessage = "The column value must be greater than or equal to 1";

		Exception e1 = assertThrows(InvalidParameterException.class, () -> {
			new Coordinate(1, 0);
		});

		assertEquals(expectedMessage, e1.getMessage());

		Exception e2 = assertThrows(InvalidParameterException.class, () -> {
			new Coordinate(2, -1);
		});

		assertEquals(expectedMessage, e2.getMessage());
	}

	@Test
	public void testGetLineReturnsCorrectValue() {
		assertEquals(1, c11.getLine());
		assertEquals(1, c12.getLine());
		assertEquals(2, c21.getLine());
		assertEquals(2, c22.getLine());
	}

	@Test
	public void testGetColumnReturnsCorrectValue() {
		assertEquals(1, c11.getColumn());
		assertEquals(2, c12.getColumn());
		assertEquals(1, c21.getColumn());
		assertEquals(2, c22.getColumn());
	}

	@Test
	public void testEqualCoordinates() {
		assertTrue(this.c11.equals(this.c11));

		assertTrue(this.c11.equals(new Coordinate(1, 1)));
		assertTrue(this.c12.equals(new Coordinate(1, 2)));
		assertTrue(this.c21.equals(new Coordinate(2, 1)));
		assertTrue(this.c22.equals(new Coordinate(2, 2)));
	}

	@Test
	public void testNotEqualCoordinates() {
		assertFalse(this.c11.equals(null));
		assertFalse(this.c11.equals(new Object()));
		assertFalse(this.c11.equals(new Coordinate(1, 2)));
		assertFalse(this.c11.equals(new Coordinate(2, 1)));
	}
}
