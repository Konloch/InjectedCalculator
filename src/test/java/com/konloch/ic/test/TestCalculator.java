package com.konloch.ic.test;

import com.konloch.ic.InjectedCalculator;
import com.konloch.ic.calculator.injector.CalculatorInjector;

/**
 * @author Konloch
 * @since 10/16/2023
 */
public class TestCalculator
{
	public static void main(String[] args)
	{
		InjectedCalculator calculator = new InjectedCalculator(new CalculatorInjector().inject());
		
		//expressions without parentheses
		assertEquals(7, calculator.evaluateExpression("3 + 2 * 2"));
		assertEquals(20, calculator.evaluateExpression("4 * 5"));
		assertEquals(5, calculator.evaluateExpression("2 + 6 / 2"));
		assertEquals(25, calculator.evaluateExpression("5 * 5"));
		
		//expressions with multiple operations
		assertEquals(11, calculator.evaluateExpression("1 + 2 * 5"));
		assertEquals(17, calculator.evaluateExpression("3 * 5 + 2"));
		assertEquals(11, calculator.evaluateExpression("10 / 2 + 6"));
		
		//expressions with subtraction
		assertEquals(1, calculator.evaluateExpression("4 - 3"));
		assertEquals(6, calculator.evaluateExpression("10 - 4"));
		assertEquals(0, calculator.evaluateExpression("3 - 3"));
		
		//expressions with mixed operations
		assertEquals(6, calculator.evaluateExpression("1 + 2 * 3 - 1"));
		assertEquals(10, calculator.evaluateExpression("2 * 3 + 5 - 2 / 2"));
		
		//expressions with larger numbers
		assertEquals(1001, calculator.evaluateExpression("1000 + 1"));
		assertEquals(1000000, calculator.evaluateExpression("1000 * 1000"));
		
		//expressions with division
		assertEquals(2, calculator.evaluateExpression("10 / 5"));
		assertEquals(2, calculator.evaluateExpression("8 / 3"));
	}
	
	public static void assertEquals(long expected, long actual)
	{
		if (expected != actual)
			throw new AssertionError("Expected: " + expected + ", Actual: " + actual);
	}
}
