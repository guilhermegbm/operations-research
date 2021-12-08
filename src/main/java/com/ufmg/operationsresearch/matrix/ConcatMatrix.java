package com.ufmg.operationsresearch.matrix;

import java.security.InvalidParameterException;

public class ConcatMatrix {

	public static Matrix concatRight(Matrix m1, Matrix m2) {
		if (m1.getLines() != m2.getLines()) {
			throw new InvalidParameterException("Number of lines on m1 (" + m1.getLines() + ") is different from the number of lines on the other matrix (" + m2.getLines() + ")");
		}

		Matrix newMatrix = m1.duplicate();

		Integer originalNumberOfColumns = newMatrix.getColumns();

		newMatrix.setNumberOfColumns(originalNumberOfColumns + m2.getColumns());

		for (int l = 1; l <= newMatrix.getLines(); l++) {
			for (int m2Col = 1; m2Col <= m2.getColumns(); m2Col++) {
				newMatrix.setValue(l, originalNumberOfColumns + m2Col, m2.getValue(l, m2Col));
			}
		}

		return newMatrix;
	}
}
