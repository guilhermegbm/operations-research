package com.ufmg.operationsresearch.matrix.hashmap_implementation;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.HashMap;

import com.ufmg.operationsresearch.matrix.Coordinate;
import com.ufmg.operationsresearch.matrix.Matrix;

public final class HashMapMatrixImpl implements Matrix {

	//The number of lines in this matrix
	private Integer lines;

	//The number of columns in this matrix
	private Integer columns;

	/** The Hashmap that will hold all the values of the matrix. If the matrix has l 'lines' and 'c' columns,
	*   this Hashmap should hold (l * c) <Coordinate, BigDecimal> entries
	*/
	private HashMap<Coordinate, BigDecimal> values;

	public HashMapMatrixImpl() {
		this.lines = 0;
		this.columns = 0;
		this.values = new HashMap<Coordinate, BigDecimal>();
	}

	/**
	 * Initializes the matrix with {@code lines * columns} entries with 0 value
	 * @param lines
	 * @param columns
	 */
	public HashMapMatrixImpl(Integer lines, Integer columns) {
		if (lines < 1) {
			throw new InvalidParameterException("Number of lines (" + lines + ") must be greater than or equal to 1");
		}

		if (columns < 1) {
			throw new InvalidParameterException("Number of columns (" + columns + ") must be greater than or equal to 1");
		}

		this.lines = lines;
		this.columns = columns;
		this.values = new HashMap<Coordinate, BigDecimal>();

		for (int l = 1; l <= lines; l++) {
			for (int c = 1; c <= columns; c++) {
				this.values.put(new Coordinate(l, c), BigDecimal.ZERO);
			}
		}
	}

	/**
	 * Initializes the matrix with the {@code values} param. The matrix will have the same size as the {@code values} param
	 * @param values
	 */
	public HashMapMatrixImpl(BigDecimal[][] values) {
		if (values == null) {
			throw new InvalidParameterException("values can not be null");
		}

		int numberOfLines = values.length;

		if (numberOfLines == 0) {
			this.lines = 0;
			this.columns = 0;
			this.values = new HashMap<Coordinate, BigDecimal>();

			return;
		}

		int numberOfColumns = values[0].length;

		this.lines = numberOfLines;
		this.columns = numberOfColumns;
		this.values = new HashMap<Coordinate, BigDecimal>();

		for (int l = 1; l <= numberOfLines; l++) {
			if (values[l - 1].length != numberOfColumns) {
				throw new InvalidParameterException("The matrix passed as parameter has line vectors with different sizes");
			}

			for (int c = 1; c <= numberOfColumns; c++) {
				this.values.put(new Coordinate(l, c), values[l - 1][c - 1]);
			}
		}
	}

	@Override
	public Integer getLines() {
		return lines;
	}

	@Override
	public Integer getColumns() {
		return columns;
	}

	@Override
	public BigDecimal getValue(Integer line, Integer column) {
		if (line > this.lines) {
			throw new InvalidParameterException("Line out of bounds");
		}

		if (column > this.columns) {
			throw new InvalidParameterException("Column out of bounds");
		}

		return this.values.get(new Coordinate(line, column));
	}

	@Override
	public void setValue(Integer line, Integer column, BigDecimal value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void concatRight(Matrix other) {
		// TODO Auto-generated method stub

	}

	@Override
	public void concatUp(Matrix other) {
		// TODO Auto-generated method stub

	}

	@Override
	public void concatLeft(Matrix other) {
		// TODO Auto-generated method stub

	}

	@Override
	public void concatDown(Matrix other) {
		// TODO Auto-generated method stub

	}

	@Override
	public void multiplyLineByScalar(Integer line, BigDecimal scalar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMultipliedLineToTargetLine(Integer lineToBeMultiplied, BigDecimal scalar, Integer targetLine) {
		// TODO Auto-generated method stub

	}

	public boolean isEmpty() {
		return (this.lines == 0 && this.columns == 0);
	}

	public String toString() {
		if (this.isEmpty()) {
			return "[]";
		}

		StringBuffer sb = new StringBuffer();

		for (int l = 1; l <= this.lines; l++) {
			/*if (l == 1) {
				sb.append("⌈");
			} else if (l == this.lines) {
				sb.append("⌊");
			} else {
				sb.append("|");
			}*/
			sb.append("|");
			for (int c = 1; c <= this.columns; c++) {
				BigDecimal val = this.getValue(l, c);

				sb.append(val.toString()).append(" ");

			}

			if (l == this.lines) {
				sb.append("|");
			} else {
				sb.append("|\n");
			}

			//TODO: Check if there is a character better than "⌉" and "⌋"
			/*if (l == 1) {
				sb.append("⌉\n");
			} else if (l == this.lines) {
				sb.append("⌋\n");
			} else {
				sb.append("|");
			}*/
		}

		return sb.toString();
	}

}
