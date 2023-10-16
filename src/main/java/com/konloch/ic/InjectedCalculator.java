package com.konloch.ic;

import com.konloch.ic.calculator.Calculator;
import com.konloch.ic.calculator.injector.CalculatorInjector;
import com.konloch.ic.calculator.expression.ExpressionEvaluator;

/**
 * "But its also missing code, so it injects what its missing"
 *
 * @author Konloch
 * @since 10/15/2023
 */
public class InjectedCalculator
{
	private final ExpressionEvaluator evaluator;
	
	public InjectedCalculator(Calculator calculator)
	{
		this.evaluator = new ExpressionEvaluator(calculator);
	}
	
	public static void main(String[] args)
	{
		InjectedCalculator calculator = new InjectedCalculator(new CalculatorInjector().inject());
		System.out.println(calculator.evaluator.evaluateExpression(String.join(" ", args)));
	}
	
	public long evaluateExpression(String expression)
	{
		return evaluator.evaluateExpression(expression);
	}
}