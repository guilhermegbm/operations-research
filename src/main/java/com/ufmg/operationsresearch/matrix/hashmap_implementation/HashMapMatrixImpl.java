package com.ufmg.operationsresearch.matrix.hashmap_implementation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.text.DecimalFormat;
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

	@Override
	public void checkIfLineAndColumnExists(Integer line, Integer column) {
		this.checkIfLineExists(line);
		this.checkIfColumnExists(column);
	}

	@Override
	public void checkIfLineExists(Integer line) {
		if (line > this.lines || line < 1) {
			throw new InvalidParameterException("Line " + line + " out of bounds (limits are <1, " + this.lines + ">)");
		}
	}

	@Override
	public void checkIfColumnExists(Integer column) {
		if (column > this.columns || column < 1) {
			throw new InvalidParameterException("Column " + column + " out of bounds (limits are <1, " + this.columns + ">)");
		}
	}

	@Override
	public BigDecimal getValue(Integer line, Integer column) {
		this.checkIfLineAndColumnExists(line, column);

		return this.values.get(new Coordinate(line, column));
	}

	@Override
	public void setValue(Integer line, Integer column, BigDecimal value) {
		this.checkIfLineAndColumnExists(line, column);

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
				sb.append(this.treatBigDecimalForPrint(this.getValue(l, c))).append(" ");
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

	private String treatBigDecimalForPrint(BigDecimal val) {
		//TODO improve efficiency URGENTLY
		DecimalFormat df = new DecimalFormat("0.#######");
		df.setRoundingMode(RoundingMode.HALF_UP);

		String bdAsStr = df.format(val);
		bdAsStr = bdAsStr.replace(",", ".");
		if ("-0".equals(bdAsStr)) {
			bdAsStr = "0";
		}
		return bdAsStr;
	}

	/*public void testBigDecimal() {
		DecimalFormat df = new DecimalFormat("0.#######");
		df.setRoundingMode(RoundingMode.HALF_UP);
	
		BigDecimal b1 = new BigDecimal("1.149026").setScale(4, RoundingMode.HALF_UP);
		System.out.println(b1);
		System.out.println(df.format(b1).replace(",", "."));
	
		BigDecimal b2 = new BigDecimal("1.0000000000").setScale(4, RoundingMode.HALF_UP);
		System.out.println(b2);
		System.out.println(df.format(b2).replace(",", "."));
	
		BigDecimal b3 = new BigDecimal("1.00").setScale(4, RoundingMode.HALF_UP);
		System.out.println(b3);
		System.out.println(df.format(b3).replace(",", "."));
	
		BigDecimal b4 = new BigDecimal("1.99999999").setScale(8, RoundingMode.HALF_UP);
		System.out.println(b4);
		System.out.println(df.format(b4).replace(",", "."));
	
		BigDecimal b5 = new BigDecimal("1.99999995").setScale(8, RoundingMode.HALF_UP);
		System.out.println(b5);
		System.out.println(df.format(b5).replace(",", "."));
	
		BigDecimal b6 = new BigDecimal("1.99999994").setScale(8, RoundingMode.HALF_UP);
		System.out.println(b6);
		System.out.println(df.format(b6).replace(",", "."));
	
		df.setRoundingMode(RoundingMode.FLOOR);
		BigDecimal b7 = new BigDecimal("1.99999999").setScale(8, RoundingMode.HALF_UP);
		System.out.println(b7);
		System.out.println(df.format(b7).replace(",", "."));
	
		df.setRoundingMode(RoundingMode.HALF_UP);
		BigDecimal b8 = new BigDecimal("1").setScale(8, RoundingMode.HALF_UP);
		System.out.println(b8);
		System.out.println(df.format(b8).replace(",", "."));
	
		BigDecimal b9 = new BigDecimal("2").setScale(8, RoundingMode.HALF_UP);
		System.out.println(b9);
		System.out.println(df.format(b9).replace(",", "."));
	
		BigDecimal b10 = new BigDecimal("23").setScale(8, RoundingMode.HALF_UP);
		System.out.println(b10);
		System.out.println(df.format(b10).replace(",", "."));
	
		BigDecimal b11 = new BigDecimal("-5").setScale(8, RoundingMode.HALF_UP);
		System.out.println(b11);
		System.out.println(df.format(b11).replace(",", "."));
	
		BigDecimal b12 = new BigDecimal("2.7").setScale(8, RoundingMode.HALF_UP);
		System.out.println(b12);
		System.out.println(df.format(b12).replace(",", "."));
	
		BigDecimal b13 = new BigDecimal("-0.99999999").setScale(8, RoundingMode.HALF_UP);
		System.out.println(b13);
		System.out.println(df.format(b13).replace(",", "."));
	
		BigDecimal b14 = new BigDecimal("-0.275").setScale(8, RoundingMode.HALF_UP);
		System.out.println(b14);
		System.out.println(df.format(b14).replace(",", "."));
	}*/

	@Override
	public void multiplyLineByScalar(Integer line, BigDecimal scalar) {
		this.checkIfLineExists(line);

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
