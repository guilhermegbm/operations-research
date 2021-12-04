package com.ufmg.operationsresearch.matrix;

import java.math.BigDecimal;

public interface Matrix {

	BigDecimal getValue(Coordinate coord);

	BigDecimal getValue(Integer line, Integer column);

	void setValue(Coordinate coord, BigDecimal value);

	void setValue(Integer line, Integer column, BigDecimal value);

	String toString();

	void concatRight(Matrix other);

	void concatUp(Matrix other);

	void concatLeft(Matrix other);

	void concatDown(Matrix other);
	
	boolean isEmpty();

	/**
	 * Alters {@code line} to be: line = line * scalar 
	 */
	void multiplyLineByScalar(Integer line, BigDecimal scalar);

	/**
	 * Alters {@code targetLine} to be: targetLine = targetLine + (scalar * lineToBeMultiplied).
	 * This method does not alter {@code lineToBeMultiplied}
	 */
	void addMultipliedLineToTargetLine(Integer lineToBeMultiplied, BigDecimal scalar, Integer targetLine);

}
