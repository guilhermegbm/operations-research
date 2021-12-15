package com.ufmg.operationsresearch.matrix;

import java.security.InvalidParameterException;

public class Coordinate implements Comparable<Coordinate> {

	//The line value of this coordinate. Starts on 1
	private Integer line;

	//The column value of this coordinate. Starts on 1
	private Integer column;

	public Coordinate(Integer line, Integer column) {
		if (line == null) {
			throw new InvalidParameterException("The line value can not be null");
		}

		if (column == null) {
			throw new InvalidParameterException("The column value can not be null");
		}

		if (line < 1) {
			throw new InvalidParameterException("The line value must be greater than or equal to 1");
		}

		if (column < 1) {
			throw new InvalidParameterException("The column value must be greater than or equal to 1");
		}

		this.line = line;
		this.column = column;
	}

	public Integer getLine() {
		return line;
	}

	public Integer getColumn() {
		return column;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((column == null) ? 0 : column.hashCode());
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Coordinate other = (Coordinate) obj;
		//if (column == null && other.column != null) { Its impossible to build a coordinate with null column
		if (column != other.column) {
			return false;
		}

		//if (line == null && other.line != null) { Its impossible to build a coordinate with null line
		if (line != other.line) {
			return false;
		}

		return true;
	}

	/**
	 * Returns -1 if the position represented by {@code this} is upwards if compared to {@code other}.
	 * Returns 1 if the position represented by {@code this} is downwards if compared to {@code other}.
	 * If {@code this} and {@code other} are on the same line:  
	 * returns -1 if the position represented by {@code this} is leftwards if compared to {@code other}.
	 * Returns 1 if the position represented by {@code this} is rightwards if compared to {@code other}.
	 * If both {@code this} and {@code other} are on the same line and column, return 0
	 */
	@Override
	public int compareTo(Coordinate other) {
		if (this.line < other.line) {
			return -1;
		} else if (this.line == other.line) {
			if (this.column < other.column) {
				return -1;
			} else if (this.column == other.column) {
				return 0;
			} else { //this.column > other.column
				return 1;
			}
		} else { //this.line > other.line
			return 1;
		}
	}
}
