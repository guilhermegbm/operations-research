package integration.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.ufmg.operationsresearch.matrix.Matrix;
import com.ufmg.operationsresearch.matrix.hashmap_implementation.HashMapMatrixImpl;

public class AddMultipliedLineToTargetLineIntegration {

	@Test
	public void testMultipleAddMultipliedLineToTargetLineOnMatrix() {
		BigDecimal aVet[][] = { { new BigDecimal("1"), new BigDecimal("0"), new BigDecimal("0") }, { new BigDecimal("0"), new BigDecimal("1"), new BigDecimal("0") }, { new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("1") } };
		Matrix a = new HashMapMatrixImpl(aVet);

		a.addMultipliedLineToTargetLine(2, new BigDecimal("5"), 1);
		a.addMultipliedLineToTargetLine(3, new BigDecimal("-2"), 1);

		String expectedMatrixString = "|1 0 0 |\n" + "|5 1 0 |\n" + "|-2 0 1 |";
		assertEquals(expectedMatrixString, a.toString());
		
		a.addMultipliedLineToTargetLine(1, new BigDecimal("-1"), 2);
		a.addMultipliedLineToTargetLine(3, new BigDecimal("2"), 2);

		expectedMatrixString = "|-4 -1 0 |\n" + "|5 1 0 |\n" + "|8 2 1 |";
		assertEquals(expectedMatrixString, a.toString());
		
		a.addMultipliedLineToTargetLine(1, new BigDecimal("0.5"), 3);
		a.addMultipliedLineToTargetLine(2, new BigDecimal("-0.25"), 3);

		expectedMatrixString = "|0 0 0.5 |\n" + "|3 0.5 -0.25 |\n" + "|8 2 1 |";
		assertEquals(expectedMatrixString, a.toString());
	}
}
