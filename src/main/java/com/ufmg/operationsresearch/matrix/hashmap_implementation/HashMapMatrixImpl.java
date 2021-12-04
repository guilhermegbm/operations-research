package com.ufmg.operationsresearch.matrix.hashmap_implementation;

import java.math.BigDecimal;
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

	/**
	 * Initializes the matrix with {@code lines * columns} entries with 0 value
	 * @param lines
	 * @param columns
	 */
	public HashMapMatrixImpl(Integer lines, Integer columns) {
		//TODO
	}

	/**
	 * Initializes the matrix with the {@code values} param. The matrix will have the same size as the {@code values} param
	 * @param values
	 */
	public HashMapMatrixImpl(BigDecimal[][] values) {

	}

	public Integer getLines() {
		return lines;
	}

	public Integer getColumns() {
		return columns;
	}

	@Override
	public BigDecimal getValue(Coordinate coord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getValue(Integer line, Integer column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(Coordinate coord, BigDecimal value) {
		// TODO Auto-generated method stub

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

}
