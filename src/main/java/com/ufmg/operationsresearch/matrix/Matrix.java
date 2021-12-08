package com.ufmg.operationsresearch.matrix;

import java.math.BigDecimal;

public interface Matrix {

	Integer getLines();

	Integer getColumns();

	BigDecimal getValue(Integer line, Integer column);

	void setValue(Integer line, Integer column, BigDecimal value);

	String toString();

	boolean isEmpty();

	/**
	 * Alters {@code line} to be: line = line * scalar 
	 */
	void multiplyLineByScalar(Integer line, BigDecimal scalar);

	/**
	 * Alters {@code targetLine} to be: targetLine = targetLine + (scalar * lineToBeMultiplied).
	 * This method does not alter {@code lineToBeMultiplied}
	 */
	void addMultipliedLineToTargetLine(Integer targetLine, BigDecimal scalar, Integer lineToBeMultiplied);

	Matrix duplicate();

	void setNumberOfLines(Integer newNumberOfLines);

	void setNumberOfColumns(Integer newNumberOfColumns);
}
