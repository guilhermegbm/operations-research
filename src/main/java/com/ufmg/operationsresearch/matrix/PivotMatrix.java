package com.ufmg.operationsresearch.matrix;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PivotMatrix {

	/*
	 * Given a matrix m, turns the element in the position [l, c] the pivot of the column c
	 * (the value of [l, c] will be one and all other values of the column c will be zero)
	 * using simple line*scalar multiplications and line additions.  
	 */
	public static void pivotCoordinate(Matrix m, Integer l, Integer c) {
		m.checkIfLineAndColumnExists(l, c);

		//Turning the [l, c] element equal to 1
		m.multiplyLineByScalar(l, BigDecimal.ONE.divide(m.getValue(l, c), 8, RoundingMode.HALF_UP));

		//Turning all other elements on column c equal to zero
		for (int otherLine = 1; otherLine <= m.getLines(); otherLine++) {
			if (otherLine == l) {
				continue;
			}

			BigDecimal valueOfElementToBeZeroed = m.getValue(otherLine, c);

			m.addMultipliedLineToTargetLine(otherLine, valueOfElementToBeZeroed.multiply(new BigDecimal("-1")), l);
		}
	}

}
