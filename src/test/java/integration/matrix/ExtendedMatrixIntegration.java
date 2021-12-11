package integration.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.ufmg.operationsresearch.matrix.ConcatMatrix;
import com.ufmg.operationsresearch.matrix.Matrix;
import com.ufmg.operationsresearch.matrix.hashmap_implementation.HashMapMatrixImpl;

public class ExtendedMatrixIntegration {

	@Test
	public void testMatrixCanBeConcatenatedAndExtended() {
		BigDecimal cVet[][] = { { new BigDecimal("2"), new BigDecimal("0"), new BigDecimal("-1") } };
		Matrix c = new HashMapMatrixImpl(cVet);

		BigDecimal aVet[][] = { { new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3") }, { new BigDecimal("-1"), new BigDecimal("4"), new BigDecimal("2") }, { new BigDecimal("2"), new BigDecimal("-2"), new BigDecimal("-1") } };
		Matrix a = new HashMapMatrixImpl(aVet);

		Matrix extendedMatrix = ConcatMatrix.concatUp(a, c);

		String expectedMatrixString = "|2 0 -1 |\n" + "|1 2 3 |\n" + "|-1 4 2 |\n" + "|2 -2 -1 |";
		assertEquals(expectedMatrixString, extendedMatrix.toString());

		BigDecimal x1Vet[][] = { { new BigDecimal("0") }, { new BigDecimal("1") }, { new BigDecimal("-1") }, { new BigDecimal("4") } };
		Matrix x1 = new HashMapMatrixImpl(x1Vet);

		BigDecimal x2Vet[][] = { { new BigDecimal("0") }, { new BigDecimal("3") }, { new BigDecimal("3") }, { new BigDecimal("-2") } };
		Matrix x2 = new HashMapMatrixImpl(x2Vet);

		BigDecimal x3Vet[][] = { { new BigDecimal("0") }, { new BigDecimal("-1") }, { new BigDecimal("0") }, { new BigDecimal("-1") } };
		Matrix x3 = new HashMapMatrixImpl(x3Vet);

		extendedMatrix = ConcatMatrix.concatRight(extendedMatrix, x1);
		extendedMatrix = ConcatMatrix.concatRight(extendedMatrix, x2);
		extendedMatrix = ConcatMatrix.concatRight(extendedMatrix, x3);

		expectedMatrixString = "|2 0 -1 0 0 0 |\n" + "|1 2 3 1 3 -1 |\n" + "|-1 4 2 -1 3 0 |\n" + "|2 -2 -1 4 -2 -1 |";
		assertEquals(expectedMatrixString, extendedMatrix.toString());

		BigDecimal leftSquareMVet[][] = { { new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0") }, { new BigDecimal("0"), new BigDecimal("1"), new BigDecimal("0"), new BigDecimal("0") },
				{ new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("1"), new BigDecimal("0") }, { new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("1") } };
		Matrix leftSquareM = new HashMapMatrixImpl(leftSquareMVet);

		extendedMatrix = ConcatMatrix.concatLeft(extendedMatrix, leftSquareM);

		expectedMatrixString = "|0 0 0 0 2 0 -1 0 0 0 |\n" + "|0 1 0 0 1 2 3 1 3 -1 |\n" + "|0 0 1 0 -1 4 2 -1 3 0 |\n" + "|0 0 0 1 2 -2 -1 4 -2 -1 |";
		assertEquals(expectedMatrixString, extendedMatrix.toString());

	}
}
