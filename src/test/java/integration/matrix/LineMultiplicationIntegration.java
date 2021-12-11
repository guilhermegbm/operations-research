package integration.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.ufmg.operationsresearch.matrix.Matrix;
import com.ufmg.operationsresearch.matrix.hashmap_implementation.HashMapMatrixImpl;

public class LineMultiplicationIntegration {

	@Test
	public void testManyLineMultiplicationsOnMatrix() {
		BigDecimal aVet[][] = { { new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3") }, { new BigDecimal("-1"), new BigDecimal("4"), new BigDecimal("2") }, { new BigDecimal("2"), new BigDecimal("-2"), new BigDecimal("-1") } };
		Matrix a = new HashMapMatrixImpl(aVet);

		a.multiplyLineByScalar(1, new BigDecimal("1"));
		a.multiplyLineByScalar(2, new BigDecimal("2"));
		a.multiplyLineByScalar(3, new BigDecimal("-1"));

		String expectedMatrixString = "|1 2 3 |\n" + "|-2 8 4 |\n" + "|-2 2 1 |";
		assertEquals(expectedMatrixString, a.toString());

		a.multiplyLineByScalar(1, new BigDecimal("0"));
		a.multiplyLineByScalar(2, new BigDecimal("0.25"));
		a.multiplyLineByScalar(3, new BigDecimal("-1"));

		expectedMatrixString = "|0 0 0 |\n" + "|-0.5 2 1 |\n" + "|2 -2 -1 |";
		assertEquals(expectedMatrixString, a.toString());

	}
}
