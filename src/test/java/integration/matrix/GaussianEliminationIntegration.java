package integration.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.ufmg.operationsresearch.matrix.ConcatMatrix;
import com.ufmg.operationsresearch.matrix.Matrix;
import com.ufmg.operationsresearch.matrix.PivotMatrix;
import com.ufmg.operationsresearch.matrix.hashmap_implementation.HashMapMatrixImpl;

public class GaussianEliminationIntegration {

	/*
	 * Consider the following linear system:
	 * 
	 * {x + y = 2
	 * {2x - y + z = 4
	 * {-x + z = 0
	 * 
	 * In its matricial form:
	 * 
	 * | 1    1  +  0  /  2 |
	 * | 2   -1  +  3  \  4 |
	 * |-1    0     1  /  0 |
	 *        ^			  ^
	 *        |			  |
	 *        X           b
	 */
	@Test
	public void testGaussianEliminationWorksWithBasicMatrixOperations() {
		BigDecimal coeficientMatrixVet[][] = { { new BigDecimal("1"), new BigDecimal("1"), new BigDecimal("0") }, { new BigDecimal("2"), new BigDecimal("-1"), new BigDecimal("3") }, { new BigDecimal("-1"), new BigDecimal("0"), new BigDecimal("1") } };
		BigDecimal bVet[][] = { { new BigDecimal("2") }, { new BigDecimal("4") }, { new BigDecimal("0") } };

		Matrix x = new HashMapMatrixImpl(coeficientMatrixVet);
		Matrix b = new HashMapMatrixImpl(bVet);

		//Concatenating X and b
		Matrix extendedMatrix = ConcatMatrix.concatRight(x, b);

		String expectedMatrixString = "|1 1 0 2 |\n" + "|2 -1 3 4 |\n" + "|-1 0 1 0 |";
		assertEquals(expectedMatrixString, extendedMatrix.toString());

		//Making the first pivoting of X[1, 1]
		PivotMatrix.pivotCoordinate(extendedMatrix, 1, 1);

		expectedMatrixString = "|1 1 0 2 |\n" + "|0 -3 3 0 |\n" + "|0 1 1 2 |";
		assertEquals(expectedMatrixString, extendedMatrix.toString());

		//Making the second pivoting of X[2, 2]
		PivotMatrix.pivotCoordinate(extendedMatrix, 2, 2);

		expectedMatrixString = "|1 0 1 2 |\n" + "|0 1 -1 0 |\n" + "|0 0 2 2 |";
		assertEquals(expectedMatrixString, extendedMatrix.toString());

		//Making the third and last pivoting of X[3, 3]
		PivotMatrix.pivotCoordinate(extendedMatrix, 3, 3);

		expectedMatrixString = "|1 0 0 1 |\n" + "|0 1 0 1 |\n" + "|0 0 1 1 |";
		assertEquals(expectedMatrixString, extendedMatrix.toString());
	}
}
