package com.ufmg.operationsresearch.matrix;

import java.security.InvalidParameterException;

public class Coordinate {

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
		if (column == null && other.column != null) {
			return false;
		} else if (column != other.column) {
			return false;
		}

		if (line == null && other.line != null) {
			return false;
		} else if (line != other.line) {
			return false;
		}

		return true;
	}
}
