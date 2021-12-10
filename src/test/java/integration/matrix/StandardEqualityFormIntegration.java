package integration.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.ufmg.operationsresearch.matrix.ConcatMatrix;
import com.ufmg.operationsresearch.matrix.Matrix;
import com.ufmg.operationsresearch.matrix.hashmap_implementation.HashMapMatrixImpl;

public class StandardEqualityFormIntegration {

	/*
	 * Given the following LP
	 * 
	 *   max		( -1, 2, 4) x
	 * 
	 *   subj to	| 1   5   3 |   | x1 |  >= | 5 |
	 *   			| 2  -1   2 | . | x2 |  <= | 4 |
	 *   			| 1   2  -1 |   | x3 |   = | 2 |
	 *   			x1, x2 >= 0
	 *   
	 *   We will execute a procedure to convert the LP above to the SEF
	 */

	@Test
	public void testProcedureToConvertLPToStandardEqualityForm() {
		//Original Objective function:
		BigDecimal objFunVet[][] = { { new BigDecimal("-1"), new BigDecimal("2"), new BigDecimal("-4") } };
		Matrix objFun = new HashMapMatrixImpl(objFunVet);

		//Original matrix "A":
		BigDecimal aVet[][] = { { new BigDecimal("1"), new BigDecimal("5"), new BigDecimal("3") }, { new BigDecimal("2"), new BigDecimal("-1"), new BigDecimal("2") }, { new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("-1") } };
		Matrix a = new HashMapMatrixImpl(aVet);

		//We replace 'min' by 'max' as follows
		objFun.multiplyLineByScalar(1, new BigDecimal("-1"));

		String expectedMatrixString = "|1 -2 4 |";
		assertEquals(expectedMatrixString, objFun.toString());

		//x3 is a free variable (x3 can be < 0)
		//We can make x3 = x3' - x3'', where x3', x3'' >= 0 and rewrite the objective function and the "A" matrix with these new variables:

		BigDecimal newVariableVet[][] = { { new BigDecimal("-4") } };
		BigDecimal newVectorForAVet[][] = { { new BigDecimal("-3") }, { new BigDecimal("-2") }, { new BigDecimal("1") } };
		Matrix newVariable = new HashMapMatrixImpl(newVariableVet);
		Matrix newVectorForA = new HashMapMatrixImpl(newVectorForAVet);

		objFun = ConcatMatrix.concatRight(objFun, newVariable);
		a = ConcatMatrix.concatRight(a, newVectorForA);

		expectedMatrixString = "|1 -2 4 -4 |";
		assertEquals(expectedMatrixString, objFun.toString());

		expectedMatrixString = "|1 5 3 -3 |\n" + "|2 -1 2 -2 |\n" + "|1 2 -1 1 |";
		assertEquals(expectedMatrixString, a.toString());

		//To replace the inequality <= in line L2, we will introduce a new slack variable x4:

		BigDecimal newVariableX4Vet[][] = { { new BigDecimal("0") } };
		BigDecimal newVectorForAX4Vet[][] = { { new BigDecimal("0") }, { new BigDecimal("1") }, { new BigDecimal("0") } };
		Matrix newVariableX4 = new HashMapMatrixImpl(newVariableX4Vet);
		Matrix newVectorForAX4 = new HashMapMatrixImpl(newVectorForAX4Vet);

		objFun = ConcatMatrix.concatRight(objFun, newVariableX4);
		a = ConcatMatrix.concatRight(a, newVectorForAX4);

		expectedMatrixString = "|1 -2 4 -4 0 |";
		assertEquals(expectedMatrixString, objFun.toString());

		expectedMatrixString = "|1 5 3 -3 0 |\n" + "|2 -1 2 -2 1 |\n" + "|1 2 -1 1 0 |";
		assertEquals(expectedMatrixString, a.toString());

		//To replace the inequality >= in line L1, we will introduce a new slack variable x5:

		BigDecimal newVariableX5Vet[][] = { { new BigDecimal("0") } };
		BigDecimal newVectorForAX5Vet[][] = { { new BigDecimal("-1") }, { new BigDecimal("0") }, { new BigDecimal("0") } };
		Matrix newVariableX5 = new HashMapMatrixImpl(newVariableX5Vet);
		Matrix newVectorForAX5 = new HashMapMatrixImpl(newVectorForAX5Vet);

		objFun = ConcatMatrix.concatRight(objFun, newVariableX5);
		a = ConcatMatrix.concatRight(a, newVectorForAX5);

		expectedMatrixString = "|1 -2 4 -4 0 0 |";
		assertEquals(expectedMatrixString, objFun.toString());

		expectedMatrixString = "|1 5 3 -3 0 -1 |\n" + "|2 -1 2 -2 1 0 |\n" + "|1 2 -1 1 0 0 |";
		assertEquals(expectedMatrixString, a.toString());
		
		//We now have the LP max {c^T . x : Ax = b, x >= 0}
	}
}
