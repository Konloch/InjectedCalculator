package com.konloch.uec;

import com.konloch.uec.calculator.Calculator;
import com.konloch.uec.calculator.builder.CalculatorBuilder;
import com.konloch.uec.calculator.expression.ExpressionEvaluator;

/**
 * "But its also missing code, so it injects what its missing"
 *
 * @author Konloch
 * @since 10/15/2023
 */
public class UnderEngineeredCalculator
{
	private final ExpressionEvaluator evaluator;
	
	public UnderEngineeredCalculator(Calculator calculator)
	{
		this.evaluator = new ExpressionEvaluator(calculator);
	}
	
	public static void main(String[] args)
	{
		UnderEngineeredCalculator calculator = new UnderEngineeredCalculator(new CalculatorBuilder().build());
		System.out.println(calculator.evaluator.evaluateExpression(String.join(" ", args)));
	}
	
	public long evaluateExpression(String expression)
	{
		return evaluator.evaluateExpression(expression);
	}
}