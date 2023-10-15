package com.konloch.uec;

import com.konloch.uec.calculator.Calculator;
import com.konloch.uec.calculator.builder.CalculatorBuilder;

/**
 * "But its also missing code, so it injects what its missing"
 *
 * @author Konloch
 * @since 10/15/2023
 */
public class UnderEngineeredCalculator
{
	public static void main(String[] args)
	{
		Calculator uec = new CalculatorBuilder().build();
		
		long addition = uec.add(1, 1);
		long subtraction = uec.sub(1, 1);
		long multiplication = uec.mul(2, 2);
		long divide = uec.div(2, 2);
		
		System.out.println("Tests: " + addition + ", " + subtraction + ", " + multiplication + ", " + divide);
	}
}