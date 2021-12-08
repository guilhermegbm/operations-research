package com.ufmg.operationsresearch.matrix.hashmap_implementation;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map.Entry;

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

	public HashMapMatrixImpl(HashMapMatrixImpl otherMatrix) {
		this.lines = otherMatrix.lines;
		this.columns = otherMatrix.columns;

		this.values = new HashMap<Coordinate, BigDecimal>();
		for (Entry<Coordinate, BigDecimal> entry : otherMatrix.values.entrySet()) {
			this.values.put(entry.getKey(), entry.getValue());
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

	private void validateLineAndColumn(Integer line, Integer column) {
		this.validateLine(line);
		this.validateColumn(column);
	}

	private void validateLine(Integer line) {
		if (line > this.lines || line < 1) {
			throw new InvalidParameterException("Line " + line + " out of bounds (limits are <1, " + this.lines + ">)");
		}
	}

	private void validateColumn(Integer column) {
		if (column > this.columns || column < 1) {
			throw new InvalidParameterException("Column " + column + " out of bounds (limits are <1, " + this.columns + ">)");
		}
	}

	@Override
	public BigDecimal getValue(Integer line, Integer column) {
		this.validateLineAndColumn(line, column);

		return this.values.get(new Coordinate(line, column));
	}

	@Override
	public void setValue(Integer line, Integer column, BigDecimal value) {
		this.validateLineAndColumn(line, column);

		if (value == null) {
			throw new InvalidParameterException("Value can not be null");
		}

		this.values.put(new Coordinate(line, column), value);
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

	@Override
	public void multiplyLineByScalar(Integer line, BigDecimal scalar) {
		this.validateLine(line);

		for (int c = 1; c <= this.columns; c++) {
			Coordinate coord = new Coordinate(line, c);
			this.values.put(coord, this.values.get(coord).multiply(scalar));
		}
	}

	@Override
	public void addMultipliedLineToTargetLine(Integer targetLine, BigDecimal scalar, Integer lineToBeMultiplied) {
		//TODO: Use "this.validateLineAndColumn(line, column);"?

		if (targetLine > this.lines || targetLine < 1) {
			throw new InvalidParameterException("Target Line " + targetLine + " out of bounds (limits are <1, " + this.lines + ">)");
		}

		if (lineToBeMultiplied > this.lines || lineToBeMultiplied < 1) {
			throw new InvalidParameterException("Line to be multiplied " + lineToBeMultiplied + " out of bounds (limits are <1, " + this.lines + ">)");
		}

		for (int c = 1; c <= this.columns; c++) {
			Coordinate targetCoord = new Coordinate(targetLine, c);
			Coordinate originCoord = new Coordinate(lineToBeMultiplied, c);

			BigDecimal valueToBeMultiplied = this.values.get(originCoord);
			BigDecimal multipliedValue = valueToBeMultiplied.multiply(scalar);

			BigDecimal originalTargetValue = this.values.get(targetCoord);
			BigDecimal addedTargetValue = originalTargetValue.add(multipliedValue);

			this.values.put(targetCoord, addedTargetValue);
		}
	}

	@Override
	public Matrix duplicate() {
		Matrix newMatrix = new HashMapMatrixImpl(this);
		return newMatrix;
	}

	@Override
	public void setNumberOfLines(Integer newNumberOfLines) {
		if (lines < 1) {
			throw new InvalidParameterException("Number of lines (" + newNumberOfLines + ") must be greater than or equal to 1");
		}
		this.lines = newNumberOfLines;
	}

	@Override
	public void setNumberOfColumns(Integer newNumberOfColumns) {
		if (columns < 1) {
			throw new InvalidParameterException("Number of columns (" + newNumberOfColumns + ") must be greater than or equal to 1");
		}
		this.columns = newNumberOfColumns;
	}

	public void printInfo() {
		System.out.println("Number of lines: " + this.getLines());
		System.out.println("Number of columns: " + this.getColumns());

		for (Entry<Coordinate, BigDecimal> entry : this.values.entrySet()) {
			System.out.println("[" + entry.getKey().getLine() + ", " + entry.getKey().getColumn() + "] = " + entry.getValue());
		}
	}
}
