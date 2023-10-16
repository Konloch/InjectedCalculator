package com.konloch.ic.calculator.expression;

import com.konloch.ic.calculator.Calculator;

import java.util.Stack;

/**
 * @author Konloch
 * @since 10/16/2023
 */
public class ExpressionEvaluator
{
	private final Calculator calculator;
	
	public ExpressionEvaluator(Calculator calculator)
	{
		this.calculator = calculator;
	}
	
	public long evaluateExpression(String expression)
	{
		Stack<Long> operands = new Stack<>();
		Stack<Character> operators = new Stack<>();
		char[] chars = expression.toCharArray();
		
		StringBuilder numBuffer = new StringBuilder();
		
		for (char c : chars)
		{
			if (Character.isDigit(c))
				numBuffer.append(c);
			else if (c == '+' || c == '-' || c == '*' || c == '/')
			{
				if(numBuffer.length() > 0)
				{
					operands.push(Long.parseLong(numBuffer.toString()));
					numBuffer = new StringBuilder();
				}
				
				while (!operators.isEmpty() && hasPrecedence(c, operators.peek()))
				{
					evaluateStackTop(operands, operators);
				}
				
				operators.push(c);
			}
		}
		
		if(numBuffer.length() > 0)
			operands.push(Long.parseLong(numBuffer.toString()));
		
		while (!operators.isEmpty())
		{
			evaluateStackTop(operands, operators);
		}
		
		return operands.pop();
	}
	
	private void evaluateStackTop(Stack<Long> operands, Stack<Character> operators)
	{
		char operator = operators.pop();
		long operand2 = operands.pop();
		long operand1 = operands.pop();
		operands.push(evaluateOperation(operator, operand1, operand2));
	}
	
	private long evaluateOperation(char operator, long operand1, long operand2)
	{
		long result = 0;
		
		switch (operator)
		{
			case '+':
				result = calculator.add(operand1, operand2);
				break;
			
			case '-':
				result = calculator.sub(operand1, operand2);
				break;
			
			case '*':
				result = calculator.mul(operand1, operand2);
				break;
			
			case '/':
				result = calculator.div(operand1, operand2);
				break;
		}
		
		return result;
	}
	
	private boolean hasPrecedence(char op1, char op2)
	{
		return (op2 == '*' || op2 == '/') && (op1 == '+' || op1 == '-');
	}
}
